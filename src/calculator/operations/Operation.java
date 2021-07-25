package calculator.operations;

import calculator.Decimal;
import calculator.ListLocation;

import java.util.ArrayList;
import java.util.List;

public abstract class Operation implements ListLocation {

    protected List<Integer> resultRightOfComma = new ArrayList<>();
    protected List<Integer> resultLeftOfComma = new ArrayList<>();
    protected Decimal result;

    public List<Integer> getResultLeftOfComma() {
        return resultLeftOfComma;
    }

    public List<Integer> getResultRightOfComma() {
        return resultRightOfComma;
    }

    public Decimal getResult() {
        return result;
    }

    public void setResult(Decimal result) {
        this.result = result;
    }

    public abstract int setOverflow(int number);

    abstract Decimal operate(Decimal number1, Decimal number2);

    abstract void executeOperation(Decimal number1, Decimal number2);

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
                    if (number1.getNumberList().get(RIGHT_OF_COMMA).size() > 0 && number2.getNumberList().get(RIGHT_OF_COMMA).size() > 0) {
                        for (int j = 0; getLengthOfLongerNumberSection(number1, number2, RIGHT_OF_COMMA) > j; j++) {

                            // minuend bigger
                            if (number1.getNumberList().get(RIGHT_OF_COMMA).get(j) > number2.getNumberList().get(RIGHT_OF_COMMA).get(j)) {

                                return true;
                                //subtrahend bigger
                            } else if (number1.getNumberList().get(RIGHT_OF_COMMA).get(j) < number2.getNumberList().get(RIGHT_OF_COMMA).get(j)) {
                                return false;
                            }
                        }
                    }
                    else if (!(number1.getNumberList().size() > 1) && number2.getNumberList().size() > 1){
                        return false;
                    }
                    else {
                        return true;
                    }
                }
            }
        }
        //minuend bigger
        else if (number1.getNumberList().get(LEFT_OF_COMMA).size() > number2.getNumberList().get(LEFT_OF_COMMA).size()) {
            return true;
        }
        // subtrahend bigger
        else {
            return false;
        }
        return false;
    }

    public Decimal trimDecimal(Decimal number) {
        if (number.getNumberList().get(LEFT_OF_COMMA).size() > 1) {
            while (number.getNumberList().get(LEFT_OF_COMMA).get(0) == 0) {
                number.getNumberList().get(LEFT_OF_COMMA).remove(0);
                if (number.getNumberList().get(LEFT_OF_COMMA).size() == 1) {
                    break;
                }
            }
        }
        if (number.getNumberList().get(RIGHT_OF_COMMA).size() > 1) {
            while (number.getNumberList().get(RIGHT_OF_COMMA).get(number.getNumberList().get(RIGHT_OF_COMMA).size() - 1) == 0) {
                number.getNumberList().get(RIGHT_OF_COMMA).remove(number.getNumberList().get(RIGHT_OF_COMMA).size() - 1);
                if (number.getNumberList().get(RIGHT_OF_COMMA).size() < 2) {
                    break;
                }
            }
        }
        return number;
    }


    public int getLengthOfLongerNumberSection(Decimal number1, Decimal number2, int location) {
        if (number1.getNumberList().get(location).size() > number2.getNumberList().get(location).size()) {
            return number1.getNumberList().get(location).size();
        }
        return number2.getNumberList().get(location).size();
    }

    public int getDigitRightOfComma(int lengthOfLongerNumber, int index, Decimal number, int location) {
        int digit = 0;
        if (isIndexInBounds(lengthOfLongerNumber - index - 1, number, location)) {
            digit = number.getNumberList().get(location).get(lengthOfLongerNumber - index - 1);
        }
        return digit;
    }

    public int getDigitLeftOfComma(int index, Decimal number, int location) {
        int digit = 0;
        if (isIndexInBounds(number.getNumberList().get(location).size() - index - 1, number, location)) {
            digit = number.getNumberList().get(location).get(number.getNumberList().get(location).size() - index - 1);
        }
        return digit;
    }

    public boolean isIndexInBounds(int index, Decimal number, int location) {
        return index >= 0 && index < number.getNumberList().get(location).size();
    }


}
