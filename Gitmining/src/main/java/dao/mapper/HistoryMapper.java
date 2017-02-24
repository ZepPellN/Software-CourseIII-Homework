package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import bean.HistoryBean;
import utility.Language;

public class HistoryMapper implements RowMapper<HistoryBean> {

	public HistoryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new HistoryBean(rs.getString("repo_name"), rs.getInt("count"), rs.getDate("latest").toString(),
				rs.getDate("start") == null ? null : rs.getDate("start").toString(), rs.getDate("finish") == null ? null : rs.getDate("finish").toString(), 0, rs.getInt("complex"),
				rs.getInt("block"), Language.setType(rs.getString("language")));
	}
}