package me.mattlogan.stravaflow.api.model;

public class StravaActivity {

    int id;

    String name;
    String startDate;
    String locationCity;
    String locationState;

    float distance;
    float totalElevationGain;

    public StravaActivity(int id, String name, String startDate, String locationCity,
                          String locationState, float distance, float totalElevationGain) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.locationCity = locationCity;
        this.locationState = locationState;
        this.distance = distance;
        this.totalElevationGain = totalElevationGain;
    }

    public int getId() {
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
