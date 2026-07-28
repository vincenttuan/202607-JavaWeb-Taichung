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
		// 多回合
		GameService service = new GameService();
		String username = "andrew";
		
		// 模擬多回合猜拳(五回合)
		service.play(username, 0);
		service.play(username, 1);
		service.play(username, 2);
		service.play(username, 0);
		service.play(username, 1);
		
		// 檢驗該玩家的歷史紀錄總數是否為 5 筆
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
		
		System.out.println("檢驗通過");
		
	}
	
}
