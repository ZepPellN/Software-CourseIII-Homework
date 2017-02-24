package bl.repobl;

import java.util.List;

import bean.RepoBean;
import utility.Language;

public interface LogInfo_Repo {
	
	public int getLearningState(RepoBean repo, Object login);
	
	public boolean startLearning(String repo, Language language, Object login);
	
	public boolean stopLearning(String repo, Object login);
	
	public boolean finishLearning(String repo, Object login);
	
	public void updateHistory(RepoBean repo, Object login);
	
	public List<RepoBean> sortRepos(List<RepoBean> repos, Object login);
}
