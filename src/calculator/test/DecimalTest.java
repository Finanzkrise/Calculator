package calculator.test;

import calculator.Decimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DecimalTest {
    @Test
    void testConstructor(){
        Decimal decimal = new Decimal();
        assertEquals(0, decimal.getNumberList().size());
    }

    @Test
    void testConstructorWithOneInput(){
        Decimal decimal = new Decimal("1,123");
        assertEquals(1, decimal.getNumberList().get(0).size());
        assertEquals(3, decimal.getNumberList().get(1).size());
    }
}
