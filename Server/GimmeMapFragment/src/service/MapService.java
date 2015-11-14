package service;

import map.MapImageHelper;
import service.model.LatLon;
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
            imageHelper.loadMapFromFile("images/krk_mapa.png", new LatLon(50.1185,19.8547), new LatLon(50.0226,20.0471));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @WebMethod(action = "getWholeImage", operationName = "getWholeImage")
    public MapResponse getWholeImage() throws MapServiceException {
        try {
            return new MapResponse(imageHelper.getImage(),new Point(0,0),new Point(imageHelper.getImageWidth(),imageHelper.getImageHeight()));
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
        try {

            Point topLeftP = imageHelper.getPointInPx(topLeft);
            Point bottomRightP = imageHelper.getPointInPx(bottomRight);

            return new MapResponse(imageHelper.getImage(topLeftP,bottomRightP),topLeftP,bottomRightP);
        } catch (IOException e) {
            throw new MapServiceException();
        }
    }
}
