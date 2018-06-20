package barnettapps.flashread.SpeedReadObjects;

public class SpeedReadPuncuation extends SpeedReadObject{


    public void calcCentre(){
        Centre = 0;
    }

    public int getObjectLength(){
        return 0;
    }

    public int getCharLength(){
        return 1;
    }

    public SpeedReadObject Split(SpeedReadObject tosplit){
        return tosplit;
    }

}

