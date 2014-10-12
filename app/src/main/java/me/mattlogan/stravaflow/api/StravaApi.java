package me.mattlogan.stravaflow.api;

import java.util.List;
import java.util.Map;

import me.mattlogan.stravaflow.api.model.AuthResponse;
import me.mattlogan.stravaflow.api.model.StravaActivity;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

public interface StravaApi {
    @POST("/oauth/token") void authenticate(
            @QueryMap Map<String, String> params, Callback<AuthResponse> callback);

    @GET("/api/v3/athlete/activities") void getActivities(
            @Query("before") Long before, Callback<List<StravaActivity>> activities);

    @GET("/api/v3/activities/{id}") void getActivity(
            @Path("id") Integer id, Callback<StravaActivity> activity);

}
