package calculator;

import java.util.ArrayList;
import java.util.List;

public class Decimal {
    List<Integer> beforeComma = new ArrayList<>();
    List<Integer> afterComma = new ArrayList<>();
    List<List<Integer>> numberList = new ArrayList<>();
    private static final int AFTER_COMMA = 0;
    private static final int BEFORE_COMMA = 1;
    private boolean isNumberPositive = true;

    public Decimal(String... numbers) {
        for (String number : numbers) {
            setNumberListFromString(number);
        }
    }

    public List<List<Integer>> getNumberList() {
        return numberList;
    }

    public void setNumberListFromString(String input) {
        numberList.add(beforeComma);
        numberList.add(afterComma);
        String[] splitAtComma = input.split("[,.]");
        String[] numberAsStringAfterComma = splitAtComma[0].split("");
        String[] numberAsStringBeforeComma = splitAtComma[1].split("");

        // 0
        for (int i = 0; i < numberAsStringAfterComma.length; i++) {
            numberList.get(AFTER_COMMA).add(0, Integer.parseInt(numberAsStringAfterComma[numberAsStringAfterComma.length - i - 1]));
        }
        // 1
        for (int i = 0; i < numberAsStringBeforeComma.length; i++) {
            numberList.get(BEFORE_COMMA).add(0, Integer.parseInt(numberAsStringBeforeComma[numberAsStringBeforeComma.length - i - 1]));
        }
    }

    public String getVorzeichen() {
        if (!isNumberPositive) {
            return "-";
        }
        return "";
    }

    @Override
    public String toString() {
        String Zahl = getVorzeichen();
        for (int i = 0; numberList.get(AFTER_COMMA).size() > i; i++) {
            Zahl += numberList.get(0).get(i);
        }
        Zahl += ",";
        for (int i = 0; numberList.get(BEFORE_COMMA).size() > i; i++) {
            Zahl += numberList.get(1).get(i);
        }
        return Zahl;
    }

    public void setIsPositive(boolean isPositive) {
        this.isNumberPositive = isPositive;
    }
}
