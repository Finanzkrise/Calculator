package calculator.operations;

import calculator.Decimal;
import calculator.ListLocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RootExtraction extends DivisionHelper implements ListLocation {
    Logger logger = LogManager.getLogger(RootExtraction.class);

    public RootExtraction() {
    }

    public RootExtraction(Decimal number1, Decimal number2) {
        result = operate(number1, number2);
    }

    @Override
    void executeOperation(Decimal number1, Decimal number2) {
        result = extractRoot(number1, number2);
    }

    public Decimal extractRoot(Decimal number1, Decimal number2) {
        Decimal result = new Decimal();
        Decimal tempDividend = new Decimal();
        Decimal tempDividendBuffer = new Decimal();
        Decimal tempSubtrahend = new Decimal();
        Decimal index = new Decimal("1");
        int numbersWritten = 0;

        Decimal dividendAsList = getDecimalAsList(number1);
        Decimal divisorAsList = getDecimalAsList(number2);

        initializeTempDividend(tempDividend, dividendAsList, divisorAsList);

        tempSubtrahend = findFirstSubtrahend(tempDividend);
        result.getNumberList().get(LEFT_OF_COMMA).add(tempSubtrahend.getNumberList().get(LEFT_OF_COMMA).get(0));
        tempDividend = new Subtraction(tempDividend, tempSubtrahend).getResult();

        while (!tempDividend.toString().equals("0,0") || !dividendAsList.getNumberList().get(LEFT_OF_COMMA).isEmpty()) {
            index = new Decimal("0");
            while (isDecimalHigherThanDecimal(number2, index)) {
                moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
                index = new Addition(index, new Decimal("1")).getResult();
            }
            moveDigitFromDividendListToTempDividend(tempDividendBuffer, dividendAsList);
            Decimal tempDivisor = findDivisor(tempDividend, tempSubtrahend);
            moveDigitFromDividendListToTempDividend(tempDividend, tempDividendBuffer);

            while (isDecimalHigherThanDecimal(getSubtrahendCandidate(result, tempDivisor), tempDividend)) {
                tempDivisor = new Subtraction(tempDivisor, new Decimal("1")).getResult();
            }
            tempDividend = new Subtraction(tempDividend, getSubtrahendCandidate(result, tempDivisor)).getResult();
            result.getNumberList().get(LEFT_OF_COMMA).add(tempDivisor.getNumberList().get(LEFT_OF_COMMA).get(0));
        }
        return result;
    }

    private Decimal getSubtrahendCandidate(Decimal result, Decimal tempDivisor) {
        return new Multiplication(new Addition(new Multiplication(result, new Decimal("10")).getResult(), tempDivisor).getResult(), tempDivisor).getResult();
    }

    private Decimal findDivisor(Decimal tempDividend, Decimal tempDivisor) {
        Decimal result = new Decimal("1");
        //tempDividend <= result *
        while (!isDecimalHigherThanDecimal(tempDividend, new Multiplication(result, new Multiplication(tempDivisor, new Decimal("2")).getResult()).getResult())) {
            result = new Addition(result, new Decimal(("1"))).getResult();
        }
        return result;
    }

    private Decimal findFirstSubtrahend(Decimal tempDividend) {
        Decimal result = new Decimal("1");
        // tempDividend <= result²
        while (isDecimalHigherThanDecimal(tempDividend, new Multiplication(result, result).getResult())) {
            result = new Addition(result, new Decimal(("1"))).getResult();
        }
        return result;
    }

    protected void initializeTempDividend(Decimal tempDividend, Decimal dividendAsList, Decimal divisorAsList) {
        moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
        adjustTempDividend(tempDividend, dividendAsList, divisorAsList);
    }

    private void adjustTempDividend(Decimal tempDividend, Decimal dividendAsList, Decimal i) {

        if (new Modulo(new Decimal(String.valueOf(dividendAsList.getNumberList().get(LEFT_OF_COMMA).size())), i).getResult().toString().equals("0,0")) {
            moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
            adjustTempDividend(tempDividend, dividendAsList, i);
        }
    }
}
