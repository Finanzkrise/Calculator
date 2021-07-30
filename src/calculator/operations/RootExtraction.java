package calculator.operations;

import calculator.Decimal;
import calculator.IListLocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RootExtraction extends DivisionHelper implements IListLocation, IOperation {
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

    public Decimal extractRoot(Decimal radicand, Decimal degree) {
        Decimal result = new Decimal();
        Decimal tempDividend = new Decimal();
        Decimal tempDividendBuffer = new Decimal();
        Decimal tempSubtrahend = new Decimal();
        Decimal index = new Decimal("1");
        int numbersWritten = 0;

        Decimal dividendAsList = getDecimalAsList(radicand);
        Decimal divisorAsList = getDecimalAsList(degree);

        // erster Schritt
        initializeTempDividend(tempDividend, dividendAsList, divisorAsList);
        tempSubtrahend = findFirstSubtrahend(tempDividend);
        result.getNumberList().get(LEFT_OF_COMMA).add(tempSubtrahend.getNumberList().get(LEFT_OF_COMMA).get(0));
        tempDividend = new Subtraction(tempDividend, new Multiplication(tempSubtrahend, tempSubtrahend).getResult()).getResult();

        // nach dem ersten Schritt
        while (!tempDividend.toString().equals("0,0") || !dividendAsList.getNumberList().get(LEFT_OF_COMMA).isEmpty()) {
            // tempIndex füllen
            index = new Decimal("1");
            while (isDecimalHigherThanDecimal(degree, index)) {
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
        return new Multiplication(new Addition(new Multiplication(result, new Decimal("20")).getResult(), tempDivisor).getResult(), tempDivisor).getResult();
    }

    private Decimal findDivisor(Decimal tempDividend, Decimal tempDivisor) {
        Decimal divisor = new Decimal("0");
        //tempDividend <= result*2 * X,
        tempDividend = trimDecimal(tempDividend);
        while (!isDecimalHigherThanDecimal(new Multiplication(divisor, new Multiplication(tempDivisor, new Decimal("2")).getResult()).getResult(), tempDividend)) {
            divisor.increment();
        }
        if (isDecimalHigherThanDecimal(divisor, new Decimal("9"))) {
            divisor =  new Decimal("9");
        }
        return divisor;
    }

    private Decimal findFirstSubtrahend(Decimal tempDividend) {
        Decimal result = new Decimal("1");
        // tempDividend <= result²
        while (!isDecimalHigherThanDecimal(new Multiplication(result, result).getResult(), tempDividend)) {
            result.increment();
        }
        // fixed for now should be handled by isDecimalHigherThanDecimal() in 83
        result = new Subtraction(result, new Decimal("1")).getResult();
        return result;
    }

    protected void initializeTempDividend(Decimal tempDividend, Decimal dividendAsList, Decimal divisorAsList) {
        moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
        adjustTempDividend(tempDividend, dividendAsList, divisorAsList);
    }

    private void adjustTempDividend(Decimal tempDividend, Decimal dividendAsList, Decimal degree) {
        if (dividendAsList.getNumberList().get(LEFT_OF_COMMA).size() > 0) {
            if (!(new Modulo(new Decimal(String.valueOf(dividendAsList.getNumberList().get(LEFT_OF_COMMA).size())), degree).isEqualTo(new Decimal("0,0")))) {
                moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
                adjustTempDividend(tempDividend, dividendAsList, degree);
            }
        }
    }

    @Override
    public Decimal operate(Decimal number1, Decimal number2) {
        executeOperation(number1, number2);
        result = trimDecimal(result);
        return result;
    }
}
