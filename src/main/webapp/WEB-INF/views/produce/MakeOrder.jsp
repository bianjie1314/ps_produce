<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>制作管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" href="${ctx}/resources/bootstrap-datetimepicker/daterangepicker-bs3.css">

<script src="${ctx}/resources/bootstrap-datetimepicker/moment.js" ></script>
<script src="${ctx}/resources/bootstrap-datetimepicker/daterangepicker.js" ></script>

<script src="${ctx}/resources/js/produce/order.js?<%=(int)(Math.random() *1000000)%>"></script> 
<script src="${ctx}/resources/js/produce/makeOrder.js?<%=(int)(Math.random() *1000000)%>"></script>

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
				<li role="presentation"<c:if test="${status==2}"> class="active" </c:if>> <a href="?status=2">待制作订单</a></li>
				<li role="presentation"<c:if test="${status==3}"> class="active" </c:if>> <a href="?status=3">已制作订单</a></li>				
			</ul>
			<c:if test="${status==2}">
			<div class="row">
			<div class=" col-md-3">	
			<button style="margin: 10px 0px;" id="addOrder" class="btn btn-primary">扫码添加订单</button>
			<button style="margin: 10px 0px;" type="button" id="makeOrder" class="btn btn-primary">制作完成</button>
			</div>
			</div>
			</c:if>		
			<form action="" id="searchForm" method="post" accept-charset="UTF-8">
			<input name="statu" value="${status}" type="hidden">
			<div  class="row search-bar">
				<div class=" col-md-3">				
					<div class="input-group">
					<label class="input-group-addon">关键词</label>
						 <input type="text" name="keyword" value="${query.keyword}" class="form-control" placeholder="请输入关键词" >
					</div> 
				</div>
				  <div class="col-sm-4">
	                     <div class="input-prepend input-group">
	                         <span class="add-on input-group-addon">下单时间：<i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
	                         <input type="text"  style="width: 230px" name="time"  value="${query.time}" id="reservation" class="form-control"/> 
	                 	 </div>
					</div>					
				<div class="form-group col-md-2">
					<div class="input-group">
						<label class="input-group-addon">下单商户</label>
						 <select type="text" name="orderUsername" value="${query.orderUsername}"  id="category"  class="form-control" placeholder="下单商户" >
						  </select>
					</div>
				</div>
				<input type="hidden" id="status" name="status" value="${status} ">
				<div class="form-group col-md-3">
					<button class=" btn btn-primary " id="search">搜索</button>
				</div>
			</div>
			</form>
			<table class="table table-striped table-hover table-aws" id="datatable">
			<c:if test="${pageBean.totalCount==0}">
			<div class="row order-header"><span>暂无数据</span></div>
			</c:if>
			<c:if test="${pageBean.totalCount!=0}">
				<c:forEach items="${pageBean.content}" var="order">
					<tr  order-id="${order.id}">
				<c:if test="${status==2}">	<td style="width: 10px;padding-top: 15px;"><input class="isMake" type="checkbox" ></td></c:if>
						<td>
							<div class="row order-header">
								<div class="col-md-2">单号：${order.orderNo}</div>
								<div class="col-md-3">下单商户：${order.orderUsername}</div>
								<div class="col-md-3">下单时间： <fmt:formatDate value="${order.orderCreateTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></div>
								<div class="col-md-4">
									
								</div>
							</div>
							<div class="row product" order-id="${order.id}">
								<div class="col-md-8">
									<c:forEach items="${order.products}" var="product"
										varStatus="stat"> 
										<div class="row order-item product" order-id="${order.id}">
											<div class="col-md-2">
												<img src="${product.image}"  style=" width: 100%;">
											</div>
											<div class="col-md-3">
												<p>${product.name}</p>
												<p>SKU：${product.sku}</p>

												<p>条形码：${product.ean}</p>
											</div>
											<div class="col-md-3">
												<p>订购产品：${product.templateName}</p>
												<p>订单量：${product.quantity}</p>
												<p><c:if test="${not empty product.color}">
												Color：${product.color}
												</c:if>
												<c:if test="${not empty product.size}">
												 Size：${product.size}
												 </c:if></p>
											</div>
											<div class="col-md-3 "  product-id="${product.id}">
												<button type="button" class="btn btn-primary downImg">下载素材</button>
											</div>
										</div>
										<c:if test="${!stat.last}">
											<div class="line"></div>
										</c:if>
									</c:forEach>
								</div>
								<div class="col-md-4">订单状态  ${fns:getOrderStatus(order.status)}</div>
								<c:if test="${status==3}"><div class="col-md-4" > <fmt:formatDate value="${order.makeDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></div></c:if> 
							</div>
						</td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
			<%@ include file="/WEB-INF/views/include/paging.jsp" %>
			
		</div>
		<jsp:include page="../include/footer.jsp" />
	</div>
</body>
</html>
