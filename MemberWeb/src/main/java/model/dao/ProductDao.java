package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public List<Product> findAll() {
		// 用來放置所有商品的集合
		List<Product> products = new ArrayList<>();
		
		String sql = """
				select id, name, category, price, stock, image_base64, image_type, create_at, update_at
				from product
				order by id
				""";
		
		try(Connection conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) {
				// 建立 entity
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getInt("price"));
				product.setStock(rs.getInt("stock"));
				product.setImageBase64(rs.getString("image_base64"));
				product.setImageType(rs.getString("image_type"));
				product.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());
				product.setUpdateAt(rs.getTimestamp("update_at").toLocalDateTime());
				
				// 放入集合
				products.add(product);
			}
		} catch (SQLException e) {
			throw new RuntimeException("查詢失敗: " + e.getMessage());
		}
		
		return products;
	} 
	
	
}
