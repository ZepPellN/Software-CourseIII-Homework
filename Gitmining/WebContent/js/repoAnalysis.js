$(document).ready(function() {
	var url1,url2,url3,url4,url5;
	
	url1 = "/Gitmining/repostatics/languages";
	url3 = "/Gitmining/repostatics/forks?interval=20";
	url4 = "/Gitmining/repostatics/stars?interval=30";
	url5 = "/Gitmining/repostatics/create_ats";
		
		$.ajax(url1, {
			type : 'GET',
			async : false,
		//	dataType : 'json',
			success : function(data, textStatus) {
				// Set up the chart
				
		
				
				var obj = JSON.stringify(data);
		
				var jsObj =  eval ("(" + obj + ")");
			
			    var myChart = echarts.init(document.getElementById('language_trend'));
			    var option = {
			            title : {
			                text: 'Distribution of Language in Different Repositories',
//			              
			                x:'center'
			            },
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
			                    // 阴影的大小
			                    shadowBlur: 200,
			                    // 阴影水平方向上的偏移
			                    shadowOffsetX: 0,
			                    // 阴影垂直方向上的偏移
			                    shadowOffsetY: 0,
			                    // 阴影颜色
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            },

			            legend: {
			                x : 'center',
			                y : 'bottom',
			                data:['Java','C','Python','Ruby','JavaScript','Perl','PHP','HTML','Shell','Others']
			            },
			            toolbox: {
			                show : true,
			                feature : {
			                    mark : {show: true},
			                   // dataView : {show: true, readOnly: true},
			                    magicType : {
			                        show: true,
			                        type: ['pie', 'funnel','bar','stack,','tiled']
			                    },
			                    restore : {show: true},
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
			        // 使用刚指定的配置项和数据显示图表。
			        myChart.setOption(option);


			
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
	                title: {
	                    text: 'Distribution of Repository in Different Language',
	                    x:'center'

	                },
	                tooltip : {
	                    trigger: 'axis',
	                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
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
	                legend: {
	                    //data:['Language']
	                },
	                toolbox: {
	                    show : true,
	                    feature : {
	                        mark : {show: true},
	                        // dataView : {show: true, readOnly: true},
	                        magicType : {
	                            show: true,
	                            type: ['pie', 'funnel','bar','stack,','tiled','line']
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

	                        data: [0,jsObj.Java, jsObj.C, jsObj.Python, jsObj.HTML, jsObj.JavaScript, jsObj.Shell, jsObj.Ruby, jsObj.PHP, jsObj.Perl, jsObj.Others]
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
	                        markLine : {
	                            lineStyle: {
	                                normal: {
	                                    type: 'dashed'
	                                }
	                            },
	                            data : [
	                                [{type : 'min'}, {type : 'max'}]
	                            ]
	                        },
	                        data: [jsObj.Java, jsObj.Java+jsObj.C, jsObj.Java+jsObj.C+jsObj.Python, jsObj.Java+jsObj.C+jsObj.Python+jsObj.HTML
	                               , jsObj.Java+jsObj.C+jsObj.Python+jsObj.HTML+jsObj.JavaScript, jsObj.Java+jsObj.C+jsObj.Python+jsObj.HTML+jsObj.JavaScript+jsObj.Shell
	                               , jsObj.Java+jsObj.C+jsObj.Python+jsObj.HTML+jsObj.JavaScript+jsObj.Shell+jsObj.Ruby
	                               , jsObj.Java+jsObj.C+jsObj.Python+jsObj.HTML+jsObj.JavaScript+jsObj.Shell+jsObj.Ruby+jsObj.PHP
	                               , jsObj.Java+jsObj.C+jsObj.Python+jsObj.HTML+jsObj.JavaScript+jsObj.Shell+jsObj.Ruby+jsObj.PHP+jsObj.Perl
	                               , jsObj.Java+jsObj.C+jsObj.Python+jsObj.HTML+jsObj.JavaScript+jsObj.Shell+jsObj.Ruby+jsObj.PHP+jsObj.Perl+jsObj.Others]
	                    },

	                ]
	            };


	            // 使用刚指定的配置项和数据显示图表。
	            myChart.setOption(


	                    option);
		
		}
	});
	
	
	var languages = new Array('Java','C','Python','Ruby','JavaScript','Perl','PHP','HTML','Shell','Others');
	var datas = new Array();
	for(var i=0;i<languages.length;i++){
	url2 = "repostatics/language?language="+languages[i];
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
        title: {
            text: 'Differnt Language Evolution with Years ',
            x:'center'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['Java','C','Python','Ruby','JavaScript','Perl','PHP','HTML','Shell','Others'],
            y:'bottom'
        },
        toolbox: {
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
                data : ['2004','2005','2006','2007','2008','2009','2010']
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
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data:[0, 0, 0, 0, datas[0]["2008"], datas[0]["2009"],datas[0]["2010"]]
            },
            {
                name:'C',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data:[0, 0, 0, 0, datas[1]["2008"], datas[1]["2009"],datas[1]["2010"]]
            },
            {
                name:'Ruby',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data:[0, 0, 0, datas[3]["2007"], datas[3]["2008"], datas[3]["2009"],datas[3]["2010"]]
            },
            {
                name:'Python',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data:[0, 0, 0, 0, datas[2]["2008"], datas[2]["2009"],datas[2]["2010"]]
            },
            {
                name:'PHP',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data:[0, 0, 0, 0, datas[6]["2008"], datas[6]["2009"],datas[6]["2010"]]
            },
       
          {
                name:'JavaScript',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data:[0, 0, 0, 0, datas[4]["2008"], datas[4]["2009"],datas[4]["2010"]]
            },
            {
                name:'Perl',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data:[0, 0, 0, 0, datas[5]["2008"], datas[5]["2009"],datas[5]["2010"]]
            },
            {
                name:'HTML',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data:[0, 0, 0, 0, datas[7]["2008"], datas[7]["2009"],datas[7]["2010"]]
            },
            {
                name:'Shell',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data:[0, 0, 0, 0, datas[8]["2008"], datas[8]["2009"],datas[8]["2010"]]
            },
            {
                name:'Others',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data:[0, 0, 0, 0, datas[9]["2008"], datas[9]["2009"],datas[9]["2010"]]
            }
        
        ]
    };


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    
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
			        title: {
			            left: 'center',
			            text: 'Distribution of Repository with Forks',
			        },
			        legend: {
			            top: 'bottom',
			            data:['Repositories']
			        },
			        toolbox: {
			            show: true,
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
			            type: 'inside',
			            start: 0,
			            end: 10
			        }, {
			            start: 0,
			            end: 10
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
			                        color: 'rgb(255, 70, 131)'
			                    }
			                },
			                areaStyle: {
			                    normal: {
			                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
			                            offset: 0,
			                            color: 'rgb(255, 158, 68)'
			                        }, {
			                            offset: 1,
			                            color: 'rgb(255, 70, 131)'
			                        }])
			                    }
			                },
			                data: datas
			            }
			        ]
			    };
			
			
			    // 使用刚指定的配置项和数据显示图表。
			    myChart.setOption(option);
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
			        title: {
			            left: 'center',
			            text: 'Distribution of Repository with Stars',
			        },
			        legend: {
			            top: 'bottom',
			            data:['Repositories']
			        },
			        toolbox: {
			            show: true,
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
			            type: 'inside',
			            start: 0,
			            end: 10
			        }, {
			            start: 0,
			            end: 10
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
			                        color: 'rgb(255, 70, 131)'
			                    }
			                },
			                areaStyle: {
			                    normal: {
			                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
			                            offset: 0,
			                            color: 'rgb(255, 158, 68)'
			                        }, {
			                            offset:1, 
			                            color: 'rgb(255, 70, 131)'
			                        }])
			                    }
			                },
			                data: datas
			            }
			        ]
			    };
			
			
			    // 使用刚指定的配置项和数据显示图表。
			    myChart.setOption(option);
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
	                title: {
	                    text: 'Distribution of Repository in Different Years',
	                    x:'center'
	                },
	                tooltip : {
	                    trigger: 'axis'
	                },
	                legend: {
	                    data:['Year']
	                },
	                toolbox: {
	                    show : true,
	                    feature : {
	                        mark : {show: true},
	                        // dataView : {show: true, readOnly: true},
	                        magicType : {
	                            show: true,
	                            type: ['pie', 'funnel','bar','stack,','tiled','line']
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

	            // 使用刚指定的配置项和数据显示图表。
	            myChart.setOption(option);
				
  	  }
   
    });		
});