package ucsd.fungineers.eventhunters;

import java.util.*;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FindEvents extends AppCompatActivity {
    private Context aContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findevents);

        aContext = this;

        ArrayList allEvent_details = getAllEventData();
        final ListView lv1 = (ListView) findViewById(R.id.AllEvents);
        lv1.setAdapter(new CustomListAdapter(this, allEvent_details));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Event eventData = (Event) lv1.getItemAtPosition(position);
                Intent intent = new Intent(FindEvents.this, EventStatusActivity.class);
                intent.putExtra(getString(R.string.KEY_EVENT_OBJ), eventData);
                startActivity(intent);

            }
        });

    }

    /*This is a button click method, which will activate when any button is clicked.*/
    public void button_Click(View view) {
        //Toast.makeText(this, "this is my Toast message!!! =)",
        //        Toast.LENGTH_SHORT).show();
        switch (view.getId()) {
            case R.id.buttonFind: {
                Log.i("clicks", "Find");
                break;
            }
            case R.id.buttonMyEvents: {
                Log.i("clicks", "MyEvents");
                Intent i = new Intent(FindEvents.this, myEvents_both.class);
                startActivity(i);
                break;
            }
        }
        /*String button_name = ((Button) view).getText().toString();
        if(button_name.equals("Home"))
        {
            Intent i = new Intent(this, Main.class);
            startActivity(i);
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_status_menu, menu);
        menu.removeItem(R.id.action_event_delete);
        menu.removeItem(R.id.action_event_edit);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_event_delete :
                // TODO delete this event
                break;
            case R.id.action_event_edit :
                // TODO edit this event
                break;
            case R.id.action_event_home :
                // TODO jump to home
                //Toast.makeText(host_event_status.this, "TODO jump to home", Toast.LENGTH_SHORT).show();
                Log.i("clicks", "Home");
                DialogInterface.OnClickListener home_clickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int id) {
                        switch (id) {
                            case DialogInterface.BUTTON_POSITIVE:
                                //i.putExtra("EventKey", newEvent);
                                //System.instance.createEvent(newEvent);
                                //startActivity(i);
                                Intent i = new Intent(FindEvents.this, Main.class);
                                startActivity(i);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder home_b = new AlertDialog.Builder(this);
                home_b.setMessage("Are you sure you want to go to the Home Page?")
                        .setTitle("Home Page")
                        .setPositiveButton("Yes", home_clickListener)
                        .setNegativeButton("No", home_clickListener)
                        .show();
            default:
                break;
        }

        return true;
    }

    private ArrayList getAllEventData() {


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


        return attendEventsArray;


        /*
        ArrayList<Event> thisWeekEvents = new ArrayList<Event>();
        try {
            thisWeekEvents = (ArrayList) System.instance.getAllEvents(RestrictionStatus.NO_RESTRICTIONS);
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }
        return thisWeekEvents;
        */
    }
}
