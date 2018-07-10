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
<script src="${ctx}/resources/js/produce/template.js"></script>
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
			<div role="form" class="row">
			   <div class="form-group col-md-3">
					<div class="input-group">
						<label class="input-group-addon">类别</label>
						 <select type="text" name="category" class="form-control" placeholder="类别" ></select>
					</div>
				</div>
			</div>
			<div role="form" class="row">
				<div class="form-group col-md-3">
					<div class="input-group">
						<label class="input-group-addon">名称</label>
						 <input type="text" name="keyword" class="form-control" placeholder="名称" required>
					</div>
				</div>
				
				<div class="form-group col-md-3">
					<div class="input-group">
					<label class="input-group-addon">上传开始时间</label>
						 <input type="text" id="no-startTime" class="form-control" placeholder="上传开始时间" required>
					</div>
				</div>
				<div class="form-group col-md-3">
					<div class="input-group">
					<label class="input-group-addon">上传结束时间</label>
						 <input type="text" id="no-endTime" class="form-control" placeholder="上传结束时间" required>
					</div>
				</div>
				<div class="form-group col-md-3">
					<button  class=" btn btn-primary " id="search">搜索</button>

				</div>
			</div>
			
			<table class="table table-striped table-hover table-aws" id="datatable">
            </table>
            
			
		</div>
		<jsp:include page="../include/footer.jsp" />
	</div>
</body>
</html>
