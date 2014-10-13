package me.mattlogan.stravaflow;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.mattlogan.stravaflow.ui.screens.ActivityListScreen;
import me.mattlogan.stravaflow.ui.screens.AuthScreen;
import me.mattlogan.stravaflow.ui.view.Container;
import me.mattlogan.stravaflow.viewboss.Screen;
import me.mattlogan.stravaflow.viewboss.ViewBoss;

public class StravaFlowActivity extends Activity {

    ViewBoss boss;

    @InjectView(R.id.container) Container container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strava_flow);
        ButterKnife.inject(this);

        boss = new ViewBoss(container);
        boss.initializeFromSavedInstanceState(savedInstanceState);

        if (boss.current() != null) {
            container.showScreen(boss.current());
        } else {
            boss.goToScreen(initialScreen());
        }
    }

    @Override public void onBackPressed() {
        if (!boss.goBack()) {
            super.onBackPressed();
        }
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return boss.goBack();
        }
        return super.onOptionsItemSelected(item);
    }

    Screen initialScreen() {
        return ((StravaFlowApplication) getApplication()).hasAccessToken() ?
                new ActivityListScreen() : new AuthScreen();
    }

    public ViewBoss getBoss() {
        return boss;
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        boss.onSaveInstanceState(outState);
    }
}
