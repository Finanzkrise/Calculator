package calculator.operations;

import calculator.Decimal;
import calculator.IListLocation;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class Modulo extends DivisionHelper implements  IOperation {
    Logger logger = LogManager.getLogger(Modulo.class);

    public Modulo(Decimal number1, Decimal number2) {
        result = operate(number1, number2);
    }

    @Override
    public Decimal operate(Decimal number1, Decimal number2) {
        executeOperation(number1, number2);
        result = trimDecimal(result);
        return result;
    }

    @Override
    void executeOperation(Decimal number1, Decimal number2) {
        result = modulo(number1, number2);
    }

    public Decimal modulo(Decimal dividend, Decimal divisor) {
        Decimal tempDividend = new Decimal();
        int numbersWritten = 0;
        int dividendInitialLength;

        if (isDecimalHigherThanDecimal(divisor, dividend)) {
            result = dividend;
        }
        else {
            Decimal divisorAsList = getDecimalAsList(divisor);
            divisorAsList = trimDecimal(divisorAsList);
            Decimal dividendAsList = getDecimalAsList(dividend);
            dividendAsList = trimDecimal(dividendAsList);
            adjustForComma(divisor, dividend, divisorAsList, dividendAsList);
            dividendInitialLength = dividendAsList.getNumberList().get(LEFT_OF_COMMA).size();

            initializeTempDividend(tempDividend, divisorAsList, dividendAsList);
            divisionSteps(tempDividend, numbersWritten, dividendInitialLength, divisorAsList, dividendAsList);
        }
        return result;
    }

    protected void divisionSteps(Decimal tempDividend, int numbersWritten, int dividendInitialLength, Decimal divisorAsList, Decimal dividendAsList) {

        while (!dividendAsList.getNumberList().get(LEFT_OF_COMMA).isEmpty()) {
            moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
            tempDividend = trimDecimal(tempDividend);
            logger.info("tempDividend before division: " + tempDividend);

            while (!isDecimalHigherThanDecimal(divisorAsList, tempDividend)) {
                tempDividend = new Subtraction().operate(tempDividend, divisorAsList);
                tempDividend = trimDecimal(tempDividend);
            }
            logger.info("remainder: " + tempDividend);
            result = tempDividend;
        }
    }


}
