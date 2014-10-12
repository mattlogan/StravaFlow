package me.mattlogan.stravaflow.api.event;

/**
 * Created by matthewlogan on 8/26/14.
 */
public class ActivitiesRequestedEvent {

    Long after;

    public ActivitiesRequestedEvent(Long after) {
        this.after = after;
    }

    public Long getAfter() {
        return after;
    }
}
