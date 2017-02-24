package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import bean.CommitBean;

public class CommitMapper implements RowMapper<CommitBean> {
	public CommitBean mapRow(ResultSet rs, int rownum) throws SQLException {		
		return new CommitBean(rs.getDate("created_at").toString(), rs.getString("user_name"), rs.getString("message"));
	}
}
