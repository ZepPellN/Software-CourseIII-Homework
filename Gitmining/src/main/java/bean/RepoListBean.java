package bean;

import utility.Language;

public class RepoListBean {
	private String name;
	private int stars;
	private int forks;
	private Language language;
	private String last_update;
	private String homepage;
	private String description;
	
	public RepoListBean(){};
	
	public RepoListBean(RepoBean repo){
		this.name = repo.getFull_name();
		this.stars = repo.getStargazers_count();
		this.forks = repo.getForks_count();
		this.last_update = repo.getUpdated_at();
		this.homepage = repo.getHomepage();
		this.description = repo.getDescription();
		this.language = repo.getLanguage();
	}

	public String getName() {
		return name;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public int getForks() {
		return forks;
	}

	public void setForks(int forks) {
		this.forks = forks;
	}

	public String getLast_update() {
		return last_update;
	}

	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}