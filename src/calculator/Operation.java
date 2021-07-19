package calculator;

public abstract class Operation {

    abstract Decimal operate(Decimal number1, Decimal number2);

    abstract int setOverflow(int number);

    public int getLengthOfLongerNumberSection(Decimal number1, Decimal number2, int location) {
        if (number1.getNumberList().get(location).size() > number2.getNumberList().get(location).size()) {
            return number1.getNumberList().get(location).size();
        }
        return number2.getNumberList().get(location).size();
    }

    public int getDigitAfterCommaAt(int lengthOfLongerNumber, int index, Decimal number, int location) {
        int digit = 0;
        if (isIndexInBounds(number.getNumberList().get(location).size() - index - 1, number, location)) {
            digit = number.getNumberList().get(location).get(number.getNumberList().get(location).size() - index - 1);
        }
        return digit;
    }

    public int getDigitBeforeCommaAt(int lengthOfLongerNumber, int index, Decimal number, int location) {
        int digit = 0;
        if (isIndexInBounds(lengthOfLongerNumber - index - 1, number, location)) {
            digit = number.getNumberList().get(location).get(lengthOfLongerNumber - index - 1);
        }
        return digit;
    }

    public boolean isIndexInBounds(int index, Decimal number, int section) {
        return index >= 0 && index < number.getNumberList().get(section).size();
    }

    public void printResult(Decimal result) {
        System.out.println(result);
    }


}
