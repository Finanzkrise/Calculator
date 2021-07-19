package calculator;

import java.util.ArrayList;
import java.util.List;

public class Substraction extends Operation {
    boolean overflow = false;
    private static final int AFTER_COMMA = 0;
    private static final int BEFORE_COMMA = 1;
    int lengthOfLongerNumber;
    private Decimal minuend;
    private Decimal subtrahend;
    private boolean isResultPositive = true;

    public Substraction(Decimal minuend, Decimal subtrahend){
        operate(minuend, subtrahend);
    }

    @Override
    public Decimal operate(Decimal minuend, Decimal substrahend) {
        setMinuend(minuend);
        setSubtrahend(substrahend);
        Decimal result = new Decimal();
        List<Integer> resultBeforeComma = new ArrayList<>();
        List<Integer> resultAfterComma = new ArrayList<>();
        lengthOfLongerNumber = getLengthOfLongerNumberSection(minuend, substrahend, BEFORE_COMMA);
        boolean isMinuendHigher = isMinuendHigherThanSubtrahend(minuend, substrahend);

        if (isMinuendHigher) {
            int lengthOfLongerNumber = getLengthOfLongerNumberSection(BEFORE_COMMA);
            for (int i = 0; lengthOfLongerNumber > i; i++) {
                int tempResult = substractTwoDigits(getDigitBeforeCommaAt(lengthOfLongerNumber, i, minuend, BEFORE_COMMA), getDigitBeforeCommaAt(lengthOfLongerNumber, i, substrahend, BEFORE_COMMA));
                resultBeforeComma.add(0, tempResult);
            }
            lengthOfLongerNumber = getLengthOfLongerNumberSection(AFTER_COMMA);
            for (int i = 0; lengthOfLongerNumber > i; i++) {
                int tempResult = substractTwoDigits(getDigitAfterCommaAt(lengthOfLongerNumber, i, minuend, AFTER_COMMA), getDigitAfterCommaAt(lengthOfLongerNumber, i, substrahend, AFTER_COMMA));
                resultAfterComma.add(0, tempResult);
            }
        }
        else {
            result.setIsPositive(false);
            int lengthOfLongerNumber = getLengthOfLongerNumberSection(BEFORE_COMMA);
            for (int i = 0; lengthOfLongerNumber > i; i++) {
                int tempResult = substractTwoDigits(getDigitBeforeCommaAt(lengthOfLongerNumber, i, substrahend, BEFORE_COMMA), getDigitBeforeCommaAt(lengthOfLongerNumber, i, minuend, BEFORE_COMMA));
                resultBeforeComma.add(0, tempResult);
            }
            lengthOfLongerNumber = getLengthOfLongerNumberSection(AFTER_COMMA);
            for (int i = 0; lengthOfLongerNumber > i; i++) {
                int tempResult = substractTwoDigits(getDigitAfterCommaAt(lengthOfLongerNumber, i, substrahend, AFTER_COMMA), getDigitAfterCommaAt(lengthOfLongerNumber, i, minuend, AFTER_COMMA));
                resultAfterComma.add(0, tempResult);
            }
        }

        result.getNumberList().add(resultAfterComma);
        result.getNumberList().add(resultBeforeComma);
        printResult(result);
        return result;
    }

    private void setSubtrahend(Decimal substrahend) {
        this.subtrahend = substrahend;
    }

    private void setMinuend(Decimal minuend) {
        this.minuend = minuend;
    }

    private boolean isMinuendHigherThanSubtrahend(Decimal minuend, Decimal subtrahend) {
        // same size
        if (minuend.getNumberList().get(AFTER_COMMA).size() == subtrahend.getNumberList().get(AFTER_COMMA).size()) {
            for (int i = 0; lengthOfLongerNumber > i; i++) {
                if (minuend.getNumberList().get(AFTER_COMMA).get(i) > subtrahend.getNumberList().get(AFTER_COMMA).get(i)) {
                    System.out.println("true");
                    return true;
                } else if (minuend.getNumberList().get(AFTER_COMMA).get(i) < subtrahend.getNumberList().get(AFTER_COMMA).get(i)) {
                    return false;
                }
            }
        }
        //minuend bigger
        else if (minuend.getNumberList().get(AFTER_COMMA).size() > subtrahend.getNumberList().get(AFTER_COMMA).size()) {
            System.out.println("true");
            return true;
        }
        // subtrahend bigger
        else {
            return false;

        }
        return false;
    }

    public int substractTwoDigits(int minuend, int subtrahend) {
        int result = (minuend - subtrahend) - isOverflow();
        result = setOverflow(result);
        return result;
    }

    public int setOverflow(int number) {

        if(overflow = number < 0){
            return number += 10;
        }
        return number;
    }

    public int getLengthOfLongerNumberSection(int location) {
        if (minuend.getNumberList().get(location).size() > subtrahend.getNumberList().get(location).size()) {
            return minuend.getNumberList().get(location).size();
        }
        return subtrahend.getNumberList().get(location).size();
    }

    public int isOverflow() {
        if (overflow) {
            overflow = false;
            return 1;
        }
        return 0;
    }


}

