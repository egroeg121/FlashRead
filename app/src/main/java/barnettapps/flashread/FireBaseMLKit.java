package barnettapps.flashread;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;

import java.util.List;

public class FireBaseMLKit {

    private static final String LOG_TAG = "FireBaseMLKit";
    private Context context;

    private String outText;
    FirebaseVisionTextDetector textDetector;
    private FirebaseVisionText outFireBaseVisionText;


    public FireBaseMLKit(Context contex) {
        this.context = contex;

        //FirebaseApp.initializeApp(context);
        textDetector = FirebaseVision.getInstance().getVisionTextDetector();
    }

    public FirebaseVisionText analyseImage(Bitmap bitmap){
        FirebaseVisionTextDetector detector = FirebaseVision.getInstance()
                .getVisionTextDetector();
        detector.detectInImage( loadBitmap(bitmap) )
                .addOnSuccessListener(
                        new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText texts) {
                                outFireBaseVisionText = texts;
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                e.printStackTrace();
                            }
                        });

        return outFireBaseVisionText;
    }

    private FirebaseVisionImage loadBitmap(Bitmap toLoad){
        FirebaseVisionImage loaded = FirebaseVisionImage.fromBitmap(toLoad);
        Log.i(LOG_TAG,"Loaded Bitmap");
        return loaded;
    }

    private void detectSuccess(FirebaseVisionText firebaseVisionText){
        outFireBaseVisionText = firebaseVisionText;
        Log.i(LOG_TAG,"Successfully Detected Text");
    }

    private void detectFaliure(Exception e){
        Log.e(LOG_TAG,"Failed to Detect Text");
        Toast.makeText(context, "Failure to detect text. Please try again or choose another image.", Toast.LENGTH_LONG).show();
    }



    public List<FirebaseVisionText.Block> getBlocks(FirebaseVisionText fireText){
        List<FirebaseVisionText.Block> blocks = fireText.getBlocks();
        if (blocks.size() == 0) {
            Toast.makeText(context, "No text Found", Toast.LENGTH_LONG).show();
        }
        return blocks;
    }
}
