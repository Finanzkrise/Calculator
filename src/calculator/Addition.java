package calculator;

import java.util.ArrayList;
import java.util.List;

public class Addition implements Operation {
    boolean overflow = false;
    private static final int AFTER_COMMA = 0;
    private static final int BEFORE_COMMA = 1;

    @Override
    public Decimal operate(Decimal number1, Decimal number2) {
        Decimal result = new Decimal();
        List<Integer> resultBeforeComma = new ArrayList<>();
        List<Integer> resultAfterComma = new ArrayList<>();

        int i = 0;
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(number1, number2, BEFORE_COMMA);
        while (lengthOfLongerNumber > i) {
            int tempResult = addTwoDigits(setDigitBeforeCommaAt(lengthOfLongerNumber, i, number1, BEFORE_COMMA), setDigitBeforeCommaAt(lengthOfLongerNumber, i, number2, BEFORE_COMMA));
            resultBeforeComma.add(0, tempResult);
            i++;
        }

        i = 0;
        lengthOfLongerNumber = getLengthOfLongerNumberSection(number1, number2, AFTER_COMMA);
        while (lengthOfLongerNumber > i) {
            int tempResult = addTwoDigits(setDigitAfterCommaAt(lengthOfLongerNumber, i, number1, AFTER_COMMA), setDigitAfterCommaAt(lengthOfLongerNumber, i, number2, AFTER_COMMA));
            resultAfterComma.add(0, tempResult);
            i++;
        }

        if (overflow) {
            resultAfterComma.add(0, addTwoDigits(0, 0));
        }

        result.getNumberList().add(resultAfterComma);
        result.getNumberList().add(resultBeforeComma);
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

    public boolean isIndexInBounds(int index, Decimal number, int section) {
        return index >= 0 && index < number.getNumberList().get(section).size();
    }

    public int setDigitAfterCommaAt(int lengthOfLongerNumber, int index, Decimal number, int location) {
        int digit = 0;
        if (isIndexInBounds(number.getNumberList().get(location).size() - index - 1, number, location)) {
            digit = number.getNumberList().get(location).get(number.getNumberList().get(location).size() - index - 1);
        }
        return digit;
    }

    public int setDigitBeforeCommaAt(int lengthOfLongerNumber, int index, Decimal number, int location) {
        int digit = 0;
        if (isIndexInBounds(lengthOfLongerNumber - index - 1, number, location)) {
            digit = number.getNumberList().get(location).get(lengthOfLongerNumber - index - 1);
        }
        return digit;
    }

    public int getLengthOfLongerNumberSection(Decimal number1, Decimal number2, int location) {
        if (number1.getNumberList().get(location).size() > number2.getNumberList().get(location).size()) {
            return number1.getNumberList().get(location).size();
        }
        return number2.getNumberList().get(location).size();
    }

}
