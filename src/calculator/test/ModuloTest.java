package calculator.test;
import calculator.Decimal;
import calculator.ListLocation;
import calculator.operations.Modulo;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

public class ModuloTest implements ListLocation {

    @Test
    void moduloTestIntInt(){
        Decimal number1 = new Decimal("10");
        Decimal number2 = new Decimal("2");
        Decimal result = new Modulo(number1, number2).getResult();

        assertEquals(0, result.getNumberList().get(LEFT_OF_COMMA).get(0));
        assertEquals(0, result.getNumberList().get(RIGHT_OF_COMMA).get(0));
    }

    @Test
    void moduloTestIntIntRemainder(){
        Decimal number1 = new Decimal("10");
        Decimal number2 = new Decimal("4");
        Decimal result = new Modulo(number1, number2).getResult();

        assertEquals(2, result.getNumberList().get(LEFT_OF_COMMA).get(result.getNumberList().get(LEFT_OF_COMMA).size()-1));
        assertEquals(0, result.getNumberList().get(RIGHT_OF_COMMA).get(0));
    }

    @Test
    void moduloTestIntDec(){
        Decimal number1 = new Decimal("5,0");
        Decimal number2 = new Decimal("2,5");
        Decimal result = new Modulo(number1, number2).getResult();

        assertEquals(0, result.getNumberList().get(LEFT_OF_COMMA).get(0));
        assertEquals(0, result.getNumberList().get(RIGHT_OF_COMMA).get(0));
    }

    @Test
    void moduloTestIntIntZero(){
        Decimal number1 = new Decimal("5,0");
        Decimal number2 = new Decimal("100");
        Decimal result = new Modulo(number1, number2).getResult();

        assertEquals(5, result.getNumberList().get(LEFT_OF_COMMA).get(0));
        assertEquals(0, result.getNumberList().get(RIGHT_OF_COMMA).get(0));
    }

    @Test
    void moduloTestDecDecZero(){
        Decimal number1 = new Decimal("2,5");
        Decimal number2 = new Decimal("1,25");
        Decimal result = new Modulo(number1, number2).getResult();

        assertEquals(0, result.getNumberList().get(LEFT_OF_COMMA).get(0));
        assertEquals(0, result.getNumberList().get(RIGHT_OF_COMMA).get(0));
    }


}

