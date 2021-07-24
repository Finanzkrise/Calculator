package calculator.operations;

import calculator.Decimal;
import calculator.ListLocation;

public class Division extends Operation implements ListLocation {

    public Division() {
    }

    @Override
    public int setOverflow(int number) {
        return 0;
    }

    @Override
    Decimal operate(Decimal number1, Decimal number2) {
        return null;
    }

    @Override
    void executeOperation(Decimal number1, Decimal number2) {

    }
}
