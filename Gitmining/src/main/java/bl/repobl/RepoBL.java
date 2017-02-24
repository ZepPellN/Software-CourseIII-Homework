package bl.repobl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import bean.RepoBean;
import bean.RepoListBean;
import bl.logbl.LogInfo;
import data.DataFactory;
import dataservice.repodataservice.RepoDataService;
import utility.CategoryOfRepo;
import utility.Language;

public class RepoBL {
	private List<RepoBean> searchlist;

	private List<RepoListBean> showlist;
	
	private RepoDataService repoData;
	
	private RepoBean currentRepo;
	
	private LogInfo_Repo logInfo;
		
	public RepoBL() {
		/* 暂存模糊查找的结果 */
		searchlist = new ArrayList<RepoBean>();;
		/* 存储筛选结果list */
		showlist = new ArrayList<RepoListBean>();
		// 初始化为按项目名全称排序：
		repoData = DataFactory.createRepoDataInstance();
		
		logInfo = LogInfo.getInstance();
	}

	/*
	 * 每次模糊查找都更新当前项目列表，并返回项目的name列表
	 */
	public List<RepoListBean> searchVague(String keyword, CategoryOfRepo cate, Language lan, String year, Object login) {	
		if ((keyword.length() > 0) || cate != CategoryOfRepo.All || lan != Language.All || !year.equals("All")) {
			showlist = new ArrayList<RepoListBean>();
			searchlist = repoData.searchVague(keyword, year, lan, cate);
			searchlist = logInfo.sortRepos(searchlist, login);
			for (RepoBean bean : searchlist) {
				showlist.add(new RepoListBean(bean));
			}
		}
		return showlist;
	}

	public RepoBean searchPrecise(Object login) {
		if(currentRepo != null){
			currentRepo.setState(logInfo.getLearningState(currentRepo, login));
		}
		return currentRepo;
	}
	
	public boolean checkAndUpdate(String name, Object login){
		currentRepo = repoData.searchPrecise(name);
		if(currentRepo == null){
			return false;
		}else{
			logInfo.updateHistory(currentRepo, login);
			return true;
		}
	}
	
	public List<RepoListBean> sort(Comparator<RepoListBean> sortBehavior) {
		// 在这里拿到完整数据，传给各个Behavior排序，返回 VO list
		// 这里改成了showlist................
		if (showlist.size() > 0) {
			Collections.sort(showlist, sortBehavior);
		}
		return showlist;
	}

	public String[] queryRecommendLine(String repo1, String repo2){
		String[] rows = new String[5];
		rows[0] = repoData.queryRecommendLine_Init(repo1);
		rows[0] = rows[0].replaceAll(" ", "");
		rows[1] = repoData.queryRecommendLine_Init(repo2);
		rows[1] = rows[1].replaceAll(" ", "");
		rows[2] = repoData.queryRecommendLine(repo1);
		rows[2] = rows[2].replaceAll(",", "");
		rows[3] = repoData.queryRecommendLine(repo2);
		rows[3] = rows[3].replaceAll(",", "");
		
		rows[4] = "";
		for (int i = 0; i < rows[0].length(); i++) {
			rows[4] += (int)Math.ceil(Math.sqrt(((rows[2].charAt(i) - '0') * (rows[3].charAt(i) - '0'))));
		}
		return rows;
	}
	
	public boolean startLearning(String fullname, Language language, Object login){
		return logInfo.startLearning(fullname, language, login);
	}
	
	public boolean stopLearning(String fullname, Object login){
			return logInfo.stopLearning(fullname, login);
	}
	
	public boolean finishLearning(String fullname, Object login){
		return logInfo.finishLearning(fullname, login);
	}
}