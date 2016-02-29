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

    public static String objectId = "objectId";
    public static String name = "Name";
    public static String hostId = "HostID";
    public static String attendeeList = "AttendeesList";
    public static String date = "Date";
    public static String restrictionStatus = "Restriction";
    public static String genre = "Genre";
    public static String description = "Description";

    public static String attendingEvents = "AttendingEvents";
    public static String hostingEvents = "HostingEvents";


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
                    currentUser.put(System.attendingEvents, new ArrayList<Integer>());
                    currentUser.put(System.hostingEvents, new ArrayList<Integer>());
                    currentUser.saveInBackground();
                } else {
                    Log.d("MyApp", "User logged in through Facebook!");
                    currentUser = user;
                }
//                testAddEvent();

            }
        });
    }

    public List<Integer> getEventsFromUser (EventType type)
    {
        List<Integer> array = new ArrayList<Integer>();
        if(type == EventType.ATTENDING)
        {
            array = currentUser.getList(System.attendingEvents);
        }
        else
        {
            array = currentUser.getList(System.hostingEvents);
        }


        return array;
    }

    /* Adds an event to the currently logged in user
     * @param type Type of event (Hosting or Attending)
     * @param eventID ID of event
     */
    public void addEventsToUser (EventType type, int eventID)
    {
        List<Integer> array = getEventsFromUser(type);

        array.add(eventID);

        if(type == EventType.ATTENDING)
        {
            currentUser.put(System.attendingEvents, array);
        }
        else
        {
            currentUser.put(System.hostingEvents, array);
        }
        currentUser.saveInBackground();
    }

    enum EventType {HOSTING, ATTENDING};

    /**
     * Creates an event in the database based on an Event object
     * @param event event to create a ParseObject from and put in database
     */
    public void createEvent(Event event)
    {
        ParseObject dbEvent = new ParseObject("Events");
        dbEvent.put(System.name, event.getName());
        dbEvent.put(System.hostId, event.getHost());
        dbEvent.put(System.attendeeList, event.getAttendees());
        dbEvent.put(System.date, event.getDate());
        dbEvent.put(System.restrictionStatus, event.getRestrictionStatus().toString());
        dbEvent.put(System.genre, event.getGenre().toString());
        dbEvent.put(System.description, event.getDescription());
        dbEvent.saveInBackground();

        event.setEventID(dbEvent.getObjectId());

    }

    /**
     * Gets an event from the database, based on an ID
     * @param id ID of event to get
     * @return Event Object created from ParseObject
     * @throws ParseException
     */
    public Event getEvent(String id) throws ParseException {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");

        query.whereEqualTo(System.objectId, id);

        List<ParseObject> foundEvent = query.find();

        if (foundEvent == null) {

            return null;

        }

        Event eventToReturn = new Event(foundEvent.get(0));

        return eventToReturn;
    }

    /**
     * Gets all events matching the restriction status
     * @param restrictionStatus Restriction status of events to get
     * @return A list of events matching the restriction status
     */
    public List<Event> getAllEvents(RestrictionStatus restrictionStatus) throws ParseException {

      ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
      List<ParseObject> allEventObjects = query.find();

      List<Event> allEvents = new ArrayList<Event>();

      for (int eventIndex = 0; eventIndex < allEventObjects.size(); eventIndex++) {

          allEvents.add(new Event(allEventObjects.get(eventIndex)));

      }

      return allEvents;

    }

    /**
     * Gets a list of events that a user is attending
     * @param userId ID of user to get attending events from
     * @return List of events that the user is attending
     * @throws ParseException
     */
    public List<Event> getAttendingEventsByUser(String userId) throws ParseException {

       ParseQuery<ParseUser> query = ParseUser.getQuery();

        query.whereEqualTo(System.objectId, userId);

        List<ParseUser> foundUser = query.find();

        if (foundUser == null) {

            return null;

        }

        List<Event> eventList = (List<Event>)foundUser.get(0).get(System.attendingEvents);

        return eventList;

    }

    /**
     * Get's a list of events that a user is hosting
     * @param userId ID of user to get events from
     * @return List of events that the user is hosting
     * @throws ParseException
     */
    public List<Event> getHostingEventsByUser(String userId) throws ParseException {

        ParseQuery<ParseUser> query = ParseUser.getQuery();

        query.whereEqualTo(System.objectId, userId);

        List<ParseUser> foundUser = query.find();

        if (foundUser == null) {

            return null;

        }

        List<Event> eventList = (List<Event>)foundUser.get(0).get(System.hostingEvents);

        return eventList;

    }

    /**
     * Gets a user from the database, based on a user ID
     * @param userId ID of user to retreive from database
     * @return A user Object created from the parse user
     * @throws ParseException
     */
    public User getUser(String userId) throws ParseException {

        ParseQuery<ParseUser> query = ParseUser.getQuery();

        query.whereEqualTo(System.objectId, userId);

        List<ParseUser> foundUser = query.find();

        if (foundUser == null) {

            return null;

        }

        User userToReturn = new User(foundUser.get(0));

        return userToReturn;

    }

    public void updateUser(User userToUpdate) throws ParseException {

        ParseQuery<ParseUser> query = ParseUser.getQuery();

        query.whereEqualTo("objectId", userToUpdate.getUserID());

        List<ParseUser> loadedUsers = query.find();

        if (loadedUsers == null) {

            return;

        }

        ParseUser loadedUser = loadedUsers.get(0);

        loadedUser.put(System.objectId, userToUpdate.getUserID());
        loadedUser.put(System.name, userToUpdate.getName());
        loadedUser.put(System.attendingEvents, userToUpdate.getAttendeeEventList());
        loadedUser.put(System.hostingEvents, userToUpdate.getHostEventList());

    }

    public void updateEvent(Event eventToUpdate) throws ParseException {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");

        query.whereEqualTo(System.objectId, eventToUpdate.getEventID());

        List<ParseObject> loadedEvents = query.find();

        if (loadedEvents == null) {

            return;

        }

        ParseObject loadedEvent = loadedEvents.get(0);

        loadedEvent.put(System.objectId, eventToUpdate.getEventID());
        loadedEvent.put(System.name, eventToUpdate.getName());
        loadedEvent.put(System.hostId, eventToUpdate.getHost());
        loadedEvent.put(System.attendeeList, eventToUpdate.getAttendees());
        loadedEvent.put(System.date, eventToUpdate.getDate());
        loadedEvent.put(System.restrictionStatus, eventToUpdate.getRestrictionStatus());
        loadedEvent.put(System.genre, eventToUpdate.getGenre());
        loadedEvent.put(System.description, eventToUpdate.getDescription());

    }

    /**
     * Deletes an event from the database
     * @param eventId Event id to delete from database
     */
    public void deleteEvent(String eventId) {

        //create a new parse query for events
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");

        //query events with an id that match id to delete
        query.whereEqualTo("objectId", eventId);

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

                else {

                    Log.d("POTATO", "Crai there was an exception ;(");
                }
            }
        });
    }

    public void testAddEvent() {
        Log.d("test", "add event");
        addEventsToUser(EventType.HOSTING, 23);
        addEventsToUser(EventType.HOSTING, 45);
        addEventsToUser(EventType.ATTENDING, 86);
        addEventsToUser(EventType.ATTENDING, 24);


    }


}
