package service;

import java.time.format.DateTimeFormatter;

import model.dao.OrderDao;

public class OrderService {
	
	private OrderDao orderDao = new OrderDao();
	
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss E");
	
	/**
	 * 查詢所有訂單 
	 * 
	 * List<Map<String, Object>> -> 轉 -> List<OrderDto>
	 * 
	 * */
	
}
