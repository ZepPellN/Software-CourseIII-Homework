package dataservice.repodataservice;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import bean.RepoBean;
import bean.RepoRadarBean;
import utility.CategoryOfRepo;
import utility.Language;

public interface RepoDataService {
	/**
	 * Return the list of po of repository according to the key word vaguely.
	 * List has no items if no po exits.
	 * @param keyword
	 * @return
	 */
	public List<RepoBean> searchVague(String keyword, String year, Language language,
			CategoryOfRepo category);
	
	/**
	 * Return the po of repository according to the user name and repository name.
	 * po is null if no po exits.
	 * @param fullName
	 * @return
	 */
	public RepoBean searchPrecise(String fullName);
	
	public Map<String, Integer> queryCommitOfRepo(String full_Name);
		
	public Map<Integer, Integer> staticsCreated_at();
	
	public Map<Integer, Integer> staticsForks_Count();
	
	public Map<Integer, Integer> staticsStargazers_count();
	
	public Map<Language, List<Pair<Integer, Integer>>> staticsLanguage();
	
	public RepoRadarBean queryRankOfRepo(String repo_name);
	
	public String queryRecommendLine_Init(String repo_name);
	
	public String queryRecommendLine(String repo_name);
}