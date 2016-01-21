package com.cyyz.image;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * author :     zhaoht
 * time   :     2016/1/20.
 */
public class ImageProcess implements SimpleImage {


    public static final Logger logger = LoggerFactory.getLogger(ImageProcess.class);

    private Image srcImage;
    private int srcImageWidth;
    private int srcImageHeight;

    public ImageProcess(File image){
        try {
            srcImage = ImageIO.read(image);
            srcImageWidth = srcImage.getWidth(null);
            srcImageHeight = srcImage.getHeight(null);
        } catch (IOException e) {
            logger.error("�ļ����ܶ�ȡ",e);
        }
    }

    /**
     * ������rѹ��ͼƬ
     * @param r
     * @param outputStream
     * @return
     */
    public OutputStream ratio(float r,OutputStream outputStream){
        BufferedImage bufferedImage = new BufferedImage((int)(srcImageWidth * r),(int)(srcImageHeight * r),BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(srcImage,0,0,(int) (srcImageWidth * r),(int)(srcImageHeight * r),null);

        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);

        JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(bufferedImage);
        try {
            encoder.encode(bufferedImage, jep);
            JPEGEncodeParam jpegEncodeParam = JPEGCodec.getDefaultJPEGEncodeParam(bufferedImage);
            jpegEncodeParam.setQuality(r, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream;
    }

    /**
     * ���ͼƬwidth > 400��ѹ����width == 400
     * @param outputStream
     * @return
     */
    public OutputStream compressWidthTo400(OutputStream outputStream) {

        float r = (float) (Resolution.MIN_RESOLUTION * 1.0 / srcImageWidth);

        if(srcImageWidth > Resolution.MIN_RESOLUTION){
            this.ratio(r, outputStream);
        }
        return outputStream;
    }


    /**
     * ��Ԫ����
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        SimpleImage simpleImage = new ImageProcess(new File("D:\\novel\\image\\src\\main\\resources\\image\\test.jpg"));
        File file = new File("D:\\novel\\image\\src\\main\\resources\\image\\hh.jpg");
        FileOutputStream outputStream = new FileOutputStream(file);
        simpleImage.compressWidthTo400(outputStream);
       // imageProcess.execute(0.8f,outputStream);
        outputStream.close();
    }
}
