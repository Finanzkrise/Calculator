package calculator.operations;

import calculator.Decimal;
import calculator.ListLocation;

import java.util.List;

public class Division extends Operation implements ListLocation {
    int overflow = 0;
    public Division() {
    }

    @Override
    Decimal operate(Decimal number1, Decimal number2) {
        executeOperation(number1, number2);
        trimResult();
        return null;
    }

    @Override
    void executeOperation(Decimal number1, Decimal number2) {
        dividePositiveNumbers(number1, number2);
    }

    public void dividePositiveNumbers(Decimal dividend, Decimal divisor) {
        Decimal tempDividend;
        Decimal tempDivisor;

        Decimal dividendAsList = new Decimal();
        for (int i = 0; dividend.getNumberList().get(LEFT_OF_COMMA).size() > i; i++) {
            dividendAsList.getNumberList().get(LEFT_OF_COMMA).add(dividend.getNumberList().get(LEFT_OF_COMMA).get(i));
        }
        for (int i = 0; dividend.getNumberList().get(RIGHT_OF_COMMA).size() > i; i++) {
            dividendAsList.getNumberList().get(LEFT_OF_COMMA).add(dividend.getNumberList().get(LEFT_OF_COMMA).get(i));
        }

        Decimal divisorAsList = new Decimal();
        for (int i = 0; divisor.getNumberList().get(LEFT_OF_COMMA).size() > i; i++) {
            divisorAsList.getNumberList().get(LEFT_OF_COMMA).add(divisor.getNumberList().get(LEFT_OF_COMMA).get(i));
        }
        for (int i = 0; divisor.getNumberList().get(RIGHT_OF_COMMA).size() > i; i++) {
            divisorAsList.getNumberList().get(LEFT_OF_COMMA).add(divisor.getNumberList().get(LEFT_OF_COMMA).get(i));
        }

        tempDividend = new Decimal();
        int i;
        if (overflow != 0){
            tempDividend.getNumberList().get(LEFT_OF_COMMA).add(overflow);
        }
        for (i = 0; divisorAsList.getNumberList().get(LEFT_OF_COMMA).size() > i ; i++) {
            tempDividend.getNumberList().get(LEFT_OF_COMMA).add(dividendAsList.getNumberList().get(LEFT_OF_COMMA).get(i));
        }

        //not possible to divide?
        if (isNumberOneHigherThanNumberTwo(divisorAsList, tempDividend)) {
            tempDividend.getNumberList().get(LEFT_OF_COMMA).add(dividendAsList.getNumberList().get(LEFT_OF_COMMA).get(++i));
        }
        int tempResult = new Subtraction(tempDividend, dividendAsList).getResult().getNumberList().get(LEFT_OF_COMMA).get(0);

        // if
        resultLeftOfComma.add(tempResult);
        // else
        resultRightOfComma.add(tempResult);




    }
    public void calcOverflow(){

    }

    @Override
    public int setOverflow(int number) {
        return 0;
    }
}
