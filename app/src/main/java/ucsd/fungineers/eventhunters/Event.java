package ucsd.fungineers.eventhunters;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class Event {

    //The id that the event has in the database.
    private String eventID;

    //name of the event
    private String name;

    //The host who created the event.
    private String hostID;

    //The list of people attending the event.
    private ArrayList<String> attendees;

    //date and time of the event
    private Date date;

    //How restricted an event is. Check for explanation in User.java
    private RestrictionStatus restrictionStatus;

    //What kind of event it is. Check for explanation at bottom of this file.
    private Genre genre;

    //event description, as visible to attendees
    private String description;

    //picture for the event
    private URL eventImage;

    public Event (ArrayList<String> attendees, String hostID, String eventID,
                  RestrictionStatus restrictionStatus, Genre genre, String name,
                  String description, Date date) {

        setAttendees(attendees);
        setHost(hostID);
        setEventID(eventID);
        setRestrictionStatus(restrictionStatus);
        setGenre(genre);
        setName(name);
        setDescription(description);

    }

    /*
     * Gets a list of attendee ID's
     */
    public ArrayList<String> getAttendees() {
        return attendees;
    }

    /*
     * Sets the current list of attendee ID's by a passed in ArrayList of attendee ID's
     */
    public void setAttendees(ArrayList<String> attendees) {
        this.attendees = attendees;
    }

    /*
     * Gets the current host ID for this event
     */
    public String getHost() {
        return hostID;
    }

    /*
     * Set's the host ID for this event, based on a passed in ID string
     */
    public void setHost(String hostID) {
        this.hostID = hostID;
    }

    /*
     * Get's the name of this event, as a String
     */
    public String getName() {
        return name;
    }

    /*
     * Sets the name of this event to a passed in String
     */
    public void setName(String name) {

        this.name = name;
    }

    /*
     * Gets the ID of this event as a String
     */
    public String getEventID() {
        return eventID;
    }

    /*
     * Sets the ID of this event, based on a passed in String
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    /*
     * Gets the restriction status of this event
     */
    public RestrictionStatus getRestrictionStatus() {
        return restrictionStatus;
    }

    /*
     * Sets the restriction status of this event
     */
    public void setRestrictionStatus(RestrictionStatus restrictionStatus) {
        this.restrictionStatus = restrictionStatus;
    }

    public Genre getGenre() {

        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDescription() {

        return description;

    }

    public void setDescription(String description) {

        this.description = description;

    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return getName() + ": Restriction: " + getRestrictionStatus() + " Genre: " + getGenre() +
                           " EventID: " + getEventID() + " Host: " + hostID;
    }
}

/**
 * This enum keeps track of the types of events possible. One per event!
 * TODO: expand to tag system. Event can have multiple tags. Easier to search.
 */
enum Genre{
    MUSIC, PARTY;
}