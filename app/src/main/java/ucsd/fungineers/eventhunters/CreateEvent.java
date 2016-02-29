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
import java.util.Calendar;
import java.util.Locale;


public class CreateEvent extends AppCompatActivity {

    private static final String TAG = CreateEvent.class.getSimpleName();
    private TextView mDatePicker;
    private TextView mTimePicker;

    private Calendar mDate;
    //private Calendar mTimerDate;
    private static final SimpleDateFormat mDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.US);
    private static final SimpleDateFormat mTimeFormat = new SimpleDateFormat("h:mm aa",Locale.US);

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
    }

    private void initDateAndTimePickers() {
        mDatePicker = (TextView) findViewById(R.id.create_event_date_picker);
        mDate = Calendar.getInstance();
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
        mDate = Calendar.getInstance();
        mTimePicker.setText(mTimeFormat.format(mDate.getTime()));


        mTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                showTimePickerDialog();
            }
        });
    }

    public void button_Click(View view) {

        String button_name = ((Button) view).getText().toString();
        if (button_name.equals("Add Event")) {
            Log.i("clicks", "Add Event");


/*
            LinearLayout l = (LinearLayout) findViewById(R.id.base);
            for (int i = 0; i < l.getChildCount(); i++) {
                if(l.getChildAt(i) instanceof TextView)
                {
                    EditText eventInfo = (EditText)l.getChildAt(i);

                } else if (l.getChildAt(i) instanceof Spinner)
                {

                }
                else if (l.getChildAt(i) instanceof RadioGroup)
                {

                }
            }*/

            EditText eventName = (EditText) findViewById(R.id.field_Name);
            // TODO add checks for date

            EditText eventLocation = (EditText) findViewById(R.id.field_Location);
            Spinner eventGenre = (Spinner) findViewById(R.id.field_Spinner_Genre);
            RadioGroup eventRestriction = (RadioGroup) findViewById(R.id.radio_Restriction);
            EditText eventDescription = (EditText) findViewById(R.id.field_Description);
            int radioId = eventRestriction.getCheckedRadioButtonId();
            RadioButton selectedID = (RadioButton) findViewById(radioId);
            if (selectedID != null
                    && !eventName.getText().toString().isEmpty()
                    && !eventLocation.getText().toString().isEmpty()
                    && !eventGenre.getSelectedItem().toString().equals("Genre")
                    && !eventRestriction.toString().isEmpty()
                    && !eventDescription.getText().toString().isEmpty()
                    ) {

                final Intent i = new Intent(this, Event_Status.class);

                i.putExtra("eventName", eventName.getText().toString());
                i.putExtra("eventLocation", eventLocation.getText().toString());
                i.putExtra("eventGenre", eventGenre.getSelectedItem().toString());
                i.putExtra("eventRestriction", selectedID.getText().toString());
                i.putExtra("eventDescription", eventDescription.getText().toString());
                DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int id) {
                        switch (id) {
                            case DialogInterface.BUTTON_POSITIVE:
                                startActivity(i);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("Are you sure you want to create this event?")
                        .setTitle("Create Event")
                        .setPositiveButton("Yes", clickListener)
                        .setNegativeButton("No", clickListener)
                        .show();


            }
            else
            {
                DialogInterface.OnClickListener failedclickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int id) {
                        //do nothing
                    }
                };
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
        // TODO

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
}


