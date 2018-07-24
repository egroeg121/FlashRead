package barnettapps.flashread.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import java.io.FileNotFoundException;
import java.io.InputStream;

import barnettapps.flashread.Activities.SpeedReadPage;
import barnettapps.flashread.R;
import barnettapps.flashread.SpeedReadGenerator;
import barnettapps.flashread.SpeedReadObjects.SpeedReadObjectGSON;
import barnettapps.flashread.SpeedReadObjects.SpeedReadSection;

public class TestOCRActivity extends Activity {

    Bitmap testImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testocractivity);

        pickImage();

        analyseImage();

    }

    private void pickImage(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    private void analyseImage(){
        FirebaseVisionImage image = FirebaseVisionImage.fromMediaImage();
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap testImage = BitmapFactory.decodeStream(imageStream);
                testImage.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

}


