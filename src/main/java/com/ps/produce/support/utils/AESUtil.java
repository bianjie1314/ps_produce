package com.ps.produce.support.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AESUtil {
	
	private static final String key="kjug15gd8ly1bgr7";
	private static final String iv = "zig5g8r6h1gfd2hr";
	private static Log logger = LogFactory.getLog(AESUtil.class);
	public static String encrypt(String data) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			int blockSize = cipher.getBlockSize();
			byte[] dataBytes = data.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			byte[] encrypted = cipher.doFinal(plaintext);
			return new sun.misc.BASE64Encoder().encode(encrypted);
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage(),e);
			return null;
		}
	}

	public static String decrypt(String data) {
		try {
			byte[] encrypted1 = new sun.misc.BASE64Decoder().decodeBuffer(data);
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original);
			return originalString;
		} catch (Exception e) {
			e.printStackTrace();logger.error(e.getMessage(),e);
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(encrypt("A1LID5XCRBL77N"));
//		logger.info(encrypt("amzn.mws.f6a744fd-62db-279c-57a0-db1cd9a45910"));
//		logger.info(encrypt("amzn.mws.34a7c6be-da28-b991-caa4-cf25f9e2ddda"));
//		logger.info(decrypt("bqOpJsSAg0Dxolfc6ikdVQ=="));
//		logger.info(decrypt("f6LkWH7HhkVGGJGUw0vrvmqh0qzC4vt2JLs9V48Ck3edeqVTWXOo693nnmPHdKyV"));
	}
}