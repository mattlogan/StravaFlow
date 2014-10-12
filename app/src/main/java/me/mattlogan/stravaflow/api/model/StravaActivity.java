package me.mattlogan.stravaflow.api.model;

public class StravaActivity {

    String id;
    String name;
    String startDate;
    String locationCity;
    String locationState;

    float distance;
    float totalElevationGain;

    public StravaActivity(String id, String name, String startDate, String locationCity,
                          String locationState, float distance, float totalElevationGain) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.locationCity = locationCity;
        this.locationState = locationState;
        this.distance = distance;
        this.totalElevationGain = totalElevationGain;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public String getLocationState() {
        return locationState;
    }

    public float getDistance() {
        return distance;
    }

    public float getTotalElevationGain() {
        return totalElevationGain;
    }

    @Override public String toString() {
        return name;
    }
}
