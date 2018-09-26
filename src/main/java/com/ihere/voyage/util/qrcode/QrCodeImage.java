package com.ihere.voyage.util.qrcode;

import jp.sourceforge.qrcode.data.QRCodeImage;

import java.awt.image.BufferedImage;

/**
 * 二维码图片对象
 * 
 * @author liruiming
 */
public class QrCodeImage implements QRCodeImage {
	
	private BufferedImage bufImg;

	public QrCodeImage(BufferedImage bufImg) {
		this.bufImg = bufImg;
	}

	@Override
	public int getHeight() {
		return bufImg.getHeight();
	}

	@Override
	public int getPixel(int x, int y) {
		return bufImg.getRGB(x, y);
	}

	@Override
	public int getWidth() {
		return bufImg.getWidth();
	}
}
