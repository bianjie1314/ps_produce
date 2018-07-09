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

<script src='${ctx}/resources/js/mustache.min.js'></script>
<script src="${ctx}/resources/js/jquery-migrate-1.1.1.js" ></script>
<link href="${ctx}/resources/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctx}/resources/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<style type="text/css">
	#jbox-content {
		width:300px;
	}
	.drop-event{
		margin-bottom: 10px;
	}
</style>

<%-- <%@include file="/WEB-INF/views/include/treetable.jsp" %> --%>
<%@include file="/WEB-INF/views/include/treeview.jsp" %>

</head>

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
			<div class="row">
				<div class="row">
					<div class="col-md-2">
						<div id="ztree" class="ztree"></div>
					</div>
  					<div class="col-md-10">
  						<iframe id="officeContent" src="${ctx}/system/office/list?id=&parentIds=" width="100%" height="91%" frameborder="0"></iframe>
  					</div>		
				</div>
			</div>
	
	<script type="text/javascript">
		var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
			callback:{onClick:function(event, treeId, treeNode){
					var id = treeNode.pId == '0' ? '' :treeNode.pId;
					$('#officeContent').attr("src","${ctx}/system/office/list?id="+id+"&parentIds="+treeNode.pIds);
				}
			}
		};
		
		function refreshTree(){
			$.getJSON("${ctx}/system/office/treeData",function(data){
				$.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
			});
		}
		refreshTree();
		 
	</script>
	
		</div>
		<jsp:include page="../include/footer.jsp" />
	</div>
</body>
</html>
