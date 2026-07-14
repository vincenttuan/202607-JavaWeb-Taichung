package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/drink")
public class DrinkServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
				
		// 參數範例: name=珍珠奶茶&size=L&suger=3&ice=0&qty=3
		String name  = req.getParameter("name"); 
		String size  = req.getParameter("size");
		String sugar = req.getParameter("sugar");
		String ice   = req.getParameter("ice");
		String qty   = req.getParameter("qty");
		
		// html
		String html = """
				品名: %s<p />
				尺寸: %s<p />
				糖分: %s<p />
				冰量: %s<p />
				數量: %s<p />
				""".formatted(name, size, sugar, ice, qty);
		
		// 回應
		resp.getWriter().print(html);
	}
	
}
