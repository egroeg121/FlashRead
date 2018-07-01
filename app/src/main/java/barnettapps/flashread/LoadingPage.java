package barnettapps.flashread;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.view.View;

import com.google.gson.Gson;

import barnettapps.flashread.SpeedReadObjects.SpeedReadObject;
import barnettapps.flashread.SpeedReadObjects.SpeedReadObjectGSON;
import barnettapps.flashread.SpeedReadObjects.SpeedReadSection;
import barnettapps.flashread.SpeedReadObjects.SpeedReadString;

public class LoadingPage extends Activity {

    private Button startButton;

    private static final String Gson = "test_Section";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        startButton = (Button) findViewById(R.id.startButton);

        startButton.performClick();
    }

    public void startButtonClicked(View v)
    {
        Intent intent = new Intent(this, SpeedReadPage.class);

        String testString = "This is a test Speed Read String, lets see how it handles it.";
        SpeedReadString testSRString= new SpeedReadString( testString );
        SpeedReadString testSRString2= new SpeedReadString( "Second Test" );

        SpeedReadSection testSRSection = new SpeedReadSection( testSRString );
        testSRSection.add(testSRString2);

        SpeedReadObject toSend = testSRString;

        String testJSON = new SpeedReadObjectGSON().toGson(toSend);
        String typeJSON = toSend.getClassString();
        intent.putExtra("OBJECT_DATA", testJSON);
        intent.putExtra("OBJECT_TYPE", typeJSON);
        startActivity(intent);

    }



}


