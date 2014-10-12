package me.mattlogan.stravaflow.api.event;

import me.mattlogan.stravaflow.api.model.StravaActivity;

public class ActivitySuccessEvent {

    StravaActivity activity;

    public ActivitySuccessEvent(StravaActivity activity) {
        this.activity = activity;
    }

    public StravaActivity getActivity() {
        return activity;
    }
}
