package ucsd.fungineers.eventhunters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import android.widget.*;
import android.widget.Button;

//Apparently this is an empty event.
public class Main extends AppCompatActivity {

    public static System system;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*create the view --> don't change or move this*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventhunters_home);
        /*---------------------------------------------*/

        /*Create a new system*/
        system = new System();

        //Add your test code here.

        String name = "test";
        ArrayList<Event> upcomingEvents = new ArrayList<Event>();
        ArrayList<Event> pastEvents = new ArrayList<Event>();
        float rating = 0;
        int userid = 42;
        User user = new User (name, userid);
        AttendeeComponent attendee = new AttendeeComponent(upcomingEvents, pastEvents,
                RestrictionStatus.NO_RESTRICTIONS, rating, user);

        ArrayList<AttendeeComponent> attendees = new ArrayList<AttendeeComponent>();
        int eventid = 24;
        Event newEvent = new Event(attendees, new HostComponent(), eventid, RestrictionStatus.NO_RESTRICTIONS,
                Genre.MUSIC, "Test Event");

        attendee.joinEvent(newEvent);
        Log.d("UpcomingEvents", newEvent.getAttendees().get(0).getUser().name);

        HostComponent testHost = new HostComponent();
        testHost.createEvent(RestrictionStatus.NO_RESTRICTIONS, Genre.MUSIC, "Anish Is Cool");
        Log.d("HostTest", Main.system.tempEventList.get(0).toString());


       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        Log.i("clicks", "You are in main");


        //Creating an example of a scrollable event bar thing
        String[]  myEventArray={"Chill Party","Movie Night at Connors","Get Drunk", "Eat Ice Cream", "Lunar New Year", "Go Eat Sushi", "Blah", "Blah Blah", "Hi", "Bye", "This is a scroll test", "Scroll more"};
        ArrayAdapter<String> singleEvent=new
                ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                myEventArray);
        ListView eventView=(ListView)
                findViewById(R.id.UpcomingEvents);
        eventView.setAdapter(singleEvent);

        //end event example

    }

    /*This is a button click method, which will activate when any button is clicked.*/
    public void button_Click(View view){
        String button_name = ((Button) view).getText().toString();
        if(button_name.equals("Create Event"))
        {
            Intent i = new Intent(this, CreateEvent.class);
            startActivity(i);
        }
        else if (button_name.equals("Host"))
        {
            Log.i("clicks", "Host");

        }
        else if (button_name.equals("Attendee"))
        {
            Log.i("clicks", "Attendee");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
/*
         ,xXXXXx,
        ,XXXXXXXX,
        XXXXXXXXXX
        `"""XX"""`
            XX
            XX
            XX
   FUNGINEERS FOREVER <3
 */