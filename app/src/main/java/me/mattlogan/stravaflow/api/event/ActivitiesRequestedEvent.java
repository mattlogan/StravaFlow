package me.mattlogan.stravaflow.api.event;

public class ActivitiesRequestedEvent {

    Long before;

    public ActivitiesRequestedEvent(Long before) {
        this.before = before;
    }

    public Long getBefore() {
        return before;
    }
}
