package ucsd.fungineers.eventhunters;

import android.util.Log;

import java.util.ArrayList;

/**
 * This class will communicate with the database and will be how to go from id to object.
 * Remember how the last PA, the tree on disk worked for CSE 12? It'll be kinda like that.
 *
 * Use main.System to access.
 */
public class System {

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
    public System()
    {
        //How to print example. In logcat, search your label.
        Log.d("test", "System created!");
        tempEventList = new ArrayList<Event>();
        tempUserList  = new ArrayList<User>();
        connect();

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
}
