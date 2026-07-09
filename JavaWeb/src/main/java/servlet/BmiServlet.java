package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bmi")
public class BmiServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// 變數宣告
		double h = 170.5;
		double w = 64.0;
		
		// 計算 bmi
		double bmi = w / Math.pow(h/100, 2);
		
		// html
		String html = "身高: %.1f 體重: %.1f BMI: %.1f".formatted(h, w, bmi);
		
		// 回應
		resp.getWriter().print(html);
		
	}
	
}
