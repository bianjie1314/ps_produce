<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><html>
<head>
<title>SOP</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<%-- <script src='${ctx}/resources/js/event/event.js'></script> --%>
</head>
<body>
<jsp:include page="include/header.jsp" />
	<div id="wrapper">
		<jsp:include page="include/nav.jsp" />
		
		<div id="page-wrapper">		
			<c:if test="${not empty success}">
				<div id="message" class="alert alert-success">
					<button data-dismiss="alert" class="close">×</button>${success}</div>
			</c:if>
			<c:if test="${not empty error}">
				<div id="message" class="alert alert-danger">
					<button data-dismiss="alert" class="close">×</button>${error}</div>
			</c:if>
			
			<table class="table table-striped  table-hover" id="datatable">
				
			</table>
		</div>
		<jsp:include page="include/footer.jsp" />
	</div>
</body>
</html>

