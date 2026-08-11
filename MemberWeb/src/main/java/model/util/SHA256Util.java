package model.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class SHA256Util {
	
	/**
	 * SHA-256 加密 + Salt hash
	 * 功能:
	 * 將使用者所輸入的文字經過 SHA-256 演算法後
	 * 轉成 64 個 16 進位的字元(hash)
	 * 
	 * 例如:
	 * 原始密碼: 1234
	 * 雜湊資料: 03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4
	 * 
	 * */
	
	public static String hash(String text, String salt) {
		try {
			
			// 密碼加鹽
			String textSalt = text + salt;
			
			// 1. 取得 SHA-256 的 MessageDigest 物件
			// MessageDigest 是 Java 提供的雜湊(hash)工具
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			// 2. 將 String 轉 byte[]
			// 因為 SHA-256 只能處理位元組資料(byte[])
			byte[] textBytes = textSalt.getBytes(StandardCharsets.UTF_8);
			
			// 3. 執行 SHA-256
			byte[] hashBytes = md.digest(textBytes);
			
			// 4. 將 byte[] 轉 16 進位字串
			StringBuilder sb = new StringBuilder();
			for(byte b : hashBytes) {
				// byte 是 -128 ~ 127, 透過 & 0xff 會變成 0~255 的資訊
				sb.append(String.format("%02x", b & 0xff));
			}
			
			// 5. 回傳 hash 字串
			return sb.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 
	
	// 產生鹽
	public static String generateSalt() {
		
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[16];
		random.nextBytes(bytes);
		
		StringBuilder sb = new StringBuilder();
		for(byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		
		return sb.toString();
	}
	
	
}
