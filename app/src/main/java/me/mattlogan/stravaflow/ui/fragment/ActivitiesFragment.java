package me.mattlogan.stravaflow.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.api.StravaApiBus;
import me.mattlogan.stravaflow.api.event.ActivitiesFailedEvent;
import me.mattlogan.stravaflow.api.event.ActivitiesRequestedEvent;
import me.mattlogan.stravaflow.api.event.ActivitiesSuccessEvent;
import me.mattlogan.stravaflow.api.model.StravaActivity;
import me.mattlogan.stravaflow.ui.view.ActivitiesAdapter;
import me.mattlogan.stravaflow.ui.view.ActivitiesView;

public class ActivitiesFragment extends BaseFragment
        implements ActivitiesAdapter.OnActivitySelectedListener {

    public interface Listener {
        public void onActivitySelected(StravaActivity stravaActivity);
    }

    private StravaApiBus apiBus = StravaApiBus.getInstance();

    private ActivitiesView activitiesView;
    private ActivitiesAdapter adapter;

    public static ActivitiesFragment newInstance() {
        return new ActivitiesFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ActivitiesAdapter(getActivity(), this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {

        activitiesView =
                (ActivitiesView) inflater.inflate(R.layout.activities, container, false);
        activitiesView.setListViewAdapter(adapter);

        return activitiesView;
    }

    @Override public void onResume() {
        super.onResume();
        apiBus.register(this);
        apiBus.post(new ActivitiesRequestedEvent(System.currentTimeMillis()));
    }

    @Override protected String getTitle() {
        return getString(R.string.app_name);
    }

    @Override public void onPause() {
        super.onPause();
        apiBus.unregister(this);
    }

    @Subscribe public void onActivitiesSuccess(ActivitiesSuccessEvent event) {
        adapter.setActivitiesList(event.getStravaActivities());
        adapter.notifyDataSetChanged();
    }

    @Subscribe public void onActivitiesFailed(ActivitiesFailedEvent event) {
    }

    @Override public void onActivitySelected(StravaActivity stravaActivity) {
        Activity activity = getActivity();
        if (activity instanceof Listener) {
            ((Listener) activity).onActivitySelected(stravaActivity);
        }
    }
}
