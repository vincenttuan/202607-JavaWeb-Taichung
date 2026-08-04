package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * GameDao 負責存取 game_record 資料表
 * 
 * DAO = Data Access Object
 * 
 * DAO 的責任
 * 1. 建立資料庫連線
 * 2. 執行 insert 新增紀錄
 * 3. 執行 select 查詢紀錄
 * 4. 將 ResultSet 轉成 Record 物件
 * */

public class GameDao {
	
	/**
	 * MySQL 連線資訊
	 * 資料庫位置與連接阜: jdbc:mysql://localhost:3306/game_db
	 * 
	 * 因為沒有 ssl 所以要能夠取得公鑰
	 * useSSL=false
	 * allowPublicKeyRetrieval=true
	 * */
	private static final String URL =
			"jdbc:mysql://localhost:3306/game_db"
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
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	
	
	
	
	
	
	
	
	
}
