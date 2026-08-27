package controller;

import java.io.IOException;
import java.util.Base64;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

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
@MultipartConfig(
		fileSizeThreshold = 2*1024*1024, // 2MB
		maxFileSize = 2*1024*1024, // 2MB(單一檔最大值)
		maxRequestSize = 2*1024*1024 // 2MB(全部最大值)
)
public class ProductController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("action") + "";
		
		switch(action) {
			case "new" -> showCreateForm(req, resp);
			
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("action") + "";
		
		switch(action) {
			case "insert" -> insert(req, resp);
			
		}
		
	}
	
	// 新增商品
	private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;chatset=utf-8");
		
		// 一般表單欄位
		String name = req.getParameter("name");
		String category = req.getParameter("category");
		String price = req.getParameter("price");
		String stock = req.getParameter("stock");
		
		// 上傳檔案(file)欄位
		Part imagePart = req.getPart("imageFile");
		// 圖檔型態(格式)
		String imageType = imagePart.getContentType();
		// 資料轉換
		byte[] imageBytes = imagePart.getInputStream().readAllBytes(); // 讀取圖檔串流
		String imageBase64 = Base64.getEncoder().encodeToString(imageBytes); // 將圖片轉 base64
		
		resp.getWriter().print("name: " + name + "<p />");
		resp.getWriter().print("category: " + category + "<p />");
		resp.getWriter().print("price: " + price + "<p />");
		resp.getWriter().print("stock: " + stock + "<p />");
		resp.getWriter().print("imageType: " + imageType + "<p />");
		resp.getWriter().print("imageBase64: " + imageBase64 + "<p />");
		resp.getWriter().print("<img src='data:" + imageType + ";base64," + imageBase64 + "'>");
		
	}
	
	// 顯示新增表單
	private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/product-form.jsp");
		req.setAttribute("formTitle", "新增");
		req.setAttribute("formAction", "insert");
		rd.forward(req, resp);
	}
	
	
}
