package dao.imp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import bean.CommitBean;
import dao.CommitDao;
import dao.mapper.CommitMapper;
import utility.GitminingHelper;

public class CommitDaoImp implements CommitDao {
	private JdbcTemplate jdbcTemplateObject;

	public void setdatasource(DataSource datasource) {
		this.jdbcTemplateObject = new JdbcTemplate(datasource);
	}

	public void addCommit(String repo_name, Map<String, String>[] maps, Date deadline) {
		Map<String, String> variables = null;
		Map<String, String> map = null;
		List<Object[]> paras = new ArrayList<Object[]>();
		String sql = "INSERT IGNORE INTO Commit(repo_name, user_name, created_at, message)VALUES(?,?,?,?)";
		Date date;
		for (int i = 0; i < maps.length; i++) {
			variables = GitminingHelper.parseJson(maps[i].get("commit"));
			map = GitminingHelper.parseJson(variables.get("committer"));
			date = java.sql.Date.valueOf((map.get("date").substring(0, 10)));
			if (date.compareTo(deadline) >= 0) {
				paras.add(new Object[] { repo_name, map.get("name"), date, variables.get("message") });
			}
		}
		jdbcTemplateObject.batchUpdate(sql, paras);
	}

	public ArrayList<CommitBean> queryCommitOfRepo(String repo_name) {
		String sql = "SELECT * FROM Commit WHERE repo_name=? ORDER BY created_at DESC";
		List<CommitBean> commits_temp = jdbcTemplateObject.query(sql, new Object[] { repo_name }, new CommitMapper());
		ArrayList<CommitBean> commits = new ArrayList<CommitBean>();
		commits.addAll(commits_temp);

		return commits;
	}

	public Date queryLatestDate(String repo_name) {
		String sql = "SELECT MAX(created_at) as updated_at FROM Commit WHERE repo_name=?";
		List<Map<String, Object>> maps = jdbcTemplateObject.queryForList(sql, repo_name);
		if (maps.size() > 0) {
			return (Date) (maps.get(0).get("updated_at"));
		}
		return null;
	}

}