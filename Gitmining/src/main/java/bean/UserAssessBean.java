package bean;

public class UserAssessBean {
	/*according to the number of followers*/
	private double popularity;
	/*according to the number of following, gists and repos*/
	private double enthusiasm;
	/*according to the density of commits in the recent year*/
	private double activity;
	
	public UserAssessBean(double popularity, double enthusiasm, double activity) {
		this.popularity = popularity;
		this.enthusiasm = enthusiasm;
		this.activity = activity;
	}
	
	public UserAssessBean() {}

	public double getPopularity() {
		return popularity;
	}
	public double getEnthusiasm() {
		return enthusiasm;
	}
	public double getActivity() {
		return activity;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public void setEnthusiasm(double enthusiasm) {
		this.enthusiasm = enthusiasm;
	}

	public void setActivity(double activity) {
		this.activity = activity;
	}
}