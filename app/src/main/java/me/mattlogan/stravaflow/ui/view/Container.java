package me.mattlogan.stravaflow.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import flow.Flow;
import flow.Layouts;

public class Container extends FrameLayout {

    public Container(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void showInitialScreen(Object screen) {
        Log.d("testing", "showInitialScreen: " + screen);
        addView(Layouts.createView(getContext(), screen));
    }

    public void showScreen(Object screen, Flow.Direction direction, Flow.Callback callback) {
        Log.d("testing", "showScreen: " + screen);
        removeAllViews();
        addView(Layouts.createView(getContext(), screen));
        callback.onComplete();
    }
}
