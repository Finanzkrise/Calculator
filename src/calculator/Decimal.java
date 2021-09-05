package calculator;

import calculator.operations.Addition;
import calculator.operations.Subtraction;

import java.util.ArrayList;
import java.util.List;

public class Decimal {

    List<List<Integer>> numberList = new ArrayList<>(2);
    private boolean isNumberPositive = true;

    public Decimal(String... numbers) {
        numberList.add(new ArrayList<>());
        numberList.add(new ArrayList<>());
        for (String number : numbers) {
            setNumberListFromString(number);
        }
    }

    public Decimal(Decimal decimal) {
        numberList = new ArrayList<>(/*decimal.numberList*/);
        numberList.add(new ArrayList<>());
        numberList.add(new ArrayList<>());

        for (Integer number : decimal.numberList.get(Location.LEFT.getIndex())) {
            numberList.get(Location.LEFT.getIndex()).add(number);
        }
        for (Integer number : decimal.numberList.get(Location.RIGHT.getIndex())) {
            numberList.get(Location.RIGHT.getIndex()).add(number);
        }
        this.isNumberPositive = decimal.isNumberPositive;
    }

    public Decimal(boolean isListRightOfComma, List<Integer> list) {
        if (isListRightOfComma) {
            this.numberList.add(new ArrayList<>());
            this.numberList.get(Location.LEFT.getIndex()).add(0);
            this.numberList.add(list);
        } else {
            this.numberList.add(list);
            this.numberList.add(new ArrayList<>());
            this.numberList.get(Location.RIGHT.getIndex()).add(0);
        }
    }

    public Decimal(List<Integer> listLeftOfComma, List<Integer> listRightOfComma) {
        if (!listLeftOfComma.isEmpty()) {
            numberList.add(listLeftOfComma);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(0);
            this.numberList.add(list);
        }
        if (!listRightOfComma.isEmpty()) {
            this.numberList.add(listRightOfComma);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(0);
            this.numberList.add(list);
        }
    }

    public List<List<Integer>> getNumberList() {
        return numberList;
    }

    public boolean isNumberPositive() {
        return isNumberPositive;
    }

    public void setIsPositive(boolean isPositive) {
        if (this.toString().equals("0,0")) {
            this.isNumberPositive = true;
        } else {
            this.isNumberPositive = isPositive;
        }
    }

    public Decimal increment() {
        return new Addition(this, new Decimal("1")).getResult();
    }

    public Decimal decrement() {
        return new Subtraction(this, new Decimal("1")).getResult();
    }

    public void setNumberListFromString(String input) {
        numberList.add(new ArrayList<>());
        numberList.add(new ArrayList<>());
        String[] numberAsStringLeftOfComma;
        String[] numberAsStringRightOfComma;
        String[] splitAtComma;

        input = setNumberIsPositive(input);
        if (input.matches("[\\d?]*[,.][\\d?][\\d?]*")) {
            splitAtComma = input.split("[,.]");
            numberAsStringLeftOfComma = splitAtComma[Location.LEFT.getIndex()].split("");
            numberAsStringRightOfComma = splitAtComma[Location.RIGHT.getIndex()].split("");

            for (int i = 0; i < numberAsStringLeftOfComma.length; i++) {
                if (numberAsStringLeftOfComma[0].equals("")) {
                    numberAsStringLeftOfComma = new String[]{"0"};
                }
                numberList.get(Location.LEFT.getIndex()).add(0, Integer.parseInt(numberAsStringLeftOfComma[numberAsStringLeftOfComma.length - i - 1]));
            }
            for (int i = 0; i < numberAsStringRightOfComma.length; i++) {
                numberList.get(Location.RIGHT.getIndex()).add(0, Integer.parseInt(numberAsStringRightOfComma[numberAsStringRightOfComma.length - i - 1]));
            }
        } else {
            numberAsStringLeftOfComma = input.split("");
            // left of comma
            for (int i = 0; i < numberAsStringLeftOfComma.length; i++) {
                numberList.get(Location.LEFT.getIndex()).add(0, Integer.parseInt(numberAsStringLeftOfComma[numberAsStringLeftOfComma.length - i - 1]));
            }
        }
    }

    private String setNumberIsPositive(String input) {
        if (String.valueOf(input.charAt(0)).equals("-")) {
            setIsPositive(false);
        }
        input = input.replace("-", "");
        return input;
    }

    public String getVorzeichen() {
        if (!isNumberPositive) {
            return "-";
        }
        return "";
    }


    // string buffer implementieren
    @Override
    public String toString() {
        StringBuffer zahl = new StringBuffer();
        zahl.append(getVorzeichen());
        for (int i = 0; numberList.get(Location.LEFT.getIndex()).size() > i; i++) {
            zahl.append(numberList.get(Location.LEFT.getIndex()).get(i));
        }
        /*
        if (this.getNumberList().get(RIGHT_OF_COMMA).isEmpty() || (this.getNumberList().get(RIGHT_OF_COMMA).size() == 1 && this.getNumberList().get(RIGHT_OF_COMMA).get(0) == 0)) {
            return zahl;
        }
         */
        zahl.append(",");
        for (int i = 0; numberList.get(Location.RIGHT.getIndex()).size() > i; i++) {
            zahl.append(numberList.get(Location.RIGHT.getIndex()).get(i));
        }

        return zahl.toString();
    }

}
