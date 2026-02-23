import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;

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
            assertTrue(ex.getMessage().toLowerCase().contains("zero"),
                    "Exception message should mention 'zero'");
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

    private static BigInteger big(long value) {
        return BigInteger.valueOf(value);
    }
}