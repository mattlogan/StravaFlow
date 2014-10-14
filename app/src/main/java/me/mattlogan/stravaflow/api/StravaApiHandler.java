package me.mattlogan.stravaflow.api;

import android.content.Context;
import android.util.Log;

import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.api.event.ActivitiesFailedEvent;
import me.mattlogan.stravaflow.api.event.ActivitiesRequestedEvent;
import me.mattlogan.stravaflow.api.event.ActivitiesSuccessEvent;
import me.mattlogan.stravaflow.api.event.ActivityFailedEvent;
import me.mattlogan.stravaflow.api.event.ActivityRequestedEvent;
import me.mattlogan.stravaflow.api.event.ActivitySuccessEvent;
import me.mattlogan.stravaflow.api.event.AuthenticateFailedEvent;
import me.mattlogan.stravaflow.api.event.AuthenticateRequestedEvent;
import me.mattlogan.stravaflow.api.event.AuthenticateSuccessEvent;
import me.mattlogan.stravaflow.api.model.AuthResponse;
import me.mattlogan.stravaflow.api.model.StravaActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class StravaApiHandler {

    Context context;
    StravaApi stravaApi;
    StravaApiBus stravaApiBus;

    public StravaApiHandler(Context context, StravaApi stravaApi, StravaApiBus stravaApiBus) {
        this.context = context;
        this.stravaApi = stravaApi;
        this.stravaApiBus = stravaApiBus;
        this.stravaApiBus.register(this);
    }

    @Subscribe public void onAuthenticateRequested(AuthenticateRequestedEvent event) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", context.getString(R.string.client_id));
        params.put("client_secret", context.getString(R.string.client_secret));
        params.put("code", event.getCode());

        stravaApi.authenticate(params, new Callback<AuthResponse>() {
            @Override public void success(AuthResponse authResponse, Response response) {
                stravaApiBus.post(new AuthenticateSuccessEvent(authResponse.getAccessToken()));
            }

            @Override public void failure(RetrofitError error) {
                stravaApiBus.post(new AuthenticateFailedEvent());
            }
        });
    }

    @Subscribe public void onActivitiesRequested(ActivitiesRequestedEvent event) {
        stravaApi.getActivities(event.getBefore(), new Callback<List<StravaActivity>>() {
            @Override public void success(List<StravaActivity> activities, Response response) {
                stravaApiBus.post(new ActivitiesSuccessEvent(activities));
            }

            @Override public void failure(RetrofitError error) {
                stravaApiBus.post(new ActivitiesFailedEvent());
            }
        });
    }

    @Subscribe public void onActivityRequested(ActivityRequestedEvent event) {
        stravaApi.getActivity(event.getId(), new Callback<StravaActivity>() {
            @Override public void success(StravaActivity stravaActivity, Response response) {
                stravaApiBus.post(new ActivitySuccessEvent(stravaActivity));
            }

            @Override public void failure(RetrofitError error) {
                Log.d("testing", "failure: " + error.getMessage());
                stravaApiBus.post(new ActivityFailedEvent());
            }
        });
    }
}
