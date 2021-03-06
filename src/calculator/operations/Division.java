package calculator.operations;

import calculator.Decimal;

import calculator.Location;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class Division extends DivisionHelper implements IOperation {
    Logger logger = LogManager.getLogger(Division.class);

    public Division() {
    }

    public Division(Decimal number1, Decimal number2) {
        result = operate(number1, number2);
    }

    @Override
    void executeOperation(Decimal number1, Decimal number2) {
        if (number1.isNumberPositive() && number2.isNumberPositive()) {
            logger.info("number1 and number2 positive");
            result = divide(number1, number2);
            result.setIsPositive(true);
        } else if (!number1.isNumberPositive() && !number2.isNumberPositive()) {
            logger.info("number1 and number2 negative");
            result = divide(number1, number2);
            result.setIsPositive(true);
        } else {
            logger.info("one number positive. the other number negative");
            result = divide(number1, number2);
            result.setIsPositive(false);
        }
    }

    public Decimal divide(Decimal dividend, Decimal divisor) {
        Decimal tempDividend = new Decimal();
        int numbersWritten = 0;
        int dividendInitialLength;

        Decimal divisorAsList = getDecimalAsList(divisor);
        divisorAsList = trimDecimal(divisorAsList);
        Decimal dividendAsList = getDecimalAsList(dividend);
        dividendAsList = trimDecimal(dividendAsList);
        adjustForComma(divisor, dividend, divisorAsList, dividendAsList);
        dividendInitialLength = dividendAsList.getNumberList().get(Location.LEFT.getIndex()).size();

        initializeTempDividend(tempDividend, divisorAsList, dividendAsList);
        divisionSteps(tempDividend, numbersWritten, dividendInitialLength, divisorAsList, dividendAsList);
        result = new Decimal(resultLeftOfComma, resultRightOfComma);
        return result;
    }


    @Override
    public Decimal operate(Decimal number1, Decimal number2) {
        executeOperation(number1, number2);
        result = trimDecimal(result);
        return result;
    }
}
