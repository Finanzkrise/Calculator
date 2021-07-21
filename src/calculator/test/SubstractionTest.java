package calculator.test;

import calculator.Decimal;
import calculator.Substraction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubstractionTest {

    @Test
    void testSubstractionFromBiggerInt(){
        Decimal decimal1 = new Decimal("2,0");
        Decimal decimal2 = new Decimal("1,0");
        Decimal ergebnis = new Decimal(new Substraction().operate(decimal1, decimal2));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testSubstractionIntFromSmallerInt(){
        Substraction substraction = new Substraction();
        Decimal decimal1 = new Decimal("1,0");
        Decimal decimal2 = new Decimal("2,0");
        Decimal ergebnis = substraction.operate(decimal1, decimal2);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
        assertEquals(false, ergebnis.isNumberPositive());
        assertEquals("-1,0", ergebnis.toString());
    }

    @Test
    void testSubstractionFromBiggerDecimals(){
        Decimal decimal1 = new Decimal("0,6");
        Decimal decimal2 = new Decimal("0,4");
        Decimal ergebnis = new Decimal(new Substraction().operate(decimal1, decimal2));
        assertEquals(0, ergebnis.getNumberList().get(0).get(0));
        assertEquals(2, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testSubstractionFromBiggerIntOverflow(){
        Decimal decimal1 = new Decimal("10,0");
        Decimal decimal2 = new Decimal("1,0");
        Decimal ergebnis = new Decimal(new Substraction().operate(decimal1, decimal2));
        assertEquals(9, ergebnis.getNumberList().get(0).get(1));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testSubstractionFromBiggerDecimalOverflow(){
        Decimal decimal1 = new Decimal("10,0");
        Decimal decimal2 = new Decimal("0,1");
        Decimal ergebnis = new Decimal(new Substraction().operate(decimal1, decimal2));
        assertEquals(9, ergebnis.getNumberList().get(0).get(1));
        assertEquals(9, ergebnis.getNumberList().get(1).get(0));
    }
}
