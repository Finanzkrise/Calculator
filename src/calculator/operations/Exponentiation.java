package calculator.operations;

import calculator.Decimal;
import calculator.IListLocation;

public class Exponentiation extends CalcHelper implements  IOperation {

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
        //Decimal zero = new Decimal("0,0");
        while (!isDecimalHigherThanDecimal(new Decimal("2"), number2)) {
            number2 = new Subtraction().operate(number2, new Decimal("1"));
            number1 = new Multiplication().operate(number1, number1);
            System.out.println("tempresult: " + number1);
        }
        return number1;
    }
}
