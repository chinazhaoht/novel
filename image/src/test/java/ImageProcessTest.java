import java.io.*;

/**
 * Created by Administrator on 2016/1/20.
 */
public class ImageProcessTest {

    public static void main(String[] args) throws IOException {

        ImageProcess imageProcess = new ImageProcess(new File("D:\\novel\\image\\src\\main\\resources\\image\\test.gif"));
        File file = new File("D:\\novel\\image\\src\\main\\resources\\image\\hh.gif");
        FileOutputStream outputStream = new FileOutputStream(file);
        imageProcess.compressWidthTo400(outputStream);
        outputStream.close();


    }
}
