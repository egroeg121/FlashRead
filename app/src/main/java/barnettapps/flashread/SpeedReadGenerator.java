package barnettapps.flashread;

import java.util.List;

import barnettapps.flashread.SpeedReadObjects.*;
import barnettapps.flashread.SpeedReadObjects.Puncuation.PuncuationList;

public class SpeedReadGenerator {

    public SpeedReadSection generate(String _input){

        // Read in String

        SpeedReadString InputString = new SpeedReadString(_input);
        SpeedReadSection dividedSec = new SpeedReadSection(InputString);

        // Get Puncuation List
        PuncuationList puncuationList = new PuncuationList();

        List<SpeedReadPuncuation> puncList = puncuationList.getPunctiationList();
        for (SpeedReadPuncuation po : puncList){
            dividedSec = dividedSec.split( po );
        }
        SpeedReadSection outSec = new SpeedReadSection( dividedSec );
        return outSec;
    }



    public SpeedReadSection flatten(SpeedReadSection input){
        return new SpeedReadSection( input.flatten() );
    }


}
