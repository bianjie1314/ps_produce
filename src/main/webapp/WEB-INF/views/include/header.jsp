<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<script type="text/javascript">
    var ctx = '${pageContext.request.contextPath}';
</script>
<nav class="navbar navbar-default navbar-static-top  navbar-inverse " role="navigation" style="margin-bottom: 0">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#" > <span class=" zoombar" style="color:#53d192; font-size: 23px;"><img class="img " style=" width:20px" id="u9_img" src="${pageContext.request.contextPath}/resources/images/u9.png"></span></a> <a class="navbar-brand" href="#">教务系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
            	 <li class="dropdown ">
            	 	<a href="#" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-expanded="true"> <span class="glyphicon glyphicon-bell"></span>&nbsp;<span id="tipsNumber" style="color:red;"></span><span
                        class="caret"></span></a>
                     <ul class="dropdown-menu" role="menu" id="tipsContent">
                     </ul>
            	 </li>
                <li class="dropdown "><a href="#" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-expanded="true"> <span class="glyphicon glyphicon-user "></span> <shiro:principal property="username" /> <span
                        class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="${pageContext.request.contextPath}/system/user/changePassword">修改密码 </a></li>
                    
                        <li><a href="${pageContext.request.contextPath}/logout">退出 <span class="glyphicon glyphicon-log-out"></span></a></li>
                    </ul>
                    </li>
            </ul>
        </div>
    </div>
</nav>

