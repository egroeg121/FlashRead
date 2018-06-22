package barnettapps.flashread.SpeedReadObjects;


import java.util.ArrayList;
import java.util.List;

public class SpeedReadSection extends SpeedReadObject{

    protected List<SpeedReadObject> Data;

    public SpeedReadSection(List<SpeedReadObject> _data) {
        Data = _data;
        CharLength =getCharLength();
        ObjectLength = getObjectLength();
        Time = getTime();
        Transparent = getTransparent();
    }


    /*
    public SpeedReadSection(String[] _data) {
        this.Data = new SpeedReadString[_data.length];
        for (int i = 0; i < _data.length; i++) {
            this.Data[i] = new SpeedReadString(_data[i]);
        }
        CharLength =getCharLength();
        ObjectLength = getObjectLength();
        Time = getTime();
        Transparent = getTransparent();
    }
    // TODO Update Shorter
    */


    @Override
    public SpeedReadSection split(String _splitter) {

        List<SpeedReadObject> Dataout = new ArrayList<>();

        for (int i = 0; i < Data.size(); i++) {
            Data.set( i, Data.get(i).split(_splitter)) ; // run split at Data(i)
        }

        return this;
    }

    @Override
    public long getTime(){
        int sum = 0;
        for (SpeedReadObject i : Data){
            sum += i.getTime();
        }

        return sum;
    }

    @Override
    boolean getTransparent() {
        int sum = 0;
        for (SpeedReadObject i : Data){
            sum = i.getTransparent() ? 1 : 0;
        }
        if (sum!=0){return false;}
        return true;
    }

    @Override
    public int getObjectLength() {
        int sum = 0;
        for (SpeedReadObject i : Data){
            sum += i.getObjectLength();
        }

        return sum;
    }

    @Override
    public int getCharLength() {
        int sum = 0;
        for (SpeedReadObject i : Data){
            sum += i.getCharLength();
        }

        return sum;
    }

    public SpeedReadObject getData(int _index){
        return Data.get(_index);
    }

    public List<SpeedReadObject> getDataArray(){
        return Data;
    }

}
