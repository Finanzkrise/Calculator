package calculator;

public class Subtraction extends Operation implements ListLocation {
    boolean overflow = false;
    private Decimal minuend;
    private Decimal subtrahend;

    public Subtraction() {
    }

    public Subtraction(Decimal minuend, Decimal subtrahend) {
        result = operate(minuend, subtrahend);
    }

    @Override
    public Decimal operate(Decimal minuend, Decimal subtrahend) {
        Decimal result;
        this.minuend = minuend;
        this.subtrahend = subtrahend;

        result = subtract(minuend, subtrahend);
        System.out.println(result);
        return result;
    }

    public Decimal subtract(Decimal minuend, Decimal subtrahend) {
        Decimal result = new Decimal();
        if (isMinuendHigherThanSubtrahend(minuend, subtrahend)) {
            substractNumbersRightOfComma(minuend, subtrahend);
            substractNumbersLeftOfComma(minuend, subtrahend);
        } else {
            result.setIsPositive(false);
            substractNumbersRightOfComma(subtrahend, minuend);
            substractNumbersLeftOfComma(subtrahend, minuend);
        }

        result.getNumberList().add(getResultLeftOfComma());
        result.getNumberList().add(getResultRightOfComma());
        result = trimResult(result);
        return result;
    }

    private boolean isMinuendHigherThanSubtrahend(Decimal minuend, Decimal subtrahend) {
        // same size
        if (minuend.getNumberList().get(LEFT_OF_COMMA).size() == subtrahend.getNumberList().get(LEFT_OF_COMMA).size()) {
            // compare digit on index i
            for (int i = 0; getLengthOfLongerNumberSection(minuend, subtrahend, LEFT_OF_COMMA) > i; i++) {
                // minuend bigger
                if (minuend.getNumberList().get(LEFT_OF_COMMA).get(i) > subtrahend.getNumberList().get(LEFT_OF_COMMA).get(i)) {
                    System.out.println("true");
                    return true;
                    //subtrahend bigger
                } else if (minuend.getNumberList().get(LEFT_OF_COMMA).get(i) < subtrahend.getNumberList().get(LEFT_OF_COMMA).get(i)) {
                    return false;
                }
                // compare RIGHT_OF_COMMA
                else {
                    for (int j = 0; getLengthOfLongerNumberSection(minuend, subtrahend, RIGHT_OF_COMMA) > j; j++) {
                        // minuend bigger
                        if (minuend.getNumberList().get(RIGHT_OF_COMMA).get(j) > subtrahend.getNumberList().get(RIGHT_OF_COMMA).get(j)) {
                            System.out.println("true");
                            return true;
                            //subtrahend bigger
                        } else if (minuend.getNumberList().get(RIGHT_OF_COMMA).get(j) < subtrahend.getNumberList().get(RIGHT_OF_COMMA).get(j)) {
                            return false;
                        }
                    }
                }
            }
        }
        //minuend bigger
        else if (minuend.getNumberList().get(LEFT_OF_COMMA).size() > subtrahend.getNumberList().get(LEFT_OF_COMMA).size()) {
            System.out.println("true");
            return true;
        }
        // subtrahend bigger
        else {
            return false;
        }
        return false;
    }

    private void substractNumbersLeftOfComma(Decimal minuend, Decimal substrahend) {
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(LEFT_OF_COMMA);
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = substractTwoDigits(getDigitLeftOfComma(i, minuend, LEFT_OF_COMMA), getDigitLeftOfComma(i, substrahend, LEFT_OF_COMMA));
            getResultLeftOfComma().add(0, tempResult);
        }
    }

    private void substractNumbersRightOfComma(Decimal minuend, Decimal substrahend) {
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(RIGHT_OF_COMMA);
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = substractTwoDigits(getDigitRightOfComma(lengthOfLongerNumber, i, minuend, RIGHT_OF_COMMA), getDigitRightOfComma(lengthOfLongerNumber, i, substrahend, RIGHT_OF_COMMA));
            getResultRightOfComma().add(0, tempResult);
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

    public int getLengthOfLongerNumberSection(int location) {
        if (minuend.getNumberList().get(location).size() > subtrahend.getNumberList().get(location).size()) {
            return minuend.getNumberList().get(location).size();
        }
        return subtrahend.getNumberList().get(location).size();
    }


}

