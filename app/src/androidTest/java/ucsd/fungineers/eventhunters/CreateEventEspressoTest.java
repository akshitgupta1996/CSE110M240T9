package ucsd.fungineers.eventhunters;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Timothy on 3/6/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateEventEspressoTest {

    @Rule
    public ActivityTestRule<CreateEvent> mActivityRule = new ActivityTestRule(CreateEvent.class);

    @Test
    public void listGoesOverTheFold() {
        onView(withText("Hello world!")).check(matches(isDisplayed()));
    }
}
