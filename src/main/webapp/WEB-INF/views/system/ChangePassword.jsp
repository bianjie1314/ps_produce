<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>修改密码</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" href="${ctx}/resources/bootstrap-datetimepicker/daterangepicker-bs3.css">

<script src="${ctx}/resources/bootstrap-datetimepicker/moment.js" ></script>


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

