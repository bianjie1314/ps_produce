<%@ page contentType="text/html;charset=UTF-8"%>
<div class="row">
	<div class="col-md-12">
		<nav>
			<ul class="pagination pagination-sm">
				<li><a href="#" onclick="gotoPage('${pageBean.pageNo-1}')" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
				<li><a href="#" onclick="gotoPage(1)">首页</a></li>
				<c:forEach begin="${pageBean.beginPageIndex}" end="${pageBean.endPageIndex}" var="pageNo">
					<c:choose>
						<c:when test="${pageNo == pageBean.pageNo}">
							<li class="active"><a href="#" onclick="gotoPage(${pageNo})">${pageNo}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="#" onclick="gotoPage(${pageNo})">${pageNo}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li><a href="#" onclick="gotoPage('${pageBean.totalPage}')">尾页</a></li>
				<li><a href="#" onclick="gotoPage('${pageBean.pageNo+1}')" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
						
			</ul>
		
		<ul class="list-inline">
			<li>
				<select name="pageSize" onchange="gotoPage(${pageBean.pageNo})">
					<option value="10" ${'10'==pageBean.pageSize?'selected':''}>10条/页</option>
					<option value="20" ${'20'==pageBean.pageSize?'selected':''}>20条/页</option>
					<option value="30" ${'30'==pageBean.pageSize?'selected':''}>30条/页</option>
				</select>
			</li>
			<li>总记录：${pageBean.totalCount}</li>
			<li id="pageBeanCount" >共${pageBean.totalPage}页</li>
			<li><input id="inputPageNo" type="text" style="width:38px;height:20px" placeholder="页码" /><a href="javascript:void(0)" onclick="gotoPage(-100)">跳转</a><span id='pageNoWarn' style='color: #a94442'></span></li>
		</ul>
		</nav>
	</div>
</div>
<script type="text/javascript">
	function gotoPage(pageNo){
		if(pageNo==0){
			return false;
		}
		var pageCountText=$("#pageBeanCount").text();
		pageCount=pageCountText.replace(/[^0-9]/ig, "");
		if(pageNo>pageCount){
			return;
		}
		if(pageNo == -100){
			var tempPageNo = $("#inputPageNo").val();
			tempPageNo = $.trim(tempPageNo);
			if(isNaN(tempPageNo) || tempPageNo.length==0){
				$("#pageNoWarn").empty().text("　请输入数字！");
				return;
			}else{
				pageNo = tempPageNo;
			}
		}
		var pageSize = $("select[name='pageSize']").val();
		$("#searchForm").append("<input type='hidden' name='pageSize' value='" + pageSize +"'>");
		$("#searchForm").append("<input type='hidden' name='pageNo' value='" + pageNo +"'>");
		$("#searchForm").submit();
	}
</script>