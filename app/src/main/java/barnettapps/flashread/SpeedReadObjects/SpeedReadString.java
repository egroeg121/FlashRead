package barnettapps.flashread.SpeedReadObjects;

public class SpeedReadString extends SpeedReadObject {

    protected String DisplayData;

    public SpeedReadString(String displayString) {
        super();
        DisplayData = displayString;
        calcTime();
        calcCentre();
    }

    public SpeedReadSection split(String splitter){
        String[] Stringarray = DisplayData.split(splitter);
        SpeedReadSection outsection = new SpeedReadSection(Stringarray.length);
        return outsection;
    }

    public void calcTime(){
        Time = DisplayData.length();
        System.out.format("Time  = %l",Time);
    }

    public void calcCentre(){
        Centre = DisplayData.length() / 4 ;
        System.out.format("Centre  = %i",Centre);
    }

    public int getCharLength(){
        return DisplayData.length();
    }
}
