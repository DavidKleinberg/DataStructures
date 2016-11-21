//David Kleinberg
//dkleinb1@jhu.edu

import java.util.Scanner;

/**
 * Reverse Polish Notation Calculator.
 * @author David
 *
 */
final class Calc {

    private static Stack<Integer> stack;

    /**
     * To make Checkstyle happy.
     */
    private Calc() {
    }

    /**
     *
     * @param args command line arguments
     */
    public static void main(String[]args) {

        Scanner input = new Scanner(System.in);
        stack = new ListStack<Integer>();
        while (input.hasNext()) {
            String next = input.next();
            if ("!".equals(next)) {
                break;
            }
            try {
                stack.push(Integer.parseInt(next));
            } catch (NumberFormatException e) {
                command(next);
            }
        }
    }

    /**
     *
     * @param s the string passed from command
     */
    public static void operator(String s) {
        int b = 0;
        int a = 0;

        if (!stack.empty()) {
            try {
                b = stack.top();
                stack.pop();
                a = stack.top();
                stack.pop();
                switch (s) {
                    case "+":
                        stack.push(a + b);
                        break;

                    case "-":
                        stack.push(a - b);
                        break;

                    case "*":
                        stack.push(a * b);
                        break;

                    case "/":
                        stack.push(a / b);
                        break;

                    case "%":
                        stack.push(a % b);
                        break;

                    default:
                        throw new NumberFormatException();
                }
            } catch (EmptyException e) {
                System.err.println("?Not enough arguments");
                stack.push(b);
            } catch (ArithmeticException e) {
                System.err.println("?Cannot divide by zero");
                stack.push(a);
                stack.push(b);
            }
        } else {
            System.err.println("?Not enough arguments");
        }
    }

    /**
     *
     * @param c the string passed from main
     */
    public static void command(String c) {
        switch (c) {
            case "?":
                System.out.println(stack.toString());
                break;

            case ".":
                try {
                    System.out.println(stack.top());
                } catch (EmptyException e) {
                    System.err.println("?Not enough arguments");
                }
                break;

            default:
                if (isOperator(c)) {
                    operator(c);
                } else {
                    System.err.println("?Invalid input");
                }
                break;
        }
    }

    public static boolean isOperator(String o) {
        if ("+".equals(o) || "-".equals(o) || "*".equals(o)
            || "/".equals(o) || "%".equals(o) || "?".equals(o)
            || ".".equals(o)) {
            return true;
        }
        return false;
    }
}


