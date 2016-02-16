package ucsd.fungineers.eventhunters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Nick on 2/15/2016.
 */
public class Event_Status extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.event_status);
        //Button mBtn1 = (Button) findViewById(R.id.button4);
        //mBtn1.setOnClickListener(this);

    }
    public void button_Click(View view){
        String button_name = ((Button) view).getText().toString();
        if(button_name.equals("Home"))
        {
            Log.i("clicks", "Home");
            Intent i = new Intent(this, Main.class);
            startActivity(i);
        }
        else if (button_name.equals("Edit Event"))
        {
            Log.i("clicks", "Edit Event");

        }
        else if (button_name.equals("Delete Event"))
        {
            Log.i("clicks", "Delete Event");
        }
        else if (button_name.equals("OtherStuff"))
        {
            Log.i("clicks", "OtherStuff");
        }
    }
}
