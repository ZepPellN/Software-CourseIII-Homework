/**
 * deal with results of repos and users
 */
var vagueName;
var recordNum = 16;
var numOfUser = 0;
var userlist = [];
var pages_user, curP_user = 0, allpages_user = 0;
var randomBG = [];
var TREASURE_MAX = 20;

function generateRandom(){
	for(var i=0; i<TREASURE_MAX; i++){
		randomBG[i] = i;
	}
	 for(var i=TREASURE_MAX; i>TREASURE_MAX-recordNum; i--){
		 var rand_pos = Math.floor(Math.random()*i)+(TREASURE_MAX-i);
		 //swap
		 var temp = randomBG[TREASURE_MAX-i];
		 randomBG[TREASURE_MAX-i] = randomBG[rand_pos];
		 randomBG[rand_pos] = temp;
	 }
}

function gotopage_user(value) {
	if (value == "-1" && curP_user > 0) {
		curP_user--;
		writeUser(curP_user);
	}
	if (value == "1" && curP_user < allpages_user - 1) {
		curP_user++;
		writeUser(curP_user);
	}
}

function getJsonLength(data) {
	var length = 0;
	for ( var item in data) {
		length++;
	}
	return length;
}

function loadUser() {
	$("#user-card-panel").empty();
	$("#user-num").text("We have found " + numOfUser + " users");
	if (numOfUser == 0) {
		$("#pages").text("0/0");
		return;
	}

	writeUser(0);
}

function writeUser(pno) {
	generateRandom();
	$("#user-card-panel").empty();
	$("#pages").text((curP_user + 1) + "/" + allpages_user);

	var i, temp, avatar_url, created_at, email, location, login, blog, bio, followers, following;
	for (i = pno * recordNum; i < (pno + 1) * recordNum && i < numOfUser; i++) {
		temp = userlist[i];
		avatar_url = temp.avatar_url;
		created_at = temp.created_at;
		email = temp.email;
		location = temp.location;
		login = temp.login;
		blog = temp.blog;
		bio = temp.bio;
		followers = temp.followers;
		following = temp.following;

		var content = $("<div></div>");
		content.attr("class", "col-lg-3 col-md-3");

		var card = $("<div></div>");
		card.attr("class", "card card-user");

		var bg = $("<div></div>");
		bg.attr("class", "image");
		var image = $("<img>");
		image.attr("src", "/Gitmining/images/userbg/art"+randomBG[i%recordNum]+".jpg");
		image.attr("alt", "...");
		bg.append(image);

		var info = $("<div></div>");
		info.attr("class", "content");

		var author = $("<div></div>");
		author.attr("class", "author");
		var avatarlink = $("<a></a>");
		avatarlink.attr("href", "/Gitmining/home/user/" + login);
		var avatar = $("<img>");
		avatar.attr({
			"src" : avatar_url,
			"class" : "avatar border-white",
			"style" : "background-color: white;"
		});
		avatarlink.append(avatar);
		var userName = $("<h4></h4>");
		var login_show =(login.length>17)? (login.substring(0,15)+"..."):login;
		var namelink = $("<a></a>").text(login_show);
		namelink.attr("href", "/Gitmining/home/user/" + login);
		namelink.attr("tips", login);
//		namelink.fn.manhua_hoverTips();
		userName.append(namelink);
		userName.attr("class", "title");
		userName.append("<br>");
		var join = $("<a></a>");
		var join_small = $("<small></small>");
		join_small.append("Joined on " + created_at);
		join.append(join_small);
		userName.append(join);
		author.append(avatarlink, userName);

		var stats = $("<div></div>");
		stats.attr("class", "text-center");
		var subrow = $("<div></div>");
		subrow.attr("class", "row");

		var newline1 = $("<br>");
		var newline2 = $("<br>");

		var following_col = $("<div></div>");
		following_col.attr("class", "col-md-5 col-md-offset-1");
		var following_bar = $("<h5></h5>");
		var following_small = $("<small></small>");
		following_small.text("Following");
		following_bar.append(following);
		following_bar.append(newline1, following_small);
		following_col.append(following_bar);

		var followers_col = $("<div></div>");
		followers_col.attr("class", "col-md-5");
		var followers_bar = $("<h5></h5>");
		var followers_small = $("<small></small>");
		followers_small.text("Followers");
		followers_bar.append(followers);
		followers_bar.append(newline2, followers_small);
		followers_col.append(followers_bar);

		subrow.append(following_col, followers_col);
		stats.append(subrow);

		info.append(author, stats);

		card.append(bg, info);

		content.append(card);

		$("#user-card-panel").append(content);
	}
}

function searchVague(keyword) {
	var user_url = "/Gitmining/user/search?user_key=" + keyword;

	$.ajax(user_url, {
		type : 'GET',
		async : false,
		success : function(data, textStatus) {
			if (typeof (data) != "string") {
				numOfUser = getJsonLength(data);
				allpages_user = Math.ceil(numOfUser / recordNum);
				for (var i = 0; i < numOfUser; i++) {
					userlist[i] = data[i];
				}
			}
		}
	});
	loadUser();
}


