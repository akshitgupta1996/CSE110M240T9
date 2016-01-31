package ucsd.fungineers.eventhunters;

import android.util.Log;

import java.util.ArrayList;

/**
 * This class will keep track of the attendee data.
 * For example, their upcoming events
 */
public class


        Attendee extends User{

    /**
     * This method adds this attendee to the event, provided their restriction level is adequate.
     * Note: Even though the event is changed, System must be informed to write to the database.
     * Use Main.system to access.
     * Haven't fully decided how to do that.
     * @param eventToJoin The event to join
     * @return true if successful. May replace with multiple error system.
     */
    public boolean joinEvent(Event eventToJoin)
    {
        //Use Main.system to update.
        return true;
    }

    /**
     * Test ctor to make attendees. Perhaps for new users?
     * Really though you should be using the other ctor.
     */
    public Attendee()
    {
        super();

    }

    /**
     * Attendee ctor. Called from system to create attendees from database.
     */
    public Attendee(String name,
            ArrayList<Event>upcomingEventList,
            ArrayList<Event> pastEventList,
            RestrictionStatus restrictionStatus,
            float rating,
            int userID)
    {
        super(name, upcomingEventList, pastEventList, restrictionStatus, rating, userID);
    }
}
