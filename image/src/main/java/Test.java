import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.imageio.plugins.jpeg.JPEG;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;


public class Test {

    public static void main(String[] args) throws IOException {
       // File file = new File("test.jpg");
        File file = new File("D:\\novel\\image\\src\\main\\resources\\image\\test.jpg");
        System.out.println(file.getName());
        System.out.println(file.canRead());
        System.out.println(new Date().toLocaleString());
        Image image =  ImageIO.read(file);
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        System.out.println(imageWidth);
        System.out.println(imageHeight);
        float resizeTimes = 0.1f;
        int toWidth = (int) (imageWidth * resizeTimes);
        int toHeight = (int) (imageHeight * resizeTimes);


        BufferedImage tag = new BufferedImage(toWidth,toHeight,BufferedImage.TYPE_INT_RGB);
        //tag.getGraphics().drawImage(image,0,0,toWidth,toHeight,null);
        tag.getGraphics().drawImage(image,0,0,toWidth,toHeight,null);
       // tag.getSubimage(0,0,toWidth,toHeight).getGraphics().drawImage(image,0,0,toWidth,toHeight,null);
        //tag.getGraphics().drawImage(image.getScaledInstance(toWidth,toHeight,Image.SCALE_SMOOTH),0,0null);
        File newFile = new File("D:\\novel\\image\\src\\main\\resources\\image\\hello5.gif");
        FileOutputStream out = new FileOutputStream(newFile);
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

        JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
        jep.setQuality(1.0f,true);
        encoder.encode(tag, jep);
        out.close();
        System.out.println(tag.getWidth());
        System.out.println(tag.getHeight());
        System.out.println(new Date().toLocaleString());

    }
}
