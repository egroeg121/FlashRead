package barnettapps.flashread.SpeedReadObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpeedReadSection extends SpeedReadObject<List<SpeedReadObject>>{

    public SpeedReadSection(List<SpeedReadObject> _input) {
        Data = new ArrayList<SpeedReadObject>();
        Data = _input;
        reCalc();
    }

    public SpeedReadSection(SpeedReadObject[] _input){
        Data = new ArrayList<SpeedReadObject>( Arrays.asList(_input) );
        reCalc();
    }

    public SpeedReadSection(SpeedReadObject _input) {
        Data = new ArrayList<SpeedReadObject>();
        Data.add(_input);
        reCalc();
    }

    @Override
    public SpeedReadSection split(SpeedReadPuncuation _splitter) {
        List<SpeedReadObject> NewData = new ArrayList();
        for (int i = 0; i < Data.size(); i++) {
            NewData.add(Data.get(i).split(_splitter)) ; // run split at Data(i)
        }
        return new SpeedReadSection(NewData);
    }

    public List<SpeedReadObject> flatten() {
        List outList = new ArrayList();
        for (SpeedReadObject o : Data) {
            outList.addAll(o.flatten());
        }
        return outList;
    }

    void reCalc(){
        CharLength =calcCharLength();
        ObjectLength = calcObjectLength();
        Time = calcTime();
        Transparent = calcTransparent();
    }

    public long calcTime(){
        int sum = 0;
        for (SpeedReadObject i : Data){
            sum += i.getTime();
        }
        return sum;
    }

    boolean calcTransparent() {
        int sum = 0;
        for (SpeedReadObject i : Data){
            sum = i.getTransparent() ? 1 : 0;
        }
        if (sum!=0){return false;}
        else{return true;}
    }

    public int calcObjectLength() {
        int sum = 0;
        for (SpeedReadObject i : Data){
            sum += i.getObjectLength();
        }

        return sum;
    }

    public int calcCharLength() {
        int sum = 0;
        for (SpeedReadObject i : Data){
            sum += i.getCharLength();
        }

        return sum;
    }

    public boolean getNewDisplay(){
        boolean out = false;
        for (SpeedReadObject i:Data){
            out = i.getNewDisplay() || out;
        }
        return out;
    }

    public SpeedReadSection add(SpeedReadObject _toAdd){
        Data.add(_toAdd);
        reCalc();
        return this;
    }

    @Override
    public SpeedReadObject merge(SpeedReadObject _toAdd) {
        this.add(_toAdd);
        return null;
    }

    public SpeedReadObject merge(SpeedReadSection _toAdd){
        this.Data.addAll(_toAdd.getData());
        return this;
    }

    public SpeedReadSection remove(int _index){
        Data.remove(_index);
        reCalc();
        return this;
    }

    public SpeedReadSection removeEnd(){
        return remove(Data.size()-1);
    }

    public SpeedReadObject getDataIndex(int _index){
        return Data.get(_index);
    }



}
