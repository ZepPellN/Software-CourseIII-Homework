package dao;

import java.sql.Date;
import java.util.Map;
import javax.sql.DataSource;

public interface EventDao {
	public void setdatasource(DataSource ds);
	
	public void addEvent(String userName, Map<String, String>[] variables, Date deadline);
		
	public Date queryLatestDate(String userName);
}