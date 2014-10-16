package me.mattlogan.stravaflow.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import me.mattlogan.stravaflow.StravaFlowApplication;

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Class firstActivity = ((StravaFlowApplication) getApplication()).hasAccessToken() ?
                ActivitiesActivity.class : AuthActivity.class;

        Intent intent = new Intent(this, firstActivity);
        startActivity(intent);
    }
}
