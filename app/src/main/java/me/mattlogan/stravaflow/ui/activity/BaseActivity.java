package me.mattlogan.stravaflow.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;

import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.StravaFlowApplication;
import me.mattlogan.stravaflow.api.StravaApi;

public abstract class BaseActivity extends Activity
        implements FragmentManager.OnBackStackChangedListener, StravaApiInjector {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
        fragmentManager = getFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);
        if (savedInstanceState == null) {
            Fragment initialFragment = getInitialFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.container, initialFragment,
                            initialFragment.getClass().getSimpleName())
                    .commit();
        } else {
            configureUpButton();
        }
    }

    protected abstract Fragment getInitialFragment();

    @Override
    public void onBackStackChanged() {
        configureUpButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                fragmentManager.popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void configureUpButton() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(fragmentManager.getBackStackEntryCount() > 0);
        }
    }

    @Override
    public StravaApi getStravaApi() {
        return ((StravaFlowApplication) getApplication()).getStravaApi();
    }
}
