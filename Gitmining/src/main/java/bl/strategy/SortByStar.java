package bl.strategy;

import java.util.Comparator;
import bean.RepoListBean;

public class SortByStar implements Comparator<RepoListBean>{

	/*
	 * 降序排列
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(RepoListBean vo1, RepoListBean vo2) {
		return vo2.getStars() - vo1.getStars();
	}
}
