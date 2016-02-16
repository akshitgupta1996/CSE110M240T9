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

            EditText editTextName = (EditText) findViewById(R.id.field_Name);
           Log.i( "Name: ",editTextName.getText().toString());
            
           /* editTextName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                //Log.d("myTag", "Yay action listener");
                @Override
                public boolean onEditorAction (TextView textView,int i, KeyEvent keyEvent){
                    Log.d("myTag", "Did we have an action?");
                    if (i == EditorInfo.IME_ACTION_NEXT) {
                        String inputText = textView.getText().toString();
                        Log.d("myTag", inputText);
                        return true;
                    }
                    return false;
                }
            });*/
            Log.i("clicks", "Add Event");
            Intent i = new Intent(this, Event_Status.class);
            startActivity(i);
        }
    }
}

        //}

