package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import bean.UserAssessBean;

public class UserAssessMapper implements RowMapper<UserAssessBean> {
	public UserAssessBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new UserAssessBean(rs.getDouble("p"), rs.getDouble("e"), rs.getDouble("a"));
	}
}
