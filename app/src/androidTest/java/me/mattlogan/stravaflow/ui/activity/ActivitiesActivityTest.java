package me.mattlogan.stravaflow.ui.activity;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

public class ActivitiesActivityTest extends ActivityInstrumentationTestCase2<ActivitiesActivity> {

    private ActivitiesActivity activitiesActivity;

    public ActivitiesActivityTest() {
        super(ActivitiesActivity.class);
    }

    @Override protected void setUp() throws Exception {
        super.setUp();
        setActivityIntent(createTargetIntent());
        activitiesActivity = getActivity();
    }

    private Intent createTargetIntent() {
        return new Intent(getInstrumentation().getTargetContext(), ActivitiesActivity.class);
    }

    public void testSomeStuff() {
        assertEquals(1, 1);
    }
}
