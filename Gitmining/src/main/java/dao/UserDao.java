package dao;

import java.util.ArrayList;
import java.util.Map;
import javax.sql.DataSource;
import bean.UserAssessBean;
import bean.UserBean;
import utility.OwnerType;

public interface UserDao {
	public void setdatasource(DataSource ds);
	
	public void addUser(Map<String, String> variables);
		
	public UserBean queryUserByNamePrecisely(String name);
	
	public ArrayList<UserBean> queryUserByNameVaguely(String partname);
	//TODO For Test Only
	public ArrayList<String> getAllNames();

	public ArrayList<String> queryRepoOfUser(String user_name);
	
	public ArrayList<String> queryHotRepoOfUser(String user_name);
	
	public Map<String, Integer> queryEventOfUser(String user_Name);
	
	public UserAssessBean queryRankOfUser(String user_name);
	
	public void setRank();
	
	public Map<OwnerType, Integer> staticsOwnerType();
	
	public Map<Integer, Integer> staticsCreated_at();
	
	public Map<Integer, Integer> staticsRepo();
}