package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Record;
import service.GameService;

public class TestMultiGameService {
	
	@Test
	public void test() {
		// 1. 初始化遊戲物件
		GameService service = new GameService();
		String username = "andrew";
		
		// 2.模擬多回合猜拳(五回合)
		service.play(username, 0);
		service.play(username, 1);
		service.play(username, 2);
		service.play(username, 0);
		service.play(username, 1);
		
		// 3.檢驗該玩家的歷史紀錄總數是否為 5 筆
		List<Record> records = service.getRecords(username);
		/*
		if(records == null) {
			throw new AssertionError("玩家記錄不應該是空的");
		}
		*/
		assertNotNull(records, "玩家記錄不應該是空的");
		
		/*
		if(records.size() != 5) {
			throw new AssertionError("總回合樹應該要是 5 回合");
		}
		*/
		assertEquals(5, records.size(), "總回合樹應該要是 5 回合");
		
		// 4. 驗證各項統計數據的總和是否等於總回合數
		long playerWins = service.getPlayerWins(username);
		long serverWins = service.getServerWins(username);
		long draws = service.getDraws(username);
		assertEquals(5, playerWins + serverWins + draws, "玩家贏+電腦贏+平手的總和必須等於總回和數 5");
		
		
		// 5. 驗證勝率計算是否正確 ?
		double winRate = service.getRate((int)playerWins, 5);
		System.out.println(records.stream().map(Record::getResult).toList());
		System.out.printf("%.1f %% %n", winRate);
		System.out.printf("%.1f ％ %n", winRate);
		
		System.out.println("檢驗通過");
		
		
		
	}
	
}
