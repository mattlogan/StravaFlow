package me.mattlogan.stravaflow.ui.screens;

import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.viewboss.Screen;

public class ActivityDetailScreen implements Screen {

    @Override public int layout() {
        return R.layout.activity_detail;
    }

    public final int id;
    public final String name;

    public ActivityDetailScreen(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
