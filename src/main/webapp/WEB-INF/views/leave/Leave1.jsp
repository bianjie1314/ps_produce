<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>请假申请</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" href="${ctx}/resources/bootstrap-datetimepicker/daterangepicker-bs3.css">

<script src="${ctx}/resources/bootstrap-datetimepicker/moment.js" ></script>
<script src="${ctx}/resources/bootstrap-datetimepicker/daterangepicker.js" ></script>

<script src="${ctx}/resources/js/leave/leave1.js?<%=(int)(Math.random() *1000000)%>"></script>

</head>
<script >
function addLeave(){
	 var $f = $('<div role="form" class="row"></div>');
	 $f.append('<div class="form-group col-md-12"><div class="input-group"><label class="input-group-addon">请假时间</label><input type="text"  style="width: 230px" name="time"   id="reservation1" class="form-control"/></div> </div>')
    $f.append('<div class="form-group col-md-12"><div class="input-group"><label class="input-group-addon">请假原因</label><input dialog-id="remarks" class="form-control" required></div></div>');
	 $f.find('#reservation1').daterangepicker(null, function(start, end, label) {
     });
    //$f.append('<div class="form-group col-md-6"><div class="input-group">点击到<a href="'+ctx+'/system/dict">字典页</a>添加主题</div></div>');
    $f.dialog({
	       modelSize:"modal-lg",
        title : "填写",
        onClose : function() {
            $(this).dialog("destroy");
        },
        buttons : [{
     	   text : "取消",
     	   click : function() {
     		   $f.dialog("destroy");
     	   },
        },
        {
     	   text : "提交",
     	   'class' : "btn-primary btn-submit",
     	   click : function() {
         	   if(!$.validate($f))
        	   			return false;
         	   var remarks= $f.find("[dialog-id=remarks]").val();
         	   var times= $f.find("#reservation1").val();
         	   if(!remarks) {
         		   $.messager.popup("请填写原因!");
         		   return false;
         	   }
         	  if(!times) {
        		   $.messager.popup("请选择时间段!");
        		   return false;
        	   }
         	   var param = {
         			  remarks : $f.find("[dialog-id=remarks]").val(),
     			      time:times
         	   }
         	   $.ajax({
                    url : ctx + "/leave/add",
                    type : "POST",
                    contentType : 'application/json; charset=utf-8',
                    dataType : 'json',
                    data : JSON.stringify(param),
                    success : function(result) {
                        if (result.ret == 1) {
                            $.messager.popup("创建成功!");
                        } else {
                        	   $.messager.popup("创建失败!");
                        }
                    },
                    complete : function() {
                        $f.dialog("destroy");
                    }
                });
            }
        }]
    });
    $('.dialog').click(function(e) {
       if(!$(e.target).hasClass('close')&&!$(e.currentTarget).hasClass('in'))
            $f.dialog("destroy");
    });
}
</script>
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
			<ul class="nav nav-tabs" id="myTab">
				<li role="presentation" <c:if test="${empty status}"> class="active" </c:if>> <a href="?"> 所有申请 </a></li>
				<li role="presentation"<c:if test="${status=='0'}"> class="active" </c:if>> <a href="?status=0">待确认</a></li>
				<li role="presentation"<c:if test="${status=='1' }"> class="active" </c:if>>  <a href="?status=1">已确认</a></li>				
				<li role="presentation"<c:if test="${status=='2'}"> class="active" </c:if>>  <a href="?status=2">已驳回</a></li>
				
			</ul>			
			<form action="" id="searchForm" method="post"  accept-charset="UTF-8">
			<input name="statu" value="${status}" style="visibility:hidden">
			<div  class="row search-bar">
				<div class=" col-md-3">				
					<div class="input-group">
					<label class="input-group-addon">关键词</label>
						 <input type="text" name="keyword" value="${query.keyword}" class="form-control" placeholder="请输入关键词" >
					</div> 
				</div>
				  <div class="col-sm-4">
	                     <div class="input-prepend input-group">
	                         <span class="add-on input-group-addon">发起时间：<i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
	                         <input type="text"  style="width: 230px" name="time"  value="${query.time}" id="reservation" class="form-control"/> 
	                 	 </div>
					</div>					
				
				<div class="form-group col-md-3">
					<button class=" btn btn-primary " id="search">搜索</button>
					<button class=" btn btn-primary " type="button" onClick="addLeave()">请假</button>
				</div>
				</div>
				
			</form>
			<table class="table table-striped table-hover table-aws" id="datatable">
			<c:if test="${pageBean.totalCount==0}">
			<div class="row order-header"><span>暂无数据</span></div>
			</c:if>
			<c:if test="${pageBean.totalCount!=0}">
			    <c:forEach items="${pageBean.content}" var="leave">
			    
					<tr  leave-id="${leave.id}">
					    <td class="parent">
							<div class="row order-header">
								<div class="col-md-2">请假单号：${leave.id}</div>
								<div class="col-md-3">发起人：${leave.stuName}</div>
								<div class="col-md-3">发起时间： <fmt:formatDate value="${leave.createDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></div>
							    
							    </div>							
							<div class="row product" leave-id="${leave.id}">
								<div class="col-md-8">
									<div class="row order-item product"  leave-id="${leave.id}">
											<div class="col-md-8">
												<p>请假时间:${leave.startTime}至${leave.endTime}</p>
											</div>
											<div class="col-md-6">
												<p>请假原因:${leave.remarks}</p>
											</div>
										</div>
										
								</div>
								<div class="col-md-4 status1" style=" height: auto;">假条状态  ${fns:getLeaveStatus(leave.status)}</div>
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
