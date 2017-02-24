package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import bean.RepoBean;
import utility.Language;

public class RepoMapper implements RowMapper<RepoBean> {

	public RepoBean mapRow(ResultSet rs, int rownum) throws SQLException {
		return new RepoBean(rs.getString("id"), rs.getString("full_name"), rs.getString("owner_name"),
				rs.getString("name"), rs.getString("category"), rs.getString("default_branch"), rs.getString("homepage"),
				rs.getString("description"), rs.getDate("created_at").toString(),
				rs.getDate("updated_at").toString(), rs.getDate("pushed_at").toString(), rs.getInt("size"),
				rs.getInt("stargazers_count"), rs.getInt("subscribers_count"), rs.getInt("contributors_count"),
				rs.getInt("open_issues_count"), rs.getInt("watchers_count"), rs.getInt("forks_count"),
				Language.setType(rs.getString("language")), rs.getBoolean("fork"), rs.getBoolean("has_issues"),
				rs.getBoolean("private"), 0, rs.getInt("complex"));
	}
}