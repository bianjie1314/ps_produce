<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>查看课表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link href="${ctx}/resources/timeTable/style/common.css" rel="stylesheet" type="text/css">
<link href="${ctx}/resources/timeTable/style/style.css" rel="stylesheet" type="text/css">
<script src="${ctx}/resources/timeTable/js/jquery-1.7.2.min.js?<%=(int)(Math.random() *1000000)%>"></script>
<script src="${ctx}/resources/js/timetable/TimeTable.js?<%=(int)(Math.random() *1000000)%>"></script>
<script src="${ctx}/resources/timeTable/js/tab.js?<%=(int)(Math.random() *1000000)%>"></script>
<script type="text/javascript">
var clazz=${clazzs}

function loadClazz(_this){
	    $("#clazz").html("");
		for(i in clazz){
			
			 var c=clazz[i]
			 if(c.officeId==$(_this).val())
			 $("#clazz").append('<option value="'+c.id+'">'+c.clazzName+'</option>');
    } 
	}
</script>

</head>
<body>
	<jsp:include page="../include/header.jsp" />
	<div id="wrapper">
		<jsp:include page="../include/nav.jsp" />
		
		<div id="page-wrapper">	
		<form action="" id="searchForm" method="post"  accept-charset="UTF-8">
		<div  class="row search-bar">
				<div class=" col-md-3">				
					<div class="input-group">
					<label class="input-group-addon">学期</label>
						 <select id="term" name ="term" class="form-control"  onchange="load(this)"><option value="1">第一学期</option>
                             <option value="2">第二学期</option>
                             <option value="3">第三学期</option>
                             <option value="4">第四学期</option>
                             <option value="5">第五学期</option>
                             <option value="6">第六学期</option>
                          </select>
					</div> 
				</div>
				<shiro:hasPermission name="sys:clazz:view">
				  <div class="col-sm-3">
	                     <div class="input-prepend input-group">
	                         <span class="add-on input-group-addon">专业：<i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
	                         <select id="office" name="office" class="form-control"  onchange="loadClazz(this)">
	                         <option value="">请选择</option>
	                         <c:forEach items="${offices}" var="office">
	                              <option value="${office.id}">${office.name}</option>
	                         </c:forEach>
	                         </select> 
	                 	 </div>
					</div>
					<div class="col-sm-3">
	                     <div class="input-prepend input-group">
	                         <span class="add-on input-group-addon">班级：<i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
	                         <select id="clazz" name="clazz" class="form-control"  ></select> 
	                 	 </div>
					</div>					
				</shiro:hasPermission>
				 <div class="form-group col-md-3">
					<button class=" btn btn-primary " id="search">搜索</button>
				 </div>
				</div>
				</form>
			
		
		
			<div style="width:800px">
<h1 class="titleH1 underNone clearfix">
	<span class="left underNone underLine">课程信息 </span>
</h1>

<div class="jyTable">


	<div class="clearfix">
		<ul class="title  left" >
			<li class='cur' >星期一</li>
			<li>星期二</li>
			<li>星期三</li>
			<li>星期四</li>
			<li>星期五</li>
			
		</ul>
	</div>
	<div class='zong'>
		<div class="list list1">
			<div class="tabCon">
				<ul>
					<li class="tabth clearfix">
						<span style="width: 13%">节次</span>
						<span style="width: 13%">教室</span>
						<span style="width: 13%">课程内容</span>
						<span style="width: 13%">教员</span>
					</li>
					
				</ul>
				<div id="s1">
					<ul class="tabUl">
					<c:forEach items="${pageBean.content}" var="timeTable">
					 <c:if test="${timeTable.day==1}"> 
						<li class="tabtr clearfix">
							<span style="width: 13%; color: #F7001E">第${timeTable.sort} 节</span>
							<span style="width: 13%;color: #AB9C08">${timeTable.classroom}</span>
							<span style="width: 13%;color: #067D14">${timeTable.courseName} </span>
							<span style="width: 13%">${timeTable.teaName} </span>
							</li>
						 </c:if> 
						</c:forEach>
					</ul>
				</div>
			</div>

			<div class="tabCon">
				<ul>
					<li class="tabth clearfix">
						<span style="width: 13%">节次</span>
						<span style="width: 13%">教室</span>
						<span style="width: 13%">课程内容</span>
						<span style="width: 13%">教员</span>
					</li>
					
				</ul>
				<div id="s2">
					<ul class="tabUl">
					<c:forEach items="${pageBean.content}" var="timeTable">
					 <c:if test="${timeTable.day==2}"> 
						<li class="tabtr clearfix">
							<span style="width: 13%; color: #F7001E">第${timeTable.sort} 节</span>
							<span style="width: 13%;color: #AB9C08">${timeTable.classroom}</span>
							<span style="width: 13%;color: #067D14">${timeTable.courseName} </span>
							<span style="width: 13%">${timeTable.teaName} </span>
							</li>
						 </c:if> 
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="tabCon">
				<ul>
					<li class="tabth clearfix">
						<span style="width: 13%">节次</span>
						<span style="width: 13%">教室</span>
						<span style="width: 13%">课程内容</span>
						<span style="width: 13%">教员</span>
					</li>
					
				</ul>
				<div id="s3">
					<ul class="tabUl">
					<c:forEach items="${pageBean.content}" var="timeTable">
					 <c:if test="${timeTable.day==3}"> 
						<li class="tabtr clearfix">
							<span style="width: 13%; color: #F7001E">第${timeTable.sort} 节</span>
							<span style="width: 13%;color: #AB9C08">${timeTable.classroom}</span>
							<span style="width: 13%;color: #067D14">${timeTable.courseName} </span>
							<span style="width: 13%">${timeTable.teaName} </span>
							</li>
						 </c:if> 
						</c:forEach>
					</ul>
				</div>
			</div>

			<div class="tabCon">
				<ul>
					<li class="tabth clearfix">
						<span style="width: 13%">节次</span>
						<span style="width: 13%">教室</span>
						<span style="width: 13%">课程内容</span>
						<span style="width: 13%">教员</span>
					</li>
					
				</ul>
				<div id="s5">
					<ul class="tabUl">
					<c:forEach items="${pageBean.content}" var="timeTable">
					 <c:if test="${timeTable.day==5}"> 
						<li class="tabtr clearfix">
							<span style="width: 13%; color: #F7001E">第${timeTable.sort} 节</span>
							<span style="width: 13%;color: #AB9C08">${timeTable.classroom}</span>
							<span style="width: 13%;color: #067D14">${timeTable.courseName} </span>
							<span style="width: 13%">${timeTable.teaName} </span>
							</li>
						 </c:if> 
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="tabCon">
				<ul>
					<li class="tabth clearfix">
						<span style="width: 25%">节次</span>
						<span style="width: 25%">教室</span>
						<span style="width: 25%">课程内容</span>
						<span style="width: 25%">教员</span>
					</li>
					
				</ul>
				<div id="s1">
					<ul class="tabUl">
					<c:forEach items="${pageBean.content}" var="timeTable">
					 <c:if test="${timeTable.day==1}"> 
						<li class="tabtr clearfix">
							<span style="width: 25%; color: #F7001E">第${timeTable.sort} 节</span>
							<span style="width: 25%;color: #AB9C08">${timeTable.classroom}</span>
							<span style="width: 25%;color: #067D14">${timeTable.courseName} </span>
							<span style="width: 25%">${timeTable.teaName} </span>
							</li>
						 </c:if> 
						</c:forEach>
					</ul>
				</div>
			</div>

			

			
		</div>
		</div>


	

	
</div>

<div style="text-align:center;margin:10px 0; font:normal 14px/24px 'MicroSoft YaHei';">
</div>
		</div>
		<jsp:include page="../include/footer.jsp" />
	</div>
	</div>
</body>
</html>
