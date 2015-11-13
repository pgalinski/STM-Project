package service;

import map.MapImageHelper;
import service.model.LatLon;
import map.projection.MapProjectionHelper;
import service.model.MapResponse;
import service.model.Point;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;

/**
 * Created by Pawe³ Galinski
 * 25.10.2015
 */
@WebService(targetNamespace = "namespaceMapService")
public class MapService {
    private static MapImageHelper imageHelper;

    public MapService() {
        imageHelper = new MapImageHelper();
        try {
            imageHelper.loadMapFromFile("images/osrt_map.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @WebMethod(action = "getWholeImage", operationName = "getWholeImage")
    public MapResponse getWholeImage() throws MapServiceException {
        try {
            return new MapResponse(imageHelper.getImage(),new Point(0,0),new Point(1000,1000));
        } catch (IOException e) {
            throw new MapServiceException();
        }
    }

    @WebMethod(action = "getImage", operationName = "getImage")
    public MapResponse getImage(@WebParam(name = "topLeft") Point topLeft,@WebParam(name = "bottomRight") Point bottmRight) throws MapServiceException {
        try {
            return new MapResponse(imageHelper.getImage(topLeft,bottmRight),topLeft,bottmRight);
        } catch (IOException e) {
            throw new MapServiceException();
        }
    }

    @WebMethod(action = "hello", operationName = "hello")
    public String hello(@WebParam(name = "name")String name) {
        return "Hello1 " + name;
    }

    @WebMethod(action = "getImageByLatLon", operationName = "getImageByLatLon")
    public MapResponse getImageByLatLon(@WebParam(name = "topLeft") LatLon topLeft, @WebParam(name = "bottomRight") LatLon bottomRight) throws MapServiceException {
        Point topLeftP = MapProjectionHelper.merc(topLeft.getLatitude(), topLeft.getLongitude());
        Point bottomRightP = MapProjectionHelper.merc(bottomRight.getLatitude(), bottomRight.getLongitude());
        try {
            return new MapResponse(imageHelper.getImage(),new Point(0,0),new Point(1000,1000));
        } catch (IOException e) {
            throw new MapServiceException();
        }
    }
}
