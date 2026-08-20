package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.Member;
import model.util.DBUtil;
import model.util.SHA256Util;

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
	
	/**
	 * 登入判斷
	 * 成功會得到: Member 物件
	 * 失敗會得到: null
	 * */
	public Member login(String username, String password) {
		String sql = """
				select id, username, salt, hash, email, role, create_time from member where username=?
				""";
		
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, username);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				
				// 1.是否有該使用者
				// rs.next() true 表示有資料
				// rs.next() false 表示無資料
				if(!rs.next()) {
					throw new RuntimeException("登入失敗, 查無使用者");
				}
				
				// 取出 salt 與 hash
				String dbSalt = rs.getString("salt");
				String dbHash = rs.getString("hash");
				
				// 計算 password 與 dbSalt 的 hash 值
				String hash = SHA256Util.hash(password, dbSalt);
				
				// 比對 dbHash 與 hash 是否相同 ?
				if(!dbHash.equals(hash)) {
					throw new RuntimeException("登入失敗, 密碼錯誤");
				}
				
				// 登入成功, 建立 Member 物件並內容注入
				Member member = new Member();
				member.setId(rs.getInt("id"));
				member.setUsername(rs.getString("username"));
				member.setEmail(rs.getString("email"));
				member.setRole(rs.getString("role"));
				member.setSalt(rs.getString("salt"));
				member.setHash(rs.getString("hash"));
				member.setCreateTime(rs.getDate("create_time"));
				
				return member;
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("登入失敗, " + e.getMessage());
		}
		
		
		
	}
	
	
	/**
	 * 更新 email
	 * */
	public void updateEmail(Integer id, String email) {
		String sql = """
				update member set email=? where id=?
				""";
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, email);
			pstmt.setInt(2, id);
			
			// 執行更新
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("修改 email 失敗, " + e.getMessage());
		}
		
	}
	
	
	/**
	 * 更新 password
	 * */
	public void updatePassword(Integer id, String salt, String hash) {
		String sql = """
				update member set salt=?, hash=? where id=?
				""";
		
		try() {
			
		} catch (SQLException e) {
			throw new RuntimeException("更新密碼失敗, " + e.getMessage());
		}
		
		
		
	}
	
	
}
