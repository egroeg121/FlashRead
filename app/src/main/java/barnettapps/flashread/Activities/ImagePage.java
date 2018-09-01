package barnettapps.flashread.Activities;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import barnettapps.flashread.Settings;
import barnettapps.flashread.R;

public class ImagePage extends Activity {
    private static final String LOG_TAG = "ImagePage";

    private Settings settings;
    private Bitmap mainImage;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagepage_portrait);

        setupVars();

        setupLayoutItems();

        mainImage = getImage();
        setImage(mainImage);

        runTextRecognition(mainImage);
    }

    public void processImageResults(){
        Log.i(LOG_TAG,"process Image Results");


    }

    private void runTextRecognition ( Bitmap mainImage){
        FirebaseVisionImage input = loadBitmap( mainImage );

        if (settings.getCloudProcessing()){
            //runCloudRecognition(input);
        }else{
            runOnDeviceRecognition(input);
        }
    }

    /*
    private void runCloudRecognition(FirebaseVisionImage firebaseVisionImage){
        FirebaseVisionCloudDetectorOptions options =
                new FirebaseVisionCloudDetectorOptions.Builder()
                        .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
                        .setMaxResults(15)
                        .build();
        FirebaseVisionCloudTextDetector detector = FirebaseVision.getInstance().getVisionCloudTextDetector();

        Task<FirebaseVisionCloudText> result = detector.detectInImage(firebaseVisionImage)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionCloudText>() {
                    @Override
                    public void onSuccess(FirebaseVisionCloudText firebaseVisionCloudText) {
                        //detectSuccess(firebaseVisionCloudText);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //detectFailure(e);
                    }
                });
    }
    */

    private void runOnDeviceRecognition(FirebaseVisionImage firebaseVisionImage){
        FirebaseVisionTextRecognizer textRecognizer = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();
        textRecognizer .processImage(firebaseVisionImage)
                .addOnSuccessListener(
                        new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText texts) {
                                Log.i(LOG_TAG,"Detection successful");
                                detectSuccess(texts);

                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i(LOG_TAG,"Detection failure");
                                detectFailure(e);
                            }
                        });

    }
    private void detectSuccess(FirebaseVisionText texts){
        List blockList = processTextRecognitionResult(texts);
        drawBoxes(blockList);

    }


    private void detectFailure(Exception e){
        e.printStackTrace();
    }

    private List<FirebaseVisionText.TextBlock> processTextRecognitionResult(FirebaseVisionText texts){
        Log.i(LOG_TAG,"Processing Results");

        if (texts.getTextBlocks().size() == 0) {
            Toast.makeText(this, "No text found.", Toast.LENGTH_LONG).show();
        }

        return texts.getTextBlocks();

    }


    private void setImage(Bitmap toDisplay){
        imageView.setImageBitmap(toDisplay);
    }
    private Bitmap getImage(){
        final Uri imageUri = getURIFromIntent(getIntent().getExtras()  );
        InputStream imageStream = null;
        try {
            imageStream = getContentResolver().openInputStream(imageUri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(imageStream);
    }
    private Uri getURIFromIntent(Bundle bundle) {
        Uri outURi = null;
        try {
            outURi = Uri.parse( bundle.getString(settings.getIMAGE_URI()) );
        } catch (NullPointerException n) {
            Log.i(LOG_TAG, "Uri from Intent is NULL");
            Toast.makeText(this, "ERROR in getting image", Toast.LENGTH_SHORT).show();
        }
        return outURi;
    }
    private FirebaseVisionImage loadBitmap(Bitmap inputBitmap){
        return FirebaseVisionImage.fromBitmap(inputBitmap);
    }


    private void drawBoxes(List<FirebaseVisionText.TextBlock> blockList ){
        Log.i(LOG_TAG,"Drawing Boxes");
        Bitmap tempBitmap = Bitmap.createBitmap(mainImage.getWidth(), mainImage.getHeight(), Bitmap.Config.RGB_565);
        Canvas tempCanvas = new Canvas(tempBitmap);
        tempCanvas.drawBitmap(mainImage, 0, 0, null);

        for (int i = 0; i < blockList.size(); i++) {
            Rect rect =  blockList.get(i).getBoundingBox();
            Point[] cornerPoints = blockList.get(i).getCornerPoints();
            float[] blockFloats = pointToFloat(cornerPoints);
            //tempCanvas.drawPoints( blockFloats , getPaintNotClicked() );

            tempCanvas.drawRect( rect, getPaintNotClicked() );
        }

        imageView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));

    }


    private void setupLayoutItems(){
        imageView = findViewById(R.id.imagePreview);
    }
    private void setupVars(){
        this.settings = new Settings();
    }

    private float[] pointToFloat(Point[] inputPoints){
        float[] out = new float[inputPoints.length*2];
        for (int i = 0; i < inputPoints.length; i++){
            out[i*2] = inputPoints[i].x;
            out[i*2+1] = inputPoints[i].y;
        }

        return out;
    }

    public Paint getPaintNotClicked() {
        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.RED);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(4.0f);
        return rectPaint;
    }
}


