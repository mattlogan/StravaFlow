package me.mattlogan.stravaflow.api.event;

public class ActivityRequestedEvent {

    Integer id;

    public ActivityRequestedEvent(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
