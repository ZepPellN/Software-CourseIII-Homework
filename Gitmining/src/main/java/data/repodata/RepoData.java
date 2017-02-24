package data.repodata;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import bean.RepoBean;
import bean.RepoRadarBean;
import dao.RepoDao;
import dataservice.repodataservice.RepoDataService;
import utility.CategoryOfRepo;
import utility.GitminingHelper;
import utility.Language;

public class RepoData implements RepoDataService {
	private static RepoData instance = null;
	private RepoDao repoDao = null;
	
	private RepoData(){
		repoDao = GitminingHelper.getInstance().getRepoDao();
	}
	
	public static RepoData getInstance(){
		return instance == null ? (instance = new RepoData()) : instance;
	}

	public List<RepoBean> searchVague(String keyword, String year, Language language,
			CategoryOfRepo category) {
		return repoDao.queryRepoByNameVaguely(keyword, year, language, category);	
	}

	public RepoBean searchPrecise(String fullName) {
		RepoBean repo = repoDao.queryRepoByNamePrecisely(fullName);
		if(repo != null){
			repo.setRecommendRepos(repoDao.queryRecommendOfRepo(fullName));
		}
		return repo;
	}
	
	public Map<String, Integer> queryCommitOfRepo(String full_Name){
		return repoDao.queryCommitOfRepo(full_Name);
	}
	
	public Map<Integer, Integer> staticsCreated_at(){
		return repoDao.staticsCreated_at();
	}
	
	public Map<Integer, Integer> staticsForks_Count(){
		return repoDao.staticsForks_Count();
	}
	
	public Map<Integer, Integer> staticsStargazers_count(){
		return repoDao.staticsStargazers_count();
	}
	
	public Map<Language, List<Pair<Integer, Integer>>> staticsLanguage(){
		return repoDao.staticsLanguage();
	}
	
	public RepoRadarBean queryRankOfRepo(String repo_name){
		return repoDao.queryRankOfRepo(repo_name);
	}
	
	public String queryRecommendLine_Init(String repo_name){
		return repoDao.queryRecommendLine_Init(repo_name);
	}
	
	public String queryRecommendLine(String repo_name){
		return repoDao.queryRecommendLine(repo_name);
	}
}