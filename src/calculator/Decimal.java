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

    public Decimal(Decimal decimal) {
        rightOfComma = decimal.numberList.get(RIGHT_OF_COMMA);
        leftOfComma = decimal.numberList.get(LEFT_OF_COMMA);
        this.numberList.add(leftOfComma);
        this.numberList.add(rightOfComma);
    }

    public Decimal(boolean isListRightOfComma, List<Integer> list) {
        if (isListRightOfComma) {
            this.leftOfComma = new ArrayList<>();
            this.leftOfComma.add(0);
            this.rightOfComma = list;
            this.numberList.add(leftOfComma);
            this.numberList.add(list);
        } else {
            this.rightOfComma = new ArrayList<>();
            this.rightOfComma.add(0);
            this.leftOfComma = list;
            this.numberList.add(list);
            this.numberList.add(rightOfComma);
        }
    }

    public Decimal(List<Integer> listLeftOfComma, List<Integer> listRightOfComma) {
        if (!listLeftOfComma.isEmpty()) {
            this.leftOfComma = listLeftOfComma;
        } else
            this.leftOfComma.add(0);
        if (!listRightOfComma.isEmpty()) {
            this.rightOfComma = listRightOfComma;
        } else
            this.rightOfComma.add(0);
        this.numberList.add(leftOfComma);
        this.numberList.add(rightOfComma);
    }

    public List<List<Integer>> getNumberList() {
        return numberList;
    }

    public void setNumberListFromString(String input) {
        numberList.add(leftOfComma);
        numberList.add(rightOfComma);
        setNumberIsPositive(input);
        String[] splitAtComma = input.split("[,.]");
        String[] numberAsStringLeftOfComma = splitAtComma[LEFT_OF_COMMA].split("");
        String[] numberAsStringRightOfComma = splitAtComma[RIGHT_OF_COMMA].split("");

        // 0
        for (int i = 0; i < numberAsStringLeftOfComma.length; i++) {
            numberList.get(LEFT_OF_COMMA).add(0, Integer.parseInt(numberAsStringLeftOfComma[numberAsStringLeftOfComma.length - i - 1]));
        }
        // 1
        for (int i = 0; i < numberAsStringRightOfComma.length; i++) {
            numberList.get(RIGHT_OF_COMMA).add(0, Integer.parseInt(numberAsStringRightOfComma[numberAsStringRightOfComma.length - i - 1]));
        }
    }

    private void setNumberIsPositive(String input) {
        String sign = String.valueOf(input.charAt(0));
        if (sign.equals("-")) {
            setIsPositive(false);
            input.replace("-", "");
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
        String Zahl = "";
        for (int i = 0; numberList.get(LEFT_OF_COMMA).size() > i; i++) {
            Zahl += numberList.get(0).get(i);
        }
        Zahl = new Integer(Zahl).toString();
        Zahl += ",";
        for (int i = 0; numberList.get(RIGHT_OF_COMMA).size() > i; i++) {
            Zahl += numberList.get(1).get(i);
        }
        Zahl = getVorzeichen() + Zahl;
        return Zahl;
    }

    public boolean isNumberPositive() {
        return isNumberPositive;
    }

    public void setIsPositive(boolean isPositive) {
        this.isNumberPositive = isPositive;
    }
}
