package action;

import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import bean.RepoBean;
import bean.RepoListBean;
import bl.BlFactory;
import bl.repobl.RepoBL;
import bl.strategy.SortByFork;
import bl.strategy.SortByLastUpdate;
import bl.strategy.SortByName;
import bl.strategy.SortByStar;
import utility.CategoryOfRepo;
import utility.Language;

@ParentPackage("action")
@Namespace("/repository")
@Results({ @Result(name = "Input", location = "/error.jsp") })
public class RepoAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private RepoBL repoBL = BlFactory.createRepoBLInstance();
	private List<RepoListBean> sortBean;
	private RepoBean repoBean;
	private String[] rows;
	private String result;
	
	@Action(value = "search", results = { @Result(name = "success", type = "json", params = { "root", "sortBean" }), })
	public String searchVague() {
		String keyword = request.getParameter("repo_key");
		
		String year = request.getParameter("year");
		if (year == null) {
			year = "All";
		}
		Language language = Language.setType(request.getParameter("language"));
		CategoryOfRepo category = CategoryOfRepo.setType(request.getParameter("category"));		
		sortBean = repoBL.searchVague(keyword, category, language, year, ActionContext.getContext().getSession().get("login"));
		ActionContext.getContext().getSession().put("repo_key", keyword);
		ActionContext.getContext().getSession().put("language", language.getIndex());
		ActionContext.getContext().getSession().put("category", category.getIndex());
		switch (year) {
		case "2007":
			ActionContext.getContext().getSession().put("year", 4);	break;
		case "2008":
			ActionContext.getContext().getSession().put("year", 3);	break;
		case "2009":
			ActionContext.getContext().getSession().put("year", 2);	break;
		case "2010":
			ActionContext.getContext().getSession().put("year", 1);	break;
		default:
			ActionContext.getContext().getSession().put("year", 0);	break;
		}
		return SUCCESS;
	}

	@Action(value = "show", results = { @Result(name = "success", type = "json", params = { "root", "repoBean" }) })
	public String searchPrecise() {
		String input = request.getParameter("full_name");
		input = input.replaceAll("\\\\", "");
		repoBean = repoBL.searchPrecise(ActionContext.getContext().getSession().get("login"));
		return SUCCESS;
	}
	
	@Action(value = "preshow", results = { @Result(name = "success", type = "json", params = { "root", "result" }) })
	public String preSearchPrecise() {
		String input = request.getParameter("full_name");
		input = input.replaceAll("\\\\", "");
		if(repoBL.checkAndUpdate(input, ActionContext.getContext().getSession().get("login"))){
			result = "success";
		}else{
			result = "fail";
		}
		return SUCCESS;
	}

	@Action(value = "sort", results = { @Result(name = "success", type = "json", params = { "root", "sortBean" }) })
	public String sort() {
		String input = request.getParameter("type");
		Comparator<RepoListBean> behavior;
		switch (input) {
		case "stars":
			behavior = new SortByStar();
			break;
		case "forks":
			behavior = new SortByFork();
			break;
		case "update":
			behavior = new SortByLastUpdate();
			break;
		default:
			behavior = new SortByName();
			break;
		}
		sortBean = repoBL.sort(behavior);
		return SUCCESS;
	}

	@Action(value = "recommends", results = { @Result(name = "success", type = "json", params = { "root", "rows" }) })
	public String recommends(){
		String repo1 = request.getParameter("first_name").replaceAll("\\\\", "");
		String repo2 = request.getParameter("second_name").replaceAll("\\\\", "");
		rows = repoBL.queryRecommendLine(repo1, repo2);
		return SUCCESS;
	}
		
	@Action(value = "start", results = { @Result(name = "success", type = "json", params = { "root", "result" }) })
	public String startLearning(){
		String repo = request.getParameter("repo_name").replaceAll("\\\\", "");
		Language language = Language.setType(request.getParameter("language"));
		if(repoBL.startLearning(repo, language, ActionContext.getContext().getSession().get("login"))){
			result = SUCCESS;
		}else{
			result = ERROR;
		}
		return SUCCESS;
	}
	
	@Action(value = "stop", results = { @Result(name = "success", type = "json", params = { "root", "result" }) })
	public String stopLearning(){
		String repo = request.getParameter("repo_name").replaceAll("\\\\", "");
		if(repoBL.stopLearning(repo, ActionContext.getContext().getSession().get("login"))){
			result = SUCCESS;
		}else{
			result = ERROR;
		}
		return SUCCESS;
	}
	
	@Action(value = "finish", results = { @Result(name = "success", type = "json", params = { "root", "result" }) })
	public String finishLearning(){
		String repo = request.getParameter("repo_name").replaceAll("\\\\", "");
		if(repoBL.finishLearning(repo, ActionContext.getContext().getSession().get("login"))){
			result = "level up";
		}else{
			result = "success";
		}
		return SUCCESS;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<RepoListBean> getSortBean() {
		return sortBean;
	}

	public RepoBean getRepoBean() {
		return repoBean;
	}
	
	public String[] getRows() {
		return rows;
	}

	public String getResult() {
		return result;
	}
}