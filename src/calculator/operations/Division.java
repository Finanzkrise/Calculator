package calculator.operations;

import calculator.Decimal;
import calculator.ListLocation;

public class Division extends Operation implements ListLocation {
    Decimal overflow = new Decimal("0");
    public Division() {
    }

    public Division(Decimal number1, Decimal number2) {
        result = operate(number1, number2);
    }

    @Override
    Decimal operate(Decimal number1, Decimal number2) {
        executeOperation(number1, number2);
        result = trimDecimal(result);
        return result;
    }

    @Override
    void executeOperation(Decimal number1, Decimal number2) {
        dividePositiveNumbers(number1, number2);
    }

    public void dividePositiveNumbers(Decimal dividend, Decimal divisor) {
        Decimal tempDividend;
        int tempResult;
        int digitOfResult;
        int numbersWritten = 0;
        System.out.println(dividend);
        System.out.println(divisor);

        // Divisor zu einer Liste vor dem Komma konvertieren
        Decimal divisorAsList = new Decimal();
        for (int i = 0; divisor.getNumberList().get(LEFT_OF_COMMA).size() > i; i++) {
            divisorAsList.getNumberList().get(LEFT_OF_COMMA).add(divisor.getNumberList().get(LEFT_OF_COMMA).get(i));
        }

        for (int i = 0; divisor.getNumberList().get(RIGHT_OF_COMMA).size() > i; i++) {
            divisorAsList.getNumberList().get(LEFT_OF_COMMA).add(divisor.getNumberList().get(RIGHT_OF_COMMA).get(i));
        }



        // Dividend zu einer Liste vor dem Komma konvertieren
        Decimal dividendAsList = new Decimal();
        for (int i = 0; dividend.getNumberList().get(LEFT_OF_COMMA).size() > i; i++) {
            dividendAsList.getNumberList().get(LEFT_OF_COMMA).add(dividend.getNumberList().get(LEFT_OF_COMMA).get(i));
        }

        for (int i = 0; dividend.getNumberList().get(RIGHT_OF_COMMA).size() > i; i++) {
            dividendAsList.getNumberList().get(LEFT_OF_COMMA).add(dividend.getNumberList().get(RIGHT_OF_COMMA).get(i));
        }


        divisorAsList = trimDecimal(divisorAsList);

        // temp Dividend zurücksetzen und befüllen

        tempDividend = new Decimal();
        digitOfResult = 0;
        int i;
        tempDividend = new Addition().operate(tempDividend, overflow);
        while ( tempDividend.getNumberList().get(LEFT_OF_COMMA).size() < divisorAsList.getNumberList().get(LEFT_OF_COMMA).size() ) {
            tempDividend.getNumberList().get(LEFT_OF_COMMA).add(dividendAsList.getNumberList().get(LEFT_OF_COMMA).get(0));
            dividendAsList.getNumberList().get(LEFT_OF_COMMA).remove(0);
        }
        tempDividend = trimDecimal(tempDividend);
        System.out.println("tempDividend: " + tempDividend);
        System.out.println("divisiorAsList : " + divisorAsList);
        //not possible to divide? -> add next digit from dividend
        if (isNumberOneHigherThanNumberTwo(divisorAsList, tempDividend)) {
            System.out.println("triggered divisor > dividend");
            tempDividend.getNumberList().get(LEFT_OF_COMMA).add(dividendAsList.getNumberList().get(LEFT_OF_COMMA).get(0));
            dividendAsList.getNumberList().get(LEFT_OF_COMMA).remove(0);
            // TODO add zero to result
        }

        System.out.println("tempDividend: " + tempDividend);
        System.out.println("divisiorAsList : " + divisorAsList);
        // subtract divisor from tempDividend, result ++
        while (isNumberOneHigherThanNumberTwo(tempDividend, divisorAsList)) {
            tempDividend = new Subtraction().operate(tempDividend, divisorAsList);
            digitOfResult++;
            System.out.println("tempDividend: " + tempDividend);
        }
        overflow = tempDividend;
        tempResult = digitOfResult;


        if (numbersWritten <= dividend.getNumberList().get(LEFT_OF_COMMA).size() - divisor.getNumberList().get(LEFT_OF_COMMA).size()){
            resultLeftOfComma.add(tempResult);
        }
        else {
            resultRightOfComma.add(tempResult);
        }

        result = new Decimal(false, resultLeftOfComma);


    }
    public void calcOverflow(){

    }

    @Override
    public int setOverflow(int number) {
        return 0;
    }
}
