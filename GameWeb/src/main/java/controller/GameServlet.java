package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Record;
import service.GameService;

/**
 * GameController
 * 
 * doGet() 直接呈現遊戲首頁
 * doPost() 接收玩家所輸入的遊戲資訊並回傳統計結果
 * 
 * */

@WebServlet("/game")
public class GameServlet extends HttpServlet {
	
	private static final GameService service = new GameService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 直接重導到遊戲首頁
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 編碼
		req.setCharacterEncoding("UTF-8");
		
		// 2. 接收前端參數
		String username = req.getParameter("username");
		int player = Integer.parseInt(req.getParameter("player")); // 字串轉數字
		
		// 3. 呼叫猜拳遊戲並得到本局結果
		Record currentRecord = service.play(username, player);
		
		// 4. 計算並取得相關數據
		long playerWins = service.getPlayerWins(username);
		long serverWins = service.getServerWins(username);
		long draws = service.getDraws(username);
		int totalGames = service.getRecords(username).size(); // 總回合數
		double winRate = service.getRate((int)playerWins, totalGames); // 勝率
		
		// 5. 將資料存入並準備傳給 jsp
		req.setAttribute("username", username); // 玩家
		req.setAttribute("currentRecord", currentRecord); // 本局結果
		req.setAttribute("playerWins", playerWins); // 累積勝場
		req.setAttribute("serverWins", serverWins); // 累積敗場
		req.setAttribute("draws", draws); // 累積平手
		req.setAttribute("winRate", String.format("%.2f", winRate)); // 累積平手
		req.setAttribute("history", service.getRecords(username)); // 歷史交手紀錄
		
		
		// 直接重導到遊戲結果頁
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/result.jsp");
		rd.forward(req, resp);
		
	}
	
}
