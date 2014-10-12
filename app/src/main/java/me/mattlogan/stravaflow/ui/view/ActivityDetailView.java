package me.mattlogan.stravaflow.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import flow.Flow;
import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.StravaFlowActivity;
import me.mattlogan.stravaflow.ui.presenter.ActivityDetailPresenter;
import me.mattlogan.stravaflow.ui.screen.ActivityDetailScreen;

public class ActivityDetailView extends RelativeLayout {

    @InjectView(R.id.date_text) TextView dateText;
    @InjectView(R.id.location_text) TextView locationText;
    @InjectView(R.id.distance_text) TextView distanceText;
    @InjectView(R.id.elevation_text) TextView elevationText;

    Flow flow;
    ActivityDetailScreen screen;
    ActivityDetailPresenter presenter;

    public ActivityDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        StravaFlowActivity activity = (StravaFlowActivity) getContext();
        flow = activity.getFlow();
        screen = (ActivityDetailScreen) activity.getCurrentScreen();
        presenter = new ActivityDetailPresenter(this);
        if (activity.getActionBar() != null) {
            activity.getActionBar().setTitle(screen.name);
        }
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);
        presenter.register();
        presenter.requestActivity(screen.id);
    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.unregister();
    }

    public void setDateText(String text) {
        dateText.setText(text);
    }

    public void setLocationText(String text) {
        locationText.setText(text);
    }

    public void setDistanceText(String text) {
        distanceText.setText(text);
    }

    public void setElevationText(String text) {
        elevationText.setText(text);
    }
}
