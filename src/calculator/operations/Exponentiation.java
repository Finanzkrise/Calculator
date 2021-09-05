package calculator.operations;

import calculator.Decimal;
import calculator.Location;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class Exponentiation extends DivisionHelper implements IOperation {
    Logger logger = LogManager.getLogger(Exponentiation.class);

    public Exponentiation(Decimal number1, Decimal number2) {
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
        result = exponantiate(number1, number2);
    }

    private Decimal exponantiate(Decimal number1, Decimal number2) {
        Decimal result = new Decimal(number1);
        Decimal exponent = getDecimalAsList(number2);
        Decimal degree = new Decimal(String.valueOf(number2.getNumberList().get(Location.RIGHT.getIndex()).size() * 10));

        while (!isDecimalHigherThanDecimal(new Decimal("2"), exponent)) {
            exponent = exponent.decrement();
            result = new Multiplication().operate(result, number1);
            System.out.println("tempresult: " + result);
        }
        if (isDecimalHigherThanDecimal(degree, new Decimal("0"))) {
            result = new RootExtraction(result, degree).getResult();
        }
        return result;
    }
}
