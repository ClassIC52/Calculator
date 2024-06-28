import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.print("Введите выражение (например, 5 + 3) или 'выход' для завершения: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("выход")) {
                running = false;
                System.out.println("Программа завершена.");
                continue;
            }

            try {
                String[] parts = input.split(" ");
                if (parts.length != 3) {
                    System.out.println("Неверный формат ввода");
                    continue;
                }

                int num1 = parseNumber(parts[0]);
                int num2 = parseNumber(parts[2]);
                char operator = parseOperator(parts[1]);

                int result = performOperation(num1, num2, operator);
                System.out.println("Результат: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static int parseNumber(String str) {
        int num = Integer.parseInt(str);
        if (num < 1 || num > 10) {
            throw new IllegalArgumentException("Число должно быть от 1 до 10 включительно");
        }
        return num;
    }

    private static char parseOperator(String str) {
        if (str.length() != 1 || (str.charAt(0) != '+' && str.charAt(0) != '-' && str.charAt(0) != '*' && str.charAt(0) != '/')) {
            throw new IllegalArgumentException("Неверный оператор");
        }
        return str.charAt(0);
    }

    private static int performOperation(int num1, int num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new IllegalArgumentException("Деление на ноль");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Неверный оператор");
        }
    }
}