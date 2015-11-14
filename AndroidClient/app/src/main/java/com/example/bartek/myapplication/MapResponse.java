package com.example.bartek.myapplication;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by bartek on 11/13/2015.
 */
public class MapResponse implements KvmSerializable {
    public MapResponse(){ }

    String base64Map;
    Point topLeft;
    Point bottomRight;
    @Override
    public Object getProperty(int i) {
        switch (i)
        {
            case 0:
                return base64Map;
            case 1:
                return topLeft;
            case 2:
                return bottomRight;
            default:
                break;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i)
        {
            case 0:
                base64Map = o.toString();
                break;
            case 1:
                 topLeft = (Point)o;
                break;
            case 2:
                bottomRight = (Point)o;
                break;
        }

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i) {
            case 0:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "base64Map";
                break;
            case 1:
                propertyInfo.type = Point.class;
                propertyInfo.name = "topLeft";
                break;
            case 2:
                propertyInfo.type = Point.class;
                propertyInfo.name = "bottomRight";
                break;
            default:
                break;
        }
    }
}
