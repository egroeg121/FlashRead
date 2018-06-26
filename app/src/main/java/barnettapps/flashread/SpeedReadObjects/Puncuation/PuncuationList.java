package barnettapps.flashread.SpeedReadObjects.Puncuation;

import java.util.ArrayList;
import java.util.List;

public class PuncuationList {

    public List getPunctiationList(){
        List PuncList = new ArrayList();
        PuncList.add( new SpeedReadStop() );
        PuncList.add( new SpeedReadExclamation() );
        PuncList.add( new SpeedReadSpace() );
        PuncList.add( new SpeedReadStop() );
        PuncList.add( new SpeedReadComma() );
        return PuncList;
    }





}
