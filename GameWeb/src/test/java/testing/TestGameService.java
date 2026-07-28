package testing;

import org.junit.jupiter.api.Test;

import model.Game;
import model.Record;
import service.GameService;

public class TestGameService {
	
	@Test
	public void test() {
		String username = "andrew";
		int player = -3; // 0 = 石, 1 = 布, 2 = 刀
		
		// 建立猜拳服務
		GameService service = new GameService();
		
		// 進行猜拳遊戲並得到此局的結果
		Record record = service.play(username, player);
		
		// 印出結果
		System.out.printf("玩家 %s 出: %s%n", username, record.getPlayer());
		System.out.printf("電腦出: %s%n", record.getServer());
		System.out.printf("結果: %s%n", record.getResult());
		
	}
	
	
}
