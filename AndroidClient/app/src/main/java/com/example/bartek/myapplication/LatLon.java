package com.example.bartek.myapplication;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by bartek on 11/14/2015.
 */
public class LatLon implements KvmSerializable {
    double latitude;
    double longitude;

    public LatLon(){}

    public LatLon(String _latitude, String _longitude)
    {
        latitude = Double.parseDouble(_latitude);
        longitude= Double.parseDouble(_longitude);
    }
    @Override
    public Object getProperty(int i) {
        switch (i)
        {
            case 0:
                return latitude;
            case 1:
                return longitude;
            default:
                break;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i)
        {
            case 0:
                latitude = (double) o;
                break;
            case 1:
                longitude = (double) o;
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i)
        {
            case 0:
                propertyInfo.type = PropertyInfo.OBJECT_CLASS;
                propertyInfo.name = "latitude";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.OBJECT_CLASS;
                propertyInfo.name = "longitude";
                break;
        }
    }
}
