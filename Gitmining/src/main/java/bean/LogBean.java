package bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utility.Language;

public class LogBean {

	private String login;

	private Date join_in;

	private int span;

	private int aptitute;

	private int grade;

	private List<HistoryBean> history;

	private String avatar;

	private Language language;

	private Map<Language, Integer> preference;
	
	public LogBean() {
	}

	public LogBean(String login, Date join_in, int aptitute, int grade, String avatar, Language language) {
		this.login = login;

		this.join_in = join_in;

		long now = new Date().getTime();
		long join = this.join_in.getTime();
		this.span = (int) ((now - join) / (1000 * 60 * 60 * 24)) + 1;

		this.aptitute = aptitute;

		this.grade = grade;

		this.history = new ArrayList<HistoryBean>();

		this.preference = new HashMap<Language, Integer>();
		
		for (Language l : Language.values()) {
			this.preference.put(l, 0);
		}
		
		this.avatar = avatar;

		this.language = language;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<HistoryBean> getHistory() {
		List<HistoryBean> validHistory = new ArrayList<HistoryBean>();
		for (HistoryBean h : history) {
			if (h.getCount() > 0) {
				validHistory.add(h);
			}
		}
		validHistory.sort(new Comparator<HistoryBean>() {
			public int compare(HistoryBean o1, HistoryBean o2) {
				return o2.getCount() - o1.getCount();
			}
		});
		return validHistory;
	}

	public void setHistory(List<HistoryBean> history) {
		this.history = history;

		int count = 0;
		for (HistoryBean h : history) {
			if(h.getStart() == null){
				this.preference.replace(h.getLanguage(), this.preference.get(h.getLanguage()) + 1);
				count ++;
			}else{
				this.preference.replace(h.getLanguage(), this.preference.get(h.getLanguage()) + 5);
				count += 5;
			}
		}
		this.preference.replace(Language.All, count);
	}

	public String getLogin() {
		return login;
	}

	public Date getJoin_in() {
		return join_in;
	}

	public void setJoin_in(Date join_in) {
		this.join_in = join_in;
	}

	public int getSpan() {
		return span;
	}

	public void setSpan(int span) {
		this.span = span;
	}

	public int getAptitute() {
		return aptitute;
	}

	public void setAptitute(int aptitute) {
		this.aptitute = aptitute;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	
	public Map<Language, Integer> getPreference() {
		return preference;
	}

	public void setPreference(Map<Language, Integer> preference) {
		this.preference = preference;
	}

	public void updateHistory(RepoBean repo) {
		this.preference.replace(repo.getLanguage(), this.preference.get(repo.getLanguage()) + 1);
		this.preference.replace(Language.All, this.preference.get(Language.All) + 1);
		this.updateLanguage();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (HistoryBean h : history) {
			if (repo.getFull_name().equals(h.getRepo())) {
				history.remove(h);
				history.add(new HistoryBean(h.getRepo(), h.getCount() + 1, sdf.format(new Date()), h.getStart(),
						h.getFinish(), h.getCommits(), h.getComplex(), h.getBlock(), h.getLanguage()));
				return;
			}
		}
		history.add(new HistoryBean(repo.getFull_name(), 1, sdf.format(new Date()), null, null, 0, repo.getComplex(),
				-1, repo.getLanguage()));
	}

	private void updateLanguage(){
		int all = this.preference.remove(Language.All);
		for (Language l : this.preference.keySet()) {
			if(this.preference.get(l) > all * 0.6){
				this.language = l;
				this.preference.put(Language.All, all);
				return;
			}
		}
		this.preference.put(Language.All, all);
	}
	
	public void deleteHistory(String repo) {
		for (int i = 0; i < history.size(); i++) {
			if (repo.equals(history.get(i).getRepo())) {
				if(history.get(i).getStart() == null){
					history.remove(i);
				}else{
					history.get(i).setCount(0);
				}
				return;
			}
		}
	}

	public void clearHistory() {
		history = this.getAllLearningList();
		for (int i = 0; i < history.size(); i++) {
			history.get(i).setCount(0);
		}
	}

	public boolean startLearning(String repo, Language language) {
		for (int i = 0; i < history.size(); i++) {
			if (history.get(i).getRepo().equals(repo)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				List<HistoryBean> learnings = this.getAllLearningList();
				history.get(i).setStart(sdf.format(new Date()));
				if (learnings.size() == 0) {
					history.get(i).setBlock(1);
				} else if (learnings.get(learnings.size() - 1).getBlock() <= 9
						&& learnings.get(learnings.size() - 1).getBlock()
								+ learnings.get(learnings.size() - 1).getComplex() > 9) {
					history.get(i).setBlock(10);
				} else if (learnings.get(learnings.size() - 1).getBlock() <= 25
						&& learnings.get(learnings.size() - 1).getBlock()
								+ learnings.get(learnings.size() - 1).getComplex() > 25) {
					history.get(i).setBlock(26);
				} else if (learnings.get(learnings.size() - 1).getBlock()
								+ learnings.get(learnings.size() - 1).getComplex() > 50) {
					history.get(i).setBlock(0);
				} else {
					history.get(i).setBlock(learnings.get(learnings.size() - 1).getBlock()
							+ learnings.get(learnings.size() - 1).getComplex());
				}
				this.preference.replace(language, this.preference.get(language) + 4);
				this.preference.replace(Language.All, this.preference.get(Language.All) + 4);
				this.updateLanguage();
				return true;
			}
		}
		return false;
	}

	public boolean stopLearning(String repo) {
		int block = -1;
		int complex = 0;
		for (int i = 0; i < history.size(); i++) {
			if (history.get(i).getRepo().equals(repo)) {
				history.get(i).setStart(null);
				this.grade -= history.get(i).getComplex() * 5;
				block = history.get(i).getBlock();
				complex = history.get(i).getComplex();
				history.get(i).setBlock(-1);
				break;
			}
		}
		if (block == -1) {
			return false;
		}else if(block == 0){
			return true;
		}
		for (int i = 0; i < history.size(); i++) {
			if (history.get(i).getBlock() > block) {
				history.get(i).setBlock(history.get(i).getBlock() - complex);
			}
		}
		return true;
	}

	public boolean finishLearning(String repo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < history.size(); i++) {
			if (history.get(i).getRepo().equals(repo)) {
				history.get(i).setFinish(sdf.format(new Date()));
				int days = 0;
				try {
					days = (int) ((new Date().getTime() - sdf.parse(history.get(i).getStart()).getTime())
							/ (24 * 60 * 60 * 1000)) + 1;
				} catch (ParseException e) {
					e.printStackTrace();
				}
				this.grade += history.get(i).getComplex() * 200 / days;
				List<Integer> finishBlock = new ArrayList<Integer>();
				for (HistoryBean h : history) {
					if (h.getFinish() != null) {
						for (int j = 0; j < h.getComplex(); j++) {
							finishBlock.add(h.getBlock() + j);
						}
					}
				}
				if (this.aptitute == 1) {
					List<Integer> level = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
					if (finishBlock.containsAll(level)) {
						this.aptitute = 2;
						return true;
					}
				}
				if (this.aptitute == 2) {
					List<Integer> level = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
							19, 20, 21, 22, 23, 24, 25);
					if (finishBlock.containsAll(level)) {
						this.aptitute = 3;
						return true;
					}
				}
				return false;
			}
		}

		return false;
	}

	public List<HistoryBean> getLearningList(int level) {
		List<HistoryBean> learnings = this.getAllLearningList();
		List<HistoryBean> result = new ArrayList<HistoryBean>();
		int low = 0;
		int high = 0;
		switch (level) {
		case 1:
			low = 0;
			high = 9;
			break;
		case 2:
			low = 9;
			high = 25;
			break;
		case 3:
			low = 25;
			high = 50;
			break;
		default:
			break;
		}
		for (HistoryBean h : learnings) {
			if ((h.getBlock() > low) && (h.getBlock() <= high)) {
				result.add(new HistoryBean(h.getRepo(), h.getCount(), h.getLatest(), h.getStart(), h.getFinish(),
						h.getCommits(), h.getComplex(), h.getBlock() - low, h.getLanguage()));
			}
		}
		return result;
	}

	public List<HistoryBean> getAllLearningList() {
		List<HistoryBean> learningList = new ArrayList<HistoryBean>();
		for (HistoryBean h : history) {
			if (h.getStart() != null) {
				learningList.add(h);
			}
		}
		learningList.sort(new Comparator<HistoryBean>() {
			public int compare(HistoryBean o1, HistoryBean o2) {
				return o1.getBlock() - o2.getBlock();
			}
		});
		return learningList;
	}

	public List<HistoryBean> getAllHistory() {
		return history;
	}
}