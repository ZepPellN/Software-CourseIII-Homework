package dao.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.RecommendBean;
import bean.RepoBean;
import bean.RepoRadarBean;
import dao.RepoDao;
import dao.mapper.RecommendMapper;
import dao.mapper.RepoMapper;
import dao.mapper.RepoRadarMapper;
import utility.CategoryOfRepo;
import utility.GitminingHelper;
import utility.Language;

public class RepoDaoImp implements RepoDao {
	private JdbcTemplate jdbcTemplateObject;

	public void setdatasource(DataSource datasource) {
		this.jdbcTemplateObject = new JdbcTemplate(datasource);
	}

	public void addRepo(Map<String, String> variables) {
		String full_name = variables.get("full_name");
		String category_string = "All";
		for (CategoryOfRepo category : CategoryOfRepo.setTypes(variables.get("description"))) {
			category_string += ("," + category.getValue());
		}
		;
		int contributors_count = GitminingHelper
				.sendGet("http://gitmining.net/api/repository/" + full_name + "/contributors/login").split(",").length
				+ 1;
		String sql = "REPLACE INTO Repo(id, full_name, name, owner_name, default_branch, homepage, description, created_at, updated_at, pushed_at, size, stargazers_count, subscribers_count, forks_count, watchers_count, open_issues_count, language, fork, has_issues, private, contributors_count, category)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplateObject.update(sql, variables.get("id"), full_name, variables.get("name"), full_name.split("/")[0],
				variables.get("default_branch"), variables.get("homepage"), variables.get("description"),
				java.sql.Date.valueOf(variables.get("created_at").substring(0, 10)),
				java.sql.Date.valueOf(variables.get("updated_at").substring(0, 10)),
				java.sql.Date.valueOf(variables.get("pushed_at").substring(0, 10)), variables.get("size"),
				variables.get("stargazers_count"), variables.get("subscribers_count"), variables.get("forks_count"),
				variables.get("watchers_count"), variables.get("open_issues_count"), variables.get("language"),
				Boolean.parseBoolean(variables.get("fork")), Boolean.parseBoolean(variables.get("has_issues")),
				Boolean.parseBoolean(variables.get("private")), contributors_count, category_string);
	}

	public void addRepoRecommend(List<Object[]> paras) {
		String sql = "INSERT INTO RepoRecommend(repo_base, repo_commend, relate)VALUES(?,?,?)";
		jdbcTemplateObject.batchUpdate(sql, paras);
	}

	public void addRepoAnalysis(List<Object[]> paras){
		String sql = "INSERT INTO RepoAnalysis(repo_name, init_mark, mark)VALUES(?,?,?)";
		jdbcTemplateObject.batchUpdate(sql, paras);
	}
	
	public ArrayList<String> getAllNames() {
		String sql = "SELECT full_name FROM Repo";
		ArrayList<String> names = new ArrayList<String>();
		names.addAll(jdbcTemplateObject.queryForList(sql, java.lang.String.class));
		return names;
	}

	public List<String> queryRepoOfLan(Language language) {
		String sql = "SELECT full_name FROM Repo WHERE language = ?";
		return jdbcTemplateObject.queryForList(sql, new Object[] { language.getType() }, java.lang.String.class);
	}

	public void setHotRepos() {
		String[] sqls = new String[3];
		sqls[0] = "DELETE FROM HotRepo";
		sqls[1] = "INSERT INTO HotRepo (repo_name, user_name) SELECT full_name, owner_name FROM Repo	WHERE forks_count / 5 + stargazers_count / 320 + subscribers_count > 90";
		sqls[2] = "UPDATE Repo SET `language`='others' WHERE language IS NULL OR (language <> 'Java' AND language <> 'Ruby' AND language <> 'Python' AND language <> 'C' AND language <> 'JavaScript' AND language <> 'Perl' AND language <> 'PHP' AND language <> 'Cplusplus' AND language <> 'Shell' AND language <> 'HTML')";
		jdbcTemplateObject.batchUpdate(sqls);
	}

	public void setRank() {
		String[] sqls = new String[6];
		sqls[0] = "UPDATE Repo U INNER JOIN (SELECT full_name, rank FROM (SELECT full_name, @curRank := IF(@prevRank = contributors_count, @curRank, @incRank) AS rank, @incRank := @incRank + 1, @prevRank := contributors_count FROM Repo u, (SELECT @curRank := 0, @prevRank := NULL, @incRank := 1) r ORDER BY contributors_count DESC) s) R ON R.full_name = U.full_name SET U.contributor_rank = R.rank";
		sqls[1] = "UPDATE Repo U INNER JOIN (SELECT full_name, rank FROM (SELECT full_name, @curRank := IF(@prevRank = size, @curRank, @incRank) AS rank, @incRank := @incRank + 1, @prevRank := size FROM Repo u, (SELECT @curRank := 0, @prevRank := NULL, @incRank := 1) r ORDER BY size DESC) s) R ON R.full_name = U.full_name SET U.size_rank = R.rank";
		sqls[2] = "UPDATE Repo U INNER JOIN (SELECT full_name, rank FROM (SELECT full_name, @curRank := IF(@prevRank = stargazers_count, @curRank, @incRank) AS rank, @incRank := @incRank + 1, @prevRank := stargazers_count FROM Repo u, (SELECT @curRank := 0, @prevRank := NULL, @incRank := 1) r ORDER BY stargazers_count DESC) s) R ON R.full_name = U.full_name SET U.star_rank = R.rank";
		sqls[3] = "UPDATE Repo U INNER JOIN (SELECT full_name, rank FROM (SELECT full_name, @curRank := IF(@prevRank = forks_count, @curRank, @incRank) AS rank, @incRank := @incRank + 1, @prevRank := forks_count FROM Repo u, (SELECT @curRank := 0, @prevRank := NULL, @incRank := 1) r ORDER BY forks_count DESC) s) R ON R.full_name = U.full_name SET U.fork_rank = R.rank";
		sqls[4] = "UPDATE Repo U INNER JOIN (SELECT full_name, rank FROM (SELECT full_name, @curRank := IF(@prevRank = open_issues_count, @curRank, @incRank) AS rank, @incRank := @incRank + 1, @prevRank := open_issues_count FROM Repo u, (SELECT @curRank := 0, @prevRank := NULL, @incRank := 1) r ORDER BY open_issues_count DESC) s) R ON R.full_name = U.full_name SET U.issue_rank = R.rank";
		sqls[5] = "UPDATE Repo U INNER JOIN (SELECT full_name, rank FROM (SELECT full_name, @curRank := IF(@prevRank = subscribers_count, @curRank, @incRank) AS rank, @incRank := @incRank + 1, @prevRank := subscribers_count FROM Repo u, (SELECT @curRank := 0, @prevRank := NULL, @incRank := 1) r ORDER BY subscribers_count DESC) s) R ON R.full_name = U.full_name SET U.subscriber_rank = R.rank";
		jdbcTemplateObject.batchUpdate(sqls);
	}

	
	
	public RepoBean queryRepoByNamePrecisely(String name) {
		RepoBean repo = null;
		String sql = "SELECT * FROM Repo WHERE full_name=? LIMIT 1";
		List<RepoBean> users = jdbcTemplateObject.query(sql, new Object[] { name }, new RepoMapper());
		if (users.size() > 0) {
			repo = users.get(0);
		}
		return repo;
	}

	public List<RepoBean> queryRepoByNameVaguely(String partname, String year, Language language,
			CategoryOfRepo category) {
		String sql = "SELECT * FROM Repo WHERE";
		ArrayList<Object> paras = new ArrayList<Object>();
		if (partname.length() != 0) {
			sql += " name LIKE ? AND";
			paras.add("%" + partname + "%");
		}
		if (!year.equals("All")) {
			sql += " year(created_at)=? AND";
			paras.add(year);
		}
		if (language != Language.All) {
			sql += " language=? AND";
			paras.add(language.getType());
		}
		if (category != CategoryOfRepo.All) {
			sql += " category LIKE ? AND";
			paras.add("%" + category.getValue() + "%");
		}
		sql = sql.substring(0, sql.length() - 4);
		sql += " ORDER BY (contributor_rank + star_rank + fork_rank + issue_rank + subscribers_count)";
		List<RepoBean> repos = jdbcTemplateObject.query(sql, paras.toArray(), new RepoMapper());
		return repos;
	}

	public List<RecommendBean> queryRecommendOfRepo(String repo_name) {
		String sql = "SELECT * FROM RepoRecommend WHERE repo_base=?";
		return jdbcTemplateObject.query(sql, new Object[] { repo_name }, new RecommendMapper());
	}

	public String queryRecommendLine_Init(String repo_name){
		String sql = "SELECT init_mark FROM RepoAnalysis WHERE repo_name  = ? LIMIT 1";
		List<String> result = jdbcTemplateObject.queryForList(sql, new Object[]{repo_name}, java.lang.String.class);
		if(result.size() > 0){
			return result.get(0);
		}
		return "";
	}
	
	public String queryRecommendLine(String repo_name){
		String sql = "SELECT mark FROM RepoAnalysis WHERE repo_name  = ? LIMIT 1";
		List<String> result = jdbcTemplateObject.queryForList(sql, new Object[]{repo_name}, java.lang.String.class);
		if(result.size() > 0){
			return result.get(0);
		}
		return "";
	}
	
	public Map<String, Integer> queryCommitOfRepo(String repo_name) {
		String sql = "SELECT created_at, COUNT(*) AS number FROM Commit WHERE repo_name  = ? GROUP BY created_at ORDER BY created_at DESC";
		List<Map<String, Object>> maps = jdbcTemplateObject.queryForList(sql, new Object[] { repo_name });
		Map<String, Integer> result = new HashMap<String, Integer>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (Map<String, Object> map : maps) {
			result.put(format.format((Date) map.get("created_at")), (int) ((long) map.get("number")));
		}
		return result;
	}

	public RepoRadarBean queryRankOfRepo(String repo_name) {
		String sql = "SELECT (1 - contributor_rank / T.total) AS Contributor, (1 - size_rank / T.total) AS Size, (1 - star_rank / T.total) AS Star, (1 - fork_rank / T.total) AS Fork, (1 - issue_rank / T.total) AS Issue, (1 - subscriber_rank / T.total) AS Subscriber FROM Repo, (SELECT COUNT(*) AS total FROM Repo) T WHERE full_name = ?";
		List<RepoRadarBean> result = jdbcTemplateObject.query(sql, new Object[] { repo_name }, new RepoRadarMapper());
		RepoRadarBean radar = null;
		if (result.size() > 0) {
			radar = result.get(0);
		}
		return radar;
	}

	public Map<Integer, Integer> staticsCreated_at() {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();

		String sql = "SELECT year(created_at) AS year_in, COUNT(*) AS number FROM Repo GROUP BY year_in";
		List<Map<String, Object>> maps = jdbcTemplateObject.queryForList(sql);
		for (Map<String, Object> map : maps) {
			result.put((int) map.get("year_in"), (int) ((long) map.get("number")));
		}
		return result;
	}

	public Map<Integer, Integer> staticsForks_Count() {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();

		String sql = "SELECT forks_count, COUNT(*) AS number FROM Repo GROUP BY forks_count";
		List<Map<String, Object>> maps = jdbcTemplateObject.queryForList(sql);
		for (Map<String, Object> map : maps) {
			result.put((int) map.get("forks_count"), (int) ((long) map.get("number")));
		}
		return result;
	}

	public Map<Integer, Integer> staticsStargazers_count() {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();

		String sql = "SELECT stargazers_count, COUNT(*) AS number FROM Repo GROUP BY stargazers_count";
		List<Map<String, Object>> maps = jdbcTemplateObject.queryForList(sql);
		for (Map<String, Object> map : maps) {
			result.put((int) map.get("stargazers_count"), (int) ((long) map.get("number")));
		}
		return result;
	}

	public Map<Language, List<Pair<Integer, Integer>>> staticsLanguage() {
		Map<Language, List<Pair<Integer, Integer>>> result = new HashMap<Language, List<Pair<Integer, Integer>>>();

		String sql = "SELECT language, year(created_at) AS year_in, COUNT(*) AS number FROM Repo GROUP BY language, year_in";
		List<Map<String, Object>> maps = jdbcTemplateObject.queryForList(sql);
		Language language = null;
		List<Pair<Integer, Integer>> year_count;
		for (Map<String, Object> map : maps) {
			language = Language.setType((String) map.get("language"));
			if ((year_count = result.get(language)) == null) {
				year_count = new ArrayList<Pair<Integer, Integer>>();
			}
			year_count.add(Pair.of((int) map.get("year_in"), (int) ((long) map.get("number"))));
			result.put(language, year_count);
		}
		return result;
	}
}