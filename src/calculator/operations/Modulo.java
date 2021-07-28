package calculator.operations;

import calculator.Decimal;
import calculator.ListLocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Modulo extends Division implements ListLocation {

    public Modulo() {
    }

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

        Decimal divisorAsList = getDecimalAsList(divisor);
        divisorAsList = trimDecimal(divisorAsList);
        Decimal dividendAsList = getDecimalAsList(dividend);
        dividendAsList = trimDecimal(dividendAsList);
        adjustForComma(divisor, dividend, divisorAsList, dividendAsList);
        dividendInitialLength = dividendAsList.getNumberList().get(LEFT_OF_COMMA).size();

        initializeTempDividend(tempDividend, divisorAsList, dividendAsList);
        divisionSteps(tempDividend, numbersWritten, dividendInitialLength, divisorAsList, dividendAsList);
        result = new Decimal(resultLeftOfComma, resultRightOfComma);
        result = new Subtraction(dividend, new Multiplication(new Decimal(false, result.getNumberList().get(LEFT_OF_COMMA)), divisor).getResult()).getResult();
        System.out.println(result);
        return result;

    }


}
