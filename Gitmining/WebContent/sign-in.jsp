<!doctype html>
<html lang="en">
<head>
<jsp:include page="head.jsp" flush="true" />
<title>Register</title>
<style type="text/css">
input:-webkit-autofill {
	-webkit-box-shadow: 0 0 0px 1000px #ffffff inset !important;
}

input.error::-webkit-input-placeholder {
	color: #F9684B;
}

input.error:-moz-placeholder {
	color: #F9684B;
}

input.error::-moz-placeholder {
	color: #F9684B;
}

input.error:-ms-input-placeholder {
	color: #F9684B;
}
</style>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="sidebar.jsp" flush="true" />

		<div class="main-panel">
			<jsp:include page="topbar.jsp" flush="true" />

			<div class="register-background">
				<div class="container">
					<div class="row">
						<div
							class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1 ">
							<div class="register-card">
								<h3 class="title">Welcome</h3>
								<form id="form" name="form" class="register-form" method="post"
									action="/Gitmining/log/login">
									<label>Login</label> <input name="login" type="text"
										class="form-control" placeholder="Login"> <label>Password</label>
									<input name="password" type="password" class="form-control"
										placeholder="Password">
									<button class="btn btn-danger btn-block"
										onclick="submitForm();return false;">Log In</button>
								</form>
								<div class="forgot">
									<a href="https://github.com/password_reset"
										class="btn btn-simple btn-danger" target="_blank">Forgot Password?</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function submitForm() {
		var log = form.login.value;
		var pass = form.password.value;
		if (log.length > 0 && pass.length > 0) {
			$.ajax({
				url : form.action,
				type : form.method,
				data : {
					login : log,
					password : pass
				},
				async : false,
				dataType : 'json',
				success : function(msg) {
					if (msg == "success") {
						self.location = document.referrer;
					} else if (msg == "new") {
						window.location.href = "/Gitmining/welcome";
					} else {
						$.notify({
							icon : 'ti-github',
							message : "Login and password do not match.Please input again."
						}, {
							type : 'danger',
							timer : 200,
							placement : {
								from : 'top',
								align : 'center'
							}
						});
						form.password.value = "";
					}
				}
			});
		} else {
			if (log.length == 0) {
				form.login.placeholder = "Please Input Login.";
				form.login.className += " error";
			} else {
				form.password.placeholder = "Please Input Password.";
				form.password.className += " error";
			}
		}
	}
</script>
</html>