package me.mattlogan.stravaflow.util;

public class DistanceUtils {

    public static float meters2miles(float meters) {
        return meters / 1609.34f;
    }

    public static float meters2feet(float meters) {
        return meters * 3.28084f;
    }
}
