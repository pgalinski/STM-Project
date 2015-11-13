package map;

import service.model.LatLon;
import service.model.Point;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by Pawe³ Galinski
 * 25.10.2015
 */
public class MapImageHelper {

    BufferedImage image;

    private LatLon topLeft;
    private LatLon bottomRight;


    public void loadMapFromFile(String path) throws IOException {
        image = ImageIO.read(new File(path));
    }

    public void loadMapFromFile(String path,LatLon topLeft, LatLon bottomRight) throws IOException {
        loadMapFromFile(path);
        setTopLeft(topLeft);
        setBottomRight(bottomRight);
    }

    public LatLon getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(LatLon topLeft) {
        this.topLeft = topLeft;
    }

    public LatLon getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(LatLon bottomRight) {
        this.bottomRight = bottomRight;
    }

    public String getImage() throws IOException {
        return getBase64String(image);
    }

    public String getImage(LatLon topLeft, LatLon bottomRight) throws IOException {
        int x1 = (int) ((Math.abs(topLeft.getLongitude() - getTopLeft().getLongitude()))/Math.abs(getBottomRight().getLongitude() - getTopLeft().getLongitude())) * getImageWidth();
        int y1 = (int) (Math.abs(topLeft.getLatitude() - getTopLeft().getLatitude())/Math.abs(getBottomRight().getLatitude() - getTopLeft().getLatitude())) * getImageHeight();

        int x2 = (int) ((Math.abs(bottomRight.getLongitude() - getTopLeft().getLongitude()))/Math.abs(getBottomRight().getLongitude() - getTopLeft().getLongitude())) * getImageWidth();
        int y2 = (int) (Math.abs(bottomRight.getLatitude() - getTopLeft().getLatitude())/Math.abs(getBottomRight().getLatitude() - getTopLeft().getLatitude())) * getImageHeight();

        return getImage(new Point(x1,y1),new Point(x2,y2));
    }

    public Point getPointInPx(LatLon latlon){
        double tmpx = (Math.abs(latlon.getLongitude() - getTopLeft().getLongitude())) / Math.abs(getBottomRight().getLongitude() - getTopLeft().getLongitude());
        int x = (int) (tmpx * (double)getImageWidth());
        double tmpy = Math.abs(latlon.getLatitude() - getTopLeft().getLatitude()) / Math.abs(getBottomRight().getLatitude() - getTopLeft().getLatitude());
        int y = (int) (tmpy * (double)getImageHeight());
        return new Point(x,y);
    }

    public String getImage(Point topLeft, Point bottomRight) throws IOException {
        int x= topLeft.getX();
        int y= topLeft.getY();

        int w = bottomRight.getX() - topLeft.getX();
        int h = bottomRight.getY() - topLeft.getY();


        return getBase64String(image.getSubimage(x,y,w,h));
    }

    public int getImageHeight(){
        return image.getHeight();
    }

    public int getImageWidth(){
        return image.getWidth();
    }



    private String getBase64String(BufferedImage img) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return  new String(Base64.getMimeEncoder().encode(imageInByte));
    }

}
