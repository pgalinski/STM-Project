package service;

import map.MapImageHelper;
import map.projection.Point;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;

/**
 * Created by Pawe³ Galinski
 * 25.10.2015
 */
@WebService
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

    @WebMethod
     public String getWholeImage() throws IOException {
        return imageHelper.getImage();
    }

    @WebMethod
    public String getImage(int x1,int y1, int x2,int y2) throws IOException {
        return imageHelper.getImage(new Point(x1,y1), new Point(x2,y2));
    }
}
