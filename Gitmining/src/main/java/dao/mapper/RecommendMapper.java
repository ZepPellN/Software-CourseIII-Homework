package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import bean.RecommendBean;

public class RecommendMapper implements RowMapper<RecommendBean> {

	public RecommendBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new RecommendBean(rs.getString("repo_commend"), rs.getString("repo_base"), rs.getInt("relate"));
	}
}
