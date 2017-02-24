package dataservice.logdataservice;

import java.util.List;
import bean.HistoryBean;
import bean.LogBean;
import bean.RepoBean;
import utility.Language;

public interface LogDataService {
	/**
	 * login with login account and password.
	 * 
	 * @param login
	 * @param password
	 * @return failure if the account is invalid, new if it's the new user,
	 *         success if it's the old user.
	 */
	public String[] login(String login, String password);

	public boolean startLearning(String repo, Language language, Object login);

	public boolean stopLearning(String repo, Object login);
	
	public boolean finishLearning(String repo, Object login);

	public void updateHistory(RepoBean repo, Object login);

	public List<RepoBean> queryRecommendRepos(Object login);

	public List<HistoryBean> queryLearningList(int complex, Object login);

	public List<HistoryBean> queryHistory(Object login);

	public int getLearningState(RepoBean repo, Object login);

	public void deleteHistory(String input, Object login);

	public void clearHistory(Object login);

	public List<LogBean> queryLogRank();

	public LogBean show(Object login);

	public void setInfo(int aptitute, Language language, Object login);

	public List<RepoBean> sortRepos(List<RepoBean> repos, Object login);
}