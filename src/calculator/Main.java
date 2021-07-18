package calculator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Decimal number1 = new Decimal();
        Decimal number2 = new Decimal();


        UserInterface userInterface = new UserInterface();

        number1 = userInterface.getNumber();
        userInterface.getOperator();
        number2 = userInterface.getNumber();

        Addition addition = new Addition();

        System.out.println(Arrays.toString(number1.getNumberList().toArray()));
        System.out.println(Arrays.toString(number2.getNumberList().toArray()));
        System.out.println(Arrays.toString(addition.operate(number1, number2).getNumberList().toArray()));

    }
}
