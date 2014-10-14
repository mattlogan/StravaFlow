package me.mattlogan.stravaflow;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;

import me.mattlogan.stravaflow.ui.fragment.ActivitiesFragment;
import me.mattlogan.stravaflow.ui.fragment.AuthFragment;

public class StravaFlowActivity extends Activity
        implements FragmentManager.OnBackStackChangedListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strava_flow);
        fragmentManager = getFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);
        fragmentManager.beginTransaction()
                .add(R.id.container, getInitialFragment())
                .commit();
    }

    private Fragment getInitialFragment() {
        return ((StravaFlowApplication) getApplication()).hasAccessToken() ?
                ActivitiesFragment.newInstance() : AuthFragment.newInstance();
    }

    @Override public void onBackStackChanged() {
        getActionBar().setDisplayHomeAsUpEnabled(fragmentManager.getBackStackEntryCount() > 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                fragmentManager.popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
