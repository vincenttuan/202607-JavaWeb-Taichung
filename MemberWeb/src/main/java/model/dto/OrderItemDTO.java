package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
	
	private Long productId;
	private String productName;
	private Integer unitPrice;
	private Integer quantity;
	
	// 小計
	private Long subtotal;
	
}
