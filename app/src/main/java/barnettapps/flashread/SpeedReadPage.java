package barnettapps.flashread;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import barnettapps.flashread.SpeedReadObjects.*;

public class SpeedReadPage extends Activity {

    private int mInterval = 5000;
    private Handler mHandler;
    private TextView mainText;

    private SpeedReadObject readSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("SpeedReadPage", "onCreate called");


        setupLayoutItems();

        // Set up Looping Items
        mHandler = new Handler();

        readSection = loadReadSectionFromIntent();

        String out;
        if (readSection.getClass() == SpeedReadSection.class){
            SpeedReadSection testSection = (SpeedReadSection) readSection;
            out = (String) testSection.getDataIndex(0).getData();
        }else{
            out = (String) readSection.getData();
        }

        mainText.setText(out);
    }

    private void setupLayoutItems(){
        setContentView(R.layout.speed_read_page_portrait);
        mainText = findViewById(R.id.mainText);

        Log.i("SpeedReadPage", "setupLayoutItemsCompleted");
    }

    private SpeedReadObject loadReadSectionFromIntent(){
        // Load SpeedReadObejct
        String GSON_INPUT = getIntent().getStringExtra("OBJECT_DATA");
        String GSON_TYPE = getIntent().getStringExtra("OBJECT_TYPE");

        SpeedReadObjectGSON gsonEngine = new SpeedReadObjectGSON();
        try {
            readSection = gsonEngine.toObject( GSON_INPUT,Class.forName(GSON_TYPE));
            Log.i("SpeedReadPage","readSection created, type:" + readSection.getClass().getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.w("SpeedReadPage","Intent GSON type error, class does not exist");
        }

        return readSection;
    }
}