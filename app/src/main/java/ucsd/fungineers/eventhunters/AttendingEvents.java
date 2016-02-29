package ucsd.fungineers.eventhunters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.*;
import java.text.*;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.*;
import android.widget.AdapterView;
import android.widget.Toast;
import android.app.Activity;
import android.widget.TextView;

import com.parse.ParseException;

/**
 * Created by kagcaoili on 2/21/16. extends AppCompactActivity
 */
public class AttendingEvents extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendingevents);

        ArrayList image_details = getListData();
        final ListView lv1 = (ListView) findViewById(R.id.UpcomingEventsAttendee);
        lv1.setAdapter(new CustomListAdapter(this, image_details));
        lv1.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                Event eventData = (Event) o;
                Toast.makeText(AttendingEvents.this, "Selected :" + " " + eventData, Toast.LENGTH_LONG).show();
            }
        });

    }

    private ArrayList getListData() {
        ArrayList<Event> attendEventsArray = new ArrayList<Event>();
        ArrayList<String> attendees = new ArrayList<>();
        attendees.add("Connor");
        attendees.add("Kristin");
        String hostid = "12345";
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
        //SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, yyyy 'at' HH:mm");
        //String dateString = "DEC 29, 1995 at 08:20";
        SimpleDateFormat fmt = new SimpleDateFormat("dd/M/yyyy hh:mm");
        String dateStringOne = "15/10/2013 20:30";
        Date dateOne = null;
        try {
            dateOne = fmt.parse(dateStringOne);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        String dateStringTwo = "29/01/2020 12:00";
        Date dateTwo = null;
        try {
            dateTwo = fmt.parse(dateStringTwo);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        String dateStringThree = "01/02/1999 05:10";
        Date dateThree = null;
        try {
            dateThree = fmt.parse(dateStringThree);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        /*GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Log.i("corncob", calendar.toString());

        Calendar mDate = Calendar.getInstance();
        SimpleDateFormat form = new SimpleDateFormat("dd/M/yyyy");
        Log.i("corn", form.format(mDate.getTime()));*/

        Event eventsDataOne = new Event(attendees, hostid, RestrictionStatus.UNDER_18, Genre.MUSIC, eventNameOne, descriptionOne, dateOne);
        attendEventsArray.add(eventsDataOne);
        Event eventsDataTwo = new Event(attendees, hostid, RestrictionStatus.UNDER_18, Genre.MUSIC, eventNameTwo, descriptionTwo, dateTwo);
        attendEventsArray.add(eventsDataTwo);
        Event eventsDataThree = new Event(attendees, hostid, RestrictionStatus.UNDER_18, Genre.MUSIC, eventNameThree, descriptionThree, dateThree);
        attendEventsArray.add(eventsDataThree);
        Event eventsDataFour = new Event(attendees, hostid, RestrictionStatus.UNDER_18, Genre.MUSIC, eventNameFour, descriptionFour, dateOne);
        attendEventsArray.add(eventsDataFour);
        Event eventsDataFive = new Event(attendees, hostid, RestrictionStatus.UNDER_18, Genre.MUSIC, eventNameFive, descriptionFive, dateTwo);
        attendEventsArray.add(eventsDataFive);
        Event eventsDataSix = new Event(attendees, hostid, RestrictionStatus.UNDER_18, Genre.MUSIC, eventNameSix, descriptionSix, dateThree);
        attendEventsArray.add(eventsDataSix);
        Event eventsDataSeven = new Event(attendees, hostid, RestrictionStatus.UNDER_18, Genre.MUSIC, eventNameSeven, descriptionSeven, dateOne);
        attendEventsArray.add(eventsDataSeven);

        return attendEventsArray;
    }

        /*
        ArrayList<Event> attendEventsArray= new ArrayList<Event>();
        try {
            attendEventsArray = (ArrayList) System.instance.getAttendingEventsByUser(System.currentUser.getUserID());
        } catch (ParseException e) {
            e.printStackTrace();
        }


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
