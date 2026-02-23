import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.border.Border;



public class Quick_Calc extends JFrame{
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


    public Quick_Calc() {
        this.setName("Quick Calculator");
        this.setTitle("Quick Calculator");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300, 265);
        this.getContentPane().setBackground(new Color(18, 33, 36));
        Border border = BorderFactory.createLineBorder(new Color(130, 193, 206), 1); //border

        //top label
        label = new JLabel("Enter The Numbers And Choose An Operator");
        label.setBounds(1, 1, 286, 31);
        label.setFont(new Font("Impact", Font.PLAIN, 13));
        label.setForeground(new Color(130, 193, 206));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(border);
        this.add(label);

        NumberFormat format = NumberFormat.getNumberInstance();
        format.setGroupingUsed(false);
        format.setMaximumFractionDigits(10);

        //first numfield
        numField1 = new JFormattedTextField(format);
        numField1.setColumns(13);
        numField1.setBounds(1, 32, 214, 55);
        numField1.setFont(new Font("Impact", Font.BOLD, 13));
        numField1.setForeground(new Color(207, 130, 162));
        numField1.setBackground(new Color(18, 33, 36));
        numField1.setBorder(border);
        numField1.setCaretColor(new Color(207, 130, 162));
        this.add(numField1);
        
        //second numfield
        numField2 = new JFormattedTextField(format);
        numField2.setColumns(13);
        numField2.setBounds(1, 87, 214, 55);
        numField2.setFont(new Font("Impact", Font.BOLD, 13));
        numField2.setForeground(new Color(207, 130, 162));
        numField2.setBackground(new Color(18, 33, 36));
        numField2.setBorder(border);
        numField2.setCaretColor(new Color(207, 130, 162));
        this.add(numField2);

        //result field
        resultField = new JFormattedTextField(format);
        resultField.setBounds(1, 182, 286, 45);
        resultField.setFont(new Font("Impact", Font.PLAIN, 13));
        resultField.setForeground(new Color(207, 130, 162));
        resultField.setBackground(new Color(18, 33, 36));
        resultField.setBorder(border);
        resultField.setEditable(false);
        this.add(resultField);

        //addition button
        add = new JButton("ADD");
        add.setBounds(215, 32, 72, 27);
        add.setFont(new Font("Impact", Font.PLAIN, 13));
        add.setForeground(new Color(130, 193, 206));
        add.setBackground(new Color(18, 33, 36));
        add.setBorder(border);
        add.setFocusable(false);
        this.add(add);

        //subtraction button
        sub = new JButton("SUB");
        sub.setBounds(215, 59, 72, 28);
        sub.setFont(new Font("Impact", Font.BOLD, 13));
        sub.setForeground(new Color(130, 193, 206));
        sub.setBackground(new Color(18, 33, 36));
        sub.setBorder(border);
        sub.setFocusable(false);
        this.add(sub);

        //multiplication button
        mul = new JButton("MUL");
        mul.setBounds(215, 87, 72, 27);
        mul.setFont(new Font("Impact", Font.BOLD, 13));
        mul.setForeground(new Color(130, 193, 206));
        mul.setBackground(new Color(18, 33, 36));
        mul.setBorder(border);
        mul.setFocusable(false);
        this.add(mul);

        //division button
        div = new JButton("DIV");
        div.setBounds(215, 114, 72, 28);
        div.setFont(new Font("Impact", Font.BOLD, 13));
        div.setForeground(new Color(130, 193, 206));
        div.setBackground(new Color(18, 33, 36));
        div.setBorder(border);
        div.setFocusable(false);
        this.add(div);

        //clear button
        clear = new JButton("CLEAR");
        clear.setBounds(215, 142, 72, 40);
        clear.setFont(new Font("Impact", Font.BOLD, 13));
        clear.setForeground(new Color(207, 130, 162));
        clear.setBackground(new Color(18, 33, 36));
        clear.setBorder(border);
        clear.setFocusable(false);
        this.add(clear);
        
        //equals button
        equals = new JButton("CALCULATE");
        equals.setBounds(1, 142, 214, 40);
        equals.setFont(new Font("Impact", Font.BOLD, 13));
        equals.setForeground(new Color(207, 130, 162));//pink
        equals.setBackground(new Color(18, 33, 36));
        equals.setBorder(border);
        equals.setFocusable(false);
        this.add(equals);
        
        this.validate();
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
    }


    public static void main(String[] args) {
        new Quick_Calc();
    }
}
