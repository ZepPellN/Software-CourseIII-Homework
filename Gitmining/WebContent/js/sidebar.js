var lan = document.getElementsByName("language");
var cat = document.getElementsByName("category");
var yea = document.getElementsByName("year");
var l, c, y;

function changeRepo() {
	if (user_keyword.value.length == 0) {
		user_keyword.placeholder = "User Search";
		user_keyword.className = "form-control border-input";
	}
	if (repo_keyword.value.length == 0) {
		repo_keyword.placeholder = "Please input.";
		repo_keyword.className += " error";
		return false;
	} else {
		repo_keyword.className = "form-control border-input";
		return true;
	}
}
function changeUser() {
	if (repo_keyword.value.length == 0) {
		repo_keyword.placeholder = "Repository Search";
		repo_keyword.className = "form-control border-input";
	}
	if (user_keyword.value.length == 0) {
		user_keyword.placeholder = "Please input.";
		user_keyword.className += " error";
		return false;
	} else {
		user_keyword.className = "form-control border-input";
		return true;
	}
}
function changeLanguage(cur) {
	for (var i = 0; i < lan.length; i++) {
		lan[i].style.backgroundColor = "transparent";
	}
	cur.style.backgroundColor = "#F9684B";
	l = cur.text;
	searchRepo();
}
function changeCategory(cur) {
	for (var i = 0; i < cat.length; i++) {
		cat[i].style.backgroundColor = "transparent";
	}
	cur.style.backgroundColor = "#F9684B";
	c = cur.text;
	searchRepo();
}
function changeYear(cur) {
	for (var i = 0; i < yea.length; i++) {
		yea[i].style.backgroundColor = "transparent";
	}
	cur.style.backgroundColor = "#F9684B";
	y = cur.text;
	searchRepo();
}
function clearColor() {
	for (var i = 1; i < lan.length; i++) {
		lan[i].style.backgroundColor = "transparent";
	}
	for (var i = 1; i < cat.length; i++) {
		cat[i].style.backgroundColor = "transparent";
	}
	for (var i = 1; i < yea.length; i++) {
		yea[i].style.backgroundColor = "transparent";
	}
	lan[0] = "#F9684B";
	cat[0] = "#F9684B";
	yea[0] = "#F9684B";
	l = "All";
	c = "All";
	y = "All";
	searchRepo();
}
function searchRepo() {
	n = repo_keyword.value;

	var h = "/Gitmining/search/repository/";
	if (n != "") {
		h += n + "/";
	}
	if (l != "All") {
		h += "language/" + l + "/";
	}
	if (c != "All") {
		h += "category/" + c + "/";
	}
	if (y != "All") {
		h += "year/" + y + "/";
	}
	window.location.href = h.substring(0, h.length - 1);
}
function searchUser() {
	n = user_keyword.value;
	window.location.href = "/Gitmining/search/user/"+ n;
}

function Hide() {
	$("#language").collapse("hide");
	$("#category").collapse("hide");
	$("#year").collapse("hide");
}
function OpenLanguage() {
	$("#language").collapse("toggle");
	$("#category").collapse("hide");
	$("#year").collapse("hide");
}
function OpenCategory() {
	$("#language").collapse("hide");
	$("#category").collapse("toggle");
	$("#year").collapse("hide");
}
function OpenYear() {
	$("#language").collapse("hide");
	$("#category").collapse("hide");
	$("#year").collapse("toggle");
}