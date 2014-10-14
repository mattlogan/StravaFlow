package me.mattlogan.stravaflow.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import me.mattlogan.stravaflow.R;

public class AuthWebView extends WebView {

    public AuthWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();

        String clientId = getResources().getString(R.string.client_id);
        String url = "https://www.strava.com/oauth/authorize?" +
                "client_id=" + clientId +
                "&response_type=code" +
                "&redirect_uri=http://localhost" +
                "&approval_prompt=force";

        loadUrl(url);
    }
}
