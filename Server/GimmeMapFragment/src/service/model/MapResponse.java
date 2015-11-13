package service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by Pawe³ Galinski
 * 10.10.2015
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MapResponse")
public class MapResponse{

    @XmlElement(name = "base64Map")
    String base64Map;
    @XmlElement(name = "topLeft")
    Point topLeft;
    @XmlElement(name = "bottomRight")
    Point bottomRight;

    public MapResponse() {
    }

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
