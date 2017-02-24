package bl;

import bl.logbl.LogBL;
import bl.repobl.RepoBL;
import bl.statisticsbl.RepoStatisticsBL;
import bl.statisticsbl.UserStatisticsBL;
import bl.userbl.UserBL;

public class BlFactory {
	private static RepoBL repo = null;
	private static UserBL user = null;
	private static LogBL log = null;
	private static RepoStatisticsBL repoStatic = null;
	private static UserStatisticsBL userStatic = null;

	public static RepoBL createRepoBLInstance() {
		return repo == null ? (repo = new RepoBL()) : repo;
	}

	public static UserBL createUserBLInstance() {
		return user == null ? (user = new UserBL()) : user;
	}

	public static LogBL createLoginBLInstance() {
		return log == null ? (log = new LogBL()) : log;
	}

	public static RepoStatisticsBL createRepoStatisticsBLInstance() {
		return repoStatic == null ? (repoStatic = new RepoStatisticsBL()) : repoStatic;
	}

	public static UserStatisticsBL createUserStatisticsBLInstance() {
		return userStatic == null ? (userStatic = new UserStatisticsBL()) : userStatic;
	}
}