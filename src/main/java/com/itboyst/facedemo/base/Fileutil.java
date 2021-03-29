package com.itboyst.facedemo.base;

import java.io.*;
import java.util.Date;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import cn.hutool.core.codec.Base64;
import sun.misc.BASE64Decoder;


public class Fileutil {

    /**
     * 将图片base64写入到本地目录
     *
     * @author wing
     * @param fileDir
     * @param base64ImgStr
     * @return
     */
    public String image64Input2Local(String fileDir, String base64ImgStr) {
        if (StringUtils.isAnyBlank(fileDir, base64ImgStr)) {
            return null;
        }
        // 截取图片文件后缀
        String fileSuffix = StringUtils.substringBetween(base64ImgStr, "data:", ";base64,");
        fileSuffix = fileSuffix.split("/")[1];

        // 截取图片数据
        String base64ImgData = StringUtils.substringAfter(base64ImgStr, ";base64,");

        // 文件名称
        String fileName = DateFormatUtils.format(new Date(), "yyyyMMddHHmmsssSSS") + "." + fileSuffix;


        // 判断目录是否存在，不存在则创建
        File file = new File(fileDir);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }

        String imgPath = fileDir + "/" + fileName;

        byte[] bs = Base64.decode(base64ImgData);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imgPath);
            fos.write(bs);
            fos.flush();
        } catch (Exception e) {
            System.out.println("保存图片到本地异常");
        }
        return imgPath;
    }



    public static void base64ToFile(String base64, String fileName, String savePath) {
        File file = null;
        //创建文件目录
        String filePath = savePath;
        File dir = new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.decode(base64);
            file=new File(filePath + fileName);
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
