package barnettapps.flashread;

import barnettapps.flashread.SpeedReadObjects.*;

public class SpeedReadGenerator {

    char Splitarray[] = {' ','.'};

    public SpeedReadSection sectionGenerator(String input){

        SpeedReadString speedstring = new SpeedReadString(input);
        SpeedReadSection Output = speedstring.split(".");
        return Output;
    }


    // Recursive function to spl it array

    private SpeedReadSection flatten(SpeedReadSection input){

        SpeedReadSection flatSection = input;

        return flatSection;
    }

}
