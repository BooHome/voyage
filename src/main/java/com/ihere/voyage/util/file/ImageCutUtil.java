package com.ihere.voyage.util.file;

import com.ihere.voyage.constant.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author: fengshibo
 * @date: 2018/9/26 17:19
 * @description:
 */
public class ImageCutUtil {
    public static String splitImage(InputStream fis) throws IOException {
        // 读入大图
        BufferedImage image = ImageIO.read(fis);
        // 分割成rows*cols个小图
        int rows = Constant.IMAGE_CUT_ROWS;
        int cols = Constant.IMAGE_CUT_COLS;
        int chunks = rows * cols;
        // 计算每个小图的宽度和高度
        int chunkWidth = image.getWidth() / cols;
        int chunkHeight = image.getHeight() / rows;
        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                //设置小图的大小和类型
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
                //写入图像内容
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0,
                        chunkWidth, chunkHeight,
                        chunkWidth * y, chunkHeight * x,
                        chunkWidth * y + chunkWidth,
                        chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }
        String fileName = UUID.randomUUID().toString().replace("-", "");
        // 输出小图
        for (int i = 1; i <=imgs.length; i++) {
            if(i==imgs.length){
                ImageIO.write(imgs[8], "jpg", new File(Constant.IMAGE_CUT_PATH + fileName + "0" + ".jpg"));
            }else{
                ImageIO.write(imgs[i-1], "jpg", new File(Constant.IMAGE_CUT_PATH + fileName + i + ".jpg"));
            }
        }
        mergeImage(fileName);
        return fileName;
    }

    public static void mergeImage(String fileName) throws IOException {
        int rows = Constant.IMAGE_CUT_ROWS;
        int cols = Constant.IMAGE_CUT_COLS;
        int chunks = rows * cols;
        int chunkWidth, chunkHeight;
        int type;
        //读入小图
        File[] imgFiles = new File[chunks];
        int k=0;
        for (int i = 1; i <= chunks; i++) {
            if(i==chunks){
                imgFiles[8] = new File(Constant.IMAGE_CUT_PATH + fileName + 0 + ".jpg");
            }else{
                imgFiles[i-1] = new File(Constant.IMAGE_CUT_PATH + fileName + i + ".jpg");
            }
        }
        //创建BufferedImage
        BufferedImage[] buffImages = new BufferedImage[chunks];
        for (int i = 0; i < chunks; i++) {
            buffImages[i] = ImageIO.read(imgFiles[i]);
        }
        type = buffImages[0].getType();
        chunkWidth = buffImages[0].getWidth();
        chunkHeight = buffImages[0].getHeight();
        //设置拼接后图的大小和类型
        if (0 == type) {
            type = 5;
        }
        BufferedImage finalImg = new BufferedImage(chunkWidth * cols, chunkHeight * rows, type);
        //写入图像内容
        int num = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                finalImg.createGraphics().drawImage(buffImages[num], chunkWidth * j, chunkHeight * i, null);
                num++;
            }
        }
        //输出拼接后的图像
        ImageIO.write(finalImg, "jpeg", new File(Constant.IMAGE_CUT_PATH + fileName + Constant.IMAGE_CUT_FINAL_NAME + ".jpg"));
        InputStream is = (InputStream) ImageIO.createImageInputStream(finalImg);
        if (is != null) {
            is.close();
        }
    }

}
