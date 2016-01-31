package ucsd.fungineers.eventhunters;

import java.util.ArrayList;

/**
 * This class will keep track of the host data.
 * For example, their upcoming events.
 */
public class Host extends User{

    /**
     * This method creates a new event. The host's upcoming list must be updated, and the system
     * must be notified to write to the database. Use Main.system to access.
     * @return true if successful. May replace with multiple error system.
     */
    public boolean createEvent()
    {
        //Use Main.System to update the list.
        return true;
    }

    /**
     * Test ctor to make attendees. Perhaps for new users?
     * Really though you should be using the other ctor.
     */
    public Host() {
    }

    /**
     * User ctor. Called from system to create users from database.
     */
    public Host(String name,
                    ArrayList<Event> upcomingEventList,
                    ArrayList<Event> pastEventList,
                    RestrictionStatus restrictionStatus,
                    float rating,
                    int userID)
    {
        super(name, upcomingEventList, pastEventList, restrictionStatus, rating, userID);
    }
}
