package bean;

public class CommitBean {
	/**
	 * commit date
	 */
	private String date;
	
	/**
	 * committer's name
	 */
	private String user_name;
	
	/**
	 * commit message
	 */
	private String message;

	

	public CommitBean() {}

	public CommitBean(String date, String user_name, String message) {
		this.date = date;
		this.user_name = user_name;
		this.message = message;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public String getCommitter() {
		return user_name;
	}

	public String getMessage() {
		return message;
	}
}