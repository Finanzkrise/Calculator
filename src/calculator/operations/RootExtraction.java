package calculator.operations;

import calculator.Decimal;
import calculator.ListLocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RootExtraction extends Operation implements ListLocation {

    Logger logger = LogManager.getLogger(RootExtraction.class);
    public RootExtraction() {
    }
    public RootExtraction(Decimal number1, Decimal number2) {
        result = operate(number1, number2);
    }

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
        Decimal result = new Decimal("1,0");
        List<Decimal> primeList = new ArrayList<>();
        int nthPrime;
        // Integer
        // while dividend not 1
        while (!number1.toString().equals("1,0")) {
            nthPrime = 0;
            // while dividend % prime != 0
            while (!isDecimalHigherThanDecimal(new Modulo(number1, generatePrime(nthPrime)).getResult(), new Decimal("0,0")) && !isDecimalHigherThanDecimal(new Decimal("0,0"), new Modulo(number1, generatePrime(nthPrime)).getResult())) {
                nthPrime++;
                if (nthPrime > primeList.size()) {
                    primeList.add(new Decimal("0"));
                }
            }
            number1 = new Division(number1, generatePrime(nthPrime)).getResult();
            primeList.set(nthPrime-1, new Addition(primeList.get(nthPrime-1), new Decimal("1")).getResult());
        }
        // divide exponent by root(number2
        int index = 0;
        // i = 0; primeList.size > i; i++
        for (Decimal i = new Decimal("0"); isDecimalHigherThanDecimal(new Decimal(String.valueOf(primeList.size())), i); i = new Addition(i, new Decimal("0")).getResult()) {
          // nthPrimeCount > 0
            if (isDecimalHigherThanDecimal(primeList.get(Integer.parseInt(i.toString())), new Decimal("0"))) {
                // primelist.nthprimecount / root(number2
                primeList.set(index, new Division(primeList.get(index), number2).getResult());
                Decimal j = new Decimal(String.valueOf(index));
                while (isDecimalHigherThanDecimal(j, primeList.get(index))) {
                    result = new Multiplication(result, primeList.get(index)).getResult();
                    j = new Subtraction(j, new Decimal("1")).getResult();
                }
            }
            index++;
        }
        return result;
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
