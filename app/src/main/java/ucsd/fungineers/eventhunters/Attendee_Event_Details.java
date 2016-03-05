package ucsd.fungineers.eventhunters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.*;

import java.text.SimpleDateFormat;

/**
 * Created by kagcaoili on 2/21/16.
 */
public class Attendee_Event_Details extends AppCompatActivity {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy EEE");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendee_event_details);

        Event event = (Event) getIntent().getSerializableExtra("selectedEvent");

        TextView eventName = (TextView)findViewById(R.id.eventname);
        eventName.setText(event.getName());
        TextView eventDate = (TextView)findViewById(R.id.event_date);
        eventDate.setText(dateFormat.format(event.getDate().getTime()));
        TextView eventTime = (TextView)findViewById(R.id.event_time);
        eventTime.setText(timeFormat.format(event.getDate().getTime()));
        TextView eventLocation = (TextView)findViewById(R.id.event_location);
        eventLocation.setText(event.getLocation());
        TextView eventHost = (TextView)findViewById(R.id.event_host);
        eventHost.setText(event.getHost());
        TextView eventGenre = (TextView)findViewById(R.id.event_genre);
        eventGenre.setText(event.getGenre().toString());
        TextView eventDescription = (TextView)findViewById(R.id.event_description);
        eventDescription.setText(event.getDescription());
        TextView eventRestriction = (TextView)findViewById(R.id.event_restriction);
        eventRestriction.setText(event.getRestrictionStatus().toString());
    }



}
