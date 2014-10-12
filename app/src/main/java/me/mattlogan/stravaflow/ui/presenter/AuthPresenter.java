package me.mattlogan.stravaflow.ui.presenter;

import com.squareup.otto.Subscribe;

import me.mattlogan.stravaflow.api.StravaApiBus;
import me.mattlogan.stravaflow.api.event.AuthenticateRequestedEvent;
import me.mattlogan.stravaflow.api.event.AuthenticateSuccessEvent;
import me.mattlogan.stravaflow.ui.view.AuthWebView;

public class AuthPresenter implements Presenter {

    AuthWebView authWebView;
    StravaApiBus stravaApiBus;

    public AuthPresenter(AuthWebView authWebView) {
        this.authWebView = authWebView;
        stravaApiBus = StravaApiBus.getInstance();
    }

    @Override public void register() {
        stravaApiBus.register(this);
    }

    @Override public void unregister() {
        stravaApiBus.unregister(this);
    }

    public void handleAccessDenied() {
    }

    public void finishAuth(String code) {
        stravaApiBus.post(new AuthenticateRequestedEvent(code));
    }

    @Subscribe public void onAuthenticateSuccess(AuthenticateSuccessEvent event) {
        authWebView.goToNext();
    }
}
