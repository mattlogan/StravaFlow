package me.mattlogan.stravaflow.ui.activity;

import android.app.Fragment;

import me.mattlogan.stravaflow.StravaFlowApplication;
import me.mattlogan.stravaflow.ui.fragment.ActivitiesFragment;
import me.mattlogan.stravaflow.ui.fragment.AuthFragment;

public class StravaFlowActivity extends BaseActivity {

    @Override protected Fragment getInitialFragment() {
        return ((StravaFlowApplication) getApplication()).hasAccessToken() ?
                ActivitiesFragment.newInstance() : AuthFragment.newInstance();
    }
}
