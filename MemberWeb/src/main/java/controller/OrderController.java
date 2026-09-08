package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action == null) action = "";
		
		switch(action) {
			case "insert" -> addToCart(req, resp); 
		}
	}
	
	// 新增到購物車
	private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;chatset=utf-8");
		
		String productId = req.getParameter("productId");
		
		Optional<ProductDto> productDtoOpt = productService.findById(Integer.valueOf(productId));
		
		if(productDtoOpt.isEmpty()) {
			resp.getWriter().print("productId = " + productId + " not found !");
			return;
		}
		
		ProductDto productDto = productDtoOpt.get();
		resp.getWriter().println("購入商品:<p />");
		resp.getWriter().println(productDto);
		
		resp.getWriter().println("<hr />");
		
		// 先判斷 session 變數中是否有購物車資料 ?
		HttpSession session = req.getSession();
		// 購物車宣告
		Map<ProductDto, Integer> cart = null;
		if(session.getAttribute("CART") == null) { // 沒有購物車資訊
			// 建立一個 Map 保存選購的商品 (新建立一個購物車)
			cart = new LinkedHashMap<>();
			// 存放到 session 變數中
			session.setAttribute("CART", cart);
		}
		
		// 自 session 變數中取得購物車資訊
		cart = (Map)session.getAttribute("CART");
		
		// 將商品加入到購物車中
		cart.put(productDto, cart.getOrDefault(productDto, 0) + 1);
		
		// 回存到 session 變數中 
		session.setAttribute("CART", cart);
		
		resp.getWriter().println("購物車:<p />");
		resp.getWriter().println("商品種類: " + cart.size() + " 種<p />");
		resp.getWriter().println("商品明細: " + cart + "<p />");
		
		
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
