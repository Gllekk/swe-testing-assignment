import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.border.Border;


public class Quick_Calc extends JFrame {
    private JFrame frame = this;
    private JLabel label;
    private JFormattedTextField numField1;
    private JFormattedTextField numField2;
    private JFormattedTextField resultField;
    private JButton add;
    private JButton sub;
    private JButton mul;
    private JButton div;
    private JButton equals;
    private JButton clear;
    private String operator;

    //normal entry-point constructor
    public Quick_Calc() {
        this(true);
    }

    //package-private constructor 
    Quick_Calc(boolean visible) {
        this.setName("Quick Calculator");
        this.setTitle("Quick Calculator");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(300, 265);
        this.getContentPane().setBackground(new Color(18, 33, 36));
        Border border1 = BorderFactory.createLineBorder(new Color(130, 193, 206), 1);
        Border border2 = BorderFactory.createLineBorder(new Color(130, 193, 206), 2);

        //window closing dialog
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JDialog closingDialog = new JDialog(frame, "Attention!", true);
                closingDialog.setSize(262, 105);
                closingDialog.getContentPane().setBackground(new Color(18, 33, 36));

                //exit label 
                JLabel exitLabel = new JLabel("Are You Sure You Want To Exit?");
                exitLabel.setBounds(0, 5, 250, 20);
                exitLabel.setFont(new Font("Impact", Font.PLAIN, 13));
                exitLabel.setForeground(new Color(130, 193, 206));
                exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
                closingDialog.add(exitLabel);

                //yes button
                JButton yes = new JButton("YES");
                yes.setBounds(10, 45, 90, 20);
                yes.setFont(new Font("Impact", Font.BOLD, 13));
                yes.setForeground(new Color(207, 130, 162));
                yes.setBackground(new Color(18, 33, 36));
                yes.setBorder(border2);
                yes.setFocusable(false);
                yes.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        System.exit(0);
                    }
                });
                closingDialog.add(yes);

                //no button
                JButton no = new JButton("NO");
                no.setBounds(150, 45, 90, 20);
                no.setFont(new Font("Impact", Font.BOLD, 13));
                no.setForeground(new Color(207, 130, 162));
                no.setBackground(new Color(18, 33, 36));
                no.setBorder(border2);
                no.setFocusable(false);
                no.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        closingDialog.dispose();
                    }
                });
                closingDialog.add(no);

                closingDialog.setLayout(null);
                closingDialog.setLocationRelativeTo(frame);
                closingDialog.setVisible(true);
            }
        });

        //top label
        label = new JLabel("Enter The Integers And Choose An Operator");
        label.setBounds(1, 1, 286, 31);
        label.setFont(new Font("Impact", Font.PLAIN, 13));
        label.setForeground(new Color(130, 193, 206));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(border1);
        this.add(label);

        //number format
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);

        //first numfield
        numField1 = new JFormattedTextField(format);
        numField1.setColumns(13);
        numField1.setBounds(1, 32, 214, 55);
        numField1.setFont(new Font("Impact", Font.BOLD, 13));
        numField1.setForeground(new Color(207, 130, 162));
        numField1.setBackground(new Color(18, 33, 36));
        numField1.setBorder(border1);
        numField1.setCaretColor(new Color(207, 130, 162));
        numField1.addFocusListener(new DigitLimitAndResetFocusListener(18));
        this.add(numField1);

        //second numfield
        numField2 = new JFormattedTextField(format);
        numField2.setColumns(13);
        numField2.setBounds(1, 87, 214, 55);
        numField2.setFont(new Font("Impact", Font.BOLD, 13));
        numField2.setForeground(new Color(207, 130, 162));
        numField2.setBackground(new Color(18, 33, 36));
        numField2.setBorder(border1);
        numField2.setCaretColor(new Color(207, 130, 162));
        numField2.addFocusListener(new DigitLimitAndResetFocusListener(18));
        this.add(numField2);

        //result field
        resultField = new JFormattedTextField(format);
        resultField.setBounds(1, 182, 286, 45);
        resultField.setFont(new Font("Impact", Font.PLAIN, 13));
        resultField.setForeground(new Color(207, 130, 162));
        resultField.setBackground(new Color(18, 33, 36));
        resultField.setBorder(border1);
        resultField.setEditable(false);
        this.add(resultField);

        //addition button
        add = new JButton("ADD");
        add.setBounds(215, 32, 72, 27);
        add.setFont(new Font("Impact", Font.PLAIN, 13));
        add.setForeground(new Color(130, 193, 206));
        add.setBackground(new Color(18, 33, 36));
        add.setBorder(border1);
        add.setFocusable(false);
        add.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                operator = "+";
                add.setForeground(new Color(207, 130, 162));
                sub.setForeground(new Color(130, 193, 206));
                mul.setForeground(new Color(130, 193, 206));
                div.setForeground(new Color(130, 193, 206));
            }
        });
        this.add(add);

        //subtraction button
        sub = new JButton("SUB");
        sub.setBounds(215, 59, 72, 28);
        sub.setFont(new Font("Impact", Font.BOLD, 13));
        sub.setForeground(new Color(130, 193, 206));
        sub.setBackground(new Color(18, 33, 36));
        sub.setBorder(border1);
        sub.setFocusable(false);
        sub.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                operator = "-";
                sub.setForeground(new Color(207, 130, 162));
                add.setForeground(new Color(130, 193, 206));
                mul.setForeground(new Color(130, 193, 206));
                div.setForeground(new Color(130, 193, 206));
            }
        });
        this.add(sub);

        //multiplication button
        mul = new JButton("MUL");
        mul.setBounds(215, 87, 72, 27);
        mul.setFont(new Font("Impact", Font.BOLD, 13));
        mul.setForeground(new Color(130, 193, 206));
        mul.setBackground(new Color(18, 33, 36));
        mul.setBorder(border1);
        mul.setFocusable(false);
        mul.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                operator = "x";
                mul.setForeground(new Color(207, 130, 162));
                add.setForeground(new Color(130, 193, 206));
                sub.setForeground(new Color(130, 193, 206));
                div.setForeground(new Color(130, 193, 206));
            }
        });
        this.add(mul);

        //division button
        div = new JButton("DIV");
        div.setBounds(215, 114, 72, 28);
        div.setFont(new Font("Impact", Font.BOLD, 13));
        div.setForeground(new Color(130, 193, 206));
        div.setBackground(new Color(18, 33, 36));
        div.setBorder(border1);
        div.setFocusable(false);
        div.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                operator = "/";
                div.setForeground(new Color(207, 130, 162));
                add.setForeground(new Color(130, 193, 206));
                sub.setForeground(new Color(130, 193, 206));
                mul.setForeground(new Color(130, 193, 206));
            }
        });
        this.add(div);

        //clear button
        clear = new JButton("CLEAR");
        clear.setBounds(215, 142, 72, 40);
        clear.setFont(new Font("Impact", Font.BOLD, 13));
        clear.setForeground(new Color(207, 130, 162));
        clear.setBackground(new Color(18, 33, 36));
        clear.setBorder(border1);
        clear.setFocusable(false);
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                numField1.setText(null);
                numField2.setText(null);
                resultField.setText(null);
                frame.requestFocusInWindow();
            }
        });
        this.add(clear);

        //equals button
        equals = new JButton("CALCULATE");
        equals.setBounds(1, 142, 214, 40);
        equals.setFont(new Font("Impact", Font.BOLD, 13));
        equals.setForeground(new Color(207, 130, 162));
        equals.setBackground(new Color(18, 33, 36));
        equals.setBorder(border1);
        equals.setFocusable(false);

        //equals button action
        equals.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {

                if (numField1.getText().equals("") || numField2.getText().equals("")) {
                    okButtonDialog("One or Both Input Fields Are Empty!", "Please, Fill Both Fields Out!");
                    return;

                } else if (!numField1.getText().matches("^-?[0-9]+$") || !numField2.getText().matches("^-?[0-9]+$")) {
                    numField1.setText(numField1.getText().replaceAll("[^0-9-]", "").replaceAll("(?<=.)-", ""));
                    numField2.setText(numField2.getText().replaceAll("[^0-9-]", "").replaceAll("(?<=.)-", ""));
                    okButtonDialog("Only Integers Are Allowed!", "");
                    return;

                } else if (numField1.getText().replaceFirst("^-", "").length() > 18 || numField2.getText().replaceFirst("^-", "").length() > 18) {
                    okButtonDialog("Only Integers Of Length Less", "Than 19 Are Allowed!");
                    return;

                } else if (operator.equals("")) {
                    okButtonDialog("Please, Choose An Operator!", "");
                    return;

                } else {
                    BigInteger num1 = new BigInteger(numField1.getText());
                    BigInteger num2 = new BigInteger(numField2.getText());

                    try {
                        String result = CalculatorLogic.calculate(operator, num1, num2);
                        resultField.setText(result);
                    } catch (ArithmeticException ex) {
                        okButtonDialog("Division By Zero!", "The Divisor Cannot Be Zero!");
                    }
                }
            }

            private void okButtonDialog(String text1, String text2) {
                JDialog dialogNoOperator = new JDialog(frame, "Attention!", true);
                dialogNoOperator.setSize(262, 105);
                dialogNoOperator.getContentPane().setBackground(new Color(18, 33, 36));

                //dialog top label1
                JLabel label1 = new JLabel(text1);
                label1.setBounds(0, 5, 250, 20);
                label1.setFont(new Font("Impact", Font.PLAIN, 13));
                label1.setForeground(new Color(130, 193, 206));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                dialogNoOperator.add(label1);

                //dialog top label2
                JLabel label2 = new JLabel(text2);
                label2.setBounds(0, 20, 250, 20);
                label2.setFont(new Font("Impact", Font.PLAIN, 13));
                label2.setForeground(new Color(130, 193, 206));
                label2.setHorizontalAlignment(SwingConstants.CENTER);
                dialogNoOperator.add(label2);

                //ok button
                JButton ok = new JButton("OK");
                ok.setBounds(140, 45, 90, 20);
                ok.setFont(new Font("Impact", Font.BOLD, 13));
                ok.setForeground(new Color(207, 130, 162));
                ok.setBackground(new Color(18, 33, 36));
                ok.setBorder(border2);
                ok.setFocusable(false);
                ok.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        dialogNoOperator.dispose();
                    }
                });
                dialogNoOperator.add(ok);

                dialogNoOperator.setLayout(null);
                dialogNoOperator.setLocationRelativeTo(frame);
                dialogNoOperator.setVisible(true);
            }
        });
        this.add(equals);

        this.operator = "";

        this.validate();
        this.setLayout(null);
        this.setResizable(false);
        if (visible) {
            this.setVisible(true);
        }
    }


    //package-private accessors (for integration tests)
    JFormattedTextField getNumField1() { return numField1; }
    JFormattedTextField getNumField2() { return numField2; }
    JFormattedTextField getResultField() { return resultField; }
    JButton getAddButton() { return add; }
    JButton getSubButton() { return sub; }
    JButton getMulButton() { return mul; }
    JButton getDivButton() { return div; }
    JButton getEqualsButton() { return equals; }
    JButton getClearButton() { return clear; }
    String  getOperator() { return operator; }

    public static void main(String[] args) {
        new Quick_Calc();
    }
}


//focusListener for the input fields
class DigitLimitAndResetFocusListener implements FocusListener {
    private int limit;

    public DigitLimitAndResetFocusListener(int limit) {
        this.limit = limit;
    }

    @Override
    public void focusGained(FocusEvent e) {}

    @Override
    public void focusLost(FocusEvent e) {
        JFormattedTextField field = (JFormattedTextField) e.getSource();
        String text = field.getText();

        if (text.length() > limit) {
            field.setText(text.substring(0, limit));
        } else if (text.isEmpty()) {
            field.setValue(null);
        }
    }
}
