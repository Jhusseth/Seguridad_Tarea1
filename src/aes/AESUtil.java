package aes;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.DecoderException;

public class AESUtil {
	
	public AESUtil() {
		
	}

	public String encrypt(String key,String iv,String src) {
		String tCipher = "";
		
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, makeKey(key), makeIv(iv));
			
			tCipher += "Key     : "+hex(key) +"\n";
			tCipher += "Iv      : "+hex(iv) +"\n";
			tCipher += "Message : "+hex(src) +"\n";
			tCipher += "Cipher  : "+Base64.encodeBase64String(cipher.doFinal(src.getBytes())) +"\n";
			
			return tCipher;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String decrypt(String key,String iv,String src) {
		String decrypted = "";
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, makeKey(key), makeIv(iv));
			decrypted = new String(cipher.doFinal(Base64.decodeBase64(src)));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return decrypted;
	}

	private AlgorithmParameterSpec makeIv(String iv) {
		return new IvParameterSpec(hex(iv));
	}

	private Key makeKey(String sKey) {
		byte[] key = hex(sKey);
		return new SecretKeySpec(key, "AES");
	}

	private byte[] hex(String str) {
		try {
				return Hex.decodeHex(str.toCharArray());
		} catch (DecoderException e) {
				throw new IllegalStateException(e);
		}
	}

}
