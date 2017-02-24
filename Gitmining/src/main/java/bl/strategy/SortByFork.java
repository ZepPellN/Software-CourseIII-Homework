package bl.strategy;

import java.util.Comparator;
import bean.RepoListBean;

public class SortByFork implements Comparator<RepoListBean>{

	/*
	 * 降序排列
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(RepoListBean po1, RepoListBean po2) {
			return po2.getForks() - po1.getForks();
	}
}
