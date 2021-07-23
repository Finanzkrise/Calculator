package calculator;

public class Addition extends Operation implements ListLocation {
    boolean overflow = false;

    public Addition() {
    }

    public Addition(Decimal number1, Decimal number2) {
        result = operate(number1, number2);
    }

    @Override
    public Decimal operate(Decimal number1, Decimal number2) {
        executeOperation(number1, number2);
        trimResult();
        return result;
    }

    public Decimal addPositiveNumbers(Decimal number1, Decimal number2) {
        Decimal result = new Decimal();
        addNumbersRightOfComma(number1, number2);
        addNumbersLeftOfComma(number1, number2);
        if (overflow) {
            getResultLeftOfComma().add(0, addTwoDigits(0, 0));
        }
        result.getNumberList().add(getResultLeftOfComma());
        result.getNumberList().add(getResultRightOfComma());
        result = new Decimal(resultLeftOfComma, resultRightOfComma);
        return result;
    }

    void executeOperation(Decimal number1, Decimal number2) {
        if (number1.isNumberPositive() && number2.isNumberPositive()) {
            result = addPositiveNumbers(number1, number2);
            result.setIsPositive(true);
        } else if (!number1.isNumberPositive() && !number2.isNumberPositive()) {
            result = addPositiveNumbers(number1, number2);
            result.setIsPositive(false);
        } else {
            if (isNumberOneHigherThanNumberTwo(number1, number2)) {
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

    @Override
    public boolean isNumberOneHigherThanNumberTwo(Decimal number1, Decimal number2) {
        // same size
        if (number1.getNumberList().get(LEFT_OF_COMMA).size() == number2.getNumberList().get(LEFT_OF_COMMA).size()) {
            // compare left of comma
            for (int i = 0; getLengthOfLongerNumberSection(number1, number2, LEFT_OF_COMMA) > i; i++) {
                // minuend bigger
                if (number1.getNumberList().get(LEFT_OF_COMMA).get(i) > number2.getNumberList().get(LEFT_OF_COMMA).get(i)) {
                    return true;
                    //subtrahend bigger
                } else if (number1.getNumberList().get(LEFT_OF_COMMA).get(i) < number2.getNumberList().get(LEFT_OF_COMMA).get(i)) {
                    return false;
                }
                // compare RIGHT_OF_COMMA
                else {
                    for (int j = 0; getLengthOfLongerNumberSection(number1, number2, RIGHT_OF_COMMA) > j; j++) {
                        // minuend bigger
                        if (number1.getNumberList().get(RIGHT_OF_COMMA).get(j) > number2.getNumberList().get(RIGHT_OF_COMMA).get(j)) {
                            System.out.println("true");
                            return true;
                            //subtrahend bigger
                        } else if (number1.getNumberList().get(RIGHT_OF_COMMA).get(j) < number2.getNumberList().get(RIGHT_OF_COMMA).get(j)) {
                            return false;
                        }
                    }
                }
            }
        }
        //minuend bigger
        else if (number1.getNumberList().get(LEFT_OF_COMMA).size() > number2.getNumberList().get(LEFT_OF_COMMA).size()) {
            System.out.println("true");
            return true;
        }
        // subtrahend bigger
        else {
            return false;
        }
        return false;
    }

    private void addNumbersLeftOfComma(Decimal number1, Decimal number2) {
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(number1, number2, LEFT_OF_COMMA);
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = addTwoDigits(getDigitLeftOfComma(i, number1, LEFT_OF_COMMA), getDigitLeftOfComma(i, number2, LEFT_OF_COMMA));
            getResultLeftOfComma().add(0, tempResult);
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

}
