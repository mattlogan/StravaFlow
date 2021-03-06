package me.mattlogan.stravaflow.ui.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;

import me.mattlogan.stravaflow.StravaFlowApplication;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        Activity activity = getActivity();
        if (activity != null) {
            ActionBar actionBar = activity.getActionBar();
            if (actionBar != null) {
                actionBar.setTitle(getTitle());
            }
        }
    }

    protected abstract String getTitle();

    protected StravaFlowApplication getStravaFlowApp() {
        return (StravaFlowApplication) getActivity().getApplication();
    }
}
