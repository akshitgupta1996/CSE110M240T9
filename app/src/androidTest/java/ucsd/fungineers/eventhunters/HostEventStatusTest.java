package ucsd.fungineers.eventhunters;

import android.content.Intent;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
/**
 * Created by Timothy on 3/6/2016.
 */
@RunWith(AndroidJUnit4.class)
public class HostEventStatusTest {

    private String mEventTitle;
    private String mLocation;
    private String mDesc;

    @Rule
    public ActivityTestRule<host_event_status> mActivityRule = new ActivityTestRule(host_event_status.class, false, false);

    @Before
    public void initValidString() {
        // Specify a valid string.
        mEventTitle = "Host Test Event Name";
        mLocation = "Host Espresso Test Location";
        mDesc = "Host Espresso Test Description";
    }

    @Test
    public void hostEventStatus() {

        ArrayList<String> test = new ArrayList<String>();
        GregorianCalendar cal = new GregorianCalendar();
        Event testEvent = new Event(test, "Test Host Id", RestrictionStatus.OVER_21, Genre.PARTY, mEventTitle, mDesc, cal, mLocation);
        Intent i = new Intent();
        i.putExtra("EventKey", testEvent);
        mActivityRule.launchActivity(i);

        onView(withId(R.id.event_title)).check(matches(withText(mEventTitle)));
        onView(withId(R.id.event_location)).check(matches(withText(mLocation)));
        onView(withId(R.id.event_genre)).check(matches(withText(Genre.PARTY.toString())));
        onView(withId(R.id.event_restriction)).check(matches(withText(RestrictionStatus.OVER_21.toString())));
        



        //...
    }
}
