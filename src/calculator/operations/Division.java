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
    public Decimal operate(Decimal number1, Decimal number2) {
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
        int digitOfResult = 0;
        int numbersWritten = 0;
        tempDividend = new Decimal();
        int dividendInitialLength;

        System.out.println(dividend);
        System.out.println(divisor);

        Decimal divisorAsList = getDecimalAsList(divisor);
        divisorAsList = trimDecimal(divisorAsList);
        Decimal dividendAsList = getDecimalAsList(dividend);
        dividendAsList = trimDecimal(dividendAsList);
        adjustForComma(divisor, dividend, divisorAsList, dividendAsList);
        dividendInitialLength = dividendAsList.getNumberList().get(LEFT_OF_COMMA).size();

        System.out.println("dividend as List: " + dividendAsList);
        System.out.println("divisor as List: " + divisorAsList);

        // set initail tempDividend
        for (int i = 1; divisorAsList.getNumberList().get(LEFT_OF_COMMA).size() > i; i++) {
            moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
        }

        // solange Divison noch nicht aus ist:
        while (!tempDividend.toString().equals("0,0") || !dividendAsList.getNumberList().get(LEFT_OF_COMMA).isEmpty()) {
            digitOfResult = 0;

            //tempDividend = new Addition().operate(tempDividend, overflow);


            //add next digit of dividend
            moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
            tempDividend = trimDecimal(tempDividend);
            System.out.println("tempDividend: " + tempDividend);

            // subtract divisor from tempDividend, result ++
            while (!isNumberOneHigherThanNumberTwo(divisorAsList, tempDividend)) {
                tempDividend = new Subtraction().operate(tempDividend, divisorAsList);
                digitOfResult++;
                System.out.println("tempDividend: " + tempDividend);
            }
            overflow = tempDividend;
            System.out.println("result digit : " + digitOfResult);

            numbersWritten = writeResult(dividendInitialLength, divisorAsList, digitOfResult, numbersWritten);
        }


        result = new Decimal(resultLeftOfComma, resultRightOfComma);


    }

    private void moveDigitFromDividendListToTempDividend(Decimal tempDividend, Decimal dividendAsList) {
        if (dividendAsList.getNumberList().get(LEFT_OF_COMMA).size() > 0) {
            tempDividend.getNumberList().get(LEFT_OF_COMMA).add(dividendAsList.getNumberList().get(LEFT_OF_COMMA).get(0));
            dividendAsList.getNumberList().get(LEFT_OF_COMMA).remove(0);
        }
        else {
            tempDividend.getNumberList().get(LEFT_OF_COMMA).add(0);
        }
    }

    private int writeResult(int dividendInitialLength, Decimal divisorAsList, int tempResult, int numbersWritten) {
        // if not right yet

        if (numbersWritten <=  dividendInitialLength - divisorAsList.getNumberList().get(LEFT_OF_COMMA).size()) {
            resultLeftOfComma.add(tempResult);
        } else {
            System.out.println("switch to right of comma!!");
            resultRightOfComma.add(tempResult);
        }
        numbersWritten++;
        return  numbersWritten;
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

    private void adjustForComma(Decimal divisor, Decimal dividend, Decimal divisorAsList, Decimal dividendAsList) {
        if (divisor.getNumberList().get(RIGHT_OF_COMMA).size() > dividend.getNumberList().get(RIGHT_OF_COMMA).size()){
            for (int i = 0; divisor.getNumberList().get(RIGHT_OF_COMMA).size() - dividend.getNumberList().get(RIGHT_OF_COMMA).size() > i; i++){
                dividendAsList.getNumberList().get(LEFT_OF_COMMA).add(0);
            }
        }
        else if (divisor.getNumberList().get(RIGHT_OF_COMMA).size() < dividend.getNumberList().get(RIGHT_OF_COMMA).size()) {
            for (int i = 0; dividend.getNumberList().get(RIGHT_OF_COMMA).size() - divisor.getNumberList().get(RIGHT_OF_COMMA).size() > i; i++){
                divisorAsList.getNumberList().get(LEFT_OF_COMMA).add(0);
            }
        }
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

    public int offset(Decimal number1, Decimal number2){
        if (isNumberOneHigherThanNumberTwo(number1, number2)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int setOverflow(int number) {
        return 0;
    }
}
