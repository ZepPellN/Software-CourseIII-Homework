<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<jsp:include page="head.jsp" flush="true" />
<title>Repository Home</title>
<link href='/Gitmining/css/repoInfo.css' rel='stylesheet' type='text/css'>
</head>
<body>	
	<div class="wrapper">
		<jsp:include page="sidebar.jsp" flush="true" />
		<div class="main-panel">
			<jsp:include page="topbar.jsp" flush="true" />

			<div class="header" style="position:relative;margin: 7% 0% -4% 0.5%;">
				<div class="card">
					<h3 class="title" style="margin-top: 1%;">
						<a id="ownername" href=""></a><a id="reponame"></a>
						<button id="study" class="btn btn-default" style="position:relative;float:right"></button>						
					</h3>
					<br>
					<button id="abort" class="btn bt-default" style="position:relative;float:right"></button>
					
					<h5 id="mainlanguage" class="title"></h5><h5 id="complexity" class="title" style="margin-top: 1%;"></h5>
					<p id="description" class="category"></p>
				</div>
			</div>
			<div class="content">
				<div class="col-md-6">
					<div class="card">
						<div class="row" style="margin: 0px">
							<h5>Repository Statistic</h5>
							<div class="col-md-4">
								<h5 id="star" style=" text-align: center;"></h5>
								<h5 style=" text-align: center;"><small>stargazers</small></h5>
							</div>
							<div class="col-md-4">
								<h5 id="fork"style=" text-align: center;"></h5>
								<h5 style=" text-align: center;"><small>forks</small></h5>
							</div>
							<div class="col-md-4">
								<h5 id="contributor" style=" text-align: center;"></h5>
								<h5 style=" text-align: center;"><small>contributors</small></h5>
							</div>
							<div class="col-md-4">
								<h5 id="issue"style=" text-align: center;"></h5>
									<h5 style=" text-align: center;"><small>open_issues</small></h5>
							</div>
							<div class="col-md-4">
								<h5 id="subscriber"style=" text-align: center;"></h5>
									<h5 style=" text-align: center;"><small>subscribers</small></h5>
							</div>
							<div class="col-md-4">
								<h5 id="watcher"style=" text-align: center;"></h5>
									<h5 style=" text-align: center;"><small>watchers</small></h5>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="row" style="margin: 0px">
							<h5>Repository Timeline</h5>
							<div class="col-md-4">
								<h5 id="created"style=" text-align: center;"></h5>
									<h5 style=" text-align: center;"><small>created</small></h5>
							</div>
							<div class="col-md-4">
								<h5 id="lastPushed"style=" text-align: center;"></h5>
									<h5 style=" text-align: center;"><small>lastPushed</small></h5>
							</div>
							<div class="col-md-4">
								<h5 id="lastUpdated"style=" text-align: center;"></h5>
									<h5 style=" text-align: center;"><small>lastUpdated</small></h5>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6" >
					<div class="card">
						<h5>Repository Scores</h5>
						<div class="content">
							<div id="reposcore" class="ct-chart"style="position:relative;height:310px"></div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="padding: 0% 0.5%;">
					<div class="card">
						<div class="content">
							<div id="repoactivity" class="ct-chart"></div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="padding: 0% 0.5%; margin-top: -1%">
					<div class="card">
					<div class="header">
                        <h5>Recommend Repositories</h5>
						</div>
						<div id="carousel-example-generic" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#carousel-example-generic" data-slide-to="0"
									class="active"></li>
								<li data-target="#carousel-example-generic" data-slide-to="1"></li>
								<li data-target="#carousel-example-generic" data-slide-to="2"></li>
								<li data-target="#carousel-example-generic" data-slide-to="3"></li>
								<li data-target="#carousel-example-generic" data-slide-to="4"></li>
							</ol>
							<div class="carousel-inner" role="listbox">
								<div class="item active">
									<div class="row">
										<div class="col-xs-12 col-sm-6 col-sm-6 col-md-6">
											<div class="thumbnail">
												<img id="first" src="/Gitmining/images/recommend/recommend1.jpg" alt="...">
												<div class="new-course-mask">
													<h3>
														<a id="firstA"></a>
													</h3>
												</div>
												<div class="caption">
													<a id="firstC"></a>
												</div>
											</div>
										</div>
										<div class="col-xs-12 col-sm-6 col-sm-6 col-md-6">
											<div class="thumbnail">
												<img id="second" src="/Gitmining/images/recommend/recommend2.jpg" alt="...">
												<div class="new-course-mask">
													<h3>
														<a id="secondA"></a>
													</h3>
												</div>
												<div class="caption">
													<a id="secondC"></a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="item">
									<div class="row">
										<div class="col-xs-12 col-sm-6 col-sm-6 col-md-6">
											<div class="thumbnail">
												<img id="third" src="/Gitmining/images/recommend/recommend3.jpg" alt="...">
												<div class="new-course-mask">
													<h3>
														<a id="thirdA"></a>
													</h3>
												</div>
												<div class="caption">
													<a id="thirdC"></a>
												</div>
											</div>
										</div>
										<div class="col-xs-12 col-sm-6 col-sm-6 col-md-6">
											<div class="thumbnail">
												<img id="forth" src="/Gitmining/images/recommend/recommend4.jpg"
													alt="...">
												<div class="new-course-mask">
													<h3>
														<a id="forthA"></a>
													</h3>
												</div>
												<div class="caption">
													<a id="forthC"></a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="item">
									<div class="row">
										<div class="col-xs-12 col-sm-6 col-sm-6 col-md-6">
											<div class="thumbnail">
												<img id="fifth" src="/Gitmining/images/recommend/recommend5.jpg" alt="...">
												<div class="new-course-mask">
													<h3>
														<a id="fifthA"></a>
													</h3>
												</div>
												<div class="caption">
													<a id="fifthC"></a>
												</div>
											</div>
										</div>
										<div class="col-xs-12 col-sm-6 col-sm-6 col-md-6">
											<div class="thumbnail">
												<img id="sixth" src="/Gitmining/images/recommend/recommend6.jpg" alt="...">
												<div class="new-course-mask">
													<h3>
														<a id="sixthA"></a>
													</h3>
												</div>
												<div class="caption">
													<a id="sixthC"></a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="item">
									<div class="row">
										<div class="col-xs-12 col-sm-6 col-sm-6 col-md-6">
											<div class="thumbnail">
												<img id="seven" src="/Gitmining/images/recommend/recommend7.jpg" alt="...">
												<div class="new-course-mask">
													<h3>
														<a id="sevenA"></a>
													</h3>
												</div>
												<div class="caption">
													<a id="sevenC"></a>
												</div>
											</div>
										</div>
										<div class="col-xs-12 col-sm-6 col-sm-6 col-md-6">
											<div class="thumbnail">
												<img id="eight" src="/Gitmining/images/recommend/recommend8.jpg" alt="...">
												<div class="new-course-mask">
													<h3>
														<a id="eightA"></a>
													</h3>
												</div>
												<div class="caption">
													<a id="eightC"></a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="item">
									<div class="row">
										<div class="col-xs-12 col-sm-6 col-sm-6 col-md-6">
											<div class="thumbnail">
												<img id="nine" src="/Gitmining/images/recommend/recommend9.jpg" alt="...">
												<div class="new-course-mask">
													<h3>
														<a id="nineA"></a>
													</h3>
												</div>
												<div class="caption">
													<a id="nineC"></a>
												</div>
											</div>
										</div>
										<div class="col-xs-12 col-sm-6 col-sm-6 col-md-6">
											<div class="thumbnail">
												<img id="ten" src="/Gitmining/images/recommend/recommend10.jpg" alt="...">
												<div class="new-course-mask">
													<h3>
														<a id="tenA"></a>
													</h3>
												</div>
												<div class="caption">
													<a id="tenC"></a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%
String repoName = request.getParameter("reponame");  
%>
<script>
var repositoryName='<%=repoName%>';
</script>
<script type="text/javascript" src="/Gitmining/js/repository.js"></script>
</html>