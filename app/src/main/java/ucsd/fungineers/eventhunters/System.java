package ucsd.fungineers.eventhunters;

import android.app.Activity;
import android.location.Location;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class will communicate with the database and will be how to go from id to object.
 * Remember how the last PA, the tree on disk worked for CSE 12? It'll be kinda like that.
 *
 * Use main.System to access.
 */
public class System {

    static System instance;

    static ParseUser currentUser;



    //If there is a connection to the database, this variable will be true. If not active, any calls
    //will return null.
    boolean connectionEstablished = false;

    //Keeps track of every event ever. Soon to be replaced by database.
    public ArrayList<Event> tempEventList;


    //Keeps track of every user ever. Soon to be replaced by database.
    public ArrayList<User> tempUserList;

    /**
     * This constructor will start a connection to the database and set the connectionEstablished
     * variable.
     */
    public System(Activity activity)
    {
        //How to print example. In logcat, search your label.
        Log.d("test", "System created!");
        tempEventList = new ArrayList<Event>();
        tempUserList  = new ArrayList<User>();
        connect();
        if(instance == null) {
            fbLogin(activity);
        }
        instance = this;





    }

    /**
     * This constructor is used on login. It will populate the user data for the current user.
     * @param userID The user id to start populating things with. Saved on device to keep user
     *               logged in.
     */
    public System(int userID)
    {

    }

    /**
     * Try to connect to the database if not already connected.
     * @return
     */
    public boolean connect()
    {
        connectionEstablished = true;
        return connectionEstablished;
    }

    /**
     * Communicates with the database to get an event's data.
     * @param eventID
     * @return
     */
    public Event getEvent(int eventID){return null;}

    /**
     * Communicates with the database to create a user's data.
     * @param userID
     * @return
     */
    public User getUser (int userID) {return null;}

    public void fbLogin (Activity activity)
    {
        //Parse.enableLocalDatastore(this);
        Parse.initialize(activity);
        FacebookSdk.sdkInitialize(activity.getApplicationContext());
        ParseFacebookUtils.initialize(activity.getApplicationContext() );

        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);

        defaultACL.setPublicWriteAccess(true);
        ParseFacebookUtils.logInWithReadPermissionsInBackground(activity, null, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (user == null) {
                    Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                } else if (user.isNew()) {
                    Log.d("MyApp", "User signed up and logged in through Facebook!");
                    currentUser = user;
                    currentUser.put("AttendingEvents", new ArrayList<Integer>());
                    currentUser.put("HostingEvents", new ArrayList<Integer>());
                    currentUser.saveInBackground();
                } else {
                    Log.d("MyApp", "User logged in through Facebook!");
                    currentUser = user;
                }
                testAddEvent();

            }
        });
    }

    public List<Integer> getEvents (EventType type)
    {
        List<Integer> array = new ArrayList<Integer>();
        if(type == EventType.ATTENDING)
        {
            array = currentUser.getList("AttendingEvents");
        }
        else
        {
            array = currentUser.getList("HostingEvents");
        }


        return array;
    }

    public void addEvents (EventType type, int eventID)
    {
        List<Integer> array = getEvents(type);

        array.add(eventID);

        if(type == EventType.ATTENDING)
        {
            currentUser.put("AttendingEvents", array);
        }
        else
        {
            currentUser.put("HostingEvents", array);
        }
        currentUser.saveInBackground();
    }

    enum EventType {HOSTING, ATTENDING};

    public void createEvent(Event event)
    {
        ParseObject dbEvent = new ParseObject("Events");
        dbEvent.put("Name", event.getName());
        dbEvent.put("HostID", event.getHost());
        dbEvent.put("AttendeesList", event.getAttendees());
        dbEvent.put("Date", event.getDate());
        dbEvent.put("Restriction", event.getRestrictionStatus().toString());
        dbEvent.put("Genre", event.getGenre().toString());
        dbEvent.put("Description", event.getDescription());
        dbEvent.saveInBackground();

        event.setEventID(dbEvent.getObjectId());

    }

    public void deleteEvent(Event eventToDelete) {

        //create a new parse query for events
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");

        //query events with an id that match id to delete
        query.whereEqualTo("objectId", eventToDelete.getEventID());

        //get the first matching item
        query.findInBackground(new FindCallback<ParseObject>() {

            //when the callback is completed.
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                //if there was no exception
                if (e == null) {

                    //this try catch was forced upon me by the will of Android Studio
                    try {
                        //deletes the found object with matching ID
                        objects.get(0).delete();
                    } catch (ParseException e1) {

                        //this was default. Don't ask me.
                        e1.printStackTrace();
                    }

                    //saves changes to parse
                    objects.get(0).saveInBackground();
                }
            }
        });
    }

    public void testAddEvent() {
        Log.d("test", "add event");
        addEvents(EventType.HOSTING, 23);
        addEvents(EventType.HOSTING, 45);
        addEvents(EventType.ATTENDING, 86);
        addEvents(EventType.ATTENDING, 24);


    }


}
