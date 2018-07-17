package barnettapps.flashread.SpeedReadObjects;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class SpeedReadPuncuation extends SpeedReadString{

    public SpeedReadPuncuation(){
        super(null);
        Data = "p";
        CharLength = 0;
        Time = 0;
        Transparent = false;
        ObjectLength = 1;
    }

    @Override
    public SpeedReadObject split(SpeedReadPuncuation _splitter){
        return this;
    };





}