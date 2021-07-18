package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Decimal {
    List<Integer> beforeComma = new ArrayList<>();
    List<Integer> afterComma = new ArrayList<>();
    List<List<Integer>> numberList = new ArrayList<>();




    public Decimal(String ...numbers) {
        for (String number : numbers) {
            setNumberListFromString(number);
        }
    }


    public void setNumberListFromString(String input) {
        numberList.add(beforeComma);
        numberList.add(afterComma);
        String[] splitAtComma = input.split("[,.]");
        String[] stringGreaterZero = splitAtComma[0].split("");
        String[] stringSmallerZero = splitAtComma[1].split("");

        // 0
        for (int i = 0; i < stringGreaterZero.length; i++) {
            numberList.get(0).add(0, Integer.parseInt(stringGreaterZero[stringGreaterZero.length - i - 1]));
        }
        // 1
        for (int i = 0; i < stringSmallerZero.length; i++) {
            numberList.get(1).add(0, Integer.parseInt(stringSmallerZero[stringSmallerZero.length - i - 1]));
        }
    }

    public List<List<Integer>> getNumberList() {
        return numberList;
    }

    @Override
    public String toString() {
        String Zahl = "";
        for (int i = 0; numberList.get(0).size() > i; i++) {
            Zahl = numberList.get(0).get(i) + Zahl;
        }
        Zahl += ",";
        for (int i = 0; numberList.get(1).size() > i; i++) {
            Zahl = numberList.get(1).get(i) + Zahl;
        }
        return Zahl;
    }

}
