package me.mattlogan.stravaflow.ui.fragment;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.api.model.StravaActivity;
import me.mattlogan.stravaflow.ui.view.ActivityDetailView;
import me.mattlogan.stravaflow.util.DistanceUtils;

public class ActivityDetailFragment extends Fragment {

    private static final String STRAVA_ACTIVITY_KEY = "strava_activity";

    private ActivityDetailView activityDetailView;

    private StravaActivity stravaActivity;

    public static ActivityDetailFragment newInstance(StravaActivity stravaActivity) {
        ActivityDetailFragment fragment = new ActivityDetailFragment();

        Bundle args = new Bundle();
        args.putSerializable(STRAVA_ACTIVITY_KEY, stravaActivity);
        fragment.setArguments(args);

        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stravaActivity = (StravaActivity) getArguments().getSerializable(STRAVA_ACTIVITY_KEY);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {

        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setTitle(stravaActivity.getName());

        activityDetailView =
                (ActivityDetailView) inflater.inflate(R.layout.activity_detail, container, false);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            Date date = sdf.parse(stravaActivity.getStartDate());
            DateFormat df = DateFormat.getDateTimeInstance();
            activityDetailView.setDateText(df.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (stravaActivity.getLocationState() != null &&
                stravaActivity.getLocationCity() != null) {

            activityDetailView.setLocationText(
                    stravaActivity.getLocationCity() + ", " + stravaActivity.getLocationState());
        } else {
            activityDetailView.setLocationTextVisibility(View.GONE);
        }

        float distMiles = DistanceUtils.meters2miles(stravaActivity.getDistance());
        activityDetailView.setDistanceText(String.format("%.2f", distMiles) + " miles");

        int elevFeet = (int) DistanceUtils.meters2feet(stravaActivity.getTotalElevationGain());
        activityDetailView.setElevationText(String.valueOf(elevFeet) + " feet elevation gain");

        return activityDetailView;
    }
}
