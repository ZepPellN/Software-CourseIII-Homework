

$(document).ready(function() {
    $("#result").hide();
    $("#return").hide();
    $("#forward").hide();

    var heatDatas = new Array(5);
    heatDatas[0] = [];
    heatDatas[1] = [];
    heatDatas[2] = [];
    heatDatas[3] = [];
    heatDatas[4] = [];

    $.ajax({
        type : "get",
        async : false,
        url : "/Gitmining/repository/recommends",
        data : {
            first_name: coreName,
            second_name: recommendName
        },
        datatype: 'json',
        success : function(result) {
            var a,b,c,d,count = 0;
            
            var length = Math.floor(Math.sqrt(result[0].length));
         
            for(var i = 0; i < length; i++){
               for (var j = 0; j < length; j++) {
            	    for (var k = 0; k < 5; k++) {
                        heatDatas[k].push([i/4, j/4, parseInt(result[k].charAt(i * length + j))]);
                    }
                }
            }
        }
    })

    var myCharts = new Array(5);
    myCharts[0] = echarts.init(document.getElementById("first"));
    myCharts[1] = echarts.init(document.getElementById("third"));
    myCharts[2] = echarts.init(document.getElementById("second"));
    myCharts[3] = echarts.init(document.getElementById("forth"));
    myCharts[4] = echarts.init(document.getElementById("final"));

    for (var i = 0; i < 5; i++) {
        var option = {
            tooltip: {
                position: 'top'
            },
            animation: false,
            grid: {
                show:false,
                height: '100%',
                width: '100%',
                y: '0%'
            },
            xAxis: {
     	        type: 'category',
     	        data: []
     	    },
     	    yAxis: {
     	        type: 'category',
     	        data: []
     	    },
            visualMap: {
                min: 0,
                max: 5,
                range: [0, 5],
               
                calculable: true,
                realtime: true,
                hoverLink:true,
                orient: 'vertical',
               
                left: '-5%',
                bottom: '20%'
            },
            series: [{
                name: 'Relevent',
                type: 'heatmap',
                data: heatDatas[i],
                label: {
                    normal: {
                        show: false
                    },
                    emphasis:{
                        show: false
                    }
                },
                itemStyle: {
                    emphasis: {
                        shadowBlur: 20,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }]
        };

        myCharts[i].setOption(option);
    }

    $("#firstH").html(coreName);
    $("#thirdH").html(recommendName);
    $("#secondH").html(coreName);
    $("#forthH").html(recommendName);
    $("#finalH").html("Final correlation between " + coreName+" and "+recommendName);
    $("#secondD").hide();
    $("#forthD").hide();
    $("#finalCol").hide();
    
    $("#next").click(function(){
        $("#next").hide();
        $("#result").show();
        $("#secondD").show();
        $("#forthD").show();
    });

    $("#result").click(function(){
        $("#finalCol").show();
        $("#result").hide();
        $("#return").show();
        $("#forward").show();
    });

    $("#return").click(function(){
    	refreshHistory(coreName);
    	return false;
    });

    $("#forward").click(function(){
        refreshHistory(recommendName);
        return false;
    });
});