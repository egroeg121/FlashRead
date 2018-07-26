package barnettapps.flashread;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Surface;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;

import kotlin.jvm.Throws;

import static android.content.Context.CAMERA_SERVICE;

public class FireBaseMLKit {

    private static final String LOG_TAG = "FireBaseMLKit";
    private Context context;

    String outText;

    public FireBaseMLKit(Context contex) {
        this.context = contex;
    }

    public String analyseImage(Bitmap bitmap){

        FirebaseApp.initializeApp(context);
        FirebaseVision testFire = FirebaseVision.getInstance();
        FirebaseVisionTextDetector detector = testFire.getVisionTextDetector();


        FirebaseVisionImage fireImage = FirebaseVisionImage.fromBitmap(bitmap);
        Task<FirebaseVisionText> result =
                detector.detectInImage(fireImage
                )
                        .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                                detectSuccess( firebaseVisionText);



                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                    }
                                });




    return outText;
    }

    void detectFaliure(){


    }

    void detectSuccess( FirebaseVisionText firebaseVisionText ){

        for (FirebaseVisionText.Block block: firebaseVisionText.getBlocks()) {
            Rect boundingBox = block.getBoundingBox();
            Point[] cornerPoints = block.getCornerPoints();
            String text = block.getText();
            outText = text;

            for (FirebaseVisionText.Line line: block.getLines()) {
                // ...

                String lineTet = line.getText();

                for (FirebaseVisionText.Element element: line.getElements()) {
                    // ...

                    String elementText = element.getText();

                }
            }
        }

    }

}
