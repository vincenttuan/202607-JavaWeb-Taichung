package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.dto.OrderItemDto;
import model.util.DBUtil;

/**
 查詢所有訂單的 SQL
 select
    o.id as order_id,
    m.id as member_id,
    m.username as member_name,
    m.email as member_email,
    o.total_amount,
    o.create_at,
    i.id as item_id,
    i.product_name,
    i.unit_price,
    i.quantity,
    i.subtotal
from member_order o

left join member m
    on m.id = o.member_id

left join order_item i
    on i.order_id = o.id

where i.id is not null

order by
    o.create_at desc,
    o.id desc,
    i.id;
 * */


public class OrderDao {
	
	/**
	 * 建立訂單
	 * */
	public void createOrder(int memberId, List<OrderItemDto> items) {
		
	}
	
	/**
	 * 查詢所有訂單
	 * key:欄位名稱 <String>
	 * value:欄位內容 <Object>
	 * 每一筆訂單的資料容器: Map<String, Object>
	 * 放置所有訂單資料容器: List<Map<String, Object>>
	 * */
	public List<Map<String, Object>> findAllOrders() {
		List<Map<String, Object>> rows = new ArrayList<>();
		
		/*
		String sql = """
				select
				    o.id as order_id,
				    m.id as member_id,
				    m.username as member_name,
				    m.email as member_email,
				    o.total_amount,
				    o.create_at,
				    i.id as item_id,
				    i.product_name,
				    i.unit_price,
				    i.quantity,
				    i.subtotal
				from member_order o
				left join member m
				    on m.id = o.member_id
				left join order_item i
				    on i.order_id = o.id
				where i.id is not null
				order by
				    o.create_at desc,
				    o.id desc,
				    i.id;
				""";
		*/
		
		// 用 view 來查詢
		String sql = "SELECT * FROM find_all_orders_view";
		
		try(Connection conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) { // 走訪每一筆紀錄
				// 建立 Map 資料容器用來放尋訪到的每一筆紀錄
				Map<String, Object> row = new LinkedHashMap<>();
				
				row.put("order_id",     rs.getInt("order_id"));
				row.put("member_id",    rs.getInt("member_id"));
				row.put("member_name",  rs.getString("member_name"));
				row.put("member_email", rs.getString("member_email"));
				row.put("total_amount", rs.getInt("total_amount"));
				row.put("create_at",    rs.getTimestamp("create_at"));
				row.put("item_id",      rs.getInt("item_id"));
				row.put("product_name", rs.getString("product_name"));
				row.put("unit_price",   rs.getInt("unit_price"));
				row.put("quantity",     rs.getInt("quantity"));
				row.put("subtotal",     rs.getInt("subtotal"));
				
				// 加入到 rows 中
				rows.add(row);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("訂單查詢失敗: " + e.getMessage());
		}
		
		return rows;
	}
	
	
}










