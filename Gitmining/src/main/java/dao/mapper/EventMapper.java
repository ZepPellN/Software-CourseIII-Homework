package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import bean.EventBean;

public class EventMapper implements RowMapper<EventBean> {
	public EventBean mapRow(ResultSet rs, int rownum) throws SQLException {
		return new EventBean(rs.getString("repo_name"), rs.getDate("created_at").toString(), rs.getString("type"));
	}
}
