package me.mattlogan.stravaflow.ui.activity;

import android.test.ActivityInstrumentationTestCase2;

import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.ui.fragment.ActivitiesFragment;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;

public class ActivitiesActivityTest extends ActivityInstrumentationTestCase2<ActivitiesActivity> {

    public ActivitiesActivityTest() {
        super(ActivitiesActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testHasActivitiesFragment() {
        assertNotNull(getActivitiesFragment());
    }

    public void testListShowing() {
        onView(withId(R.id.activities_list)).check(matches(isDisplayed()));
    }

    public void testListIsEmpty() {
        // todo: check child count == 0
    }

    ActivitiesFragment getActivitiesFragment() {
        return (ActivitiesFragment) getActivity().getFragmentManager()
                .findFragmentByTag(ActivitiesFragment.class.getSimpleName());
    }
}
