package test;

import org.junit.jupiter.api.Test;

import model.entity.Member;
import service.OrderService;

public class TestOrderService {
	
	@Test
	public void test() {
		
		OrderService orderService = new OrderService();
		Member member = new Member();
		//member.setId(1);
		//member.setRole("ADMIN");
		member.setId(3);
		member.setRole("John");
				
		orderService.findAllByMember(member)
					.forEach(System.out::println);
		
	}
	
}
