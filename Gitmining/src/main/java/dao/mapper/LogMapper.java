package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import bean.LogBean;
import utility.Language;

public class LogMapper implements RowMapper<LogBean>  {
	public LogBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new LogBean(rs.getString("login"), rs.getDate("join_in"), rs.getInt("aptitute"), rs.getInt("grade"), rs.getString("avatar"), Language.setType(rs.getString("language")));
	}
}