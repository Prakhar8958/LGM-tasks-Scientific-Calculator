package calci;




import java.util.Stack;

public class ExpressionEvaluator {
    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    private static double applyOperator(double a, double b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            case '^':
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public static double evaluate(String expression) {
        // Remove any spaces from the expression
        expression = expression.replaceAll("\\s+", "");

        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                values.push(Double.parseDouble(numBuilder.toString()));
            } else if (ch == '(') {
                operators.push(ch);
                i++;
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    double b = values.pop();
                    double a = values.pop();
                    values.push(applyOperator(a, b, operators.pop()));
                }
                operators.pop(); // Pop the opening parenthesis
                i++;
            } else {
                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    double b = values.pop();
                    double a = values.pop();
                    values.push(applyOperator(a, b, operators.pop()));
                }
                operators.push(ch);
                i++;
            }
        }

        while (!operators.isEmpty()) {
            double b = values.pop();
            double a = values.pop();
            values.push(applyOperator(a, b, operators.pop()));
        }

        return values.pop();
    }
}


