package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.ProductDto;
import service.ProductService;

/**
 * 訂購商品
 * 
 * 會依照 action 的參數內容有不同的行為
 * -- GET ----
 * action=         -> 訂購商品主頁
 * action=cart     -> 查看購物車
 * action=checkout -> 結帳頁
 * action=history  -> 歷史訂單頁
 * action=success  -> 交易成功頁
 * 
 * -- POST ----
 * action=insert   -> 將商品新增到購物車
 * action=update   -> 修改購物車商品
 * action=remove   -> 移除購物車商品
 * action=checkout -> 結帳
 * */
@WebServlet("/order")
public class OrderController extends HttpServlet {
	
	private ProductService productService = new ProductService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action == null) action = "";
		
		switch(action) {
			case "" -> showProduct(req, resp); 
		}
		
	}
	
	// 呈現商品資料
	private void showProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 查詢所有商品
		List<ProductDto> productDtos = productService.findAll();
		// 商品分類
		String[][] categories = {
				{"BURGER", "🍔 漢堡類"}, {"SNACK", "🍟 點心類"}, {"DRINK", "🥤 飲料類"}
		};
		
		req.setAttribute("products", productDtos);
		req.setAttribute("categories", categories);
		
		//resp.getWriter().print(productDtos);
		//resp.getWriter().print(categories);
		
		// 重導到訂購商品主頁
		req.getRequestDispatcher("/WEB-INF/view/order-main.jsp").forward(req, resp);
		
	}
	
}
