package map.projection;

/**
 * Created by Pawe³ Galinski
 * 04.10.2015
 */
public class LatLon {
    double latitude;
    double longitude;

    public LatLon(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LatLon{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
