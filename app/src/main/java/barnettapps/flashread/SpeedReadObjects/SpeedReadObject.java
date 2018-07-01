package barnettapps.flashread.SpeedReadObjects;

import java.util.ArrayList;
import java.util.List;

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

    public List<SpeedReadObject> flatten(){
        List outList = new ArrayList();
        outList.add(this);
        return outList;
    }

    public String getClassString(){
        // getName returns full package name 'barnett.apps...SpeedReadObject'
        // getSimpleName returns just Object name 'SpeedReadObject'
        return getClass().getName();
    }

    @Override
    public boolean equals(Object o) {
        // TODO Improve equals
        return true;
    }

}