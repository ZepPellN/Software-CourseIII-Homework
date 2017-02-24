//===================================================================picture.js=============================================================================
var MAX_LEVEL = 1;
var login;
var level;					//从profile获取
var horizontal = [3, 4, 5];
var vertical = [3,4,5];
var bg_width = [600, 514, 800];
var bg_height = [600, 600, 533];
var width, height;
var width_frag, height_frag;
var bg_img_src;
var opacity = [];
var learninglist = [];	
var recommendlist = [];
var sum_of_complex_i = 0;

function loadPic(){
	//initialize learninglist[]
	for(var i=0; i<level; i++){
		learninglist[i] = [];
		opacity[i] = [];
	}
	
	for(var k=0; k<level; k++){
		for(var i=0; i<horizontal[k]*vertical[k]; i++){
			opacity[k][i]=1;		//non-transparent
		}
	}
	
	//$.ajax() get level
	url_rec = "/Gitmining/log/recommend";
	$.ajax(url_rec, {
		type: 'GET',
		async: false,
		success: function(data, textStatus){
			if (!isEmptyObj(data)) {
				recommendlist = data;
			}
		}
	})
	
	var overview = $("#level-thumbnail");
	overview.empty();

	//获取所有level的learning信息
	for(var i=1; i<=level; i++){
		url_learning = "/Gitmining/log/learning?level=" + i;
		$.ajax(url_learning, {
			type: 'GET',
			async: false,
			success: function(data, textStatus){
				if(!isEmptyObj(data)){
					
					//计算每个level的complex总数 >= block总数则显示全图
					var sum_of_complex = 0;
					for(var k=0; k<data.length; k++){
						var temp = data[k];
						if(temp.finish)
							sum_of_complex += temp.complex;
						learninglist[i-1][temp.block-1] = temp;		//learninglist其他为空值
						if(temp.complex==2)
							learninglist[i-1][temp.block] = temp;
						if(temp.complex==3){
							learninglist[i-1][temp.block] = temp;
							learninglist[i-1][temp.block+1] = temp;
						}
					}
					if(sum_of_complex < horizontal[i-1]*vertical[i-1]){ 				//没填满的都要设置透明度
						if(sum_of_complex_i==0)
							sum_of_complex_i = i;
						for(var k=0; k<data.length;k++){
							var temp = data[k];
							var opacity_temp = (temp.finish) ? 0 : getOpacity(temp.count, temp.start, temp.latest, temp.complex);
							opacity[i-1][temp.block-1] = opacity_temp;
							if(temp.complex == 2){
								opacity[i-1][temp.block] = opacity_temp;
							}
							if(temp.complex ==3){
								opacity[i-1][temp.block] = opacity_temp;
								opacity[i-1][temp.block+1] = opacity_temp;
							}
						}
						
						if(i<level){
							var thumbnail = $("<div></div>");
							var a = $("<a></a>");
							a.attr("href", "javascript: reload("+i+", 1)");
							if(i==1)
								a.css("padding-top", "2%");
							var img = $("<img>");
							img.attr("src", "/Gitmining/images/level/current_level.png");
							a.append(img);
							thumbnail.append(a);
							overview.append(thumbnail);
						}
					}
					
					//add thumbnails
					if(sum_of_complex >= horizontal[i-1]*vertical[i-1]){
						for(var j=0; j<horizontal[i-1]*vertical[i-1];j++){
							opacity[i-1][j] = 0;
						}
						var thumbnail = $("<div></div>");
						var a = $("<a></a>");
						a.attr("href", "javascript: reload("+i+", 1)");
						if(i==1)
							a.css("padding-top", "2%");
						var img = $("<img>");
						img.attr("src", "/Gitmining/images/level/lv"+i+".jpg");
						a.append(img);
						thumbnail.append(a);
						overview.append(thumbnail);
						if(i==3)
							MAX_LEVEL = 3;
					}
					
				}else{//data is empty
					if(i<level){
						var thumbnail = $("<div></div>");
						var a = $("<a></a>");
						a.attr("href", "javascript: reload("+i+", 1)");
						if(i==1)
							a.css("padding-top", "2%");
						var img = $("<img>");
						img.attr("src", "/Gitmining/images/level/current_level.png");
						a.append(img);
						thumbnail.append(a);
						overview.append(thumbnail);
						if(sum_of_complex_i==0)
							sum_of_complex_i = i;
					}
					else{
						if(sum_of_complex_i==0)
							sum_of_complex_i = i;
					}
					
				}
			}//callback()
			
		})

		if(i==level && MAX_LEVEL<3){
			//设置当前level的图片，'?'
			var thumbnail = $("<div></div>");
			var a = $("<a></a>");
			a.attr("href", "javascript: reload("+level+", 1)");
			if(i==1)
				a.css("padding-top", "2%");
			var img = $("<img>");
			img.attr("src", "/Gitmining/images/level/current_level.png");
			a.append(img);
			thumbnail.append(a);
			overview.append(thumbnail);
		}

	}
	
	if(sum_of_complex_i==0)
		sum_of_complex_i = 1;
	
	reload(sum_of_complex_i, 0);
}

function reload(level_p, ctrl){
	 //首次传入
	 if (ctrl==0){
		 resetFrag(sum_of_complex_i);
	 }else{
		 resetFrag(level_p);
	 }
}

function setSize(){
	alert(this.width+" : "+this.height);
	width = this.width + 2;
	height = this.height + 2;
	width_frag = (this.width)/horizontal[sum_of_complex_i-1];
	height_frag = (this.height)/vertical[sum_of_complex_i-1];
	resetFrag(sum_of_complex_i);
}

function resetFrag(level_p){
	bg_img_src = "/Gitmining/images/level/lv" + level_p+ ".jpg";
	bg_img_url = "url("+bg_img_src+")";
	width = bg_width[level_p-1]+2;
	height = bg_height[level_p-1]+2;
	width_frag = (width-2)/horizontal[level_p-1];
	height_frag = (height-2)/vertical[level_p-1];
	var picture = $("#panel-wrapper");
	 picture.css("background-image",bg_img_url);
	
	var plist = $("#piece-panel");
	plist.empty();

	$("#bg-div").css({"width":width, "height":height});
	$("#panel-wrapper").css({"width":width, "height":height});
	plist.css({"width":width, "height":height});
	
	for(var i=0; i<horizontal[level_p-1]*vertical[level_p-1]; i++){
		var li = $("<li></li>");
		var opacity_double = opacity[level_p-1][i];
		var	opacity_str = "rgba(255,255,255," + opacity_double + ")";
			
		li.css({"width":width_frag, "height": height_frag, "background-color": opacity_str});
		var a = $("<a></a>");
		a.css({"width":width_frag, "height": height_frag});
		a.attr({"id":"a"+i, "tabindex":"0", "role":"button", "data-toggle":"popover"});
		li.append(a);
		plist.append(li);

		var element = $('#a'+i);
		var id = element.attr('id');
		var txt = element.html();

		element.popover({
			trigger: 'manual',
			placement: (Math.floor(i/horizontal[level_p-1]) <= Math.floor(vertical[level_p-1]/2))?'bottom':'top',
			html: 'true',
			content: contentMethod(i, level_p, opacity_double),
		}).on("mouseenter", function(){
			var _this = this;
			$(this).popover("show");
			$(this).siblings(".popover").on("mouseleave", function(){
				$(_this).popover("hide");
			});
		}).on("mouseleave", function(){
			var _this = this;
			setTimeout(function(){
				if(!$(".popover:hover").length){
					$(_this).popover("hide");
				}
			}, 100);
		});
	}
}

/*根据count*/
function getOpacity(count, start, latest, complex){
	var duration = 2;
	if(complex==2) duration = 4;
	if(complex==3) duration = 7;
	var progress = 0;
	var delta = 0.25;
	var startarr = start.split("-");
	var startDate = new Date(startarr[0], parseInt(startarr[1])-1, startarr[2]);
	var latestarr = latest.split("-");
	var latestDate= new Date(latestarr[0], parseInt(latestarr[1])-1, latestarr[2]);
	var lastfor = (latestDate.getTime() - startDate.getTime())/(1000*60*60*24);
	progress = (lastfor <= duration)? 0 : 0.25;
	delta = (lastfor <= duration)? 0.2 : 0.25;
	for(var i=0; i<count; i++){
		progress += delta;
		delta /= 2;
	}
	return 1-progress;
}

function contentMethod(i, level_p, opacitystr){
	if(level_p<=level){
		var temp = learninglist[level_p-1][i];
		var content = '<div id="'+i+'" style="display:block; min-width: 260px; min-height: 50px; padding: 10px; color:#333 ">';
		if(opacitystr == 1){
			
			content += 'Recommended for you:';
			if(getJsonLength(recommendlist)==0)
				content += '<p class="pop-info">Nothing to recommend yet...</p><span class="pop-reward">Go and explore more repositories!</span>';
			
			for(var i=0; i<getJsonLength(recommendlist);i++){
				var name = recommendlist[i].name;
				var stars = recommendlist[i].stars;
				var forks = recommendlist[i].forks;
				var language = recommendlist[i].language;
				var method = "if(event.button == 2){openGithub('" + name + "');}else if(event.button == 0){refreshHistory('" + name + "');}return false;";
				content += '<a style="color: #E3725D; font-weight:bold;"'+' onmousedown="'+method+ '">'+name+'</a>';
				content += '<div class="row">';
				content += '<div class="col-md-4">'+ 'Stars'+'\ '+'<br><span class="pop-info">'+ stars+'</span>' + '</div>';
				content += '<div class="col-md-4">'+'Forks'+'\   '+'<br><span class="pop-info">'+ forks+'</span>' + '</div>';
				content += '<div class="col-md-4">'+ 'Language'+'\ '+'<br><span class="pop-info">'+ language+'</span>' + '</div>';
				content += '</div>';
				if(i < getJsonLength(recommendlist) ) 
					content += '<hr style="margin: 1px; height:1px;border:none;border-top:1px solid #eee;">';		//分割线
			}
			content += '</div> ';
			return content;
		}
		else{	//当前学习或已完成的项目信息
			
			if(!isEmptyObj(temp)){
				if(temp.finish){
					content += 'You have finished:<br/>';
					var method = "if(event.button == 2){openGithub('" + temp.repo + "');}else if(event.button == 0){refreshHistory('" + temp.repo + "');}return false;";
					content += '<a style="color:#2B9216;font-weight:bold"'+' onmousedown="'+method+'">'+ temp.repo +'</a>';
				}
				else{
					content += 'You are learning: <br/>';
					var method = "if(event.button == 2){openGithub('" + temp.repo + "');}else if(event.button == 0){refreshHistory('" + temp.repo + "');}return false;";
					content += '<a style="color:#3894D5;font-weight:bold"'+' onmousedown="'+method+'">'+ temp.repo +'</a>';
				}
				content += '<div class="row">';
				content += '<div class="col-md-6">'+ 'start from'+'\ '+'<br><span class="pop-info">'+ temp.start+'</span>' + '</div>';
				content += '<div class="col-md-6">'+'last visit'+'\   '+'<br><span class="pop-info">'+ temp.latest+'</span>' + '</div>';
				content += '</div>';
				content += 'during which you have'+'\ '+'<span class="pop-info">'+temp.commits+'</span>'+'\ commits' + '<br>';
				
				if(temp.commits==0)
					content += '<span class="pop-reward">Commit reflects learning :-)<span/>';
				if(temp.commits>0 && temp.commit<3)
					content += '<span class="pop-reward">NICE TRY!<span/>';
				if(temp.commits>=3 && temp.commits<10)
					content += '<span class="pop-reward">GREAT STREAK!<span/>';
				if(temp.commits>=10)
					content += '<span class="pop-reward">EXCELLENT JOB!<span/>';
				
			}
			content += '</div> ';
			return content;
		}
	}
}

function closeDiv(i){
	$("#a"+i).popover("hide");
}

function isEmptyObj(obj){
	for(var i in obj){
		return false;
	}
	return true;
}

function getJsonLength(data) {
	var length = 0;
	for ( var item in data) {
		length++;
	}
	return length;
}
//============================================================================picture.js=============================================================================================
//
//
//
//
//
//============================================================================profile.js=============================================================================================
function loadProfile (){
	var url = "/Gitmining/log/show";
	var log_login, log_avatar, log_span, log_grade, log_history, log_CLR, log_FLR, log_BRH;
	var log_levels = new Array();
	log_levels[0] = "A";
	log_levels[1] = "S";
	log_levels[2] = "SSS";

	$.ajax(url,{
		type : 'GET',
		async : false,
		success:function(data,textStatus){
			var obj = JSON.stringify(data);
			var jsObj =  eval ("(" + obj + ")");
			log_login = jsObj.login;
			login = log_login;
			log_avatar = jsObj.avatar;
			log_span = jsObj.span;
			log_level = log_levels[jsObj.aptitute - 1];
			level = jsObj.aptitute;														//global variable 'level'
			log_grade = jsObj.grade;
			log_CLR = 0;
			log_FLR = 0;
			log_BRH = 0;
			log_history = jsObj.history;
			for (var i = 0; i < log_history.length; i++) {
				log_BRH ++;
				if(log_history[i].start != null){
					if(log_history[i].finish == null){
						log_CLR ++;
					}else{
						log_FLR ++;
					}
				}
			}
		}
	});
	

	$("#log_avatar").attr("src", log_avatar);
	$("#log_login").text(log_login);
	$("#log_span").text(log_span);
	$("#log_level").text(log_level);
	$("#log_grade").text(log_grade);
	$("#log_CLR").text(log_CLR);
	$("#log_FLR").text(log_FLR);
	$("#log_BRH").text(log_BRH);

	$("#log_evaluation").text(loadEvaluation(log_span, log_grade, log_BRH));
}


//=============================================================================== profile.js ==================================================================================
//
//
//
//
//================================================================================ rank.js ====================================================================================
function loadRank (){
	var url = "/Gitmining/log/rank";

	$.ajax(url,{
		type : 'GET',
		async : false,
		dataType: "json",
		success:function(data){
			for (var i = 0; i < data.length; i++) {
				var content = $("<tr></tr>");
				content.attr("height", "60px");
				content.css("font-size", "18px");
				var link = $("<a target='_blank'></a>");
				if(data[i].login == login){
					content.css("color","#E3725D");
					link.attr("style", "color:#E3725D;");
				}

				var rankbar = $("<td></td>");
				rankbar.text(i + 1);
				var loginbar = $("<td></td>");
				link.attr("href", "https://github.com/"+data[i].login);
				link.text(data[i].login);
				loginbar.append(link);

				var agebar = $("<td></td>");
				agebar.text(data[i].span + " days");

				var levelbar = $("<td></td>");
				levelbar.text(data[i].aptitute);

				var gradebar = $("<td></td>");
				gradebar.text(data[i].grade);

				content.append(rankbar, loginbar, agebar, levelbar, gradebar);
				$("#rank-list").append(content);
			}
		}
	});
}
//============================================================================= rank.js =======================================================================================
