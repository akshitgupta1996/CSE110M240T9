package ucsd.fungineers.eventhunters;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;


public class EventHunters_Home extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
Log.i("clicks","Lol");
        setContentView(R.layout.eventhunters_home);
        //Button mBtn1 = (Button) findViewById(R.id.button4);
        //mBtn1.setOnClickListener(this);

    }

    public void button_Click(View view) {
        String button_name = ((Button) view).getText().toString();
        if (button_name.equals("Attendee")) {
            Log.i("clicks", "AttendeePage");
            Intent i = new Intent(this, AttendingEvents.class);
            startActivity(i);
        }
    }

    public void onClick(View v) {
        Log.i("clicks", "You Clicked B4");
        //  Intent i=new Intent(MainActivity.this, MainActivity2.class);
        // startActivity(i);
        if(v.getId() == R.id.button4) {
            setContentView(R.layout.createevent);
            //  Button mBtn2 = (Button) findViewById(R.id.button5);
            // mBtn2.setOnClickListener(this);}
        }

        if(v.getId() == R.id.button5) {
            setContentView(R.layout.attendingevents);
        }
    }

}
