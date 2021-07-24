package calculator;

public class Subtraction extends Operation implements ListLocation {
    boolean overflow = false;

    public Subtraction() {
    }

    public Subtraction(Decimal minuend, Decimal subtrahend) {
        result = operate(minuend, subtrahend);
    }

    @Override
    public Decimal operate(Decimal minuend, Decimal subtrahend) {
        executeOperation(minuend, subtrahend);
        trimResult();
        return result;
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
        if (isNumberOneHigherThanNumberTwo(minuend, subtrahend)) {
            // ++
            if (minuend.isNumberPositive() && subtrahend.isNumberPositive()) {
                result = subtract(minuend, subtrahend);
                result.setIsPositive(true);
            // +-
            } else if (minuend.isNumberPositive() && !subtrahend.isNumberPositive()) {
                Decimal changedSubtrahend = subtrahend;
                changedSubtrahend.setIsPositive(true);
                result = new Addition().operate(minuend, changedSubtrahend);
            // -+
            } else if (!minuend.isNumberPositive() && subtrahend.isNumberPositive()) {
                Decimal changedMinuend = minuend;
                changedMinuend.setIsPositive(true);
                result = new Addition().operate(subtrahend, changedMinuend);
                result.setIsPositive(false);
            // --
            } else if (!minuend.isNumberPositive() && !subtrahend.isNumberPositive()) {
                Decimal changedMinuend = minuend;
                changedMinuend.setIsPositive(true);
                Decimal changedSubtrahend = subtrahend;
                changedSubtrahend.setIsPositive(true);
                result = new Addition().operate(changedMinuend, changedSubtrahend);
                result.setIsPositive(false);
            }
        // minuend < subtrahend
        } else {
            // ++
            if (minuend.isNumberPositive() && subtrahend.isNumberPositive()) {
                result = subtract(subtrahend, minuend);
                result.setIsPositive(false);
            // +-
            } else if (minuend.isNumberPositive() && !subtrahend.isNumberPositive()) {
                Decimal changedSubtrahend = subtrahend;
                changedSubtrahend.setIsPositive(true);
                result = new Addition().operate(minuend, changedSubtrahend);
                result.setIsPositive(true);
            // -+
            } else if (!minuend.isNumberPositive() && subtrahend.isNumberPositive()) {
                Decimal changedMinuend = minuend;
                changedMinuend.setIsPositive(true);
                result = new Addition().operate(changedMinuend, subtrahend);
                result.setIsPositive(false);
            // --
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
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(minuend, substrahend, LEFT_OF_COMMA);
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = substractTwoDigits(getDigitLeftOfComma(i, minuend, LEFT_OF_COMMA), getDigitLeftOfComma(i, substrahend, LEFT_OF_COMMA));
            resultLeftOfComma.add(0, tempResult);
        }
    }

    private void substractNumbersRightOfComma(Decimal minuend, Decimal substrahend) {
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(minuend, substrahend, RIGHT_OF_COMMA);
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = substractTwoDigits(getDigitRightOfComma(lengthOfLongerNumber, i, minuend, RIGHT_OF_COMMA), getDigitRightOfComma(lengthOfLongerNumber, i, substrahend, RIGHT_OF_COMMA));
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


}

