<!doctype html>
<html lang="en">
<head>
	<link href="/Gitmining/assets/css/manhua_hoverTips.css" rel="stylesheet" />
    <title>User Search</title>
    <jsp:include page="head.jsp"></jsp:include>
</head>
<body>
<div class="wrapper">
    <jsp:include page="sidebar.jsp" flush="true" />

    <div class="main-panel">
        <jsp:include page="topbar.jsp" flush="true" />
		<div class="content" id="fatherNode" style="padding: 0 15px 30px 15px; margin-top:80px">
			<nav class="navbar navbar-default" style="border-bottom: 0; margin-top: -3%;">
				<div class="container-fluid">
					<div class="collapse navbar-collapse">
						<ul class="nav navbar-nav">
							<li>
								<h4 id="user-num" class="title">We have found 0
									users</h4>
							</li>
						</ul>

						<ul class="nav navbar-nav navbar-right">
							<li><a href="javascript:gotopage_user('-1');"> <i
									class="ti-angle-left"></i>
							</a></li>
							<li id="pages" style="padding-top: 20px">0/0</li>
							<li><a href="javascript:gotopage_user('1');"> <i
									class="ti-angle-right"></i>
							</a></li>
						</ul>
					</div>
				</div>
			</nav>
			
			<div class="content">
				<div class="container-fluid">
					<div id="user-card-panel" class="row">	
					</div>
				</div>
			</div>
		</div>
		
    </div>
</div>
</body>
<%
String userName = request.getParameter("user_key");  
%>
<script>
$(document).ready(function(){
	generateRandom();
	searchVague('<%=userName%>');	
})
</script>
<script type="text/javascript" src="/Gitmining/js/userSearch.js"></script>
<script type="text/javascript" src="/Gitmining/assets/js/manhua_hoverTips.js"></script>
</html>