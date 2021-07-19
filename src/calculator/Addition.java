package calculator;

import java.util.ArrayList;
import java.util.List;

public class Addition extends Operation {
    boolean overflow = false;
    private static final int AFTER_COMMA = 0;
    private static final int BEFORE_COMMA = 1;

    public Addition(Decimal number1, Decimal number2) {
        operate(number1, number2);
    }

    @Override
    public Decimal operate(Decimal number1, Decimal number2) {
        Decimal result = new Decimal();
        List<Integer> resultBeforeComma = new ArrayList<>();
        List<Integer> resultAfterComma = new ArrayList<>();

        int lengthOfLongerNumber = getLengthOfLongerNumberSection(number1, number2, BEFORE_COMMA);
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = addTwoDigits(getDigitBeforeCommaAt(lengthOfLongerNumber, i, number1, BEFORE_COMMA), getDigitBeforeCommaAt(lengthOfLongerNumber, i, number2, BEFORE_COMMA));
            resultBeforeComma.add(0, tempResult);
        }

        lengthOfLongerNumber = getLengthOfLongerNumberSection(number1, number2, AFTER_COMMA);
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = addTwoDigits(getDigitAfterCommaAt(lengthOfLongerNumber, i, number1, AFTER_COMMA), getDigitAfterCommaAt(lengthOfLongerNumber, i, number2, AFTER_COMMA));
            resultAfterComma.add(0, tempResult);
        }

        if (overflow) {
            resultAfterComma.add(0, addTwoDigits(0, 0));
        }

        result.getNumberList().add(resultAfterComma);
        result.getNumberList().add(resultBeforeComma);
        printResult(result);
        return result;
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
