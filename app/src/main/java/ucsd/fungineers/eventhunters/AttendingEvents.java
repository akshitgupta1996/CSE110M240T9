package ucsd.fungineers.eventhunters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by kagcaoili on 2/21/16.
 */
public class AttendingEvents extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendingevents);


        String[] attendEventsArray={"Chill Party","Movie Night at Connors","Get Drunk", "Eat Ice Cream", "Lunar New Year", "Go Eat Sushi", "Blah", "Blah Blah", "Board Games", "Hi", "Bye", "This is a scroll test", "Scroll more"};
        ArrayAdapter<String> attendeeEvents=new
                ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                attendEventsArray);
        ListView attendeeView=(ListView)
                findViewById(R.id.UpcomingEventsAttendee);
        attendeeView.setAdapter(attendeeEvents);
    }

    /*This is a button click method, which will activate when any button is clicked.*/
    public void button_Click(View view){
        String button_name = ((Button) view).getText().toString();
        if(button_name.equals("Find Events"))
        {
            Intent i = new Intent(this, FindEvents.class);
            startActivity(i);
        }
        else if (button_name.equals("Host"))
        {
            Log.i("clicks", "Host");
            Intent i = new Intent(this, Main.class);
            startActivity(i);

        }
        else if (button_name.equals("Attendee"))
        {
            Log.i("clicks", "Attendee");
        }
    }

}
