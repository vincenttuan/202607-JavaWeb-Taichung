package test;

import org.junit.jupiter.api.Test;

import service.OrderService;

public class TestOrderService {
	
	@Test
	public void test() {
		
		OrderService orderService = new OrderService();
		orderService.findAll()
					.forEach(System.out::println);
		
	}
	
}
