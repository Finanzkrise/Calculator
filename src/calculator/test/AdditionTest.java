package calculator.test;

import calculator.Addition;
import calculator.Decimal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdditionTest {
    @Test
    void testAdditionInt() {
        Decimal decimal1 = new Decimal("1,0");
        Decimal decimal2 = new Decimal("1,0");
        Decimal ergebnis = new Decimal(new Addition().operate(decimal1, decimal2));
        assertEquals(2, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testAdditionDecimalsSamePosition() {
        Decimal decimal1 = new Decimal("0,1");
        Decimal decimal2 = new Decimal("0,8");
        Decimal ergebnis = new Decimal(new Addition().operate(decimal1, decimal2));
        assertEquals(0, ergebnis.getNumberList().get(0).get(0));
        assertEquals(9, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testAdditionDecimalsNotSamePosition() {
        Decimal decimal1 = new Decimal("0,1");
        Decimal decimal2 = new Decimal("0,08");
        Decimal ergebnis = new Decimal(new Addition().operate(decimal1, decimal2));
        assertEquals(0, ergebnis.getNumberList().get(0).get(0));
        assertEquals(1, ergebnis.getNumberList().get(1).get(0));
        assertEquals(8, ergebnis.getNumberList().get(1).get(1));
    }

    @Test
    void testAdditionMixed() {
        Decimal decimal1 = new Decimal("11,0");
        Decimal decimal2 = new Decimal("0,9");
        Decimal ergebnis = new Decimal(new Addition().operate(decimal1, decimal2));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(1, ergebnis.getNumberList().get(0).get(1));
        assertEquals(9, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testAdditionOverFlowInt() {
        Decimal decimal1 = new Decimal("9,0");
        Decimal decimal2 = new Decimal("1,0");
        Decimal ergebnis = new Decimal(new Addition().operate(decimal1, decimal2));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(0).get(1));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testAdditionOverFlowDecimals() {
        Decimal decimal1 = new Decimal("0,9");
        Decimal decimal2 = new Decimal("0,1");
        Decimal ergebnis = new Decimal(new Addition().operate(decimal1, decimal2));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testAdditionOverFlowMixed() {
        Decimal decimal1 = new Decimal("9,9");
        Decimal decimal2 = new Decimal("1,1");
        Decimal ergebnis = new Decimal(new Addition().operate(decimal1, decimal2));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(1, ergebnis.getNumberList().get(0).get(1));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
    }


}
