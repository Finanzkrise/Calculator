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


    public int getDigitRightOfComma(int index, Decimal number, int location) {
        int digit = 0;
        if (isIndexInBounds(index, number, location)) {
            digit = number.getNumberList().get(location).get(index);
        }
        return digit;
    }

    @Override
    public int getDigitLeftOfComma(int index, Decimal number, int location) {
        return super.getDigitLeftOfComma(index, number, location);
    }

    private Decimal multiplyNumbers(Decimal number1, Decimal number2) {

        Decimal tempResultForAddition = new Decimal("0,0");
        // for length of number2 Right of Comma
        for (int numberTwoIndex = 0; number2.getNumberList().get(RIGHT_OF_COMMA).size() > numberTwoIndex; numberTwoIndex++) {

            // multiply each digit of number1RightOfComma with number2RightOfComma[numberTwoIndex]
            // 0,x * 0,x
            resultRightOfComma = new ArrayList<>();
            int numberOneIndex = 0;
            for (numberOneIndex = 0; number1.getNumberList().get(RIGHT_OF_COMMA).size() > numberOneIndex; numberOneIndex++) {
                int tempResult = multiplyTwoDigits(
                        getDigitRightOfComma(number1.getNumberList().get(RIGHT_OF_COMMA).size() - 1 - numberOneIndex, number1, RIGHT_OF_COMMA),
                        getDigitRightOfComma(numberTwoIndex, number2, RIGHT_OF_COMMA)
                );
                System.out.println("number 1 "+getDigitRightOfComma(number1.getNumberList().get(RIGHT_OF_COMMA).size() - 1 - numberOneIndex, number1, RIGHT_OF_COMMA));
                System.out.println("number 2 " + getDigitRightOfComma(numberTwoIndex, number2, RIGHT_OF_COMMA));
                System.out.println("result " + tempResult);
                getResultRightOfComma().add(0, tempResult);

                System.out.println("result " + tempResult);
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
            for (numberOneIndex = number1.getNumberList().get(LEFT_OF_COMMA).size(); 0 <= numberOneIndex; numberOneIndex--) {
                int i;
                int tempResult = multiplyTwoDigits(getDigitLeftOfComma(numberOneIndex, number1, LEFT_OF_COMMA), getDigitRightOfComma(numberTwoIndex, number2, RIGHT_OF_COMMA));

                //System.out.println("digit to be multiplied " + getDigitLeftOfComma(numberOneIndex, number1, LEFT_OF_COMMA));
                // System.out.println("temp result: " + tempResult);
//_____________
                i = numberTwoIndex;
                if (numberTwoIndex >= i) {
                    getResultRightOfComma().add(0, tempResult);
                    i++;
                } else {
                    getResultLeftOfComma().add(0, tempResult);
                }
                resultLeftOfComma.add(0, multiplyTwoDigits(0, 0));

            }
            //System.out.println("left of comma * right of comma " + new Decimal(resultLeftOfComma, resultRightOfComma));
            decimalList.add(new Decimal(resultLeftOfComma, resultRightOfComma));
            System.out.println(numberTwoIndex +1 + ". Durchlauf - groß");
        }


        for (int numberTwoIndex = 0; number2.getNumberList().get(LEFT_OF_COMMA).size() > numberTwoIndex; numberTwoIndex++) {
            // multiply each digit of number1RightOfComma with number2LeftOfComma[numberTwoIndex]
            resultRightOfComma = new ArrayList<>();
            resultLeftOfComma = new ArrayList<>();
            for (int numberOneIndex = number2.getNumberList().get(LEFT_OF_COMMA).size(); 0 <= numberOneIndex; numberOneIndex--) {
                int i = 0;
                int tempResult = multiplyTwoDigits(getDigitRightOfComma(numberOneIndex, number1, RIGHT_OF_COMMA), getDigitRightOfComma(numberTwoIndex, number2, LEFT_OF_COMMA));
                if (i < number2.getNumberList().get(LEFT_OF_COMMA).size()) {
                    getResultRightOfComma().add(0, tempResult);
                } else {
                    getResultLeftOfComma().add(tempResult);
                }
            }
            System.out.println("right of comma * left of comma " + new Decimal(resultLeftOfComma, resultRightOfComma));
            decimalList.add(new Decimal(resultLeftOfComma, resultRightOfComma));


            // multiply each digit of number1LeftOfComma with number2LeftOfComma[numberTwoIndex]
            resultLeftOfComma = new ArrayList<>();
            // multiply each digit of number1LeftOfComma with number2LeftOfComma[numberTwoIndex]
            for (int numberOneIndex = number2.getNumberList().get(LEFT_OF_COMMA).size(); 0 <= numberOneIndex; numberOneIndex--) {
                int tempResult = multiplyTwoDigits(getDigitRightOfComma(numberOneIndex, number1, LEFT_OF_COMMA), getDigitRightOfComma(numberTwoIndex, number2, LEFT_OF_COMMA));
                getResultLeftOfComma().add(0, tempResult);
            }
            for (int k = number2.getNumberList().get(LEFT_OF_COMMA).size() - numberTwoIndex - 1; k > 0; k--) {
                getResultLeftOfComma().add(0);
            }
            System.out.println("left of comma * left of comma " + new Decimal(false, getResultLeftOfComma()));
            decimalList.add(new Decimal(false, getResultLeftOfComma()));
        }


        for (Decimal tempResult : decimalList) {
            System.out.println("addition");
            tempResultForAddition = new Addition().operate(tempResultForAddition, tempResult);
        }

        return tempResultForAddition;

    }

    /*
        private void multiplyNumbersOfNumberOne(Decimal number1, Decimal number2) {
            int lengthOfLongerNumber = getLengthOfLongerNumberSection(number1, number2, LEFT_OF_COMMA);
            for (int i = 0; lengthOfLongerNumber > i; i++) {
                int tempResult = multiplyTwoDigits(getDigitAfterCommaAt(i, number1, LEFT_OF_COMMA), getDigitAfterCommaAt(i, number2, LEFT_OF_COMMA));
                getResultLeftOfComma().add(0, tempResult);
            }
        }


     */
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
