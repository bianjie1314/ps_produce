<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${title }</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<script src="${ctx}/resources/js/jquery-migrate-1.1.1.js" ></script>
<link href="${ctx}/resources/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/resources/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<link href="${ctx}/resources/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctx}/resources/jquery-jbox/2.3/jquery.jBox-2.3.js" type="text/javascript"></script>
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
			
			<form:form id="inputForm" modelAttribute="menu" action="${ctx}/system/menu/save" method="post" class="form-horizontal">
				<form:hidden path="id"/>
					<sys:message content="${message}"/>
					<div class="control-group">
						<label class="control-label">上级菜单:</label>
						<div class="controls">
				               <sys:treeselect id="menu" name="parent.id" value="${menu.parent.id}" labelName="parent.name" labelValue="${menu.parent.name}"
								title="菜单" url="/system/menu/treeData" extId="${menu.id}" cssClass="required"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">名称:</label>
						<div class="controls">
							<form:input path="name" htmlEscape="false" maxlength="50" class="required input-xxlarge form-control"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">链接:</label>
						<div class="controls">
							<form:input path="href" htmlEscape="false" maxlength="2000" class="input-xxlarge form-control"/>
							<span class="help-inline">点击菜单跳转的页面</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">图标:</label>
						<div class="controls">
							<sys:iconselect id="icon" name="icon" value="${menu.icon}"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">排序:</label>
						<div class="controls">
							<form:input path="sort" htmlEscape="false" maxlength="50" class="required digits input-small form-control"/>
							<span class="help-inline">排列顺序，升序。</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">可见:</label>
						<div class="controls">
							<form:radiobuttons path="isShow" items="${showMap}"  class="required"/>
							<span class="help-inline">该菜单或操作是否显示到系统菜单中</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">权限标识:</label>
						<div class="controls">
							<form:input path="permission" htmlEscape="false" maxlength="100" class="input-xxlarge form-control"/>
							<span class="help-inline">控制器中定义的权限标识，如：@RequiresPermissions("权限标识")</span>
						</div>
					</div>
					<div class="form-actions">
						<shiro:hasPermission name="sys:menu:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
						<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
					</div>
				</form:form>
			<table class="table table-striped  table-hover" id="datatable">
			</table>
		</div>
		<jsp:include page="../include/footer.jsp" />
	</div>
</body>
</html>
