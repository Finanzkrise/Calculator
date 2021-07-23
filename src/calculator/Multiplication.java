package calculator;

import java.util.ArrayList;
import java.util.List;

public class Multiplication extends Operation implements ListLocation {
    int overflow = 0;
    List<Decimal> decimalList = new ArrayList<>();

    public Multiplication(Decimal number1, Decimal number2) {
        result = operate(number1, number2);
    }

    public Decimal operate(Decimal number1, Decimal number2) {
        Decimal result = new Decimal();
        result = multiply(number1, number2);
        result.getNumberList().get(LEFT_OF_COMMA).add(0, multiplyTwoDigits(0, 0));
        return result;
    }

    private Decimal multiply(Decimal number1, Decimal number2) {
        Decimal result = new Decimal("0,0");

        // for length of number2 Right of Comma
        for (int numberTwoIndex = 0; number2.getNumberList().get(RIGHT_OF_COMMA).size() > numberTwoIndex; numberTwoIndex++) {

            // 0,XXX * 0,X
            resultRightOfComma = new ArrayList<>();
            for (int numberOneIndex = 0; number1.getNumberList().get(RIGHT_OF_COMMA).size() > numberOneIndex; numberOneIndex++) {
                int tempResult = multiplyTwoDigits(
                        getDigit(number1.getNumberList().get(RIGHT_OF_COMMA).size() - 1 - numberOneIndex, number1, RIGHT_OF_COMMA),
                        getDigit(numberTwoIndex, number2, RIGHT_OF_COMMA)
                );
                getResultRightOfComma().add(0, tempResult);
            }
            for (int i = 0; numberTwoIndex >= i; i++) {
                getResultRightOfComma().add(0, multiplyTwoDigits(0, 0));
            }
            decimalList.add(new Decimal(true, getResultRightOfComma()));

        }

        multiplyIntDecimal(number1, number2);
        multiplyIntDecimal(number2, number1);


        // for length of number2 Left of Comma
        for (int numberTwoIndex = 0; number2.getNumberList().get(LEFT_OF_COMMA).size() > numberTwoIndex; numberTwoIndex++) {


            // XXX,0 * X,0
            resultLeftOfComma = new ArrayList<>();
            // verbessern
            for (int numberOneIndex = 0; number1.getNumberList().get(LEFT_OF_COMMA).size() > numberOneIndex; numberOneIndex++) {
                int tempResult = multiplyTwoDigits(
                        getDigit(number1.getNumberList().get(LEFT_OF_COMMA).size() - 1 - numberOneIndex, number1, LEFT_OF_COMMA),
                        getDigit(numberTwoIndex, number2, LEFT_OF_COMMA)
                );
                getResultLeftOfComma().add(0, tempResult);
            }
            getResultLeftOfComma().add(0, multiplyTwoDigits(0, 0));
            for (int i = number2.getNumberList().get(LEFT_OF_COMMA).size() - 1 - numberTwoIndex; 0 < i; i--) {
                getResultLeftOfComma().add(0);
            }
            decimalList.add(new Decimal(false, getResultLeftOfComma()));
        }

        for (Decimal tempResult : decimalList) {
            result = new Addition().operate(result, tempResult);
        }

        //result = trimResult(result);


        return result;
    }

    public void multiplyIntDecimal(Decimal integer, Decimal decimal) {
        Decimal result = new Decimal();
        int numberOneIndex;

        for (int numberTwoIndex = 0; decimal.getNumberList().get(RIGHT_OF_COMMA).size() > numberTwoIndex; numberTwoIndex++) {
            resultRightOfComma = new ArrayList<>();
            resultLeftOfComma = new ArrayList<>();

            for (numberOneIndex = 0; integer.getNumberList().get(LEFT_OF_COMMA).size() > numberOneIndex; numberOneIndex++) {
                int tempResult = multiplyTwoDigits(
                        getDigit(integer.getNumberList().get(LEFT_OF_COMMA).size() - 1 - numberOneIndex, integer, LEFT_OF_COMMA),
                        getDigit(numberTwoIndex, decimal, RIGHT_OF_COMMA)
                );
                System.out.println("digit1 int : " + getDigit(integer.getNumberList().get(LEFT_OF_COMMA).size() - 1 - numberOneIndex, integer, LEFT_OF_COMMA));
                System.out.println("digit2 dec : " + getDigit(numberTwoIndex, decimal, RIGHT_OF_COMMA));
                System.out.println("result : " + tempResult);

                if (numberTwoIndex >= numberOneIndex) {
                    resultRightOfComma.add(0, tempResult);
                    System.out.println("right!");

                } else {
                    System.out.println("left!");
                    resultLeftOfComma.add(0, tempResult);
                }
            }
            if (numberTwoIndex >= numberOneIndex) {
                resultRightOfComma.add(0, multiplyTwoDigits(0, 0));
                System.out.println("right!");

            } else {
                System.out.println("left!");
                resultLeftOfComma.add(0, multiplyTwoDigits(0, 0));
            }
            result = new Decimal(resultLeftOfComma, resultRightOfComma);
            System.out.println(result.getNumberList().get(LEFT_OF_COMMA) + "," + result.getNumberList().get(RIGHT_OF_COMMA));
            decimalList.add(result);
        }

    }


    public int getDigit(int index, Decimal number, int location) {
        int digit = 0;
        if (isIndexInBounds(index, number, location)) {
            digit = number.getNumberList().get(location).get(index);
        }
        return digit;
    }

    private int multiplyTwoDigits(int number1, int number2) {
        int product = number1 * number2 + overflow;
        int result = product % 10;
        overflow = setOverflow(product);
        return result % 10;
    }

    @Override
    public int setOverflow(int product) {
        int overflow = product / 10;
        return overflow;
    }

}
