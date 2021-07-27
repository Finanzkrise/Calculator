package calculator.operations;

import calculator.Decimal;
import calculator.ListLocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RootExtraction extends Operation implements ListLocation {

    Logger logger = LogManager.getLogger(RootExtraction.class);

    @Override
    Decimal operate(Decimal number1, Decimal number2) {
        executeOperation(number1, number2);
        result = trimDecimal(result);
        return null;
    }

    @Override
    void executeOperation(Decimal number1, Decimal number2) {
        result = extractRoot(number1, number2);
    }

    private Decimal extractRoot(Decimal number1, Decimal number2) {

        // Integer
        while (!number1.toString().equals("0,")) {

        }

        return null;
    }



    public Decimal generatePrime(int count){
        Decimal result = null;
        int prime = 3;
        int divisor = 3;
        int primesFound = 1;

        if (count == 1) {
            return new Decimal("2");
        }
        while (count > primesFound) {
            logger.info(primesFound + " of " + count + " Primes found");
            while (prime % divisor != 0) {
                divisor += 2;
                logger.info(prime + " % " + divisor + " = " + prime % divisor);
            }
            if (prime == divisor) {
                logger.info(prime + " % " + divisor + " = " + prime % divisor);
                divisor = 3;
                primesFound++;
                result = new Decimal(String.valueOf(prime));
                logger.info("Prime found: " + prime);
                prime += 2;
            }else{
                prime += 2;
                divisor = 3;
            }
        }
        return result;
    }

}
