package calculator;

import calculator.operations.*;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private static final String VALID_OPERATORS = "[*+-/^%r]";

    public void useProgram() {
        Decimal number1 = getNumber();
        String operatorSign = getOperator();
        Decimal number2 = getNumber();
        switch (operatorSign) {
            case "+" -> System.out.println("Ergebnis: " + new Addition(number1, number2).getResult());
            case "-" -> System.out.println("Ergebnis: " + new Subtraction(number1, number2).getResult());
            case "*" -> System.out.println("Ergebnis: " + new Multiplication(number1, number2).getResult());
            case "/" -> System.out.println("Ergebnis: " + new Division(number1, number2).getResult());
            case "^" -> System.out.println("Ergebnis: " + new Exponentiation(number1, number2).getResult());
            case "%" -> System.out.println("Ergebnis: " + new Modulo(number1, number2).getResult());
            case "r" -> System.out.println("Ergebnis: " + new RootExtraction(number1, number2).getResult());
        }
    }

    public Decimal getNumber() {
        Decimal decimal = new Decimal();
        String input;
        System.out.println("Please enter a number");
        input = readInput("-?[\\d?]*[,.]?[\\d?]*", "number");
        decimal.setNumberListFromString(input);
        return decimal;
    }

    public String getOperator() {
        String input;
        System.out.println("Please enter an operator");
        input = readInput(VALID_OPERATORS, "operator");
        System.out.println("input: " + input);
        return input;
    }

    private String readInput(String regex, String name) {
        String input;
        boolean invalidInput;
        do {
            input = scanner.next();
            invalidInput = !input.matches(regex);
            if (invalidInput) {
                System.out.println(input + " is not a valid " + name);
            }
        } while (invalidInput);
        return input;
    }
}
