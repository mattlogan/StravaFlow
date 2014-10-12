package me.mattlogan.stravaflow;

import android.app.Application;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.otto.Subscribe;

import flow.Backstack;
import flow.Flow;
import me.mattlogan.stravaflow.api.StravaApi;
import me.mattlogan.stravaflow.api.StravaApiBus;
import me.mattlogan.stravaflow.api.StravaApiHandler;
import me.mattlogan.stravaflow.api.event.AuthenticateSuccessEvent;
import me.mattlogan.stravaflow.ui.screen.ActivityListScreen;
import me.mattlogan.stravaflow.ui.screen.AuthScreen;
import me.mattlogan.stravaflow.util.PreferencesUtils;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class StravaFlowApplication extends Application {

    private static final String ENDPOINT = "https://www.strava.com";

    private StravaApiHandler stravaApiHandler;
    private StravaApiBus stravaApiBus = StravaApiBus.getInstance();
    private String accessToken;

    @Override public void onCreate() {
        super.onCreate();
        stravaApiBus.register(this);
        stravaApiHandler = new StravaApiHandler(this, buildStravaApi(), stravaApiBus);
        accessToken = PreferencesUtils.retrieveAccessToken(this);
    }

    StravaApi buildStravaApi() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        return new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setConverter(new GsonConverter(gson))
//                .setLogLevel(RestAdapter.LogLevel.FULL)
//                .setLog(new RestAdapter.Log() {
//                    @Override public void log(String message) {
//                        Log.d("testing", message);
//                    }
//                })
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override public void intercept(RequestFacade request) {
                        if (accessToken != null) {
                            request.addHeader("Authorization", " Bearer " + accessToken);
                        }
                    }
                })
                .build()
                .create(StravaApi.class);
    }

    @Subscribe public void onAuthenticateSuccess(AuthenticateSuccessEvent event) {
        accessToken = event.getAccessToken();
        PreferencesUtils.saveAccessToken(this, accessToken);
    }

    public boolean hasAccessToken() {
        return accessToken != null;
    }
}
