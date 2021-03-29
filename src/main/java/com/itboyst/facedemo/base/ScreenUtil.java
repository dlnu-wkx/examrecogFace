package com.itboyst.facedemo.base;

import org.springframework.util.ClassUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.itboyst.facedemo.controller.ZcommandController.logger;


public class ScreenUtil {
    /**
     * 捕获屏幕，生成图片
     */
    public static String capture() {

        // 获取屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        String fileName = null ;
        try {

            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(screenSize);
            // 捕获屏幕
            BufferedImage screenCapture = robot.createScreenCapture(screenRect);
            //存放位置
            String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
            fileName = System.currentTimeMillis() + ".png" ;
           // logger.info("屏幕截屏路径：{}", path);
            // 把捕获到的屏幕 输出为 图片
            ImageIO.write(screenCapture, "png", new File(path +File.separator + fileName));

        } catch (Exception e) {
            logger.error("获取屏幕截屏发生异常", e);
        }

        return fileName ;
    }

    public static void copeImage(String f1,String f2) throws IOException {

              //1.找到需要复制的图片
             File file = new File(f1); // NotFoundException
             FileInputStream inputStream = new FileInputStream(file);
             File file1 = new File(f2);
             FileOutputStream outputStream = new FileOutputStream(file1);
             byte[] b = new byte[1024];
             while(inputStream.read(b)!=-1){

                         outputStream.write(b);
                   }

             outputStream.close();


         }




}
