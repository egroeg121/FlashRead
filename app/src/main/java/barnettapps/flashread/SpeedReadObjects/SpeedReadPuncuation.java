package barnettapps.flashread.SpeedReadObjects;

public abstract class SpeedReadPuncuation extends SpeedReadObject<String>{

    protected boolean DoesSplit; // Should it be added after splitting (period is added to section)

    public SpeedReadPuncuation(){
        CharLength = 0;
        Time = 0;
        Transparent = false;
        ObjectLength = 0;
        DoesSplit = false;
    }

    boolean getDoesSplit(){
        return DoesSplit;
    }

}