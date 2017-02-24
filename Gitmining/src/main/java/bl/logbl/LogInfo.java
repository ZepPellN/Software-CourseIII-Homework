package bl.logbl;

import java.util.List;

import bean.RepoBean;
import bl.repobl.LogInfo_Repo;
import data.DataFactory;
import dataservice.logdataservice.LogDataService;
import utility.Language;

public class LogInfo implements LogInfo_Repo {
	private static LogInfo instance;
	private LogDataService logData;
	
	public static LogInfo getInstance(){
		return instance == null ? (instance = new LogInfo()) : instance;
	}
	
	private LogInfo(){
		logData = DataFactory.createLogDataInstance();
	}

	public int getLearningState(RepoBean repo, Object login) {
		return logData.getLearningState(repo, login);
	}

	public boolean startLearning(String repo, Language language, Object login) {
		return logData.startLearning(repo, language, login);		
	}

	public boolean stopLearning(String repo, Object login){
		return logData.stopLearning(repo, login);
	}
	
	public boolean finishLearning(String repo, Object login) {
		return logData.finishLearning(repo, login);
	}

	public void updateHistory(RepoBean repo, Object login) {
		logData.updateHistory(repo, login);
	}
	
	public List<RepoBean> sortRepos(List<RepoBean> repos, Object login){
		return logData.sortRepos(repos, login);
	}
}
