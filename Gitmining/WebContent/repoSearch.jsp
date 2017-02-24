<!doctype html>
<html lang="en">
<head>
<jsp:include page="head.jsp"></jsp:include>
<title>Repository Search</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="sidebar.jsp" flush="true" />

		<div class="main-panel">
			<jsp:include page="topbar.jsp" flush="true" />

			<div class="content"
				style="padding: 0 15px 30px 15px; margin-top: 80px">
				<nav class="navbar navbar-default" style="border-bottom: 0;">
					<div class="container-fluid">

						<div>
							<ul class="nav navbar-nav navbar-transparent">
								<li>
									<h4 id="repo-num" class="title">We have found 0 repository</h4>
								</li>
							</ul>

							<ul class="nav navbar-nav navbar-right">
								<li><a href="javascript:gotopage('-1');"> <i
										class="ti-angle-left"></i>
								</a></li>
								<li id="pages" style="padding-top: 20px">0/0</li>
								<li><a href="javascript:gotopage('1');"> <i
										class="ti-angle-right"></i>
								</a></li>
							</ul>
						</div>
					</div>
				</nav>
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="content table-responsive table-full-width">
									<table class="table table-hover">
										<thead style="font-size: 18px; font-weight: bold;">
											<tr>
												<th>Language</th>
												<th><a style="color: #1C1B1A"
													href="javascript:sort('name');">Name</a></th>
												<th></th>
												<th><a style="color: #1C1B1A"
													href="javascript:sort('stars');">Stargazers</a></th>
												<th><a style="color: #1C1B1A"
													href="javascript:sort('forks');">Forks</a></th>
												<th><a style="color: #1C1B1A"
													href="javascript:sort('update');">Last Update</a></th>
											</tr>
										</thead>
										<tbody id="repo-list" style="font-size: 18px;">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="/Gitmining/js/repoSearch.js"></script>
<%
String repoName = "";
if(request.getParameter("repo_key") != null){
	repoName = request.getParameter("repo_key");
}
String cateName = "All";
if(request.getParameter("category") != null){
	cateName = request.getParameter("category");
}
String langName = "All";
if(request.getParameter("language") != null){
	langName = request.getParameter("language");
}
String yearName = "All";
if(request.getParameter("year") != null){
	yearName = request.getParameter("year");
}
%>
<script>
	$(document).ready(
		function() {
			searchVague('<%=repoName%>','<%=langName%>','<%=cateName%>','<%=yearName%>');
		})
</script>
</html>