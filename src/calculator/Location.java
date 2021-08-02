package calculator;

    // sollte enum werden
public enum Location {

    LEFT_OF_COMMA (0),
    RIGHT_OF_COMMA (1);

    Location(int index) {
        this.index = index;
    }

    int index;

    public int getIndex() {
        return index;
    }
}
