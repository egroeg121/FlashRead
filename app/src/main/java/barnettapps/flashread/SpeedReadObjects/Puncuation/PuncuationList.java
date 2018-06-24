package barnettapps.flashread.SpeedReadObjects.Puncuation;

public class PuncuationList {

    Class[] getPunctiationList(){
        Class[] classes = new Class[] {
                SpeedReadStop.class,
                SpeedReadComma.class,
                SpeedReadSpace.class,
                SpeedReadExclamation.class
        };
        return classes;
    }



}
