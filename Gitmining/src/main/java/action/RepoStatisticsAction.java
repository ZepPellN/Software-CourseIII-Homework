package action;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import bean.RepoRadarBean;
import bl.BlFactory;
import bl.statisticsbl.RepoStatisticsBL;
import utility.Language;

@ParentPackage("action")
@Namespace("/repostatics")
@Results({ @Result(name = "Input", location = "/error.jsp") })
public class RepoStatisticsAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private RepoStatisticsBL repoStatisticsBL = BlFactory.createRepoStatisticsBLInstance();
	private RepoRadarBean repoRadar;
	private Map<String, Integer> map_str_int;
	private Map<Integer, Integer> map_int_int;
	private Map<Language, Integer> map_lan_int;

	@Action(value = "radar", results = { @Result(name = "success", type = "json", params = { "root", "repoRadar" }) })
	public String getRadar() {
		String input = request.getParameter("full_name");
		input = input.replaceAll("\\\\", "");
		repoRadar = repoStatisticsBL.getRadar(input);
		return SUCCESS;
	}

	@Action(value = "activities", results = {
			@Result(name = "success", type = "json", params = { "root", "map_str_int" }) })
	public String getRepoActivity() {
		String input = request.getParameter("full_name");
		input = input.replaceAll("\\\\", "");
		map_str_int = repoStatisticsBL.getRepoActivity(input);
		return SUCCESS;
	}

	@Action(value = "languages", results = {
			@Result(name = "success", type = "json", params = { "root", "map_lan_int" }) })
	public String getLanguages() {
		map_lan_int = repoStatisticsBL.getLanguage();
		return SUCCESS;
	}

	@Action(value = "create_ats", results = {
			@Result(name = "success", type = "json", params = { "root", "map_int_int" }) })
	public String getCreateTime() {
		map_int_int = repoStatisticsBL.getCreateTime();
		return SUCCESS;
	}

	@Action(value = "forks", results = { @Result(name = "success", type = "json", params = { "root", "map_int_int" }) })
	public String getRepoFork() {
		String input = request.getParameter("interval");
		int interval = 5;
		if (input != null) {
			interval = Integer.parseInt(input);
		}
		map_int_int = repoStatisticsBL.getRepoFork(interval);
		return SUCCESS;
	}

	@Action(value = "stars", results = { @Result(name = "success", type = "json", params = { "root", "map_int_int" }) })
	public String getRepoStar() {
		String input = request.getParameter("interval");
		int interval = 10;
		if (input != null) {
			interval = Integer.parseInt(input);
		}
		map_int_int = repoStatisticsBL.getRepoStar(interval);
		return SUCCESS;
	}

	@Action(value = "language", results = {
			@Result(name = "success", type = "json", params = { "root", "map_int_int" }) })
	public String getLanguage() {
		String input = request.getParameter("language");
		map_int_int = repoStatisticsBL.getLanguage(Language.setType(input));
		return SUCCESS;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Map<String, Integer> getMap_str_int() {
		return map_str_int;
	}

	public Map<Integer, Integer> getMap_int_int() {
		return map_int_int;
	}

	public Map<Language, Integer> getMap_lan_int() {
		return map_lan_int;
	}

	public RepoRadarBean getRepoRadar() {
		return repoRadar;
	}
}