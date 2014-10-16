package me.mattlogan.stravaflow.api.model;

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
