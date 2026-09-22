package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	 * 
	 * Transaction 交易
	 * 1. 查詢商品庫存
	 * 2. 計算總金額
	 * 3. 建立 MemberOrder -> 得到訂單編號
	 * 4. 取得訂單編號後 -> 建立 OrderItem
	 * 5. 扣除庫存
	 * 
	 * 成功 -> commit (任務提交)
	 * 失敗 -> rollback (回滾)
	 * */
	public void createOrder(int memberId, List<OrderItemDto> items) {
		// 查詢商品庫存 sql
		String productSql = "select id, stock from product where id=?";
		
		// 新增訂單 sql
		String orderSql = "insert into member_order (member_id, total_amount, status) values(?, ?, ?)";
		
		// 新增訂單項目 sql
		String itemSql = """
					insert into order_item (order_id, product_id, product_name, unit_price, quantity, subtotal)
					values(?, ?, ?, ?, ?, ?) 
				""";
		
		// 修改商品庫存 sql
		String stockSql = """
					update product set stock = stock - ?
					where id = ?
				""";
		
		// 資料庫(交易)處理程序
		try(Connection conn = DBUtil.getConnection()) {
			// 1.開始 Transaction
			conn.setAutoCommit(false); // 支援手動 commit
			
			int total = 0; // 總金額
			
			// 2.檢查商品存在與否? 庫存足量 ?
			for(OrderItemDto item : items) {
				
				PreparedStatement ps = conn.prepareStatement(productSql);
				ps.setInt(1, item.getProductId());
				
				ResultSet rs = ps.executeQuery();
				
				// 檢查商品存在與否?
				if(!rs.next()) {
					throw new RuntimeException("商品不存在: " + item.getProductId());
				}
				
				// 庫存足量 ?
				int stock = rs.getInt("stock"); // 目前該商品的庫存
				if(stock < item.getQuantity()) {
					throw new RuntimeException(item.getProductName() + " 庫存不足");
				}
				
				// 計算 total
				total += item.getSubtotal();
				
				rs.close();
				ps.close();
			}
			
			// 3.建立訂單
			int orderId;
			try(PreparedStatement ps = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
				
				ps.setInt(1, memberId);
				ps.setInt(2, total);
				ps.setString(3, "PAID");
				
				ps.executeUpdate();
				
				// 取得 orderId 的紀錄
				ResultSet rs = ps.getGeneratedKeys();
				
				if(!rs.next()) {
					throw new RuntimeException("無法取得訂單編號");
				}
				
				orderId = rs.getInt(1); // 得到 orderId
				
			}
			
			// 4.建立訂單明細
			for(OrderItemDto item : items) {
				
				try(PreparedStatement ps = conn.prepareStatement(itemSql)) {
					
					ps.setInt(1, orderId);
					ps.setInt(2, item.getProductId());
					ps.setString(3, item.getProductName());
					ps.setInt(4, item.getUnitPrice());
					ps.setInt(5, item.getQuantity());
					ps.setInt(6, item.getSubtotal());
					
					ps.executeUpdate();
				}
				
				// 5.扣庫存
				try(PreparedStatement ps = conn.prepareStatement(stockSql)) {
					ps.setInt(1, item.getQuantity());
					ps.setInt(2, item.getProductId());
					
					ps.executeUpdate();
				}
			}
			
			
			// 提交確認
			conn.commit();
			
		} catch (SQLException e) {
			throw new RuntimeException("結帳失敗:" + e);
		}
		
		
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










