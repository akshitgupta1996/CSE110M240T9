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
public class CreateEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.createevent);
        //Button mBtn1 = (Button) findViewById(R.id.button4);
        //mBtn1.setOnClickListener(this);


    }
    public void button_Click(View view){
        String button_name = ((Button) view).getText().toString();
        if(button_name.equals("Add Event"))
        {
            Log.i("clicks", "Add Event");
            Intent i = new Intent(this, Event_Status.class);
            startActivity(i);
        }
    }

}
