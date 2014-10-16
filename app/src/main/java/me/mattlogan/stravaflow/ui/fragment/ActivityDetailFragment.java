package me.mattlogan.stravaflow.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.api.model.StravaActivity;
import me.mattlogan.stravaflow.util.DateUtils;
import me.mattlogan.stravaflow.util.DistanceUtils;

public class ActivityDetailFragment extends BaseFragment {

    @InjectView(R.id.date_text) TextView dateText;
    @InjectView(R.id.location_text) TextView locationText;
    @InjectView(R.id.distance_text) TextView distanceText;
    @InjectView(R.id.elevation_text) TextView elevationText;

    private static final String STRAVA_ACTIVITY_KEY = "strava_activity";

    StravaActivity stravaActivity;

    public static ActivityDetailFragment newInstance(StravaActivity stravaActivity) {
        ActivityDetailFragment fragment = new ActivityDetailFragment();

        Bundle args = new Bundle();
        args.putSerializable(STRAVA_ACTIVITY_KEY, stravaActivity);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stravaActivity = (StravaActivity) getArguments().getSerializable(STRAVA_ACTIVITY_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View activityDetailView = inflater.inflate(R.layout.activity_detail, container, false);
        ButterKnife.inject(this, activityDetailView);
        setupViews();
        return activityDetailView;
    }

    void setupViews() {
        dateText.setText(DateUtils.formatStravaDate(stravaActivity.getStartDate()));

        if (stravaActivity.getLocationState() != null && stravaActivity.getLocationCity() != null) {
            locationText.setText(stravaActivity.getLocationCity() + ", "
                    + stravaActivity.getLocationState());
        } else {
            locationText.setVisibility(View.GONE);
        }

        float distMiles = DistanceUtils.meters2miles(stravaActivity.getDistance());
        distanceText.setText(String.format("%.2f", distMiles) + " miles");

        int elevFeet = (int) DistanceUtils.meters2feet(stravaActivity.getTotalElevationGain());
        elevationText.setText(String.valueOf(elevFeet) + " feet elevation gain");
    }

    @Override
    protected String getTitle() {
        return stravaActivity.getName();
    }
}
