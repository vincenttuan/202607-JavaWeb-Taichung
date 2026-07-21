package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
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
		
		// 判斷 bmi 正常, 過重, 過輕 
		String result = (bmi > 23) ? "過重" : (bmi <= 18) ? "過輕" : "正常";
		
		/*
		// html
		String html = "身高: %.1f 體重: %.1f BMI: %.2f 判斷: %s".formatted(h, w, bmi, result);
		
		// 回應
		resp.getWriter().print(html);
		*/
		
		// 1.準備要給 jsp 的參數資料
		req.setAttribute("h", h);
		req.setAttribute("w", w);
		req.setAttribute("bmi", bmi);
		req.setAttribute("result", result);
		
		// 2.建立一個"請求分派器"用來告知要傳到哪一個 jsp 檔案
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/bmi_result.jsp");
		
		// 3.forward 傳送
		rd.forward(req, resp);
	}
	
}
