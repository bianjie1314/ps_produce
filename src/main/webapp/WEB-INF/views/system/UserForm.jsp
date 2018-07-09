<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${title }</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<script src="${ctx}/resources/js/jquery-migrate-1.1.1.js" ></script>
<link href="${ctx}/resources/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/resources/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<link href="${ctx}/resources/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctx}/resources/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<style type="text/css">
	#jbox-content {
		width:300px;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#name").focus();
		$("#inputForm").validate({
			submitHandler: function(form){
				form.submit();
			},
			errorContainer: "#messageBox",
			errorPlacement: function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="../include/header.jsp" />
	<div id="wrapper">
		<jsp:include page="../include/nav.jsp" />
		
		<div id="page-wrapper">		
			<c:if test="${not empty success}">
				<div id="message" class="alert alert-success">
					<button data-dismiss="alert" class="close">×</button>${success}</div>
			</c:if>
			<c:if test="${not empty error}">
				<div id="message" class="alert alert-danger">
					<button data-dismiss="alert" class="close">×</button>${error}</div>
			</c:if>
			
			<form:form id="inputForm" modelAttribute="user" action="${ctx}/system/user/save" method="post" class="form-horizontal">
				<form:hidden path="id"/>
				<div class="control-group">
				<label class="control-label">归属部门:</label>
					<div class="controls">
		                <sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
							title="部门" url="/system/office/treeData?type=2" cssClass="required" notAllowSelectParent="true"/>
					</div>
				</div>
				
				<%-- <div class="control-group">
					<label class="control-label">工号:</label>
					<div class="controls">
						<form:input path="no" htmlEscape="false" maxlength="50" class="required"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div> --%>
				<div class="control-group">
					<label class="control-label">用户名:</label>
					<div class="controls">
						<form:input path="username" htmlEscape="false" maxlength="50" class="required"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">邮箱:</label>
					<div class="controls">
						<form:input path="email" htmlEscape="false" maxlength="100" class="email"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">电话:</label>
					<div class="controls">
						<form:input path="phone" htmlEscape="false" maxlength="100"/>
					</div>
				</div>
<!-- 				<div class="control-group"> -->
<!-- 					<label class="control-label">是否允许登录:</label> -->
<!-- 					<div class="controls"> -->
<%-- 						<form:select path="loginFlag"> --%>
<%-- 							<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
<%-- 						</form:select> --%>
<!-- 						<span class="help-inline"><font color="red">*</font> “是”代表此账号允许登录，“否”则表示此账号不允许登录</span> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<div class="control-group">
					<label class="control-label">用户角色:</label>
					<div class="controls">
						<form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false" class="required"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">备注:</label>
					<div class="controls">
						<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
					</div>
				</div>
				<div class="form-actions">
					<shiro:hasPermission name="sys:user:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
					<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
				</div>	
			</form:form>
		</div>
		<jsp:include page="../include/footer.jsp" />
	</div>
</body>
</html>
