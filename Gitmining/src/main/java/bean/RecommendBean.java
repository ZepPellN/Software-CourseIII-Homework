package bean;

public class RecommendBean {
	/**
	 * recommended repository
	 */
	private String reco_name;
	/**
	 * repository based on which the repository above is recommended
	 */
	private String base_name;
	/**
	 * the count of strong related users between these repositories
	 */
	private int relate_count;
	
	public RecommendBean(String reco_name, String base_name, int relate_count) {
		this.reco_name = reco_name;
		this.base_name = base_name;
		this.relate_count = relate_count;
	}
	
	public RecommendBean() {}
	
	public String getReco_name() {
		return reco_name;
	}
	public void setReco_name(String reco_name) {
		this.reco_name = reco_name;
	}
	public String getBase_name() {
		return base_name;
	}
	public void setBase_name(String base_name) {
		this.base_name = base_name;
	}
	public int getRelate_count() {
		return relate_count;
	}
	public void setRelate_count(int relate_count) {
		this.relate_count = relate_count;
	}
}