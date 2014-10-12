package me.mattlogan.stravaflow.ui.presenter;

import com.squareup.otto.Subscribe;

import me.mattlogan.stravaflow.api.StravaApiBus;
import me.mattlogan.stravaflow.api.event.ActivitiesFailedEvent;
import me.mattlogan.stravaflow.api.event.ActivitiesRequestedEvent;
import me.mattlogan.stravaflow.api.event.ActivitiesSuccessEvent;
import me.mattlogan.stravaflow.ui.view.ActivityListView;

public class ActivityListPresenter implements Presenter {

    ActivityListView activityListView;
    StravaApiBus stravaApiBus;

    public ActivityListPresenter(ActivityListView activityListView) {
        this.activityListView = activityListView;
        stravaApiBus = StravaApiBus.getInstance();
    }

    @Override public void register() {
        stravaApiBus.register(this);
    }

    @Override public void unregister() {
        stravaApiBus.unregister(this);
    }

    public void requestActivities() {
        stravaApiBus.post(new ActivitiesRequestedEvent(System.currentTimeMillis()));
    }

    @Subscribe public void onActivitiesSuccess(ActivitiesSuccessEvent event) {
        activityListView.updateActivities(event.getStravaActivities());
    }

    @Subscribe public void onActivitiesFailed(ActivitiesFailedEvent event) {
    }
}
