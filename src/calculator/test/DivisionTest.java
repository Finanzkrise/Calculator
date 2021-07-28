package calculator.test;

import calculator.Decimal;
import calculator.operations.Division;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DivisionTest  {

    @Test
    void testDivisionIdentityInt() {
        Decimal decimal1 = new Decimal("3");
        Decimal decimal2 = new Decimal("3");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
    }

    @Test
    void testDivisionIdentityIntBig() {
        Decimal decimal1 = new Decimal("37");
        Decimal decimal2 = new Decimal("37");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
    }

    @Test
    void testDivisionIdentityDec() {
        Decimal decimal1 = new Decimal("0,7");
        Decimal decimal2 = new Decimal("0,7");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
    }

    @Test
    void testDivisionIdentityDecHigh() {
        Decimal decimal1 = new Decimal("0,735");
        Decimal decimal2 = new Decimal("0,735");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
    }

    @Test
    void testDivisionIdentityMixed() {
        Decimal decimal1 = new Decimal("45,123");
        Decimal decimal2 = new Decimal("45,123");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
    }

    @Test
    void testDivisionIntInt() {
        Decimal decimal1 = new Decimal("10");
        Decimal decimal2 = new Decimal("2");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(5, ergebnis.getNumberList().get(0).get(0));
    }

    @Test
    void testDivisionIntIntBig() {
        Decimal decimal1 = new Decimal("2");
        Decimal decimal2 = new Decimal("10");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(0, ergebnis.getNumberList().get(0).get(0));
        assertEquals(2, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testDivisionIntDecBelow10() {
        Decimal decimal1 = new Decimal("4");
        Decimal decimal2 = new Decimal("0,5");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(8, ergebnis.getNumberList().get(0).get(0));
    }

    @Test
    void testDivisionBigIntDec() {
        Decimal decimal1 = new Decimal("10");
        Decimal decimal2 = new Decimal("0,5");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(2, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(0).get(1));
    }

    @Test
    void testDivisionIntDecBig() {
        Decimal decimal1 = new Decimal("8");
        Decimal decimal2 = new Decimal("0,25");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(3, ergebnis.getNumberList().get(0).get(0));
        assertEquals(2, ergebnis.getNumberList().get(0).get(1));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testDivisionNegativityIntNegativPositive() {
        Decimal decimal1 = new Decimal("-3");
        Decimal decimal2 = new Decimal("3");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(false, ergebnis.isNumberPositive());

        ergebnis = new Division().operate(decimal2, decimal1);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(false, ergebnis.isNumberPositive());
    }

    @Test
    void testDivisionNegativityIntNegativeNegative() {
        Decimal decimal1 = new Decimal("-3");
        Decimal decimal2 = new Decimal("-3");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(true, ergebnis.isNumberPositive());

        ergebnis = new Division().operate(decimal2, decimal1);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(true, ergebnis.isNumberPositive());
    }

    @Test
    void testDivisionNegativityDecNegativePositive() {
        Decimal decimal1 = new Decimal("-0,3");
        Decimal decimal2 = new Decimal("0,3");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(false, ergebnis.isNumberPositive());

        ergebnis = new Division().operate(decimal2, decimal1);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(false, ergebnis.isNumberPositive());
    }

    @Test
    void testDivisionNegativityDecNegativeNegative() {
        Decimal decimal1 = new Decimal("-0,4");
        Decimal decimal2 = new Decimal("-0,4");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(true, ergebnis.isNumberPositive());

        ergebnis = new Division().operate(decimal2, decimal1);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(true, ergebnis.isNumberPositive());
    }

    @Test
    void testDivisionNegativityMixedNegativePositive() {
        Decimal decimal1 = new Decimal("-12,43");
        Decimal decimal2 = new Decimal("12,43");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(false, ergebnis.isNumberPositive());

        ergebnis = new Division().operate(decimal2, decimal1);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(false, ergebnis.isNumberPositive());
    }

    @Test
    void testDivisionNegativityMixedNegativeNegative() {
        Decimal decimal1 = new Decimal("-12,43");
        Decimal decimal2 = new Decimal("-12,43");
        Decimal ergebnis = new Division().operate(decimal1, decimal2);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(true, ergebnis.isNumberPositive());

        ergebnis = new Division().operate(decimal2, decimal1);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(true, ergebnis.isNumberPositive());
    }




}
