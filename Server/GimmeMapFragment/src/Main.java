import map.MapImageHelper;
import map.projection.LatLon;
import map.projection.MapProjectionHelper;
import map.projection.Point;
import service.MapService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Pawe� Gali�ski
 * 04.10.2015
 */

public class Main {




    public static void main(String [] args) {

        MapService mapService = new MapService();
        Endpoint endpoint = Endpoint.publish("http://192.168.1.107/MapService",mapService);
       // endpoint.stop();
    }



}
