package calculator.test;

import calculator.Decimal;
import calculator.operations.Division;
import calculator.operations.Exponentiation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExponentiationTest {

    @Test
    void testExponentiation0sqt() {
        Decimal decimal1 = new Decimal("0");
        Decimal decimal2 = new Decimal("2");
        Decimal ergebnis = new Exponentiation(decimal1, decimal2).getResult();
        assertEquals(0, ergebnis.getNumberList().get(0).get(0));
    }

    @Test
    void testExponentiation1sqt() {
        Decimal decimal1 = new Decimal("1");
        Decimal decimal2 = new Decimal("2");
        Decimal ergebnis = new Exponentiation(decimal1, decimal2).getResult();
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
    }

    @Test
    void testExponentiation2sqt() {
        Decimal decimal1 = new Decimal("2");
        Decimal decimal2 = new Decimal("2");
        Decimal ergebnis = new Exponentiation(decimal1, decimal2).getResult();
        assertEquals(4, ergebnis.getNumberList().get(0).get(0));
    }

    @Test
    void testExponentiation3sq() {
        Decimal decimal1 = new Decimal("3");
        Decimal decimal2 = new Decimal("2");
        Decimal ergebnis = new Exponentiation(decimal1, decimal2).getResult();
        assertEquals(9, ergebnis.getNumberList().get(0).get(0));
    }

    @Test
    void testExponentiation4sqt() {
        Decimal decimal1 = new Decimal("4");
        Decimal decimal2 = new Decimal("2");
        Decimal ergebnis = new Exponentiation(decimal1, decimal2).getResult();
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(6, ergebnis.getNumberList().get(0).get(1));
    }

    @Test
    void testExponentiation5sqt() {
        Decimal decimal1 = new Decimal("5");
        Decimal decimal2 = new Decimal("2");
        Decimal ergebnis = new Exponentiation(decimal1, decimal2).getResult();
        assertEquals(2, ergebnis.getNumberList().get(0).get(0));
        assertEquals(5, ergebnis.getNumberList().get(0).get(1));
    }

    @Test
    void testExponentiation6sqt() {
        Decimal decimal1 = new Decimal("6");
        Decimal decimal2 = new Decimal("2");
        Decimal ergebnis = new Exponentiation(decimal1, decimal2).getResult();
        assertEquals(3, ergebnis.getNumberList().get(0).get(0));
        assertEquals(6, ergebnis.getNumberList().get(0).get(1));
    }

    @Test
    void testExponentiation7sqt() {
        Decimal decimal1 = new Decimal("7");
        Decimal decimal2 = new Decimal("2");
        Decimal ergebnis = new Exponentiation(decimal1, decimal2).getResult();
        assertEquals(4, ergebnis.getNumberList().get(0).get(0));
        assertEquals(9, ergebnis.getNumberList().get(0).get(1));
    }

    @Test
    void testExponentiation8sqt() {
        Decimal decimal1 = new Decimal("8");
        Decimal decimal2 = new Decimal("2");
        Decimal ergebnis = new Exponentiation(decimal1, decimal2).getResult();
        assertEquals(6, ergebnis.getNumberList().get(0).get(0));
        assertEquals(4, ergebnis.getNumberList().get(0).get(1));
    }

    @Test
    void testExponentiation9sqt() {
        Decimal decimal1 = new Decimal("9");
        Decimal decimal2 = new Decimal("2");
        Decimal ergebnis = new Exponentiation(decimal1, decimal2).getResult();
        assertEquals(8, ergebnis.getNumberList().get(0).get(0));
        assertEquals(1, ergebnis.getNumberList().get(0).get(1));
    }

    @Test
    void testExponentiation10sqt() {
        Decimal decimal1 = new Decimal("10");
        Decimal decimal2 = new Decimal("2");
        Decimal ergebnis = new Exponentiation(decimal1, decimal2).getResult();
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(0).get(1));
        assertEquals(0, ergebnis.getNumberList().get(0).get(2));
    }

    @Test
    void testExponentiation2cubed() {
        Decimal decimal1 = new Decimal("2");
        Decimal decimal2 = new Decimal("3");
        Decimal ergebnis = new Exponentiation(decimal1, decimal2).getResult();
        assertEquals(8, ergebnis.getNumberList().get(0).get(0));
    }

    @Test
    void testExponentiation3cubed() {
        Decimal decimal1 = new Decimal("3");
        Decimal decimal2 = new Decimal("3");
        Decimal ergebnis = new Exponentiation(decimal1, decimal2).getResult();
        assertEquals(2, ergebnis.getNumberList().get(0).get(0));
        assertEquals(7, ergebnis.getNumberList().get(0).get(1));
    }

    @Test
    void testExponentiation4cubed() {
        Decimal decimal1 = new Decimal("4");
        Decimal decimal2 = new Decimal("3");
        Decimal ergebnis = new Exponentiation(decimal1, decimal2).getResult();
        assertEquals(6, ergebnis.getNumberList().get(0).get(0));
        assertEquals(4, ergebnis.getNumberList().get(0).get(1));
    }
}
