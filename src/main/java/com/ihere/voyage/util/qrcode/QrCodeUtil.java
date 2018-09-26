package com.ihere.voyage.util.qrcode;

import com.ihere.voyage.constant.Constant;
import com.ihere.voyage.constant.QrCodeConstant;
import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @模块名 : com.jshq.core.util.qrcode
 * @文件名 : QrCodeUtil.java
 * @文件实现功能 : 二维码工具
 * @作者 : yangpeng
 * @版本 : 1.0
 * @备注 : 无
 * @创建时间 : 2015-9-9 下午7:35:06
 * @修改时间 : 2015-9-9 下午7:35:06
 * @修改版本 :
 * @修改人 :
 * @修改内容 :
 */
public class QrCodeUtil {

	private static final Logger logger = LoggerFactory.getLogger(QrCodeUtil.class);

	/** 二维码LOGO宽度 */
	private static final int imageWidth = 40;

	/** 二维码LOGO高度 */
	private static final int imageHeight = 40;

	/**
	 * @函数名称 : createPhotoAtCenter
	 * @函数功能描述 : 在二维码中间加入LOGO
	 * @参数说明 : bufImg-图片
	 * @函数返回值 : 加入LOGO的图片
	 * @其它说明 : 无
	 * @抛出异常 : 无
	 */
	private static BufferedImage createPhotoAtCenter(BufferedImage bufImg) {
		InputStream is = null;
		try {
			// new一个URL对象
			URL url = new URL(QrCodeConstant.logo_file_path);
			// 打开链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置请求方式为"GET"
			conn.setRequestMethod("GET");
			// 超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			// 通过输入流获取图片数据
			is = conn.getInputStream();
			Image im = ImageIO.read(is);
			Graphics2D g = bufImg.createGraphics();
			// 获取bufImg的中间位置
			int centerX = bufImg.getMinX() + bufImg.getWidth() / 2 - imageWidth / 2;
			int centerY = bufImg.getMinY() + bufImg.getHeight() / 2 - imageHeight / 2;
			g.drawImage(im, centerX, centerY, imageWidth, imageHeight, null);
			g.dispose();
			bufImg.flush();
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		return bufImg;
	}

	/**
	 * @函数名称 : qrCodeCommon
	 * @函数功能描述 : 生成二维码的公共方法
	 * @参数说明 : content-需要生成二维码的内容；imgType-图片类型；size-二维码尺寸
	 * @函数返回值 : 二维码图片
	 * @其它说明 : 无
	 * @抛出异常 : 无
	 */
	private static BufferedImage qrCodeCommon(String content, String imgType, int size) {
		BufferedImage bufImg = null;
		try {
			Qrcode qrcodeHandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcodeHandler.setQrcodeErrorCorrect('L');
			qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcodeHandler.setQrcodeVersion(size);
			// 获得内容的字节数组，设置编码格式
			byte[] contentBytes = content.toString().getBytes(Constant.CHARSET_UTF8_NAME);
			// 图片尺寸
			int imgSize = 67 + 12 * (size - 1);
			bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			// 设置背景颜色
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize);
			// 设定图像颜色> BLACK
			gs.setColor(Color.BLACK);
			// 设置偏移量，不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容> 二维码
			if (contentBytes.length > 0 && contentBytes.length < 800) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
			}
			gs.dispose();
			bufImg.flush();
		} catch (Exception e) {
			logger.error("", e);
		}
		return bufImg;
	}

	/**
	 * @函数名称 : encoderQRCode
	 * @函数功能描述 : 生成二维码
	 * @参数说明 : content-需要生成二维码的内容;imgPath-图片路径；imgType-图片类型；size-二维码尺寸
	 * @函数返回值 : 无
	 * @其它说明 : 无
	 * @抛出异常 : 无
	 */
	public static void encoderQRCode(String content, String imgPath, String imgType, int size) {
		try {
			BufferedImage bufImg = qrCodeCommon(content, imgType, size);
			// 在二维码中间加入图片
			createPhotoAtCenter(bufImg);
			File imgFile = new File(imgPath);
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, imgFile);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * @函数名称 : encoderQRCode
	 * @函数功能描述 : 生成二维码
	 * @参数说明 : content-需要生成二维码的内容;output-输出流；imgType-图片类型；size-二维码尺寸
	 * @函数返回值 : 无
	 * @其它说明 : 无
	 * @抛出异常 : 无
	 */
	public static void encoderQRCode(String content, OutputStream output, String imgType, int size) {
		try {
			BufferedImage bufImg = qrCodeCommon(content, imgType, size);
			// 在二维码中间加入图片
			createPhotoAtCenter(bufImg);
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, output);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * @函数名称 : decoderQRCode
	 * @函数功能描述 : 解析二维码
	 * @参数说明 : imgPath-图片路径
	 * @函数返回值 : 二维码内容
	 * @其它说明 : 无
	 * @抛出异常 : 无
	 */
	public static String decoderQRCode(String imgPath) {
		// QRCode 二维码图片的文件
		File imageFile = new File(imgPath);
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(imageFile);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new QrCodeImage(bufImg)), Constant.CHARSET_UTF8_NAME);
		} catch (IOException e) {
			logger.error("", e);
		} catch (DecodingFailedException dfe) {
			logger.error("", dfe);
		}
		return content;
	}

	/**
	 * @函数名称 : decoderQRCode
	 * @函数功能描述 : 解析二维码
	 * @参数说明 : input-输入流
	 * @函数返回值 : 二维码内容
	 * @其它说明 : 无
	 * @抛出异常 : 无
	 */
	public static String decoderQRCode(InputStream input) {
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(input);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new QrCodeImage(bufImg)), Constant.CHARSET_UTF8_NAME);
		} catch (IOException e) {
			logger.error("", e);
		} catch (DecodingFailedException dfe) {
			logger.error("", dfe);
		}
		return content;
	}
}