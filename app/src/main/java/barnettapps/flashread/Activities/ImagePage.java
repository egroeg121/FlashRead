package barnettapps.flashread.Activities;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import barnettapps.flashread.Settings;

import barnettapps.flashread.R;

public class ImagePage extends Activity {
    private static final String LOG_TAG = "ImagePage";

    private Settings settings;
    private Bitmap previewImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        setupVars();

        setupLayoutItems();

        previewImage = getImage();


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

    }

    private void setupVars(){
        settings = new Settings();
    }

    private void toSpeedRead(){
        // FireBaseMLKit firebaseMLKit = new FireBaseMLKit(this);
        // String outString = firebaseMLKit.analyseImage(testImage);
    }

}


