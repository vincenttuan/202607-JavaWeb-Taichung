package model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	static {
		// 建立 MySQL Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * MySQL 連線資訊
	 * 資料庫位置與連接阜: jdbc:mysql://localhost:3306/shopping
	 * 
	 * 因為沒有 ssl 所以要能夠取得公鑰
	 * useSSL=false
	 * allowPublicKeyRetrieval=true
	 * */
	private static final String URL =
			"jdbc:mysql://localhost:3306/shopping"
			+ "?useUnicode=true"
			+ "&characterEncoding=UTF-8"
			+ "&serverTimezone=Asia/Taipei"
			+ "&useSSL=false"
			+ "&allowPublicKeyRetrieval=true";
	
	// 帳號
	private static final String USERNAME = "root";
	
	// 密碼
	private static final String PASSWORD = "12345678";
	
	// 建立資料庫連線
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
