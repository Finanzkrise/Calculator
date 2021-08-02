package calculator.operations;

import calculator.Decimal;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class Exponentiation extends CalcHelper implements  IOperation {
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
        result = exponantiate(number1,number2);
    }

    private Decimal exponantiate(Decimal number1, Decimal number2) {
        Decimal result = number1;
        while (!isDecimalHigherThanDecimal(new Decimal("2"), number2)) {
            number2 = new Subtraction().operate(number2, new Decimal("1"));
            result = new Multiplication().operate(result, number1);
            System.out.println("tempresult: " + result);
        }
        return result;
    }
}
