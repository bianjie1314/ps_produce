<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>课程管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<script>
var teacher=${teacher}
</script>
<script src="${ctx}/resources/js/system/course.js"></script>


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