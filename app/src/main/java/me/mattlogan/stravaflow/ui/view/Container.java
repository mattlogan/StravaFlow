package me.mattlogan.stravaflow.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import me.mattlogan.stravaflow.viewboss.Screen;
import me.mattlogan.stravaflow.viewboss.ViewBoss;

public class Container extends FrameLayout implements ViewBoss.Listener {

    public Container(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override public void showScreen(Screen screen) {
        removeAllViews();
        addView(LayoutInflater.from(getContext()).inflate(screen.layout(), this, false));
    }
}
