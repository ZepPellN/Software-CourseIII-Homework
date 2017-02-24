package action;

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
import bean.HistoryBean;
import bean.LogBean;
import bean.RepoListBean;
import bl.BlFactory;
import bl.logbl.LogBL;
import utility.Language;

@ParentPackage("action")
@Namespace("/log")
@Results({ @Result(name = "Input", location = "/error.jsp") })
public class LogAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private LogBL logBL = BlFactory.createLoginBLInstance();
	private List<RepoListBean> repoList;
	private List<HistoryBean> history;
	private List<LogBean> logs;
	private LogBean log;
	private String result;

	@Action(value = "login", results = { @Result(name = "success", type = "json", params = { "root", "result" }) })
	public String login() {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String[] results = logBL.login(login, password);
		result = results[0];
		if(!result.equals("failure")){
			ActionContext.getContext().getSession().put("login", results[1]);
			if(result.equals("new")){
				ActionContext.getContext().getSession().put("isNew", "T");
			}else{
				ActionContext.getContext().getSession().put("isNew", "F");
			}
		}
		return SUCCESS;
	}

	@Action(value = "logout", results = { @Result(name = "success", type = "json", params = { "root", "result" }) })
	public String logout() {
		ActionContext.getContext().getSession().remove("login");
		return SUCCESS;
	}
	
	@Action(value = "show", results = { @Result(name = "success", type = "json", params = { "root", "log" }) })
	public String show() {		
		log = logBL.show(ActionContext.getContext().getSession().get("login"));
		return SUCCESS;
	}

	@Action(value = "delete", results = { @Result(name = "success", type = "json", params = { "root", "result" }) })
	public String delete() {
		String input = request.getParameter("full_name");
		input = input.replaceAll("\\\\", "");
		logBL.deleteHistory(input, ActionContext.getContext().getSession().get("login"));
		result = "success";
		return SUCCESS;
	}

	@Action(value = "deleteAll", results = { @Result(name = "success", type = "json", params = { "root", "result" }) })
	public String deleteAll() {
		logBL.clearHistory(ActionContext.getContext().getSession().get("login"));
		result = "success";
		return SUCCESS;
	}

	@Action(value = "history", results = { @Result(name = "success", type = "json", params = { "root", "history" }) })
	public String queryHistory() {
		history = logBL.queryHistory(ActionContext.getContext().getSession().get("login"));
		return SUCCESS;
	}

	@Action(value = "learning", results = { @Result(name = "success", type = "json", params = { "root", "history" }) })
	public String queryLearningList() {
		String input = request.getParameter("level");
		history = logBL.queryLearningList(Integer.parseInt(input), ActionContext.getContext().getSession().get("login"));
		return SUCCESS;
	}

	@Action(value = "recommend", results = {
			@Result(name = "success", type = "json", params = { "root", "repoList" }) })
	public String recommendRepo() {
		repoList = logBL.queryRecommendRepos(ActionContext.getContext().getSession().get("login"));
		return SUCCESS;
	}

	@Action(value = "rank", results = { @Result(name = "success", type = "json", params = { "root", "logs" }) })
	public String queryLogRank() {
		logs = logBL.queryLogRank();
		return SUCCESS;
	}

	@Action(value = "set", results = {
			@Result(name = "success", type = "json", params = { "root", "repoList" }) })
	public String setInfo() {
		int level = Integer.parseInt(request.getParameter("level"));
		Language language = Language.setType(request.getParameter("language"));
		repoList = logBL.setInfo(level, language, ActionContext.getContext().getSession().get("login"));
		ActionContext.getContext().getSession().put("isNew", "F");
		return SUCCESS;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public List<RepoListBean> getRepoList() {
		return repoList;
	}

	public LogBean getLog() {
		return log;
	}

	public List<LogBean> getLogs() {
		return logs;
	}

	public List<HistoryBean> getHistory() {
		return history;
	}

	public String getResult() {
		return result;
	}
}