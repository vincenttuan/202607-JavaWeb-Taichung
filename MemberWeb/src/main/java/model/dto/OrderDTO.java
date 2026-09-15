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
	
	private Long orderId;
	private Long totalAmount;
	
	private String customerName;
	private String customerPhone;
	private String createdAt;
	private List<OrderItemDTO> items = new ArrayList<>();
	
	public OrderDTO(Long orderId, Long totalAmount) {
		this.orderId = orderId;
		this.totalAmount = totalAmount;
	}
}
