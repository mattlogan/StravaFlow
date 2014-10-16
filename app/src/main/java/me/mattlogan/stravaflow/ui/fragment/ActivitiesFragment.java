package me.mattlogan.stravaflow.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.api.StravaApi;
import me.mattlogan.stravaflow.api.model.StravaActivity;
import me.mattlogan.stravaflow.ui.view.ActivitiesAdapter;
import me.mattlogan.stravaflow.ui.view.ActivitiesView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActivitiesFragment extends BaseFragment
        implements ActivitiesAdapter.OnActivitySelectedListener {

    public interface Listener {
        public void onActivitySelected(StravaActivity stravaActivity);
    }

    private ActivitiesView activitiesView;
    private ActivitiesAdapter adapter;
    private StravaApi stravaApi;

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

    @Override protected String getTitle() {
        return getString(R.string.app_name);
    }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        stravaApi = getStravaFlowApp(activity).getStravaApi();
    }

    @Override public void onResume() {
        super.onResume();
        fetchActivities();
    }

    private void fetchActivities() {
        stravaApi.getActivities(System.currentTimeMillis(), new Callback<List<StravaActivity>>() {
            @Override
            public void success(List<StravaActivity> stravaActivities, Response response) {
                if (adapter != null) {
                    adapter.setActivitiesList(stravaActivities);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

    @Override public void onActivitySelected(StravaActivity stravaActivity) {
        Activity activity = getActivity();
        if (activity instanceof Listener) {
            ((Listener) activity).onActivitySelected(stravaActivity);
        }
    }
}
