package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fruit/*")
public class FruitServlet extends HttpServlet {
	
	private String[] fruits = {"Apple", "Banana", "Watermelon", "Orange", "Papaya", "Mango"};
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String pathInfo = req.getPathInfo(); // 例如: /1, /2, /3 ... 注意:包含 "/"
		// 去除 "/" 並轉 int
		int index = Integer.parseInt(pathInfo.replace("/", "")); // 例如: 1, 2, 3 ...
		
		String fruit = fruits[index];
		
		// html
		String html = """
					PathInfo: %s<p />
					Index: %d<p />
					Fruit: %s<p />
				""".formatted(pathInfo, index, fruit);
		
		// 回應
		resp.getWriter().print(html);
	}

}
