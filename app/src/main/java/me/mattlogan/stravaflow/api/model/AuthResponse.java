package me.mattlogan.stravaflow.api.model;

/**
 * Created by matthewlogan on 8/25/14.
 */
public class AuthResponse {

    String accessToken;
    StravaAthlete stravaAthlete;

    public AuthResponse(String accessToken, StravaAthlete stravaAthlete) {
        this.accessToken = accessToken;
        this.stravaAthlete = stravaAthlete;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public StravaAthlete getStravaAthlete() {
        return stravaAthlete;
    }
}
