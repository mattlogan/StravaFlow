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

import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.api.StravaApi;
import me.mattlogan.stravaflow.api.model.AuthResponse;
import me.mattlogan.stravaflow.api.util.ApiUtils;
import me.mattlogan.stravaflow.ui.activity.ActivitiesActivity;
import me.mattlogan.stravaflow.ui.view.AuthWebView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AuthFragment extends BaseFragment {

    private AuthWebView authWebView;

    private StravaApi stravaApi;

    public static AuthFragment newInstance() {
        return new AuthFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        authWebView = (AuthWebView) inflater.inflate(R.layout.auth, container, false);
        authWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
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

    @Override
    protected String getTitle() {
        return getString(R.string.app_name);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        stravaApi = getStravaFlowApp(activity).getStravaApi();
    }

    private void finishAuth(String code) {
        stravaApi.authenticate(ApiUtils.getAuthParams(getActivity(), code),
                new Callback<AuthResponse>() {

            @Override
            public void success(AuthResponse authResponse, Response response) {
                Activity activity = getActivity();
                if (activity != null) {
                    getStravaFlowApp(activity).saveAccessToken(authResponse.getAccessToken());
                    Intent intent = new Intent(activity, ActivitiesActivity.class);
                    activity.startActivity(intent);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void handleAccessDenied() {
    }
}
