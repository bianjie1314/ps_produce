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

<script src="${ctx}/resources/bootstrap-datetimepicker/moment.js"></script>
<script
	src="${ctx}/resources/bootstrap-datetimepicker/daterangepicker.js"></script>

<script src="${ctx}/resources/js/produce/order.js"></script>

<style type="text/css">
#page-wrapper {
	margin-left: 100px;
	margin-right: 100px;
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

			<div class="row">
				<img src="${ctx}/barcode/${order.orderNo}">
			</div>
			<div class="row">
				<div class="col-md-6">
					<p>
					<h4>
						<strong>订单详情</strong>
					</h4>
					</p>
					<p>订单编号：${order.orderNo} 下单商户：${order.orderUsername}</p>
					<p>下单时间： <fmt:formatDate value="${order.orderCreateTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></p>
				</div>
				<div class="col-md-6">
					<p>
					<h4>
						<strong>用户信息</strong>
					</h4>
					</p>
					<p>用户姓名：${order.expressUsername}</p>
					<p>联系电话：${order.expressTel}</p>
					<p>收货地址：${order.expressAddress}</p>
					<p>固定电话：${order.expressFixedPhone}</p>
					
					<p>邮编：${order.expressZipCode}</p>
					

				</div>
				<div class="line"></div>
			</div>
			<div class="row">
				<p>
				<h4>
					<strong>备注信息</strong>
				</h4>
				</p>
				<p>${order.remarks}</p>
				<div class="line"></div>
			</div>
			<div class="row">
				<p>
				<h4>
					<strong>订单进度</strong>
				</h4>
				</p>
			
					<c:if test="${empty orderLogs}">
						<div class="row order-header">
							<span>暂无数据</span>
						</div>
					</c:if>
					<c:if test="${not empty orderLogs}">
	                 <p>
						<table class="table table-hover">
							<thead>
								<tr>
									<th>时间</th>
									<th>当前进度</th>
									<th>状态</th>
									<th>操作员</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody>
								
									<c:forEach items="${orderLogs}" var="log" varStatus="stat">
									<tr>
									
										<td> <fmt:formatDate value="${log.createDate }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>

										</td>
										<td> ${fns:getOrderStatus(log.status)}</td>
										<td>成功</td>
										<td>${log.optUsername}</td>
										<td>${log.remarks}</td>
											</tr>
									</c:forEach>
							
							</tbody>
						</table>
						</p>
						<div class="line"></div>
					</c:if>
				
				
			</div>
			<div class="row">

				<c:forEach items="${order.products}" var="product" varStatus="stat">
					<div class="row order-item">
						<div class="col-md-4">
							<img src="${product.image}" style="max-width: 200px;">
						</div>
						<div class="col-md-8">
							<div class="row">
							<div class="col-md-12">
								<strong>商品信息</strong>
								</div>
							</div>
							
							<div class="row" style="margin-top: 10px;">
							<div class="col-md-12">
								商品名称：${product.name}	
									</div>
							</div>
							
							<div class="row" >
								<div class="col-md-12">
							<c:if test="${not empty product.templateName}"> 模板名称：${product.templateName}</c:if>
								</div>
							</div>
						
						
							<div class="row">
							<div class="col-md-4">
							<c:if test="${not empty product.sku}">  SKU：${product.sku}</c:if>
							</div>
							<div class="col-md-8">
							<c:if test="${not empty product.size}"> 尺寸：${product.size} </c:if>
							</div>
							</div>
                              
                          	<div class="row">
                             <div class="col-md-4">
                             <c:if test="${not empty product.quantity}"> 数量：${product.quantity}</c:if>
                             </div>
                               <div class="col-md-8">
                             <c:if test="${not empty product.color}">颜色：${product.color}</c:if>
                             	</div>
                           </div>
								<div class="row">
								 <div class="col-md-12">
								<c:if test="${not empty product.ean}">
								<img src="${ctx}/barcode/${product.ean}">
								</c:if>
								</div>
							  </div>
						</div>
						
						<%-- <div class="col-md-3">
												<p>${product.templateName}</p>
												<p>订单量：${product.quantity}</p>
												<p>Color：${product.quantity} Size：${product.size}</p>
											</div> --%>
					</div>
					<c:if test="${!stat.last}">
						<div class="line"></div>
					</c:if>
				</c:forEach>
			</div>

		</div>
		<jsp:include page="../include/footer.jsp" />
	</div>
</body>
</html>
