<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<jsp:include page="head.jsp" flush="true" />
<title>Repository Analysis</title>
<link href="/Gitmining/css/repoAnalysis.css" rel="stylesheet" />
</head>
<body>
	<div class="wrapper">
		<jsp:include page="sidebar.jsp" flush="true" />

		<div class="main-panel">
			<jsp:include page="topbar.jsp" flush="true" />
			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="card">
                                <div class="header">
                                    <h5>Distribution of Repository in Different Years</h5>
                                </div>
								<div id="createtime" class="ct-chart2"></div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="card" style="height: 38em;">
								<div class="header">
									<div id="myCarousel" class="carousel slide">
										<ol class="carousel-indicators">
											<li data-target="#myCarousel" data-slide-to="0"
												class="active"></li>
											<li data-target="#myCarousel" data-slide-to="1"></li>
											<li data-target="#myCarousel" data-slide-to="2"></li>
										</ol>
										<!-- Carousel items -->
										<div class="carousel-inner">
											<div class="active item">
                                                <div class="header">
                                                    <h5>Distribution of Languages in Different Repositories</h5>
                                                </div>
												<canvas id="language_trend" width="850px" height="400px"
													id="fork" class="ct-charts2" alt="First slide"></canvas>
											</div>
											<div class="item">
                                                <div class="header">
                                                    <h5>Distribution of Repositories in Different Languages</h5>
                                                </div>
												<canvas id="language_group" width="850px" height="400px"
													id="fork" class="ct-charts2" alt="First slide"></canvas>
											</div>
											<div class="item">
                                                <div class="header">
                                                    <h5>Distribution Language Evolution with Years</h5>
                                                </div>
												<canvas id="language_distribution" width="850px"
													height="400px" id="fork" class="ct-charts2"
													alt="First slide"></canvas>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="card" style="height: 500px">
									<div id="myCarousel2" class="carousel slide">
										<ol class="carousel-indicators">
											<li data-target="#myCarousel2" data-slide-to="0"
												class="active"></li>
											<li data-target="#myCarousel2" data-slide-to="1"></li>
										</ol>
										<!-- Carousel items -->
										<div class="carousel-inner">
											<div class="active item">
                                                <div class="header">
                                                    <h5>Distribution of Repository with Forks</h5>
                                                </div>
												<canvas width="850px" height="400px" id="fork"
													class="ct-charts2" alt="First slide"></canvas>
											</div>
											<div class="item">
                                                <div class="header">
                                                    <h5>Distribution of Repository with Stars</h5>
                                                </div>
												<canvas width="850px" height="400px" id="star"
													class="ct-charts2" src="" alt="First slide"></canvas>
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
<script type="text/javascript">
    $(document).ready(function() {
    	var url1,url2,url3,url4,url5;
    	
    	 var img1 = new Image();
    	 var img2 = new Image();
    	 var img3 = new Image();
    	 var img4 = new Image();
    	 var img5 = new Image();
    	
    	url1 = "/Gitmining/repostatics/languages";
    	url3 = "/Gitmining/repostatics/forks?interval=20";
    	url4 = "/Gitmining/repostatics/stars?interval=30";
    	url5 = "/Gitmining/repostatics/create_ats";
    		
    		$.ajax(url1, {
    			type : 'GET',
    			async : false,
    			success : function(data, textStatus) {    				
    				var obj = JSON.stringify(data);
    				var jsObj =  eval ("(" + obj + ")");    			
    			    var myChart = echarts.init(document.getElementById('language_trend'));
    			    var option = {
    			            tooltip : {
    			                trigger: 'item',
    			                formatter: "{a} <br/>{b} : {c} ({d}%)"
    			            },
    			            itemStyle: {
    			                emphasis: {
    			                    shadowBlur: 200,
    			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
    			                },
    			                normal: {
    			                    shadowBlur: 200,
    			                    shadowOffsetX: 0,
    			                    shadowOffsetY: 0,
    			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
    			                }
    			            },

    			            legend: {
    			                x : 'center',
    			                y : 'bottom',
    			               
    			            },
    			            toolbox: {
    			                show : true,
                                showTitle: false,
    			                feature : {
    			                    mark : {show: true},
    			                    saveAsImage : {show: true}
    			                }
    			            },

    			            calculable : true,
    			            series: [
    			                {
    			                    name:'distribution',
    			                    type:'pie',
    			                    selectedMode: 'single',
    			                    radius: [0, '30%'],

    			                    label: {
    			                        normal: {
    			                            position: 'inner'
    			                        }
    			                    },
    			                    labelLine: {
    			                        normal: {
    			                            show: false
    			                        }
    			                    },
    			                    data:[
    			                        {value:jsObj.Others+jsObj.Perl+jsObj.Ruby, name:'Others', selected:true},
    			                        {value:jsObj.JavaScript+jsObj.PHP+jsObj.HTML+jsObj.Ruby, name:'Web'},
    			                        {value:jsObj.Java+jsObj.C+jsObj.Python, name:'Desktop'}
    			                    ]

    			                },
    			                {
    			                    name:'',
    			                    type:'pie',
    			                    radius: ['40%', '55%'],
    			                    center : ['25%', 200],
    			                    data:[

    			                        {value:jsObj.Others, name:'Others'},
    			                        {value:jsObj.Java, name:'Java'},
    			                        {value:jsObj.C, name:'C'},
    			                        {value:jsObj.Python, name:'Python'},
    			                        {value:jsObj.PHP, name:'PHP'},
    			                        {value:jsObj.JavaScript, name:'JavaScript'},
    			                        {value:jsObj.HTML, name:'HTML'},
    			                        {value:jsObj.Shell,name:'Shell'},
    			                        {value:jsObj.Ruby,name:'Ruby'},
    			                        {value:jsObj.Perl,name:'Perl'}
    			                    ]
    			                },

    			                {
    			                    name:'面积模式',
    			                    type:'pie',
    			                    radius : [30, 110],
    			                    center : ['75%', 200],
    			                    roseType : 'area',
    			                    data:[
    			                        {value:jsObj.Others, name:'Others'},
    				                        {value:jsObj.Java, name:'Java'},
    				                        {value:jsObj.C, name:'C'},
    				                        {value:jsObj.Python, name:'Python'},
    				                        {value:jsObj.PHP, name:'PHP'},
    				                        {value:jsObj.JavaScript, name:'JavaScript'},
    				                        {value:jsObj.HTML, name:'HTML'},
    				                        {value:jsObj.Shell,name:'Shell'},
    				                        {value:jsObj.Ruby,name:'Ruby'},
    				                        {value:jsObj.Perl,name:'Perl'}
    			                    ]
    			                }
    			            ]
    			        };
    			        myChart.setOption(option);
    			        img1.src = myChart.getDataURL({
    			            pixelRatio: 2, 
    			        });
    					$("#first").attr("src",img1.src);
    			}
    		});
    		
    	$.ajax(url1,{
    		type:'GET',
    		async:false,
    		success:function(data,textStatus){
    			var obj = JSON.stringify(data);
    			
    			var jsObj =  eval ("(" + obj + ")");
    			
    			var myChart = echarts.init(document.getElementById('language_group'));

    	        var option = {
    	                tooltip : {
    	                    trigger: 'axis',
    	                    axisPointer : {
    	                        type : 'shadow'
    	                    },
    	                    formatter: function (params) {
    	                        var tar;
    	                        if (params[1].value != '-') {
    	                            tar = params[1];
    	                        }
    	                        else {
    	                            tar = params[0];
    	                        }
    	                        return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
    	                    }
    	                },
    	                toolbox: {
    	                    show : true,
                            showTitle: false,
    	                    feature : {
    	                        magicType : {
    	                            show: true,
    	                            type: ['stack,','tiled','line']
    	                        },
    	                        restore : {show: true},
    	                        saveAsImage : {show: true}
    	                    }
    	                },

    	                grid: {
    	                    left: '3%',
    	                    right: '4%',
    	                    bottom: '3%',
    	                    containLabel: true
    	                },
    	                xAxis: {
    	                    type : 'category',
    	                    splitLine: {show:false},
    	                    data :  function (){
    	                        var list = [];
    	                        list.push('Java');
    	                        list.push('C');
    	                       
    	                        list.push('Python');
    	                        list.push('HTML');
    	                        list.push('JavaScript');
    	                        list.push('Shell');
    	                        list.push('Ruby');
    	                        list.push('PHP');
    	                        list.push('Perl');
    	                        list.push('Others');
    	                        return list;
    	                    }()
    	                },
    	                yAxis: {
    	                    type : 'value'
    	                },
    	                series: [
    	                    {
    	                        name: 'Total',
    	                        type: 'bar',
    	                        stack: 'TotalNumber',
    	                        itemStyle: {
    	                            normal: {
    	                                barBorderColor: 'rgba(0,0,0,0)',
    	                                color: 'rgba(0,0,0,0)'
    	                            },
    	                            emphasis: {
    	                                barBorderColor: 'rgba(0,0,0,0)',
    	                                color: 'rgba(0,0,0,0)'
    	                            }
    	                        },

    	                        data:[jsObj.Java, jsObj.Java+jsObj.C, jsObj.Java+jsObj.C+jsObj.Python, jsObj.Java+jsObj.C+jsObj.Python+jsObj.HTML
   	                               , jsObj.Java+jsObj.C+jsObj.Python+jsObj.HTML+jsObj.JavaScript, jsObj.Java+jsObj.C+jsObj.Python+jsObj.HTML+jsObj.JavaScript+jsObj.Shell
	                               , jsObj.Java+jsObj.C+jsObj.Python+jsObj.HTML+jsObj.JavaScript+jsObj.Shell+jsObj.Ruby
	                               , jsObj.Java+jsObj.C+jsObj.Python+jsObj.HTML+jsObj.JavaScript+jsObj.Shell+jsObj.Ruby+jsObj.PHP
	                               , jsObj.Java+jsObj.C+jsObj.Python+jsObj.HTML+jsObj.JavaScript+jsObj.Shell+jsObj.Ruby+jsObj.PHP+jsObj.Perl
	                               , jsObj.Java+jsObj.C+jsObj.Python+jsObj.HTML+jsObj.JavaScript+jsObj.Shell+jsObj.Ruby+jsObj.PHP+jsObj.Perl+jsObj.Others]
    	                    },
    	                    {
    	                        name: 'Language',
    	                        type: 'bar',
    	                        stack: 'TotalNumber',
    	                        label: {
    	                            normal: {
    	                                show: true,
    	                                position: 'top'
    	                            }
    	                        },
    	                       
    	                        data:[jsObj.Java, jsObj.C, jsObj.Python, jsObj.HTML, jsObj.JavaScript, jsObj.Shell, jsObj.Ruby, jsObj.PHP, jsObj.Perl, jsObj.Others]
    	                        	
    	                    },

    	                ]
    	            };

    	            myChart.setOption(option);
    	            
    	            img2.src = myChart.getDataURL({
			            pixelRatio: 2,
			            
			        });
					
					$("#second").attr("src",img2.src);
    		}
    	});
    	
    	
    	var languages = new Array('Java','C','Python','Ruby','JavaScript','Perl','PHP','HTML','Shell','Others');
    	var datas = new Array();
    	for(var i=0;i<languages.length;i++){
    	url2 = "/Gitmining/repostatics/language?language="+languages[i];
    		$.ajax(url2,{
    			type : 'GET',
    			async : false,
    			success:function(data,textStatus){
    				var obj = JSON.stringify(data);
    				
    				var jsObj =  eval ("(" + obj + ")");
    				datas[i] = jsObj;
    			
    			}
    		});
    	}

    	var myChart = echarts.init(document.getElementById('language_distribution'));

        var option = {
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['Java','C','Python','Ruby','JavaScript','Perl','PHP','HTML','Shell','Others'],
                y:'94.3%'
            },
            toolbox: {
                showTitle: false,
                feature: {
                    saveAsImage: {}
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : ['2007','2008','2009','2010']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'Java',
                    type:'line',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'top'
                        }
                    },
                    areaStyle: {normal: {}},
                    data:[ 0, datas[0]["2008"], datas[0]["2009"],datas[0]["2010"]]
                },
                {
                    name:'C',
                    type:'line',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'top'
                        }
                    },
                    areaStyle: {normal: {}},
                    data:[ 0, datas[1]["2008"], datas[1]["2009"],datas[1]["2010"]]
                },
                {
                    name:'Ruby',
                    type:'line',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'top'
                        }
                    },
                    areaStyle: {normal: {}},
                    data:[ datas[3]["2007"], datas[3]["2008"], datas[3]["2009"],datas[3]["2010"]]
                },
                {
                    name:'Python',
                    type:'line',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'top'
                        }
                    },
                    areaStyle: {normal: {}},
                    data:[ 0, datas[2]["2008"], datas[2]["2009"],datas[2]["2010"]]
                },
                {
                    name:'PHP',
                    type:'line',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'top'
                        }
                    },
                    areaStyle: {normal: {}},
                    data:[ 0, datas[6]["2008"], datas[6]["2009"],datas[6]["2010"]]
                },
           
              {
                    name:'JavaScript',
                    type:'line',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'top'
                        }
                    },
                    areaStyle: {normal: {}},
                    data:[0, datas[4]["2008"], datas[4]["2009"],datas[4]["2010"]]
                },
                {
                    name:'Perl',
                    type:'line',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'top'
                        }
                    },
                    areaStyle: {normal: {}},
                    data:[ 0, datas[5]["2008"], datas[5]["2009"],datas[5]["2010"]]
                },
                {
                    name:'HTML',
                    type:'line',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'top'
                        }
                    },
                    areaStyle: {normal: {}},
                    data:[ 0, datas[7]["2008"], datas[7]["2009"],datas[7]["2010"]]
                },
                {
                    name:'Shell',
                    type:'line',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'top'
                        }
                    },
                    areaStyle: {normal: {}},
                    data:[ 0, datas[8]["2008"], datas[8]["2009"],datas[8]["2010"]]
                },
                {
                    name:'Others',
                    type:'line',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false,
                            position: 'top'
                        }
                    },
                    areaStyle: {normal: {}},
                    data:[ 0, datas[9]["2008"], datas[9]["2009"],datas[9]["2010"]]
                }
            ]
        };
        
        myChart.setOption(option);
        img3.src = myChart.getDataURL({
            pixelRatio: 2,
            
        });
		
		$("#third").attr("src",img3.src);
        
        $.ajax(url3,{
       	 type : 'GET',
    		 async : false,
    		 success:function(data,textStatus){
    			 var obj = JSON.stringify(data);
    			
    			 var jsObj =  eval ("(" + obj + ")");
    		
    			  var myChart = echarts.init(document.getElementById('fork'));
    				
    			 	var stars = [];
    			    var datas = [];
    			    for (var key in jsObj) {
    			  
    			        stars.push(key);
    			        datas.push(jsObj[key]);
    			    }

    			    option = {
    			        tooltip: {
    			            trigger: 'axis'
    			        },
    			        toolbox: {
    			            show: true,
                            showTitle: false,
    			            feature: {
    			                dataView: {show: false, readOnly: false},
    			                magicType: {show: true, type: [ 'bar']},
    			                restore: {show: true},
    			                saveAsImage: {show: true}
    			            }
    			        },
    			        xAxis: {
    			            type: 'category',
    			            boundaryGap: false,
    			            data: stars
    			        },
    			        yAxis: {
    			            type: 'value',
    			            boundaryGap: [0, '100%']
    			        },
    			        dataZoom: [{
    			            start: 0,
    			            end: 5
    			        }],
    			        series: [
    			            {
    			                name:'Repositories',
    			                type:'line',
    			                smooth:true,
    			                symbol: 'none',
    			                sampling: 'average',
    			                itemStyle: {
    			                    normal: {
    			                        color: 'rgb(104,179,200)'
    			                    }
    			                },
    			                areaStyle: {
    			                    normal: {
    			                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
    			                            offset: 0,
    			                            color: 'rgb(104,179,200)'
    			                        }, {
    			                            offset: 1,
    			                            color: 'rgb(104,179,200)'
    			                        }])
    			                    }
    			                },
    			                data: datas
    			            }
    			        ]
    			    };
    			
    			    myChart.setOption(option);
    			    img4.src = myChart.getDataURL({
			            pixelRatio: 2,
			            
			        });
					
					$("#first1").attr("src",img4.src);
    		 }
	     });
        
        $.ajax(url4,{
       	 type : 'GET',
    		 async : false,
    		 success:function(data,textStatus){
    			 var obj = JSON.stringify(data);
    			
    			 var jsObj =  eval ("(" + obj + ")");
    		
    			  var myChart = echarts.init(document.getElementById('star'));
    				
    			 	var stars = [];
    			    var datas = [Math.random() * 300];
    			    for (var key in jsObj) {
    					
    			        stars.push(key);
    			        datas.push(jsObj[key]);
    			        
    			    }
    			
    			    option = {
    			        tooltip: {
    			            trigger: 'axis'
    			        },
    			        toolbox: {
    			            show: true,
                            showTitle: false,
    			            feature: {
    			                dataView: {show: false, readOnly: false},
    			                magicType: {show: true, type: [ 'bar']},
    			                restore: {show: true},
    			                saveAsImage: {show: true}
    			            }
    			        },
    			        xAxis: {
    			            type: 'category',
    			            boundaryGap: false,
    			            data: stars
    			        },
    			        yAxis: {
    			            type: 'value',
    			            boundaryGap: [0, '100%']
    			        },
    			        dataZoom: [ {
    			            start: 0,
    			            end: 4
    			        }],
    			        series: [
    			            {
    			                name:'Repositories',
    			                type:'line',
    			                smooth:true,
    			                symbol: 'none',
    			                sampling: 'average',
    			                itemStyle: {
    			                    normal: {
    			                        color: 'rgb(104,179,200)'
    			                    }
    			                },
    			                areaStyle: {
    			                    normal: {
    			                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
    			                            offset: 0,
    			                            color: 'rgb(104,179,200)'
    			                        }, {
    			                            offset:1, 
    			                            color: 'rgb(104,179,200)'
    			                        }])
    			                    }
    			                },
    			                data: datas
    			            }
    			        ]
    			    };
    			
    			    myChart.setOption(option);
    			    img5.src = myChart.getDataURL({
			            pixelRatio: 2,
			            
			        });
					
					$("#second2").attr("src",img5.src);
    		 }
       });
        $.ajax(url5,{
      	  type:'GET',
      	  async:false,
      	  success:function(data,textStatus){
      		 var obj = JSON.stringify(data);
    			
    		 var jsObj =  eval ("(" + obj + ")");
      		
    		 obj = JSON.stringify(data);
    		
      		 jsObj =  eval ("(" + obj + ")");
    	            var myChart = echarts.init(document.getElementById('createtime'));

    	            var option = {

    	                tooltip : {
    	                    trigger: 'axis'
    	                },
    	                legend: {
    	                    data:['Year']
    	                },
    	                toolbox: {
    	                    show : true,
                            showTitle: false,
    	                    feature : {
    	                        mark : {show: true},
    	                        magicType : {
    	                            show: true,
    	                            type: ['bar']
    	                        },
    	                        restore : {show: true},
    	                        saveAsImage : {show: true}
    	                    }
    	                },
    	                grid: {
    	                    left: '3%',
    	                    right: '4%',
    	                    bottom: '3%',
    	                    containLabel: true
    	                },
    	                xAxis : [
    	                    {
    	                        type : 'category',
    	                        boundaryGap : false,
    	                        data : ['2007','2008','2009','2010',]
    	                    }
    	                ],
    	                yAxis : [
    	                    {
    	                        type : 'value'
    	                    }
    	                ],
    	                series : [
    	                    {
    	                        name:'Repositories',
    	                        type:'line',
    	                        stack: '总量',
    	                        areaStyle: {normal: {}},
    	                        label: {
    	                            normal: {
    	                                show: true,
    	                                position: 'top'
    	                            }
    	                        },
    	                        areaStyle: {normal: {}},
    	                        data:[jsObj["2007"],jsObj["2008"],jsObj["2009"],jsObj["2010"]]

    	                    },

    	                ]
    	            };
    	            myChart.setOption(option);		
      	  }
        });
    });
    $("#myCarousel").carousel('cycle');
    $("#myCarousel2").carousel('cycle');
    </script>
</html>
