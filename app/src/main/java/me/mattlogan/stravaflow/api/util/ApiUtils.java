package me.mattlogan.stravaflow.api.util;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import me.mattlogan.stravaflow.R;

public class ApiUtils {

    public static Map<String, String> getAuthParams(Context context, String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", context.getString(R.string.client_id));
        params.put("client_secret", context.getString(R.string.client_secret));
        params.put("code", code);
        return params;
    }
}
