<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<title>绑定订单系统账号</title>

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
	background-color: #334054;
}

.login-box {
	width: 500px;
	min-width: 300px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 10%;
	background-color: #334054;
}

.login-title {
	font-family: Helvetica, Arial, "Microsoft YaHei", FreeSans, Arimo,
		"Droid Sans", "wenquanyi micro hei", "Hiragino Sans GB",
		"Hiragino Sans GB W3", FontAwesome, sans-serif;
	color: #fff;
	font-weight: bold;
	font-size: 30px;
	text-align: center;
	line-height: 30px;
	margin-bottom: 30px;
}

.login-form input {
	height: 46px;
	padding: 10px 16px;
	font-size: 18px;
	line-height: 1.33;
	border-radius: 6px;
	border-color: #3c763d;
	color: #3c763d;
}

.form-control-feedback {
	color: #ddd;
}

.btn-green {
	color: #fff;
	background-color: #53d192;
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

<div class=" login-box">
			<div class="login-title ">
			<span>${username} 欢迎！</span>							
				<span>第一次登陆需要关联订单系统账号</span>
				
			</div>
			<div class="login-form">
				<c:if test="${not empty isDangerShow}">
					<div id="message" class="alert alert-danger">
						<button data-dismiss="alert" class="close">×</button>${dangerMessage}</div>
				</c:if>
                   <form role="form" method="post" action="bind" >
					<div class="form-group  has-feedback ">
                           <input class="form-control" placeholder="订单系统用户名" required="required" name="osUsername" type="text" autofocus>
					</div>
					<div class="form-group has-feedback ">
                                    <input class="form-control" placeholder="订单系统密码" required="required" name="password" type="password" value="">
					</div>
					<!-- <div class="rember ">
						    <label>  <input type="checkbox"> 下次自动登录</label>
						  </div> -->
					<div class="form-group">
						<button type="submit" class="btn  btn-block  btn-green">绑定</button>
					</div>
				</form>
			</div>
		</div>
		
		
   


</body>

</html>
