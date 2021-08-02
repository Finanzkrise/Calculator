package calculator.operations;

import calculator.Decimal;
import calculator.Location;

public abstract class DivisionHelper extends CalcHelper {

    abstract void executeOperation(Decimal number1, Decimal number2);

    protected void initializeTempDividend(Decimal tempDividend, Decimal divisorAsList, Decimal dividendAsList) {
        for (int i = 1; divisorAsList.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).size() > i; i++) {
            moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
        }
    }

    protected void divisionSteps(Decimal tempDividend, int numbersWritten, int dividendInitialLength, Decimal divisorAsList, Decimal dividendAsList) {
        int digitOfResult;
        while (!tempDividend.toString().equals("0,0") || !dividendAsList.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).isEmpty()) {
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
        if (dividendAsList.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).size() > 0) {
            tempDividend.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).add(dividendAsList.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).get(0));
            dividendAsList.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).remove(0);
        } else {
            tempDividend.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).add(0);
        }
    }

    protected int writeResult(int dividendInitialLength, Decimal divisorAsList, int tempResult, int numbersWritten) {
        if (numbersWritten <= dividendInitialLength - divisorAsList.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).size()) {
            resultLeftOfComma.add(tempResult);
        } else {
            resultRightOfComma.add(tempResult);
        }
        numbersWritten++;
        return numbersWritten;
    }



}
