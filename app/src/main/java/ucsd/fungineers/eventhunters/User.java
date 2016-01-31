package ucsd.fungineers.eventhunters;

import java.util.ArrayList;

/**
 * This abstract class will be extended by both the host and the attendee.
 * The instance variables will have different meanings based on whichever it is.
 */
public abstract class User {

    //The name of the person. Just a string, no worrying about first/last name.
    String name;

    //Upcoming events, either hosting or attending.
    ArrayList<Event> upcomingEventList;

    //Previous events, either hosted or attended.
    ArrayList<Event> pastEventList;

    //What the status of the users age is. Hosts cannot make events that are higher restriction,
    //attendees cannot join
    RestrictionStatus restrictionStatus;

    //Rating of the user, either as a host
    float rating;

    //The id that the user has in the database.
    int userID;

    //Other misc data? Photo? Description? Birthday?

    /**
     * A ctor that gets everything. Great for creating from the database.
     * @param name
     * @param upcomingEventList
     * @param pastEventList
     * @param restrictionStatus
     * @param rating
     * @param userID
     */
    public User(String name,
                    ArrayList<Event>upcomingEventList,
                    ArrayList<Event> pastEventList,
                    RestrictionStatus restrictionStatus,
                    float rating,
                    int userID)
    {
        this.name = name;
        this.upcomingEventList = upcomingEventList;
        this.pastEventList = pastEventList;
        this.restrictionStatus = restrictionStatus;
        this.rating = rating;
        this.userID = userID;
    }

    /**
     * Just for testing. Not really legitimate.
     */
    public User()
    {

    }
}


/**
 * This enum keeps track of how restricted an event is.
 * It acts like a class.
 */
enum RestrictionStatus{
    NO_RESTRICTIONS, UNDER_18, UNDER_21;
}
