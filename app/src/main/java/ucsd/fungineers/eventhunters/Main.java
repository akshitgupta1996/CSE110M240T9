package ucsd.fungineers.eventhunters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.Date;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseACL;

import com.facebook.FacebookSdk;
import com.parse.SaveCallback;
import android.widget.*;
import android.widget.Button;

//Apparently this is an empty event.
public class Main extends AppCompatActivity {

    public static System system;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        system = new System(this);

        ArrayList<String> attendees = new ArrayList<>();
        attendees.add("aaa");
        attendees.add("bbbb");
        attendees.add("ccccc");
        Date date = new Date();
        //Event testEvent = new Event(attendees, "tempHostId", null, RestrictionStatus.NO_RESTRICTIONS, Genre.MUSIC, "TITLENAME", "DESCDESCDESC", date);

        //system.createEvent(testEvent);
/*
        //Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        ParseFacebookUtils.initialize(getApplicationContext() );

        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);

        defaultACL.setPublicWriteAccess(true);
*/
        //ParseObject testObject = new ParseObject("TestObject");
        //testObject.put("hello", "tim");
        //testObject.saveInBackground();
        /*create the view --> don't change or move this*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventhunters_home);
        /*---------------------------------------------*/


        //Add your test code here.


        //String name = "test";
        //ArrayList<Event> upcomingEvents = new ArrayList<Event>();
        //ArrayList<Event> pastEvents = new ArrayList<Event>();
        //float rating = 0;
        //int userid = 42;
        //User user1 = new User (name, userid);
        /*
        AttendeeComponent attendee = new AttendeeComponent(upcomingEvents, pastEvents,
                RestrictionStatus.NO_RESTRICTIONS, rating, user1);

        ArrayList<AttendeeComponent> attendees = new ArrayList<AttendeeComponent>();
        int eventid = 24;
        Event newEvent = new Event(attendees, new HostComponent(), eventid, RestrictionStatus.NO_RESTRICTIONS,
                Genre.MUSIC, "Test Event");

        attendee.joinEvent(newEvent);
        Log.d("UpcomingEvents", newEvent.getAttendees().get(0).getUser().name);

        HostComponent testHost = new HostComponent();
        testHost.createEvent(RestrictionStatus.NO_RESTRICTIONS, Genre.MUSIC, "Anish Is Cool");
        Log.d("HostTest", Main.system.tempEventList.get(0).toString());
*/
        /*
        ParseFacebookUtils.logInWithReadPermissionsInBackground(this, null, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (user == null) {
                    Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                } else if (user.isNew()) {
                    Log.d("MyApp", "User signed up and logged in through Facebook!");
                    //currentUser = user;
                } else {
                    Log.d("MyApp", "User logged in through Facebook!");
                    //currentUser = user;
                }

            }
        });*/

        //system.fbLogin(this);

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
        String[]  myEventArray={"Chill Party","Movie Night at Connors","Get Drunk", "Eat Ice Cream", "Lunar New Year", "Go Eat Sushi", "Blah", "Blah Blah", "Board Games", "Hi", "Bye", "This is a scroll test", "Scroll more"};
        ArrayAdapter<String> hostEvents=new
                ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                myEventArray);
        ListView hostView=(ListView)
                findViewById(R.id.UpcomingEventsHost);
        hostView.setAdapter(hostEvents);
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
            Intent i = new Intent(this, AttendingEvents.class);
            startActivity(i);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
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