package calculator.operations;

import calculator.Decimal;
import calculator.Location;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Multiplication extends CalcHelper implements IOperation {
    int overflow = 0;
    List<Decimal> decimalList = new ArrayList<>();
    Logger logger = LogManager.getLogger(Multiplication.class);
    public Multiplication() {
    }

    public Multiplication(Decimal number1, Decimal number2) {
        result = operate(number1, number2);
    }

    @Override
    public Decimal operate(Decimal number1, Decimal number2) {
        executeOperation(number1, number2);
        result = trimDecimal(result);
        return result;
    }

    @Override
    void executeOperation(Decimal number1, Decimal number2) {
        if (number1.isNumberPositive() && number2.isNumberPositive()) {
            result = multiply(number1, number2);
            result.setIsPositive(true);
        } else if (!number1.isNumberPositive() && !number2.isNumberPositive()) {
            result = multiply(number1, number2);
            result.setIsPositive(true);
        } else {
            result = multiply(number1, number2);
            result.setIsPositive(false);
        }
    }

    private Decimal multiply(Decimal number1, Decimal number2) {
        Decimal result = new Decimal("0,0");

        multiplayIntInt(number1, number2);
        multiplyIntDecimal(number1, number2);
        multiplyIntDecimal(number2, number1);
        multiplyDecimalDecimal(number1, number2);

        for (Decimal tempResult : decimalList) {
            result = new Addition().operate(result, tempResult);
        }
        return result;
    }

    private void multiplyDecimalDecimal(Decimal number1, Decimal number2) {
        // for length of number2 Right of Comma
        for (int numberTwoIndex = 0; number2.getNumberList().get(Location.RIGHT_OF_COMMA.getIndex()).size() > numberTwoIndex; numberTwoIndex++) {
            resultRightOfComma = new ArrayList<>();

            for (int numberOneIndex = 0; number1.getNumberList().get(Location.RIGHT_OF_COMMA.getIndex()).size() > numberOneIndex; numberOneIndex++) {
                int tempResult = multiplyTwoDigits(
                        getDigit(number1.getNumberList().get(Location.RIGHT_OF_COMMA.getIndex()).size() - 1 - numberOneIndex, number1, Location.RIGHT_OF_COMMA.getIndex()),
                        getDigit(numberTwoIndex, number2, Location.RIGHT_OF_COMMA.getIndex())
                );
                getResultRightOfComma().add(0, tempResult);
            }
            for (int i = 0; numberTwoIndex >= i; i++) {
                getResultRightOfComma().add(0, multiplyTwoDigits(0, 0));
            }
            decimalList.add(new Decimal(true, getResultRightOfComma()));
        }
    }

    private void multiplayIntInt(Decimal number1, Decimal number2) {
        // for length of number2 Left of Comma
        for (int numberTwoIndex = 0; number2.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).size() > numberTwoIndex; numberTwoIndex++) {
            resultLeftOfComma = new ArrayList<>();

            for (int numberOneIndex = 0; number1.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).size() > numberOneIndex; numberOneIndex++) {
                int tempResult = multiplyTwoDigits(
                        getDigit(number1.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).size() - 1 - numberOneIndex, number1, Location.LEFT_OF_COMMA.getIndex()),
                        getDigit(numberTwoIndex, number2, Location.LEFT_OF_COMMA.getIndex())
                );
                getResultLeftOfComma().add(0, tempResult);
            }
            getResultLeftOfComma().add(0, multiplyTwoDigits(0, 0));
            for (int i = number2.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).size() - 1 - numberTwoIndex; 0 < i; i--) {
                getResultLeftOfComma().add(0);
            }
            decimalList.add(new Decimal(false, getResultLeftOfComma()));
        }
    }

    public void multiplyIntDecimal(Decimal integer, Decimal decimal) {
        int numberOneIndex;

        for (int numberTwoIndex = 0; decimal.getNumberList().get(Location.RIGHT_OF_COMMA.getIndex()).size() > numberTwoIndex; numberTwoIndex++) {
            resultRightOfComma = new ArrayList<>();
            resultLeftOfComma = new ArrayList<>();

            for (numberOneIndex = 0; integer.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).size() > numberOneIndex; numberOneIndex++) {
                int tempResult = multiplyTwoDigits(
                        getDigit(integer.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).size() - 1 - numberOneIndex, integer, Location.LEFT_OF_COMMA.getIndex()),
                        getDigit(numberTwoIndex, decimal, Location.RIGHT_OF_COMMA.getIndex())
                );
                if (numberTwoIndex >= numberOneIndex) {
                    resultRightOfComma.add(0, tempResult);
                } else {
                    resultLeftOfComma.add(0, tempResult);
                }
            }
            if (numberTwoIndex >= numberOneIndex) {
                resultRightOfComma.add(0, multiplyTwoDigits(0, 0));

            } else {
                resultLeftOfComma.add(0, multiplyTwoDigits(0, 0));
            }
            decimalList.add(new Decimal(resultLeftOfComma, resultRightOfComma));
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

    public int setOverflow(int product) {
        int overflow = product / 10;
        return overflow;
    }

}
