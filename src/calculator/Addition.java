package calculator;

public class Addition extends Operation implements ListLocation {
    boolean overflow = false;

    public Addition() {

    }

    public Addition(Decimal number1, Decimal number2) {
        operate(number1, number2);
    }

    @Override
    public Decimal operate(Decimal number1, Decimal number2) {
        Decimal result = new Decimal();

        addNumbersRightOfComma(number1, number2);
        addNumbersLeftOfComma(number1, number2);

        if (overflow) {
            getResultLeftOfComma().add(0, addTwoDigits(0, 0));
        }

        result.getNumberList().add(getResultLeftOfComma());
        result.getNumberList().add(getResultRightOfComma());
        return result;
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
