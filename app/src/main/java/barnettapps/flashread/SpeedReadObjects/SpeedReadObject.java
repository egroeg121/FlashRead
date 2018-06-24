package barnettapps.flashread.SpeedReadObjects;

public abstract class SpeedReadObject<T>{
    protected T Data;
    protected boolean Transparent; // Whether the symbol gets shown on screen
    protected int ObjectLength; // For array lengths
    protected int CharLength; // For centreing
    protected long Time; // How long to display on screen

    public SpeedReadObject() {
        Transparent = false;
        ObjectLength = 1;
        CharLength =0;
        Time = 0;
    }

    public T getData(){
        return Data;
    }

    public int getObjectLength() {
        return ObjectLength;
    }

    public int getCharLength() {
        return CharLength;
    }

    long getTime() {
        return Time;
    }

    boolean getTransparent() {
        return Transparent;
    }

    public SpeedReadObject split(SpeedReadPuncuation _splitter){
        return this;
    };

    @Override
    public boolean equals(Object o) {
        // TODO Improve equals
        return true;
    }
}

