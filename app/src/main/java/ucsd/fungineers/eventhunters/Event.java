package ucsd.fungineers.eventhunters;

import android.content.Context;
import android.content.res.Resources;

import com.parse.ParseObject;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.io.Serializable;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Event implements Serializable{

    //The id that the event has in the database.
    private String eventID;

    //name of the event
    private String name;

    //The host who created the event.
    private String hostID;

    //The list of people attending the event.
    private ArrayList<String> attendees;

    //date and time of the event
    private GregorianCalendar date;

    //How restricted an event is. Check for explanation in User.java
    private RestrictionStatus restrictionStatus;

    //What kind of event it is. Check for explanation at bottom of this file.
    private Genre genre;

    //event description, as visible to attendees
    private String description;

    //event location
    private String location;

    //picture for the event
    private URL eventImage;

    public Event (ArrayList<String> attendees, String hostID,
                  RestrictionStatus restrictionStatus, Genre genre, String name,
                  String description, GregorianCalendar date, String location) {

        setAttendees(attendees);
        setHost(hostID);
        setRestrictionStatus(restrictionStatus);
        setGenre(genre);
        setName(name);
        setDescription(description);
        setDate(date);
        setLocation(location);

    }

    public Event (ParseObject parseEvent) {

        setEventID(parseEvent.getObjectId());
        setName((String) parseEvent.get(System.name));
        setHost((String) parseEvent.get(System.hostId));
        setAttendees((ArrayList<String>) parseEvent.get(System.attendeeList));
        setRestrictionStatus((RestrictionStatus) parseEvent.get(System.restrictionStatus));
        setGenre((Genre) parseEvent.get(System.genre));
        setDescription((String) parseEvent.get(System.description));
        setLocation((String) parseEvent.get(System.location));

        //Date is stored in database and converted to GregorianCalendar
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime((Date) parseEvent.get(System.date));
        setDate(calendar);


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
/*TODO FIND OUT WHAT THIS IS FOR
    public void setGenre(String genre) {

      Genre.fromString(genre, this);

    }*/

    public String getDescription() {

        return description;

    }

    public void setDescription(String description) {

        this.description = description;

    }

    public GregorianCalendar getDate()
    {
        return date;
    }

    public void setDate(GregorianCalendar date)
    {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
    MUSIC(0), PARTY(1);

    private final int value;
    private Genre(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    //Get a genre from an int.
    public static Genre fromInt(int value) {
        for (Genre genre : Genre.values()) {
            if (genre.getValue() == value) {
                return genre;
            }
        }
        //If it gets here then you derped.
        return null;
    }

    public static Genre fromString(String string, Context context)
    {
        Resources res = context.getResources();
        String[] arrayContents = res.getStringArray(R.array.spinnerItems);
        if (string.equals(arrayContents[1]))
        {
            return Genre.MUSIC;
        }

        else
        {
            return Genre.PARTY;
        }
    }


    @Override
    public String toString() {
        switch (this.value) {
            case 0 :
                return "Music";

            case 1 :
                return "Party";

            default:
                return "";
        }
    }
}