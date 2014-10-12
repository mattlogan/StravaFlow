package me.mattlogan.stravaflow.api.event;

import java.util.List;

import me.mattlogan.stravaflow.api.model.StravaActivity;

public class ActivitiesSuccessEvent {

    List<StravaActivity> stravaActivities;

    public ActivitiesSuccessEvent(List<StravaActivity> stravaActivities) {
        this.stravaActivities = stravaActivities;
    }

    public List<StravaActivity> getStravaActivities() {
        return stravaActivities;
    }
}
