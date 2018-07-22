package barnettapps.flashread.SpeedReadObjects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import barnettapps.flashread.SpeedReadObjects.Puncuation.SpeedReadStop;

public class SpeedReadString extends SpeedReadObject<String>{

    private static final String LOG_TAG = "SpeedReadString";

    public SpeedReadString(String _data) {
        Data = _data;
        CharLength = Data.length();
        Time = CharLength;
        Transparent = false;
        ObjectLength = 1;
        newDisplay = true;
    }

    public SpeedReadString(String _data,int _charlength,long _time, boolean _transparent, int _objectlength, boolean _newDisplay) {
        Data = _data;
        CharLength = _charlength;
        Time = _time;
        Transparent = _transparent;
        ObjectLength = _objectlength;
        newDisplay = _newDisplay;
    }

    public SpeedReadObject<? extends Object> split(SpeedReadPuncuation splitter) {
        SpeedReadSection outSec;


        // no splitting
        if (Data.indexOf( splitter.getData() ) <0 ){
            return this;
        }

        // Get number of times to split
        int splitterchar = ( Data.length() - Data.replace(splitter.getData(), "").length() );
        int splitterlength = splitter.getData().length();
        int splittercount = splitterchar/splitterlength;

        // Use Tokenizer, avoid regex special characters
        if (splitter.getData().length() < 2) {

            ArrayList tokenizedOutArray= new ArrayList<SpeedReadObject>();

            StringTokenizer testTokenizer = new StringTokenizer(Data,splitter.getData());
            while (testTokenizer.hasMoreTokens()) {

                tokenizedOutArray.add( new SpeedReadString(testTokenizer.nextToken()) );
                tokenizedOutArray.add(splitter);
            }

            outSec = new SpeedReadSection(tokenizedOutArray);

        }else{ // Use Splitter, multi character splitters
            List<SpeedReadObject> Dataout = new ArrayList<>();
            String[] strinarray = Data.split(splitter.getData());

            for (String s : strinarray){
                SpeedReadString splitloop = new SpeedReadString(s);
                Dataout.add(splitloop);
                Dataout.add(splitter);
            }

            outSec = new SpeedReadSection(Dataout);
        }

        // Remove if extra symbol added to end
        if (outSec.getData().size()/2-1 == splittercount) {
            outSec.removeEnd();
        }

        return outSec;
    }

    @Override
    public SpeedReadObject merge(SpeedReadObject _toAdd) {
        if (this.getData().getClass() != _toAdd.getData().getClass()){
            Log.e(LOG_TAG,"Tried to merge, Data types are not the same");
            throw new IllegalArgumentException();
        }

        if (! _toAdd.getTransparent() ){
            this.Data = this.getData() + _toAdd.getData().toString();
        }
        this.Time = this.getTime() + _toAdd.getTime();
        this.Transparent = this.getTransparent() && _toAdd.getTransparent();
        this.newDisplay = this.getNewDisplay() || _toAdd.getNewDisplay();
        this.CharLength = this.getCharLength() + _toAdd.getCharLength();
        return this;
    }



    public void reCalcFromData(){
        CharLength = Data.length();
        Time = CharLength;
    }

}
