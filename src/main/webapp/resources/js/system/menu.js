(function($) {
	$(function() {
		initUI();
	});
	function initUI() {
		$('#add').click(function() {
			window.location.href = ctx + "/system/menu/form";
		});
	}
})(jQuery);

function del(id) {
	$.messager.model = {
		ok : {
			text : "确定",
			'classed' : ' btn-primary'
		},
		cancel : {
			text : "取消",
			'classed' : ' btn-default'
		}
	};
	$.messager.confirm("", "确认删除菜单,并删除其所有子菜单？", function() {
		$.ajax({
			url : ctx + "/system/menu/delete",
			type : "post",
			data : "ids=" + id,
			success : function(d) {
				$.messager.popup("删除成功!");
				window.location.href = ctx + "/system/menu"
			}
		});
	});
}
