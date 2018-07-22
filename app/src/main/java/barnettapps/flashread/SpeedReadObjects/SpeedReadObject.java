package barnettapps.flashread.SpeedReadObjects;

import java.util.ArrayList;
import java.util.List;

public abstract class SpeedReadObject<T>{
    protected T Data;
    protected boolean Transparent; // Whether the symbol gets shown on screen
    protected int ObjectLength; // For array lengths
    protected int CharLength; // For centreing
    protected double Time; // How long to display on screen
    protected boolean newDisplay; //

    public SpeedReadObject() {
        Transparent = false;
        ObjectLength = 1;
        CharLength =0;
        Time = 0;
        newDisplay = false;
    }

    public T getData(){
        return Data;
    }

    public SpeedReadObject getDataIndex(int _index) { return this;}

    public int size(){
        return 1;
    }

    public int getObjectLength() {
        return ObjectLength;
    }

    public int getCharLength() {
        return CharLength;
    }

    public double getTime() {
        return Time;
    }

    public boolean getTransparent() {
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

    public boolean getNewDisplay(){
        return this.newDisplay;
    };

    public abstract SpeedReadObject merge(SpeedReadObject _toAdd);

    @Override
    public boolean equals(Object o) {
        // TODO Improve equals
        return true;
    }

}