package calculator.test;

import calculator.Decimal;
import calculator.operations.Addition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationTest {
    @Test
    void testisNumberOneHigherThanNumberTwoIntInt() {
        Decimal decimal1 = new Decimal("2,0");
        Decimal decimal2 = new Decimal("1,0");
        assertEquals(true, new Addition().isDecimalHigherThanDecimal(decimal1, decimal2));
        assertEquals(false, new Addition().isDecimalHigherThanDecimal(decimal2, decimal1));
    }
    @Test
    void testisNumberOneHigherThanNumberTwoIntDec() {
        Decimal decimal1 = new Decimal("2,0");
        Decimal decimal2 = new Decimal("1,9");
        assertEquals(true, new Addition().isDecimalHigherThanDecimal(decimal1, decimal2));
        assertEquals(false, new Addition().isDecimalHigherThanDecimal(decimal2, decimal1));
    }

    @Test
    void testisNumberOneHigherThanNumberTwoDecDec() {
        Decimal decimal1 = new Decimal("0,4");
        Decimal decimal2 = new Decimal("0,39");
        assertEquals(true, new Addition().isDecimalHigherThanDecimal(decimal1, decimal2));
        assertEquals(false, new Addition().isDecimalHigherThanDecimal(decimal2, decimal1));
    }

    @Test
    void testisNumberOneHigherThanNumberTwoMixed() {
        Decimal decimal1 = new Decimal("14,46");
        Decimal decimal2 = new Decimal("9,9");
        assertEquals(true, new Addition().isDecimalHigherThanDecimal(decimal1, decimal2));
        assertEquals(false, new Addition().isDecimalHigherThanDecimal(decimal2, decimal1));
    }
}
