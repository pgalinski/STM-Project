package map;

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


    public void loadMapFromFile(String path) throws IOException {
        image = ImageIO.read(new File(path));
    }

    public String getImage() throws IOException {
        return getBase64String(image);
    }

    public String getImage(Point topLeft, Point bottomRight) throws IOException {
        int x= topLeft.getX();
        int y= topLeft.getY();

        int w = bottomRight.getX() - topLeft.getX();
        int h = bottomRight.getY() - topLeft.getY();


        return getBase64String(image.getSubimage(x,y,w,h));
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
