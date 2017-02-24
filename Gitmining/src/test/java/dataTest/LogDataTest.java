package dataTest;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import bean.LogBean;
import bean.RepoBean;
import data.logdata.LogData;
import utility.Language;

public class LogDataTest {
	private static LogData data;
	private int size;
	private Object login = "hanifor";

	@BeforeClass
	public static void init() {
		data = LogData.getInstance();
	}
	@Test
	public void testLogin() {
		assertEquals(data.login("Hanifor", "123456")[0], "failure");
		String[] result = data.login("371071798@qq.com", "371071798@qq.com");
		assertEquals(result[1], "Hanifor");
		if(result[0].equals("new")){
			data.setInfo(1, Language.Java, login);
		}
		LogBean log = data.show(login);
		assertEquals(log.getLogin(), "Hanifor");
		
		size = data.queryHistory(login).size();
		
		RepoBean test = new RepoBean();
		test.setFull_name("test/test1");
		test.setComplex(1);
		data.updateHistory(test, login);

		assertEquals(data.getLearningState(test, login), 1);
		
		assertEquals(data.queryHistory(login).size(), size + 1);

		assertTrue(data.startLearning("test/test1", Language.Java, login));
		
		assertTrue(data.stopLearning("test/test1", login));
		assertNotNull(data.queryRecommendRepos(login));

		assertNotNull(data.queryLearningList(1, login));
		
		data.deleteHistory("test/test1", login);
		assertEquals(data.queryHistory(login).size(), size);
		
		assertNotNull(data.queryLogRank());
	}
}