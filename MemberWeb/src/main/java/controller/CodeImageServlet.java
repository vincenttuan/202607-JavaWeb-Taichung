package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
		// 3.2 建立畫布
		Graphics g = img.getGraphics();
		// 3.3 設定顏色
		g.setColor(Color.YELLOW);
		// 3.4 塗滿背景
		g.fillRect(0, 0, 80, 30);
		// 3.5 設定顏色
		g.setColor(Color.BLACK);
		// 3.6 設定字型
		g.setFont(new Font("Arial", Font.BOLD, 25));
		// 3.7 繪文字
		g.drawString(code, 10, 23);
		// 3.8 加上干擾線
		g.setColor(Color.RED);
		for(int i=0;i<10;i++) {
			int x1 = random.nextInt(80); // 0~79
			int y1 = random.nextInt(30); // 0~29
			int x2 = random.nextInt(80); // 0~79
			int y2 = random.nextInt(30); // 0~29
			
			g.drawLine(x1, y1, x2, y2);
		}
		
		
		// 顯示圖檔, 將資料以串流格式回傳給瀏覽器
		ImageIO.write(img, "PNG", resp.getOutputStream());
		
	}
	
}
