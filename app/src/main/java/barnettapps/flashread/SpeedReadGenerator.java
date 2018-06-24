package barnettapps.flashread;

import barnettapps.flashread.SpeedReadObjects.*;

public class SpeedReadGenerator {

    char Splitarray[] = {' ','.'};


    public SpeedReadSection SpeedReadGenerator(String _input){

        // Read in String

        SpeedReadString InputString = new SpeedReadString(_input);
        SpeedReadSection Output = new SpeedReadSection(InputString);



        return Output;
    }


    /*
    private SpeedReadSection flatten(SpeedReadSection input){

        // get total object length

        // create single section of that length

        return flatSection;
    }
    */

}
