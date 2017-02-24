<!doctype html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="head.jsp" flush="true" />
<link href="/Gitmining/assets/css/tabs-basic.css" rel="stylesheet" />
<title>Personal Growth</title>
<style type="text/css">
#picture-ul {
	
	margin: auto;
	width: inherit;
	height: 620px;
	text-align: center;
}

.piece-ul {
	position: relative;
	background-image: url();
	background-position: top;
	background-repeat: no-repeat;
	margin: 10% auto;
	width: inherit;
	height: inherit;
	border:1px solid rgba(0,0,0,0);
}


.bg-area{
	position: absolute;
	background-position: center;
	background-repeat: no-repeat;
	margin: -1px;
	width: 800px;
	height: 800px;
	border:1px solid #999;
}

.piece-ul li {
	list-style-type: none;
	float: left;
	border: 0.1px solid rgba(255,255,255,0.5);
	margin: 0;
	margin-bottom: 0;
}

.piece-ul li a {
	display: block;
	border: none;
	margin: 0;
	padding: 0;
}

.piece-ul img {
	margin: 0;
	border: none;
}

.popover{
	max-width: 2000px;
}

.thumbnail{
	margin-bottom: 0;
	padding: 0;
}

.imgbox{
	min-height: 250px;
	text-align: center;
	/* border: 1px solid #999;
	border-radius: 3px; */
}

.imgbox img{
	height: 180px;
	float: left;
	margin-left: 10%;
}

.pop-info{
	color: #A7A7A7;
	font-weight: 200;
}

.pop-reward{
	color: #E3725D;
}

</style>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="sidebar.jsp" flush="true" />

		<div class="main-panel">
			<jsp:include page="topbar.jsp" flush="true" />
			<div class="content"
				style="padding: 0 15px 30px 15px; margin: 5% 1.5% 0 1.5%;">
				<div class="tabs-basic">

					<ul>
						<li><a class="tab-active" data-index="0" style="padding: 15px 22px 10px;">Performance</a></li>
						<li><a data-index="1" style="padding: 15px 22px 10px;">Profile</a></li>
						<li><a data-index="2" style="padding: 15px 22px 10px;">Rank</a></li>
					</ul>

					<div class="tabs-content-placeholder">
						<div class="tab-content-active">
							<div id="picture-ul" >
								<div id="panel-wrapper" class="piece-ul">
									<div id="bg-div"     class="bg-area">
										<img id="bg" alt="" src="">
									</div>
									<div id="piece-panel" class="bg-area" >
									</div>
								</div>
							</div>
							<!-- static thumbnail -->
							<div id="level-thumbnail" class="imgbox card">
								<div >
									<a class="" href='javascript: reload(1, 1)' style="padding-top: 2%">
										<img src="/Gitmining/images/level/lv1.jpg" >
									</a>
								</div >
								<div class=" ">
									<a class="" href='javascript: reload(2, 1)'>
										<img src="/Gitmining/images/level/lv2.jpg">
									</a>
								</div>
								<div class=" ">
									<a class="" href='javascript: reload(3, 1)'>
										<img src="/Gitmining/images/level/lv3.jpg">
									</a>
								</div>
							</div>
						</div>
						<div>
							<div class="row">
								<div class="col-md-offset-3 col-md-6">
									<div class="card card-user" style="background-color: rgba(0,0,0,0.7);">
			                            <div class="image">
			                                <img src="/Gitmining/assets/img/background.jpg"/>
			                            </div>
			                            <div class="content">
			                                <div class="author">
			                                  <img class="avatar border-white" id="log_avatar"/>
			                                  <h4 class="title" id="log_login" style="color: #EFEFEF"></h4>
			                                </div>
			                            </div>
			                            <div class="text-center">
			                            	<div class="row">
			                            		<div class="col-md-8 col-md-offset-2">
			                            			<p id="log_evaluation" style="text-indent: 2em; color: #EFEFEF; word-break:break-all;"></p>
			                            		</div>
			                            	</div>
			                            	<div class="row">
			                                    <div class="col-md-3 col-md-offset-1">
			                                        <h5 id="log_span" style="color: #EFEFEF"></h5><h5><small style="color: #EFEFEF">Span</small></h5>
			                                    </div>
			                                    <div class="col-md-4">
			                                        <h5 id="log_level" style="color: #EFEFEF"></h5><h5><small style="color: #EFEFEF">Level</small></h5>
			                                    </div>
			                                    <div class="col-md-3">
			                                        <h5 id="log_grade" style="color: #EFEFEF"></h5><h5><small style="color: #EFEFEF">Grade</small></h5>
			                                    </div>
			                                </div>
			                                <div class="row">
			                                    <div class="col-md-3 col-md-offset-1">
			                                        <h5 id="log_CLR" style="color: #EFEFEF"></h5><h5><small style="color: #EFEFEF">CLR</small></h5>
			                                    </div>
			                                    <div class="col-md-4">
			                                        <h5 id="log_FLR" style="color: #EFEFEF"></h5><h5><small style="color: #EFEFEF">FLR</small></h5>
			                                    </div>
			                                    <div class="col-md-3">
			                                        <h5 id="log_BRH" style="color: #EFEFEF"></h5><h5><small style="color: #EFEFEF">BRH</small></h5>
			                                    </div>
			                                </div>
			                            </div>
			                        </div>
		                        </div>
							</div>
							<div>
								<div class="col-md-offset-2 col-md-9">
									<ul>
										<li><b>Grade:</b> Related positively to the number and the complexity of finished learning repositories, negatively to the finish time.</li>
										<li><b>CLR:</b> Current Learning Repositories</li>
										<li><b>FLR:</b> Finished Learning Repositories</li>
										<li><b>BRH:</b> Browsed Repository History</li>
									</ul>
								</div>
							</div>
						</div>
						<div>
							<div class="row">
								<div class="col-md-12">
									<div class="content table-responsive table-full-width">
										<table class="table table-hover">
											<thead style="font-size: 18px; font-weight: bold;">
												<tr>
													<th>Rank</th>
													<th>Login</th>
													<th>Age</th>
													<th>Level</th>
													<th>Grade</th>
												</tr>
											</thead>
											<tbody id="rank-list" style="font-size: 18px;">
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
	</div>
	<script>

	$(document).ready(function() {

		var widget = $('.tabs-basic');

		var tabs = widget.find('ul a'),
			content = widget.find('.tabs-content-placeholder > div');

		tabs.on('click', function (e) {

			e.preventDefault();

			var index = $(this).data('index');

			tabs.removeClass('tab-active');
			content.removeClass('tab-content-active');

			$(this).addClass('tab-active');
			content.eq(index).addClass('tab-content-active');

		});
		
		loadProfile();
		loadRank();
		loadPic();
	});

</script>
	<script type="text/javascript" src="/Gitmining/js/picture.js"></script>
	<script type="text/javascript" src="/Gitmining/js/profile.js"></script>
</body>
</html>