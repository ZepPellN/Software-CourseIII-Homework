$("#study").hide();
$("#abort").hide();
var repo_language;
$(document).ready(function() {
	var url = "/Gitmining/repository/show?full_name=" + repositoryName;
	var arr = new Array();
	var owner, reponame, description, homepage, complex,star, fork, contributor,subscribers, collaborator, size, watchers, created, lastPushed, lastUpdated, recommendRepos;
	$.ajax(url, {
		type : 'GET',
		async : false,
		success : function(data, textStatus) {
			if(data === null){
				window.location.href("/Gitmining/error");
				return;
			}
			arr = data.recommendRepos;
			var obj = JSON.stringify(data);
			var jsObj = eval("(" + obj + ")");
			owner = jsObj.owner_name;
			reponame = jsObj.name;
			description = jsObj.description;
			homepage = jsObj.homepage;
			star = jsObj.stargazers_count;
			fork = jsObj.forks_count;
			contributor = jsObj.contributors_count;
			subscribers = jsObj.subscribers_count;
			collaborator = jsObj.open_issues_count;
			size = jsObj.size;
			watchers = jsObj.watchers_count;
			created = jsObj.created_at;
			lastPushed = jsObj.pushed_at;
			lastUpdated = jsObj.updated_at;
			recommendRepos = jsObj.recommendRepos;
			repo_language = jsObj.language;
			state = jsObj.state;
			complex = jsObj.complex;
		}
	});
	$("#ownername").html(owner);
	$("#reponame").html( " / "+reponame);
	$("#ownername").attr("href", "/Gitmining/home/user/" + owner);
	$("#mainlanguage").html("Language: " + repo_language);
	$("#complexity").html("Complexity: " + complex);
	$("#description").html("Description: " + description);
	$("#star").html(star);
	$("#lastUpdated").html(lastUpdated);
	$("#fork").html(fork);
	$("#subscriber").html(subscribers);
	$("#contributor").html(contributor);
	$("#issue").html(collaborator);
	$("#watcher").html(watchers);
	$("#created").html(created);
	$("#lastPushed").html(lastPushed);
	

	if (homepage === "") {
		$("#reponame").attr("onmousedown", "if(event.button == 2){openGithub('" + repositoryName + "');}return false;");
	} else {
		$("#reponame").attr("onmousedown", "if(event.button == 2){openGithub('" + repositoryName + "');}else if(event.button == 0){window.open('" + homepage + "');}return false;");
	}

	if (state === 0){
		$("#study").hide();
	}else if(state === 1){
		$("#study").html("Learn");
		$("#study").show();
	}else if(state === 2){
		$("#study").html("Finish");
		$("#study").show();
		$("#abort").html("Abort");
		$("#abort").show();
	}else if(state === 3){
		$("#study").html("Master");
		$("#study").show();
		$("#study").attr("disabled","disabled");
	}else if(state === 4){
		$("#study").html("Block").css({"background-color":"#E0E0E0"});
		$("#study").show();
	}
	
	$("#study").click(function(){
		
		learn(state);
	});
	
	$("#abort").click(function(){
		
		var url = "/Gitmining/repository/stop";
		$.ajax(url, {
			type : 'POST',
			async : false,
			data : {
				repo_name : repositoryName
			},
			success : function(data, textStatus) {
				if(data === "success"){
					state = 1;
					$("#study").html("Learn");
					$("#abort").hide();
				}
			}
		});
	});
	
	$("#firstA").attr("onmousedown",
			"if(event.button == 2){openGithub('" + arr[0].reco_name + "');}else if(event.button == 0){refreshHistory('" + arr[0].reco_name + "');}return false;")
			.html(arr[0].reco_name);
	$("#secondA").attr("onmousedown",
			"if(event.button == 2){openGithub('" + arr[1].reco_name + "');}else if(event.button == 0){refreshHistory('" + arr[1].reco_name + "');}return false;")
			.html(arr[1].reco_name);
	$("#thirdA").attr("onmousedown",
			"if(event.button == 2){openGithub('" + arr[2].reco_name + "');}else if(event.button == 0){refreshHistory('" + arr[2].reco_name + "');}return false;")
			.html(arr[2].reco_name);
	$("#forthA").attr("onmousedown",
			"if(event.button == 2){openGithub('" + arr[3].reco_name + "');}else if(event.button == 0){refreshHistory('" + arr[3].reco_name + "');}return false;")
			.html(arr[3].reco_name);
	$("#fifthA").attr("onmousedown",
			"if(event.button == 2){openGithub('" + arr[4].reco_name + "');}else if(event.button == 0){refreshHistory('" + arr[4].reco_name + "');}return false;")
			.html(arr[4].reco_name);
	$("#sixthA").attr("onmousedown",
			"if(event.button == 2){openGithub('" + arr[5].reco_name + "');}else if(event.button == 0){refreshHistory('" + arr[5].reco_name + "');}return false;")
			.html(arr[5].reco_name);
	$("#sevenA").attr("onmousedown",
			"if(event.button == 2){openGithub('" + arr[6].reco_name + "');}else if(event.button == 0){refreshHistory('" + arr[6].reco_name + "');}return false;")
			.html(arr[6].reco_name);
	$("#eightA").attr("onmousedown",
			"if(event.button == 2){openGithub('" + arr[7].reco_name + "');}else if(event.button == 0){refreshHistory('" + arr[7].reco_name + "');}return false;")
			.html(arr[7].reco_name);
	$("#nineA").attr("onmousedown",
			"if(event.button == 2){openGithub('" + arr[8].reco_name + "');}else if(event.button == 0){refreshHistory('" + arr[8].reco_name + "');}return false;")
			.html(arr[8].reco_name);
	$("#tenA").attr("onmousedown",
			"if(event.button == 2){openGithub('" + arr[9].reco_name + "');}else if(event.button == 0){refreshHistory('" + arr[9].reco_name + "');}return false;")
			.html(arr[9].reco_name);
	$("#firstC").attr("href", "/Gitmining/recommend/"+repositoryName+"&"+arr[0].reco_name).html(
			"Potential relevant: " + arr[0].relate_count);
	$("#secondC").attr("href", "/Gitmining/recommend/"+repositoryName+"&"+arr[1].reco_name).html(
			"Potential relevant: " + arr[1].relate_count);
	$("#thirdC").attr("href",  "/Gitmining/recommend/"+repositoryName+"&"+arr[2].reco_name).html(
			"Potential relevant: " + arr[2].relate_count);
	$("#forthC").attr("href",  "/Gitmining/recommend/"+repositoryName+"&"+arr[3].reco_name).html(
			"Potential relevant: " + arr[3].relate_count);
	$("#fifthC").attr("href",  "/Gitmining/recommend/"+repositoryName+"&"+arr[4].reco_name).html(
			"Potential relevant: " + arr[4].relate_count);
	$("#sixthC").attr("href",  "/Gitmining/recommend/"+repositoryName+"&"+arr[5].reco_name).html(
			"Potential relevant: " + arr[5].relate_count);
	$("#sevenC").attr("href",  "/Gitmining/recommend/"+repositoryName+"&"+arr[6].reco_name).html(
			"Potential relevant: " + arr[6].relate_count);
	$("#eightC").attr("href",  "/Gitmining/recommend/"+repositoryName+"&"+arr[7].reco_name).html(
			"Potential relevant: " + arr[7].relate_count);
	$("#nineC").attr("href",  "/Gitmining/recommend/"+repositoryName+"&"+arr[8].reco_name).html(
			"Potential relevant: " + arr[8].relate_count);
	$("#tenC").attr("href",  "/Gitmining/recommend/"+repositoryName+"&"+arr[9].reco_name).html(
			"Potential relevant: " + arr[9].relate_count);
});

function learn(i){
	var url_start = "/Gitmining/repository/start";
	var url_finish = "/Gitmining/repository/finish";
	var learning,finished;
	if(i === 1){
		$.ajax(url_start, {
			type : 'POST',
			async : false,
			data : {
				repo_name : repositoryName,
				language : repo_language
			},
			success : function(data, textStatus) {
				if(data === "success"){
					$("#study").html("Finish");
					
					$("#abort").html("Abort");
					$("#abort").show();
					state = 2;
				}else{
					alert("error");
				}
			}
		});
		
	}else if(i === 2){
		
		$.ajax(url_finish,{
			type: 'POST',
			async: false,
			data :{
				repo_name : repositoryName
			},
			success:function(data, textStatus){
				if(data === "success"){
					
					$("#study").html("Master")
					$("#study").attr("disabled","disabled");
					$("#abort").hide();
					$.notify(
							{
								icon : 'ti-github',
								message : "You have finished this repository! Good job!"
							}, {
								
								timer : 200,
								placement : {
									from : 'top',
									align : 'center'
								}
							});
					state = 3;
				}else if(data === "level up"){
					
					$.notify(
							{
								icon : 'ti-github',
								message : "Congratulations! You have been upgraded!"
							}, {
								
								timer : 200,
								placement : {
									from : 'top',
									align : 'center'
								}
							});
				}
			}
		});
		
	}else if(i === 3){
		
	}else if(i === 4){
		$.notify(
				{
					icon : 'ti-github',
					message : "You have enough repositories to study in this level! Work harder to get upgraded or abort some learning repositories."
				}, {
					type : 'danger',
					timer : 200,
					placement : {
						from : 'top',
						align : 'center'
					}
				});
	}
	
}


var url = "/Gitmining/repostatics/radar?full_name=" + repositoryName;

$.ajax(url, {
	type : 'GET',
	async : false,
	success : function(data, textStatus) {
		var myChart = echarts.init(document.getElementById("reposcore"));
		var option = {
			legend : {
				data : [ 'scores:' ],
				left : 'left top'
			},
			tooltip : {
				trigger : 'axis'
			},
			radar : [
				{
					indicator : [ {
						text : 'star',
						max : 1
					}, {
						text : 'fork',
						max : 1
					}, {
						text : 'size',
						max : 1
					}, {
						text : 'issues',
						max : 1
					}, {
						text : 'contributor',
						max : 1
					}, {
						text : 'subscribers',
						max : 1
					} ],
					center : [ '50%', '50%' ],
					radius : 90,
					startAngle : 90,
					splitNumber : 4,
					shape : 'circle',
					name : {
						formatter : '【{value}】',
						textStyle : {
							color : '#72ACD1'
						}
					},
					splitArea : {
						areaStyle : {
							color : [ 'rgba(255, 70, 131, 0.2)',
									'rgba(255, 70, 131, 0.4)',
									'rgba(255, 70, 131, 0.6)',
									'rgba(255, 70, 131, 0.8)',
									'rgba(255, 70, 131, 1)' ],
							shadowColor : 'rgba(0, 0, 0, 0.3)',
							shadowBlur : 10
						}
					},
					axisLine : {
						lineStyle : {
							color : 'rgba(255, 255, 255, 0.5)'
						}
					},
					splitLine : {
						lineStyle : {
							color : 'rgba(255, 255, 255, 0.5)'
						}
					}
				},
			],
			series : [
				{
					name : '雷达图',
					type : 'radar',
					tooltip : {
						trigger : 'item'
					},
					itemStyle : {
						normal : {
							areaStyle : {
								type : 'default'
							}
						},
						emphasis : {
							// color: 各异,
							lineStyle : {
								width : 4
							}
						}
					},
					data : [
					{
						value : [ data.star, data.fork, data.size,
								data.issue, data.contributor,
								data.subscriber ],
						name : 'scores',
						areaStyle : {
							normal : {
								color : 'rgba(255, 255, 255, 0.5)'
							}
						}
					} ]
				}, 
			]
		};
		myChart.setOption(option);
	}
});
var url2 = "/Gitmining/repostatics/activities?full_name=" + repositoryName;
$.ajax(url2, {
	type : 'GET',
	async : false,
	success : function(data, textStatus) {
		var myChart = echarts.init(document.getElementById('repoactivity'));

		var obj = JSON.stringify(data);

		var jsObj = eval("(" + obj + ")");

		var date = [];

		var datas = [ Math.random() * 300 ];

		for ( var key in jsObj) {
			date.push(key);
			datas.push(jsObj[key]);
		}
		date = date.sort(function(a, b) {
			return (new Date(a.replace(/-/g, '/')).getTime() - new Date(b
					.replace(/-/g, '/')).getTime());
		});

		option = {
			tooltip : {
				trigger : 'axis'
			},
			title : {
				left : 'center',
				text : 'Repo Activities',
			},
			legend : {
				top : 'bottom',
				data : [ '意向' ]
			},
			toolbox : {
				show : true,
				feature : {
					dataView : {
						show : false,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'bar' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
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
				end : 10
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
						color : new echarts.graphic.LinearGradient(0, 0, 0, 1,
								[ {
									offset : 0,
									color : 'rgb(255, 158, 68)'
								}, {
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