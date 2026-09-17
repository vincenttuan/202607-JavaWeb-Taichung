package model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	 * 查詢所有訂單
	 * key:欄位名稱 <String>
	 * value:欄位內容 <Object>
	 * 每一筆訂單的資料容器: Map<String, Object>
	 * 放置所有訂單資料容器: List<Map<String, Object>>
	 * */
	public List<Map<String, Object>> findAllOrders() {
		List<Map<String, Object>> rows = new ArrayList<>();
		
		
		return rows;
	}
	
	
}










