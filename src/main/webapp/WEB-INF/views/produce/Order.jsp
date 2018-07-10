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
<link rel="stylesheet" href="${ctx}/resources/bootstrap-datetimepicker/daterangepicker-bs3.css">

<script src="${ctx}/resources/bootstrap-datetimepicker/moment.js" ></script>
<script src="${ctx}/resources/bootstrap-datetimepicker/daterangepicker.js" ></script>

<script src="${ctx}/resources/js/produce/order.js"></script>

<script type="text/javascript">
$(document).ready(function() {
    $('#reservation').daterangepicker(null, function(start, end, label) {
    });
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
			<ul class="nav nav-tabs">
				<li role="presentation" class="active"><a href="#"> 所有订单 </a></li>
				<li role="presentation"><a href="#">待处理订单</a></li>
				<li role="presentation"><a href="#">备货中订单</a></li>
				<li role="presentation"><a href="#">已发货订单</a></li>
				<li role="presentation"><a href="#">已取消订单</a></li>
			</ul>
			<div  class="row search-bar">
				<div class=" col-md-3">				
					<div class="input-group">
					<label class="input-group-addon">关键词</label>
						 <input type="text" name="keyword" class="form-control" placeholder="请输入关键词" >
					</div> 
				</div>
				  <div class="col-sm-4">
	                     <div class="input-prepend input-group">
	                         <span class="add-on input-group-addon">下单时间：<i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span><input type="text" readonly style="width: 230px" name="reservation" id="reservation" class="form-control"/> 
	                 	 </div>
					</div>			
				
				<div class="form-group col-md-2">
					<div class="input-group">
						<label class="input-group-addon">下单商户</label>
						 <select type="text" name="category" id="category" class="form-control" placeholder="下单商户" >
						  </select>
					</div>
				</div>
				<div class="form-group col-md-3">
					<button class=" btn btn-primary " id="search">搜索</button>

				</div>
			</div>

			<table class="table table-striped table-hover table-aws" id="datatable">

				<c:forEach items="${orders}" var="order">
					<tr>
						<td>
							<div class="row order-header">
								<div class="col-md-2">单号：${order.orderNo}</div>
								<div class="col-md-3">下单商户：${order.orderUsername}</div>
								<div class="col-md-3">下单时间：${order.orderCreateTime}</div>
								<div class="col-md-4">
									<button class=" btn btn-primary " id="cancle">取消订单</button>
								</div>
							</div>
							<div class="row">
								<div class="col-md-8">
									<c:forEach items="${order.products}" var="product"
										varStatus="stat">
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
										<c:if test="${!stat.last}">
											<div class="line"></div>
										</c:if>
									</c:forEach>
								</div>
								<div class="col-md-4">订单状态</div>
							</div>
						</td>
					</tr>
				</c:forEach>

			</table>
			<nav aria-label="Page navigation">
			  <ul class="pagination">
			    <li>
			      <a href="#" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    <li><a href="#">1</a></li>
			    <li><a href="#">2</a></li>
			    <li><a href="#">3</a></li>
			    <li><a href="#">4</a></li>
			    <li><a href="#">5</a></li>
			    <li>
			      <a href="#" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			  </ul>
			</nav>

		</div>
		<jsp:include page="../include/footer.jsp" />
	</div>
</body>
</html>
