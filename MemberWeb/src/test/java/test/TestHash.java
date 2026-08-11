package test;

import model.util.SHA256Util;

public class TestHash {

	public static void main(String[] args) {
		// 原始密碼
		String password = "1234";
		// 鹽
		String salt = SHA256Util.generateSalt();
		// 原始密碼 + 鹽 = 加鹽版哈希
		String hash = SHA256Util.hash(password, salt);
		
		System.out.printf("原始密碼: %s%n", password);
		System.out.printf("鹽: %s%n", salt);
		System.out.printf("加鹽版哈希: %s%n", hash);

	}

}
