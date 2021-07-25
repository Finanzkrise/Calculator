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


        Decimal divisorAsList = getDecimalAsList(divisor);
        divisorAsList = trimDecimal(divisorAsList);
        Decimal dividendAsList = getDecimalAsList(dividend);
        dividendAsList = trimDecimal(dividendAsList);


        // temp Dividend zurücksetzen und befüllen
        tempDividend = new Decimal();
        while (!tempDividend.toString().equals("0,0") || !dividendAsList.getNumberList().get(LEFT_OF_COMMA).isEmpty()) {
            digitOfResult = 0;

            //tempDividend = new Addition().operate(tempDividend, overflow);
            System.out.println("tempDividend after overflow-Addition: " + tempDividend);
            while (tempDividend.getNumberList().get(LEFT_OF_COMMA).size() < divisorAsList.getNumberList().get(LEFT_OF_COMMA).size()) {
                tempDividend.getNumberList().get(LEFT_OF_COMMA).add(dividendAsList.getNumberList().get(LEFT_OF_COMMA).get(0));
                dividendAsList.getNumberList().get(LEFT_OF_COMMA).remove(0);
            }

            tempDividend = trimDecimal(tempDividend);

            //not possible to divide? -> add next digit from dividend
            if (isNumberOneHigherThanNumberTwo(divisorAsList, tempDividend)) {
                System.out.println("triggered divisor > dividend");
                tempDividend.getNumberList().get(LEFT_OF_COMMA).add(dividendAsList.getNumberList().get(LEFT_OF_COMMA).get(0));
                dividendAsList.getNumberList().get(LEFT_OF_COMMA).remove(0);
/*
                if (numbersWritten <= dividend.getNumberList().get(LEFT_OF_COMMA).size() - divisor.getNumberList().get(LEFT_OF_COMMA).size()) {
                    resultLeftOfComma.add(0);

                } else {
                    resultRightOfComma.add(0);

                }

 */
            }

            System.out.println("tempDividend: " + tempDividend);
            System.out.println("divisiorAsList : " + divisorAsList);
            // subtract divisor from tempDividend, result ++
            while (!isNumberOneHigherThanNumberTwo(divisorAsList, tempDividend)) {
                tempDividend = new Subtraction().operate(tempDividend, divisorAsList);
                digitOfResult++;
                System.out.println("tempDividend: " + tempDividend);
            }
            overflow = tempDividend;
            tempResult = digitOfResult;
            System.out.println("result digit : " + digitOfResult);

            if (numbersWritten <= dividend.getNumberList().get(LEFT_OF_COMMA).size() - divisor.getNumberList().get(LEFT_OF_COMMA).size()) {
                resultLeftOfComma.add(tempResult);
            } else {
                resultRightOfComma.add(tempResult);
            }
        }


        result = new Decimal(resultLeftOfComma, resultRightOfComma);


    }

    private Decimal getDecimalAsList(Decimal divisor) {
        Decimal divisorAsList = new Decimal();
        for (int i = 0; divisor.getNumberList().get(LEFT_OF_COMMA).size() > i; i++) {
            divisorAsList.getNumberList().get(LEFT_OF_COMMA).add(divisor.getNumberList().get(LEFT_OF_COMMA).get(i));
        }
        for (int i = 0; divisor.getNumberList().get(RIGHT_OF_COMMA).size() > i; i++) {
            divisorAsList.getNumberList().get(LEFT_OF_COMMA).add(divisor.getNumberList().get(RIGHT_OF_COMMA).get(i));
        }
        return divisorAsList;
    }

    @Override
    public boolean isNumberOneHigherThanNumberTwo(Decimal number1, Decimal number2) {
        // same size
        if (number1.getNumberList().get(LEFT_OF_COMMA).size() == number2.getNumberList().get(LEFT_OF_COMMA).size()) {
            // compare left of comma
            for (int i = 0; getLengthOfLongerNumberSection(number1, number2, LEFT_OF_COMMA) > i; i++) {
                // minuend bigger
                if (number1.getNumberList().get(LEFT_OF_COMMA).get(i) > number2.getNumberList().get(LEFT_OF_COMMA).get(i)) {
                    return true;
                    //subtrahend bigger
                } else if (number1.getNumberList().get(LEFT_OF_COMMA).get(i) < number2.getNumberList().get(LEFT_OF_COMMA).get(i)) {
                    return false;
                }
                // compare RIGHT_OF_COMMA
                else {
                    if (number1.getNumberList().get(RIGHT_OF_COMMA).size() > 0 && number2.getNumberList().get(RIGHT_OF_COMMA).size() > 0) {
                        for (int j = 0; getLengthOfLongerNumberSection(number1, number2, RIGHT_OF_COMMA) > j; j++) {

                            // minuend bigger
                            if (number1.getNumberList().get(RIGHT_OF_COMMA).get(j) > number2.getNumberList().get(RIGHT_OF_COMMA).get(j)) {

                                return true;
                                //subtrahend bigger
                            } else if (number1.getNumberList().get(RIGHT_OF_COMMA).get(j) < number2.getNumberList().get(RIGHT_OF_COMMA).get(j)) {
                                return false;
                            }
                        }
                    } else if (number1.getNumberList().size() > 1 && !(number2.getNumberList().size() > 1)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        //minuend bigger
        else if (number1.getNumberList().get(LEFT_OF_COMMA).size() > number2.getNumberList().get(LEFT_OF_COMMA).size()) {
            return true;
        }
        // subtrahend bigger
        else {
            return false;
        }
        return false;
    }

    @Override
    public int setOverflow(int number) {
        return 0;
    }
}
