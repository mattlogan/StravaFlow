package me.mattlogan.stravaflow.api.event;

public class AuthenticateRequestedEvent {

    String code;

    public AuthenticateRequestedEvent(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
