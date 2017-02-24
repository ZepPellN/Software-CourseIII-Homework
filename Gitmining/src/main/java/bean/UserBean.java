package bean;

import java.util.ArrayList;
import utility.OwnerType;

public class UserBean {
	private String id;

	private String login;

	private String name;

	private String company;

	private String location;

	private String email;

	private String blog;

	private String bio;

	private int public_repos = -1;

	private int public_gists = -1;

	private int followers = -1;

	private int following = -1;
	
	private String created_at;

	private String updated_at;

	private boolean site_admin = false;

	private boolean hireable = false;

	private OwnerType type;
		
	private ArrayList<String> reposName;
	
	private ArrayList<String> reposName_HOT;
			
	private String avatar_url;

	public UserBean() {}

	public UserBean(String id, String login, String name, String company, String location, String email, String blog,
			String bio, int public_repos, int public_gists, int followers, int following, String created_at,
			String updated_at, boolean site_admin, boolean hireable, OwnerType type, String avatar) {
		this.id = id;
		this.login = login;
		this.name = name;
		this.company = company;
		this.location = location;
		this.email = email;
		this.blog = blog;
		this.bio = bio;
		this.public_repos = public_repos;
		this.public_gists = public_gists;
		this.followers = followers;
		this.following = following;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.site_admin = site_admin;
		this.hireable = hireable;
		this.type = type;
		this.avatar_url = avatar;
	}

	public String getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}

	public String getCompany() {
		return company;
	}

	public String getLocation() {
		return location;
	}

	public String getEmail() {
		return email;
	}

	public String getBlog() {
		return blog;
	}

	public String getBio() {
		return bio;
	}

	public int getPublic_repos() {
		return public_repos;
	}

	public int getPublic_gists() {
		return public_gists;
	}

	public int getFollowers() {
		return followers;
	}

	public int getFollowing() {
		return following;
	}

	public ArrayList<String> getReposName() {
		return reposName;
	}

	public String getCreated_at() {
		return created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public boolean isSite_admin() {
		return site_admin;
	}

	public boolean isHireable() {
		return hireable;
	}

	public OwnerType getType() {
		return type;
	}
		
	public ArrayList<String> getReposName_HOT() {
		return reposName_HOT;
	}

	public void setReposName(ArrayList<String> reposName) {
		this.reposName = reposName;
	}

	public void setReposName_HOT(ArrayList<String> reposName_HOT) {
		this.reposName_HOT = reposName_HOT;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setPublic_repos(int public_repos) {
		this.public_repos = public_repos;
	}

	public void setPublic_gists(int public_gists) {
		this.public_gists = public_gists;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public void setFollowing(int following) {
		this.following = following;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public void setSite_admin(boolean site_admin) {
		this.site_admin = site_admin;
	}

	public void setHireable(boolean hireable) {
		this.hireable = hireable;
	}

	public void setType(OwnerType type) {
		this.type = type;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
}