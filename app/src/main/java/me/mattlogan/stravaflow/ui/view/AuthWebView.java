package me.mattlogan.stravaflow.ui.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import me.mattlogan.stravaflow.StravaFlowActivity;
import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.ui.presenter.AuthPresenter;

public class AuthWebView extends WebView {

    AuthPresenter presenter;

    public AuthWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        StravaFlowActivity activity = (StravaFlowActivity) getContext();
        presenter = new AuthPresenter(this);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        presenter.register();

        String clientId = getResources().getString(R.string.client_id);
        String url = "https://www.strava.com/oauth/authorize?" +
                "client_id=" + clientId +
                "&response_type=code" +
                "&redirect_uri=http://localhost" +
                "&approval_prompt=force";

        loadUrl(url);

        setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                if (uri.getHost().equals("localhost")) {
                    if (uri.getQueryParameterNames().contains("error")) {
                        presenter.handleAccessDenied();
                    } else {
                        presenter.finishAuth(uri.getQueryParameter("code"));
                        return true;
                    }
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.unregister();
    }

    public void goToNext() {
        // todo: go to activity list
    }
}
