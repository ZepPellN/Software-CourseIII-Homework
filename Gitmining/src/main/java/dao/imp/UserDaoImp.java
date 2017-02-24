package dao.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import bean.UserAssessBean;
import bean.UserBean;
import dao.UserDao;
import dao.mapper.UserAssessMapper;
import dao.mapper.UserMapper;
import utility.OwnerType;

public class UserDaoImp implements UserDao {
	private JdbcTemplate jdbcTemplateObject;

	public void setdatasource(DataSource datasource) {
		this.jdbcTemplateObject = new JdbcTemplate(datasource);
	}

	public void addUser(Map<String, String> variables) {
		String sql = "REPLACE INTO User(id, login, name, company, location, email, blog, bio, public_repos, public_gists, followers, following, created_at, updated_at, site_admin, hireable, type, avatar)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplateObject.update(sql, variables.get("id"), variables.get("login"), variables.get("name"),
				variables.get("company"), variables.get("location"), variables.get("email"), variables.get("blog"),
				variables.get("bio"), variables.get("public_repos"), variables.get("public_gists"),
				variables.get("followers"), variables.get("following"),
				java.sql.Date.valueOf(variables.get("created_at").substring(0, 10)),
				java.sql.Date.valueOf(variables.get("updated_at").substring(0, 10)),
				Boolean.parseBoolean(variables.get("site_admin")), Boolean.parseBoolean(variables.get("hireable")),
				variables.get("type"), variables.get("avatar_url"));
	}

	public UserBean queryUserByNamePrecisely(String name) {
		UserBean user = null;
		String sql = "SELECT * FROM User WHERE login=? LIMIT 1";
		List<UserBean> users = jdbcTemplateObject.query(sql, new Object[] { name }, new UserMapper());
		if (users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	public ArrayList<String> getAllNames() {
		String sql = "SELECT login FROM User";
		ArrayList<String> names = new ArrayList<String>();
		names.addAll(jdbcTemplateObject.queryForList(sql, java.lang.String.class));
		return names;
	}

	public ArrayList<UserBean> queryUserByNameVaguely(String partname) {
		String sql = "SELECT * FROM User WHERE login LIKE ? ORDER BY login";
		List<UserBean> users_temp = jdbcTemplateObject.query(sql, new Object[] { "%" + partname + "%" },
				new UserMapper());
		ArrayList<UserBean> users = new ArrayList<UserBean>();
		users.addAll(users_temp);
		return users;
	}

	public ArrayList<String> queryRepoOfUser(String user_name) {
		String sql = "SELECT full_name FROM Repo WHERE owner_name=?";
		ArrayList<String> repos = new ArrayList<String>();
		repos.addAll(jdbcTemplateObject.queryForList(sql, new Object[] { user_name }, java.lang.String.class));

		return repos;
	}

	public ArrayList<String> queryHotRepoOfUser(String user_name) {
		String sql = "SELECT repo_name FROM HotRepo WHERE user_name=?";
		ArrayList<String> repos = new ArrayList<String>();
		repos.addAll(jdbcTemplateObject.queryForList(sql, new Object[] { user_name }, java.lang.String.class));

		return repos;
	}

	public Map<String, Integer> queryEventOfUser(String user_Name) {
		String sql = "SELECT created_at, COUNT(*) AS number FROM Event WHERE user_name  = ? GROUP BY created_at ORDER BY created_at DESC";
		List<Map<String, Object>> maps = jdbcTemplateObject.queryForList(sql, new Object[] { user_Name });
		Map<String, Integer> result = new HashMap<String, Integer>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (Map<String, Object> map : maps) {
			result.put(format.format((Date) map.get("created_at")), (int) ((long) map.get("number")));
		}
		return result;
	}

	public UserAssessBean queryRankOfUser(String user_name) {
		String sql = "SELECT (1 - popularity_rank / T.total) AS p, (1 - enthusiasm_rank / T.total) AS e, (1 - activity_rank / T.total) AS a FROM User, (SELECT COUNT(*) AS total FROM User) T WHERE login = ?";
		List<UserAssessBean> result = jdbcTemplateObject.query(sql, new Object[] { user_name }, new UserAssessMapper());
		UserAssessBean assess = null;
		if (result.size() > 0) {
			assess = result.get(0);
		}
		return assess;
	}

	public void setRank() {
		String[] sqls = new String[4];
		sqls[0] = "UPDATE User U INNER JOIN (SELECT user_name, COUNT(*) AS number FROM Event WHERE DATEDIFF(now(),created_at) < 30 GROUP BY user_name) AS A ON U.login = A.user_name	SET U.activity = A.number";
		sqls[1] = "UPDATE User U INNER JOIN (SELECT login, rank FROM (SELECT login, @curRank := IF(@prevRank = activity, @curRank, @incRank) AS rank, @incRank := @incRank + 1, @prevRank := activity FROM User u, (SELECT @curRank := 0, @prevRank := NULL, @incRank := 1) r ORDER BY activity DESC) s) R ON R.login = U.login SET U.activity_rank = R.rank";
		sqls[2] = "UPDATE User U INNER JOIN (SELECT login, rank FROM (SELECT login, @curRank := IF(@prevRank = followers, @curRank, @incRank) AS rank, @incRank := @incRank + 1, @prevRank := followers FROM User u, (SELECT @curRank := 0, @prevRank := NULL, @incRank := 1) r ORDER BY followers DESC) s) R ON R.login = U.login SET U.popularity_rank = R.rank";
		sqls[3] = "UPDATE User U INNER JOIN (SELECT login, rank FROM (SELECT login, @curRank := IF(@prevRank = following + public_repos + public_gists, @curRank, @incRank) AS rank, @incRank := @incRank + 1, @prevRank := following + public_repos + public_gists FROM User u, (SELECT @curRank := 0, @prevRank := NULL, @incRank := 1) r ORDER BY following + public_repos + public_gists DESC) s) R ON R.login = U.login SET U.enthusiasm_rank = R.rank";
		jdbcTemplateObject.batchUpdate(sqls);
	}

	public Map<OwnerType, Integer> staticsOwnerType() {
		Map<OwnerType, Integer> result = new HashMap<OwnerType, Integer>();

		String sql = "SELECT type, COUNT(*) AS number FROM User GROUP BY type";
		List<Map<String, Object>> maps = jdbcTemplateObject.queryForList(sql);
		for (Map<String, Object> map : maps) {
			result.put(OwnerType.setType((String) map.get("type")), (int) ((long) map.get("number")));
		}

		return result;
	}

	public Map<Integer, Integer> staticsCreated_at() {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();

		String sql = "SELECT year(created_at) AS year_in, COUNT(*) AS number FROM User GROUP BY year_in";
		List<Map<String, Object>> maps = jdbcTemplateObject.queryForList(sql);
		for (Map<String, Object> map : maps) {
			result.put((int) map.get("year_in"), (int) ((long) map.get("number")));
		}
		return result;
	}

	public Map<Integer, Integer> staticsRepo() {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();

		String sql = "SELECT public_repos, COUNT(*) AS number FROM User GROUP BY public_repos";
		List<Map<String, Object>> maps = jdbcTemplateObject.queryForList(sql);
		for (Map<String, Object> map : maps) {
			result.put((int) map.get("public_repos"), (int) ((long) map.get("number")));
		}
		return result;
	}
}