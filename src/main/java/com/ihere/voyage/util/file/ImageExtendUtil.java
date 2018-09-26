package com.ihere.voyage.util.file;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class ImageExtendUtil {
	/**
	 * 获取去锯齿圆角图片
	 *
	 * @param img      图片
	 * @param diameter 直径
	 */
	public static BufferedImage makeCircularImg(BufferedImage img, int diameter) {
		return makeCircularImg(img, diameter, diameter);
	}

	/**
	 * 获取去锯齿圆角图片
	 *
	 * @param img           图片
	 * @param diameter      直径
	 * @param rotationAngle 圆角距离边角的距离，最大与边长相同是为圆形，越小圆角也越小
	 */
	public static BufferedImage makeCircularImg(BufferedImage img, int diameter, float rotationAngle) {
		BufferedImage circularImg = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = circularImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fill(new RoundRectangle2D.Float(0, 0, diameter, diameter, rotationAngle, rotationAngle));
		g2.setComposite(AlphaComposite.SrcAtop);
		g2.drawImage(img.getScaledInstance(diameter, diameter, Image.SCALE_SMOOTH), 0, 0, diameter, diameter, null);
		g2.dispose();
		return circularImg;
	}


	/**
	 * 获取去锯齿拼接图片
	 *
	 * @param smallImg      填充图片素材
	 * @param backGroundImg 背景图
	 * @param x             起始x坐标
	 * @param y             起始y坐标
	 * @param w             填充的宽度
	 * @param h             填充的高度
	 */

	public static BufferedImage fillImage(BufferedImage smallImg, BufferedImage backGroundImg, int x, int y, int w, int h) {
		Graphics2D g = backGroundImg.createGraphics();
		g.setComposite(AlphaComposite.SrcAtop);
		g.drawImage(smallImg, x, y, w, h, null);
		g.dispose();
		return backGroundImg;
	}

	/**
	 * 获取去锯齿拼接图片
	 *
	 * @param smallImg      填充图片素材
	 * @param backGroundImg 背景图
	 * @param x             起始x坐标
	 * @param y             起始y坐标
	 */

	public static BufferedImage fillImage(BufferedImage smallImg, BufferedImage backGroundImg, int x, int y) {
		return fillImage(smallImg, backGroundImg, x, y, smallImg.getWidth(), smallImg.getHeight());
	}

	/**
	 * 获取填充文字的图片
	 *
	 * @param backGroundImg 背景图
	 * @param x             起始x坐标
	 * @param y             起始y坐标
	 * @param str           填充字符
	 * @param font          字体
	 * @param color         颜色
	 */
	public static BufferedImage fillText(BufferedImage backGroundImg, int x, int y, String str, Font font, Color color) {
		Graphics2D g = backGroundImg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(font);
		g.setColor(color);
		g.drawString(str, x, y);
		g.dispose();
		return backGroundImg;
	}
}
