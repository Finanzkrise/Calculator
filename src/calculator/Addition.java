package calculator;

import java.util.ArrayList;
import java.util.List;

public class Addition extends Operation implements ListLocation {
    boolean overflow = false;

    public Addition(Decimal number1, Decimal number2) {
        operate(number1, number2);
    }

    @Override
    public Decimal operate(Decimal number1, Decimal number2) {
        Decimal result = new Decimal();
        List<Integer> resultRightOfComma = new ArrayList<>();
        List<Integer> resultLeftOfComma = new ArrayList<>();

        addNumbersBeforeComma(number1, number2, resultRightOfComma);
        addNumbersAfterComma(number1, number2, resultLeftOfComma);

        if (overflow) {
            resultLeftOfComma.add(0, addTwoDigits(0, 0));
        }

        result.getNumberList().add(resultLeftOfComma);
        result.getNumberList().add(resultRightOfComma);
        printResult(result);
        return result;
    }

    private void addNumbersAfterComma(Decimal number1, Decimal number2, List<Integer> resultAfterComma) {
        int lengthOfLongerNumber;
        lengthOfLongerNumber = getLengthOfLongerNumberSection(number1, number2, LEFT_OF_COMMA);
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = addTwoDigits(getDigitAfterCommaAt(lengthOfLongerNumber, i, number1, LEFT_OF_COMMA), getDigitAfterCommaAt(lengthOfLongerNumber, i, number2, LEFT_OF_COMMA));
            resultAfterComma.add(0, tempResult);
        }
    }

    private void addNumbersBeforeComma(Decimal number1, Decimal number2, List<Integer> resultBeforeComma) {
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(number1, number2, RIGHT_OF_COMMA);
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = addTwoDigits(getDigitBeforeCommaAt(lengthOfLongerNumber, i, number1, RIGHT_OF_COMMA), getDigitBeforeCommaAt(lengthOfLongerNumber, i, number2, RIGHT_OF_COMMA));
            resultBeforeComma.add(0, tempResult);
        }
    }

    public int setOverflow(int number) {
        overflow = number > 9;
        return number % 10;
    }

    public int isOverflow() {
        if (overflow) {
            overflow = false;
            return 1;
        }
        return 0;
    }

    public int addTwoDigits(int number1, int number2) {
        int result = number1 + number2 + isOverflow();
        result = setOverflow(result);
        return result;
    }

}
