package blTest;

import static org.junit.Assert.*;
import java.util.Map;
import org.junit.BeforeClass;
import org.junit.Test;
import bl.BlFactory;
import bl.statisticsbl.RepoStatisticsBL;
import utility.Language;

public class RepoStatisticsBLTest {

private static RepoStatisticsBL bl;
	@BeforeClass
	public static void init() {
		bl = BlFactory.createRepoStatisticsBLInstance();
	}
	
	@Test
	public void test() {
		int total = bl.getLanguage().get(Language.Ruby);
		int part = 0;
		Map<Integer, Integer> maps = bl.getLanguage(Language.Ruby);
		for (int year : maps.keySet()) {
			part += maps.get(year);
		}
		assertNotNull(bl.getLanguage());
		assertEquals(total, part);
		bl.getRepoFork(5);
		bl.getRepoStar(5);
		assertTrue(bl.getRadar("fesfsef").getSize() == 0);
		assertNotNull(bl.getRepoActivity("ffi/ffi"));
	}
}