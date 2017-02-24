$(document).ready(function(){
	var url = "login/recommend";
	var arr = new Array();
	$.ajax(url,{
		type:'GET',
		async:'false',
		success:function(data,textStatus){
			arr = data.recommendRepos;
		}
		
	});
	$("#firstA").attr("href",
			"repository.jsp?reponame=" + arr[0].reco_name)
			.html(arr[0].reco_name);
	$("#secondA").attr("href",
			"repository.jsp?reponame=" + arr[1].reco_name)
			.html(arr[1].reco_name);
	$("#thirdA").attr("href",
			"repository.jsp?reponame=" + arr[2].reco_name)
			.html(arr[2].reco_name);
	$("#forthA").attr("href",
			"repository.jsp?reponame=" + arr[3].reco_name)
			.html(arr[3].reco_name);
	$("#fifthA").attr("href",
			"repository.jsp?reponame=" + arr[4].reco_name)
			.html(arr[4].reco_name);
	$("#sixthA").attr("href",
			"repository.jsp?reponame=" + arr[5].reco_name)
			.html(arr[5].reco_name);
	$("#sevenA").attr("href",
			"repository.jsp?reponame=" + arr[6].reco_name)
			.html(arr[6].reco_name);
	$("#eightA").attr("href",
			"repository.jsp?reponame=" + arr[7].reco_name)
			.html(arr[7].reco_name);
	$("#nineA").attr("href",
			"repository.jsp?reponame=" + arr[8].reco_name)
			.html(arr[9].reco_name);
	$("#tenA").attr("href",
			"repository.jsp?reponame=" + arr[9].reco_name)
			.html(arr[9].reco_name);
	$("#firstC").attr("href", "heat.jsp?name="+repositoryName+"&&recommend="+arr[0].reco_name).html(
			"Potential relevant: " + arr[0].relate_count);
	$("#secondC").attr("href", "heat.jsp?name="+repositoryName+"&&recommend="+arr[1].reco_name).html(
			"Potential relevant: " + arr[1].relate_count);
	$("#thirdC").attr("href",  "heat.jsp?name="+repositoryName+"&&recommend="+arr[2].reco_name).html(
			"Potential relevant: " + arr[2].relate_count);
	$("#forthC").attr("href",  "heat.jsp?name="+repositoryName+"&&recommend="+arr[3].reco_name).html(
			"Potential relevant: " + arr[3].relate_count);
	$("#fifthC").attr("href",  "heat.jsp?name="+repositoryName+"&&recommend="+arr[4].reco_name).html(
			"Potential relevant: " + arr[4].relate_count);
	$("#sixthC").attr("href",  "heat.jsp?name="+repositoryName+"&&recommend="+arr[5].reco_name).html(
			"Potential relevant: " + arr[5].relate_count);
	$("#sevenC").attr("href",  "heat.jsp?name="+repositoryName+"&&recommend="+arr[6].reco_name).html(
			"Potential relevant: " + arr[6].relate_count);
	$("#eightC").attr("href",  "heat.jsp?name="+repositoryName+"&&recommend="+arr[7].reco_name).html(
			"Potential relevant: " + arr[7].relate_count);
	$("#nineC").attr("href",  "heat.jsp?name="+repositoryName+"&&recommend="+arr[8].reco_name).html(
			"Potential relevant: " + arr[8].relate_count);
	$("#tenC").attr("href",  "heat.jsp?name="+repositoryName+"&&recommend="+arr[9].reco_name).html(
			"Potential relevant: " + arr[9].relate_count);
	
});