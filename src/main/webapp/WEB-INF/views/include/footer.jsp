<%@ page contentType="text/html;charset=UTF-8"%>
<script type="text/javascript">
    $.ajax({
    	url : ctx + "/system/tips",
    	type: "get",
    	success: function(result) {
    		if (result.ret == 0) {
    			$("#tipsNumber").text(result.data.length)
    			if (result.data.length > 0) {
    				for (var i in result.data) {
    					$("#tipsContent").append($('<li></li>').append((eval(i) + 1) + ":" + result.data[i].name)).append("<br>")
    				}
    			}
    		}
    	}
    })
</script>