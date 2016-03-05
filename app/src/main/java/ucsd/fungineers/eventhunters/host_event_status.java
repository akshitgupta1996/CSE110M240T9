package ucsd.fungineers.eventhunters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class host_event_status extends AppCompatActivity {
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_event_status);
    }*/
    private TextView title;
    private TextView subtitle;
    private TextView date;
    private TextView time;
    private TextView location;
    private TextView genre;
    private TextView restriction;
    private TextView description;
    private TextView attendeeNum;
    //private RatingBar eventRatingBar;
    private Event event;

    //private boolean editable = true;
    private static final String TAG = host_event_status.class.getSimpleName();
    private static final SimpleDateFormat mDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.US);
    private static final SimpleDateFormat mTimeFormat = new SimpleDateFormat("h:mm aa",Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_event_status);

         event = (Event) getIntent().getSerializableExtra("EventKey");
        //editable = getIntent().getBooleanExtra(getString(R.string.KEY_EVENT_EDITABLE), false);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = (TextView) findViewById(R.id.event_title);
        subtitle = (TextView) findViewById(R.id.event_subtitle);
        date = (TextView) findViewById(R.id.event_date);
        time = (TextView) findViewById(R.id.event_time);
        location = (TextView) findViewById(R.id.event_location);
        genre = (TextView) findViewById(R.id.event_genre);
        restriction = (TextView) findViewById(R.id.event_restriction);
        description = (TextView) findViewById(R.id.event_description);
        attendeeNum = (TextView) findViewById(R.id.event_attendeeNum);
        //eventRatingBar = (RatingBar) findViewById(R.id.event_rating_bar);
/*
        title.setText(getIntent().getExtras().getString("eventName"));
        subtitle.setText("Hosted by " + getIntent().getExtras().getString("eventName"));
        //date.setText(mDateFormat.format(getIntent().getExtras().getString("eventName")));
        //time.setText(mTimeFormat.format(getIntent().getExtras().getString("eventName")));
        location.setText(getIntent().getExtras().getString("eventLocation"));
        genre.setText(getIntent().getExtras().getString("eventGenre"));
        restriction.setText(getIntent().getExtras().getString("eventRestriction"));
        description.setText(getIntent().getExtras().getString("eventDescription"));
*/
        title.setText(event.getName());
        subtitle.setText("Hosted by " + event.getHost());
        date.setText(mDateFormat.format(event.getDate().getTime()));
        time.setText(mTimeFormat.format(event.getDate().getTime()));
        location.setText(event.getLocation());
        genre.setText(event.getGenre().toString());
        restriction.setText(event.getRestrictionStatus().toString());
        description.setText(event.getDescription());
        attendeeNum.setText("" + event.getAttendees().size());
        /*
        eventRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // TODO save the newer set rating for this user
            }
        });
        */
        ImageView closeButton = (ImageView) findViewById(R.id.close_activity_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_status_menu, menu);

        /*if (editable) {
            menu.removeItem(R.id.action_event_delete);
            menu.removeItem(R.id.action_event_edit);
        }*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_event_delete :
                // TODO delete this event
                //.makeText(host_event_status.this, "TODO Delete event", Toast.LENGTH_SHORT).show();
                Log.i("clicks", "Delete Event");
                DialogInterface.OnClickListener delete_clickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int id) {
                        switch (id) {
                            case DialogInterface.BUTTON_POSITIVE:
                                System.instance.deleteEvent(event.getEventID());
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder delete_b = new AlertDialog.Builder(this);
                delete_b.setMessage("Are you sure you want to delete this event?")
                        .setTitle("Delete Event")
                        .setPositiveButton("Yes", delete_clickListener)
                        .setNegativeButton("No", delete_clickListener)
                        .show();
                break;
            case R.id.action_event_edit :
                // TODO edit this event
                //Toast.makeText(host_event_status.this, "TODO Edit event", Toast.LENGTH_SHORT).show();
                Log.i("clicks", "Edit Event");
                DialogInterface.OnClickListener edit_clickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int id) {
                        switch (id) {
                            case DialogInterface.BUTTON_POSITIVE:
                                //i.putExtra("EventKey", newEvent);
                                //System.instance.createEvent(newEvent);
                                //startActivity(i);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder edit_b = new AlertDialog.Builder(this);
                edit_b.setMessage("Are you sure you want to edit this event?")
                        .setTitle("Edit Event")
                        .setPositiveButton("Yes", edit_clickListener)
                        .setNegativeButton("No", edit_clickListener)
                        .show();
                break;
            case R.id.action_event_home :
                // TODO jump to home
                //Toast.makeText(host_event_status.this, "TODO jump to home", Toast.LENGTH_SHORT).show();
                Log.i("clicks", "Home");
                DialogInterface.OnClickListener home_clickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int id) {
                        switch (id) {
                            case DialogInterface.BUTTON_POSITIVE:
                                //i.putExtra("EventKey", newEvent);
                                //System.instance.createEvent(newEvent);
                                //startActivity(i);
                                Intent i = new Intent(host_event_status.this, Main.class);
                                startActivity(i);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder home_b = new AlertDialog.Builder(this);
                home_b.setMessage("Are you sure you want to go to the Home Page?")
                        .setTitle("Home Page")
                        .setPositiveButton("Yes", home_clickListener)
                        .setNegativeButton("No", home_clickListener)
                        .show();
            default:
                break;
        }

        return true;
    }
}
