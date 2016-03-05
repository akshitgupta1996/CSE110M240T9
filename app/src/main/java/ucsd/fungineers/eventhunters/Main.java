package ucsd.fungineers.eventhunters;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    private Context aContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        system = new System(this);

        ArrayList<String> attendees = new ArrayList<>();
        attendees.add("aaa");
        attendees.add("bbbb");
        attendees.add("ccccc");
        Date date = new Date();

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
        aContext = this;

        ArrayList image_details = getListData();
        final ListView lv1 = (ListView) findViewById(R.id.UpcomingEventsHost);
        lv1.setAdapter(new CustomListAdapter(this, image_details));
        /*lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                Event eventData = (Event) o;

                //final Intent i = new Intent(aContext, Attendee_Event_Details.class);
                //i.putExtra("selectedEvent", eventData);
                Toast.makeText(Main.this, "Selected :" + " " + eventData, Toast.LENGTH_LONG).show();
                //startActivity(i);
            }
        });*/
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Event eventData = (Event) lv1.getItemAtPosition(position);
                Intent intent = new Intent(Main.this, host_event_status.class);
                intent.putExtra("EventKey", eventData);
                startActivity(intent);

            }
        });


    }




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
/*
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
*/

    private ArrayList getListData() {

        //test 1

        ArrayList<Event> hostEventsArray = new ArrayList<Event>();
        ArrayList<String> attendees = new ArrayList<>();
        attendees.add("Connor");
        attendees.add("Kristin");
        String hostid = "12345";
        String location = "San Diego, CA";
        String eventNameOne = "Pentatonix Concert";
        String eventNameTwo = "Fall Out Boy Concert";
        String eventNameThree = "Wizard of Oz Movie";
        String eventNameFour = "End of Quarter Party!";
        String eventNameFive = "Halloween Dance";
        String eventNameSix = "Study Party";
        String eventNameSeven = "Ingress Tournament";
        String descriptionOne = "Let's go to a concert!";
        String descriptionTwo = "Downtown party! Let's go!";
        String descriptionThree = "Let's go down the yellow brick road";
        String descriptionFour = "So many people, so much dancing";
        String descriptionFive = "There's a dance on campus to celebrate Halloween! Stay safe!";
        String descriptionSix = "Let's destroy those midterms with a study party. Free breakfast";
        String descriptionSeven = "Join us as we walk around La Jolla destroying Resistance portals and resonators.";

        //test 2
        GregorianCalendar calendarOne = new GregorianCalendar(2012,1,29,12,10);
        Log.i("tophat", "Month" + calendarOne.get(Calendar.MONTH));
        calendarOne.add(Calendar.MONTH, -1);
        if (calendarOne.get(Calendar.AM_PM) == 1) {
            calendarOne.set(Calendar.AM_PM, Calendar.PM);
        }
        Event eventsDataOne = new Event(attendees, hostid, RestrictionStatus.OVER_18, Genre.MUSIC, eventNameOne, descriptionOne, calendarOne, location);
        hostEventsArray.add(eventsDataOne);

        GregorianCalendar calendarTwo = new GregorianCalendar(2025,6,28,9,15);
        Log.i("tophat", "Month" + calendarTwo.get(Calendar.MONTH));
        calendarTwo.add(Calendar.MONTH, -1);
        if (calendarTwo.get(Calendar.AM_PM) == 1) {
            calendarTwo.set(Calendar.AM_PM, Calendar.PM);
        }
        Event eventsDataTwo = new Event(attendees, hostid, RestrictionStatus.OVER_18, Genre.MUSIC, eventNameTwo, descriptionTwo, calendarTwo, location);
        hostEventsArray.add(eventsDataTwo);

        GregorianCalendar calendarThree = new GregorianCalendar(1995,4,2,20,00);
        Log.i("tophat", "Month" + calendarThree.get(Calendar.MONTH));
        calendarThree.add(Calendar.MONTH, -1);
        if (calendarThree.get(Calendar.AM_PM) == 1) {
            calendarThree.set(Calendar.AM_PM, Calendar.PM);
        }
        Event eventsDataThree = new Event(attendees, hostid, RestrictionStatus.OVER_18, Genre.MUSIC, eventNameThree, descriptionThree, calendarThree, location);
        hostEventsArray.add(eventsDataThree);

        GregorianCalendar calendarFour = new GregorianCalendar(2010,12,20,3,30);
        Log.i("tophat", "Month" + calendarFour.get(Calendar.MONTH));
        calendarFour.add(Calendar.MONTH, -1);
        if (calendarFour.get(Calendar.AM_PM) == 1) {
            calendarFour.set(Calendar.AM_PM, Calendar.PM);
        }
        Event eventsDataFour = new Event(attendees, hostid, RestrictionStatus.OVER_18, Genre.MUSIC, eventNameFour, descriptionFour, calendarFour, location);
        hostEventsArray.add(eventsDataFour);

        GregorianCalendar calendarFive = new GregorianCalendar(2016,2,29,19,20);
        Log.i("tophat", "Month" + calendarFive.get(Calendar.MONTH));
        calendarFive.add(Calendar.MONTH, -1);
        if (calendarFive.get(Calendar.AM_PM) == 1) {
            calendarFive.set(Calendar.AM_PM, Calendar.PM);
        }
        Event eventsDataFive = new Event(attendees, hostid, RestrictionStatus.OVER_18, Genre.MUSIC, eventNameFive, descriptionFive, calendarFive, location);
        hostEventsArray.add(eventsDataFive);

        GregorianCalendar calendarSix = new GregorianCalendar(2017,8,15,11,15);
        Log.i("tophat", "Month" + calendarSix.get(Calendar.MONTH));
        calendarSix.add(Calendar.MONTH, -1);
        if (calendarSix.get(Calendar.AM_PM) == 1) {
            calendarSix.set(Calendar.AM_PM, Calendar.PM);
        }
        Event eventsDataSix = new Event(attendees, hostid, RestrictionStatus.OVER_18, Genre.MUSIC, eventNameSix, descriptionSix, calendarSix, location);
        hostEventsArray.add(eventsDataSix);

        GregorianCalendar calendarSeven = new GregorianCalendar(1964,7,22,22,45);
        Log.i("tophat", "Month" + calendarSeven.get(Calendar.MONTH));
        calendarSeven.add(Calendar.MONTH, -1);
        if (calendarSeven.get(Calendar.AM_PM) == 1) {
            calendarSeven.set(Calendar.AM_PM, Calendar.PM);
        }
        Event eventsDataSeven = new Event(attendees, hostid, RestrictionStatus.OVER_18, Genre.MUSIC, eventNameSeven, descriptionSeven, calendarSeven, location);
        hostEventsArray.add(eventsDataSeven);


        /*
        ArrayList<Event> hostEventsArray= new ArrayList<Event>();
        try {
            hostEventsArray = (ArrayList) System.instance.getHostingEventsByUser(System.currentUser.getUserID());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        */

        return hostEventsArray;
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