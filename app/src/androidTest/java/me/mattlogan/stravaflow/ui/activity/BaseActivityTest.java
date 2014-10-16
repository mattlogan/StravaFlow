package me.mattlogan.stravaflow.ui.activity;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

public class BaseActivityTest extends ActivityInstrumentationTestCase2<BaseActivity> {

    private BaseActivity baseActivity;

    public BaseActivityTest() {
        super(BaseActivity.class);
    }

    @Override protected void setUp() throws Exception {
        super.setUp();
        this.setActivityIntent(createTargetIntent());
        this.baseActivity = getActivity();
    }

    private Intent createTargetIntent() {
        Intent intent = new Intent(getInstrumentation().getTargetContext(), BaseActivity.class);
        return intent;
    }
}
