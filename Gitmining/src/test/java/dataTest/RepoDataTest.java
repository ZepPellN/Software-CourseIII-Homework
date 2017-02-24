package dataTest;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import bean.RepoBean;
import bean.RepoRadarBean;
import data.repodata.RepoData;
import utility.CategoryOfRepo;
import utility.Language;

public class RepoDataTest {
	private static RepoData data;
	private String full_name = "1stvamp/memorised";
	
	@BeforeClass
	public static void init() {
		data = RepoData.getInstance();
	}
	
	@Test
	public void testSearchVague() {
		assertNotNull(data.searchVague("mo", "All", Language.All, CategoryOfRepo.All));
		assertEquals(data.searchVague("mo", "2007", Language.C, CategoryOfRepo.OS).size(), 0);
	}

	@Test
	public void testSearchPrecise() {
		RepoBean repo = data.searchPrecise(full_name);
		assertEquals(repo.getFull_name(), full_name);
		assertNotNull(repo.getRecommendRepos());
	}

	@Test
	public void testQueryCommitOfRepo() {
		assertNotNull(data.queryCommitOfRepo(full_name));
	}

	@Test
	public void testStaticsCreated_at() {
		assertNotNull(data.staticsCreated_at());
	}

	@Test
	public void testStaticsForks_Count() {
		assertNotNull(data.staticsForks_Count());
	}

	@Test
	public void testStaticsStargazers_count() {
		assertNotNull(data.staticsStargazers_count());
	}

	@Test
	public void testStaticsLanguage() {
		assertNotNull(data.staticsLanguage());
	}

	@Test
	public void testQueryRankOfRepo() {
		RepoRadarBean radar = data.queryRankOfRepo(full_name);
		assertEquals(radar.getContributor(), 0.5, 0.5);
		assertEquals(radar.getFork(), 0.5, 0.5);
		assertEquals(radar.getIssue(), 0.5, 0.5);
		assertEquals(radar.getSize(), 0.5, 0.5);
		assertEquals(radar.getStar(), 0.5, 0.5);
		assertEquals(radar.getSubscriber(), 0.5, 0.5);
	}

	@Test
	public void testQueryRecommendLine_Init() {
		assertNotNull(data.queryRecommendLine_Init(full_name));
	}

	@Test
	public void testQueryRecommendLine() {
		assertNotNull(data.queryRecommendLine(full_name));
	}
}