package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
	
	private Integer productId;
	private String productName;
	private Integer unitPrice;
	private Integer quantity;
	
	// 小計
	private Integer subtotal;
	
}
