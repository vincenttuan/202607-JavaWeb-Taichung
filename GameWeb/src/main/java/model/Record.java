package model;

/**
 * 每完成一場拳賽，就建立一個 Record 物件
 * */
public class Record {
	
	private int player; // 玩家所出的拳
	private int server; // 電腦所出的拳
	private String result; // 拳賽結果
	
	// 建構子
	public Record(int player, int server, String result) {
		super();
		this.player = player;
		this.server = server;
		this.result = result;
	}
	
	// getters
	public int getPlayer() {
		return player;
	}

	public int getServer() {
		return server;
	}

	public String getResult() {
		return result;
	}
	
}
