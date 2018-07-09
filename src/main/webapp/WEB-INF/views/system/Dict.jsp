<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>zg-erp</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" href="${ctx}/resources/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/resources/metisMenu/2.0.0/metisMenu.min.css">
<link rel="stylesheet" href="${ctx}/resources/css/common.css">
<link rel="stylesheet" href="${ctx}/resources/bootstrap-datatable/css/dataTables.bootstrap.css">
<script src="${ctx}/resources/jquery/1.11.1/jquery.min.js"></script>
<script src="${ctx}/resources/bootstrap-datatable/js/jquery.dataTables.min.js"></script>
<script src="${ctx}/resources/bootstrap-datatable/js/dataTables.bootstrap.js"></script>
<script src="${ctx}/resources/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${ctx}/resources/metisMenu/2.0.0/metisMenu.min.js"></script>
<script src="${ctx}/resources/jquery.bootstrap/jquery.bootstrap.min.js"></script>
<script>
var dictType=${dictType};
</script>
<script src="${ctx}/resources/js/system/dict.js"></script>
<script src="${ctx}/resources/js/decorate.jquery.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<jsp:include page="../include/header.jsp" />
    <div id="wrapper">
        <jsp:include page="../include/nav.jsp" />
        <div id="page-wrapper">
            <jsp:include page="../include/showMessage.jsp" />
            <c:if test="${not empty success}">
                <div id="message" class="alert alert-success">
                    <button data-dismiss="alert" class="close">×</button>${success}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div id="message" class="alert alert-error">
                    <button data-dismiss="alert" class="close">×</button>${error}</div>
            </c:if>
            <table class="table table-striped  table-hover  table-aws" id="datatable">
            </table>
        </div>
        
        <jsp:include page="../include/footer.jsp" />
    </div>
</body>
</html>