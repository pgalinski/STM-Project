package service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by Pawe³ Galinski
 * 04.10.2015
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LatLon")
public class LatLon{
    @XmlElement(name = "latitude")
    double latitude;

    @XmlElement(name = "longitude")
    double longitude;

    public LatLon() {
    }

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
