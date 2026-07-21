package servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 胖胖冰果店訂單 Servlet
 * 
 * 功能：
 * 1. 接收訂單資料
 * 2. 將訂單內容產生 QRCode
 * 3. QRCode 轉成 Base64 字串
 * 4. 直接嵌入 HTML 顯示，不需產生圖片檔
 */
@WebServlet("/ice")
public class IceServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// ===========================
		// 設定編碼，避免中文亂碼
		// ===========================
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// ===========================
		// 接收使用者輸入資料
		// ===========================
		String iceName = req.getParameter("iceName");
		String size = req.getParameter("size");
		String sweet = req.getParameter("sweet");
		String ice = req.getParameter("ice");
		String[] toppings = req.getParameterValues("topping");
		String qty = req.getParameter("qty");
		String pickupDate = req.getParameter("pickupDate");
		String bowlColor = req.getParameter("bowlColor");
		
		// checkbox 若沒勾選會回傳 null
		String birthday = req.getParameter("birthday") == null ? "否" : "壽星";

		String memo = req.getParameter("memo");

		// ===========================
		// 組合 QRCode 內容
		// ===========================
		String text = """
				冰品：%s
				大小：%s
				甜度：%s
				冰量：%s
				配料：%s
				碗色：%s
				數量：%s
				取餐日期：%s
				壽星：%s
				備註：%s
				""".formatted(
						iceName,
						size,
						sweet,
						ice,
						Arrays.toString(toppings),
						bowlColor,
						qty,
						pickupDate,
						birthday,
						memo);

		// ===========================
		// 產生 Base64 QRCode
		// ===========================
		String qrCodeBase64 = createQRCodeBase64(text);

		// ===========================
		// 回傳 HTML
		// ===========================
		String html = """
				<html>
					<head>
						<meta charset="UTF-8">
						<title>胖胖冰果店訂單</title>
					</head>

					<body>

						<h1>胖胖冰果店訂單</h1>

						冰品：%s<br>
						大小：%s<br>
						甜度：%s<br>
						冰量：%s<br>
						配料：%s<br>
						碗色：%s<br>
						數量：%s<br>
						取餐日期：%s<br>
						壽星：%s<br>
						備註：%s

						<p>

						<h3>訂單 QRCode</h3>

						<img src="%s" width="250">

					</body>

				</html>
				""".formatted(
						iceName,
						size,
						sweet,
						ice,
						Arrays.toString(toppings),
						bowlColor,
						qty,
						pickupDate,
						birthday,
						memo,
						qrCodeBase64);

		// 將 HTML 回傳給瀏覽器
		resp.getWriter().print(html);
	}

	/**
	 * 產生 QRCode 並轉成 Base64
	 *
	 * @param text QRCode 要儲存的文字
	 * @return Base64 Data URI，可直接放到 img src
	 */
	private String createQRCodeBase64(String text) {

		try {

			// ===========================
			// ZXing 產生 QRCode 的設定
			// ===========================
			Map<EncodeHintType, Object> hints = new HashMap<>();

			// 使用 UTF-8，避免中文變成 ??
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

			// QRCode 外圍白邊大小
			hints.put(EncodeHintType.MARGIN, 1);

			// ===========================
			// 建立 QRCode
			// ===========================
			BitMatrix matrix = new QRCodeWriter().encode(
					text,
					BarcodeFormat.QR_CODE,
					300,
					300,
					hints);

			// ===========================
			// 將 QRCode 寫入記憶體(ByteArray)
			// 不建立任何 png 檔案
			// ===========================
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			MatrixToImageWriter.writeToStream(
					matrix,
					"PNG",
					baos);

			// ===========================
			// Byte[] → Base64
			// ===========================
			String base64 = Base64.getEncoder()
					.encodeToString(baos.toByteArray());

			// ===========================
			// 回傳 Data URI
			// 可直接放到
			// <img src="data:image/png;base64,...">
			// ===========================
			return "data:image/png;base64," + base64;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

}