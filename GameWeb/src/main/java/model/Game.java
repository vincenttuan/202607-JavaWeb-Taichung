package model;

/**
 * 負責定義 "剪刀"，"石頭" 與 "布" 的數字
 * */
public class Game {
	
	// 猜拳使用的數字
	public static final int ROCK     = 0; // 石頭
	public static final int PAPER    = 1; // 布
	public static final int SCISSORS = 2; // 剪刀
	
	// 將數字轉成中文名稱
	public static String getName(int number) {
		switch (number) {
			case 0:
				return "石頭";
			case 1:
				return "布";
			case 2:
				return "剪刀";	
		}
		
		throw new RuntimeException("資料錯誤");
	}
	
	
}
