//加载用户信息
$(function(){
	$(document).ready(function(){
		var url = "/Gitmining/user/show?login="+UserName;
		var url_eva="/Gitmining/userstatics/assess?login="+UserName;
		var user_login,head,user_name,date,mail,blog,company,address,bio,activity,enthusiasm,popularity,relatedRepo,popularRepo;

		$.ajax(url,{
			type : 'GET',
			async : false,
			success:function(data,textStatus){
				var obj = JSON.stringify(data);
				var jsObj =  eval ("(" + obj + ")");
				user_login = jsObj.login;
				head = jsObj.avatar_url;
				user_name = jsObj.name;
				date = jsObj.created_at;
				mail = jsObj.email;
				blog = jsObj.blog;
				bio = jsObj.bio;
				company = jsObj.company;
				address = jsObj.location;
				relatedRepo = jsObj.reposName;
				popularRepo = jsObj.reposName_HOT;
			}
		});
		$("#avatar").attr("src", head);
		$("#avatar_link").attr("href", "https://github.com/" + user_login);
		$("#user_login").attr("value", user_login);
		$("#names").attr("value", user_name);
		$("#date").attr("value", date);
		if(mail!=null){
			$("#mail").attr("value", mail);
		}
		if(blog!=null){
			$("#blog").attr("value", blog);
		}
		if(company!=null){
			$("#company").attr("value", company);
		}
		if(address!=null){
			$("#address").attr("value", address);
		}
		if(bio!=null){
			$("#bio").attr("value", bio);
		}
		$.ajax(url_eva,{
			type : 'GET',
			async : false,
			success:function(data,textStatus){
				var obj = JSON.stringify(data);
				var jsObj =  eval ("(" + obj + ")");
				activity = jsObj.activity;
				enthusiasm = jsObj.enthusiasm;
				popularity = jsObj.popularity;
			}
		});
		$("#n_1").html(Math.round(activity*100));
		$("#n_2").html(Math.round(enthusiasm*100));
		$("#n_3").html(Math.round(popularity*100));

		$("#related_ul").empty();
		for(var x in relatedRepo){	
			var txt=$("<a class='col-md-offset-1'></a>").text(relatedRepo[x]);
			txt.attr("onmousedown", "if(event.button == 2){openGithub('" + relatedRepo[x] + "');}else if(event.button == 0){refreshHistory('" + relatedRepo[x] + "');}return false;");			
			for(var y in popularRepo){
				if(relatedRepo[x]==relatedRepo[y]){
					txt.attr("style", "color: #F9684B;");
					break;
				}
			}
			var single=$("<li></li>");
			single.append(txt);
			$("#related_ul").append(single); 
		}
	});
});