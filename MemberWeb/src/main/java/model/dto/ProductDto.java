package model.dto;

import lombok.Data;

@Data
public class ProductDto {
	private Integer id;
	private String name;
	private String category;
	private Integer price;
	private Integer stock;
	private String imageBase64;
	private String imageType;
}
