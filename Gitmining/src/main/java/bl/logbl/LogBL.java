package bl.logbl;

import java.util.ArrayList;
import java.util.List;
import bean.HistoryBean;
import bean.LogBean;
import bean.RepoBean;
import bean.RepoListBean;
import data.DataFactory;
import dataservice.logdataservice.LogDataService;
import utility.Language;

public class LogBL {
	private LogDataService logData;
	
	public LogBL() {
		logData = DataFactory.createLogDataInstance();
	}

	public String[] login(String login, String password) {
		return logData.login(login, password);
	}

	public List<RepoListBean> queryRecommendRepos(Object login) {
		List<RepoBean> repos = logData.queryRecommendRepos(login);
		List<RepoListBean> results = new ArrayList<RepoListBean>();
		for (RepoBean repo : repos) {
			results.add(new RepoListBean(repo));
		}
		return results;
	}

	public List<HistoryBean> queryHistory(Object login) {
		return logData.queryHistory(login);
	}

	public List<HistoryBean> queryLearningList(int complex, Object login) {
		return logData.queryLearningList(complex, login);
	}

	public void deleteHistory(String input, Object login) {
		logData.deleteHistory(input, login);
	}

	public void clearHistory(Object login) {
		logData.clearHistory(login);
	}

	public List<LogBean> queryLogRank() {
		return logData.queryLogRank();
	}

	public LogBean show(Object login) {
		return logData.show(login);
	}

	public List<RepoListBean> setInfo(int aptitute, Language language, Object login) {
		logData.setInfo(aptitute, language, login);
		return this.queryRecommendRepos(login);
	}
}