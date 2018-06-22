package barnettapps.flashread.SpeedReadObjects;

public class SpeedReadPuncuation extends SpeedReadString {

    public SpeedReadPuncuation(String data) {
        super(data);
        Time = 10;
        Transparent = true;
    }

    @Override
    public SpeedReadObject split(String splitter) {
        return this;
    }


    public String getData() {
        return Data;
    }

    @Override
    public int getObjectLength() {
        return 1;
    }

    @Override
    public int getCharLength() {
        return 1;
    }

}