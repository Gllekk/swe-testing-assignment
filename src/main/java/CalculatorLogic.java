import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;


// internal calulation logic
public class CalculatorLogic {
    public static String calculate(String operator, BigInteger n1, BigInteger n2) {
        switch (operator) {
            case "+":
                return n1.add(n2).toString();

            case "-":
                return n1.subtract(n2).toString();

            case "x":
                return n1.multiply(n2).toString();

            case "/":
                if (n2.equals(BigInteger.ZERO)) {
                    throw new ArithmeticException("Division by zero");
                }
                BigDecimal result = new BigDecimal(n1).divide(new BigDecimal(n2), 10, RoundingMode.HALF_UP).stripTrailingZeros();
                return result.toPlainString();

            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}