package calculator.operations;

import calculator.Decimal;
import calculator.ListLocation;
import org.apache.logging.log4j.Logger;

public abstract  class DivisionHelper extends Operation implements ListLocation {
    Logger logger;

    abstract Decimal operate(Decimal number1, Decimal number2);
    abstract void executeOperation(Decimal number1, Decimal number2);

    protected void initializeTempDividend(Decimal tempDividend, Decimal divisorAsList, Decimal dividendAsList) {
        for (int i = 1; divisorAsList.getNumberList().get(LEFT_OF_COMMA).size() > i; i++) {
            moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
        }
        logger.info("tempDividend intitialized as: " + tempDividend);
    }

    protected void divisionSteps(Decimal tempDividend, int numbersWritten, int dividendInitialLength, Decimal divisorAsList, Decimal dividendAsList) {
        int digitOfResult;
        while (!tempDividend.toString().equals("0,0") || !dividendAsList.getNumberList().get(LEFT_OF_COMMA).isEmpty()) {
            digitOfResult = 0;

            moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
            tempDividend = trimDecimal(tempDividend);
            logger.info("tempDividend before division: " + tempDividend);
            // subtract divisor from tempDividend, result ++
            while (!isDecimalHigherThanDecimal(divisorAsList, tempDividend)) {
                tempDividend = new Subtraction().operate(tempDividend, divisorAsList);
                tempDividend = trimDecimal(tempDividend);
                digitOfResult++;
                logger.info("tempDividend after " + digitOfResult + " cycles: " + tempDividend);
            }
            logger.info("remainder: " + tempDividend);
            numbersWritten = writeResult(dividendInitialLength, divisorAsList, digitOfResult, numbersWritten);
        }
    }

    protected void moveDigitFromDividendListToTempDividend(Decimal tempDividend, Decimal dividendAsList) {
        if (dividendAsList.getNumberList().get(LEFT_OF_COMMA).size() > 0) {
            tempDividend.getNumberList().get(LEFT_OF_COMMA).add(dividendAsList.getNumberList().get(LEFT_OF_COMMA).get(0));
            logger.info("'" + dividendAsList.getNumberList().get(LEFT_OF_COMMA).get(0) + "'" + " moved from dividendAsList to tempDividend");
            dividendAsList.getNumberList().get(LEFT_OF_COMMA).remove(0);
        } else {
            tempDividend.getNumberList().get(LEFT_OF_COMMA).add(0);
            logger.info("dividendAsList empty: added 0 to tempDividend");
        }
    }

    protected int writeResult(int dividendInitialLength, Decimal divisorAsList, int tempResult, int numbersWritten) {
        if (numbersWritten <= dividendInitialLength - divisorAsList.getNumberList().get(LEFT_OF_COMMA).size()) {
            resultLeftOfComma.add(tempResult);
            logger.info("Result: " + tempResult + " written to resultLeftOfComma");
        } else {
            resultRightOfComma.add(tempResult);
            logger.info("Result: " + tempResult + " written to resultRightOfComma");
        }
        numbersWritten++;
        logger.info("numbersWritten: " + numbersWritten);
        return numbersWritten;
    }



}
