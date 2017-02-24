package dao;

import java.sql.Date;
import java.util.Map;
import javax.sql.DataSource;

public interface CommitDao {
	public void setdatasource(DataSource ds);
	
	public void addCommit(String repo_name, Map<String, String>[] variables, Date deadline);
		
	public Date queryLatestDate(String repo_name);
}