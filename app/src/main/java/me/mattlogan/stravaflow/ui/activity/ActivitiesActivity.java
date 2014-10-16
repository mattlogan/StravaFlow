package me.mattlogan.stravaflow.ui.activity;

import android.app.Fragment;

import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.api.model.StravaActivity;
import me.mattlogan.stravaflow.ui.fragment.ActivitiesFragment;
import me.mattlogan.stravaflow.ui.fragment.ActivityDetailFragment;

public class ActivitiesActivity extends BaseActivity implements ActivitiesFragment.Listener {

    @Override
    protected Fragment getInitialFragment() {
        return ActivitiesFragment.newInstance();
    }

    @Override
    public void onActivitySelected(StravaActivity stravaActivity) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, ActivityDetailFragment.newInstance(stravaActivity))
                .addToBackStack(null)
                .commit();
    }
}
