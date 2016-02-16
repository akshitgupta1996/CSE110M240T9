package ucsd.fungineers.eventhunters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
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
        //Button mBtn1 = (Button) findViewById(R.id.button4);
        //mBtn1.setOnClickListener(this);

    }

    public void button_Click(View view) {

        String button_name = ((Button) view).getText().toString();
        if (button_name.equals("Add Event")) {
            Log.i("clicks", "Add Event");

            EditText eventName = (EditText) findViewById(R.id.field_Name);
            EditText eventDate = (EditText) findViewById(R.id.field_Data);
            EditText eventLocation = (EditText) findViewById(R.id.field_Location);
            Spinner eventGenre = (Spinner) findViewById(R.id.field_Spinner_Genre);
            RadioGroup eventRestriction = (RadioGroup) findViewById(R.id.radio_Restriction);
            EditText eventDescription = (EditText) findViewById(R.id.field_Description);


            Intent i = new Intent(this, Event_Status.class);


            i.putExtra("eventName",eventName.getText().toString());
            i.putExtra("eventDate",eventDate.getText().toString());
            i.putExtra("eventLocation",eventLocation.getText().toString());
            i.putExtra("eventGenre",eventGenre.getSelectedItem().toString());
            int radioId = eventRestriction.getCheckedRadioButtonId();
            RadioButton selectedID = (RadioButton)findViewById(radioId);
            i.putExtra("eventRestriction",selectedID.getText().toString());
            i.putExtra("eventDescription",eventDescription.getText().toString());
            
            startActivity(i);
        }
    }
}


