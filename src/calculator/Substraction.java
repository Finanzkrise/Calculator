package calculator;

import java.util.ArrayList;
import java.util.List;

public class Substraction extends Operation implements ListLocation {
    boolean overflow = false;
    int lengthOfLongerNumber;
    private Decimal minuend;
    private Decimal subtrahend;
    private boolean isResultPositive = true;

    public Substraction(Decimal minuend, Decimal subtrahend) {
        operate(minuend, subtrahend);
    }

    @Override
    public Decimal operate(Decimal minuend, Decimal subtrahend) {
        setMinuend(minuend);
        setSubtrahend(subtrahend);
        Decimal result = new Decimal();
        List<Integer> resultBeforeComma = new ArrayList<>();
        List<Integer> resultAfterComma = new ArrayList<>();
        boolean isMinuendHigher = isMinuendHigherThanSubtrahend(minuend, subtrahend);

        result = substract(minuend, subtrahend, resultBeforeComma, resultAfterComma );

        printResult(result);
        return result;
    }

    public Decimal substract(Decimal minuend, Decimal subtrahend, List<Integer> resultBeforeComma, List<Integer> resultAfterComma){
        Decimal result = new Decimal();
        if (isMinuendHigherThanSubtrahend(minuend, subtrahend)) {
            substractNumbersRightOfComma(minuend, subtrahend, resultBeforeComma);
            substractNumbersLeftOfComma(minuend, subtrahend, resultAfterComma);
        } else {
            result.setIsPositive(false);
            substractNumbersRightOfComma(subtrahend, minuend, resultBeforeComma);
            substractNumbersLeftOfComma(subtrahend, minuend, resultAfterComma);
        }
        result.getNumberList().add(resultAfterComma);
        result.getNumberList().add(resultBeforeComma);
        return  result;
    }

    private void setSubtrahend(Decimal substrahend) {
        this.subtrahend = substrahend;
    }

    private void setMinuend(Decimal minuend) {
        this.minuend = minuend;
    }

    // noch nicht fertig
    private boolean isMinuendHigherThanSubtrahend(Decimal minuend, Decimal subtrahend) {
        // same size
        if (minuend.getNumberList().get(LEFT_OF_COMMA).size() == subtrahend.getNumberList().get(LEFT_OF_COMMA).size()) {
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
                        if (minuend.getNumberList().get(LEFT_OF_COMMA).get(j) > subtrahend.getNumberList().get(LEFT_OF_COMMA).get(j)) {
                            System.out.println("true");
                            return true;
                            //subtrahend bigger
                        } else if (minuend.getNumberList().get(LEFT_OF_COMMA).get(j) < subtrahend.getNumberList().get(LEFT_OF_COMMA).get(j)) {
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

    private void substractNumbersLeftOfComma(Decimal minuend, Decimal substrahend, List<Integer> resultAfterComma) {
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(LEFT_OF_COMMA);
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = substractTwoDigits(getDigitAfterCommaAt(lengthOfLongerNumber, i, minuend, LEFT_OF_COMMA), getDigitAfterCommaAt(lengthOfLongerNumber, i, substrahend, LEFT_OF_COMMA));
            resultAfterComma.add(0, tempResult);
        }
    }

    private void substractNumbersRightOfComma(Decimal minuend, Decimal substrahend, List<Integer> resultBeforeComma) {
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(RIGHT_OF_COMMA);
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = substractTwoDigits(getDigitBeforeCommaAt(lengthOfLongerNumber, i, minuend, RIGHT_OF_COMMA), getDigitBeforeCommaAt(lengthOfLongerNumber, i, substrahend, RIGHT_OF_COMMA));
            resultBeforeComma.add(0, tempResult);
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

