package ucsd.fungineers.eventhunters;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import android.widget.*;
import android.transition.AutoTransition;
import android.transition.Scene;
import android.transition.Transition;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.transition.TransitionManager;

//Apparently this is an empty event.
public class Main extends AppCompatActivity {

    /*TRANSITION TEST VARIABLES*/
    /*END TRANSITION TEST VARS*/


    public static System system;
    ViewGroup rootContainer;
    Scene scene1;
    Scene scene2;
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
        setContentView(R.layout.eventhunters_home);
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
/*
        rootContainer =
                (ViewGroup) findViewById(R.id.hunter_home);
        scene1 = Scene.getSceneForLayout(rootContainer,
                R.layout.eventhunters_home, this);

        scene2 = Scene.getSceneForLayout(rootContainer,
                R.layout.createevent, this);

        scene1.enter();*/
        /*This is where I'm testing transitions*/
      //  Button mBtn1 = (Button) findViewById(R.id.button);
      //  mBtn1.setOnClickListener(this);
       // if(getContentScene() == getContentScene(R.layout.eventstatus))
      //  Button mBtn2 = (Button) findViewById(R.id.button5);
        //mBtn2.setOnClickListener(this);
        /*END TRANSITION TEST*/
       // ListView v = (ListView) findViewById(R.id.UpcomingEvents);
        String[]  myEventArray={"Chill Party","Movie Night at Connors","Get Drunk", "Eat Ice Cream", "Lunar New Year", "Go Eat Sushi", "Blah", "Blah Blah", "Hi", "Bye", "This is a scroll test", "Scroll more"};
        ArrayAdapter<String> singleEvent=new
                ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                myEventArray);
        ListView eventView=(ListView)
                findViewById(R.id.UpcomingEvents);
        eventView.setAdapter(singleEvent);
    }

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

   /* public void goToScene2 (View view)
    {
        TransitionManager.go(scene2);
    }

    public void goToScene1 (View view)
    {
       // setContentView(R.layout.eventhunters_home);
      TransitionManager.go(scene1);
    }*/
   // @Override
   /* public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.eventstatus, container, false);
        Button buttonClick = (Button) rootView.findViewById(R.id.button5);
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.createevent);
            }
        });
        return rootView;
    }
*/

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