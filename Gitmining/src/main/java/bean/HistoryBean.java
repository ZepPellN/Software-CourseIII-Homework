package bean;

import utility.Language;

public class HistoryBean {
	private String repo;
	private int count;
	private String latest;
	private String start;
	private String finish;
	private int commits;
	private int complex;
	private int block;
	private Language language;
	
	public HistoryBean(String repo, int count, String latest, String start, String finish, int commits,
			int complex, int block, Language language) {
		this.repo = repo;
		this.count = count;
		this.latest = latest;
		this.start = start;
		this.finish = finish;
		this.commits = commits;
		this.complex = complex;
		this.block = block;
		this.language = language;
	}

	public HistoryBean() {}

	public String getRepo() {
		return repo;
	}

	public void setRepo(String repo) {
		this.repo = repo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getLatest() {
		return latest;
	}

	public void setLatest(String latest) {
		this.latest = latest;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public int getCommits() {
		return commits;
	}

	public void setCommits(int commits) {
		this.commits = commits;
	}

	public int getComplex() {
		return complex;
	}

	public void setComplex(int complex) {
		this.complex = complex;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
}