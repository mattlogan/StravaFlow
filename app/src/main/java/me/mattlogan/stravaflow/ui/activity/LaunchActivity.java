package me.mattlogan.stravaflow.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import me.mattlogan.stravaflow.StravaFlowApplication;

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, getInitialActivity());
        startActivity(intent);
    }

    Class getInitialActivity() {
        return ((StravaFlowApplication) getApplication()).hasAccessToken() ?
                ActivitiesActivity.class : AuthActivity.class;
    }
}
