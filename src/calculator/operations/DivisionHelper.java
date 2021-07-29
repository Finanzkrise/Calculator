package calculator.operations;

import calculator.Decimal;
import calculator.ListLocation;

public abstract class DivisionHelper extends Operation implements ListLocation {

    abstract void executeOperation(Decimal number1, Decimal number2);

    protected void initializeTempDividend(Decimal tempDividend, Decimal divisorAsList, Decimal dividendAsList) {
        for (int i = 1; divisorAsList.getNumberList().get(LEFT_OF_COMMA).size() > i; i++) {
            moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
        }
    }

    protected void divisionSteps(Decimal tempDividend, int numbersWritten, int dividendInitialLength, Decimal divisorAsList, Decimal dividendAsList) {
        int digitOfResult;
        while (!tempDividend.toString().equals("0,0") || !dividendAsList.getNumberList().get(LEFT_OF_COMMA).isEmpty()) {
            digitOfResult = 0;

            moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
            tempDividend = trimDecimal(tempDividend);

            // subtract divisor from tempDividend, result ++
            while (!isDecimalHigherThanDecimal(divisorAsList, tempDividend)) {
                tempDividend = new Subtraction().operate(tempDividend, divisorAsList);
                tempDividend = trimDecimal(tempDividend);
                digitOfResult++;
            }
            numbersWritten = writeResult(dividendInitialLength, divisorAsList, digitOfResult, numbersWritten);
        }
    }

    protected void moveDigitFromDividendListToTempDividend(Decimal tempDividend, Decimal dividendAsList) {
        if (dividendAsList.getNumberList().get(LEFT_OF_COMMA).size() > 0) {
            tempDividend.getNumberList().get(LEFT_OF_COMMA).add(dividendAsList.getNumberList().get(LEFT_OF_COMMA).get(0));
            dividendAsList.getNumberList().get(LEFT_OF_COMMA).remove(0);
        } else {
            tempDividend.getNumberList().get(LEFT_OF_COMMA).add(0);
        }
    }

    protected int writeResult(int dividendInitialLength, Decimal divisorAsList, int tempResult, int numbersWritten) {
        if (numbersWritten <= dividendInitialLength - divisorAsList.getNumberList().get(LEFT_OF_COMMA).size()) {
            resultLeftOfComma.add(tempResult);
        } else {
            resultRightOfComma.add(tempResult);
        }
        numbersWritten++;
        return numbersWritten;
    }



}
