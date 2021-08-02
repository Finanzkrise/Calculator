package calculator;

public enum Location {

    LEFT(0),
    RIGHT(1);

    Location(int index) {
        this.index = index;
    }

    int index;

    public int getIndex() {
        return index;
    }
}
