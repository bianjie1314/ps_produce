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
<link href="${ctx}/resources/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/resources/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>

<%-- <%@include file="/WEB-INF/views/include/treeview.jsp" %> --%>
	<script type="text/javascript">
		$(document).ready(function(){
// 			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var ids = [], nodes = tree.getCheckedNodes(true);
					for(var i=0; i<nodes.length; i++) {
						ids.push(nodes[i].id);
					}
					$("#menuIds").val(ids);
// 					var ids2 = [], nodes2 = tree2.getCheckedNodes(true);
// 					for(var i=0; i<nodes2.length; i++) {
// 						ids2.push(nodes2[i].id);
// 					}
// 					$("#officeIds").val(ids2);
					form.submit();
				},
			});
		});
	</script>
</head>
<body>
	<div id="wrapper">
		<div id="page-wrapper">		
			<c:if test="${not empty success}">
				<div id="message" class="alert alert-success">
					<button data-dismiss="alert" class="close">×</button>${success}</div>
			</c:if>
			<c:if test="${not empty error}">
				<div id="message" class="alert alert-danger">
					<button data-dismiss="alert" class="close">×</button>${error}</div>
			</c:if>
			<form:form id="inputForm" modelAttribute="office" action="${ctx}/system/office/save" method="post" class="form-horizontal">
				<form:hidden path="id"/>
				<sys:message content="${message}"/>
				<div class="control-group">
					<label class="control-label">上级机构:</label>
					<div class="controls">
			               <sys:treeselect id="office" name="parent.id" value="${office.parent.id}" labelName="parent.name" labelValue="${office.parent.name}"
							title="机构" url="/system/office/treeData" extId="${office.id}" cssClass="" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">专业名称:</label>
					<div class="controls">
						<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">专业编码:</label>
					<div class="controls">
						<form:input path="code" htmlEscape="false" maxlength="50"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">辅导员:</label>
					<div class="controls">
						<form:select path="master" items="${cous}" itemValue="id" itemLabel="name" ></form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">电话:</label>
					<div class="controls">
						<form:input path="phone" htmlEscape="false" maxlength="50"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">邮箱:</label>
					<div class="controls">
						<form:input path="email" htmlEscape="false" maxlength="50"/>
					</div>
				</div>
				<div class="form-actions">
					<shiro:hasPermission name="sys:office:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
					<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
				</div>
			</form:form>
		</div>
		<jsp:include page="../include/footer.jsp" />
	</div>
</body>
</html>
