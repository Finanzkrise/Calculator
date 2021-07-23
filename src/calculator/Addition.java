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
        result = new Decimal();
        addNumbersRightOfComma(number1, number2);
        addNumbersLeftOfComma(number1, number2);

        if (overflow) {
            getResultLeftOfComma().add(0, addTwoDigits(0, 0));
        }

        result.getNumberList().add(getResultLeftOfComma());
        result.getNumberList().add(getResultRightOfComma());
        result = new Decimal(resultLeftOfComma, resultRightOfComma);
        setPositivityOfResult(number1, number2);
        System.out.println(result.isNumberPositive());
        //result = trimResult(result);
        return result;
    }

    @Override
    void setPositivityOfResult(Decimal number1, Decimal number2) {
        if (number1.isNumberPositive() && number2.isNumberPositive()) {
          result.setIsPositive(true);

        }
        else if (number1.isNumberPositive() || number2.isNumberPositive()){
           result = new Subtraction().operate(number1, number2);

        }
        else if (!number1.isNumberPositive() && !number2.isNumberPositive()) {
        result.setIsPositive(false);
        }

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
