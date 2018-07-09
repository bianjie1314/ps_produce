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
<%@include file="/WEB-INF/views/include/treetable.jsp" %>
<script src="${ctx}/resources/js/system/menu.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#treeTable").treeTable({expandLevel : 3}).show();
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
            <div class="toolbar-btn"><button class=" btn btn-primary" id="add">添加</button></div>
			<table class="table table-striped table-hover table-aws " id="treeTable">
				<thead><tr><th>名称</th><th>链接</th><th style="text-align:center;">排序</th><th>可见</th><th>权限标识</th><shiro:hasPermission name="sys:menu:edit"><th>操作</th></shiro:hasPermission></tr></thead>
				<tbody><c:forEach items="${list}" var="menu">
					<tr id="${menu.id}" pId="${menu.parent.id ne '1'?menu.parent.id:'0'}">
						<td nowrap><i class="icon-${not empty menu.icon?menu.icon:'hide'}"></i><a href="${ctx}/menu/form?id=${menu.id}">${menu.name}</a></td>
						<td title="${menu.href}">${fns:abbr(menu.href,30)}</td>
						<td style="text-align:center;">
							<shiro:hasPermission name="sys:menu:edit">
								<input type="hidden" name="ids" value="${menu.id}"/>
								<input name="sorts" type="text" value="${menu.sort}" style="width:50px;margin:0;padding:0;text-align:center;">
							</shiro:hasPermission><shiro:lacksPermission name="sys:menu:edit">
								${menu.sort}
							</shiro:lacksPermission>
						</td>
						<td>${menu.isShow eq '1'?'显示':'隐藏'}</td>
						<td title="${menu.permission}">${fns:abbr(menu.permission,30)}</td>
						<shiro:hasPermission name="sys:menu:edit"><td nowrap>
							<a href="${ctx}/system/menu/form?id=${menu.id}">修改</a>
							<a  onclick="del(${menu.id});">删除</a>
							<a href="${ctx}/system/menu/form?parent.id=${menu.id}">添加下级菜单</a> 
						</td></shiro:hasPermission>
					</tr>
				</c:forEach></tbody>
			</table>
		</div>
		<jsp:include page="../include/footer.jsp" />
	</div>
</body>
</html>
