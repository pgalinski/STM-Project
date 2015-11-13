package service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Created by Pawe� Galinski
 * 04.10.2015
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Point")
public class Point {
    @XmlElement(name = "x")
    protected int x;
    @XmlElement(name = "y")
    protected int y;

    public Point(){}

    public Point(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }

    public int getX() {
        return x;
    }

    public void setX(int _x) {
        this.x = _x;
    }

    public int getY() {
        return y;
    }

    public void setY(int _y) {
        this.y = _y;
    }

}
