package calculator.operations;

import calculator.Decimal;
import calculator.Location;

import java.util.ArrayList;
import java.util.List;

public abstract class CalcHelper {
    protected List<Integer> resultRightOfComma = new ArrayList<>(); // unnötig?
    protected List<Integer> resultLeftOfComma = new ArrayList<>(); // unnötig?
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

    abstract void executeOperation(Decimal number1, Decimal number2);

    public boolean isDecimalHigherThanDecimal(Decimal number1, Decimal number2) {
        // left of comma same size
        if (number1.getNumberList().get(Location.LEFT.getIndex()).size() == number2.getNumberList().get(Location.LEFT.getIndex()).size()) {
            // compare left of comma
            for (int i = 0; getLengthOfLongerNumberSection(number1, number2, Location.LEFT.getIndex()) > i; i++) {
                // minuend bigger
                if (number1.getNumberList().get(Location.LEFT.getIndex()).get(i) > number2.getNumberList().get(Location.LEFT.getIndex()).get(i)) {
                    return true;
                    //subtrahend bigger
                } else if (number1.getNumberList().get(Location.LEFT.getIndex()).get(i) < number2.getNumberList().get(Location.LEFT.getIndex()).get(i)) {
                    return false;
                }
            }
            if (!number1.getNumberList().get(Location.RIGHT.getIndex()).isEmpty() && !number2.getNumberList().get(Location.RIGHT.getIndex()).isEmpty()) {
                // compare RIGHT_OF_COMMA
                for (int j = 0; getLengthOfLongerNumberSection(number1, number2, Location.RIGHT.getIndex()) > j; j++) {
                    // minuend bigger
                    if (number1.getNumberList().get(Location.RIGHT.getIndex()).get(j) > number2.getNumberList().get(Location.RIGHT.getIndex()).get(j)) {
                        return true;
                        //subtrahend bigger
                    } else if (number1.getNumberList().get(Location.RIGHT.getIndex()).get(j) < number2.getNumberList().get(Location.RIGHT.getIndex()).get(j)) {
                        return false;
                    }
                }
            } else if (!number1.getNumberList().get(Location.RIGHT.getIndex()).isEmpty() && number2.getNumberList().get(Location.RIGHT.getIndex()).isEmpty()) {
                for (int i = 0; number1.getNumberList().get(Location.RIGHT.getIndex()).size() > i; i++) {
                    if (number1.getNumberList().get(Location.RIGHT.getIndex()).get(i) != 0) {
                        return true;
                    }
                }
                return false;
            } else {
                for (int i = 0; number2.getNumberList().get(Location.RIGHT.getIndex()).size() > i; i++) {
                    if (number2.getNumberList().get(Location.RIGHT.getIndex()).get(i) != 0) {
                        return true;
                    }
                }
                return false;
            }
        }
        //minuend left of comma size bigger
        else if (number1.getNumberList().get(Location.LEFT.getIndex()).size() > number2.getNumberList().get(Location.LEFT.getIndex()).size()) {
            return true;
        }
        // subtrahend bigger
        else
            return false;
        return false;
    }

    protected void adjustForComma(Decimal divisor, Decimal dividend, Decimal divisorAsList, Decimal dividendAsList) {
        if (divisor.getNumberList().get(Location.RIGHT.getIndex()).size() > dividend.getNumberList().get(Location.RIGHT.getIndex()).size()) {
            for (int i = 0; divisor.getNumberList().get(Location.RIGHT.getIndex()).size() - dividend.getNumberList().get(Location.RIGHT.getIndex()).size() > i; i++) {
                dividendAsList.getNumberList().get(Location.LEFT.getIndex()).add(0);
            }
            //logger.info(divisor.getNumberList().get(RIGHT_OF_COMMA).size() - dividend.getNumberList().get(RIGHT_OF_COMMA).size() + " zeroes written to dividendAsList");
        } else if (divisor.getNumberList().get(Location.RIGHT.getIndex()).size() < dividend.getNumberList().get(Location.RIGHT.getIndex()).size()) {
            for (int i = 0; dividend.getNumberList().get(Location.RIGHT.getIndex()).size() - divisor.getNumberList().get(Location.RIGHT.getIndex()).size() > i; i++) {
                divisorAsList.getNumberList().get(Location.LEFT.getIndex()).add(0);
            }
            //logger.info(dividend.getNumberList().get(RIGHT_OF_COMMA).size() - divisor.getNumberList().get(RIGHT_OF_COMMA).size() + " zeroes written to divisorAsList");
        }
    }

    public Decimal trimDecimal(Decimal number) {
        if (number.getNumberList().get(Location.LEFT.getIndex()).size() > 1) {
            while (number.getNumberList().get(Location.LEFT.getIndex()).get(0) == 0) {
                number.getNumberList().get(Location.LEFT.getIndex()).remove(0);
                if (number.getNumberList().get(Location.LEFT.getIndex()).size() == 1) {
                    break;
                }
            }
        }
        if (number.getNumberList().get(Location.RIGHT.getIndex()).size() > 1) {
            while (number.getNumberList().get(Location.RIGHT.getIndex()).get(number.getNumberList().get(Location.RIGHT.getIndex()).size() - 1) == 0) {
                number.getNumberList().get(Location.RIGHT.getIndex()).remove(number.getNumberList().get(Location.RIGHT.getIndex()).size() - 1);
                if (number.getNumberList().get(Location.RIGHT.getIndex()).size() < 2) {
                    break;
                }
            }
        }
        return number;
    }

    public boolean isEqualTo(Decimal number) {
        if (!isDecimalHigherThanDecimal(this.getResult(), number) && !isDecimalHigherThanDecimal(number, this.getResult())) {
            return true;
        }
        return false;
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
