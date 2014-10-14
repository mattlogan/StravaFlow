package me.mattlogan.stravaflow.ui.activity;

import android.app.Fragment;

import me.mattlogan.stravaflow.ui.fragment.ActivitiesFragment;

public class ActivitiesActivity extends BaseActivity {

    @Override protected Fragment getInitialFragment() {
        return ActivitiesFragment.newInstance();
    }
}
