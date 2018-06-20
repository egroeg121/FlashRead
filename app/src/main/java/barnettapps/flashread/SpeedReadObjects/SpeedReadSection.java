package barnettapps.flashread.SpeedReadObjects;

public class SpeedReadSection extends SpeedReadObject{

    SpeedReadObject[] Data;

    public SpeedReadSection(int length){
        Data = new SpeedReadObject[length];
    }

    public SpeedReadSection(String[] stringarray){

        Data = new SpeedReadObject[stringarray.length];
        for (int i = 0; i <= stringarray.length; i++){
            Data[i] = new SpeedReadString( stringarray[i] );
        }

    }

    public int getObjectLength(){

        int objectlength = 0;
        for (SpeedReadObject obj : Data){
            objectlength += obj.getObjectLength();
        }
        return objectlength;
    }

    public int getCharLenth(){
        int charlength = 0;
        for (SpeedReadObject obj : Data){
            charlength += obj.getCharLength();
        }
        return charlength;
    }



    public SpeedReadObject Split(SpeedReadObject tosplit){
        return tosplit;
    }


    public SpeedReadObject getObject(int index){

        return Data[index];
    }

}
