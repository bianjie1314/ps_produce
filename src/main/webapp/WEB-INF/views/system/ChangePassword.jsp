<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>OHS</title>
<link rel="stylesheet" href="${ctx}/resources/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/resources/metisMenu/2.0.0/metisMenu.min.css">
<link rel="stylesheet" href="${ctx}/resources/css/common.css">
<link rel="stylesheet" href="${ctx}/resources/bootstrap-datatable/css/dataTables.bootstrap.css">
<link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon">
<script src="${ctx}/resources/jquery/1.11.1/jquery.min.js"></script>
<script src="${ctx}/resources/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${ctx}/resources/jquery-validation/1.11.1/jquery.validate.min.js"></script>
<script src="${ctx}/resources/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<script src="${ctx}/resources/bootstrap-datatable/js/jquery.dataTables.min.js"></script>
<script src="${ctx}/resources/bootstrap-datatable/js/dataTables.bootstrap.js"></script>
<script src="${ctx}/resources/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${ctx}/resources/metisMenu/2.0.0/metisMenu.min.js"></script>
<script src="${ctx}/resources/jquery.bootstrap/jquery.bootstrap.min.js"></script>
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<style type="text/css">
#form{
max-width: 500px;
    margin-left: auto;
    margin-right: auto;
    margin-top: 50px;
}
</style>

</head>
<body>
<jsp:include page="../include/header.jsp" />
    <div id="wrapper">
        <jsp:include page="../include/nav.jsp" />
        <div id="page-wrapper">
            
           <form class="form" role="form" action="${ctx}/system/user/cp" method="post" id="form">
           <jsp:include page="../include/showMessage.jsp" />
            <c:if test="${not empty success}">
                <div id="message" class="alert alert-success">
                    <button data-dismiss="alert" class="close">×</button>${success}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div id="message" class="alert alert-danger">
                    <button data-dismiss="alert" class="close">×</button>${error}</div>
            </c:if>
						<div class="panel panel-default">
							<div class="panel-heading">修改密码</div>
							<div class="panel-body">
							<div class="form-group">
									<div class="input-group">
										<label class="input-group-addon" for="oldPassword">旧密码</label> 
										<input type="password" id="oldPassword" name="oldPassword" class="form-control" required="true">
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<label class="input-group-addon" for="newPassword">新密码</label> 
										<input type="password" id="newPassword" name="newPassword" class="form-control" required="true">
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<label class="input-group-addon" for="confirmPassword">确认密码</label> 
										<input type="password" id="confirmPassword" name="confirmPassword" class="form-control"
											required="true" equalTo="#newPassword">
									</div>
								</div>

							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-info">提交</button>
							</div>
						</div>
					</form>
        </div>
        
        <jsp:include page="../include/footer.jsp" />
    </div>
</body>



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
</html>

