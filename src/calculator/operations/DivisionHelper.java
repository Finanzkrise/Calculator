package calculator.operations;

import calculator.Decimal;
import calculator.Location;

public abstract class DivisionHelper extends CalcHelper {

    abstract void executeOperation(Decimal number1, Decimal number2);

    protected void initializeTempDividend(Decimal tempDividend, Decimal divisorAsList, Decimal dividendAsList) {
        for (int i = 1; divisorAsList.getNumberList().get(Location.LEFT.getIndex()).size() > i; i++) {
            moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
        }
    }

    protected void divisionSteps(Decimal tempDividend, int numbersWritten, int dividendInitialLength, Decimal divisorAsList, Decimal dividendAsList) {
        int digitOfResult;
        while (!tempDividend.toString().equals("0,0") || !dividendAsList.getNumberList().get(Location.LEFT.getIndex()).isEmpty()) {
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

    protected Decimal getDecimalAsList(Decimal number) {
        Decimal divisorAsList = new Decimal();
        for (int i = 0; number.getNumberList().get(Location.LEFT.getIndex()).size() > i; i++) {
            divisorAsList.getNumberList().get(Location.LEFT.getIndex()).add(number.getNumberList().get(Location.LEFT.getIndex()).get(i));
        }
        for (int i = 0; number.getNumberList().get(Location.RIGHT.getIndex()).size() > i; i++) {
            divisorAsList.getNumberList().get(Location.LEFT.getIndex()).add(number.getNumberList().get(Location.RIGHT.getIndex()).get(i));
        }
        return divisorAsList;
    }

    protected void moveDigitFromDividendListToTempDividend(Decimal tempDividend, Decimal dividendAsList) {
        if (dividendAsList.getNumberList().get(Location.LEFT.getIndex()).size() > 0) {
            tempDividend.getNumberList().get(Location.LEFT.getIndex()).add(dividendAsList.getNumberList().get(Location.LEFT.getIndex()).get(0));
            dividendAsList.getNumberList().get(Location.LEFT.getIndex()).remove(0);
        } else {
            tempDividend.getNumberList().get(Location.LEFT.getIndex()).add(0);
        }
    }

    protected int writeResult(int dividendInitialLength, Decimal divisorAsList, int tempResult, int numbersWritten) {
        if (numbersWritten <= dividendInitialLength - divisorAsList.getNumberList().get(Location.LEFT.getIndex()).size()) {
            resultLeftOfComma.add(tempResult);
        } else {
            resultRightOfComma.add(tempResult);
        }
        numbersWritten++;
        return numbersWritten;
    }



}
