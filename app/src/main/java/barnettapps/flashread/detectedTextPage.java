package barnettapps.flashread;

import android.graphics.Color;
import android.graphics.Paint;

import com.google.firebase.ml.vision.text.FirebaseVisionText;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class detectedTextPage extends ArrayList {

    List<detectedTextBlock> list;

    public detectedTextPage(FirebaseVisionText inputblock){
        list = new ArrayList<>();
        for (FirebaseVisionText.TextBlock textBlock : inputblock.getTextBlocks()){
            list.add( new detectedTextBlock(textBlock) );
        }
    }

}

