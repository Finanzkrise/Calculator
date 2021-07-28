package calculator.operations;

import calculator.Decimal;
import calculator.ListLocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RootExtraction extends Operation implements ListLocation {
    Logger logger = LogManager.getLogger(RootExtraction.class);

    @Override
    void executeOperation(Decimal number1, Decimal number2) {

    }

    public Decimal extractRoot(Decimal number1, Decimal number2) {


        return result;
    }
}
