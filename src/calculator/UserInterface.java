package calculator;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);


    public Decimal getNumber() {
        Decimal decimal = new Decimal();
        String input;
        System.out.println("Please enter a number");
        input = readInput("\\d*[,.]?\\d*", "number");
        decimal.setNumberListFromString(input);
        return decimal;
    }

    public String getOperator() {
        String input;
        System.out.println("Please enter an operator");
        input = readInput("[+]", "operator");
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
