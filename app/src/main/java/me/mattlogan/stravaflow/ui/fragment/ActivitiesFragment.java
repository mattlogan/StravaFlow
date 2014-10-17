package me.mattlogan.stravaflow.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.api.StravaApi;
import me.mattlogan.stravaflow.api.model.StravaActivity;
import me.mattlogan.stravaflow.ui.view.ActivitiesAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActivitiesFragment extends BaseFragment
        implements ActivitiesAdapter.OnActivitySelectedListener {

    @InjectView(R.id.activities_list) ListView listView;

    public interface Listener {
        public void onActivitySelected(StravaActivity stravaActivity);
    }

    ActivitiesAdapter adapter;

    public static ActivitiesFragment newInstance() {
        return new ActivitiesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ActivitiesAdapter(getActivity(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View activitiesView = inflater.inflate(R.layout.activities, container, false);
        ButterKnife.inject(this, activitiesView);
        setupListView();
        return activitiesView;
    }

    void setupListView() {
        listView.setAdapter(adapter);
    }

    @Override
    protected String getTitle() {
        return getString(R.string.app_name);
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchActivities(getStravaFlowApp(getActivity()).getStravaApi());
    }

    void fetchActivities(StravaApi stravaApi) {
        stravaApi.getActivities(System.currentTimeMillis(), new Callback<List<StravaActivity>>() {
            @Override
            public void success(List<StravaActivity> stravaActivities, Response response) {
                if (isAdded()) {
                   populateList(stravaActivities);
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

    void populateList(List<StravaActivity> stravaActivities) {
        adapter.setActivitiesList(stravaActivities);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivitySelected(StravaActivity stravaActivity) {
        Activity activity = getActivity();
        if (activity instanceof Listener) {
            ((Listener) activity).onActivitySelected(stravaActivity);
        }
    }
}
