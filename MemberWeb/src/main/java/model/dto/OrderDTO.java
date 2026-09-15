package model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private Integer orderId;
	private Integer totalAmount;
	
	private String customerName;
	private String customerEmail;
	
	private String createdAt;
	
	private List<OrderItemDTO> items = new ArrayList<>();
	
	public OrderDTO(Integer orderId, Integer totalAmount) {
		this.orderId = orderId;
		this.totalAmount = totalAmount;
	}
}
