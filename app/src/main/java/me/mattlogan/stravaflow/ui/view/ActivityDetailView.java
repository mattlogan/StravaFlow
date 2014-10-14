package me.mattlogan.stravaflow.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.mattlogan.stravaflow.R;

public class ActivityDetailView extends RelativeLayout {

    @InjectView(R.id.date_text) TextView dateText;
    @InjectView(R.id.location_text) TextView locationText;
    @InjectView(R.id.distance_text) TextView distanceText;
    @InjectView(R.id.elevation_text) TextView elevationText;

    public ActivityDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);
    }

    public void setDateText(String text) {
        dateText.setText(text);
    }

    public void setLocationTextVisibility(int visibility) {
        locationText.setVisibility(visibility);
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
