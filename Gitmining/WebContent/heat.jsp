<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<jsp:include page="head.jsp" flush="true" />
<title>Correlation</title>
<link href='/Gitmining/css/repoInfo.css' rel='stylesheet' type='text/css'>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="sidebar.jsp" flush="true" />
		<div class="main-panel">
			<jsp:include page="topbar.jsp" flush="true" />

			<div class="content">
				<div class="row" style="margin-top: 2%;">
					<div class="col-md-6" style="position: relative; margin: 0 -1% 0 1%;">
						<div class="card">
							<h5>
								<a style="color: black; cursor: default">Initial user's
									correlation of <br><span id="firstH"></span></a>
							</h5>
							<p id="description" class="category">Each pixel stands for
								some users.The shade of each pixel shows the level of users'
								interest in this repository.</p>
							<div class="content">
								<div id="first" class="ct-chart"></div>
							</div>
						</div>
					</div>
					<div class="col-md-6 " style="position: relative; margin: 0 1% 0 -1%;">
						<div class="card">
							<h5>
								<a style="color: black; cursor: default">Initial user's
									correlation of <br><span id="thirdH"></span></a>
							</h5>
							<p id="description" class="category">Each pixel stands for
								some users. The shade of each pixel shows the level of users'
								interest in this repository.</p>
							<div class="content">
								<div class="ct-chart" id="third"></div>
							</div>
						</div>
					</div>
				</div>
				<button id="next" class="btn btn-default" style="position: relative; left: 90%">Next</button>
				<div class="row">
					<div class="col-md-6" style="position: relative; margin: 0 -1% 0 1%;"
						id="secondD">
						<div class="card">
							<h5>
								<a style="color: black; cursor: default">Intimate Analysis
									correlation of <br><span id="secondH"></span></a>
							</h5>
							<p id="description" class="category">After intimate analysis,
								predict the level of interest of those unrelated to this
								repository.</p>
							<div class="content">
								<div class="ct-chart" id="second"></div>
							</div>
						</div>
					</div>
					<div class="col-md-6" id="forthD"
						style="position: relative; margin: 0 1% 0 -1%;">
						<div class="card">
							<h5>
								<a style="color: black; cursor: default">Intimate Analysis
									correlation of <br><span id="forthH"></span></a>
							</h5>
							<p id="description" class="category">After intimate analysis,
								predict the level of interest of those unrelated to this
								repository.</p>
							<div class="content">
								<div class="ct-chart" id="forth"></div>
							</div>
						</div>
					</div>
				</div>
				<button id="result" class="btn btn-default" style="position: relative; left: 90%">Next</button>
				<div class="row">
					<div class="col-md-6" id="finalCol" style="position: relative; margin: 0 -1% 0 1%;">
						<div class="card">
							<h5 id="finalH"></h5>
							<p id="description" class="category">Multiply this levels of
								corresponding pixels of both pictures, which means the larger the
								resultant level is, the more common interest users represented by
								this pixel show in these repositories. Once many users are interested
								in both repositories, chances are that these repositories have much
								in common.</p>
							<div class="content">
								<div class="ct-chart" id="final"></div>
							</div>
						</div>
					</div>
					<div class="col-md-1" style="position: relative; margin: 0 1% 0 -1%;">
						<button id="forward" class="btn btn-default" style="position: relative; margin-top: 27em;">Next</button>
						<button id="return" class="btn btn-default" style="position: relative; margin-top: 15%;">Back</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%
	String name = request.getParameter("name");
	String recommend = request.getParameter("recommend");
%>
<script>
var coreName='<%=name%>';
var recommendName = '<%=recommend%>';
</script>
<script src="/Gitmining/js/heat.js" type="text/javascript"></script>
</html>