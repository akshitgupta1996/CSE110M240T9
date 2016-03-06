package ucsd.fungineers.eventhunters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by Nick on 2/15/2016.
 */
public class Event_Status extends AppCompatActivity {

    Event e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.event_status);

        Bundle transmitted_Data = getIntent().getExtras();


        if(transmitted_Data!= null){
            e = (Event)transmitted_Data.getSerializable("event");
            TextView eventName = (TextView) findViewById(R.id.data_Name);
            eventName.setText(eventName.getText() + e.getName());
            TextView eventDate = (TextView) findViewById(R.id.data_Date);
          //  SimpleDateFormat fmt = new SimpleDateFormat("MM-DD-YY");
          // eventDate.setText(eventDate.getText() + fmt.format(e.getDate()));
            TextView eventLocation = (TextView) findViewById(R.id.data_Location);
            eventLocation.setText(eventLocation.getText() + e.getLocation());
            TextView eventRestriction = (TextView) findViewById(R.id.data_Restriction);
            eventRestriction.setText(eventRestriction.getText() + e.getRestrictionStatus().toString());
            TextView eventDescription = (TextView) findViewById(R.id.data_Description);
            eventDescription.setText(eventDescription.getText() + e.getDescription());

        }

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
            System.instance.deleteEvent(e.getEventID());
        }
        else if (button_name.equals("OtherStuff"))
        {
            Log.i("clicks", "OtherStuff");
        }
    }
}
