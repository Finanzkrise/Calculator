package calculator.test;

import calculator.Decimal;
import calculator.operations.RootExtraction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RootExtractionTest {


    @Test
    void rootExtractionTest1() {
        Decimal number1 = new Decimal("1");
        Decimal number2 = new Decimal("2");
        Decimal result = new RootExtraction(number1, number2).getResult();
        assertEquals(1, result.getNumberList().get(0).get(0));
    }

    @Test
    void rootExtractionTest4() {
        Decimal number1 = new Decimal("4");
        Decimal number2 = new Decimal("2");
        Decimal result = new RootExtraction(number1, number2).getResult();
        assertEquals(2, result.getNumberList().get(0).get(0));
    }

    @Test
    void rootExtractionTest9() {
        Decimal number1 = new Decimal("9");
        Decimal number2 = new Decimal("2");
        Decimal result = new RootExtraction(number1, number2).getResult();
        assertEquals(3, result.getNumberList().get(0).get(0));
    }

    @Test
    void rootExtractionTest16() {
        Decimal number1 = new Decimal("16");
        Decimal number2 = new Decimal("2");
        Decimal result = new RootExtraction(number1, number2).getResult();
        assertEquals(4, result.getNumberList().get(0).get(0));
    }

    @Test
    void rootExtractionTest25() {
        Decimal number1 = new Decimal("25");
        Decimal number2 = new Decimal("2");
        Decimal result = new RootExtraction(number1, number2).getResult();
        assertEquals(5, result.getNumberList().get(0).get(0));
    }

    @Test
    void rootExtractionTest36() {
        Decimal number1 = new Decimal("36");
        Decimal number2 = new Decimal("2");
        Decimal result = new RootExtraction(number1, number2).getResult();
        assertEquals(6, result.getNumberList().get(0).get(0));
    }

    @Test
    void rootExtractionTest49() {
        Decimal number1 = new Decimal("49");
        Decimal number2 = new Decimal("2");
        Decimal result = new RootExtraction(number1, number2).getResult();
        assertEquals(7, result.getNumberList().get(0).get(0));
    }

    @Test
    void rootExtractionTest64() {
        Decimal number1 = new Decimal("64");
        Decimal number2 = new Decimal("2");
        Decimal result = new RootExtraction(number1, number2).getResult();
        assertEquals(8, result.getNumberList().get(0).get(0));
    }

    @Test
    void rootExtractionTest81() {
        Decimal number1 = new Decimal("81");
        Decimal number2 = new Decimal("2");
        Decimal result = new RootExtraction(number1, number2).getResult();
        assertEquals(9, result.getNumberList().get(0).get(0));
    }

    @Test
    void rootExtractionTest100() {
        Decimal number1 = new Decimal("100");
        Decimal number2 = new Decimal("2");
        Decimal result = new RootExtraction(number1, number2).getResult();
        assertEquals(1, result.getNumberList().get(0).get(0));
        assertEquals(0, result.getNumberList().get(0).get(1));
    }

    @Test
    void rootExtractionTest121() {
        Decimal number1 = new Decimal("121");
        Decimal number2 = new Decimal("2");
        Decimal result = new RootExtraction(number1, number2).getResult();
        assertEquals(1, result.getNumberList().get(0).get(0));
        assertEquals(1, result.getNumberList().get(0).get(1));
    }





 /*
    @Test
    void generatePrimeFirstTest() {
        Decimal prime = new RootExtraction().generatePrime(1);
        assertEquals(2, prime.getNumberList().get(0).get(0));
    }

    @Test
    void generatePrimeSecondTest() {
        Decimal prime = new RootExtraction().generatePrime(2);
        assertEquals(3, prime.getNumberList().get(0).get(0));
    }

    @Test
    void generatePrimeThirdTest() {
        Decimal prime = new RootExtraction().generatePrime(3);
        assertEquals(5, prime.getNumberList().get(0).get(0));
    }

    @Test
    void generatePrimeFourthTest() {
        Decimal prime = new RootExtraction().generatePrime(4);
        assertEquals(7, prime.getNumberList().get(0).get(0));
    }

    @Test
    void generatePrimeTenTest() {
        Decimal prime = new RootExtraction().generatePrime(10);
        assertEquals(2, prime.getNumberList().get(0).get(0));
        assertEquals(9, prime.getNumberList().get(0).get(1));
    }

    @Test
    void generatePrime25Test() {
        Decimal prime = new RootExtraction().generatePrime(25);
        assertEquals(9, prime.getNumberList().get(0).get(0));
        assertEquals(7, prime.getNumberList().get(0).get(1));
    }

    @Test
    void generatePrimeHundredTest() {
        Decimal prime = new RootExtraction().generatePrime(100);
        assertEquals(5, prime.getNumberList().get(0).get(0));
        assertEquals(4, prime.getNumberList().get(0).get(1));
        assertEquals(1, prime.getNumberList().get(0).get(2));
    }

 */
}
