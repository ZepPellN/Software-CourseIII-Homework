package blTest;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import bl.BlFactory;
import bl.logbl.LogBL;

public class LogBLTest {
	private static LogBL bl;

	@BeforeClass
	public static void init() {
		bl = BlFactory.createLoginBLInstance();
	}

	@Test
	public void test() {
		String login = "Hanifor";
		String password = "371071798@qq.com";
		assertNotNull(bl.login(login, password)[1]);
		assertFalse(bl.queryRecommendRepos(login).isEmpty());
	}
}