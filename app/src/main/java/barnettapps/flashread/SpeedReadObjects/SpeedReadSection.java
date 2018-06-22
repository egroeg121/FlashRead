package barnettapps.flashread.SpeedReadObjects;

public class SpeedReadSection extends SpeedReadObject{

    protected SpeedReadObject[] Data;

    public SpeedReadSection(SpeedReadObject[] _data) {
        Data = _data;
        CharLength =getCharLength();
        ObjectLength = getObjectLength();
        Time = getTime();
        Transparent = getTransparent();
    }


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


    @Override
    public SpeedReadSection split(String splitter) {



        SpeedReadObject[] splitout = new SpeedReadObject[Data.length];
        for (int i = 0; i < Data.length; i++) {
           splitout[i] = Data[i].split(splitter); // outputs SpeedReadSection
        }
        Data = splitout;
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

    public SpeedReadObject getDataIndex(int _index){
        return Data[_index];
    }

    public SpeedReadObject[] getDataArray(){
        return Data;
    }

}
