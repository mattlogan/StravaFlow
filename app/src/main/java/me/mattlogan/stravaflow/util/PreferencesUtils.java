package me.mattlogan.stravaflow.util;

import android.content.Context;

/**
 * Created by matthewlogan on 8/27/14.
 */
public class PreferencesUtils {

    private static final String STRAVA_STATS_PREFERENCES = "STRAVA_STATS_PREFERENCES";

    private static final String KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN";

    public static void saveAccessToken(Context context, String accessToken) {
        context.getSharedPreferences(STRAVA_STATS_PREFERENCES, 0)
                .edit()
                .putString(KEY_ACCESS_TOKEN, accessToken)
                .commit();
    }

    public static String retrieveAccessToken(Context context) {
        return context.getSharedPreferences(STRAVA_STATS_PREFERENCES, 0)
                .getString(KEY_ACCESS_TOKEN, null);
    }
}
