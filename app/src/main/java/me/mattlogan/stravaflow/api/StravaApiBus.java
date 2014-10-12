package me.mattlogan.stravaflow.api;

import com.squareup.otto.Bus;

public class StravaApiBus extends Bus {

    private static StravaApiBus singleton;

    public static StravaApiBus getInstance() {
        if (singleton == null) {
            singleton = new StravaApiBus();
        }
        return singleton;
    }

    private StravaApiBus() {
    }
}
