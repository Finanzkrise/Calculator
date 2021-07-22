package calculator.test;

import calculator.Decimal;
import calculator.Multiplication;
import calculator.Subtraction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplicationTest {

    @Test
    void testMultiplicationIntInt() {
        Decimal decimal1 = new Decimal("2,0");
        Decimal decimal2 = new Decimal("1,0");
        Multiplication multiplication = new Multiplication(decimal1, decimal2);
        Decimal ergebnis = multiplication.getResult();
        assertEquals(2, ergebnis.getNumberList().get(0).get(ergebnis.getNumberList().get(0).size() - 1));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));

        multiplication = new Multiplication(decimal2, decimal1);
        ergebnis = multiplication.getResult();
        assertEquals(2, ergebnis.getNumberList().get(0).get(ergebnis.getNumberList().get(0).size() - 1));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testMultiplicationIntZero() {
        Decimal decimal1 = new Decimal("2,0");
        Decimal decimal2 = new Decimal("0,0");
        Multiplication multiplication = new Multiplication(decimal1, decimal2);
        Decimal ergebnis = multiplication.getResult();
        assertEquals(0, ergebnis.getNumberList().get(0).get(ergebnis.getNumberList().get(0).size() - 1));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));

        multiplication = new Multiplication(decimal2, decimal1);
        ergebnis = multiplication.getResult();
        assertEquals(0, ergebnis.getNumberList().get(0).get(ergebnis.getNumberList().get(0).size() - 1));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testMultiplicationDecimalDecimal() {
        Decimal decimal1 = new Decimal("0,5");
        Decimal decimal2 = new Decimal("0,8");
        Multiplication multiplication = new Multiplication(decimal1, decimal2);
        Decimal ergebnis = multiplication.getResult();
        assertEquals(0, ergebnis.getNumberList().get(0).get(ergebnis.getNumberList().get(0).size() - 1));
        assertEquals(4, ergebnis.getNumberList().get(1).get(0));

        multiplication = new Multiplication(decimal2, decimal1);
        ergebnis = multiplication.getResult();
        assertEquals(0, ergebnis.getNumberList().get(0).get(ergebnis.getNumberList().get(0).size() - 1));
        assertEquals(4, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testMultiplicationDecimalInt() {
        Decimal decimal1 = new Decimal("0,5");
        Decimal decimal2 = new Decimal("4,0");
        Multiplication multiplication = new Multiplication(decimal1, decimal2);
        Decimal ergebnis = multiplication.getResult();
        assertEquals(2, ergebnis.getNumberList().get(0).get(ergebnis.getNumberList().get(0).size() - 1));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));

        multiplication = new Multiplication(decimal2, decimal1);
        ergebnis = multiplication.getResult();
        assertEquals(2, ergebnis.getNumberList().get(0).get(ergebnis.getNumberList().get(0).size() - 1));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testMultiplicationMixed() {
        Decimal decimal1 = new Decimal("4,2");
        Decimal decimal2 = new Decimal("10,95");
        Multiplication multiplication = new Multiplication(decimal1, decimal2);
        Decimal ergebnis = multiplication.getResult();
        assertEquals(5, ergebnis.getNumberList().get(0).get(ergebnis.getNumberList().get(0).size() - 1));
        assertEquals(4, ergebnis.getNumberList().get(0).get(ergebnis.getNumberList().get(0).size() - 2));
        assertEquals(9, ergebnis.getNumberList().get(1).get(0));
        assertEquals(9, ergebnis.getNumberList().get(1).get(1));

        multiplication = new Multiplication(decimal2, decimal1);
        ergebnis = multiplication.getResult();
        assertEquals(5, ergebnis.getNumberList().get(0).get(ergebnis.getNumberList().get(0).size() - 1));
        assertEquals(4, ergebnis.getNumberList().get(0).get(ergebnis.getNumberList().get(0).size() - 2));
        assertEquals(9, ergebnis.getNumberList().get(1).get(0));
        assertEquals(9, ergebnis.getNumberList().get(1).get(1));
    }

}
