import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continueCalculating = true;
            while (continueCalculating) {
                try {
                    System.out.println("\n--- Simple Calculator ---");
                    System.out.println("Enter first number:");
                    double num1 = getValidNumber(scanner);
                    System.out.println("Enter second number:");
                    double num2 = getValidNumber(scanner);
                    System.out.println("Enter operation (+, -, *, /):");
                    char operation = getValidOperation(scanner);
                    double result = performOperation(num1, num2, operation);
                    if (!Double.isNaN(result)) {
                        System.out.printf("\nResult: %.2f%n", result);
                    } else {
                        System.out.println("\nCalculation failed!");
                    }
                    System.out.println("\nDo you want to perform another calculation? (yes/no):");
                    if (!getUserConfirmation(scanner)) {
                        continueCalculating = false;
                        System.out.println("Thank you for using the calculator. Goodbye!");
                    }
                } catch (Exception e) {
                    System.err.println("An error occurred: " + e.getMessage());
                    scanner.nextLine();
                }
            }
        }
    }

    private static double getValidNumber(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input! Please enter a valid number:");
            scanner.nextLine();
        }
        double number = scanner.nextDouble();
        scanner.nextLine();
        return number;
    }

    private static char getValidOperation(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.length() == 1 && "+-*/".indexOf(input.charAt(0)) != -1) {
                return input.charAt(0);
            } else {
                System.out.println("Invalid operation! Please enter a valid operation (+, -, *, /):");
            }
        }
    }

    private static double performOperation(double num1, double num2, char operation) {
        switch (operation) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/': 
                if (num2 == 0) {
                    System.out.println("Error! Division by zero is not allowed.");
                    return Double.NaN;
                }
                return num1 / num2;
            default: 
                System.out.println("Invalid operation!");
                return Double.NaN;
        }
    }

    private static boolean getUserConfirmation(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input! Please enter 'yes' or 'no':");
            }
        }
    }
}