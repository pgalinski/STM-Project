package com.example.bartek.myapplication;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by bartek on 11/13/2015.
 */
public class Point implements KvmSerializable {
    public int x;
    public int y;
    public Point (int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public Point ()
    {
    }
    @Override
    public Object getProperty(int i) {
        switch (i)
        {
            case 0:
                return x;
            case 1:
                return y;
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
                x = Integer.parseInt(o.toString());
                break;
            case 1:
                y = Integer.parseInt(o.toString());
                break;
        }

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i)
        {
            case 0:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "x";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "y";
                break;
        }
    }
}
