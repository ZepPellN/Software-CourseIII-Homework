package bean;

import java.util.ArrayList;
import java.util.List;

import utility.CategoryOfRepo;
import utility.Language;

public class RepoBean {
	
	private String id;	
	
	private String full_name;
	
	private String owner_name;
	
	private String name;
	
	private String default_branch;

	private String homepage;

	private String description; 

	private String created_at; 

	private String updated_at; 

	private String pushed_at;

	private int size;

	private int stargazers_count; 

	private int subscribers_count;
		
	private int contributors_count;
	
	private int open_issues_count;
	
	private int watchers_count;
	
	private int forks_count;

	private Language language;
	
	private boolean fork;
	
	private boolean has_issues;
	
	private boolean privacy;

	private List<RecommendBean> recommendRepos;
	
	private ArrayList<CategoryOfRepo> categories;
	//0 for visitor, 1 for not learning, 2 for being learning, 3 for finished.
	private int state;
	
	private int complex;
	
	public RepoBean(){
		language = Language.Others;
		contributors_count = -1;
		open_issues_count = -1;
		watchers_count = -1;
		forks_count = -1;
	};
	
	public RepoBean(String id, String full_name, String owner_name, String name, String category, String default_branch, String homepage,
			String description, String created_at, String updated_at, String pushed_at, int size, int stargazers_count,
			int subscribers_count, int contributors_count, int open_issues_count, int watchers_count, int forks_count,
			Language language, boolean fork, boolean has_issues, boolean privacy, int state, int complex) {
		this.id = id;
		this.full_name = full_name;
		this.owner_name = owner_name;
		this.name = name;
		this.default_branch = default_branch;
		this.homepage = homepage;
		this.description = description;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.pushed_at = pushed_at;
		this.size = size;
		this.stargazers_count = stargazers_count;
		this.subscribers_count = subscribers_count;
		this.contributors_count = contributors_count;
		this.open_issues_count = open_issues_count;
		this.watchers_count = watchers_count;
		this.forks_count = forks_count;
		this.language = language;
		this.fork = fork;
		this.has_issues = has_issues;
		this.privacy = privacy;
		this.categories = new ArrayList<CategoryOfRepo>();
		for (String category_str : category.split(",")) {
			this.categories.add(CategoryOfRepo.setType(category_str));
		}
		this.state = state;
		this.complex = complex;
	}
	//======================================get=========================================
	public String getId() {
		return id;
	}

	public String getFull_name() {
		return full_name;
	}
	
	public String getOwner_name() {
		return owner_name;
	}
	
	public String getName() {
		return name;
	}

	public String getDefault_branch() {
		return default_branch;
	}

	public String getDescription() {
		return description;
	}

	public String getCreated_at() {
		return created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public String getPushed_at() {
		return pushed_at;
	}

	public int getSize() {
		return size;
	}

	public int getStargazers_count() {
		return stargazers_count;
	}

	public int getSubscribers_count() {
		return subscribers_count;
	}

	public int getOpen_issues_count() {
		return open_issues_count;
	}

	public int getWatchers_count() {
		return watchers_count;
	}

	public int getForks_count() {
		return forks_count;
	}

	public Language getLanguage() {
		return language;
	}

	public boolean isFork() {
		return fork;
	}

	public boolean isHas_issues() {
		return has_issues;
	}

	public boolean isPrivacy() {
		return privacy;
	}
	
	public String getHomepage() {
		return homepage;
	}
	
	public int getContributior() {
		return this.contributors_count;
	}

	public ArrayList<CategoryOfRepo> getCategories(){
		return this.categories;
	}
	
	public void setRecommendRepos(List<RecommendBean> recommendRepos) {
		this.recommendRepos = recommendRepos;
	}

	public int getContributors_count() {
		return contributors_count;
	}

	public void setContributors_count(int contributors_count) {
		this.contributors_count = contributors_count;
	}

	public List<RecommendBean> getRecommendRepos() {
		return recommendRepos;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDefault_branch(String default_branch) {
		this.default_branch = default_branch;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public void setPushed_at(String pushed_at) {
		this.pushed_at = pushed_at;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setStargazers_count(int stargazers_count) {
		this.stargazers_count = stargazers_count;
	}

	public void setSubscribers_count(int subscribers_count) {
		this.subscribers_count = subscribers_count;
	}

	public void setOpen_issues_count(int open_issues_count) {
		this.open_issues_count = open_issues_count;
	}

	public void setWatchers_count(int watchers_count) {
		this.watchers_count = watchers_count;
	}

	public void setForks_count(int forks_count) {
		this.forks_count = forks_count;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public void setFork(boolean fork) {
		this.fork = fork;
	}

	public void setHas_issues(boolean has_issues) {
		this.has_issues = has_issues;
	}

	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}

	public void setCategories(ArrayList<CategoryOfRepo> categories) {
		this.categories = categories;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getComplex() {
		return complex;
	}

	public void setComplex(int complex) {
		this.complex = complex;
	}
}