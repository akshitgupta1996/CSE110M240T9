package ucsd.fungineers.eventhunters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.ParseException;
import java.util.*;

import java.text.*;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.*;
import android.widget.AdapterView;
import android.app.Activity;


/**
 * Created by kagcaoili on 2/21/16.
 */
public class AttendingEvents extends AppCompatActivity {
    private Context aContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendingevents);
        aContext = this;

        ArrayList image_details = getListData();
        final ListView lv1 = (ListView) findViewById(R.id.UpcomingEventsAttendee);
        lv1.setAdapter(new CustomListAdapter(this, image_details));
        lv1.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Event eventData = (Event) lv1.getItemAtPosition(position);
                Intent intent = new Intent(AttendingEvents.this, EventStatusActivity.class);
                intent.putExtra(getString(R.string.KEY_EVENT_OBJ), eventData);
                startActivity(intent);

            }
        });

    }

    private ArrayList getListData() {

        //test 1

        ArrayList<Event> attendEventsArray = new ArrayList<Event>();
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
        GregorianCalendar calendarOne = new GregorianCalendar(2012, 1, 29, 12, 10);
        Log.i("tophat", "Month" + calendarOne.get(Calendar.MONTH));
        calendarOne.add(Calendar.MONTH, -1);
        if (calendarOne.get(Calendar.AM_PM) == 1) {
            calendarOne.set(Calendar.AM_PM, Calendar.PM);
        }
        Event eventsDataOne = new Event(attendees, hostid, RestrictionStatus.OVER_18, Genre.MUSIC, eventNameOne, descriptionOne, calendarOne, location);
        attendEventsArray.add(eventsDataOne);

        GregorianCalendar calendarTwo = new GregorianCalendar(2025, 6, 28, 9, 15);
        Log.i("tophat", "Month" + calendarTwo.get(Calendar.MONTH));
        calendarTwo.add(Calendar.MONTH, -1);
        if (calendarTwo.get(Calendar.AM_PM) == 1) {
            calendarTwo.set(Calendar.AM_PM, Calendar.PM);
        }
        Event eventsDataTwo = new Event(attendees, hostid, RestrictionStatus.OVER_18, Genre.MUSIC, eventNameTwo, descriptionTwo, calendarTwo, location);
        attendEventsArray.add(eventsDataTwo);

        GregorianCalendar calendarThree = new GregorianCalendar(1995, 4, 2, 20, 00);
        Log.i("tophat", "Month" + calendarThree.get(Calendar.MONTH));
        calendarThree.add(Calendar.MONTH, -1);
        if (calendarThree.get(Calendar.AM_PM) == 1) {
            calendarThree.set(Calendar.AM_PM, Calendar.PM);
        }
        Event eventsDataThree = new Event(attendees, hostid, RestrictionStatus.OVER_18, Genre.MUSIC, eventNameThree, descriptionThree, calendarThree, location);
        attendEventsArray.add(eventsDataThree);

        GregorianCalendar calendarFour = new GregorianCalendar(2010, 12, 20, 3, 30);
        Log.i("tophat", "Month" + calendarFour.get(Calendar.MONTH));
        calendarFour.add(Calendar.MONTH, -1);
        if (calendarFour.get(Calendar.AM_PM) == 1) {
            calendarFour.set(Calendar.AM_PM, Calendar.PM);
        }
        Event eventsDataFour = new Event(attendees, hostid, RestrictionStatus.OVER_18, Genre.MUSIC, eventNameFour, descriptionFour, calendarFour, location);
        attendEventsArray.add(eventsDataFour);

        GregorianCalendar calendarFive = new GregorianCalendar(2016, 2, 29, 19, 20);
        Log.i("tophat", "Month" + calendarFive.get(Calendar.MONTH));
        calendarFive.add(Calendar.MONTH, -1);
        if (calendarFive.get(Calendar.AM_PM) == 1) {
            calendarFive.set(Calendar.AM_PM, Calendar.PM);
        }
        Event eventsDataFive = new Event(attendees, hostid, RestrictionStatus.OVER_18, Genre.MUSIC, eventNameFive, descriptionFive, calendarFive, location);
        attendEventsArray.add(eventsDataFive);

        GregorianCalendar calendarSix = new GregorianCalendar(2017, 8, 15, 11, 15);
        Log.i("tophat", "Month" + calendarSix.get(Calendar.MONTH));
        calendarSix.add(Calendar.MONTH, -1);
        if (calendarSix.get(Calendar.AM_PM) == 1) {
            calendarSix.set(Calendar.AM_PM, Calendar.PM);
        }
        Event eventsDataSix = new Event(attendees, hostid, RestrictionStatus.OVER_18, Genre.MUSIC, eventNameSix, descriptionSix, calendarSix, location);
        attendEventsArray.add(eventsDataSix);

        GregorianCalendar calendarSeven = new GregorianCalendar(1964, 7, 22, 22, 45);
        Log.i("tophat", "Month" + calendarSeven.get(Calendar.MONTH));
        calendarSeven.add(Calendar.MONTH, -1);
        if (calendarSeven.get(Calendar.AM_PM) == 1) {
            calendarSeven.set(Calendar.AM_PM, Calendar.PM);
        }
        Event eventsDataSeven = new Event(attendees, hostid, RestrictionStatus.OVER_18, Genre.MUSIC, eventNameSeven, descriptionSeven, calendarSeven, location);
        attendEventsArray.add(eventsDataSeven);

        /*
        ArrayList<Event> attendEventsArray = new ArrayList<Event>();

        try {
            attendEventsArray = (ArrayList) System.instance.getAttendingEventsByUser(System.currentUser.getUserID());
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }
        */
        return attendEventsArray;
    }
        /*

        //String[] attendEventsArray={"Chill Party","Movie Night at Connors","Get Drunk", "Eat Ice Cream", "Lunar New Year", "Go Eat Sushi", "Blah", "Blah Blah", "Board Games", "Hi", "Bye", "This is a scroll test", "Scroll more"};
        ArrayAdapter<Event> attendeeEvents=new
                ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                attendEventsArray);
        ListView attendeeView=(ListView)
                findViewById(R.id.UpcomingEventsAttendee);
        attendeeView.setAdapter(attendeeEvents);
        attendeeView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        Log.i("cereal", ((TextView) view).getText().toString());
        Log.i("flakes", ("" + position));
        Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

    }
*/

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
