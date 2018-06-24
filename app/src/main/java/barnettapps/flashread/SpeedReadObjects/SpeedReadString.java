package barnettapps.flashread.SpeedReadObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import barnettapps.flashread.SpeedReadObjects.Puncuation.SpeedReadStop;

public class SpeedReadString extends SpeedReadObject<String> {

    public SpeedReadString(String data) {
        Data = data;
        CharLength = Data.length();
        Time = CharLength;
        Transparent = false;
        ObjectLength = 1;
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
                if (splitter.DoesSplit){tokenizedOutArray.add(splitter);};
            }

            outSec = new SpeedReadSection(tokenizedOutArray);

        }else{ // Use Splitter, multi character splitters
            List<SpeedReadObject> Dataout = new ArrayList<>();
            String[] strinarray = Data.split(splitter.getData());

            for (String s : strinarray){
                SpeedReadString splitloop = new SpeedReadString(s);
                Dataout.add(splitloop);
                if (splitter.DoesSplit){Dataout.add(splitter);}
            }

            outSec = new SpeedReadSection(Dataout);
        }

        // Remove if extra symbol added to end
        if (outSec.getData().size()/2-1 == splittercount) {
            outSec.removeEnd();
        }

        return outSec;
    }


}
