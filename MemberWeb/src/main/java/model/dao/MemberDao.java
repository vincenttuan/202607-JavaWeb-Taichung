package model.dao;

import java.sql.Connection;
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
		
		try(Connection conn = DBUtil.) {
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
