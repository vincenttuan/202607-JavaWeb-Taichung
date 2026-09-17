package test;

import org.junit.jupiter.api.Test;

import model.dao.OrderDao;

public class TestOrderDao {
	
	@Test
	public void test() {
		
		OrderDao orderDao = new OrderDao();
		orderDao.findAllOrders()
				.forEach(System.out::println);
		
	}
	
}
