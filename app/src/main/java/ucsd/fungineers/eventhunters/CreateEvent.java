package ucsd.fungineers.eventhunters;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


public class CreateEvent extends AppCompatActivity {
    //this variable tells if the event is old or not. If it is old, we need to load the old data and update.
    private boolean isOldEvent = false;

    //This is something to do with dates
    private static final String TAG = CreateEvent.class.getSimpleName();
    private TextView mDatePicker;
    private TextView mTimePicker;

    private GregorianCalendar mDate;
    private static final SimpleDateFormat mDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.US);
    private static final SimpleDateFormat mTimeFormat = new SimpleDateFormat("h:mm aa",Locale.US);

    //This is the event of this form
    private Event newEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createevent);
        /*eventDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                public void showStartDateDialog(View v){
                DialogFragment dialogFragment = new StartDatePicker();
                dialogFragment.show(getFragmentManager(), "start_date_picker");
            }
            }
        });*/

        initDateAndTimePickers();

        /*If the event is actually old, we need to update instead of create a new event. */
        if(isOldEventPage(getIntent().getExtras()))
        {
            newEvent = (Event)getIntent().getExtras().getSerializable("EventKey");
           loadOldEventToForm(newEvent);

        }
    }

    private void initDateAndTimePickers() {
        mDatePicker = (TextView) findViewById(R.id.create_event_date_picker);
        mDate = new GregorianCalendar();
        mDatePicker.setText(mDateFormat.format(mDate.getTime()));

        mDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                showDatePickerDialog();
            }
        });


        mTimePicker = (TextView) findViewById(R.id.create_event_time_picker);
        // TODO set the default value for the time in mDate and update the time picker's text
        //mDate = new GregorianCalendar();
        mTimePicker.setText(mTimeFormat.format(mDate.getTime()));


        mTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                showTimePickerDialog();
            }
        });
    }

/*If the create button is clicked, we go here.*/
    public void button_Click(View view) {

        String button_name = ((Button) view).getText().toString();
        if (button_name.equals("Add Event")) {
            Log.i("clicks", "Add Event");

            if(isOldEvent == true)
            {
                //Call Update Old Event Script
                try {
                    EditText eventName = (EditText) findViewById(R.id.field_Name);
                    // TODO add checks for date

            /*This is where we grab the ID's for the form elements so we can change them*/
                    EditText eventLocation = (EditText) findViewById(R.id.field_Location);
                    Spinner eventGenre = (Spinner) findViewById(R.id.field_Spinner_Genre);
                    RadioGroup eventRestriction = (RadioGroup) findViewById(R.id.radio_Restriction);
                    EditText eventDescription = (EditText) findViewById(R.id.field_Description);
                    int radioId = eventRestriction.getCheckedRadioButtonId();
                    RadioButton selectedID = (RadioButton) findViewById(radioId);
                    Event x = new Event(new ArrayList<String>(),
                            System.currentUser.userID, RestrictionStatus.fromString(selectedID.getText().toString()),
                            Genre.fromString(eventGenre.getSelectedItem().toString(), this),
                            eventName.getText().toString(),
                            eventDescription.getText().toString(),
                            mDate,
                            eventLocation.getText().toString());
                            x.setHost(newEvent.getHost());
                            x.setEventID(newEvent.getEventID());
                    System.instance.updateEvent(x);
                    Log.d("Updated lol", x.getLocation());
                }
                catch( Exception e)
                {
Log.d("Error Updating",e.getMessage());
                }

              return;
            }
            EditText eventName = (EditText) findViewById(R.id.field_Name);
            // TODO add checks for date

            /*This is where we grab the ID's for the form elements so we can change them*/
            EditText eventLocation = (EditText) findViewById(R.id.field_Location);
            Spinner eventGenre = (Spinner) findViewById(R.id.field_Spinner_Genre);
            RadioGroup eventRestriction = (RadioGroup) findViewById(R.id.radio_Restriction);
            EditText eventDescription = (EditText) findViewById(R.id.field_Description);
            int radioId = eventRestriction.getCheckedRadioButtonId();
            RadioButton selectedID = (RadioButton) findViewById(radioId);

            /*Error checking, because we should only create the new form if all the required data was filled in.*/
            if (selectedID != null
                    && !eventName.getText().toString().isEmpty()
                    && !eventLocation.getText().toString().isEmpty()
                    && !eventGenre.getSelectedItem().toString().equals("Genre")
                    && !eventRestriction.toString().isEmpty()
                    && !eventDescription.getText().toString().isEmpty()
                    ) {


                final Intent i = new Intent(this, host_event_status.class);

                Log.d("ASDFGHJKL", System.currentUser.toString());
                newEvent = new Event(new ArrayList<String>(),
                        System.currentUser.userID, RestrictionStatus.fromString(selectedID.getText().toString()),
                        Genre.fromString(eventGenre.getSelectedItem().toString(), this),
                        eventName.getText().toString(),
                        eventDescription.getText().toString(),
                        mDate,
                        eventLocation.getText().toString());

               final CreateEvent x = this;
                DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int id) {
                        switch (id) {
                            case DialogInterface.BUTTON_POSITIVE:

                                i.putExtra("EventKey", newEvent);
                               System.instance.createEvent(newEvent, i, x);
                             //   startActivity(i);
                                finish();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                /*This is the message asked to the user.*/
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("Are you sure you want to create this event?")
                        .setTitle("Event Creation Confirmation")
                        .setPositiveButton("Yes", clickListener)
                        .setNegativeButton("No", clickListener)
                        .show();
            }

            /*This happens if the user has not filled in all the form data*/
            else
            {
                DialogInterface.OnClickListener failedclickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int id) {
                        //do nothing because we should not create event if there isnt the required info.
                    }
                };

                /*The message to display to the user.*/
                AlertDialog.Builder failed = new AlertDialog.Builder(this);
                failed.setMessage("Please fill in all fields.")
                        .setTitle("Failed to Create Event")
                        .setNegativeButton("OK", failedclickListener)
                        .show();
            }
        }
    }

    private void showDatePickerDialog() {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.d(TAG, "Date selected: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                mDate.set(year, monthOfYear, dayOfMonth);
                mDatePicker.setText(mDateFormat.format(mDate.getTimeInMillis()));
            }
        }, mDate.get(Calendar.YEAR), mDate.get(Calendar.MONTH), mDate.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        dialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.d(TAG, "Time selected: " + hourOfDay + ":" + minute);
                mDate.set(mDate.get(Calendar.YEAR), mDate.get(Calendar.MONTH), mDate.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
                mTimePicker.setText(mTimeFormat.format(mDate.getTimeInMillis()));
            }
        }, mDate.get(Calendar.HOUR_OF_DAY), mDate.get(Calendar.MINUTE), false);
        timePickerDialog.show();
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean isOldEventPage(Bundle data)
    {
        if(data == null) {
            isOldEvent = false;
            return false;
        }
        else {
            isOldEvent = true;
            return true;
        }
    }

    private void loadOldEventToForm(Event e)
    {
        /*These are the form elements we are going to change*/
        EditText eventName = (EditText) findViewById(R.id.field_Name);
        EditText eventLocation = (EditText) findViewById(R.id.field_Location);
        Spinner eventGenre = (Spinner) findViewById(R.id.field_Spinner_Genre);
        RadioGroup eventRestriction = (RadioGroup) findViewById(R.id.radio_Restriction);
        EditText eventDescription = (EditText) findViewById(R.id.field_Description);
        int radioId = eventRestriction.getCheckedRadioButtonId();
      //  RadioButton selectedID = (RadioButton) findViewById(radioId);
        eventName.setText(e.getName());
        eventLocation.setText(e.getLocation());
        eventGenre.setSelection(e.getGenre().getValue());
        eventDescription.setText(e.getDescription());
        eventRestriction.check(getRadioButtonID(e));
    }
    private int getRadioButtonID(Event e)
    {
        if (e.getRestrictionStatus() == RestrictionStatus.NO_RESTRICTIONS)
        {
            return R.id.ratio_NoRestrictions;
        }
        else if(e.getRestrictionStatus() == RestrictionStatus.OVER_18)
        {
            return R.id.radio_18Plus;
        }
        else if (e.getRestrictionStatus() == RestrictionStatus.OVER_21)
        {
            return R.id.radio21Plus;
        }

        return 0;
    }
    /*void createEvent()
    {
        final Intent i = new Intent(this, host_event_status.class);
    }*/
}


