package calculator;

public enum Location {

    LEFT(0),
    RIGHT(1);

    int index;

    Location(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
