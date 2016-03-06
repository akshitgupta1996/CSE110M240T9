package ucsd.fungineers.eventhunters;

import java.util.*;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

public class FindEvents extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findevents);
    }

    /*This is a button click method, which will activate when any button is clicked.*/
    public void button_Click(View view) {
        //Toast.makeText(this, "this is my Toast message!!! =)",
        //        Toast.LENGTH_SHORT).show();
        switch (view.getId()) {
            case R.id.buttonFind: {
                Log.i("clicks", "Find");
                break;
            }
            case R.id.buttonMyEvents: {
                Log.i("clicks", "MyEvents");
                Intent i = new Intent(FindEvents.this, myEvents_both.class);
                startActivity(i);
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
