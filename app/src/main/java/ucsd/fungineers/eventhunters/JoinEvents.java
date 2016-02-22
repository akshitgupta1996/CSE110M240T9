package ucsd.fungineers.eventhunters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by kagcaoili on 2/21/16.
 */
public class JoinEvents extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joinevents);

        TextView eventName = (TextView) findViewById(R.id.eventname);
        eventName.setText(eventName.getText() + transmitted_Data.getString("eventName"));
        TextView eventDate = (TextView) findViewById(R.id.data_Date);
        eventDate.setText(eventDate.getText() + transmitted_Data.getString("eventDate"));
        TextView eventLocation = (TextView) findViewById(R.id.data_Location);
        eventLocation.setText(eventLocation.getText() + transmitted_Data.getString("eventLocation"));
        TextView eventRestriction = (TextView) findViewById(R.id.data_Restriction);
        eventRestriction.setText(eventRestriction.getText() + transmitted_Data.getString("eventRestriction"));
        TextView eventDescription = (TextView) findViewById(R.id.data_Description);
        eventDescription.setText(eventDescription.getText() + transmitted_Data.getString("eventDescription"));

    }



}
