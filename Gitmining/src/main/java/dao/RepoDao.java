package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.lang3.tuple.Pair;
import bean.RecommendBean;
import bean.RepoBean;
import bean.RepoRadarBean;
import utility.CategoryOfRepo;
import utility.Language;

public interface RepoDao {
	public void setdatasource(DataSource ds);
	
	public void addRepo(Map<String, String> variables);
	
	public void addRepoRecommend(List<Object[]> paras);
	
	public void addRepoAnalysis(List<Object[]> paras);
	
	public ArrayList<String> getAllNames();
		
	public List<RecommendBean> queryRecommendOfRepo(String repo_name);
	
	public String queryRecommendLine_Init(String repo_name);
	
	public String queryRecommendLine(String repo_name);
	
	public List<String> queryRepoOfLan(Language language);
	
	public void setHotRepos();
		
	public void setRank();
	
	public RepoBean queryRepoByNamePrecisely(String name);
	
	public List<RepoBean> queryRepoByNameVaguely(String partname, String year, Language language,
			CategoryOfRepo category);
	
	public Map<String, Integer> queryCommitOfRepo(String repo_name);
	
	public Map<Integer, Integer> staticsCreated_at();
	
	public Map<Integer, Integer> staticsForks_Count();
	
	public Map<Integer, Integer> staticsStargazers_count();
	
	public Map<Language, List<Pair<Integer, Integer>>> staticsLanguage();
	
	public RepoRadarBean queryRankOfRepo(String repo_name);
}