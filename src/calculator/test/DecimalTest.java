package calculator.test;

import calculator.Decimal;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DecimalTest {
    @Test
    void testConstructor() {
        Decimal decimal = new Decimal();
        assertEquals(2, decimal.getNumberList().size());
    }

    @Test
    void testConstructorWithOneInput() {
        Decimal decimal = new Decimal("1,123");
        assertEquals(1, decimal.getNumberList().get(0).size());
        assertEquals(1, decimal.getNumberList().get(0).get(0));
        assertEquals(3, decimal.getNumberList().get(1).size());
        assertEquals(1, decimal.getNumberList().get(1).get(0));
        assertEquals(2, decimal.getNumberList().get(1).get(1));
        assertEquals(3, decimal.getNumberList().get(1).get(2));
    }

    @Test
    void testConstructorWithOneIntInput() {
        Decimal decimal = new Decimal("154");
        assertEquals(3, decimal.getNumberList().get(0).size());
        assertEquals(1, decimal.getNumberList().get(0).get(0));
        assertEquals(5, decimal.getNumberList().get(0).get(1));
        assertEquals(4, decimal.getNumberList().get(0).get(2));
        assertEquals(0, decimal.getNumberList().get(1).size());

    }

    @Test
    void testConstructorWithOneDecInputWOut0() {
        Decimal decimal = new Decimal(",154");
        assertEquals(1, decimal.getNumberList().get(0).size());
        assertEquals(0, decimal.getNumberList().get(0).get(0));
        assertEquals(3, decimal.getNumberList().get(1).size());
        assertEquals(1, decimal.getNumberList().get(1).get(0));
        assertEquals(5, decimal.getNumberList().get(1).get(1));
        assertEquals(4, decimal.getNumberList().get(1).get(2));
    }

    @Test
    void testConstructorWithTwoLists() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(0);
        List<Integer> list2 = new ArrayList<>();
        list2.add(0);
        Decimal decimal = new Decimal(list1, list2);
        assertEquals(1, decimal.getNumberList().get(0).size());
        assertEquals(1, decimal.getNumberList().get(1).size());
        assertEquals(0, decimal.getNumberList().get(0).get(0));
        assertEquals(0, decimal.getNumberList().get(1).get(0));
    }

    @Test
    void testPositivity() {
        Decimal decimal = new Decimal("12");
        assertEquals(true, decimal.isNumberPositive());
    }

    @Test
    void testNegativity() {
        Decimal decimal = new Decimal("-12");
        assertEquals(false, decimal.isNumberPositive());
    }

}

