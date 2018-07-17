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
<script src="${ctx}/resources/js/system/user.js?<%=(int)(Math.random() *1000000)%>"></script>
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
			<table class="table table-striped table-hover table-aws" id="datatable">
            </table>
            
			<%-- <table class="table table-striped  table-hover" id="datatable">
				<thead><tr><th>归属部门</th><th class="sort-column login_name">登录名</th><th>手机</th><th>角色</th><shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.content}" var="user">
			<tr>
				<td>${user.office.name}</td>
				<td><a href="${ctx}/system/user/form?id=${user.id}">${user.username}</a></td>
				<td>${user.phone}</td>
				<td>${user.roleNames}</td>
				<shiro:hasPermission name="sys:user:edit"><td>
    				<a href="${ctx}/system/user/form?id=${user.id}">修改</a>
					<a href="${ctx}/system/user/delete?id=${user.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
			</table> --%>
		</div>
		<jsp:include page="../include/footer.jsp" />
	</div>
</body>
</html>
