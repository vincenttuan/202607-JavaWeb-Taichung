package testing;

import org.junit.jupiter.api.Test;

import model.Game;
import model.Record;
import service.GameService;

public class TestGameService {
	
	@Test
	public void test() {
		String username = "andrew";
		int userplay = 1; // 0 = 石, 1 = 布, 2 = 刀
		
		GameService service = new GameService();
		Record record = service.play(username, userplay);
		// 印出結果
		System.out.printf("玩家 %s 出: %s%n", username, Game.getName(record.getPlayer()));
		System.out.printf("電腦出: %s%n", Game.getName(record.getServer()));
		System.out.printf("結果: %s%n", record.getResult());
		
	}
	
	
}
