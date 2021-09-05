package calculator.operations;

import calculator.Decimal;
import calculator.Location;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RootExtraction extends DivisionHelper implements IOperation {
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
        Decimal tempSubtrahend;
        Decimal index;

        Decimal radicand = getDecimalAsList(number1);
        Decimal degree = getDecimalAsList(number2);

        // erster Schritt
        initializeTempDividend(tempDividend, radicand, degree);
        tempSubtrahend = findFirstSubtrahend(tempDividend, degree);
        result.getNumberList().get(Location.LEFT.getIndex()).add(tempSubtrahend.getNumberList().get(Location.LEFT.getIndex()).get(0));
        tempDividend = new Subtraction(tempDividend, new Exponentiation(tempSubtrahend, degree).getResult()).getResult();

        // nach dem ersten Schritt
        while (!tempDividend.toString().equals("0,0") || !radicand.getNumberList().get(Location.LEFT.getIndex()).isEmpty()) {
            // tempIndex füllen
            index = new Decimal("1");
            while (isDecimalHigherThanDecimal(number2, index)) {
                moveDigitFromDividendListToTempDividend(tempDividend, radicand);
                index = index.increment();
            }
            moveDigitFromDividendListToTempDividend(tempDividendBuffer, radicand);

            Decimal tempDivisor = findDivisor(tempDividend, tempSubtrahend);
            moveDigitFromDividendListToTempDividend(tempDividend, tempDividendBuffer);


            while (isDecimalHigherThanDecimal(getSubtrahendCandidate(result, tempDivisor), tempDividend)) {
                tempDivisor = new Subtraction(tempDivisor, new Decimal("1")).getResult();
            }
            tempDividend = new Subtraction(tempDividend, getSubtrahendCandidate(result, tempDivisor)).getResult();
            result.getNumberList().get(Location.LEFT.getIndex()).add(tempDivisor.getNumberList().get(Location.LEFT.getIndex()).get(0));
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
        while (isDecimalHigherThanDecimal(tempDividend, new Multiplication(divisor, new Multiplication(tempDivisor, new Decimal("2")).getResult()).getResult())) {
            divisor = divisor.increment();
        }
        if (isDecimalHigherThanDecimal(divisor, new Decimal("9"))) {
            divisor =  new Decimal("9");
        }
        return divisor;
    }

    private Decimal findFirstSubtrahend(Decimal tempDividend, Decimal degree) {
        Decimal result = new Decimal("0");
        Decimal power;
        Decimal index;

        // tempDividend <= result^degree
        do {
            result = result.increment();
            power = result;
            index = new Decimal("1");
            while (isDecimalHigherThanDecimal(degree, index)) {
                power = new Multiplication(power, result).getResult();
                index = index.increment();
            }
        } while (isDecimalHigherThanDecimal(tempDividend, power));

        return result;
    }

    protected void initializeTempDividend(Decimal tempDividend, Decimal dividendAsList, Decimal divisorAsList) {
        moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
        adjustTempDividend(tempDividend, dividendAsList, divisorAsList);
    }

    private void adjustTempDividend(Decimal tempDividend, Decimal dividendAsList, Decimal degree) {
        if (dividendAsList.getNumberList().get(Location.LEFT.getIndex()).size() > 0) {
            // dividendAsListSize % degree == 0
            while (!(new Modulo(new Decimal(String.valueOf(dividendAsList.getNumberList().get(Location.LEFT.getIndex()).size())), degree).isEqualTo(new Decimal("0,0")))) {
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
