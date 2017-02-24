/**
 * deal with results of repos and users
 */
var recordNum = 15;
var numOfRepo = 0;
var repolist = [];
var pages, curP = 0, allpages = 0;

function gotopage(value) {
	if (value == "-1" && curP > 0) {
		curP--;
		writePage(curP);
	}
	if (value == "1" && curP < allpages - 1) {
		curP++;
		writePage(curP);
	}
}

function getJsonLength(data) {
	var length = 0;
	for ( var item in data) {
		length++;
	}
	return length;
}

function loadRepo() {
	$("#repo-list").empty();
	$("#repo-num").text("We have found " + numOfRepo + " repository results");
	if (numOfRepo == 0) {
		$("#pages").text("0/0");
		return;
	}

	writePage(0);
}

function writePage(pno) {
	if(allpages>0)
		$("#pages").text((curP + 1) + "/" + allpages);
	$("#repo-list").empty();
	var i, temp, name, stars, forks, last_update, description, homepage, language;

	for (i = pno * recordNum; i < (pno + 1) * recordNum && i < numOfRepo; i++) {
		temp = repolist[i];
		homepage = temp.homepage;
		description = temp.description;
		language = temp.language;
		name = temp.name;
		stars = temp.stars;
		forks = temp.forks;
		last_update = temp.last_update;

		var content = $("<tr></tr>");
		content.attr("height", "60px");
		content.attr("font-size", "18px");

		var languagebar = $("<td></td>");
		languagebar.text(language);

		var namebar = $("<td></td");
		var link = $("<a></a>");
		link.attr("onmousedown", "if(event.button == 2){openGithub('" + name + "');}else if(event.button == 0){refreshHistory('" + name + "');}return false;");
		link.text(name);
		namebar.append(link);

		var icontd = $("<td></td>");
		if (homepage != "") {
			var iconlink = $("<a target='_blank'></a>");
			iconlink.attr("style", "color:#1C1B1A");
			iconlink.attr("href", homepage);
			var icon = $("<i></i>");
			icon.attr("class", "ti-more-alt");
			iconlink.append(icon);
			icontd.append(iconlink);
		}

		var stargazersbar = $("<td></td>");
		stargazersbar.text(stars);

		var forksbar = $("<td></td");
		forksbar.text(forks);

		var last_updatebar = $("<td></td>");
		last_updatebar.text(last_update);

		content.append(languagebar, namebar, icontd, stargazersbar, forksbar,
				last_updatebar);

		$("#repo-list").append(content);
	}
}

function searchVague(keyword, language, category, year) {
	curP = 0;
	var repo_url = "/Gitmining/repository/search?repo_key=" + keyword
			+ "&language=" + language + "&category=" + category + "&year="
			+ year;
	$.ajax(repo_url, {
		type : 'GET',
		async : false,
		success : function(data, textStatus) {
			if (!isEmptyObj(data)) {
				numOfRepo = getJsonLength(data);
				allpages = Math.ceil(numOfRepo / recordNum);
				repolist = data;
			}
		}
	});
	loadRepo();
}

function sort(sortType) {
	curP = 0;
	var url = "/Gitmining/repository/sort?type=" + sortType;
	$.ajax(url, {
		type : 'GET',
		async : false,
		success : function(data, textStatus) {
			if (!isEmptyObj(data)) {
				repolist = data;
			}
		}
	});
	loadRepo();
}

function isEmptyObj(obj){
	for(var i in obj){
		return false;
	}
	return true;
}
