package service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dao.GameDao;
import model.Record;

/**
 * 負責項目:
 * 1. Server 隨機出拳
 * 2. 判斷勝負
 * 3. 呼叫 GameDao 將記錄存放到 MySQL
 * 4. 計算勝場與勝率
 * 
 * MVC 分層
 * 網頁請求 request --> GameServlet --> GameService --> GameDao --> MySQL 
 * 
 * GameService 不直接撰寫 SQL, 所有的資料庫操作都交給 GameDao
 * */
public class GameService {
	
	// GameDao 負責操作 MySQL
	private GameDao gameDao = new GameDao();
	
	private Random random = new SecureRandom(); // SecureRandom 具有不可預測
	
	/*
	 * 進行一場拳賽 play
	 * username: 玩家名
	 * player: 玩家所出的拳
	 * */
	public Record play(String username, int player) {
		// 檢查 player
		if(player < 0 || player > 2) {
			throw new RuntimeException("玩家猜拳的資料輸入錯誤(只能是 0, 1, 2), player=" + player);
		}
		
		//player = Math.abs(player);
		//player = player % 3;
		
		// Server 隨機產生 0, 1, 2
		int server = random.nextInt(3); // 電腦隨機所出的拳
		
		// 判斷勝負
		String result = judge(player, server);
		
		// 建立本局紀錄
		Record record = new Record(player, server, result);
		
		// 存入資料庫
		gameDao.addRecord(username, record);
		
		return record;
	}
	
	/* 
	 * 判斷勝負 
	 * */
	public String judge(int player, int server) {
		
		// result 只會有 0, 1, 2
		int result = (player - server + 3) % 3;
		
		switch (result) {
			case 1: 
				return "玩家贏"; // 1
			case 2: 
				return "電腦贏"; // 2
			default:
				return "平手"; // 0
		}
		
	}
	
	/*
	 * 取得指定玩家的所有紀錄
	 * */
	public List<Record> getRecords(String username) {
		return recordMap.get(username);
	}
	
	/*
	 * 計算玩家的獲勝次數
	 * */
	public long getPlayerWins(String username) {
		return getRecords(username).stream()
								   .filter(record -> "玩家贏".equals(record.getResult()))
								   .count();
	}
	
	/*
	public int getPlayerWins(String username) {
		int count = 0;
		
		// 取得該玩家的所有紀錄
		List<Record> records = getRecords(username);
		
		for(Record record : records) {
			if("玩家贏".equals(record.getResult())) {
				count++;
			}
		}
		
		return count;
	}
	*/
	
	/*
	 * 計算 Server 獲勝次數
	 * */
	public long getServerWins(String username) {
		return getRecords(username).stream()
								   .filter(record -> "電腦贏".equals(record.getResult()))
								   .count();
	}
	
	/*
	public int getServerWins(String username) {
		int count = 0;
		
		// 取得該玩家的所有紀錄
		List<Record> records = getRecords(username);
		
		for(Record record : records) {
			if("電腦贏".equals(record.getResult())) {
				count++;
			}
		}
		
		return count;
	}
	*/
	/*
	 * 計算平手次數
	 * */
	public long getDraws(String username) {
		return getRecords(username).stream()
								   .filter(record -> "平手".equals(record.getResult()))
								   .count();
	}
	
	
	/*
	public int getDraws(String username) {
		int count = 0;
		
		// 取得該玩家的所有紀錄
		List<Record> records = getRecords(username);
		
		for(Record record : records) {
			if("平手".equals(record.getResult())) {
				count++;
			}
		}
		
		return count;
	}
	*/
	
	/*
	 * 計算勝率
	 * */
	public double getRate(int winCount, int total) {
		return winCount * 100.0 / total;
	}
	
	
}
