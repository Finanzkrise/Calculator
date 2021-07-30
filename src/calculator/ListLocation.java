package calculator;

public enum ListLocation {
    LEFT_OF_COMMA (0),
    RIGHT_OF_COMMA (1);

    private int location;
    private ListLocation(int Location){
        this.location = location;
    }

    public int getLocation(){
        return this.location;
    }
}
