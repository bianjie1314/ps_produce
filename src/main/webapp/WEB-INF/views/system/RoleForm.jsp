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
<style type="text/css">
	#jbox-content {
		width:300px;
	}
</style>
<script type="text/javascript">
var dataScopes=${fns:getDataScopeJson()};
</script>
<%@include file="/WEB-INF/views/include/treeview.jsp" %>
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

			var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:false},
					data:{simpleData:{enable:true}},callback:{beforeClick:function(id, node){
						tree.checkNode(node, !node.checked, true, true);
						return false;
					}}};
			
			// 用户-菜单
			var zNodes=[
					<c:forEach items="${menuList}" var="menu">{id:"${menu.id}", pId:"${not empty menu.parent.id?menu.parent.id:0}", name:"${not empty menu.parent.id?menu.name:'权限列表'}"},
		            </c:forEach>];
			// 初始化树结构
			var tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);
			// 不选择父节点
			tree.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
			// 默认选择节点
			var ids = "${role.menuIds}".split(",");
			for(var i=0; i<ids.length; i++) {
				var node = tree.getNodeByParam("id", ids[i]);
				try{tree.checkNode(node, true, false);}catch(e){}
			}
			// 默认展开全部节点
			tree.expandAll(true);
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
			<form:form id="inputForm" modelAttribute="role" action="${ctx}/system/role/save" method="post" class="form-horizontal">
				<form:hidden path="id"/>
				<div class="control-group">
					<label class="control-label">角色名称:</label>
					<div class="controls">
						<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">数据范围:</label>
					<div class="controls">
						<form:select path="dataScope" class="input-medium">
							<form:options items="${fns:getDataScopeMap() }" htmlEscape="false"/>
						</form:select>
<!-- 						<span class="help-inline">特殊情况下，设置为“按明细设置”，可进行跨机构授权</span> -->
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">角色授权:</label>
					<div class="controls">
						<div id="menuTree" class="ztree" style="margin-top:3px;float:left;"></div>
						<form:hidden path="menuIds"/>
					</div>
				</div>
				<shiro:hasPermission name="sys:role:edit"><input class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			</form:form>
			<table class="table table-striped  table-hover" id="datatable">
				
			</table>
		</div>
		<jsp:include page="../include/footer.jsp" />
	</div>
</body>
</html>
