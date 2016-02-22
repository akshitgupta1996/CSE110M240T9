package ucsd.fungineers.eventhunters;

import android.util.Log;

import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * This abstract class will be extended by both the host and the attendee.
 * The instance variables will have different meanings based on whichever it is.
 */
public class User {

    //The name of the person. Just a string, no worrying about first/last name.
    String name;

    //The id that the user has in the database.
    String userID;

    //Upcoming events this user is hosting.
    ArrayList<Event> hostEventList;

    //Upcoming events this user is hosting.
    ArrayList<Event> attendeeEventList;

    //What the status of the users age is. Hosts cannot make events that are higher restriction
    RestrictionStatus restrictionStatus;

    //Rating of the user as a host
    float hostRating;

    //Rating of the user as an attendee
    float attendeeRating;

    //Other misc data? Photo? Description? Birthday?

    /**
     * A ctor that gets everything. Great for creating from the database.
     *
     * @param name
     * @param userID
     */
    public User(String name,
                String userID) {

        setName(name);
        setUserID(userID);

    }

    /**
     * Just for testing. Not really legitimate.
     */
    public User() {

    }

    public User(String userID) {

    }

    /**
     * Creates a user object out of a parse user
     * @param user The ParseUser passed in
     */
    public User(ParseUser user) {

        setName((String) user.get("Name"));
        setUserID((String) user.get("UserID"));

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getName() {

        return name;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {

        return userID;

    }

    public boolean createEvent() {
        //Event ev = new Event(new ArrayList<User>(), this, 10, RestrictionStatus.NO_RESTRICTIONS, Genre.PARTY, "Test Event");
        //Main.system.tempEventList.add(ev);

        //Use Main.System to update the list.
        return true;
    }

    public boolean createEvent(RestrictionStatus r, Genre g, String name) {
        //Event ev = new Event(new ArrayList<User>(), this, 10, r, g, name);
        //Main.system.tempEventList.add(ev);

        //Use Main.System to update the list.
        return true;
    }

    /**
     * This method adds this attendee to the event, provided their restriction level is adequate.
     * Note: Even though the event is changed, System must be informed to write to the database.
     * Use Main.system to access.
     * Haven't fully decided how to do that.
     *
     * @param eventToJoin The event to join
     * @return true if successful. May replace with multiple error system.
     */
    public boolean joinEvent(Event eventToJoin) {
        if (restrictionStatus.compareTo(eventToJoin.getRestrictionStatus()) >= 0) {
            Log.d("User", "Joining Event");
            attendeeEventList.add(eventToJoin);
            //eventToJoin.getAttendees().add(this);
        } else {
            Log.d("User", "Fail to join event");

        }
        //Use Main.system to update.
        return true;
    }

    @Override
    public String toString() {
        return name + ": id: " + userID + ": Attendee Rating: " + attendeeRating + " Restriction Status: " + restrictionStatus + ": Host Rating: " + hostRating;
    }
}

/**
 * This enum keeps track of how restricted an event is.
 * It acts like a class.
 */
enum RestrictionStatus {
    NO_RESTRICTIONS(0), UNDER_18(1), UNDER_21(2);

    private final int value;
    private RestrictionStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}