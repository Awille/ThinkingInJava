package threadPoolTest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static ThreadPoolExecutor requestExcutor = new ThreadPoolExecutor(
            5,
            100,
            100,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r);
                }
            });

    public static void main(String[] args) {
//        requestExcutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hello");
//            }
//        });
//        File file = new File("D:\\毕业文件相关\\证件照\\IMGL6924-upload.jpg");
//        try {
//            BufferedImage bi = ImageIO.read(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("D:\\毕业文件相关\\证件照\\IMGL6924-upload.jpg");
            Integer i;
            int index = 0;
            byte[] bytes = new byte[fileInputStream.available()];
            while ((i = fileInputStream.read()) != -1) {
                bytes[index] = i.byteValue();
                index ++;
            }
            String imgStr = new BASE64Encoder().encode(bytes).replaceAll("[\\s*\t\n\r]", "");
            byte[] imgBytes =  new BASE64Decoder().decodeBuffer(imgStr);
            byte[] imgBase64Bytes = imgStr.getBytes();
            File path = new File("upload\\avatar");
            if (!path.exists()) {
                path.mkdirs();
            }
            File file = new File("" +
                    "upload\\avatar\\imgStr.jpg");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(imgBytes);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
