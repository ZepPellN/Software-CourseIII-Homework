/**
 * deal with results of repos and users
 */

	var recordNum = 15;		
	var numOfRepo, numOfUser;
	var repolist=[];
	var userlist=[];
	var pages, curP, allpages;
	var pages_user, curP_user, allpages_user;

	$(document).ready(function(){
		obj = document.getElementById("frameContent");
		pages = document.getElementById("pages");
		curP = 0;
		allpages = 0;
		pages_user = document.getElementById("pages_user");
		curP_user = 0;
		allpages_user = 0;
		
		searchVague(vagueName);
		
	})
	
	function gotopage(value){
		
		if(value=="-1"&&curP>0){
			curP--;
			writePage(curP);
		}
		if(value=="1"&& curP<allpages-1){
			curP++;
			writePage(curP);
		}
		
	}
	function gotopage_user(value){
		if(value=="-1"&&curP_user>0){
			curP_user--;
			writeUser(curP_user);
		}
		if(value=="1"&& curP_user<allpages_user-1){
			curP_user++;
			writeUser(curP_user);
		}
		
	}
	function getJsonLength(data){
		var length = 0;
		for(var item in data){
			length++;
		}
		return length;
	}
	function loadRepo(){
//		alert("loadRepo");
		
		$("#repo-num").text("We have found "+numOfRepo+" repository results");
		if(numOfRepo == 0) return;
		
		
		writePage(0);
		
		
		
//		alert(allpages + " pages");
//		
//		alert("pass");
		
		pages.innerHTML =	"<a href=\"javascript:gotopage('-1');\">last page</a>";
		
		
		pages.innerHTML += "   a total of "+allpages + " pages   ";
		
		pages.innerHTML += " <a href=\"javascript:gotopage('1');\"> next page</a>";
		
	}
	
	/*show 15 repositories each page*/
	function writePage(pno){
		$("#repolist").empty();
		var i, temp, name, stars, forks, last_update;
		
		var stars_svg = $("<svg></svg>");
		stars_svg.attr({"class": "octicon octicon-star", "height": "16", "version": "1.1", "viewBox": "0 0 14 16", "width": "14"});
		stars_svg.attr("aria-hidden", "true");
		
		var stars_path = $("<path></path>");
		stars_path.attr("d", "M14 6l-4.9-0.64L7 1 4.9 5.36 0 6l3.6 3.26L2.67 14l4.33-2.33 4.33 2.33L10.4 9.26 14 6z");
		stars_svg.append(stars_path);
		
		var forks_svg = $("<svg></svg");
		forks_svg.attr({"aria-hidden":"true", "class":"octicon octicon-git-branch", "height":"16", "version":"1.1", "viewBox":"0 0 10 16", "width":"10"});
		var forks_path = $("<path></path");
		forks_path.attr("d", "M10 5c0-1.11-0.89-2-2-2s-2 0.89-2 2c0 0.73 0.41 1.38 1 1.72v0.3c-0.02 0.52-0.23 0.98-0.63 1.38s-0.86 0.61-1.38 0.63c-0.83 0.02-1.48 0.16-2 0.45V4.72c0.59-0.34 1-0.98 1-1.72 0-1.11-0.89-2-2-2S0 1.89 0 3c0 0.73 0.41 1.38 1 1.72v6.56C0.41 11.63 0 12.27 0 13c0 1.11 0.89 2 2 2s2-0.89 2-2c0-0.53-0.2-1-0.53-1.36 0.09-0.06 0.48-0.41 0.59-0.47 0.25-0.11 0.56-0.17 0.94-0.17 1.05-0.05 1.95-0.45 2.75-1.25s1.2-1.98 1.25-3.02h-0.02c0.61-0.36 1.02-1 1.02-1.73zM2 1.8c0.66 0 1.2 0.55 1.2 1.2s-0.55 1.2-1.2 1.2-1.2-0.55-1.2-1.2 0.55-1.2 1.2-1.2z m0 12.41c-0.66 0-1.2-0.55-1.2-1.2s0.55-1.2 1.2-1.2 1.2 0.55 1.2 1.2-0.55 1.2-1.2 1.2z m6-8c-0.66 0-1.2-0.55-1.2-1.2s0.55-1.2 1.2-1.2 1.2 0.55 1.2 1.2-0.55 1.2-1.2 1.2z");
		forks_svg.append(forks_path);
		
		for(i=pno*recordNum; i<(pno+1)*recordNum&&i<numOfRepo;i++){
			
			temp = repolist[i];
			name = temp.name;
			stars = temp.stars;
			forks = temp.forks;
			last_update = temp.last_update;
			
			
			var content = $("<li></li>");
			content.attr("class", "repo-list-item");
			
			var stars_text = $("<a></a>");
			stars_text.attr("class", "repo-list-stat-item tooltipped tooltipped-s");
			stars_text.text(stars);
			
			var forks_text = $("<a></a>");
			forks_text.attr("class", "repo-list-stat-item tooltipped tooltipped-s");
			forks_text.text(forks);
			
			var right_div = $("<div></div>");
			right_div.attr("class", "repo-list-stats");
			right_div.append(stars_text, forks_text);
			
			var full_name=$("<a></a>").text(name);
			full_name.attr("href","repository.jsp?reponame="+name);
			var full_name_h3 = $("<h3></h3>");
			full_name_h3.attr("class","repo-list-name");
			full_name_h3.append(full_name);
			
			var create_at_text = $("<p></p>").text("Updated on "+last_update);
			create_at_text.attr("class", "repo-list-meta");
			
			content.append(right_div, full_name_h3, create_at_text);
			
			$("#repolist").append(content);
		}
	}
	function loadUser(){
		
		$("#user-num").text("We have found "+numOfUser+" users");
		if(numOfUser == 0) return;
		
		writeUser(0);
		
		pages_user.innerHTML =	"<a href=\"javascript:gotopage_user('-1');\">last page</a>";
		
		
		pages_user.innerHTML += "   a total of "+allpages + " pages   ";
		
		pages_user.innerHTML += " <a href=\"javascript:gotopage_user('1');\"> next page</a>";
	}
	
	function writeUser(pno){
		$("#userlist").empty();
		
		var i, temp, avatar_url, created_at, email, location, login;
		for(i=pno*recordNum; i<(pno+1)*recordNum&&i<numOfUser;i++){
			temp = userlist[i];
			avatar_url = temp.avatar_url;
			created_at = temp.created_at;
			email = temp.email;
			location = temp.location;
			login = temp.login;
			
			var content = $("<div></div>");
			content.attr("class", "user-list-item");
			
			var avatar = $("<a></a>");
			avatar.attr("href", "userpage.jsp?username="+login);
			var img = $("<img>");
			img.attr("src", avatar_url);
			img.attr({"class":"avatar", "height":"48", "width":"48"});
			avatar.append(img);
			
			var info = $("<div></div>");
			info.attr("class", "user-list-info");
			var userName = $("<a></a>").text(login);
			userName.attr("href", "userpage.jsp?username="+login);
			userName.attr({"class":"user-list-name", "style":"margin: 8px 0 0 20px"});
			var small_text = $("<ul></ul>");
			small_text.attr("class", "user-list-meta text-small text-muted");
			var location_text = $("<li></li>").text(location);
			var email_text= $("<li></li>").text(email);
			var join_text = $("<li></li>").text(created_at);
			
			if(typeof(location)!="object") small_text.append(location_text);
			if(typeof(email)!="object") small_text.append(email_text);
			if(typeof(join)!="object") small_text.append(join_text);
			
//			small_text.append(location_text, email_text, join_text);
			
			info.append(userName, small_text);
			
			content.append(avatar, info);
			
			$("#userlist").append(content);
		}
	}
	
	function searchVagueController(){
		$("#search-btn").blur();
		var keyword = $("#search-keyword").val();
		searchVague(keyword);
	}
	
	function searchVague(keyword){
		
//		alert(keyword);
		if(keyword == ""){
			alert("Please enter something...");
		}
		else{
			var repo_url = "repository/search?keyword="+keyword;
			
			$.ajax(repo_url,{
				type: 'GET',
				async: false,
				success: function(data, textStatus){
					
					if(typeof(data)!="string"){
						numOfRepo = getJsonLength(data);
						allpages = Math.ceil(numOfRepo/recordNum);
//						var upperBound = (numOfRepo > 10) ? 10: numOfRepo;
						for(var i=0; i<numOfRepo; i++){
							repolist[i] = data[i];
						}
					}
				}
			});
			loadRepo();
			
			var user_url = "user/search?keyword="+keyword;
			
			$.ajax(user_url,{
				type: 'GET',
				async:false,
				success: function(data, textStatus){
					if(typeof(data)!="string"){
						numOfUser = getJsonLength(data);
						allpages_user = Math.ceil(numOfUser/recordNum);
						var upperBound = (numOfUser > 13) ? 13: numOfUser;
						for(var i=0; i<numOfUser; i++){
							userlist[i] = data[i];
						}
					}
				}
			});
			loadUser();
			
		}
	}
	
	function sort(sortType){
		var url = "repository/sort?type="+ sortType;
//		var repolist=[];
		$.ajax(url, {
			type:'GET',
			async: false,
			success: function(data, textStatus){
				if(typeof(data)!="string"){
					numOfRepo = getJsonLength(data);
//					allpages = Math.ceil(numOfRepo/recordNum);
					var upperBound = (numOfRepo > 10) ? 10: numOfRepo;
					for(var i=0; i<numOfRepo; i++){
						repolist[i] = data[i];
					}
				}
			}
		});
		loadRepo();
	}
	
