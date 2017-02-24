package dataservice.userdataservice;

import java.util.ArrayList;
import java.util.Map;
import bean.UserAssessBean;
import bean.UserBean;
import utility.OwnerType;

public interface UserDataService {
	/**
	 * Return the list of po of user according to the key word vaguely.
	 * List has no items if no po exits.
	 * @param keyword
	 * @return
	 */
	public ArrayList<UserBean> searchVague(String keyword);
	
	/**
	 * Return the po of user according to the user name and repository name.
	 * po is null if no po exits.
	 * @param fullName
	 * @return
	 */
	public UserBean searchPrecise(String fullName);

	public Map<String, Integer> queryEventOfUser(String user_Name);
	
	public UserAssessBean queryRankOfUser(String user_name);
	
	public Map<OwnerType, Integer> staticsOwnerType();

	public Map<Integer, Integer> staticsCreated_at();
	
	public Map<Integer, Integer> staticsRepo();
}
