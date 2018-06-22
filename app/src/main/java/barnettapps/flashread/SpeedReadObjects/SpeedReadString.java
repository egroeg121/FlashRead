package barnettapps.flashread.SpeedReadObjects;

import java.util.ArrayList;
import java.util.List;

public class SpeedReadString extends SpeedReadObject {

    protected String Data;

    public SpeedReadString(String data) {
        Data = data;
        Transparent = getTransparent();
        ObjectLength = getObjectLength();
        CharLength =getCharLength();
        Time = getTime();
    }

    @Override
    public SpeedReadObject split(String splitter) {

        if (Data.indexOf(splitter) <0 ){
            return this; // no splitting
        }else{
            List<SpeedReadObject> Dataout = new ArrayList<>();
            String[] strinarray = Data.split(splitter);

            for (String s : strinarray){
                SpeedReadString splitloop = new SpeedReadString(s);
                Dataout.add(splitloop);
            }
            SpeedReadSection outarray = new SpeedReadSection(Dataout); // split into Section
            return outarray;
        }
    }


    public String getData() {
        return Data;
    }

    @Override
    public int getObjectLength() {
        return 1;
    }

    @Override
    public int getCharLength() {
        return Data.length();
    }

    @Override
    long getTime() {
        return CharLength;
    }

    @Override
    boolean getTransparent() {
        return false;
    }

}
