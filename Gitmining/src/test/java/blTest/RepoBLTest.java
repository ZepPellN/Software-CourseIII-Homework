package blTest;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;

import bean.RepoBean;
import bean.RepoListBean;
import bl.BlFactory;
import bl.repobl.RepoBL;
import bl.strategy.SortByFork;
import bl.strategy.SortByLastUpdate;
import bl.strategy.SortByName;
import bl.strategy.SortByStar;
import utility.CategoryOfRepo;
import utility.Language;

public class RepoBLTest {
	private static RepoBL bl;
	private String full_name = "1stvamp/memorised";
	private Object login = "hanifor";
	@BeforeClass
	public static void init() {
		bl = BlFactory.createRepoBLInstance();
	}

	@Test
	public void test() {
		List<RepoListBean> repos = bl.searchVague("mo", CategoryOfRepo.All, Language.All, "All", login);
		
		repos = bl.sort(new SortByLastUpdate());
		repos = bl.sort(new SortByName());
		repos = bl.sort(new SortByStar());
		repos = bl.sort(new SortByFork());
		for (int i = 0; i < repos.size() - 1; i++) {
			assertTrue(repos.get(i).getForks() >= repos.get(i + 1).getForks());
		}

		assertTrue(bl.checkAndUpdate(full_name, login));
		RepoBean repo = bl.searchPrecise(login);
		assertNotNull(repo);
		
		assertTrue(bl.queryRecommendLine("openresty/lua-nginx-module", "antirez/redis").length > 0);
	}
}