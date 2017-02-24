package bl.strategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import bean.RepoListBean;

public class SortByLastUpdate implements Comparator<RepoListBean>{
	
	/*
	 * 按最新更新日期降序排列
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(RepoListBean o1, RepoListBean o2) {
		SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd" );
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = format.parse(o1.getLast_update());
			date2 = format.parse(o2.getLast_update());
		} catch (ParseException e) {
		}
		return date2.compareTo(date1);
	}

}
