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
import bean.UserBean;
import bean.UserListBean;
import bl.BlFactory;
import bl.userbl.UserBL;

@ParentPackage("action")
@Namespace("/user")
@Results({ @Result(name = "Input", location = "/error.jsp") })
public class UserAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private UserBL userBL = BlFactory.createUserBLInstance();
	private UserBean user;
	private List<UserListBean> namelist;

	@Action(value = "search", results = { @Result(name = "success", type = "json", params = { "root", "namelist" }) })
	public String searchVague() {
		String input = request.getParameter("user_key");
		namelist = userBL.searchVague(input);
		ActionContext.getContext().getSession().put("user_key", input);
		return SUCCESS;
	}

	@Action(value = "show", results = { @Result(name = "success", type = "json", params = { "root", "user" }) })
	public String searchPrecise() {
		user = userBL.searchPrecise(request.getParameter("login"));
		return SUCCESS;
	}

	public UserBean getUser() {
		return user;
	}

	public List<UserListBean> getNamelist() {
		return namelist;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}