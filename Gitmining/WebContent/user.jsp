<%@ page language="java" import="java.util.*"
    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <jsp:include page="head.jsp" flush="true" />
	<title>User Home</title>
</head>
<body>

<div class="wrapper">
    <jsp:include page="sidebar.jsp" flush="true" />

    <div class="main-panel">
        <jsp:include page="topbar.jsp" flush="true" />

        <div id="fresh" class="content" style="margin-top: 2%;">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-4 col-md-5" style="margin-top: 0.5%; margin-left: 1%; margin-right: -1%;">
                        <div class="card card-user">
                            <div class="image">
                                <img src="/Gitmining/assets/img/background.jpg" alt="..."/>
                            </div>
                            <div class="content">
                                <div class="author">
                                  <a id="avatar_link" target="_blank"><img class="avatar border-white" id="avatar" style="background-color: white;"/></a>
                                  <h4 class="title" id="name"></h4>
                                </div>
                            </div>
                            <div class="text-center">
                                <div class="row">
                                    <div class="col-md-3 col-md-offset-1">
                                        <h5 id="n_1"></h5><h5><small>Activity</small></h5>
                                    </div>
                                    <div class="col-md-4">
                                        <h5 id="n_2"></h5><h5><small>Enthusiasm</small></h5>
                                    </div>
                                    <div class="col-md-3">
                                        <h5 id="n_3"></h5><h5><small>Popularity</small></h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card" style="margin-top: 10%; height: 19em;">
                            <div class="header">
                                <h4 class="title">Repository List</h4>
                            </div>
                            <div class="content" style="min-height: 200px;">
                                <ul id="related_ul" class="list-unstyled team-members">
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8 col-md-7">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">User Profile</h4>
                            </div>
                            <div class="content">
                                <form>
                                    <div class="row">
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Login</label>
                                                <input id="user_login" type="text" class="form-control border-input" readOnly="true">
                                            </div>
                                        </div>
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input id="names" type="text" class="form-control border-input" readOnly="true">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Date Joined</label>
                                                <input id="date" type="text" class="form-control border-input" readOnly="true">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div id="mail_group" class="form-group">
                                                <label>Email</label>
                                                <input id="mail" type="email" class="form-control border-input" readOnly="true" placeholder="Nothing...">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div id="blog_group" class="form-group">
                                                <label>Blog</label>
                                                <input id="blog" type="url" class="form-control border-input" readOnly="true" placeholder="Nothing...">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div id="company_group" class="form-group">
                                                <label>Company</label>
                                                <input id="company" type="text" class="form-control border-input" readOnly="true" placeholder="Nothing...">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div id="address_group" class="form-group">
                                                <label>Address</label>
                                                <input id="address" type="text" class="form-control border-input" readOnly="true" placeholder="Nothing...">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div id="bio_group" class="form-group">
                                                <label>About Me</label>
                                                <textarea id="bio" rows="5" class="form-control border-input" readOnly="true" placeholder="Nothing..."></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            	<div class="row">
            		<div class="col-md-12" style="padding: 0;">
						<div class="card" style="margin: 1.5% 2.55% 0 2.9%;">
                            <div class="head">
                                <h4 class="title">User Activity</h4>
                            </div>
							<div class="content">
								<div id="chart" style="width: 100%; height: 300%;"></div>
                             
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
String userName = request.getParameter("username");
%>
<script>
    var UserName="<%=userName%>";
</script>
<script type="text/javascript" src="/Gitmining/js/user.js"></script>
<script type="text/javascript">
var url = "/Gitmining/userstatics/activities?login=abarisain";
$.ajax(url,{
    type : 'GET',
    async : false,
    success : function(data, textStatus) {

        var myChart = echarts.init(document
                .getElementById('chart'));
        var obj = JSON.stringify(data);
        var jsObj = eval("(" + obj + ")");
        //存储日期
        var date = [];
        for ( var i in jsObj) {
            date.push(i);
        }
        //日期排序
        date.sort();
        // 指定图表的配置项和数据              
        var datas = [];
        for ( var j in date) {
            datas.push(jsObj[date[j]]);
        }

var myChart = echarts.init(document
		.getElementById('chart'));
var obj = JSON.stringify(data);
var jsObj = eval("(" + obj + ")");
//存储日期
var date = [];
for ( var i in jsObj) {
	date.push(i);
}
//日期排序
date.sort();
// 指定图表的配置项和数据				
var datas = [];
for ( var j in date) {
	datas.push(jsObj[date[j]]);
}

option = {
	tooltip : {
		trigger : 'axis'
	},
	title : {
		left : 'center',
		text : 'User Activity',
	},
	legend : {
		top : 'bottom',
		data : [ '意向' ]
	},
	
	xAxis : {
		type : 'category',
		boundaryGap : false,
		data : date
	},
	yAxis : {
		type : 'value',
		boundaryGap : [ 0, '100%' ]
	},
	dataZoom : [{
		start : 0,
		end : 100
	} ],
	series : [ {
		name : 'activity',
		type : 'line',
		smooth : true,
		symbol : 'none',
		sampling : 'average',
		itemStyle : {
			normal : {
				color : 'rgb(255, 70, 131)'
			}
		},
		areaStyle : {
			normal : {
				color : new echarts.graphic.LinearGradient(
						0,
						0,
						0,
						1,
						[
								{
									offset : 0,
									color : 'rgb(255, 158, 68)'
								},
								{
									offset : 1,
									color : 'rgb(255, 70, 131)'
								} ])
			}
		},
		data : datas
	} ]
};
// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);

        option = {
            tooltip : {
                trigger : 'axis'
            },
            legend : {
                top : 'bottom',
                data : [ '意向' ]
            },
            xAxis : {
                type : 'category',
                boundaryGap : false,
                data : date
            },
            yAxis : {
                type : 'value',
                boundaryGap : [ 0, '100%' ]
            },
            dataZoom : [ {
                start : 0,
                end : 100
            } ],
            series : [ {
                name : 'activity',
                type : 'line',
                smooth : true,
                symbol : 'none',
                sampling : 'average',
                itemStyle : {
                    normal : {
                        color : 'rgb(255, 70, 131)'
                    }
                },
                areaStyle : {
                    normal : {
                        color : new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [{
                                    offset : 0,
                                    color : 'rgb(255, 158, 68)'
                                },
                                {
                                    offset : 1,
                                    color : 'rgb(255, 70, 131)'
                                } ])
                    }
                },
                data : datas
            } ]
        };
        myChart.setOption(option);
    }
});
</script>
</html>