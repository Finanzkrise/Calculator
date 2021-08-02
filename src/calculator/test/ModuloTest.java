package calculator.test;
import calculator.Decimal;
import calculator.Location;
import calculator.operations.Modulo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModuloTest {

    @Test
    void moduloTestIntInt(){
        Decimal number1 = new Decimal("10");
        Decimal number2 = new Decimal("2");
        Decimal result = new Modulo(number1, number2).getResult();

        assertEquals(0, result.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).get(0));
        assertEquals(0, result.getNumberList().get(Location.RIGHT_OF_COMMA.getIndex()).get(0));
    }

    @Test
    void moduloTestIntIntRemainder(){
        Decimal number1 = new Decimal("10");
        Decimal number2 = new Decimal("4");
        Decimal result = new Modulo(number1, number2).getResult();

        assertEquals(2, result.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).get(result.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).size()-1));
        assertEquals(0, result.getNumberList().get(Location.RIGHT_OF_COMMA.getIndex()).get(0));
    }

    @Test
    void moduloTestIntDec(){
        Decimal number1 = new Decimal("5,0");
        Decimal number2 = new Decimal("2,5");
        Decimal result = new Modulo(number1, number2).getResult();

        assertEquals(0, result.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).get(0));
        assertEquals(0, result.getNumberList().get(Location.RIGHT_OF_COMMA.getIndex()).get(0));
    }

    @Test
    void moduloTestIntIntZero(){
        Decimal number1 = new Decimal("5,0");
        Decimal number2 = new Decimal("100");
        Decimal result = new Modulo(number1, number2).getResult();

        assertEquals(5, result.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).get(0));
        assertEquals(0, result.getNumberList().get(Location.RIGHT_OF_COMMA.getIndex()).get(0));
    }

    @Test
    void moduloTestDecDecZero(){
        Decimal number1 = new Decimal("2,5");
        Decimal number2 = new Decimal("1,25");
        Decimal result = new Modulo(number1, number2).getResult();

        assertEquals(0, result.getNumberList().get(Location.LEFT_OF_COMMA.getIndex()).get(0));
        assertEquals(0, result.getNumberList().get(Location.RIGHT_OF_COMMA.getIndex()).get(0));
    }


}

