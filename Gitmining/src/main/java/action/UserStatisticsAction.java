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
import bean.UserAssessBean;
import bl.BlFactory;
import bl.statisticsbl.UserStatisticsBL;
import utility.OwnerType;

@ParentPackage("action")
@Namespace("/userstatics")
@Results({ @Result(name = "Input", location = "/error.jsp") })
public class UserStatisticsAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private UserStatisticsBL userStatisticsBL = BlFactory.createUserStatisticsBLInstance();
	private Map<String, Integer> map_str_int;
	private Map<OwnerType, Integer> map_own_int;
	private Map<Integer, Integer> map_int_int;
	private UserAssessBean userAssess;

	@Action(value = "assess", results = { @Result(name = "success", type = "json", params = { "root", "userAssess" }) })
	public String getUserAssessment() {
		String input = request.getParameter("login");
		userAssess = userStatisticsBL.getScore(input);
		return SUCCESS;
	}

	@Action(value = "activities", results = {
			@Result(name = "success", type = "json", params = { "root", "map_str_int" }) })
	public String getUserActivity() {
		String input = request.getParameter("login");
		map_str_int = userStatisticsBL.getUserActivity(input);
		return SUCCESS;
	}

	@Action(value = "ownertypes", results = {
			@Result(name = "success", type = "json", params = { "root", "map_own_int" }) })
	public String getOwnerType() {
		map_own_int = userStatisticsBL.getOwnerType();
		return SUCCESS;
	}

	@Action(value = "created_ats", results = {
			@Result(name = "success", type = "json", params = { "root", "map_int_int" }) })
	public String getCreated_at() {
		map_int_int = userStatisticsBL.getCreated_at();
		return SUCCESS;
	}

	@Action(value = "repositories", results = {
			@Result(name = "success", type = "json", params = { "root", "map_int_int" }) })
	public String getRepository() {
		String input = request.getParameter("interval");
		int interval = 10;
		if (input != null) {
			interval = Integer.parseInt(input);
		}
		map_int_int = userStatisticsBL.getRepository(interval);
		return SUCCESS;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Map<String, Integer> getMap_str_int() {
		return map_str_int;
	}

	public Map<OwnerType, Integer> getMap_own_int() {
		return map_own_int;
	}

	public Map<Integer, Integer> getMap_int_int() {
		return map_int_int;
	}

	public UserAssessBean getUserAssess() {
		return userAssess;
	}
}