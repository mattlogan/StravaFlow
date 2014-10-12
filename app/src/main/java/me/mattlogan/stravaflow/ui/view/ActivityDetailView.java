package me.mattlogan.stravaflow.ui.view;

import android.app.ActionBar;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.StravaFlowActivity;
import me.mattlogan.stravaflow.ui.screens.ActivityDetailScreen;
import me.mattlogan.stravaflow.viewboss.ViewBoss;
import me.mattlogan.stravaflow.ui.presenter.ActivityDetailPresenter;

public class ActivityDetailView extends RelativeLayout {

    @InjectView(R.id.date_text) TextView dateText;
    @InjectView(R.id.location_text) TextView locationText;
    @InjectView(R.id.distance_text) TextView distanceText;
    @InjectView(R.id.elevation_text) TextView elevationText;

    ActivityDetailPresenter presenter;
    ViewBoss boss;

    public ActivityDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        presenter = new ActivityDetailPresenter(this);
        boss = ViewBoss.getInstance();
        ActivityDetailScreen screen = ((ActivityDetailScreen) boss.current());
        presenter.requestActivity(screen.id);
        ActionBar actionBar = ((StravaFlowActivity) getContext()).getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(screen.name);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);
        presenter.register();
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
