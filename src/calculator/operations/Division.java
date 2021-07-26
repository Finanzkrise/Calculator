package calculator.operations;

import calculator.Decimal;
import calculator.ListLocation;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class Division extends Operation implements ListLocation {
    Logger logger = LogManager.getLogger(Division.class);
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
        if (number1.isNumberPositive() && number2.isNumberPositive()) {
            logger.info("number1 and number2 positive");
            result = divide(number1, number2);
            result.setIsPositive(true);
        } else if (!number1.isNumberPositive() && !number2.isNumberPositive()) {
            logger.info("number1 and number2 negative");
            result = divide(number1, number2);
            result.setIsPositive(true);
        } else {
            logger.info("one number positive. the other number negative");
            result = divide(number1, number2);
            result.setIsPositive(false);
        }
    }

    public Decimal divide(Decimal dividend, Decimal divisor) {
        Decimal tempDividend = new Decimal();
        int numbersWritten = 0;
        int dividendInitialLength;

        Decimal divisorAsList = getDecimalAsList(divisor);
        divisorAsList = trimDecimal(divisorAsList);
        Decimal dividendAsList = getDecimalAsList(dividend);
        dividendAsList = trimDecimal(dividendAsList);
        adjustForComma(divisor, dividend, divisorAsList, dividendAsList);
        dividendInitialLength = dividendAsList.getNumberList().get(LEFT_OF_COMMA).size();

        initializeTempDividend(tempDividend, divisorAsList, dividendAsList);
        divisionSteps(tempDividend, numbersWritten, dividendInitialLength, divisorAsList, dividendAsList);
        result = new Decimal(resultLeftOfComma, resultRightOfComma);
        return result;
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
        if (divisor.getNumberList().get(RIGHT_OF_COMMA).size() > dividend.getNumberList().get(RIGHT_OF_COMMA).size()) {
            for (int i = 0; divisor.getNumberList().get(RIGHT_OF_COMMA).size() - dividend.getNumberList().get(RIGHT_OF_COMMA).size() > i; i++) {
                dividendAsList.getNumberList().get(LEFT_OF_COMMA).add(0);
            }
            logger.info(divisor.getNumberList().get(RIGHT_OF_COMMA).size() - dividend.getNumberList().get(RIGHT_OF_COMMA).size() + " zeroes written to dividendAsList");
        } else if (divisor.getNumberList().get(RIGHT_OF_COMMA).size() < dividend.getNumberList().get(RIGHT_OF_COMMA).size()) {
            for (int i = 0; dividend.getNumberList().get(RIGHT_OF_COMMA).size() - divisor.getNumberList().get(RIGHT_OF_COMMA).size() > i; i++) {
                divisorAsList.getNumberList().get(LEFT_OF_COMMA).add(0);
            }
            logger.info(dividend.getNumberList().get(RIGHT_OF_COMMA).size() - divisor.getNumberList().get(RIGHT_OF_COMMA).size() + " zeroes written to divisorAsList");
        }
    }

    private void initializeTempDividend(Decimal tempDividend, Decimal divisorAsList, Decimal dividendAsList) {
        for (int i = 1; divisorAsList.getNumberList().get(LEFT_OF_COMMA).size() > i; i++) {
            moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
        }
        logger.info("tempDividend intitialized as: " + tempDividend);
    }

    private void divisionSteps(Decimal tempDividend, int numbersWritten, int dividendInitialLength, Decimal divisorAsList, Decimal dividendAsList) {
        int digitOfResult;
        while (!tempDividend.toString().equals("0,0") || !dividendAsList.getNumberList().get(LEFT_OF_COMMA).isEmpty()) {
            digitOfResult = 0;

            //add next digit of dividend
            moveDigitFromDividendListToTempDividend(tempDividend, dividendAsList);
            tempDividend = trimDecimal(tempDividend);
            logger.info("tempDividend before division: " + tempDividend);
            // subtract divisor from tempDividend, result ++
            while (!isNumberOneHigherThanNumberTwo(divisorAsList, tempDividend)) {
                tempDividend = new Subtraction().operate(tempDividend, divisorAsList);
                tempDividend = trimDecimal(tempDividend);
                digitOfResult++;
                logger.info("tempDividend after "+ digitOfResult + " cycles: " + tempDividend);
            }
            logger.info("remainder: " + tempDividend);
            numbersWritten = writeResult(dividendInitialLength, divisorAsList, digitOfResult, numbersWritten);
        }
    }

    private void moveDigitFromDividendListToTempDividend(Decimal tempDividend, Decimal dividendAsList) {
        if (dividendAsList.getNumberList().get(LEFT_OF_COMMA).size() > 0) {
            tempDividend.getNumberList().get(LEFT_OF_COMMA).add(dividendAsList.getNumberList().get(LEFT_OF_COMMA).get(0));
            logger.info("'" + dividendAsList.getNumberList().get(LEFT_OF_COMMA).get(0) + "'" + " moved from dividendAsList to tempDividend");
            dividendAsList.getNumberList().get(LEFT_OF_COMMA).remove(0);
        } else {
            tempDividend.getNumberList().get(LEFT_OF_COMMA).add(0);
            logger.info("dividendAsList empty: added 0 to tempDividend");
        }
    }

    private int writeResult(int dividendInitialLength, Decimal divisorAsList, int tempResult, int numbersWritten) {
        if (numbersWritten <= dividendInitialLength - divisorAsList.getNumberList().get(LEFT_OF_COMMA).size()) {
            resultLeftOfComma.add(tempResult);
            logger.info("Result: " + tempResult + " written to resultLeftOfComma");
        } else {
            resultRightOfComma.add(tempResult);
            logger.info("Result: " + tempResult + " written to resultRightOfComma");
        }
        numbersWritten++;
        logger.info("numbersWritten: " + numbersWritten);
        return numbersWritten;
    }
/*
    @Override
    public boolean isNumberOneHigherThanNumberTwo(Decimal number1, Decimal number2) {
        // same size
        number1 = trimDecimal(number1);
        number2 = trimDecimal(number2);
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

 */

    @Override
    public int setOverflow(int number) {
        return 0;
    }
}
