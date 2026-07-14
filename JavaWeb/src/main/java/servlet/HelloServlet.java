package servlet;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/hello")
@WebServlet(name = "Hello", urlPatterns = {"/hello", "/hi", "/any/*"})
public class HelloServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 設定請求編碼
		req.setCharacterEncoding("UTF-8");
		// 設定回應編碼
		resp.setCharacterEncoding("UTF-8");
		// 設定瀏覽器編碼
		resp.setContentType("text/html;charset=UTF-8");
		
		// 取得現在時刻
		LocalDateTime dt = LocalDateTime.now();
		
		// 取得 servlet path
		// 會得到 "/hello" 或 "/hi"
		String servletPath = req.getServletPath();
		
		// 取得 Path info
		// 取得 /* 的資訊
		String pathInfo = req.getPathInfo();
		
		// 建立 html 字串
		String html = "現在時刻 => " + dt.getHour() + ":" + dt.getMinute() + ":" + dt.getSecond();
		html += "<p />ServletPath = " + servletPath;
		html += "<p />PathInfo = " + pathInfo;
				
		// 回應
		resp.getWriter().print(html); 
	}
	
}
