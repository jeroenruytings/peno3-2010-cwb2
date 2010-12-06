package cw.kuleuven.be.peno1011.cwb2.database;
//NOG NIET OPERATIONEEL
//bron: http://android.voxisland.com/code_examples/How_to_encrypt_and_decrypt_strings.rhtml



import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class Cryptography {
	
	private final String SEED="ditishetencryptiepaswoord";
	private final String HEX="0123456789ABCDEF";
	private static Cryptography cryptography;
	
	private Cryptography(){	}
	
	public static Cryptography getInstance() {
		if(cryptography==null) {
			cryptography = new Cryptography();
		}
		return cryptography;
	}
	
	//Seed = wachtwoord, zonder wachtwoord geen decryption
	
	public String encrypt(String cleartext) {
		byte[] rawKey;
		String resultString;
		try {
			rawKey = getRawKey(SEED.getBytes());
			byte[] result = encrypt(rawKey, cleartext.getBytes());
			resultString = toHex(result);
		} catch (Exception e) {
			resultString = cleartext;
			e.printStackTrace();
		}
		return resultString;
	}
	
	public String decrypt(String encrypted){
		byte[] rawKey;
		String resultString;
		try {
			rawKey = getRawKey(SEED.getBytes());
			byte[] enc = toByte(encrypted);
			byte[] result = decrypt(rawKey, enc);
			resultString = new String(result);
			
		} catch (Exception e) {
			resultString = encrypted;
			e.printStackTrace();
		}
		return resultString;
	}

	private byte[] getRawKey(byte[] seed) throws Exception {
//		KeyGenerator kgen = KeyGenerator.getInstance("AES");
//		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//		sr.setSeed(seed);
//	    kgen.init(128, sr); // 192 and 256 bits may not be available
//	    SecretKey skey = kgen.generateKey();
//	    byte[] raw = skey.getEncoded();
		byte[] raw = {119, -106, 119, 101, -97, 32, 103, -52, 89, -10, 10, -10, -102, 84, -1, -63};
	    return raw;
	}

	
	private byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
	    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	    byte[] encrypted = cipher.doFinal(clear);
		return encrypted;
	}

	private byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
	    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	    byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}

	public String toHex(String txt) {
		return toHex(txt.getBytes());
	}
	public String fromHex(String hex) {
		return new String(toByte(hex));
	}
	
	public byte[] toByte(String hexString) {
		int len = hexString.length()/2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++)
			result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
		return result;
	}

	public String toHex(byte[] buf) {
		if (buf == null)
			return "";
		StringBuffer result = new StringBuffer(2*buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}
	
	private void appendHex(StringBuffer sb, byte b) {
		sb.append(HEX.charAt((b>>4)&0x0f)).append(HEX.charAt(b&0x0f));
	}
	
    public String toMysqlDate(Date date){
  	  if (date==null) return "NULL";
  	  SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  	  return sqlValueWithQuotas(sdf.format(date));
  	 }

  	 private static String sqlValueWithQuotas(Object obj){
  	  if ( obj == null ) return "NULL";
  	  
  	  String str = obj.toString();
  	  str.replaceAll("'", "\\'");
  	  str = '\''+str+'\'';
  	  
  	  return str;
  	  
  	 }
}
