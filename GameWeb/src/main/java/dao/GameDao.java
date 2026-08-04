package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Record;

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
	
	// 新增資料紀錄
	public void addRecord(String username, Record record) {
		String sql = """
				insert into game_record (username, player, server, result) values(?, ?, ?, ?)
				""";
		
		try(Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			// 每一個問號要放的內容
			pstmt.setString(1, username);
			pstmt.setInt(2, record.getPlayer());
			pstmt.setInt(3, record.getServer());
			pstmt.setString(4, record.getResult());
			
			// 執行更新
			pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("新增失敗, 原因:" + e.getMessage());
		}
		
	}
	
	
	
	
	
	
	
	
}
