package servlet;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ice")
public class IceServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// 接收參數
		String iceName = req.getParameter("iceName");
		String size = req.getParameter("size");
		String sweet = req.getParameter("sweet");
		String ice = req.getParameter("ice");
		String[] toppings = req.getParameterValues("topping");
		String qty = req.getParameter("qty");
		String pickupDate = req.getParameter("pickupDate");
		String birthday = req.getParameter("birthday") == null ? "否" : "壽星";
		String memo = req.getParameter("memo");
		
		// 訂單內容
		String html = """
				<html>
					<head>
						<title>胖胖冰果店訂單</title>
					</head>
					<body>
						<h1>胖胖冰果店訂單</h1>
						冰品：%s<br />
						大小：%s<br />
						甜度：%s<br />
						冰量：%s<br />
						配料：%s<br />
						數量：%s<br />
						取餐日期：%s<br />
						壽星：%s<br />
						備註：%s<br />
					</body>
				</html>
				""".formatted(iceName, size, sweet, ice, Arrays.toString(toppings), qty, pickupDate, birthday, memo);
		
		
		resp.getWriter().print(html);
	}
	
}
