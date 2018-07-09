<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title></title>
<link rel="stylesheet" href="${ctx}/resources/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/resources/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon">
<script src="${ctx}/resources/jquery/1.11.1/jquery.min.js"></script>
<script src="${ctx}/resources/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${ctx}/resources/jquery-validation/1.11.1/jquery.validate.min.js"></script>
<script src="${ctx}/resources/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<script src="${ctx}/resources/jquery-placeholder/jquery.placeholder.min.js"></script>



<style type="text/css">
body {
	background-color: #fff;
}

.login-box {
	width: 500px;
	min-width: 300px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 10%;
	background-color: #fff;
}


.login-title {
	font-family: Helvetica, Arial, "Microsoft YaHei", FreeSans, Arimo,
		"Droid Sans", "wenquanyi micro hei", "Hiragino Sans GB",
		"Hiragino Sans GB W3", FontAwesome, sans-serif;
	color: #1697FF;
	font-weight: bold;
	font-size: 40px;
	text-align: center;
	line-height: 40px;
	margin-bottom: 40px;
}

.login-form input {
	height: 46px;
	padding: 10px 16px;
	font-size: 18px;
	line-height: 1.33;
	border-radius: 6px;
	border-color: #e0e0e0;
	color: #3c763d;
}
.form-control-feedback {
	color: #ddd;
}

.btn-green {
	color: #fff;
	background-color: #1697FF;
	height: 46px;
	padding: 10px 16px;
	margin-top: 30px;
}
.login-form .error{
	color:#a30101;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class=" login-box">
			<div class="login-title ">
				<!-- <span class="glyphicon glyphicon-leaf" ></span> -->
				<img class="img " id="u9_img" src="${ctx}/resources/images/u9.png">
				紫光生产系统
			</div>
			<div class="login-form">
				<c:if test="${not empty isDangerShow}">
					<div id="message" class="alert alert-danger">
						<button data-dismiss="alert" class="close">×</button>${dangerMessage}</div>
				</c:if>
				<form role="form" action="loginAction" method="post" id="form">
					<div class="form-group  has-feedback ">
						<input type="text" id="username" name="username" value="" class="form-control" placeholder="用户名" required="true">
					</div>
					<div class="form-group has-feedback ">
						<input type="password" id="password" name="password"  value="" class="form-control" placeholder="密码" required="true">
					</div>
					<!-- <div class="rember ">
						    <label>  <input type="checkbox"> 下次自动登录</label>
						  </div> -->
					<div class="form-group">
						<button type="submit" class="btn  btn-block  btn-green">登录</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<footer class="footer"> </footer>
	<script>
		$(document).ready(function() {
			$.validator.setDefaults({
				highlight : function(element) {
					$(element).closest('.form-group').addClass('has-error');
				},
				unhighlight : function(element) {
					$(element).closest('.form-group').removeClass('has-error');
				},
				errorElement : 'span',
				errorClass : 'error',
				errorPlacement : function(error, element) {
					if (element.parent('.input-group').length) {
						error.insertAfter(element.parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			$("#form").validate();
		});
	</script>
</body>
</html>