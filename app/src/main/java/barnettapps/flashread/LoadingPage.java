package barnettapps.flashread;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.view.View;

public class LoadingPage extends Activity {

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        startButton = (Button) findViewById(R.id.startButton);

    }

    public void startButtonClicked(View v)
    {
        Intent intent = new Intent(this, SpeedReadPage.class);
        startActivity(intent);
    }



}


