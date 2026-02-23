import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;


class Quick_Calc_Test {

    // UNIT TESTS

    @Nested
    @DisplayName("Addition")
    class AdditionTests {

        @Test
        @DisplayName("Two positive integers sum correctly")
        void add_twoPositiveIntegers() {
            String result = CalculatorLogic.calculate("+", big(5), big(3));
            assertEquals("8", result);
        }

        @Test
        @DisplayName("Positive and negative integer sum correctly")
        void add_positiveAndNegative() {
            String result = CalculatorLogic.calculate("+", big(-7), big(10));
            assertEquals("3", result);
        }

        @Test
        @DisplayName("Two negative integers sum correctly")
        void add_twoNegativeIntegers() {
            String result = CalculatorLogic.calculate("+", big(-4), big(-6));
            assertEquals("-10", result);
        }
    }

    @Nested
    @DisplayName("Subtraction")
    class SubtractionTests {

        @Test
        @DisplayName("Subtraction correctly yields a positive result")
        void subtract_positiveResult() {
            String result = CalculatorLogic.calculate("-", big(15), big(6));
            assertEquals("9", result);
        }

        @Test
        @DisplayName("Subtraction correctly yields a negative result")
        void subtract_negativeResult() {
            String result = CalculatorLogic.calculate("-", big(3), big(10));
            assertEquals("-7", result);
        }

        @Test
        @DisplayName("Subtracting a negative is equivalent to addition")
        void subtract_negativeFromPositive() {
            String result = CalculatorLogic.calculate("-", big(5), big(-3));
            assertEquals("8", result);
        }
    }

    @Nested
    @DisplayName("Multiplication")
    class MultiplicationTests {

        @Test
        @DisplayName("Two positive integers multiply correctly")
        void multiply_twoPositiveIntegers() {
            String result = CalculatorLogic.calculate("x", big(6), big(7));
            assertEquals("42", result);
        }

        @Test
        @DisplayName("Two negative integers correctly produce a positive product")
        void multiply_twoNegativeIntegers() {
            String result = CalculatorLogic.calculate("x", big(-3), big(-4));
            assertEquals("12", result);
        }

        @Test
        @DisplayName("Any number multiplied by zero is zero")
        void multiply_byZero() {
            String result = CalculatorLogic.calculate("x", big(999), big(0));
            assertEquals("0", result);
        }
    }

    @Nested
    @DisplayName("Division")
    class DivisionTests {

        @Test
        @DisplayName("Even division produces a whole-number result")
        void divide_evenDivision() {
            String result = CalculatorLogic.calculate("/", big(10), big(2));
            assertEquals("5", result);
        }

        @Test
        @DisplayName("Non-even division produces a decimal result (10 decimal places)")
        void divide_decimalResult() {
            String result = CalculatorLogic.calculate("/", big(10), big(3));
            assertEquals("3.3333333333", result);
        }

        @Test
        @DisplayName("Dividing a negative by a positive produces a negative result")
        void divide_negativeByPositive() {
            String result = CalculatorLogic.calculate("/", big(-9), big(3));
            assertEquals("-3", result);
        }
    }

    @Nested
    @DisplayName("Edge cases")
    class EdgeCaseTests {

        @Test
        @DisplayName("Edge Case: Division by zero throws ArithmeticException")
        void divideByZero_throwsArithmeticException() {
            ArithmeticException ex = assertThrows(
                    ArithmeticException.class,
                    () -> CalculatorLogic.calculate("/", big(42), big(0))
            );
            assertTrue(ex.getMessage().toLowerCase().contains("zero"), "Exception message should mention 'zero'");
        }

        @Test
        @DisplayName("Edge Case: Very large numbers (18-digit operands) are handled by BigInteger")
        void add_veryLargeNumbers() {
            // Max allowed inputs: 18-digit numbers
            BigInteger a = new BigInteger("999999999999999999"); // 18 nines
            BigInteger b = new BigInteger("999999999999999999");
            String result = CalculatorLogic.calculate("x", a, b);
            assertEquals("999999999999999998000000000000000001", result);
        }

        @Test
        @DisplayName("Edge Case: Unknown operator throws IllegalArgumentException")
        void unknownOperator_throwsIllegalArgumentException() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> CalculatorLogic.calculate("%", big(5), big(2))
            );
        }
    }


    // INTEGRATION TESTS 

    @Nested
    @DisplayName("Integration: UI and CalculatorLogic")
    class IntegrationTests {

        private Quick_Calc calc;

        @BeforeEach
        void setUp() throws Exception {
            //create the hidden frame
            AtomicReference<Quick_Calc> ref = new AtomicReference<>();
            SwingUtilities.invokeAndWait(() -> ref.set(new Quick_Calc(false)));
            calc = ref.get();
        }

        //dispose of the hidden frame
        @AfterEach
        void tearDown() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                if (calc != null) {
                    calc.dispose();
                }
            });
        }

        //IT-1: simultes inputting 5 and 3, clicking the "Add" button, and clicking the "Calculte" button
        @Test
        @DisplayName("IT-1: Full addition workflow produces correct result in result field")
        void integration_Addition() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                calc.getNumField1().setText("5");
                calc.getNumField2().setText("3");
                clickButton(calc.getAddButton());
                clickButton(calc.getEqualsButton());
            });

            SwingUtilities.invokeAndWait(() -> {
                assertEquals("8", calc.getResultField().getText(), "Result field should display '8' after adding 5 and 3");
            });
        }

        //IT-2: simultes inputting 10 and 4, clicking the "Sub" button, clicking the "Calculte" button, and clicking the "Clear" button
        @Test
        @DisplayName("IT-2: Pressing CLEAR after a calculation resets all fields to empty")
        void integration_clearResetsAllFields() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                calc.getNumField1().setText("10");
                calc.getNumField2().setText("4");
                clickButton(calc.getSubButton());
                clickButton(calc.getEqualsButton());
            });

            //confirm the calculation result is correct
            SwingUtilities.invokeAndWait(() -> assertEquals("6", calc.getResultField().getText()));

            //clear and verify that all the fields are empty
            SwingUtilities.invokeAndWait(() -> clickButton(calc.getClearButton()));

            SwingUtilities.invokeAndWait(() -> {
                assertEquals("", calc.getNumField1().getText(), "numField1 should be empty after CLEAR");
                assertEquals("", calc.getNumField2().getText(), "numField2 should be empty after CLEAR");
                assertEquals("", calc.getResultField().getText(), "resultField should be empty after CLEAR");
            });
        }

        //IT-3: simultes inputting -8 and 3, clicking the "Mul" button, and clicking the "Calculte" button
        @Test
        @DisplayName("IT-3: Negative number flows through UI and calculation correctly")
        void integration_negativeNumberMultiplication() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                calc.getNumField1().setText("-8");
                calc.getNumField2().setText("3");
                clickButton(calc.getMulButton());
                clickButton(calc.getEqualsButton());
            });

            SwingUtilities.invokeAndWait(() ->
                    assertEquals("-24", calc.getResultField().getText(), "Result field should display '-24' after -8 * 3"));
        }

        //IT-4: simultes inputting 16 and 15, clicking the "Div" button, and clicking the "Calculte" button
        @Test
        @DisplayName("IT-3: Negative number flows through UI and calculation correctly")
        void integration_Division() throws Exception {
            SwingUtilities.invokeAndWait(() -> {
                calc.getNumField1().setText("16");
                calc.getNumField2().setText("15");
                clickButton(calc.getDivButton());
                clickButton(calc.getEqualsButton());
            });

            SwingUtilities.invokeAndWait(() ->
                    assertEquals("1.0666666667", calc.getResultField().getText(), "Result field should display '1.0666666667' after 16 / 15"));
        }

        //simulate a button press
        private void clickButton(JButton button) {
            button.getActionListeners()[0].actionPerformed(
                    new ActionEvent(button, ActionEvent.ACTION_PERFORMED, button.getActionCommand())
            );
        }
    }

    //helper
    private static BigInteger big(long value) {
        return BigInteger.valueOf(value);
    }
}
