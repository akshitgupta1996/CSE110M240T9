package ucsd.fungineers.eventhunters;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
@LargeTest
public class CreateEventEspressoTest {



    private String mName;
    private String mLocation;
    private String mDesc;
    @Rule
    public ActivityTestRule<CreateEvent> mActivityRule = new ActivityTestRule(CreateEvent.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        mName = "Espresso Test Name";
        mLocation = "Espresso Test Location";
        mDesc = "Espresso Test Description";
    }


    @Test
    public void testCreateEvent() {

        onView(withId(R.id.field_Name)).perform(typeText(mName));
        onView(withId(R.id.field_Location)).perform(typeText(mLocation), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.button_clear)).perform(click());
        onView(withText("Yes")).perform(click());

        onView(withId(R.id.field_Name)).check(matches(withText("")));
        onView(withId(R.id.field_Location)).check(matches(withText("")));

        //TEST INCOMPLETE

        onView(withId(R.id.field_Name)).perform(typeText(mName));
        onView(withId(R.id.field_Location)).perform(typeText(mLocation), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.button_AddEvent)).perform(click());

        onView(withText("Please fill in all fields.")).check(matches(isDisplayed()));
        onView(withText("OK")).perform(click());

        //TEST CREATE EVENT

        //onView(withId(R.id.field_Name)).perform(typeText(mName));
        //onView(withId(R.id.field_Location)).perform(typeText(mLocation));
        onView(withId(R.id.field_Description)).perform(typeText(mDesc), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.field_Spinner_Genre)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Music"))).perform(click());

        onView(withId(R.id.radio_18Plus)).perform(click());

        onView(withId(R.id.button_AddEvent)).perform(click());
        onView(withText("Event Creation Confirmation")).check(matches(isDisplayed()));






    }
}
