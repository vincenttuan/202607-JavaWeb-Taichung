package servlet;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/lotto")
public class LottoServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 設定編碼
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// 生成亂數
		Random random = new Random();
		int num1 = random.nextInt(10); // 0~9
		int num2 = random.nextInt(10); // 0~9
		int num3 = random.nextInt(10); // 0~9
		int num4 = random.nextInt(10); // 0~9
		
		// 產生 html
		String html = "四星彩電腦選號: %d%d%d%d";
		html = String.format(html, num1, num2, num3, num4);
		
		// 回應
		resp.getWriter().print(html);
	}
	
}
