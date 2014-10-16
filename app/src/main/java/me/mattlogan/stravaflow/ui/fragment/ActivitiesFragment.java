package me.mattlogan.stravaflow.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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

    private ActivitiesAdapter adapter;
    private StravaApi stravaApi;

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

    private void setupListView() {
        listView.setAdapter(adapter);
    }

    @Override
    protected String getTitle() {
        return getString(R.string.app_name);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        stravaApi = getStravaFlowApp(activity).getStravaApi();
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchActivities();
    }

    private void fetchActivities() {
        stravaApi.getActivities(System.currentTimeMillis(), new Callback<List<StravaActivity>>() {
            @Override
            public void success(List<StravaActivity> stravaActivities, Response response) {
                if (isAdded()) {
                    adapter.setActivitiesList(stravaActivities);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

    @Override
    public void onActivitySelected(StravaActivity stravaActivity) {
        Activity activity = getActivity();
        if (activity instanceof Listener) {
            ((Listener) activity).onActivitySelected(stravaActivity);
        }
    }
}
