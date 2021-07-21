package calculator;

import java.util.List;

public class Substraction extends Operation implements ListLocation {
    boolean overflow = false;
    private Decimal minuend;
    private Decimal subtrahend;

    public Substraction(){

    }

    public Substraction(Decimal minuend, Decimal subtrahend) {
        operate(minuend, subtrahend);
    }

    @Override
    public Decimal operate(Decimal minuend, Decimal subtrahend) {
        setMinuend(minuend);
        setSubtrahend(subtrahend);
        Decimal result = new Decimal();

        result = substract(minuend, subtrahend, getResultRightOfComma(), getResultLeftOfComma() );

        System.out.println(result);
        return result;
    }

    public Decimal substract(Decimal minuend, Decimal subtrahend, List<Integer> resultRightOfComma, List<Integer> resultLeftOfComma){
        Decimal result = new Decimal();
        if (isMinuendHigherThanSubtrahend(minuend, subtrahend)) {
            substractNumbersRightOfComma(minuend, subtrahend, resultRightOfComma);
            substractNumbersLeftOfComma(minuend, subtrahend, resultLeftOfComma);
        } else {
            result.setIsPositive(false);
            substractNumbersRightOfComma(subtrahend, minuend, resultRightOfComma);
            substractNumbersLeftOfComma(subtrahend, minuend, resultLeftOfComma);
        }

        result.getNumberList().add(getResultLeftOfComma());
        result.getNumberList().add(getResultRightOfComma());
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

    private void substractNumbersLeftOfComma(Decimal minuend, Decimal substrahend, List<Integer> resultAfterComma) {
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(LEFT_OF_COMMA);
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = substractTwoDigits(getDigitLeftOfComma(i, minuend, LEFT_OF_COMMA), getDigitLeftOfComma(i, substrahend, LEFT_OF_COMMA));
            resultAfterComma.add(0, tempResult);
        }
    }

    private void substractNumbersRightOfComma(Decimal minuend, Decimal substrahend, List<Integer> resultBeforeComma) {
        int lengthOfLongerNumber = getLengthOfLongerNumberSection(RIGHT_OF_COMMA);
        for (int i = 0; lengthOfLongerNumber > i; i++) {
            int tempResult = substractTwoDigits(getDigitRightOfComma(lengthOfLongerNumber, i, minuend, RIGHT_OF_COMMA), getDigitRightOfComma(lengthOfLongerNumber, i, substrahend, RIGHT_OF_COMMA));
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

