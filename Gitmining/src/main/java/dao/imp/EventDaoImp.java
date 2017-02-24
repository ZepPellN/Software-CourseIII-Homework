package dao.imp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import dao.EventDao;
import utility.GitminingHelper;

public class EventDaoImp implements EventDao {
	private JdbcTemplate jdbcTemplateObject;

	public void setdatasource(DataSource datasource) {
		this.jdbcTemplateObject = new JdbcTemplate(datasource);
	}

	public void addEvent(String user_Name, Map<String, String>[] maps, Date deadline) {
		String sql = "INSERT INTO Event(user_name, repo_name, created_at, type)VALUES(?,?,?,?)";
		List<Object[]> paras = new ArrayList<Object[]>();
		Date date;
		for (Map<String, String> variables : maps) {
			date = java.sql.Date.valueOf((variables.get("created_at").substring(0, 10)));
			if(date.compareTo(deadline) >= 0){
				paras.add(new Object[] { user_Name, GitminingHelper.parseJson(variables.get("repo")).get("name"), date,
						variables.get("type") });
			}
		}
		jdbcTemplateObject.batchUpdate(sql, paras);
	}

	public Date queryLatestDate(String user_name) {
		String sql = "SELECT MAX(created_at) as updated_at FROM Event WHERE user_name=?";
		List<Map<String, Object>> maps = jdbcTemplateObject.queryForList(sql, user_name);
		if (maps.size() > 0) {
			return (Date) (maps.get(0).get("updated_at"));
		}
		return null;
	}
}