<html>
<script src="/Gitmining/assets/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="/Gitmining/assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/Gitmining/paper-kit/assets/js/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
<script src="/Gitmining/assets/js/bootstrap-notify.js"></script>  
<script src="/Gitmining/library/js/echarts.min.js" charset="UTF-8"></script>  
<script type="text/javascript">
document.oncontextmenu = function(e){return false;};

function openGithub(h){
	window.open("https://github.com/" + h);
}

function refreshHistory(h) {    	
	var action = "/Gitmining/repository/preshow";
    $.ajax({
        type: "GET",
        url: action,
        async : false,
        data : {
            full_name : h,
        },
        success: function(msg){
        	if(msg == "success"){
            	window.location.href = "/Gitmining/home/repository/" + h;
        	}
        }
    });
}
</script>
</html>