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
    private static final String LOG_TAG = "SpeedReadPage";
    private static final double DELAY_TIME = 20;

    private Handler mHandler;
    private int index; // index of latest item to be displayed, global variable

    private TextView mainText;
    private TextView intervalText;
    long startTime;

    private SpeedReadObject readSection;
    private SpeedReadObject displayObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("SpeedReadPage", "onCreate called");


        setupLayoutItems();

        // Set up Looping Items
        mHandler = new Handler();
        index =0;
        Bundle IntentBundle = getIntent().getExtras();
        readSection = loadReadSectionFromBundle( IntentBundle );
        displayObject = readSection.getDataIndex(index);

        startTime = System.currentTimeMillis();
    }


    void startRepeatingTask() {
        Log.i(LOG_TAG,"Wait Start");
        mainText.setText("Ready?");
        mHandler.postDelayed(reapeaterRunnable, 1000 );
        Log.i(LOG_TAG,"Started repeating task");
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(reapeaterRunnable);
        Log.i(LOG_TAG,"stopRepeatingTask");
    }

    Runnable reapeaterRunnable = new Runnable() {
        @Override
        public void run() {
            try { // Try to run
                displayObjectData(displayObject);
            } finally {

                if (index<readSection.size()-1){
                    long mHandlerTime = Math.round(displayObject.getTime() * DELAY_TIME);
                    mHandler.postDelayed(reapeaterRunnable, mHandlerTime);
                    displayObject = readSection.getDataIndex(++index);
                    Log.i(LOG_TAG,"Set new Handler in " + String.valueOf(displayObject.getTime() * DELAY_TIME));
                }else{
                    stopRepeatingTask();
                    Log.i(LOG_TAG,"Finish Speed Read" );
                    intervalText.setText("Section Finished in " + String.valueOf( (System.currentTimeMillis() - startTime)/1000 + " seconds!" ));
                }

            }
        }
    };


    private void displayObjectData(SpeedReadObject displayObject){
        if (displayObject.getClass() == SpeedReadSection.class){
            Log.e(LOG_TAG,"displayObject was a SpeedReadSection");
            throw new IllegalArgumentException();
        }
        if (displayObject.getData().getClass() == String.class ) {
            displayStringObject(displayObject);
            return;
        }
        Log.e(LOG_TAG,"SpeedReadObject not displayable");
        throw new IllegalArgumentException();
    }

    private void displayStringObject(SpeedReadObject displayObject){
        mainText.setText( displayObject.getData().toString() );
        intervalText.setText(String.valueOf( displayObject.getTime() * DELAY_TIME ));
    }


    private void setupLayoutItems(){
        setContentView(R.layout.speed_read_page_portrait);
        mainText = findViewById(R.id.mainText);
        intervalText = findViewById(R.id.intervalValue);
        Log.i(LOG_TAG, "setupLayoutItemsCompleted");
    }

    private SpeedReadObject loadReadSectionFromBundle(Bundle toRead){
        // Load SpeedReadObejct
        String GSON_INPUT = toRead.getString("OBJECT_DATA");
        String GSON_TYPE = toRead.getString("OBJECT_TYPE");

        SpeedReadObjectGSON gsonEngine = new SpeedReadObjectGSON();
        try {
            readSection = gsonEngine.toObject( GSON_INPUT,Class.forName(GSON_TYPE));
            Log.i(LOG_TAG,"readSection created, type:" + readSection.getClass().getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.w(LOG_TAG,"Intent GSON type error, class does not exist");
        }

        return readSection;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        index = savedInstanceState.getInt("SpeedReadPageIndex");
        readSection = loadReadSectionFromBundle(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // killed and restarted.
        savedInstanceState.putInt("Index", index);
        String PutReadSection = new SpeedReadObjectGSON().toGson( readSection );
        savedInstanceState.putString("ReadSectionData",PutReadSection);
        savedInstanceState.putString("ReadSectionType",readSection.getClassString());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG,"onResume()");
        // initialise first Handler
        startRepeatingTask();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG,"onPause()");
        stopRepeatingTask();
    }

}