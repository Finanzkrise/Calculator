package calculator.operations;

import calculator.Decimal;
import calculator.ListLocation;

public class Exponentiation extends  Operation implements ListLocation {

    public Exponentiation(Decimal number1, Decimal number2) {
        result = operate(number1, number2);
    }

    @Override
    Decimal operate(Decimal number1, Decimal number2) {
        executeOperation(number1, number2);
        result = trimDecimal(result);
        return result;
    }

    @Override
    void executeOperation(Decimal number1, Decimal number2) {
        result = exponantiate(number1,number2);
    }

    private Decimal exponantiate(Decimal number1, Decimal number2) {
        Decimal result;
        Decimal root = new Decimal("1");

        // adjust for right of comma - * 10^i
        for (int i = 0; number2.getNumberList().get(RIGHT_OF_COMMA).size() > i; i++) {
            number2 = new Multiplication(number2, new Decimal("10")).getResult();
            root = new Multiplication(root, new Decimal("10")).getResult();
        }

        if (number2.toString().equals("0,")) {
            result = new Division(number1, number1).getResult();
        }
        else if (number2.toString().equals("1,")) {
            result = number1;
        }
        else{
            while (!isDecimalHigherThanDecimal(new Decimal("2"), number2)) {
                number2 = new Subtraction().operate(number2, new Decimal("1"));
                number1 = new Multiplication().operate(number1, number1);
                System.out.println("tempresult: " + number1);
            }
            result = new RootExtraction(number1, root).getResult();
        }
        if (!number1.isNumberPositive()) {
            result.setIsPositive(false);
        }
        return result;
    }
}
