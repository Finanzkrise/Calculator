package calculator;

import java.util.ArrayList;
import java.util.List;

public class Multiplication extends Operation implements ListLocation {
    int overflow = 0;
    Operation addition;
    List<Decimal> decimalList = new ArrayList<>();


    public Multiplication(Decimal number1, Decimal number2) {
        operate(number1, number2);
    }


    public Decimal operate(Decimal number1, Decimal number2) {
        Decimal result = new Decimal();

        result = multiplyNumbers(number1, number2);

        result.getNumberList().get(LEFT_OF_COMMA).add(0, multiplyTwoDigits(0, 0));

        System.out.println(result);
        return result;
    }


    public int getDigit(int index, Decimal number, int location) {
        int digit = 0;
        if (isIndexInBounds(index, number, location)) {
            digit = number.getNumberList().get(location).get(index);
        }
        return digit;
    }


    private Decimal multiplyNumbers(Decimal number1, Decimal number2) {

        Decimal tempResultForAddition = new Decimal("0,0");
        // for length of number2 Right of Comma
        for (int numberTwoIndex = 0; number2.getNumberList().get(RIGHT_OF_COMMA).size() > numberTwoIndex; numberTwoIndex++) {

            // multiply each digit of number1RightOfComma with number2RightOfComma[numberTwoIndex]
            // 0,x * 0,x
            resultRightOfComma = new ArrayList<>();

            for (int numberOneIndex = 0; number1.getNumberList().get(RIGHT_OF_COMMA).size() > numberOneIndex; numberOneIndex++) {
                int tempResult = multiplyTwoDigits(
                        getDigit(number1.getNumberList().get(RIGHT_OF_COMMA).size() - 1 - numberOneIndex, number1, RIGHT_OF_COMMA),
                        getDigit(numberTwoIndex, number2, RIGHT_OF_COMMA)
                );
                System.out.println("number 1 "+ getDigit(number1.getNumberList().get(RIGHT_OF_COMMA).size() - 1 - numberOneIndex, number1, RIGHT_OF_COMMA));
                System.out.println("number 2 " + getDigit(numberTwoIndex, number2, RIGHT_OF_COMMA));
                System.out.println("result " + tempResult);
                getResultRightOfComma().add(0, tempResult);
                System.out.println(numberOneIndex +1 + ". Durchlauf - klein");
            }
            for (int i = 0; numberTwoIndex >= i; i++ ){
                getResultRightOfComma().add(0, multiplyTwoDigits(0, 0));
            }

            System.out.println("right of comma * right of comma " + new Decimal(true, getResultRightOfComma()));
            decimalList.add(new Decimal(true, getResultRightOfComma()));








            // multiply each digit of number1LeftOfComma with number2RightOfComma[numberTwoIndex]
            //x,0 * 0,x
            resultRightOfComma = new ArrayList<>();
            resultLeftOfComma = new ArrayList<>();
            for (int numberOneIndex = 0; number1.getNumberList().get(LEFT_OF_COMMA).size() > numberOneIndex; numberOneIndex++) {

                int tempResult = multiplyTwoDigits(
                        getDigit(number1.getNumberList().get(LEFT_OF_COMMA).size() - 1 - numberOneIndex, number1, LEFT_OF_COMMA),
                        getDigit(numberTwoIndex, number2, RIGHT_OF_COMMA)
                );
                System.out.println(getDigit(number1.getNumberList().get(LEFT_OF_COMMA).size() - 1 - numberOneIndex, number1, LEFT_OF_COMMA) + " * " + getDigit(numberTwoIndex, number2, RIGHT_OF_COMMA));

                if (numberOneIndex <= numberTwoIndex) {
                    getResultRightOfComma().add(0, tempResult);

                } else {
                    getResultLeftOfComma().add(0, tempResult);
                }
                System.out.println(numberOneIndex +1 + ". Durchlauf - klein: " + getResultLeftOfComma() + ", " + getResultRightOfComma());

            }
            resultLeftOfComma.add(0, multiplyTwoDigits(0, 0));
            System.out.println("left of comma * right of comma " + new Decimal(resultLeftOfComma, resultRightOfComma));
            decimalList.add(new Decimal(resultLeftOfComma, resultRightOfComma));
            System.out.println(numberTwoIndex +1 + ". Durchlauf - groß");
        }





        // for length of number2 Left of Comma
        for (int numberTwoIndex = 0; number2.getNumberList().get(LEFT_OF_COMMA).size() > numberTwoIndex; numberTwoIndex++) {




            // multiply each digit of number1RightOfComma with number2LeftOfComma[numberTwoIndex]
            //0,x * x,0
            resultRightOfComma = new ArrayList<>();
            resultLeftOfComma = new ArrayList<>();
            for (int numberOneIndex = 0; number1.getNumberList().get(RIGHT_OF_COMMA).size() > numberOneIndex; numberOneIndex++) {

                int tempResult = multiplyTwoDigits(
                        getDigit(number1.getNumberList().get(RIGHT_OF_COMMA).size() - 1 - numberOneIndex, number1, RIGHT_OF_COMMA),
                        getDigit(numberTwoIndex, number2, LEFT_OF_COMMA)
                );
                System.out.println(getDigit(number1.getNumberList().get(RIGHT_OF_COMMA).size() - 1 - numberOneIndex, number1, RIGHT_OF_COMMA) + " * " + getDigit(numberTwoIndex, number2, LEFT_OF_COMMA));

                if (numberOneIndex <= numberTwoIndex) {
                    getResultRightOfComma().add(0, tempResult);

                } else {
                    getResultLeftOfComma().add(0, tempResult);
                }
                System.out.println(numberOneIndex +1 + ". Durchlauf - klein: " + getResultLeftOfComma() + ", " + getResultRightOfComma());

            }
            resultLeftOfComma.add(0, multiplyTwoDigits(0, 0));
            System.out.println("left of comma * right of comma " + new Decimal(resultLeftOfComma, resultRightOfComma));
            decimalList.add(new Decimal(resultLeftOfComma, resultRightOfComma));
            System.out.println(numberTwoIndex +1 + ". Durchlauf - groß");












            // multiply each digit of number1LeftOfComma with number2LeftOfComma[numberTwoIndex]
            // x,0 * x,0
            resultLeftOfComma = new ArrayList<>();
            // verbessern
            for (int numberOneIndex = 0; number1.getNumberList().get(LEFT_OF_COMMA).size() > numberOneIndex; numberOneIndex++) {
                int tempResult = multiplyTwoDigits(
                    getDigit(number1.getNumberList().get(LEFT_OF_COMMA).size() -1-numberOneIndex, number1, LEFT_OF_COMMA),
                    getDigit(numberTwoIndex, number2, LEFT_OF_COMMA)
                );
                System.out.println(getDigit(number1.getNumberList().get(LEFT_OF_COMMA).size() - 1 - numberOneIndex, number1, LEFT_OF_COMMA) + " * " + getDigit(numberTwoIndex, number2, LEFT_OF_COMMA));
                System.out.println("result " + tempResult);
                getResultLeftOfComma().add(0, tempResult);
                System.out.println(numberOneIndex +1 + ". Durchlauf - klein: " + getResultLeftOfComma());
            }

            getResultLeftOfComma().add(0, multiplyTwoDigits(0,0));
            for (int i = number2.getNumberList().get(LEFT_OF_COMMA).size() -1 - numberTwoIndex; 0 < i; i--){
                getResultLeftOfComma().add(0);
            }

            System.out.println("Durchlauf - klein: " + getResultLeftOfComma());
            System.out.println("left of comma * left of comma " + new Decimal(false, getResultLeftOfComma()));
            decimalList.add(new Decimal(false, getResultLeftOfComma()));
        }

        for (Decimal tempResult : decimalList) {
            System.out.println("addition");
            tempResultForAddition = new Addition().operate(tempResultForAddition, tempResult);
        }
        return tempResultForAddition;
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
