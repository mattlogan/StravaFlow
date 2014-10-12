package me.mattlogan.stravaflow.ui.presenter;

import com.squareup.otto.Subscribe;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.mattlogan.stravaflow.api.StravaApiBus;
import me.mattlogan.stravaflow.api.event.ActivityRequestedEvent;
import me.mattlogan.stravaflow.api.event.ActivitySuccessEvent;
import me.mattlogan.stravaflow.api.model.StravaActivity;
import me.mattlogan.stravaflow.ui.view.ActivityDetailView;
import me.mattlogan.stravaflow.util.DistanceUtils;

public class ActivityDetailPresenter implements Presenter {

    ActivityDetailView activityDetailView;
    StravaApiBus stravaApiBus;

    public ActivityDetailPresenter(ActivityDetailView activityDetailView) {
        this.activityDetailView = activityDetailView;
        stravaApiBus = StravaApiBus.getInstance();
    }

    @Override public void register() {
        stravaApiBus.register(this);
    }

    @Override public void unregister() {
        stravaApiBus.unregister(this);
    }

    public void requestActivity(int id) {
        stravaApiBus.post(new ActivityRequestedEvent(id));
    }

    @Subscribe public void onActivitySuccess(ActivitySuccessEvent event) {
        StravaActivity activity = event.getActivity();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            Date date = sdf.parse(activity.getStartDate());
            DateFormat df = DateFormat.getDateTimeInstance();
            activityDetailView.setDateText(df.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        activityDetailView.setLocationText(
                activity.getLocationCity() + ", " + activity.getLocationState());

        float distMiles = DistanceUtils.meters2miles(activity.getDistance());
        activityDetailView.setDistanceText(String.format("%.2f", distMiles) + " miles");

        int elevFeet = (int) DistanceUtils.meters2feet(activity.getTotalElevationGain());
        activityDetailView.setElevationText(String.valueOf(elevFeet) + " feet elevation gain");
    }
}
