package service.model;

import java.io.Serializable;

/**
 * Created by Pawe³ Galinski
 * 10.10.2015
 */
public class MapResponse implements Serializable{
    String base64Map;
    Point topLeft;
    Point bottomRight;

    public MapResponse(String base64Map, Point topLeft, Point bottomRight) {
        this.base64Map = base64Map;
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public String getBase64Map() {
        return base64Map;
    }

    public void setBase64Map(String base64Map) {
        this.base64Map = base64Map;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }
}
