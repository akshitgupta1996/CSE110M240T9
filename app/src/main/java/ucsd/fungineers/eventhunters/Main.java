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

    public static Main main;

    private Context aContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        new System(this);
        main = this;

        ArrayList<String> attendees = new ArrayList<>();
        attendees.add("aaa");
        attendees.add("bbbb");
        attendees.add("ccccc");
        Date date = new Date();

        /*create the view --> don't change or move this*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_home);
        aContext = this;


    }

    public void getListData() {

        //test 1

        /*
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
        String descriptionOne = "@string/large_text";
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

        */

        ArrayList<Event> hostEventsArray= new ArrayList<Event>();

        hostEventsArray = (ArrayList) System.instance.getLoadedHostingEvents();

        ArrayList image_details = hostEventsArray;

        final ListView lv1 = (ListView) findViewById(R.id.UpcomingEventsHost);
        lv1.setAdapter(new CustomListAdapter(this, image_details));

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



    /*This is a button click method, which will activate when any button is clicked.*/
    public void button_Click(View view) {
        switch (view.getId()) {
            case R.id.buttonCE: {
                Intent i = new Intent(this, CreateEvent.class);
                startActivity(i);

                break;
            }

            case R.id.buttonHost: {
                Log.i("clicks", "Host");
                break;
            }

            case R.id.buttonAttendee: {
                Log.i("clicks", "Attendee");
                Toast.makeText(this, "Attendee Mode", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, AttendingEvents.class);
                startActivity(i);

                break;
            }
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
        if (id == R.id.action_my_events) {
            Intent i = new Intent(this, myEvents_both.class);
            startActivity(i);
            finish();
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