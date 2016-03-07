package ucsd.fungineers.eventhunters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * This class will communicate with the database and will be how to go from id to object.
 * Remember how the last PA, the tree on disk worked for CSE 12? It'll be kinda like that.
 *
 * Use main.System to access.
 */
public class System {

    //public static String objectId = "objectId";
    public static String name = "Name";
    public static String hostId = "HostID";
    public static String attendeeList = "AttendeesList";
    public static String date = "Date";
    public static String restrictionStatus = "Restriction";
    public static String genre = "Genre";
    public static String description = "Description";
    public static String location = "Location";

    public static String attendingEvents = "AttendingEvents";
    public static String hostingEvents = "HostingEvents";

    public static String attendeeRating = "AttendeeRating";
    public static String totalAttendeeRatingVotes = "TotalAttendeeRatingVotes";

    public static String hostRating = "HostRating";
    public static String totalHostRatingVotes = "TotalHostRatingVotes";

    public String username;

    private Event storedEvent;

    private List<Event> loadedEvents;
    private List<Event> loadedHostingEvents;
    private List<Event> loadedAttendingEvents;


    final int MAX_SCORE = 5;

    static System instance;

    private ParseUser currentParseUser;
    public static User currentUser;

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
        loadedEvents = new ArrayList<Event>();
        if(instance == null) {
            fbLogin(activity);
        }

        try {
            loadAllEvents();
        } catch (ParseException e) {
            e.printStackTrace();
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

        //Asking permissions seems to cause the parse user to not be null.
        List<String> permissions = Arrays.asList("public_profile",
                "user_friends");

        ParseFacebookUtils.logInWithReadPermissionsInBackground(activity, permissions, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (user == null) {
                    Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                    return;
                } else if (user.isNew()) {
                    Log.d("MyApp", "User signed up and logged in through Facebook!");
                    currentParseUser.put(System.restrictionStatus, RestrictionStatus.OVER_21.getValue());
                    currentParseUser.saveInBackground();

                    currentUser = new User(currentParseUser);
                    getFBInfo();

                } else {
                    Log.d("MyApp", "User logged in through Facebook!");
                    currentParseUser = user;

                    Log.d("HELPUSER", currentParseUser.toString());
                    Log.d("HELPUSER", currentParseUser.getObjectId());

                    currentUser = new User(currentParseUser);
                    Log.d("HELPUSER", currentUser.toString());

                }

                ParseACL defaultACL = new ParseACL();
// Optionally enable public read access while disabling public write access.
                defaultACL.setPublicReadAccess(true);
                defaultACL.setPublicWriteAccess(true);
                ParseACL.setDefaultACL(defaultACL, true);

                try {
                    loadAllEvents();
                } catch (ParseException e) {
                    e.printStackTrace();
                }



//                testAddEvent();
            }
        });
    }

    private void getFBInfo()
    {
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name,picture");



        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me",
                parameters,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
         /* handle the result */
                        try {


                            username = response.getJSONObject().getString("name");


                            JSONObject picture = response.getJSONObject().getJSONObject("picture");
                            JSONObject data = picture.getJSONObject("data");
                            //  Returns a 50x50 profile picture
                            String pictureUrl = data.getString("url");

                            new ProfilePicAsync(pictureUrl).execute();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
    }

    public void saveNewUser()
    {
        //TODO: Initialize Fields
        currentParseUser = ParseUser.getCurrentUser();
        currentParseUser.put(System.name, username);
        currentParseUser.put(System.attendingEvents, new ArrayList<Integer>());
        currentParseUser.put(System.hostingEvents, new ArrayList<Integer>());
        currentParseUser.put(System.attendeeRating, 0);
        currentParseUser.put(System.hostRating,0);
        currentParseUser.put(System.restrictionStatus, 0);
        currentParseUser.put(System.totalAttendeeRatingVotes,0);
        currentParseUser.put(System.totalHostRatingVotes, 0);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bitmap = ProfilePicAsync.bitmap;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] data = stream.toByteArray();
        String thumbName = currentParseUser.getUsername().replaceAll("\\s+", "");
        final ParseFile parseFile = new ParseFile(thumbName + "_thumb.jpg", data);

        currentParseUser.put("profileThumb", parseFile);
        //currentParseUser.saveInBackground();
        //currentUser = new User(currentParseUser);
        currentParseUser.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                currentUser = new User(currentParseUser);
            }
        });
    }

    private void testAddRating()
    {
        try {
            rateUser(currentUser,3,RatingType.ATTENDEE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<String> getEventsFromUser(EventType type)
    {
        List<String> array = new ArrayList<String>();

        if(type == EventType.ATTENDING)
        {
            array = currentParseUser.getList(System.attendingEvents);
        }
        else
        {
            array = currentParseUser.getList(System.hostingEvents);
        }

        return array;
    }

    /* Adds an event to the currently logged in user
     * @param type Type of event (Hosting or Attending)
     * @param eventID ID of event
     */
    public void addEventsToUser (EventType type, String eventID)
    {
        List<String> array = getEventsFromUser(type);

        array.add(eventID);

        if(type == EventType.ATTENDING)
        {
            currentParseUser.put(System.attendingEvents, array);
        }
        else
        {
            currentParseUser.put(System.hostingEvents, array);
        }
        currentParseUser.saveInBackground();
    }

    enum EventType {HOSTING, ATTENDING};

    /**
     * Creates an event in the database based on an Event object
     * @param event event to create a ParseObject from and put in database
     */
    public void createEvent(Event event, final Intent i, final CreateEvent evtClass)
    {
        final ParseObject dbEvent = new ParseObject("Events");

        dbEvent.put(System.name, event.getName());
        dbEvent.put(System.hostId, event.getHost());
        dbEvent.put(System.attendeeList, event.getAttendees());
        dbEvent.put(System.date, event.getDate().getTime());
        dbEvent.put(System.location, event.getLocation());
        dbEvent.put(System.restrictionStatus, event.getRestrictionStatus().toString());
        dbEvent.put(System.genre, event.getGenre().toString());
        dbEvent.put(System.description, event.getDescription());

        storedEvent = event;

        dbEvent.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {


                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime((Date) dbEvent.get(System.date));

                Event evt = new Event((ArrayList) dbEvent.get(System.attendeeList),
                        (String) dbEvent.get(System.hostId), RestrictionStatus.fromString((String) dbEvent.get(System.restrictionStatus)),
                        Genre.fromString((String) dbEvent.get(System.genre)),
                        (String) dbEvent.get(System.name),
                        (String) dbEvent.get(System.description),
                        calendar,
                        (String) dbEvent.get(System.location));

                evt.setEventID(dbEvent.getObjectId());
                i.putExtra("EventKey", evt);

                RestrictionStatus.fromString((String) dbEvent.get(System.restrictionStatus));
                evtClass.startActivity(i);
                //Need to set the event's id at some point.
                storedEvent.setEventID(dbEvent.getObjectId());
                addEventsToUser(EventType.HOSTING, storedEvent.getEventID());

            }
        });


     //   Log.d("EVENTS WHOA", "Event created. ID: " + event.getEventID());

    }

    /**
     * Gets an event from the database, based on an ID
     * @param id ID of event to get
     * @return Event Object created from ParseObject
     * @throws ParseException
     */
    public Event getEvent(String id) throws ParseException {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");

        query.get(id);

        List<ParseObject> foundEvent = query.find();

        if (foundEvent == null) {

            return null;

        }

        Event eventToReturn = new Event(foundEvent.get(0));

        return eventToReturn;
    }

    public void setLoadedEvents(List<ParseObject> events) {

        List<Event> newList = new ArrayList<Event>();

        for (int i = 0; i < events.size(); i++) {

            newList.add(new Event(events.get(i)));

        }

      loadedEvents = newList;

    }

    /**
     * Gets all events matching the restriction status
     * @return A list of events matching the restriction status
     */
    public void loadAllEvents() throws ParseException {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");

        //get the first matching item
        query.findInBackground(new FindCallback<ParseObject>() {//when the callback is completed.
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                //if there was no exception
                if (e == null) {

                    Log.d("ALL EVENTS: ", "" + objects.size());
                    loadedEvents.clear();
                    setLoadedEvents(objects);

                    try {
                        loadHostingEvents();
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }

                } else {

                    Log.d("POTATO", "Crai there was an exception ;(");
                }
            }
        });
    }

    public void loadHostingEvents() throws ParseException {


    }

    public void loadAttendingEvents() throws ParseException {


    }

    public List<Event> getAllEvents(RestrictionStatus restriction)  {

        Log.d("LOADED EVENT SIZE: ", "" + loadedEvents.size());
        List<Event> allEvents = new ArrayList<Event>();

        for (int i = 0; i < loadedEvents.size(); i++) {

            if (currentUser.getRestrictionStatus().compareTo(loadedEvents.get(i).getRestrictionStatus()) >= 0) {

                allEvents.add(loadedEvents.get(i));

            }
        }

        return allEvents;
    }

    /**
     * Gets a user from the database, based on a user ID
     * @param userId ID of user to retreive from database
     * @return A user Object created from the parse user
     * @throws ParseException
     */
    public User getUser(String userId) throws ParseException {

        ParseQuery<ParseUser> query = ParseUser.getQuery();

        ParseObject newEvent = query.get(userId);

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

        loadedUser.put(System.name, userToUpdate.getName());
        loadedUser.put(System.attendingEvents, userToUpdate.getAttendeeEventList());
        loadedUser.put(System.hostingEvents, userToUpdate.getHostEventList());
        loadedUser.put(System.attendeeRating,userToUpdate.getAttendeeRating());
        loadedUser.put(System.totalAttendeeRatingVotes,userToUpdate.getTotalAttendeeVotes());
        loadedUser.put(System.hostRating,userToUpdate.getHostRating());
        loadedUser.put(System.totalHostRatingVotes, userToUpdate.getTotalHostVotes());
    }

    public void updateEvent(Event eventToUpdate) throws ParseException {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");

        ParseObject loadedEvent = query.get(eventToUpdate.getEventID());

        if (loadedEvent == null) {

            return;

        }

        loadedEvent.put(System.name, eventToUpdate.getName());
        loadedEvent.put(System.hostId, eventToUpdate.getHost());
        loadedEvent.put(System.attendeeList, eventToUpdate.getAttendees());
        loadedEvent.put(System.date, eventToUpdate.getDate().getTime());
        loadedEvent.put(System.restrictionStatus, eventToUpdate.getRestrictionStatus().toString());
        loadedEvent.put(System.genre, eventToUpdate.getGenre().toString());
        loadedEvent.put(System.description, eventToUpdate.getDescription());
        loadedEvent.put(System.location,eventToUpdate.getLocation());

        loadedEvent.saveInBackground();

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
                } else {

                    Log.d("POTATO", "Crai there was an exception ;(");
                }
            }
        });
    }

    public void testAddEvent() {
        /*
        Log.d("test", "add event");
        addEventsToUser(EventType.HOSTING, 23);
        addEventsToUser(EventType.HOSTING, 45);
        addEventsToUser(EventType.ATTENDING, 86);
        addEventsToUser(EventType.ATTENDING, 24);
        */

    }

    public void rateUser(User user, int rating, RatingType type) throws ParseException {
        float total;
        float newRating;
        if(type == RatingType.ATTENDEE)
        {
            total = user.attendeeRating * user.totalAttendeeVotes;
        }
        else
        {
            total = user.hostRating * user.totalHostVotes;
        }
        newRating = (total + rating)/MAX_SCORE;

        if(type == RatingType.ATTENDEE)
        {
            user.attendeeRating = newRating;
            user.totalAttendeeVotes++;
        }
        else
        {
            user.hostRating = newRating;
            user.totalHostVotes++;
        }

        updateUser(user);

        return;
    }

    enum RatingType {
        HOST(0), ATTENDEE(1);

        private final int value;
        private RatingType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
