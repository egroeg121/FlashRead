package barnettapps.flashread;

import java.util.ArrayList;
import java.util.List;

import barnettapps.flashread.SpeedReadObjects.*;
import barnettapps.flashread.SpeedReadObjects.Puncuation.PuncuationList;

public class SpeedReadGenerator {

    public SpeedReadSection generate(String _input) { // split string into SpeedReadObjects

        // Read in String

        SpeedReadString InputString = new SpeedReadString(_input);
        SpeedReadSection dividedSec = new SpeedReadSection(InputString);

        // Get Puncuation List
        PuncuationList puncuationList = new PuncuationList();

        List<SpeedReadPuncuation> puncList = puncuationList.getPunctiationList();
        for (SpeedReadPuncuation po : puncList) {
            dividedSec = dividedSec.split(po);
        }
        SpeedReadSection outSec = new SpeedReadSection(dividedSec);
        return outSec;
    }


    public SpeedReadSection flatten(SpeedReadSection input) { // removed nested Speedreadsection
        return new SpeedReadSection(input.flatten());
    }

    public SpeedReadSection combine(SpeedReadSection inputSection) {  // combine objects together to objects to display
        ArrayList outList = new ArrayList<>();
        for (int i = 0; i < inputSection.size(); i++) {
            int j = i + 1;
            while ( j<inputSection.size() && !inputSection.getDataIndex(j).getNewDisplay() ) {
                inputSection.getDataIndex(i).merge(inputSection.getDataIndex(j));
                inputSection.remove( j );
            }
            outList.add( inputSection.getDataIndex(i) );
        }

        return new SpeedReadSection( outList );
    }

    public SpeedReadObject combine(SpeedReadObject inputObject){
        return inputObject;
    }
}
