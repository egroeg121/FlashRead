package barnettapps.flashread.SpeedReadObjects;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class SpeedReadPuncuation extends SpeedReadString{

    public SpeedReadPuncuation(){
        super("p");
        CharLength = 0;
        Time = 0;
        Transparent = false;
        ObjectLength = 1;
        newDisplay = false;
    }

    @Override
    public SpeedReadObject split(SpeedReadPuncuation _splitter){
        return this;
    };





}