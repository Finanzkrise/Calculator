package calculator.operations;

import calculator.Decimal;

public interface IOperation {

    Decimal operate(Decimal number1, Decimal number2);
}
