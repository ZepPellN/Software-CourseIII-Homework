package bl.userbl;

import java.util.ArrayList;
import java.util.List;
import bean.UserBean;
import bean.UserListBean;
import data.DataFactory;
import dataservice.userdataservice.UserDataService;

public class UserBL {
	private List<UserBean> searchlist;
	private List<UserListBean> showlist;
	private UserDataService userData;

	public UserBL() {
		searchlist = new ArrayList<UserBean>();
		showlist = new ArrayList<UserListBean>();
		// 初始化为按name字典序排序
		userData = DataFactory.createUserDataInstance();
	}

	public List<UserListBean> searchVague(String keyword) {
		if (keyword.length() > 0) {
			searchlist = userData.searchVague(keyword);
			showlist = new ArrayList<UserListBean>();

			for (UserBean bean : searchlist) {
				showlist.add(new UserListBean(bean));
			}
		}

		return showlist;
	}

	public UserBean searchPrecise(String user) {
		return userData.searchPrecise(user);
	}
}