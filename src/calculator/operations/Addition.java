package calculator.operations;

import calculator.Decimal;
import calculator.IListLocation;

public class Addition extends CalcHelper implements  IOperation {
    boolean overflow = false;

    public Addition(Decimal ...numbers) {
    }

    public Addition(Decimal number1, Decimal number2) {
        result = operate(number1, number2);
    }

    @Override
    void executeOperation(Decimal number1, Decimal number2) {
        if (number1.isNumberPositive() && number2.isNumberPositive()) {
            result = addPositiveNumbers(number1, number2);
            result.setIsPositive(true);
        } else if (!number1.isNumberPositive() && !number2.isNumberPositive()) {
            result = addPositiveNumbers(number1, number2);
            result.setIsPositive(false);
        } else {
            if (isDecimalHigherThanDecimal(number1, number2)) {
                if (number1.isNumberPositive() && !number2.isNumberPositive()) {
                    Decimal changedNumber2 = number2;
                    changedNumber2.setIsPositive(true);
                    result = new Subtraction().operate(number1, changedNumber2);
                    result.setIsPositive(true);
                } else if (!number1.isNumberPositive() && number2.isNumberPositive()) {
                    Decimal changedNumber1 = number1;
                    changedNumber1.setIsPositive(true);
                    result = new Subtraction().operate(changedNumber1, number2);
                    result.setIsPositive(false);
                }
            } else {
                if (number1.isNumberPositive() && !number2.isNumberPositive()) {
                    Decimal changedNumber2 = number2;
                    changedNumber2.setIsPositive(true);
                    result = new Subtraction().operate(changedNumber2, number1);
                    result.setIsPositive(false);
                } else if (!number1.isNumberPositive() && number2.isNumberPositive()) {
                    Decimal changedNumber1 = number1;
                    changedNumber1.setIsPositive(true);
                    result = new Subtraction().operate(number2, changedNumber1);
                    result.setIsPositive(true);
                }
            }
        }
    }

    public Decimal addPositiveNumbers(Decimal number1, Decimal number2) {
        addNumbersRightOfComma(number1, number2);
        addNumbersLeftOfComma(number1, number2);
        result = new Decimal(resultLeftOfComma, resultRightOfComma);
        return result;
    }

    private void addNumbersLeftOfComma(Decimal number1, Decimal number2) {
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(number1, number2, LEFT_OF_COMMA);
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = addTwoDigits(getDigitLeftOfComma(i, number1, LEFT_OF_COMMA), getDigitLeftOfComma(i, number2, LEFT_OF_COMMA));
            getResultLeftOfComma().add(0, tempResult);
        }
        if (overflow) {
            getResultLeftOfComma().add(0, addTwoDigits(0, 0));
        }
    }

    private void addNumbersRightOfComma(Decimal number1, Decimal number2) {
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(number1, number2, RIGHT_OF_COMMA);
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = addTwoDigits(getDigitRightOfComma(lengthOfLongerNumber, i, number1, RIGHT_OF_COMMA), getDigitRightOfComma(lengthOfLongerNumber, i, number2, RIGHT_OF_COMMA));
            getResultRightOfComma().add(0, tempResult);
        }
    }

    public int addTwoDigits(int number1, int number2) {
        int result = number1 + number2 + getOverflow();
        result = setOverflow(result);
        return result;
    }

    public int setOverflow(int number) {
        overflow = number > 9;
        return number % 10;
    }

    public int getOverflow() {
        if (overflow) {
            overflow = false;
            return 1;
        }
        return 0;
    }

    @Override
    public Decimal operate(Decimal number1, Decimal number2) {
        executeOperation(number1, number2);
        result = trimDecimal(result);
        return result;
    }

}
