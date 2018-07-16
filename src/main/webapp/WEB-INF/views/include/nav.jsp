<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%-- <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>   --%>
	<div class="navbar-default sidebar" role="navigation">
	
		<div class="sidebar-nav navbar-collapse">
			<ul id="menu" class="nav" style="*white-space:nowrap;float:none;">
				<c:set var="firstMenu" value="true"/>
				<c:set var="menuList" value="${fns:getMenuList()}"/>
				<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
					<c:if test="${menu.parent.id eq '1'&&menu.isShow eq '1'}">
						<li <c:if test="${firstMenu}">class="active"</c:if>>
							<a href="#" name="oneLevel"><i class="icon-${not empty menu.icon ? menu.icon : 'circle-arrow-right'}"></i>&nbsp;${menu.name}</a>
							<ul class="nav nav-second-level">
								<c:forEach items="${menuList}" var="menu2">
									<c:if test="${menu2.parent.id eq menu.id&&menu2.isShow eq '1'}">
										<li><a data-href=".menu3-${menu2.id}" href="${fn:indexOf(menu2.href, '://') eq -1 ? ctx : ''}${not empty menu2.href ? menu2.href : '/404'}" target="${not empty menu2.target ? menu2.target : ''}" ><i class="icon-${not empty menu2.icon ? menu2.icon : 'circle-arrow-right'}"></i>&nbsp;${menu2.name}</a>

										</li>
										<c:set var="firstMenu" value="false"/>
									</c:if>
								</c:forEach>
							</ul>
						</li>	
						<c:if test="${firstMenu}">
							<c:set var="firstMenuId" value="${menu.id}"/>
						</c:if>
						<c:set var="firstMenu" value="false"/>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>


<script type="text/javascript">

	$(function() {
		var url = window.location;
		var element = $('ul.nav a').filter(function() {
			return this.href == url /* || url.href.indexOf(this.href) == 0 */;
		}).addClass('active').parent().parent().addClass('in').parent();
		if (element.is('li')) {
			element.addClass('active');
		}

		$('#menu').metisMenu(/* {toggle: false} */);
	});

// 	//动态切换三角形形态 
// 	$("a[name='oneLevel']").on("click", function(){
// 		var $i = $(this).find("i");
// 		if($i.hasClass("glyphicon glyphicon-chevron-left")){
// 			$i.attr("class","glyphicon glyphicon-chevron-down");
// 		}else{
// 			$i.attr("class","glyphicon glyphicon-chevron-left");
// 		}
		
// 		$("a[name='oneLevel'] i").not($i).attr("class","glyphicon glyphicon-chevron-left");
// 	});
	
	 $('.zoombar').click(function(){
     	var width =$(".sidebar").css("width");
     	if(width=="200px"){
     		$('.sidebar').css("width","0px");
     		$('.sidebar').css("display","none");
     		$('.sidebar').css("min-width","0px");
     		$('.zoombar').css("color", "#ddd");
     	}else{
     		$('.sidebar').css("width","200px");
     		$('.sidebar').css("min-width","200px");
     		$('.sidebar').css("display","block");
     		$('.zoombar').css("color", "#53d192");

     	}        	
     })
	
</script>
