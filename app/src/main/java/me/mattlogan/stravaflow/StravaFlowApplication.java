package me.mattlogan.stravaflow;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.mattlogan.stravaflow.api.StravaApi;
import me.mattlogan.stravaflow.util.PreferencesUtils;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class StravaFlowApplication extends Application {

    static final String ENDPOINT = "https://www.strava.com";

    StravaApi stravaApi;
    String accessToken;

    @Override
    public void onCreate() {
        super.onCreate();
        stravaApi = buildStravaApi();
        accessToken = PreferencesUtils.retrieveAccessToken(this);
    }

    StravaApi buildStravaApi() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        return new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        if (accessToken != null) {
                            request.addHeader("Authorization", " Bearer " + accessToken);
                        }
                    }
                })
                .build()
                .create(StravaApi.class);
    }

    public void saveAccessToken(String accessToken) {
        this.accessToken = accessToken;
        PreferencesUtils.saveAccessToken(this, accessToken);
    }

    public boolean hasAccessToken() {
        return accessToken != null;
    }

    public StravaApi getStravaApi() {
        return stravaApi;
    }
}
