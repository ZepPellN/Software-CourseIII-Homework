package bean;

public class RepoRadarBean {
	private double contributor;
	private double size;
	private double star;
	private double fork;
	private double issue;
	private double subscriber;
	
	public RepoRadarBean() {}

	public RepoRadarBean(double contributor, double size, double star, double fork, double issue, double subscribers) {
		this.contributor = contributor;
		this.size = size;
		this.star = star;
		this.fork = fork;
		this.issue = issue;
		this.subscriber = subscribers;
	}

	public double getContributor() {
		return contributor;
	}

	public double getSize() {
		return size;
	}

	public double getStar() {
		return star;
	}

	public double getFork() {
		return fork;
	}

	public double getIssue() {
		return issue;
	}

	public double getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(double subscriber) {
		this.subscriber = subscriber;
	}

	public void setContributor(double contributor) {
		this.contributor = contributor;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public void setStar(double star) {
		this.star = star;
	}

	public void setFork(double fork) {
		this.fork = fork;
	}

	public void setIssue(double issue) {
		this.issue = issue;
	}
}