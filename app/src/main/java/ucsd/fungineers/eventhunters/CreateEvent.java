package ucsd.fungineers.eventhunters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Nick on 2/15/2016.
 */
public class CreateEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createevent);

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
            EditText eventDate = (EditText) findViewById(R.id.field_Data);
            EditText eventLocation = (EditText) findViewById(R.id.field_Location);
            Spinner eventGenre = (Spinner) findViewById(R.id.field_Spinner_Genre);
            RadioGroup eventRestriction = (RadioGroup) findViewById(R.id.radio_Restriction);
            EditText eventDescription = (EditText) findViewById(R.id.field_Description);


            final Intent i = new Intent(this, Event_Status.class);


            i.putExtra("eventName",eventName.getText().toString());
            i.putExtra("eventDate",eventDate.getText().toString());
            i.putExtra("eventLocation",eventLocation.getText().toString());
            i.putExtra("eventGenre", eventGenre.getSelectedItem().toString());

            int radioId = eventRestriction.getCheckedRadioButtonId();

            RadioButton selectedID = (RadioButton)findViewById(radioId);
            i.putExtra("eventRestriction",selectedID.getText().toString());
            i.putExtra("eventDescription", eventDescription.getText().toString());
            DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener()
            {
                    public void onClick(DialogInterface d, int id) {
                        switch(id) {
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
    }
}


