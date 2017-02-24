<!doctype html>
<html lang="en">
<%
String login = "";
if(session.getAttribute("login") != null){
	login = (String)session.getAttribute("login");
}
String isNew = "T";
if(session.getAttribute("isNew") != null){
	isNew = (String)session.getAttribute("isNew");
}
%>
<head>
    <style type="text/css">
        .dropdown-submenu {
            position: relative;
        }
        .dropdown-submenu > .dropdown-menu {
            width: 14em;
            position: absolute;
            top: 0;
            left: 100%;
            margin-top: 1px;
            margin-left: 1px;
            -webkit-border-radius: 0 6px 6px 6px;
            -moz-border-radius: 0 6px 6px;
            border-radius: 0 6px 6px 6px;
            visibility: hidden;
        }
        .dropdown-submenu:hover > .dropdown-menu {
            position: absolute;
            display: block;
            visibility: visible;
        }
        .dropdown-submenu > a:after {
            display: block;
            content: " ";
            float: right;
            width: 0;
            height: 0;
            border-color: transparent;
            border-style: solid;
            border-width: 5px 0 5px 5px;
            border-left-color: #ccc;
            margin-top: 5px;
            margin-right: -10px;
        }
        .dropdown-submenu:hover > a:after {
            border-left-color: #fff;
        }
        .dropdown-submenu.pull-left {
            float: none;
        }
        .dropdown-submenu.pull-left > .dropdown-menu {
            left: -100%;
            margin-left: 10px;
            -webkit-border-radius: 6px 0 6px 6px;
            -moz-border-radius: 6px 0 6px 6px;
            border-radius: 6px 0 6px 6px;
        }
        
        .spinner {
		  padding: 20% 60% 80% 40%;
		  width: 20px;
		  height: 20px;
		  position: fixed;
		  z-index: 8999;
		  background-color: wheat;
		}
		 
		.container1 > div, .container2 > div, .container3 > div {
		  width: 6px;
		  height: 6px;
		  background-color: #333;
		 
		  border-radius: 100%;
		  position: absolute;
		  -webkit-animation: bouncedelay 1.2s infinite ease-in-out;
		  animation: bouncedelay 1.2s infinite ease-in-out;
		  -webkit-animation-fill-mode: both;
		  animation-fill-mode: both;
		}
		 
		.spinner .spinner-container {
		  position: absolute;
		  width: 30px;
		  height: 30px;
		}
		 
		.container2 {
		  -webkit-transform: rotateZ(45deg);
		  transform: rotateZ(45deg);
		}
		 
		.container3 {
		  -webkit-transform: rotateZ(90deg);
		  transform: rotateZ(90deg);
		}
		 
		.circle1 { top: 0; left: 0; }
		.circle2 { top: 0; right: 0; }
		.circle3 { right: 0; bottom: 0; }
		.circle4 { left: 0; bottom: 0; }
		 
		.container2 .circle1 {
		  -webkit-animation-delay: -1.1s;
		  animation-delay: -1.1s;
		}
		 
		.container3 .circle1 {
		  -webkit-animation-delay: -1.0s;
		  animation-delay: -1.0s;
		}
		 
		.container1 .circle2 {
		  -webkit-animation-delay: -0.9s;
		  animation-delay: -0.9s;
		}
		 
		.container2 .circle2 {
		  -webkit-animation-delay: -0.8s;
		  animation-delay: -0.8s;
		}
		 
		.container3 .circle2 {
		  -webkit-animation-delay: -0.7s;
		  animation-delay: -0.7s;
		}
		 
		.container1 .circle3 {
		  -webkit-animation-delay: -0.6s;
		  animation-delay: -0.6s;
		}
		 
		.container2 .circle3 {
		  -webkit-animation-delay: -0.5s;
		  animation-delay: -0.5s;
		}
		 
		.container3 .circle3 {
		  -webkit-animation-delay: -0.4s;
		  animation-delay: -0.4s;
		}
		 
		.container1 .circle4 {
		  -webkit-animation-delay: -0.3s;
		  animation-delay: -0.3s;
		}
		 
		.container2 .circle4 {
		  -webkit-animation-delay: -0.2s;
		  animation-delay: -0.2s;
		}
		 
		.container3 .circle4 {
		  -webkit-animation-delay: -0.1s;
		  animation-delay: -0.1s;
		}
		 
		@-webkit-keyframes bouncedelay {
		  0%, 80%, 100% { -webkit-transform: scale(0.0) }
		  40% { -webkit-transform: scale(1.0) }
		}
		 
		@keyframes bouncedelay {
		  0%, 80%, 100% {
		    transform: scale(0.0);
		    -webkit-transform: scale(0.0);
		  } 40% {
		    transform: scale(1.0);
		    -webkit-transform: scale(1.0);
		  }
		}
    </style>
</head>

<body onload="closeSpinner();">
    <nav class="navbar navbar-default navbar-transparent" style="position: fixed; width: 80%">
        <div class="container-fluid">
            <ul id="topbar" class="nav navbar-nav navbar-right">
            </ul>
        </div>
    </nav>
    
    <div id="spinner" class="spinner">
	  <div class="spinner-container container1">
	    <div class="circle1"></div>
	    <div class="circle2"></div>
	    <div class="circle3"></div>
	    <div class="circle4"></div>
	  </div>
	  <div class="spinner-container container2">
	    <div class="circle1"></div>
	    <div class="circle2"></div>
	    <div class="circle3"></div>
	    <div class="circle4"></div>
	  </div>
	  <div class="spinner-container container3">
	    <div class="circle1"></div>
	    <div class="circle2"></div>
	    <div class="circle3"></div>
	    <div class="circle4"></div>
	  </div>
	</div>
</body>
<script type="text/javascript">

function closeSpinner(){
	$("#spinner").hide();
}
   
$(document).ready(function(){
	var myLogin = "<%=login%>";
	var isNew = "<%=isNew%>";
	
	if(myLogin != ""){
      	if(isNew == "F"){
   		var action = "/Gitmining/log/history";
       	$.ajax({
               type: "GET",
               url: action,
               async : false,
               dataType: "json",
               success: function(data){
             	if(data != null && data.length > 0){
             		$("#topbar").append('<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"><p>History<span class="caret"></span></p></a><ul id="historylist" class="dropdown-menu"></ul></li>');
           		}
               	for(var i = 0; i < data.length; i++){
               		var tag = $("<a>" + data[i].repo + "</a>");
               		tag.attr("onmousedown", "if(event.button == 2){openGithub('" + data[i].repo + "');}else if(event.button == 0){refreshHistory('" + data[i].repo + "');}return false;");
               		var submenu = $("<ul class='dropdown-menu'></ul>");
                          submenu.append($("<li><a>Browser Time: "+ data[i].count + "</a></li>"));
                          submenu.append($("<li><a>Last Browser: "+ data[i].latest + "</a></li>"));
                          submenu.append($("<li><a>New Commits: "+ data[i].commits + "</a></li>"));
                          submenu.append($("<li><a>Complexity: "+ data[i].complex + "</a></li>"));
                          var link = $("<li><a>Delete History</a></li>");
                          link.attr("onclick", "deleteHistory('" + data[i].repo + "');");
                          var menu = $("<li class='dropdown-submenu'></li>");
                          submenu.append(link);
                          menu.append(tag, submenu);
                          $("#historylist").append(menu);
               	}
               	var tag = $("<li><a onclick='clearHistory();'>Clear History</a></li>");
               	$("#historylist").append(tag);
               }
           });
           $("#topbar").append('<li><a href="/Gitmining/analysis"><p>Analysis</p></a></li>');
      	}
        $("#topbar").append('<li><a onclick="signout();"><i>' + myLogin + '</i></a></li>');
		}else{
            if(window.location.pathname != ("/Gitmining/register")){
                $("#topbar").append($('<li><a href="/Gitmining/register"><p>Sign In</p></a></li>'));
            }
            $("#topbar").append($('<li><a href="https://github.com/join?source=header" target="_blank"><p>Sign Up</p></a></li>'));
		}
     });
     
     function signout(){
    	 var action = "/Gitmining/log/logout";
         $.ajax({
             type: "GET",
             url: action,
             async : false,
             success: function(msg){
            	 if(window.location.pathname == "/Gitmining/analysis" || window.location.pathname == "/Gitmining/welcome"){
               		window.location.href = "/Gitmining/";
               	 }else{
                    window.location.reload();
               	}
             }
         });
     }

     function deleteHistory(h){
         var action = "/Gitmining/log/delete";
         $.ajax({
             type: "GET",
             url: action,
             data : {
                 full_name : h,
             },
             async : false,
             success: function(msg){
                 window.location.reload();
             }
         });
     }

     function clearHistory(){
         var action = "/Gitmining/log/deleteAll";
         $.ajax({
             type: "GET",
             url: action,
             async : false,
             success: function(msg){
                 window.location.reload();
             }
         });
     }
 </script>
</html>