package barnettapps.flashread.SpeedReadObjects;

public abstract class SpeedReadObject {
    protected long Time;
    protected int Centre;

    public SpeedReadObject() {
        calcCentre();
        calcTime();
    }

    public void calcTime(){
        Time = 0;
    }

    public void calcCentre(){
        Centre = 0;
    }

    public int getObjectLength(){
        return 1;
    }

    public int getCharLength(){
        return 1;
    }

    public SpeedReadObject Split(SpeedReadObject tosplit){
        return tosplit;
    }

}

