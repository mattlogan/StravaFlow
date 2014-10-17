package me.mattlogan.stravaflow.ui.activity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.ui.fragment.ActivitiesFragment;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;

@SmallTest
public class ActivitiesFragmentTest extends ActivityInstrumentationTestCase2<FragmentTestActivity> {

    public ActivitiesFragmentTest() {
        super(FragmentTestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        FragmentTestActivity activity = getActivity();
        activity.addFragment(ActivitiesFragment.newInstance());
    }

    public void testHasActivitiesFragment() {
        assertNotNull(getActivitiesFragment());
    }

    public void testListShowing() {
        onView(withId(R.id.activities_list)).check(matches(isDisplayed()));
    }

    ActivitiesFragment getActivitiesFragment() {
        return (ActivitiesFragment) getActivity().getFragmentManager()
                .findFragmentByTag(ActivitiesFragment.class.getSimpleName());
    }
}
