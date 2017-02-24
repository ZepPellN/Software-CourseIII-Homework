<!doctype html>
<html>
<%
String login2 = (String)session.getAttribute("login");
%>
<head>
<jsp:include page="head.jsp" flush="true" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="sidebar.jsp" flush="true" />
		<div class="main-panel">
			<jsp:include page="topbar.jsp" flush="true" />
			<div class="main">
				<div class="landing-header"
					style="background-image: url('/Gitmining/images/welcome.jpg');">
					<div class="container">
						<div class="motto" style="padding-bottom: 12%;">
							<h2 id="motto_content"></h2>
						</div>
					</div>

					<div class="section text-center section-light-brown landing-section">
						<div class="container">
							<div class="row">
								<div class="col-md-8 column">
									<h2>Understand You More Than You Do</h2>
									<h5>
										Trace your learning track intimately.<br>
										Record your growing experience accurately.<br>
										Provide you with most professional analysis.
									</h5>
								</div>
								<div class="col-md-4 column">
									<img alt="..." src="/Gitmining/images/welcome/profile.png" style="width: 100%;">
								</div>
							</div>
						</div>
					</div>

					<div class="section text-center section-transparent landing-section">
						<div class="container">
							<div class="row">
								<div class="col-md-4 column">
									<img alt="..." src="/Gitmining/images/welcome/picture1.png" style="width: 100%;">
								</div>
								<div class="col-md-8 column">
									<h2>Past, Present And Future</h2>
									<h5>
										Piece your past achievement together.<br>
										Supervise your present task.<br>
										Show you where your future is located.<br>
										Complete your own masterpiece.
									</h5>
								</div>
							</div>
						</div>
					</div>

					<div class="section text-center section-light-brown landing-section">
						<div class="container">
							<div class="row">
								<div class="col-md-8 column">
									<h2>Your World, Unique World</h2>
									<h5>
										One and only search engine that belongs to you.<br>
										The more you know about it, the more it knows about you.
										Change everything before you realize.
									</h5>
								</div>
								<div class="col-md-4 column">
									<img alt="..." src="/Gitmining/images/welcome/search.png" style="width: 100%; height: 13em;">
								</div>
							</div>
						</div>
					</div>

					<div class="section section-transparent landing-section">
						<div class="container">
							<div id="initial_row" class="row">
								<div class="col-md-8 col-md-offset-2">
									<h2 class="text-center">Now It's Time to Make It</h2>
									<form name="form" class="contact-form" method="post"
										action="/Gitmining/log/set">
										<div class="row">
											<div class="col-md-6 form-group">
												<label>Programming Duration</label> <select name="level"
													class="form-control">
													<option selected="selected" value="1">&lt 3 month</option>
													<option value="2">3 months ~ 1 year</option>
													<option value="3">&gt 1 year</option>
												</select>
											</div>
											<div class="col-md-6 form-group">
												<label>Preference Language</label><select name="languages"
													class="form-control"><option selected="selected"
														value="All">All Interested</option>
													<option value="C">C</option>
													<option value="PHP">PHP</option>
													<option value="HTML">HTML</option>
													<option value="Java">Java</option>
													<option value="Perl">Perl</option>
													<option value="Ruby">Ruby</option>
													<option value="Shell">Shell</option>
													<option value="Python">Python</option>
													<option value="JavaScript">JavaScript</option>
													<option value="Others">Others</option></select>
											</div>
										</div>
										<div class="row">
											<div class="col-md-4 col-md-offset-4">
												<button class="btn btn-danger btn-block btn-lg btn-fill"
													style="border: none" onclick="submitForm();return false;">Confirm</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="/Gitmining/js/welcome.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var login = "<%=login2%>";
		$("#motto_content").text(login + ", welcome to join us!");
	});
</script>
</html>