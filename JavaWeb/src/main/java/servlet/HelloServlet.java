package servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 取得現在時刻
		LocalDateTime dt = LocalDateTime.now();
		
		// 建立 html 字串
		String html = "now => " + dt.getHour() + ":" + dt.getMinute() + ":" + dt.getSecond();
		
		// 回應
		resp.getWriter().print(html);
	}
	
}
