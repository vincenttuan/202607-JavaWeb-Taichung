package servlet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

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
		
		// QRCode 內容
		String text = "冰品：%s 大小：%s 甜度：%s 冰量：%s 配料：%s 數量：%s 取餐日期：%s 壽星：%s 備註：%s"
						.formatted(iceName, size, sweet, ice, Arrays.toString(toppings), qty, pickupDate, birthday, memo);
		// QRCode 檔名
		String fileName = "ice_qrcode.png";
		
		// QRCode 存放位置
		String path = getServletContext().getRealPath("/images/" + fileName);
		
		System.out.println(path);
		// 產生 QRCode
		createQRCode(text, path);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
						備註：%s<p />
						
						<img src='/JavaWeb/images/%s'>
						
					</body>
				</html>
				""".formatted(iceName, size, sweet, ice, Arrays.toString(toppings), qty, pickupDate, birthday, memo, fileName);
		
		
		resp.getWriter().print(html);
	}
	
	// 使用 ZXing 產生 QRCode
	private void createQRCode(String text, String path) {
		try {
			BitMatrix matrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, 300, 300);
			MatrixToImageWriter.writeToPath(matrix, "PNG", new File(path).toPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
