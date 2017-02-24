package bl.strategy;

import java.util.Comparator;
import bean.RepoListBean;

public class SortByName implements Comparator<RepoListBean>{

	/*
	 * 字典序排列
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(RepoListBean o1, RepoListBean o2) {
		String name1 = o1.getName().toLowerCase();
		String name2 = o2.getName().toLowerCase();
		return name1.compareToIgnoreCase(name2);
	}
}
