package data.userdata;

import java.util.ArrayList;
import java.util.Map;
import bean.UserAssessBean;
import bean.UserBean;
import dao.UserDao;
import dataservice.userdataservice.UserDataService;
import utility.GitminingHelper;
import utility.OwnerType;

public class UserData implements UserDataService {
	private static UserData userData = null;
	private UserDao userDao = null;
	
	private UserData(){
		userDao = GitminingHelper.getInstance().getUserDao();
	}
	
	public static UserData getInstance(){
		return userData == null ? (userData = new UserData()) : userData;
	}
	
	public ArrayList<UserBean> searchVague(String keyword) {
		return userDao.queryUserByNameVaguely(keyword);
	}
	
	public UserBean searchPrecise(String fullName) {
		UserBean user = userDao.queryUserByNamePrecisely(fullName);
		if(user == null){
			return null;
		}
		user.setReposName(userDao.queryRepoOfUser(fullName));
		user.setReposName_HOT(userDao.queryHotRepoOfUser(fullName));
		return user;
	}
	
	public Map<String, Integer> queryEventOfUser(String user_Name){
		return userDao.queryEventOfUser(user_Name);
	}
	
	public UserAssessBean queryRankOfUser(String user_name){
		return userDao.queryRankOfUser(user_name);
	}
	
	public Map<OwnerType, Integer> staticsOwnerType(){
		return userDao.staticsOwnerType();
	}
	
	public Map<Integer, Integer> staticsCreated_at(){
		return userDao.staticsCreated_at();
	}
	
	public Map<Integer, Integer> staticsRepo(){
		return userDao.staticsRepo();
	}
}