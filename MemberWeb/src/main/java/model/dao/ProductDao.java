package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.Product;
import model.util.DBUtil;

/**
 * ProductDao
 * 負責 JDBC 與 MySQL
 * 資料使用 Product (entity)
 * */
public class ProductDao {
	
	// 新增商品
	public void insert(Product product) {
		String sql = """
				insert into product(name, category, price, stock, image_base64, image_type)
				values(?, ?, ?, ?, ?, ?)  
				""";
		
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, product.getName());
			pstmt.setString(2, product.getCategory());
			pstmt.setInt(3, product.getPrice());
			pstmt.setInt(4, product.getStock());
			pstmt.setString(5, product.getImageBase64());
			pstmt.setString(6, product.getImageType());
			
			// 執行更新
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("新增失敗: " + e.getMessage());
		}
	}
	
	// 查詢所有商品
	
	
	
}
