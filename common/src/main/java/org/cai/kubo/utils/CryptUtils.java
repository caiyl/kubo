package org.cai.kubo.utils;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CryptUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String value = "JZYX";
		String ss = getInstance().encrypt(value);
		String end = getInstance().decrypt(ss);
		System.out.println("加密后：" + ss);
		System.out.println("解密后：" + end);
	}

	private final static BASE64Encoder base64encoder = new BASE64Encoder();
	private final static BASE64Decoder base64decoder = new BASE64Decoder();
	private final static String encoding = "UTF-8";
	static byte[] keyData = { 1, 9, 8, 2, 0, 8, 2, 1 };

	static CryptUtils utils = null;
	public static CryptUtils getInstance(){
		if(utils == null){
			utils = new CryptUtils();
		}
		return utils;
	}
	
	/**
	 * 加密
	 */
	public String encrypt(String str) {
		String result = str;
		if (str != null && str.length() > 0) {
			try {
				byte[] encodeByte = symmetricEncrypto(str.getBytes(encoding));
				result = base64encoder.encode(encodeByte);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 解密
	 */
	public String decrypt(String str) {
		String result = str;
		if (str != null && str.length() > 0) {
			try {
				byte[] encodeByte = base64decoder.decodeBuffer(str);
				byte[] decoder = symmetricDecrypto(encodeByte);
				result = new String(decoder, encoding);
			} catch (Exception e) {	
				return str;
//				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 对称加密方法
	 * 
	 * @param byteSource
	 *            需要加密的数据
	 * @return 经过加密的数据
	 * @throws Exception
	 */
	public byte[] symmetricEncrypto(byte[] byteSource) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			int mode = Cipher.ENCRYPT_MODE;
			Key key = createDESSecretKey();
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(mode, key);
			byte[] result = cipher.doFinal(byteSource);
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			baos.close();
		}
	}

	/**
	 * 对称解密方法
	 * 
	 * @param byteSource
	 *            需要解密的数据
	 * @return 经过解密的数据
	 * @throws Exception
	 */
	public byte[] symmetricDecrypto(byte[] byteSource) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			int mode = Cipher.DECRYPT_MODE;
			Key key = createDESSecretKey();
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(mode, key);
			byte[] result = cipher.doFinal(byteSource);
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			baos.close();
		}
	}

	SecretKey createDESSecretKey()
			throws NoSuchAlgorithmException, InvalidKeyException,
			InvalidKeySpecException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		DESKeySpec keySpec = new DESKeySpec(keyData);
		SecretKey key = keyFactory.generateSecret(keySpec);
		return key;
	}
}
