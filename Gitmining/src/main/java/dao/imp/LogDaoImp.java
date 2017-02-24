package dao.imp;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import bean.HistoryBean;
import bean.LogBean;
import bean.RepoBean;
import dao.LogDao;
import dao.mapper.HistoryMapper;
import dao.mapper.LogMapper;
import dao.mapper.RepoMapper;
import utility.Language;

public class LogDaoImp implements LogDao {
	private JdbcTemplate jdbcTemplateObject;

	public void setdatasource(DataSource datasource) {
		this.jdbcTemplateObject = new JdbcTemplate(datasource);
	}
	
	public LogBean queryLog(Object o) {
		if(o == null){
			return null;
		}
		String login = (String)o;
		String sql = "SELECT * FROM Log WHERE login = ? LIMIT 1";
		List<LogBean> logs = jdbcTemplateObject.query(sql, new Object[]{login}, new LogMapper());
		if(logs == null || logs.size() == 0){
			return null;
		}else{
			LogBean log = logs.get(0);
			sql = "SELECT * FROM LogRepo WHERE login=? ORDER BY count DESC";
			List<HistoryBean> history = jdbcTemplateObject.query(sql, new Object[]{login}, new HistoryMapper());
			sql = "SELECT count(*) As commits FROM Commit WHERE repo_name = ? AND created_at > ? LIMIT 1";
			for (HistoryBean h : history) {
				h.setCommits(jdbcTemplateObject.queryForList(sql, new Object[]{h.getRepo(), h.getLatest()}, java.lang.Integer.class).get(0));
			}
			log.setHistory(history);
			return  log;
		}
	}
	
	public void deleteLog(String login) {
		String sql = "DELETE FROM Log WHERE login=?";
		jdbcTemplateObject.update(sql, login);
	}
	
	public void updateLog(LogBean log){
		String sql = "DELETE FROM LogRepo WHERE login=?";
		jdbcTemplateObject.update(sql, log.getLogin());
		List<Object[]> paras = new ArrayList<Object[]>();
		for (HistoryBean h : log.getAllHistory()) {
			paras.add(new Object[]{log.getLogin(), h.getRepo(), h.getComplex(), h.getCount(), h.getLatest(), h.getStart(), h.getFinish(), h.getBlock(), h.getLanguage().getType()});
		}
		sql = "INSERT INTO LogRepo (login, repo_name, complex, count, latest, start, finish, block, language) VALUES (?,?,?,?,?,?,?,?,?)";
		
		jdbcTemplateObject.batchUpdate(sql, paras);
		
		sql = "REPLACE INTO Log (login, join_in, aptitute, grade, avatar, language) VALUES (?,?,?,?,?,?)";

		jdbcTemplateObject.update(sql, new Object[]{log.getLogin(), log.getJoin_in(), log.getAptitute(), log.getGrade(), log.getAvatar(), log.getLanguage().getType()});
	}

	public List<LogBean> queryLogRank(){
		String sql = "SELECT * FROM Log WHERE aptitute > 0 ORDER BY grade DESC";
		return jdbcTemplateObject.query(sql, new LogMapper());
	}

	public List<RepoBean> queryRecommendRepos(List<String> names, int complex, Language language) {
		List<RepoBean> results = new ArrayList<RepoBean>();
		String sql = "SELECT * FROM Repo WHERE complex=? AND full_name IN (SELECT repo_commend FROM RepoRecommend WHERE repo_base=?)";
		for (String name : names) {
			for (RepoBean repo : jdbcTemplateObject.query(sql, new Object[]{complex, name}, new RepoMapper())) {
				if(!names.contains(repo.getFull_name())){
					boolean isRepeated = false;
					for (RepoBean repo2 : results) {
						if(repo2.getFull_name().equals(repo.getFull_name())){
							isRepeated = true;
						}
					}
					if(!isRepeated){
						results.add(repo);
					}
				}
			}
		}
		if(results.size() < 6){
			sql = "SELECT * FROM Repo WHERE complex = ?";
			ArrayList<Object> paras = new ArrayList<Object>();
			paras.add(complex);
			if (language != Language.All) {
				sql += " AND language = ?";
				paras.add(language.getType());
			}
			sql += " ORDER BY (contributor_rank + star_rank + fork_rank + issue_rank + subscribers_count) LIMIT " + (50 - results.size());
			for (RepoBean repo : jdbcTemplateObject.query(sql, paras.toArray(), new RepoMapper())) {
				if(!names.contains(repo.getFull_name())){
					boolean isRepeated = false;
					for (RepoBean repo2 : results) {
						if(repo2.getFull_name().equals(repo.getFull_name())){
							isRepeated = true;
						}
					}
					if(!isRepeated){
						results.add(repo);
					}
				}
			}
		}
		
		if(results.size() > 6){
			results = results.subList(0, 6);
		}

		return results;
	}
	
	public void clearLog(String login){
		String sql = "DELETE FROM Log WHERE login=?";
		jdbcTemplateObject.update(sql, new Object[]{login});
		sql = "DELETE FROM LogRepo WHERE login=?";
		jdbcTemplateObject.update(sql, new Object[]{login});
	}
}