package calculator.test;

import calculator.operations.Addition;
import calculator.Decimal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdditionTest {

    @Test
    void testConstructorVarArgs(){
        Decimal number1 = new Decimal("2");
        Decimal number2 = new Decimal("4");
        Decimal number3 = new Decimal("5");
        Decimal number4 = new Decimal("7");
        Decimal ergebnis = new Addition(number1, number2, number3, number4).getResult();

        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(8, ergebnis.getNumberList().get(0).get(1));
    }

    @Test
    void testAdditionInt() {
        Decimal decimal1 = new Decimal("1,0");
        Decimal decimal2 = new Decimal("1,0");
        Decimal ergebnis = new Decimal(new Addition().operate(decimal1, decimal2));
        assertEquals(2, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));

        ergebnis = new Decimal(new Addition().operate(decimal2, decimal1));
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

        ergebnis = new Decimal(new Addition().operate(decimal2, decimal1));
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

        ergebnis = new Decimal(new Addition().operate(decimal2, decimal1));
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

        ergebnis = new Decimal(new Addition().operate(decimal2, decimal1));
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

        ergebnis = new Decimal(new Addition().operate(decimal2, decimal1));
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

        ergebnis = new Decimal(new Addition().operate(decimal2, decimal1));
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

        ergebnis = new Decimal(new Addition().operate(decimal2, decimal1));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(1, ergebnis.getNumberList().get(0).get(1));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testAdditionNegativeIntNegativeInt(){
        Decimal decimal1 = new Decimal("-1");
        Decimal decimal2 = new Decimal("-1");
        Decimal ergebnis = new Decimal(new Addition().operate(decimal1, decimal2));
        assertEquals(2, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
        assertEquals(false, ergebnis.isNumberPositive());

        ergebnis = new Decimal(new Addition().operate(decimal2, decimal1));
        assertEquals(2, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
        assertEquals(false, ergebnis.isNumberPositive());
    }

    @Test
    void testAdditionNegativeDecNegativeInt(){
        Decimal decimal1 = new Decimal("-0,5");
        Decimal decimal2 = new Decimal("-1");
        Decimal ergebnis = new Decimal(new Addition().operate(decimal1, decimal2));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(5, ergebnis.getNumberList().get(1).get(0));
        assertEquals(false, ergebnis.isNumberPositive());

        ergebnis = new Decimal(new Addition().operate(decimal2, decimal1));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(5, ergebnis.getNumberList().get(1).get(0));
        assertEquals(false, ergebnis.isNumberPositive());
    }

    @Test
    void testAdditionNegativeDecNegativeDec(){
        Decimal decimal1 = new Decimal("-0,5");
        Decimal decimal2 = new Decimal("-0,5");
        Decimal ergebnis = new Decimal(new Addition().operate(decimal1, decimal2));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
        assertEquals(false, ergebnis.isNumberPositive());

        ergebnis = new Decimal(new Addition().operate(decimal2, decimal1));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
        assertEquals(false, ergebnis.isNumberPositive());
    }

    @Test
    void testAdditionNegativeDecPositiveInt(){
        Decimal decimal1 = new Decimal("-0,5");
        Decimal decimal2 = new Decimal("3");
        Decimal ergebnis = new Addition().operate(decimal1, decimal2);
        assertEquals(2, ergebnis.getNumberList().get(0).get(0));
        assertEquals(5, ergebnis.getNumberList().get(1).get(0));
        assertEquals(true, ergebnis.isNumberPositive());


        decimal1 = new Decimal("-0,5");
        decimal2 = new Decimal("3");
        ergebnis = new Addition().operate(decimal2, decimal1);
        assertEquals(2, ergebnis.getNumberList().get(0).get(0));
        assertEquals(5, ergebnis.getNumberList().get(1).get(0));
        assertEquals(true, ergebnis.isNumberPositive());
    }

    @Test
    void testAdditionPositiveMixedNegativeMixed(){
        Decimal decimal1 = new Decimal("10,15");
        Decimal decimal2 = new Decimal("-3,7");
        Decimal ergebnis = new Addition().operate(decimal1, decimal2);
        assertEquals(6, ergebnis.getNumberList().get(0).get(0));
        assertEquals(4, ergebnis.getNumberList().get(1).get(0));
        assertEquals(5, ergebnis.getNumberList().get(1).get(1));
        assertEquals(true, ergebnis.isNumberPositive());


        decimal1 = new Decimal("10,15");
        decimal2 = new Decimal("-3,7");
        ergebnis = new Addition().operate(decimal2, decimal1);
        assertEquals(6, ergebnis.getNumberList().get(0).get(0));
        assertEquals(4, ergebnis.getNumberList().get(1).get(0));
        assertEquals(5, ergebnis.getNumberList().get(1).get(1));
        assertEquals(true, ergebnis.isNumberPositive());
    }


}
