package bl.statisticsbl;

import java.util.HashMap;
import java.util.Map;
import bean.UserAssessBean;
import data.DataFactory;
import dataservice.userdataservice.UserDataService;
import utility.OwnerType;

public class UserStatisticsBL {
	private UserDataService userData;
	
	public UserStatisticsBL(){
		userData = DataFactory.createUserDataInstance();
	}

	//========================================= personal attributes ==========================================
	public UserAssessBean getScore(String name){
		UserAssessBean assessPO = userData.queryRankOfUser(name);
		if(assessPO == null){
			return new UserAssessBean(0, 0, 0);
		}
		return assessPO;
	}
	
	//user events within seven days
	public Map<String, Integer> getUserActivity(String name){
		return userData.queryEventOfUser(name);
	}
	
	//======================================= public attributes ============================================
	public Map<OwnerType, Integer> getOwnerType(){		
		return userData.staticsOwnerType();
	}
	
	public HashMap<Integer, Integer> getCreated_at(){
		return (HashMap<Integer, Integer>)userData.staticsCreated_at();
	}
	
	public HashMap<Integer, Integer> getRepository(int interval) {
		Map<Integer, Integer> reposMap = userData.staticsRepo();
		HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
		int[] count = new int[2000 / interval];
		for (int repo_num : reposMap.keySet()) {
			if(repo_num >= 2000 / interval){
				count[2000 / interval - 1] += reposMap.get(repo_num);
			}else{
				count[repo_num / interval] += reposMap.get(repo_num);
			}
		}
		
		for (int i = 0; i < count.length; i++) {
			result.put(i * interval, count[i]);
		}
	
		return result;
	}
}