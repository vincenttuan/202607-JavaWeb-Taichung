package service;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.dao.OrderDao;
import model.dto.OrderDto;
import model.dto.OrderItemDto;

public class OrderService {
	
	private OrderDao orderDao = new OrderDao();
	
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss E");
	
	/**
	 * 查詢所有訂單 
	 * 
	 * List<Map<String, Object>> -> 轉 -> List<OrderDto>
	 * 
	 * */
	public List<OrderDto> findAll() {
		// 取得原始訂單資料
		List<Map<String, Object>> rows = orderDao.findAllOrders();
		
		// 因為同一個訂單在資料庫裏面會出現多次
		// Key: 訂單編號, Value: OrderDto 
		Map<Integer, OrderDto> orders = new LinkedHashMap<>();
		
		// 將 rows 轉 dto
		for(Map<String, Object> row : rows) {
			// 取得訂單 id
			int orderId = (Integer)row.get("order_id");
			
			// 建立訂單 OrderDto
			// 判斷 orders 是否已有 orderId
			if(!orders.containsKey(orderId)) {
				OrderDto orderDto = new OrderDto();
				orderDto.setOrderId(orderId);
				orderDto.setCustomerName((String)row.get("member_name"));
				orderDto.setCustomerEmail((String)row.get("member_email"));
				orderDto.setTotalAmount((Integer)row.get("total_amount"));
				orderDto.setCreatedAt(((Timestamp)row.get("create_at")).toLocalDateTime().format(DATE_TIME_FORMATTER));
				
				// 將 OrderDto 放入到 Map 集合
				orders.put(orderId, orderDto);
			}
			
			// 建立訂單明細 OrderItemDto
			OrderItemDto orderItemDto = new OrderItemDto();
			orderItemDto.setProductName((String)row.get("product_name"));
			orderItemDto.setUnitPrice((Integer)row.get("unit_price"));
			orderItemDto.setQuantity((Integer)row.get("quantity"));
			orderItemDto.setSubtotal((Integer)row.get("subtotal"));
			
			// 找到訂單
			OrderDto orderDto = orders.get(orderId);
			// 將訂單明細加入到訂單主檔
			orderDto.getItems().add(orderItemDto);
		}
		
		return new ArrayList<>(orders.values());
	}
	
	
	
	
}





