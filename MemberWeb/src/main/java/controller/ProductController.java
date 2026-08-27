package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 商品 Controller
 * 
 * GET:
 * 給予 UI 畫面
 * 新增畫面 /products?action=new
 * 修改畫面 /products?action=edit
 * 列表畫面 /products?action=list 或 /products
 * 
 * POST:
 * 修改資料
 * 新增動作 /products?action=insert
 * 修改動作 /products?action=update
 * 刪除動作 /products?action=delete
 * 
 * */

@WebServlet("/products")
public class ProductController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("action") + "";
		
		switch(action) {
			case "new" -> showCreateForm(req, resp);
			
		}
		
	}
	
	// 顯示新增表單
	private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/product-form.jsp");
		req.setAttribute("formTitle", "新增");
		req.setAttribute("formAction", "insert");
		rd.forward(req, resp);
	}
	
	
}
