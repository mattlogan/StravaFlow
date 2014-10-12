package me.mattlogan.stravaflow.api.event;

/**
 * Created by matthewlogan on 8/25/14.
 */
public class AuthenticateSuccessEvent {

    String accessToken;

    public AuthenticateSuccessEvent(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
