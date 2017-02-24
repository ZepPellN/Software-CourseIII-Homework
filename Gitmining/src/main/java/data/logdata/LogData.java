package data.logdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.kohsuke.github.GHMyself;
import org.kohsuke.github.GitHub;
import bean.HistoryBean;
import bean.LogBean;
import bean.RepoBean;
import dao.LogDao;
import dataservice.logdataservice.LogDataService;
import utility.GitminingHelper;
import utility.Language;

public class LogData implements LogDataService {
	private static LogData loginData = null;
	private LogDao logDao = null;

	private LogData() {
		logDao = GitminingHelper.getInstance().getLogDao();
	};

	public static LogData getInstance() {
		return loginData == null ? (loginData = new LogData()) : loginData;
	}

	public String[] login(String login, String password) {
		LogBean currentUser = null;
		String[] result = new String[2];
		GitHub git = null;
		try {
			git = GitHub.connectUsingPassword(login, password);
		} catch (IOException e) {
			System.out.println("network error.");
		}

		boolean isValid = false;
		try {
			isValid = git.isCredentialValid();
		} catch (IOException e) {
			System.out.println("network error");
		}
		if (isValid) {
			GHMyself self = null;
			try {
				self = git.getMyself();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			login = self.getLogin();
			result[1] = login;
			currentUser = this.show(login);
			if ((currentUser == null) || (currentUser.getAptitute() == -1)) {
				currentUser = new LogBean(login, new Date(), 0, 0, self.getAvatarUrl(), Language.All);
				result[0] = "new";
			} else {
				result[0] = "success";
			}
			this.save(currentUser);
		} else {
			currentUser = null;
			result[0] = "failure";
		}
		return result;
	}
	
	public void save(LogBean currentUser) {
		if (currentUser != null) {
			logDao.updateLog(currentUser);
		}
	}

	public boolean startLearning(String repo, Language language, Object login) {
		LogBean currentUser = this.show(login);

		if ((currentUser != null) && (currentUser.startLearning(repo, language))) {
			this.save(currentUser);
			return true;
		}
		return false;
	}

	public boolean stopLearning(String repo, Object login){
		LogBean currentUser = this.show(login);

		if((currentUser != null) && (currentUser.stopLearning(repo))){
			this.save(currentUser);
			return true;
		}
		return false;
	}
	
	public boolean finishLearning(String repo, Object login) {
		LogBean currentUser = this.show(login);

		boolean result = false;
		if (currentUser != null) {
			result = currentUser.finishLearning(repo);
			this.save(currentUser);
		}
		return result;
	}

	public List<RepoBean> queryRecommendRepos(Object login) {
		LogBean currentUser = this.show(login);

		if (currentUser != null) {
			List<String> repos = new ArrayList<String>();
			for (HistoryBean h : currentUser.getAllLearningList()) {
				repos.add(h.getRepo());
			}
			return logDao.queryRecommendRepos(repos, currentUser.getAptitute(), currentUser.getLanguage());
		}
		return new ArrayList<RepoBean>();
	}

	public List<HistoryBean> queryLearningList(int complex, Object login) {
		LogBean currentUser = this.show(login);

		if (currentUser != null) {
			return currentUser.getLearningList(complex);
		} else {
			return new ArrayList<HistoryBean>();
		}
	}

	public List<HistoryBean> queryHistory(Object login) {
		LogBean currentUser = this.show(login);
		
		if (currentUser != null) {
			return currentUser.getHistory();
		} else {
			return new ArrayList<HistoryBean>();
		}
	}

	public void updateHistory(RepoBean repo, Object login) {
		LogBean currentUser = this.show(login);

		if (currentUser != null) {
			currentUser.updateHistory(repo);
			this.save(currentUser);
		}
	}

	public int getLearningState(RepoBean repo, Object login) {
		LogBean currentUser = this.show(login);

		if (currentUser == null || currentUser.getAptitute() == 0) {
			return 0;
		} else {
			for (HistoryBean learning : currentUser.getAllLearningList()) {
				if (learning.getRepo().equals(repo.getFull_name())) {
					if (learning.getFinish() == null) {
						return 2;
					} else {
						return 3;
					}
				}
			}
			List<HistoryBean> learnings = currentUser.getAllLearningList();
			if (learnings.size() > 0) {
				if ((currentUser.getAptitute() == 1 && (learnings.get(learnings.size() - 1).getBlock()
						+ learnings.get(learnings.size() - 1).getComplex() > 9))
						|| (currentUser.getAptitute() == 2 && (learnings.get(learnings.size() - 1).getBlock()
								+ learnings.get(learnings.size() - 1).getComplex() > 25))) {
					return 4;
				}
			}
			return 1;
		}
	}

	public void deleteHistory(String input, Object login) {
		LogBean currentUser = this.show(login);

		if (currentUser != null) {
			currentUser.deleteHistory(input);
			this.save(currentUser);
		}
	}

	public void clearHistory(Object login) {
		LogBean currentUser = this.show(login);

		if (currentUser != null) {
			currentUser.clearHistory();
			this.save(currentUser);
		}
	}

	public List<LogBean> queryLogRank() {
		return logDao.queryLogRank();
	}

	public LogBean show(Object login) {
		return logDao.queryLog(login);
	}

	public void setInfo(int aptitute, Language language, Object login) {
		LogBean currentUser = this.show(login);

		if (currentUser != null) {
			currentUser.setAptitute(aptitute);
			currentUser.setLanguage(language);
			this.save(currentUser);
		}
	}

	public List<RepoBean> sortRepos(List<RepoBean> repos, Object login) {
		LogBean currentUser = this.show(login);

		if(currentUser != null){
			Map<Language, Integer> languages = new HashMap<Language, Integer>();
			languages.putAll(currentUser.getPreference());
			int all = languages.remove(Language.All);
			if(currentUser.getLanguage() == Language.All){
				if(all == 0){
					return repos;
				}else{
					for (Language language : languages.keySet()) {
						languages.replace(language, languages.get(language) * 15 / all);
					}
				}
			}else{
				if(all != 0){
					for (Language language : languages.keySet()) {
						languages.replace(language, languages.get(language) * 10 / all);
					}
				}
				languages.replace(currentUser.getLanguage(), languages.get(currentUser.getLanguage()) + 5);
			}
			List<RepoBean> first = new ArrayList<RepoBean>();
			List<RepoBean> left = new ArrayList<RepoBean>();
			for (RepoBean repo : repos) {
				if(repo.getComplex() == currentUser.getAptitute()){
					if(languages.get(repo.getLanguage()) > 0){
						first.add(repo);
						languages.replace(repo.getLanguage(), languages.get(repo.getLanguage()) - 1);
						continue;
					}
				}
				left.add(repo);
			}
			repos.clear();
			repos.addAll(first);
			repos.addAll(left);
		}
		return repos;
	}
}