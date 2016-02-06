package ucsd.fungineers.eventhunters;

/**
 * This abstract class will be extended by both the host and the attendee.
 * The instance variables will have different meanings based on whichever it is.
 */
public class User {

    //The name of the person. Just a string, no worrying about first/last name.
    String name;

    //The id that the user has in the database.
    int userID;

    //All the attendee information.
    AttendeeComponent attendeeComponent;

    //All the host information.
    HostComponent hostComponent;

    //Other misc data? Photo? Description? Birthday?

    /**
     * A ctor that gets everything. Great for creating from the database.
     * @param name
     * @param userID
     */
    public User(String name,
                    int userID)
    {
        this.name = name;
        this.userID = userID;
    }

    /**
     * Just for testing. Not really legitimate.
     */
    public User()
    {

    }

    public User(int userID)
    {
        //System call to get name.

        //System calls to load everything else.
        hostComponent = new HostComponent(userID);
        attendeeComponent = new AttendeeComponent(userID);
    }

    @Override
    public String toString()
    {
        return name + ": id: " + userID;
    }
}


/**
 * This enum keeps track of how restricted an event is.
 * It acts like a class.
 */
enum RestrictionStatus{
    NO_RESTRICTIONS, UNDER_18, UNDER_21;
}
