package calculator;

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

    abstract int setOverflow(int number);

    abstract Decimal operate(Decimal number1, Decimal number2);

    abstract void executeOperation(Decimal number1, Decimal number2);

    public abstract boolean isNumberOneHigherThanNumberTwo(Decimal number1, Decimal number2);

    public void trimResult() {
        if (result.getNumberList().get(LEFT_OF_COMMA).size() > 1)
            while (result.getNumberList().get(LEFT_OF_COMMA).get(0) == 0) {
                result.getNumberList().get(LEFT_OF_COMMA).remove(0);
                if (result.getNumberList().get(LEFT_OF_COMMA).size() == 1) {
                    break;
                }
            }
        if (result.getNumberList().get(RIGHT_OF_COMMA).size() > 1) {
            while (result.getNumberList().get(RIGHT_OF_COMMA).get(result.getNumberList().get(RIGHT_OF_COMMA).size() - 1) == 0) {
                result.getNumberList().get(RIGHT_OF_COMMA).remove(result.getNumberList().get(RIGHT_OF_COMMA).size() - 1);
                if (result.getNumberList().get(RIGHT_OF_COMMA).size() < 2) {
                    break;
                }
            }
        }
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
