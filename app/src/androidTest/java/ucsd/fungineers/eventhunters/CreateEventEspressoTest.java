package ucsd.fungineers.eventhunters;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Timothy on 3/6/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateEventEspressoTest {



    private String mStringToBetyped;
    @Rule
    public ActivityTestRule<CreateEvent> mActivityRule = new ActivityTestRule(CreateEvent.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        mStringToBetyped = "Espresso";
    }


    @Test
    public void testCreateEvent() {
        System system = new System(mActivityRule.getActivity());

        onView(withId(R.id.field_Name)).perform(typeText(mStringToBetyped));


    }
}
