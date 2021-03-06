package calculator.operations;

import calculator.Decimal;
import calculator.Location;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Addition extends CalcHelper implements  IOperation {
    boolean overflow = false;

    Logger logger = LogManager.getLogger(Addition.class);

    public Addition(Decimal ...numbers) {
            result = new Decimal("0");
        for (Decimal number : numbers) {
            result = new Addition(result, number).getResult();
        }
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
                    number2.setIsPositive(true);
                    result = new Subtraction().operate(number1, number2);
                    result.setIsPositive(true);
                } else if (!number1.isNumberPositive() && number2.isNumberPositive()) {
                    number1.setIsPositive(true);
                    result = new Subtraction().operate(number1, number2);
                    result.setIsPositive(false);
                }
            } else {
                if (number1.isNumberPositive() && !number2.isNumberPositive()) {
                    number2.setIsPositive(true);
                    result = new Subtraction().operate(number2, number1);
                    result.setIsPositive(false);
                } else if (!number1.isNumberPositive() && number2.isNumberPositive()) {
                    number1.setIsPositive(true);
                    result = new Subtraction().operate(number2, number1);
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
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(number1, number2, Location.LEFT.getIndex());
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = addTwoDigits(getDigitLeftOfComma(i, number1, Location.LEFT.getIndex()), getDigitLeftOfComma(i, number2, Location.LEFT.getIndex()));
            getResultLeftOfComma().add(0, tempResult);
        }
        if (overflow) {
            getResultLeftOfComma().add(0, addTwoDigits(0, 0));
        }
    }

    private void addNumbersRightOfComma(Decimal number1, Decimal number2) {
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(number1, number2, Location.RIGHT.getIndex());
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = addTwoDigits(getDigitRightOfComma(lengthOfLongerNumber, i, number1, Location.RIGHT.getIndex()), getDigitRightOfComma(lengthOfLongerNumber, i, number2, Location.RIGHT.getIndex()));
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
