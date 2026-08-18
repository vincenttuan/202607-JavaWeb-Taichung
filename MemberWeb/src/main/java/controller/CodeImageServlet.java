package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;

import javax.imageio.ImageIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 圖形認證碼
 * 利用 Java2D 圖學技術動態產生認證碼
 * */
@WebServlet("/code")
public class CodeImageServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.利用 Random 產生一組四位數的隨機認證碼(不足補 0)
		SecureRandom random = new SecureRandom();
		String code = "%04d".formatted(random.nextInt(10000)); // 0000~9999
		
		//resp.getWriter().print(code);
		
		// 3.開始繪圖
		// 3.1 建立圖檔暫存區
		BufferedImage img = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
		
		// 顯示圖檔, 將資料以串流格式回傳給瀏覽器
		ImageIO.write(img, "PNG", resp.getOutputStream());
		
	}
	
}
