$(function(){
	$(document).ready(function(){
		//alert("start");
		userType();
		createTime();
		myFunction(10);
	});
});

function createTime(){
	var url = "/Gitmining/userstatics/created_ats";
	$.ajax(url, {
		type : 'GET',
		async : false,
		success : function(data, textStatus) {
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document
					.getElementById('create-time'));

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
					trigger : 'item',
					formatter : '{a} <br/>{b} : {c}'
				},
				/*  legend: {
				     left: 'left',
				     data: ['2的指数', '3的指数']
				 }, */
				xAxis : {
					type : 'category',
					name : 'year',
					splitLine : {
						show : false
					},
					data : date
				},
				grid : {
					left : '3%',
					right : '4%',
					bottom : '3%',
					containLabel : true
				},
				yAxis : {
					type : 'log',
					name : 'population'
				},
				series : [ {
					name : 'population',
					type : 'line',
					data : datas
				} ]
			};

			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		}
	});
	
};


function userType(){
	var url = "/Gitmining/userstatics/ownertypes";
	$.ajax(url, {
		type : 'GET',
		async : false,
		success : function(data, textStatus) {
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document
					.getElementById('user-type'));

			var obj = JSON.stringify(data);
			var jsObj = eval("(" + obj + ")");

			// 指定图表的配置项和数据
			option = {
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					orient : 'vertical',
					left : 'center',
					data : [ 'User', 'Organization' ]
				},
				series : [ {
					name : 'type',
					type : 'pie',
					radius : '55%',
					center : [ '50%', '60%' ],
					data : [ {
						value : jsObj["Organization"],
						name : 'Organization'
					}, {
						value : jsObj["User"],
						name : 'User'
					}, ],
					itemStyle : {
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						}
					}
				} ]
			};

			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		}
	});	
};


function myFunction(n){
    var url = "/Gitmining/userstatics/repositories";
    $.ajax(url,{
      	 type : 'GET',
    		 async : false,
    		 success:function(data,textStatus){ 
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('number'));
        var obj = JSON.stringify(data);
  	  	var jsObj =  eval ("(" + obj + ")");
  	  	//处理数据
  	  	var x = [];
  	  	var y = [];
		for(var i in jsObj){
			x.push(i);
			y.push(jsObj[i]);
		}
		var group=200/n;
		var newx=[];
		var newy=new Array(group);
		//初始化
		for(var i=0;i<group;i++){
			newy[i]=0;
			//alert("hello");
		}
		for(var i=0;i<group;i++){
			newx.push((i+1)*n);
			//newx[i]=(i+1)*n;
		}
		for(var j in x){
			for(var k in newx){
				if(parseInt(x[j])<(newx[k]+1)&&parseInt(x[j])>=(newx[k])-n){
					newy[k]+=y[j];
				}
			}	
		}
      option = {
          tooltip : {
              trigger: 'axis',
              axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                  type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
              }
          },
          legend: {
              data:['Population']
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
                  data : newx
              }
          ],
          yAxis : [
              {
                  type : 'value'
              }
          ],
          series : [
              {
                  name:'Population',
                  type:'bar',
                  data: newy
              }
          ]
      };

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    		}
    });
    };