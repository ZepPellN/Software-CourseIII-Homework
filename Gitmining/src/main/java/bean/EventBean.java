package bean;

public class EventBean {	
	private String repo_name;
	private String created_at;
	private String type;
	
	public EventBean(){};
	
	public EventBean(String full_name, String date, String event){
		created_at = date;
		type = event;
		repo_name = full_name;
	}
	
	public String getRepo_name() {
		return repo_name;
	}

	public void setRepo_name(String repo_name) {
		this.repo_name = repo_name;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreatedAt(){
		return created_at;
	}
	
	public String getEvent(){
		return type;
	}
	
	public String getRepoName(){
		return repo_name;
	}
}