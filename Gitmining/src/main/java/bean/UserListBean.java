package bean;

public class UserListBean {
	private String login;
	private String location;
	private String email;
	private String created_at;
	private String avatar_url;
	private String blog;
	private String bio;
	private int followers;
	private int following;
	
	public UserListBean() {}
	
	public UserListBean(UserBean user) {
		this.login = user.getLogin();
		this.location = user.getLocation();
		this.email = user.getEmail();
		this.created_at = user.getCreated_at();
		this.avatar_url = user.getAvatar_url();
		this.blog = user.getBlog();
		this.bio = user.getBio();
		this.followers = user.getFollowers();
		this.following = user.getFollowing();
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}
}