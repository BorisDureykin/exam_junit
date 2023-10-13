package iFellow;

public class Calculator {
    public static double calculate(double a, double b, char operator) {
        double result = 0;

        switch (operator) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if (b != 0) {
                    result = a / b;
                } else {
                    throw new ArithmeticException("Деление на ноль!");
                }
                break;
            default:
                throw new IllegalArgumentException("Неверный оператор!");
        }

        return result;
    }
}