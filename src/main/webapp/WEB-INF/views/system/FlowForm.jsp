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
			<form action="${ctx }/system/flow/save" method="post">
				<input type="hidden" name="id" value="${flow.id }"/>
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="row form-group">
								<div class="form-group">
									<div class="col-sm-4">
										<div class="input-group">
											<label class="input-group-addon" for="name" >名称</label> 
											<input type="text" class="form-control" name="name" value="${flow.name }" required="required"/>
										</div>
									</div>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-sm-4">
									<button type="button" onclick="add()">添加审批人</button>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-sm-4">
									<label class="input-group-addon" for="approver" >审批人</label> 
								</div>
							</div>
							<div class="row form-group" >
								<div id="form-row">
									<c:forEach items="${flow.flowLines }" var="flowLine">
										<div class=" row form-group">
											<div class="col-sm-2" >
												<select class="form-control" name="userIds">
													<c:forEach items="${users }" var="user">
														<c:if test="${flowLine.current.id == user.id }">
															<option value="${user.id }" selected="selected">${user.username }</option>
														</c:if>
														<c:if test="${flowLine.current.id != user.id }">
															<option value="${user.id }">${user.username }</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
											<div class="col-sm-2" >
												<button onclick="delNode(this)">删除</button>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
							<button type="submit" class="btn btn-primary">提交</button>
							<input class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
						</div>
					</div>
				</div>
			</form>
		</div>
		<jsp:include page="../include/footer.jsp" />
	</div>
	
	<script type="text/javascript">
		function add(){
			var $formgroup = $('<div class=" row form-group"></div>');
			$formgroup.html('<div class="col-sm-2" ><select class="form-control" name="userIds" id="username_" required="true"> <c:forEach items="${users}" var="user"><option class="option" value="${user.id}" label="${user.username}">${user.username}</option></c:forEach></select></div><div class="col-sm-2" ><button onclick="delNode(this)">删除</button></div>');
			$("#form-row").append($formgroup);
		}
		
		function delNode(a){
			$(a).parent().parent().remove();
		}
	</script>
</body>
</html>