package calculator.test;

import calculator.Decimal;
import calculator.operations.Addition;
import calculator.operations.Subtraction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubtractionTest {

    @Test
    void testConstructorVarArgs(){
        Decimal number1 = new Decimal("2");
        Decimal number2 = new Decimal("4");
        Decimal number3 = new Decimal("5");
        Decimal number4 = new Decimal("7");
        Decimal ergebnis = new Subtraction(number1, number2, number3, number4).getResult();

        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(4, ergebnis.getNumberList().get(0).get(1));
        assertEquals(false, ergebnis.isNumberPositive());
    }

    @Test
    void testSubstractionFromBiggerInt() {
        Decimal decimal1 = new Decimal("2,0");
        Decimal decimal2 = new Decimal("1,0");
        Decimal ergebnis = new Decimal(new Subtraction().operate(decimal1, decimal2));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testSubstractionIntFromSmallerInt() {
        Subtraction subtraction = new Subtraction();
        Decimal decimal1 = new Decimal("1,0");
        Decimal decimal2 = new Decimal("2,0");
        Decimal ergebnis = subtraction.operate(decimal1, decimal2);
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
        assertEquals(false, ergebnis.isNumberPositive());
        assertEquals("-1,0", ergebnis.toString());
    }

    @Test
    void testSubstractionFromBiggerDecimals() {
        Decimal decimal1 = new Decimal("0,6");
        Decimal decimal2 = new Decimal("0,4");
        Decimal ergebnis = new Decimal(new Subtraction().operate(decimal1, decimal2));
        assertEquals(0, ergebnis.getNumberList().get(0).get(0));
        assertEquals(2, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testSubstractionFromBiggerIntOverflow() {
        Decimal decimal1 = new Decimal("10,0");
        Decimal decimal2 = new Decimal("1,0");
        Decimal ergebnis = new Decimal(new Subtraction().operate(decimal1, decimal2));
        assertEquals(9, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testSubstractionFromBiggerDecimalOverflow() {
        Decimal decimal1 = new Decimal("10,0");
        Decimal decimal2 = new Decimal("0,1");
        Decimal ergebnis = new Decimal(new Subtraction().operate(decimal1, decimal2));
        assertEquals(9, ergebnis.getNumberList().get(0).get(0));
        assertEquals(9, ergebnis.getNumberList().get(1).get(0));
    }

    @Test
    void testSubtractionPositiveIntNegativeInt(){
        Decimal decimal1 = new Decimal("1");
        Decimal decimal2 = new Decimal("-1");
        Decimal ergebnis = new Decimal(new Subtraction().operate(decimal1, decimal2));
        assertEquals(2, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
        assertEquals(true, ergebnis.isNumberPositive());
    }

    @Test
    void testSubtractionNegativeIntPositiveInt(){
        Decimal decimal1 = new Decimal("-1");
        Decimal decimal2 = new Decimal("1");
        Decimal ergebnis = new Decimal(new Subtraction().operate(decimal1, decimal2));
        assertEquals(2, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
        assertEquals(false, ergebnis.isNumberPositive());
    }

    @Test
    void testSubtractionNegativIntNegativInt(){
        Decimal decimal1 = new Decimal("-1");
        Decimal decimal2 = new Decimal("-1");
        Decimal ergebnis = new Decimal(new Subtraction().operate(decimal1, decimal2));
        assertEquals(0, ergebnis.getNumberList().get(0).get(0));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
        assertEquals(true, ergebnis.isNumberPositive());
    }

    @Test
    void testSubtractionPositiveDecNegativeInt(){
        Decimal decimal1 = new Decimal("0,5");
        Decimal decimal2 = new Decimal("-1");
        Decimal ergebnis = new Decimal(new Subtraction().operate(decimal1, decimal2));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(5, ergebnis.getNumberList().get(1).get(0));
        assertEquals(true, ergebnis.isNumberPositive());
    }

    @Test
    void testSubtractionNegativeDecPositiveDec(){
        Decimal decimal1 = new Decimal("-,5");
        Decimal decimal2 = new Decimal("1");
        Decimal ergebnis = new Decimal(new Subtraction().operate(decimal1, decimal2));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(5, ergebnis.getNumberList().get(1).get(0));
        assertEquals(false, ergebnis.isNumberPositive());
    }

    @Test
    void testSubtractionPositiveMixedBigNegativeMixed(){
        Decimal decimal1 = new Decimal("5,7");
        Decimal decimal2 = new Decimal("-12,31");
        Decimal ergebnis = new Decimal(new Subtraction().operate(decimal1, decimal2));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(8, ergebnis.getNumberList().get(0).get(1));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
        assertEquals(1, ergebnis.getNumberList().get(1).get(1));
        assertEquals(true, ergebnis.isNumberPositive());
    }

    @Test
    void testSubtractionNegativeMixedBigPositiveMixed(){
        Decimal decimal1 = new Decimal("-5,7");
        Decimal decimal2 = new Decimal("12,31");
        Decimal ergebnis = new Decimal(new Subtraction().operate(decimal1, decimal2));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(8, ergebnis.getNumberList().get(0).get(1));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
        assertEquals(1, ergebnis.getNumberList().get(1).get(1));
        assertEquals(false, ergebnis.isNumberPositive());
    }

    @Test
    void testSubtractionBigNegativeMixedPositiveMixed(){
        Decimal decimal1 = new Decimal("-12,31");
        Decimal decimal2 = new Decimal("5,7");
        Decimal ergebnis = new Decimal(new Subtraction().operate(decimal1, decimal2));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(8, ergebnis.getNumberList().get(0).get(1));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
        assertEquals(1, ergebnis.getNumberList().get(1).get(1));
        assertEquals(false, ergebnis.isNumberPositive());
    }

    @Test
    void testSubtractionBigPositiveMixedNegativeMixed(){
        Decimal decimal1 = new Decimal("12,31");
        Decimal decimal2 = new Decimal("-5,7");
        Decimal ergebnis = new Decimal(new Subtraction().operate(decimal1, decimal2));
        assertEquals(1, ergebnis.getNumberList().get(0).get(0));
        assertEquals(8, ergebnis.getNumberList().get(0).get(1));
        assertEquals(0, ergebnis.getNumberList().get(1).get(0));
        assertEquals(1, ergebnis.getNumberList().get(1).get(1));
        assertEquals(true, ergebnis.isNumberPositive());
    }




}
