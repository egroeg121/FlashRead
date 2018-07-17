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

    private long mInterval = 5000;
    private Handler mHandler;
    private int index; // index of latest item to be displayed

    private TextView mainText;
    private TextView intervalText;

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
        readSection = loadReadSectionFromIntent();

        // initialise first Handler
        startRepeatingTask();

        String out;
        if (readSection.getClass() == SpeedReadSection.class){
            SpeedReadSection testSection = (SpeedReadSection) readSection;
            out = (String) testSection.getDataIndex(index).getData();
        }else{
            out = (String) readSection.getDataIndex(index).getData();
        }

        mainText.setText(out);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("SpeedReadPage","OnDestory()");
        stopRepeatingTask();
    }

    void startRepeatingTask() {
        Log.i("SpeedReadPage","Started repeating task");
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
        Log.i("SpeedReadPage","stopRepeatingTask");
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {


            toDisplay = nextItem();
            try { // Try to run
                
            } finally { // will defintely run even with try exception


                // set up new handler after delay of mInterval
                mHandler.postDelayed(mStatusChecker, mInterval);
                Log.i("SpeedReadPage","Set new Handler in " + String.valueOf(mInterval));
                mInterval = 0;
            }
        }
    };



    // updates index, time and outputs string to display
    private String nextItem(){
        String displaystring = "";
        mInterval = 1000;
        while (readSection.getDataIndex(index).getNewDisplay()){ // until new DoesSplit
            SpeedReadObject testObject = readSection.getDataIndex(index);
            SpeedReadObject testObject2 = readSection.getDataIndex(index);
            long testTime = testObject2.getTime();
            mInterval += readSection.getDataIndex(index).getTime();
            if (!readSection.getDataIndex(index).getTransparent()){
                String stringToAdd =  readSection.getDataIndex(index).getData().toString();
                displaystring = displaystring + stringToAdd;
            }
            index++;
        }



        return displaystring;
    }

    private void displayObject(SpeedReadObject toDisplay){
        if (toDisplay.getClass() == SpeedReadSection.class){
            throw new IllegalArgumentException();
        }
        mainText.setText( toDisplay.getData().toString() );
    }

    private SpeedReadObject addToDisplayObject(){

    }


    private void setupLayoutItems(){
        setContentView(R.layout.speed_read_page_portrait);
        mainText = findViewById(R.id.mainText);
        intervalText = findViewById(R.id.intervalValue);
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