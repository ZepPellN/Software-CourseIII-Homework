<!doctype html>
<html lang="en">
<head>
<style type="text/css">
input:-webkit-autofill {
	-webkit-box-shadow: 0 0 0px 1000px #ffffff inset !important;
}

input.error::-webkit-input-placeholder {
	color: #F9684B;
}

input.error:-moz-placeholder {
	color: #F9684B;
}

input.error::-moz-placeholder {
	color: #F9684B;
}

input.error:-ms-input-placeholder {
	color: #F9684B;
}
</style>
</head>
<body>
	<div class="sidebar" class="" data-background-color="white"
		data-active-color="primary">
		<div class="sidebar-wrapper">
			<div class="logo">
				<a href="/Gitmining/"
					class="simple-text"> InSighter </a>
			</div>

			<ul class="nav">
				<li><a>
						<div class="input-group border-input">
							<span class="input-group-addon ti-user"></span> <input
								id="user_keyword" name="user_keyword" type="text"
								class="form-control border-input" 
								onkeydown='if(event.keyCode==13){if(changeUser()){searchUser();};return false;}'>
						</div>
				</a></li>
				<HR
					style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)"
					width="80%" color=#987cb9 SIZE=5 />
				<li><a>
						<div class="input-group border-input">
							<span class="input-group-addon ti-archive"></span> <input
								id="repo_keyword" name="repo_keyword" type="text"
								class="form-control border-input"
								onkeydown='if(event.keyCode==13){if(changeRepo()){searchRepo();}return false;}'>
						</div>
				</a></li>
				<li style="text-align: center;" class="form-group"><a
					onclick="javascript:OpenLanguage();">
						<p>Language</p>
				</a>
					<ul id="language" class="nav collapse">
						<li><a name="language"
							onclick="changeLanguage(this);">All</a></li>
						<li><a name="language"
							onclick="changeLanguage(this);">C</a></li>
						<li><a name="language"
							onclick="changeLanguage(this);">PHP</a></li>
						<li><a name="language"
							onclick="changeLanguage(this);">HTML</a></li>
						<li><a name="language"
							onclick="changeLanguage(this);">Java</a></li>
						<li><a name="language"
							onclick="changeLanguage(this);">Perl</a></li>
						<li><a name="language"
							onclick="changeLanguage(this);">Ruby</a></li>
						<li><a name="language"
							onclick="changeLanguage(this);">Shell</a></li>
						<li><a name="language"
							onclick="changeLanguage(this);">Python</a></li>
						<li><a name="language"
							onclick="changeLanguage(this);">JavaScript</a></li>
						<li><a name="language"
							onclick="changeLanguage(this);">Others</a></li>
					</ul></li>
				<li style="text-align: center;" class="form-group"><a
					onclick="javascript:OpenCategory();">
						<p>Category</p>
				</a>
					<ul id="category" class="nav collapse">
						<li><a name="category"
							onclick="changeCategory(this);">All</a></li>
						<li><a name="category"
							onclick="changeCategory(this);">OS</a></li>
						<li><a name="category"
							onclick="changeCategory(this);">API</a></li>
						<li><a name="category" 
							onclick="changeCategory(this);">App</a></li>
						<li><a name="category" 
							onclick="changeCategory(this);">Mac</a></li>
						<li><a name="category"
							onclick="changeCategory(this);">Web</a></li>
						<li><a name="category"
							onclick="changeCategory(this);">Json</a></li>
						<li><a name="category"
							onclick="changeCategory(this);">Tool</a></li>
						<li><a name="category"
							onclick="changeCategory(this);">Linux</a></li>
						<li><a name="category"
							onclick="changeCategory(this);">Plugin</a></li>
						<li><a name="category"
							onclick="changeCategory(this);">Server</a></li>
						<li><a name="category"
							onclick="changeCategory(this);">Library</a></li>
						<li><a name="category" 
							onclick="changeCategory(this);">Template</a></li>
						<li><a name="category"
							onclick="changeCategory(this);">Interface</a></li>
						<li><a name="category"
							onclick="changeCategory(this);">Others</a></li>
					</ul></li>
				<li style="text-align: center;" class="form-group"><a
					onclick="javascript:OpenYear();">
						<p>Year</p>
				</a>
					<ul id="year" class="nav collapse">
						<li><a name="year"
							onclick="changeYear(this);">All</a></li>
						<li><a name="year"
							onclick="changeYear(this);">2010</a></li>
						<li><a name="year"
							onclick="changeYear(this);">2009</a></li>
						<li><a name="year"
							onclick="changeYear(this);">2008</a></li>
						<li><a name="year"
							onclick="changeYear(this);">2007</a></li>
					</ul></li>
				<li style="text-align: center;" class="form-group"><a
					onclick="Hide();clearColor();">
						<p>Clear Filter</p>
				</a></li>
				<HR
					style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)"
					width="80%" color=#987cb9 SIZE=5>
				<li><a href="/Gitmining/analysis/user" class="form-group"> <i
						class="ti-bar-chart"></i>
						<p style="padding-left: 65px">User Analysis</p>
				</a></li>
				<li><a href="/Gitmining/analysis/repository" class="form-group"> <i
						class="ti-stats-up"></i>
						<p>Repository Analysis</p>
				</a></li>
			</ul>
		</div>
	</div>
</body>
<jsp:include page="foot.jsp" flush="true" />
<%@page import="utility.CategoryOfRepo"%>
<%@page import="utility.Language"%>
<%
String user_key = "";
String repo_key = "";
int language = 0;
int category = 0;
int year = 0;
String url = ((HttpServletRequest)request).getServletPath();
if(url.equals("/repoSearch.jsp")){
	if(session.getAttribute("user_key") != null)
		user_key = (String)session.getAttribute("user_key");
	if(request.getParameter("repo_key") != null)
		repo_key = request.getParameter("repo_key");
	if(request.getParameter("language") != null)
		language = Language.setType(request.getParameter("language")).getIndex();
	if(request.getParameter("category") != null)
		category = CategoryOfRepo.setType(request.getParameter("category")).getIndex();
	if(request.getParameter("year") != null)
		year = 2011 - Integer.parseInt(request.getParameter("year"));
	
}else if(url.equals("/userSearch.jsp")){
	if(request.getParameter("user_key") != null)
		user_key = request.getParameter("user_key");
	if(session.getAttribute("repo_key") != null)
		repo_key = (String)session.getAttribute("repo_key");
	if(session.getAttribute("language") != null)
		language = (int)session.getAttribute("language");
	if(session.getAttribute("category") != null)
		category = (int)session.getAttribute("category");
	if(session.getAttribute("year") != null)
		year = (int)session.getAttribute("year");
	
}else{
	if(session.getAttribute("user_key") != null)
		user_key = (String)session.getAttribute("user_key");
	if(session.getAttribute("repo_key") != null)
		repo_key = (String)session.getAttribute("repo_key");
	if(session.getAttribute("language") != null)
		language = (int)session.getAttribute("language");
	if(session.getAttribute("category") != null)
		category = (int)session.getAttribute("category");
	if(session.getAttribute("year") != null)
		year = (int)session.getAttribute("year");
}
%>
<script src="/Gitmining/js/sidebar.js"></script>
<script type="text/javascript">	
	$(document).ready(function() {
		initColor();
		repo_keyword.value = "<%=repo_key%>";
		user_keyword.value = "<%=user_key%>";
		repo_keyword.placeholder = "Repository Search";
		user_keyword.placeholder = "User Search";
	});

	function initColor() {
		for (var i = 0; i < lan.length; i++) {
			lan[i].style.backgroundColor = "transparent";
		}
		lan["<%=language%>"].style.backgroundColor = "#F9684B";
		l = lan["<%=language%>"].text;
		for (var i = 0; i < cat.length; i++) {
			cat[i].style.backgroundColor = "transparent";
		}
		cat["<%=category%>"].style.backgroundColor = "#F9684B";
		c = cat["<%=category%>"].text;
		for (var i = 0; i < yea.length; i++) {
			yea[i].style.backgroundColor = "transparent";
		}
		yea["<%=year%>"].style.backgroundColor = "#F9684B";
		y = yea["<%=year%>"].text;
	}
</script>
</html>