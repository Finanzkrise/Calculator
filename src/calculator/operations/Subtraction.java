package calculator.operations;

import calculator.Decimal;
import calculator.Location;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Subtraction extends CalcHelper implements IOperation {
    boolean overflow = false;
    Logger logger = LogManager.getLogger(Subtraction.class);
    public Subtraction(Decimal ...numbers) {
        if (numbers.length > 0) {
            result = numbers[0];
            for (int i = 1; i < numbers.length; i++) {
                result = new Subtraction(result, numbers[i]).getResult();
            }
        }
    }

    public Subtraction(Decimal minuend, Decimal subtrahend) {
        result = operate(minuend, subtrahend);
    }

    public Decimal subtract(Decimal minuend, Decimal subtrahend) {
        substractNumbersRightOfComma(minuend, subtrahend);
        substractNumbersLeftOfComma(minuend, subtrahend);
        result = new Decimal(resultLeftOfComma, resultRightOfComma);
        return result;
    }

    @Override
    void executeOperation(Decimal minuend, Decimal subtrahend) {
        // minuend > subtrahend
        if (!isDecimalHigherThanDecimal(trimDecimal(subtrahend), trimDecimal(minuend))) {
            if (minuend.isNumberPositive() && subtrahend.isNumberPositive()) {
                result = subtract(minuend, subtrahend);
                result.setIsPositive(true);
            } else if (minuend.isNumberPositive() && !subtrahend.isNumberPositive()) {
                Decimal changedSubtrahend = subtrahend;
                changedSubtrahend.setIsPositive(true);
                result = new Addition().operate(minuend, changedSubtrahend);
            } else if (!minuend.isNumberPositive() && subtrahend.isNumberPositive()) {
                Decimal changedMinuend = minuend;
                changedMinuend.setIsPositive(true);
                result = new Addition().operate(subtrahend, changedMinuend);
                result.setIsPositive(false);
            } else if (!minuend.isNumberPositive() && !subtrahend.isNumberPositive()) {
                Decimal changedMinuend = minuend;
                changedMinuend.setIsPositive(true);
                Decimal changedSubtrahend = subtrahend;
                changedSubtrahend.setIsPositive(true);
                result = new Subtraction().operate(changedMinuend, changedSubtrahend);
                result.setIsPositive(false);
            }
        // minuend < subtrahend
        } else {
            if (minuend.isNumberPositive() && subtrahend.isNumberPositive()) {
                result = subtract(subtrahend, minuend);
                result.setIsPositive(false);
            } else if (minuend.isNumberPositive() && !subtrahend.isNumberPositive()) {
                Decimal changedSubtrahend = subtrahend;
                changedSubtrahend.setIsPositive(true);
                result = new Addition().operate(minuend, changedSubtrahend);
                result.setIsPositive(true);
            } else if (!minuend.isNumberPositive() && subtrahend.isNumberPositive()) {
                Decimal changedMinuend = minuend;
                changedMinuend.setIsPositive(true);
                result = new Addition().operate(changedMinuend, subtrahend);
                result.setIsPositive(false);
            } else if (!minuend.isNumberPositive() && !subtrahend.isNumberPositive()) {
                Decimal changedMinuend = minuend;
                changedMinuend.setIsPositive(true);
                Decimal changedSubtrahend = subtrahend;
                changedSubtrahend.setIsPositive(true);
                result = subtract(changedSubtrahend, changedMinuend);
                result.setIsPositive(true);
            }
        }
    }

    private void substractNumbersLeftOfComma(Decimal minuend, Decimal substrahend) {
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(minuend, substrahend, Location.LEFT.getIndex());
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = substractTwoDigits(getDigitLeftOfComma(i, minuend, Location.LEFT.getIndex()), getDigitLeftOfComma(i, substrahend, Location.LEFT.getIndex()));
            resultLeftOfComma.add(0, tempResult);
        }
    }

    private void substractNumbersRightOfComma(Decimal minuend, Decimal substrahend) {
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(minuend, substrahend, Location.RIGHT.getIndex());
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = substractTwoDigits(getDigitRightOfComma(lengthOfLongerNumber, i, minuend, Location.RIGHT.getIndex()), getDigitRightOfComma(lengthOfLongerNumber, i, substrahend, Location.RIGHT.getIndex()));
            resultRightOfComma.add(0, tempResult);
        }
    }

    public int substractTwoDigits(int minuend, int subtrahend) {
        int result = (minuend - subtrahend) - isOverflow();
        result = setOverflow(result);
        return result;
    }

    public int setOverflow(int number) {
        if (overflow = number < 0) {
            return number += 10;
        }
        return number;
    }

    public int isOverflow() {
        if (overflow) {
            overflow = false;
            return 1;
        }
        return 0;
    }

    public int getLengthOfLongerNumberSection(Decimal minuend, Decimal subtrahend, int location) {
        if (minuend.getNumberList().get(location).size() > subtrahend.getNumberList().get(location).size()) {
            return minuend.getNumberList().get(location).size();
        }
        return subtrahend.getNumberList().get(location).size();
    }

    @Override
    public Decimal operate(Decimal number1, Decimal number2) {
        executeOperation(number1, number2);
        result = trimDecimal(result);
        return result;
    }


}

