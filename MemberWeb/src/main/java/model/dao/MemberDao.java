package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.util.DBUtil;

public class MemberDao {
	
	/**
	 * 新會員註冊
	 * */
	public void register(String username, String email, String role, String salt, String hash) {
		String sql = """
				insert into member(username, email, role, salt, hash)
				values(?, ?, ?, ?, ?);
				""";
		
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, username);
			pstmt.setString(2, email);
			pstmt.setString(3, role);
			pstmt.setString(4, salt);
			pstmt.setString(5, hash);
			
			// 更新
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("新增失敗: " + e.getMessage());
		}
		
		
	}
	
}
