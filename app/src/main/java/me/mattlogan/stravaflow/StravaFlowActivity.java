package me.mattlogan.stravaflow;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.Gson;

import butterknife.ButterKnife;
import butterknife.InjectView;
import flow.Backstack;
import flow.Flow;
import flow.HasParent;
import flow.Layouts;
import flow.Parcer;
import me.mattlogan.stravaflow.ui.screen.ActivityListScreen;
import me.mattlogan.stravaflow.ui.screen.AuthScreen;
import me.mattlogan.stravaflow.ui.view.Container;
import me.mattlogan.stravaflow.util.GsonParcer;

public class StravaFlowActivity extends Activity implements Flow.Listener {

    @InjectView(R.id.container) Container container;

    static final String BACKSTACK_KEY = "backstack";

    Flow flow;
    Object currentScreen;

    Parcer<Object> parcer = new GsonParcer<Object>(new Gson());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strava_flow);
        ButterKnife.inject(this);

        flow = new Flow(getInitialBackstack(savedInstanceState), this);

        currentScreen = flow.getBackstack().current().getScreen();
        container.showInitialScreen(currentScreen);
        getActionBar().setDisplayHomeAsUpEnabled(currentScreen instanceof HasParent);
    }

    @Override
    public void go(Backstack nextBackstack, Flow.Direction direction, Flow.Callback callback) {
        currentScreen = nextBackstack.current().getScreen();
        container.showScreen(currentScreen, direction, callback);
        getActionBar().setDisplayHomeAsUpEnabled(currentScreen instanceof HasParent);
    }

    public Flow getFlow() {
        return flow;
    }

    public Object getCurrentScreen() {
        return currentScreen;
    }

    @Override public void onBackPressed() {
        if (!flow.goBack()) {
            super.onBackPressed();
        }
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return flow.goUp();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BACKSTACK_KEY, flow.getBackstack().getParcelable(parcer));
    }

    Backstack getInitialBackstack(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            return Backstack.from(savedInstanceState.getParcelable(BACKSTACK_KEY), parcer);
        } else {
            return Backstack.fromUpChain(getDefaultScreen());
        }
    }

    Object getDefaultScreen() {
        return ((StravaFlowApplication) getApplication()).hasAccessToken() ?
                new ActivityListScreen() : new AuthScreen();
    }
}
