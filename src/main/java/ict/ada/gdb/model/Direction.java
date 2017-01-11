package ict.ada.gdb.model;

/**
 * Created by lon on 16-2-23.
 */
public enum Direction {
    NONE(0x0),
    OUT(0x01),
    IN(0x10),
    BOTH(0x11);

    private static final Direction[] OPPOSITE = {
            NONE, IN, OUT, BOTH
    };

    private int value;

    Direction(int v) {
        this.value = v;
    }

    public static Direction getDir(int va){
        if(va== Direction.BOTH.getIntValue())return Direction.BOTH;
        if(va== Direction.OUT.getIntValue())return Direction.OUT;
        if(va== Direction.IN.getIntValue())return Direction.IN;
        if(va== Direction.NONE.getIntValue())return Direction.NONE;
        return Direction.IN;
    }



    public int getIntValue() {
        return this.value;
    }

    public Direction opposite() {
        switch (this.getIntValue()){
            case 0x0:
                return Direction.BOTH;
            case 0x01:
                return Direction.IN;
            case 0x10:
                return Direction.OUT;
            case 0x11:
                return Direction.NONE;
            default: return Direction.OUT;

        }
    }
}
