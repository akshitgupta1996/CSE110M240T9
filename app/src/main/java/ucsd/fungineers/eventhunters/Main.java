package ucsd.fungineers.eventhunters;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

//Apparently this is an empty event.
public class Main extends AppCompatActivity {

    public static System system;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
   FUNGINEERS FOREVER
 */