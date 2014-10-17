package me.mattlogan.stravaflow.ui.activity;

import android.app.Fragment;
import android.test.suitebuilder.annotation.SmallTest;

import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.ui.fragment.ActivitiesFragment;
import util.FragmentTestCase;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;

@SmallTest
public class ActivitiesFragmentTest extends FragmentTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected Fragment getFragment() {
        return ActivitiesFragment.newInstance();
    }

    public void testListShowing() {
        onView(withId(R.id.activities_list)).check(matches(isDisplayed()));
    }
}
