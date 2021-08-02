package calculator.operations;

import calculator.Decimal;

import calculator.Location;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class Modulo extends CalcHelper implements IOperation {
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
        result = dividend;
        while (!isDecimalHigherThanDecimal(divisor, result)) {
            result = new Subtraction(result, divisor).getResult();
        }
        return result;
    }

}
