$(function(){
	$(document).ready(function(){
		alert("!!");
		var xmlhttp; 
		function startrefresh(){ 
		xmlhttp=new XMLHttpRequest(); 
		xmlhttp.open("POST","user.jsp",true); 
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded"); 
		//xmlhttp.send("name=wk"); --需要传输参数时增加 
		xmlhttp.onreadystatechange = function(){ 
		if(xmlhttp.readyState == 4) 
		if(xmlhttp.status == 200) 
		document.getElementById("fresh").innerHTML=xmlhttp.responseText; 
		} 
		} 
		alert("hello");
	});
});