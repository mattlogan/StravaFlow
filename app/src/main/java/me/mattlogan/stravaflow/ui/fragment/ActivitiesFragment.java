package me.mattlogan.stravaflow.ui.fragment;

import android.app.Fragment;
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

public class ActivitiesFragment extends Fragment
        implements ActivitiesAdapter.OnActivitySelectedListener {

    StravaApiBus apiBus = StravaApiBus.getInstance();

    ActivitiesView activitiesView;
    ActivitiesAdapter adapter;

    public static ActivitiesFragment newInstance() {
        return new ActivitiesFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ActivitiesAdapter(getActivity(), this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {

        getActivity().getActionBar().setTitle(getString(R.string.app_name));

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

    @Override public void onActivitySelected(StravaActivity activity) {
        getActivity().getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, ActivityDetailFragment.newInstance(activity))
                .addToBackStack(null)
                .commit();
    }
}
