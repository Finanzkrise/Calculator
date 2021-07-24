package calculator;

import calculator.operations.Addition;
import calculator.operations.Multiplication;
import calculator.operations.Subtraction;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private static final String VALID_OPERATORS = "[*+-]";

    public void useProgram() {
        Decimal number1 = getNumber();
        String operatorSign = getOperator();
        Decimal number2 = getNumber();
        switch (operatorSign) {
            case "+":
                System.out.println(new Addition(number1, number2).getResult());
                break;
            case "-":
                System.out.println(new Subtraction(number1, number2).getResult());
                break;
            case "*":
                System.out.println(new Multiplication(number1, number2).getResult());
                break;
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
