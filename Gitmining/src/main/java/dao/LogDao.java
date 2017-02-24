package dao;

import java.util.List;
import javax.sql.DataSource;
import bean.LogBean;
import bean.RepoBean;
import utility.Language;

public interface LogDao {
	public void setdatasource(DataSource ds);
	
	public LogBean queryLog(Object login);
	
	public void deleteLog(String login);
		
	public void updateLog(LogBean log);

	public List<RepoBean> queryRecommendRepos(List<String> repos, int complex, Language language);

	public List<LogBean> queryLogRank();
	
	public void clearLog(String login);
}