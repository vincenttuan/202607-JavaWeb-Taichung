package service;

import model.dao.ProductDao;
import model.dto.ProductDto;
import model.entity.Product;

/**
 * Product Service
 * 負責資料驗證, 商業邏輯與 DTO / Entity 之間的轉換
 * */
public class ProductService {
	
	private ProductDao productDao = new ProductDao();
	
	// 新增商品
	public void create(ProductDto productDto) {
		
		// dto 轉換 entity
		Product product = toEntity(productDto);
		
		// 儲存 product (entity)
		productDao.insert(product);
	}
	
	// 轉 entity
	private Product toEntity(ProductDto productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setCategory(productDto.getCategory());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.getStock());
		product.setImageBase64(productDto.getImageBase64());
		product.setImageType(productDto.getImageType());
		
		return product;
	}
	
	
	
}
