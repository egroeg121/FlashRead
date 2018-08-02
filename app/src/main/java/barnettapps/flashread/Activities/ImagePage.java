package barnettapps.flashread.Activities;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.ml.vision.text.FirebaseVisionText;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import barnettapps.flashread.FireBaseMLKit;
import barnettapps.flashread.Settings;
import barnettapps.flashread.R;

public class ImagePage extends Activity {
    private static final String LOG_TAG = "ImagePage";

    private Settings settings;
    private Bitmap previewImage;
    private ImageView imageView;
    private FireBaseMLKit fireBaseMLKit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagepage_portrait);

        setupVars();

        setupLayoutItems();

        previewImage = getImage();
        setImage(previewImage);
        FirebaseVisionText detectedText = detectText(previewImage);
        List<FirebaseVisionText.Block> listFire = fireBaseMLKit.getBlocks( detectedText );
        drawBoxes( listFire );
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

    private void setupLayoutItems(){
        imageView = findViewById(R.id.imagePreview);
    }

    private void setupVars(){
        settings = new Settings();
        fireBaseMLKit = new FireBaseMLKit(this);
    }

    private void toSpeedRead(){
        // FireBaseMLKit firebaseMLKit = new FireBaseMLKit(this);
        // String outString = firebaseMLKit.analyseImage(testImage);
    }

    private FirebaseVisionText detectText(Bitmap toDetect){
        return fireBaseMLKit.analyseImage(toDetect);
    }

    private void drawBoxes(List<FirebaseVisionText.Block> blockList){
        Bitmap tempBitmap = Bitmap.createBitmap(previewImage.getWidth(), previewImage.getHeight(), Bitmap.Config.RGB_565);
        Canvas tempCanvas = new Canvas(tempBitmap);
        tempCanvas.drawBitmap(previewImage, 0, 0, null);


        for (int i = 0; i < blockList.size(); i++) {
            Rect toDraw = blockList.get(i).getBoundingBox();
            tempCanvas.drawRect( toDraw , getPaintNotClicked() );
        }

        imageView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
    }



    private void setOnClick(){
    }

    private Paint getPaintNotClicked(){
        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.RED);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(4.0f);
        return rectPaint;
    }

    private Paint getPaintClicked(){
        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.RED);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(4.0f);
        return rectPaint;
    }

}


