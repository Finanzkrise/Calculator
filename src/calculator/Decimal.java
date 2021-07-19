package calculator;

import java.util.ArrayList;
import java.util.List;

public class Decimal implements ListLocation {
    List<Integer> leftOfComma = new ArrayList<>();
    List<Integer> rightOfComma = new ArrayList<>();
    List<List<Integer>> numberList = new ArrayList<>();
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
        numberList.add(leftOfComma);
        numberList.add(rightOfComma);
        String[] splitAtComma = input.split("[,.]");
        String[] numberAsStringAfterComma = splitAtComma[0].split("");
        String[] numberAsStringBeforeComma = splitAtComma[1].split("");

        // 0
        for (int i = 0; i < numberAsStringAfterComma.length; i++) {
            numberList.get(LEFT_OF_COMMA).add(0, Integer.parseInt(numberAsStringAfterComma[numberAsStringAfterComma.length - i - 1]));
        }
        // 1
        for (int i = 0; i < numberAsStringBeforeComma.length; i++) {
            numberList.get(RIGHT_OF_COMMA).add(0, Integer.parseInt(numberAsStringBeforeComma[numberAsStringBeforeComma.length - i - 1]));
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
        for (int i = 0; numberList.get(LEFT_OF_COMMA).size() > i; i++) {
            Zahl += numberList.get(0).get(i);
        }
        Zahl += ",";
        for (int i = 0; numberList.get(RIGHT_OF_COMMA).size() > i; i++) {
            Zahl += numberList.get(1).get(i);
        }
        return Zahl;
    }

    public void setIsPositive(boolean isPositive) {
        this.isNumberPositive = isPositive;
    }
}
