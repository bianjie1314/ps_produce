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
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<style type="text/css">
.order-header {
	padding-bottom: 9px;
	margin: 10px 0 10px;
	border-bottom: 1px solid #eee;
}

.order-item {
	padding-bottom: 9px;
	margin: 10px 0 10px;
}
.line{
	border-bottom: 1px solid #eee;

}
</style>
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
			<!-- <div role="form" class="row">
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
			</div> -->

			<table class="table table-striped table-hover table-aws" id="datatable">

				<c:forEach items="${orders}" var="order">
					<tr>
						<td>
							<div class="row order-header">
								<div class="col-md-2">单号：${order.orderNo}</div>
								<div class="col-md-2">下单商户：${order.orderUsername}</div>
								<div class="col-md-2">下单时间：${order.orderCreateTime}</div>
								<div class="col-md-6">
									<button class=" btn btn-primary " id="cancle">取消订单</button>
								</div>
							</div>
							<div class="row">
								<div class="col-md-9">
									<c:forEach items="${order.products}" var="product" varStatus="stat">
										<div class="row order-item">
											<div class="col-md-2">
												<img src="${product.image}">
											</div>
											<div class="col-md-3">
												<p>${product.name}</p>
												<p>SKU：${product.name}</p>

												<p>条形码：${product.ean}</p>
											</div>
											<div class="col-md-3">
												<p>${product.templateName}</p>
												<p>订单量：${product.quantity}</p>
												<p>Color：${product.color} Size：${product.size}</p>
											</div>
										</div>
										  <c:if test="${!stat.last}"><div class="line"></div></c:if>
									</c:forEach>
								</div>
								<div class="col-md-3">订单状态</div>
							</div>
						</td>
					</tr>
				</c:forEach>

			</table>


		</div>
		<jsp:include page="../include/footer.jsp" />
	</div>
</body>
</html>
