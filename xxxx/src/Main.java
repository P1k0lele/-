import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Map<Character, Integer> ROMAN_MAP = new HashMap<>();
    static {
        ROMAN_MAP.put('I', 1);
        ROMAN_MAP.put('V', 5);
        ROMAN_MAP.put('X', 10);
        ROMAN_MAP.put('L', 50);
    }
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            String[] arr = input.split(" ");
            if (arr.length != 3) {
                throw new IllegalArgumentException("");
            }
            int num1 = convertNumber(arr[0]);
            int num2 = convertNumber(arr[2]);
            char operator = arr[1].charAt(0);

            int result = calculate(num1, num2, operator);
            System.out.println(result);

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    public static int convertNumber(String numStr) {
        if (numStr.matches("\\d+")) {
            return Integer.parseInt(numStr);
        } else if (numStr.matches("[IVXLCDM]+")) {
            return romanToArabic(numStr);
        } else {
            throw new IllegalArgumentException("");
        }
    }
    private static int romanToArabic(String numStr) {
        int result = 0;
        int previousValue = 0;
        for (int i = numStr.length() - 1; i >= 0; i--) {
            char currentChar = numStr.charAt(i);
            int currentValue = ROMAN_MAP.get(currentChar);

            if (currentValue < previousValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            previousValue = currentValue;
        }
        return result;
    }
    public static int calculate(int num1, int num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("");
        }
    }
}