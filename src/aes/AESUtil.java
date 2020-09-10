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
		
		String t = "00000000000000000000000000000000,00000000000000000000000000000000,96ab5c2ff612d9dfaae8c31f30c42168";
		
	}

	public String encrypt(String key,String iv,String src) {
		String tCipher = "";
		
		byte[] theKey = null;
		byte[] theIv = null;
        byte[] theMsg = null;
        
        
        theKey = hexToBytes(key);
        theIv = hexToBytes(iv);
        theMsg = hexToBytes(src);
		
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, makeKey(theKey), makeIv(theIv));
			
			tCipher += "Key     : "+bytesToHex(hex(key)) +"\n";
			tCipher += "Iv      : "+bytesToHex(hex(iv)) +"\n";
			tCipher += "Message : "+bytesToHex(hex(src)) +"\n";
			char[] arrayc = bytesToHex(cipher.doFinal(theMsg)).toCharArray();
			tCipher += "Cipher  : "+aux(arrayc) +"\n";
			
			return tCipher;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String decrypt(String key,String iv,String src) {
		String decrypted = "";
		byte[] theKey = null;
		byte[] theIv = null;
        byte[] theMsg = null;
		theKey = hexToBytes(key);
        theIv = hexToBytes(iv);
        theMsg = hexToBytes(src);
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, makeKey(theKey), makeIv(theIv));
			decrypted = new String(cipher.doFinal(Base64.decodeBase64(theMsg)));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return decrypted;
	}

	private AlgorithmParameterSpec makeIv(byte[] iv) {
		return new IvParameterSpec(iv);
	}

	private Key makeKey(byte[] sKey) {
		byte[] key = sKey;
		return new SecretKeySpec(key, "AES");
	}

	private byte[] hex(String str) {
		try {
				return Hex.decodeHex(str.toCharArray());
		} catch (DecoderException e) {
				throw new IllegalStateException(e);
		}
	}
	
	public byte[] hexToBytes(String str) {
	      if (str==null) {
	         return null;
	      }
	      else if (str.length() < 2) {
	         return null;
	      }
	      else {
	         int len = str.length() / 2;
	         byte[] buffer = new byte[len];
	         for (int i=0; i<len; i++) {
	             buffer[i] = (byte) Integer.parseInt(
	                str.substring(i*2,i*2+2),16);
	         }
	         return buffer;
	     }
	}
	
	private String aux(char[] array) {
		String word = "";
		
		for(int i = 0;i<32;i++) {
			word += array[i]; 
		}
		return word;
	}
	
	  
	public String bytesToHex(byte[] data) {
		if (data==null) { 
			return null;
	    }
		else {
	         
			int len = data.length;
	        String str = "";
	        for (int i=0; i<len; i++) {
	           if ((data[i]&0xFF)<16) str = str + "0" 
	              + java.lang.Integer.toHexString(data[i]&0xFF);
	           else str = str
	              + java.lang.Integer.toHexString(data[i]&0xFF);
	        }
	        return str.toUpperCase();  
		}	
	} 
}
