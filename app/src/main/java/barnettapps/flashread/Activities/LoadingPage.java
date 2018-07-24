package barnettapps.flashread.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.view.View;

import barnettapps.flashread.Activities.SpeedReadPage;
import barnettapps.flashread.R;
import barnettapps.flashread.SpeedReadGenerator;
import barnettapps.flashread.SpeedReadObjects.SpeedReadObjectGSON;
import barnettapps.flashread.SpeedReadObjects.SpeedReadSection;

public class LoadingPage extends Activity {

    private Button speedReadButton;
    private Button loadImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        speedReadButton = findViewById(R.id.speedReadButton);
        loadImageButton = findViewById(R.id.loadImageButton);
    }

    public void speedReadClicked(View v)
    {
        Intent intent = new Intent(this, SpeedReadPage.class);

        //String testString = "This is a shorter sequence.";
        String testString = "Hello! This is FlashRead, I'll help you read faster! I'm still in early development, but it should get a lot better over time. This section probably took just over 5 seconds to read. it would normally take you about 10 seconds.";
        SpeedReadGenerator speedReadGenerator = new SpeedReadGenerator();
        SpeedReadSection toFlatten = speedReadGenerator.generate( testString );
        SpeedReadSection toCombine = speedReadGenerator.flatten( toFlatten );
        SpeedReadSection toSend = speedReadGenerator.combine( toCombine );

        String testJSON = new SpeedReadObjectGSON().toGson(toSend);
        String typeJSON = toSend.getClassString();
        intent.putExtra("OBJECT_DATA", testJSON);
        intent.putExtra("OBJECT_TYPE", typeJSON);
        startActivity(intent);

    }

    public void loadImageClicked(View v)
    {
        Intent intent = new Intent(this, SpeedReadPage.class);
        startActivity(intent);

    }



}


