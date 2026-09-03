package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
		
		
	}
	
}
