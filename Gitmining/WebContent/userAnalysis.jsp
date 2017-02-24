<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<jsp:include page="head.jsp" flush="true" />
<title>User Analysis</title>
<link href="/Gitmining/css/userChart2.css" rel="stylesheet" />
</head>
<body>
    <div class="wrapper">
        <jsp:include page="sidebar.jsp" flush="true" />
    	<div class="main-panel">
        <jsp:include page="topbar.jsp" flush="true" />
    		<div class="content">
				<div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h5>User Type</h5>
                            </div>
                            <div class="content">
                                <div id="user-type" class="ct-chart2"></div>
                            </div>
                        </div>
                    </div>
                </div>

				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="header">
								<h5>Created Time</h5>
							</div>
							<div class="content">
								<div id="create-time" class="ct-chart2"></div>
							</div>
						</div>
					</div>
				</div>
				
                <div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="header">
								<h5>Repository number - Population</h5>
							</div>
							<div class="content">
								<div id="select" class="dropdown" style="margin-left: 3%;">
                                    <a class="dropdown-toggle" data-toggle="dropdown">
                                        <p>interval<span class="caret"></span></p>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a  onclick="myFunction(10)">10</a></li>
                                        <li><a  onclick="myFunction(20)">20</a></li>
                                        <li><a  onclick="myFunction(50)">50</a></li>
                                    </ul>
								</div>
								<div id="number" class="ct-chart2"></div>
							</div>
						</div>
					</div>
				</div>
    		</div>
    	</div>
    </div>
</body>
<script type="text/javascript" src="/Gitmining/js/userAnalysis.js"></script>
</html>