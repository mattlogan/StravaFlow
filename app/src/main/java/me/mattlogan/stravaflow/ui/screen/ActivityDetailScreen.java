package me.mattlogan.stravaflow.ui.screen;

import flow.HasParent;
import flow.Layout;
import me.mattlogan.stravaflow.R;

@Layout(R.layout.activity_detail)
public class ActivityDetailScreen implements HasParent<ActivityListScreen> {

    public final int id;
    public final String name;

    public ActivityDetailScreen(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override public ActivityListScreen getParent() {
        return new ActivityListScreen();
    }
}
