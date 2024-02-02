public class Program {
    public static void main(String[] args) {
        if (args.length == 3) { // 3 + 4
            int firstOperand = 0;
            int secondOperand = 0;
            try {
                firstOperand = Integer.parseInt(args[0]);
                secondOperand = Integer.parseInt(args[2]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String operator = args[1];

            mathOperation(firstOperand, secondOperand, operator);
        } else if (args.length == 1) { // 3+4
            String[] expressions = args[0].split("");

            if (expressions.length != 3) {
                System.out.println("Invalid Expression");
                return;
            }
            mathOperation(Integer.parseInt(expressions[0]), Integer.parseInt(expressions[2]), expressions[1]);
        } else {
            System.out.println("Invalid Expression");
            }
        }

    private static void mathOperation(int firstOperand, int secondOperand, String operator) {
        switch (operator) {
            case "+" -> System.out.println(add(firstOperand, secondOperand));
            case "-" -> System.out.println(sub(firstOperand, secondOperand));
            case "x" -> System.out.println(mul(firstOperand, secondOperand));
            case "/" -> System.out.println(div(firstOperand, secondOperand));
            case "^" -> System.out.println(pow(firstOperand, secondOperand));
            default -> System.out.println("Unsupported operator");
        }
    }

    private static int add(int firstOperand, int secondOperand) {
        return firstOperand + secondOperand;
    }

    private static int sub(int firstOperand, int secondOperand) {
        return firstOperand - secondOperand;
    }

    private static int mul(int firstOperand, int secondOperand) {
        return firstOperand * secondOperand;
    }

    private static double div(int firstOperand, int secondOperand) {
        return (double) firstOperand / secondOperand;
    }

    private static int pow(int x, int n) {
        int product = 1;
        for (int i = 1; i <= n; i++) {
            product *= x;
        }
        return product;
    }
}