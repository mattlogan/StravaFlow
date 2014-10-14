package me.mattlogan.stravaflow.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.squareup.otto.Subscribe;

import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.api.StravaApiBus;
import me.mattlogan.stravaflow.api.event.ActivitiesRequestedEvent;
import me.mattlogan.stravaflow.api.event.AuthenticateRequestedEvent;
import me.mattlogan.stravaflow.api.event.AuthenticateSuccessEvent;
import me.mattlogan.stravaflow.ui.activity.ActivitiesActivity;
import me.mattlogan.stravaflow.ui.view.AuthWebView;

public class AuthFragment extends BaseFragment {

    private StravaApiBus apiBus = StravaApiBus.getInstance();

    private AuthWebView authWebView;

    public static AuthFragment newInstance() {
        return new AuthFragment();
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {

        authWebView = (AuthWebView) inflater.inflate(R.layout.auth, container, false);
        authWebView.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                if (uri.getHost().equals("localhost")) {
                    if (uri.getQueryParameterNames().contains("error")) {
                        handleAccessDenied();
                    } else {
                        finishAuth(uri.getQueryParameter("code"));
                        return true;
                    }
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        return authWebView;
    }

    @Override public void onResume() {
        super.onResume();
        apiBus.register(this);
        apiBus.post(new ActivitiesRequestedEvent(System.currentTimeMillis()));
    }

    @Override protected String getTitle() {
        return getString(R.string.app_name);
    }

    @Override public void onPause() {
        super.onPause();
        apiBus.unregister(this);
    }

    private void finishAuth(String code) {
        apiBus.post(new AuthenticateRequestedEvent(code));
    }

    @Subscribe public void onAuthenticateSuccess(AuthenticateSuccessEvent event) {
        Activity activity = getActivity();
        if (activity != null) {
            Intent intent = new Intent(activity, ActivitiesActivity.class);
            activity.startActivity(intent);
        }
    }

    private void handleAccessDenied() {
    }
}
