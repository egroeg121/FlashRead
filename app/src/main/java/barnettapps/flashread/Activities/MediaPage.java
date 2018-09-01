package barnettapps.flashread.Activities;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import barnettapps.flashread.Settings;

import barnettapps.flashread.R;

public class MediaPage extends Activity {

    private static final String LOG_TAG = "MediaPage";

    private Settings settings;
    private Button mediaButton, photoButton, cameraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mediapage_portrait);

        setupVars();
        setupLayoutItems();
    }

    private void pickImage(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent,settings.getRESULT_LOAD_IMG());
    }

    private void setupLayoutItems(){
        cameraButton = findViewById(R.id.cameraButton);
        mediaButton = findViewById(R.id.mediaButton);
        photoButton = findViewById(R.id.photoButton);
        //previewImage = getImage();
    }

    private void setupVars(){
        settings = new Settings();
    }



    public void cameraButtonClicked(View v){
        // set mode to camera

        // Take Picture!! Lol its not that easy
    }

    public void mediaButtonClicked(View v){
        // set mode to media button
    }

    public void photosButtonClicked(View v){
        Log.i(LOG_TAG,"Photo Button Clicked");
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult( photoPickerIntent , settings.getRESULT_GET_PHOTOPICKER() );
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK && reqCode == settings.getRESULT_GET_PHOTOPICKER()) {
            Log.i(LOG_TAG,"getRESULT_GET_PHOTOPICKE && RESULT_OK");
            Intent imagepageIntent = new Intent(this,ImagePage.class);
            imagepageIntent.putExtra(settings.getIMAGE_URI(), data.getData().toString()); // gets Uri from intent
            startActivity(imagepageIntent);
        } else if (resultCode == RESULT_OK && reqCode == settings.getRESULT_LOAD_IMG() ) {

            Log.i(LOG_TAG,"getRESULT_LOAD_IMG && RESULT_OK");
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                Bitmap previewImage = BitmapFactory.decodeStream(imageStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }




    }
}



