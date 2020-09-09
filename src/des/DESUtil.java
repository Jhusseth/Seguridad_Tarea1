package des;

import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class DESUtil {
	
	
	
	
	public DESUtil() {
		
	}
	
	
     public String solve(String key, String msg) {
	   
	  String cipher = "";
	  
      try {
         byte[] theKey = null;
         byte[] theMsg = null;
//         byte[] theExpCiph = null;

         theKey = hexToBytes(key);
         theMsg = hexToBytes(msg); 
//         theExpCiph = hexToBytes(expct);

         KeySpec ks = new DESKeySpec(theKey);
         SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
         SecretKey ky = kf.generateSecret(ks);
         Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
         cf.init(Cipher.ENCRYPT_MODE,ky);
         byte[] theCph = cf.doFinal(theMsg);
         cf.init(Cipher.DECRYPT_MODE,ky);
         byte[] theDeCph = cf.doFinal(theCph);
         cipher += "Key     : "+bytesToHex(theKey) +"\n";
         cipher += "Cipher  : "+bytesToHex(theCph)  +"\n" ;
//         cipher += "ExpectedCipher  : "+bytesToHex(theExpCiph) +"\n";
         cipher += "Message : "+bytesToHex(theMsg);
//         cipher += "ExpectedMessage: "+bytesToHex(theDeCph) +"\n";
         
         return cipher;
         
      } catch (Exception e) {  
         throw new RuntimeException(e);
      }
   }
   
   
   
   
   public byte[] hexToBytes(String str) {
      if (str==null) {
         return null;
      } else if (str.length() < 2) {
         return null;
      } else {
         int len = str.length() / 2;
         byte[] buffer = new byte[len];
         for (int i=0; i<len; i++) {
             buffer[i] = (byte) Integer.parseInt(
                str.substring(i*2,i*2+2),16);
         }
         return buffer;
      }

   }
   public String bytesToHex(byte[] data) {
      if (data==null) {
         return null;
      } else {
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