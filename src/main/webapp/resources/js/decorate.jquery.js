$.extend({
	getRequestByUrl: function(panStr, url) { //解码获取URL参数
        var vStr = "",
            sorStr = url || location.href || window.location.href;
        if (sorStr == null || sorStr == "" || panStr == null || panStr == "") {
            return vStr
        }
        panStr += "=";
        var itmp = sorStr.indexOf(panStr);
        if (itmp < 0) {
            return vStr
        }
        sorStr = sorStr.substr(itmp + panStr.length);
        itmp = sorStr.indexOf("&");
        if (itmp < 0) {
            return decodeURIComponent(sorStr)
        } else {
            return decodeURIComponent(sorStr.substr(0, itmp))
        }
    },
    
   
    
	 bindEvent:function(table){
	     $("div.toolbar-refresh").append('<button  class=" glyphicon glyphicon-refresh"></button>');

	       $('div.toolbar-refresh').on('click', 'button', function(e) {
	    	   table.ajax.reload();
	       }); 
		 $('#datatable tbody').on('click', 'tr', function() {
	            var tn = $(this).closest('table').eq(0).attr('id');
	            if (tn != 'datatable')
	                return false;
	            if ($(this).hasClass('selected')) {
	                $(this).removeClass('selected');
	                $(this).find("[name=check-item]:checkbox").prop("checked",false);
	            } else {
	                $(this).addClass('selected');
	                $(this).find("[name=check-item]:checkbox").prop("checked",true);

	            }
	        });
	       
	        $(".btn-dropdown").on('click', function (e) {
	        	var e = event || window.event;
	        	var dropdown=$("div.toolbar-btn").find('.dropdown-menu');
	        	$(dropdown).css("left",e.clientX+50-e.offsetX+'px');
	            $(dropdown).css("top", e.clientY+34-e.offsetY +'px');
	            $(dropdown).css("display",'block');
	      })
	      $("body").on('click', function (e) {
	    	if(!$(e.target).hasClass('btn-dropdown'))
	    		var dropdown=$("div.toolbar-btn").find('.dropdown-menu');
	            $(dropdown).css("display",'none');
	      })
	      $('#datatable tbody').on('contextmenu', 'tr', function(e) {
	        	var dropdown=$("div.toolbar-btn").find('.dropdown-menu');
	        	var display =$(dropdown).css("display");
	        	if(!$(e.target).hasClass('dropdown-menu')){
	        		 var rows = table.rows({
	                     page : 'current'
	                 });
	                 var nodes = rows.nodes();
	                 $.each(nodes, function() {
	                     var tr = $(this).closest('tr');
	                     tr.removeClass('selected');
	                     $("[name=check-item]:checkbox").prop("checked",false);
	                     $("[name=check-all]:checkbox").prop("checked",false);
	                 });
	            	$(this).addClass('selected');
	            	$(this).find("[name=check-item]:checkbox").prop("checked",true);
	            	var e = event || window.event;
	           	 	$(dropdown).css("left",e.pageX+'px');
	                $(dropdown).css("top", e.pageY +'px');
	                $(dropdown).css("display",'block');
	                return false;
	        	}
	   	
	        }); 
	        // 全选、取消选择。
	        $('[name=check-all]:checkbox').on('click',  function(e) { 
	        	var t = this;
	        	$("[name=check-item]:checkbox").prop("checked",this.checked);
	            var rows = table.rows({
	                page : 'current'
	            });
	            var nodes = rows.nodes();
	            var text = $("#check-all").text();
	            if (this.checked) {
	            	$.each(nodes, function() {
	                    var tr = $(this).closest('tr');
	                    tr.addClass('selected');
	                });
	               
	            } else {
	            	 $.each(nodes, function() {
	                     var tr = $(this).closest('tr');
	                     tr.removeClass('selected');
	                 });
	                
	            }
	           
	        });
	 },
	 checkPermissions : function(permissions) {
		 var lis = $("div.toolbar-btn").find("button,li,span")
	     var jsonArr = new Array();
	     $.each(permissions, function(){
	     	jsonArr.push(String(this))
		 });
	     for (var i=0; i < lis.length; i++) {
	     	var $li = $(lis[i])
	     	if ($li.attr("data-permissions")) {
	     		console.log($.inArray($li.attr("data-permissions"),jsonArr))
	         	if ($.inArray($li.attr("data-permissions"),jsonArr) == -1){
	         		$li.addClass("hide");
	         	}
	     	}
	     }
	 },
     
	myTrim : function (str) {
		return str.replace(/^\s+|\s+$/gm,'');
	},
	 
	stockStatus : function(data ){
		var result='<span class="label label-info">待采购</span>';
		if(data.status==-2)
			result = '<span class="label label-danger">审核失败</span>';
		if(data.status==-1)
			result = '<span class="label label-warning">待审核</span>';
		if(data.status==1)
			result = '<span class="label label-info">待采购</span>';
		if(data.status==2)
			result= '<span class="label label-danger">已采购</span>';
		if(data.status==3)
			result = '<span class="label label-info">库存发货</span>';
		if(data.status==4)
			result = '<span class="label label-info">已到货</span>';
		result +='<a tabindex="0" class="btn btn-xs btn-success" role="button" data-toggle="popover" data-trigger="focus" data-placement="left" productId="'+data.productId+'" asin="'+data.asin+'" site="'+data.site+'">销量</a>';
		return result;
	},validate : function(form ){
		var result=true;
		form.find("input,select,textarea").filter('[required]:visible').each(
 			   function(index){  
 				   if(!$(this).val()){
 			         $(this).addClass("error");
 			         $(this).blur(function(){
 			        	 if($(this).val())
 			        		 $(this).removeClass("error");
 			        });
 			         result=false;
 				   }
 			   }
 	    );
		return result;
	},
	decorate : function(data,pairs ){
		for ( var i in pairs) {
			var value = pairs[i].value
			if (pairs[i].value == data)
				return pairs[i].label;
		}
		return "未知";
	},
	copy:function(t,id){
	    var e=document.getElementById(id);//对象是content 
	    e.select(); //选择对象 
	    document.execCommand("Copy"); //执行浏览器复制命令
	    var text = $(t).text();
	    $(t).text(text+"√");
	},
	formatDate:function (datestr, fmt) {
		var date=new Date(datestr);
		 function pad(value) {
		        return (value.toString().length < 2) ? '0' + value : value;
		    }
		    return fmt.replace(/%([a-zA-Z])/g, function (_, fmtCode) {
		        switch (fmtCode) {
		        case 'Y':
		            return date.getFullYear();
		        case 'M':
		            return pad(date.getMonth() + 1);
		        case 'd':
		            return pad(date.getDate());
		        case 'H':
		            return pad(date.getHours());
		        case 'm':
		            return pad(date.getMinutes());
		        case 's':
		            return pad(date.getSeconds());
		        default:
		            throw new Error('Unsupported format code: ' + fmtCode);
		        }
		    });
	},
	 preview:function(_this){
		if(_this.src){
			if($(_this).next().length==0){
				$(_this).after('<div style="position: absolute;z-index: 99;" ><img style="width: 300px;" src="'+_this.src+'"></div>')
			}else{
				$(_this).next().remove();
			}
			
		}
			
		return false;
	},
	 previewRedirect:function(_this){
		if(_this.href){
			var url =window.btoa(_this.href);
			window.open(ctx+"/image/preview/"+url); 
		}
			
		return false;
	},
	
});

!(function ($) {
	
	    $.cookie = function(name, value, options) {
	        if (typeof value != "undefined") {
	            options = options || {};
	            if (value === null) {
	                value = "";
	                options.expires = -1
	            }
	            var expires = "";
	            if (options.expires && (typeof options.expires == "number" || options.expires.toUTCString)) {
	                var date;
	                if (typeof options.expires == "number") {
	                    date = new Date();
	                    date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000))
	                } else {
	                    date = options.expires
	                }
	                expires = "; expires=" + date.toUTCString()
	            }
	            var path = options.path ? "; path=" + options.path: "";
	            var domain = options.domain ? "; domain=" + options.domain: "";
	            var secure = options.secure ? "; secure": "";
	            document.cookie = [name, "=", encodeURIComponent(value), expires, path, domain, secure].join("")
	        } else {
	            var cookieValue = null;
	            if (document.cookie && document.cookie != "") {
	                var cookies = document.cookie.split(";");
	                for (var i = 0; i < cookies.length; i++) {
	                    var cookie = jQuery.trim(cookies[i]);
	                    if (cookie.substring(0, name.length + 1) == (name + "=")) {
	                        cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
	                        break
	                    }
	                }
	            }
	            return cookieValue
	        }
	    };
	})(jQuery);

