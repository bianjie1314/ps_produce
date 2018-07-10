(function($) {
    $(function() {
        initUI();
        queryType();
        $.myAjax({
	   		sUrl:ctx + "/template",
	   		sType:"get",
	   		fnSuccess:function(result){
	   			if(result.ret == 0&&result.data){
	   					var categorys = $('[name=category]');
	   					categorys.append('<option value="">请选择类别</option>')

	   					for(var i in result.data) {
	   						categorys.append('<option value="'+result.data[i].id+'">'+result.data[i].category+'</option>')
						}
	   						   		
	   			}
	   		}
	   	});
        $('#no-startTime').datepicker({ dateFormat: 'yy-mm-dd'});
        $('#no-endTime').datepicker({ dateFormat: 'yy-mm-dd'});
    });
    function queryType(){
    	
    	$.ajax({
			url : ctx + "/template/queryType",
			type : "post",
			success : function(d) {
				var content=""
				for(var item in d){
					content+='<option value='+d[item]+'>'+d[item]+'</option>';
				}
				$('#category').html(content);
			}
		});
    }
    
    function initUI() {
        var table = $('#datatable').DataTable({
            "dom" : '<"toolbar-btn"> tr<"row"<"col-xs-6"<"col-xs-6"l><"col-xs-6"i>><"col-xs-6"p>>',
            "lengthMenu" : [ [ 50, 100, 200, 500, 2000, 1000000 ], [ 50, 100, 200, 500, 2000, "All" ] ],
            "bStateSave" : true,
            language: {
                search: "",
                searchPlaceholder: "Search..."
            },
            "bDeferRender" : true,
            "bServerSide" : false,
            "bProcessing":true,
            'bAutoWidth' : false,
            "aoColumnDefs": [
            	{ 'bSortable': false, 'aTargets': [ 0 ],"sWidth": "50px"}
             ],
            "createdRow" : function(row, data, dataIndex) {
                row.id = data.id
            },
            "sAjaxSource" : ctx + '/template/source',
            "fnServerData" : function(sSource, aoData, fnCallback) {
            	var productType=$('[name=category]').val();
            	var productName=$('[name=productName]').val();
            	var startDate=$('[name=startDate]').val();
            	var endDate=$('[name=endDate]').val();
                if(productType){                	
                    aoData.push({ "name": "productType", "value":encodeURI(productType)});
                }
                if(productName){                	
                    aoData.push({ "name": "productName", "value":encodeURI(productName)});
                }
                if(startDate){                	
                    aoData.push({ "name": "startDate", "value":encodeURI(startDate)});
                }
                if(endDate){                	
                    aoData.push({ "name": "endDate", "value":encodeURI(endDate)});
                }
                $.ajax({
                    "data" : aoData,
                    "contentType" : "application/json; charset=utf-8",
                    "success" : fnCallback,
                    "url" : sSource
                });
            },
            "fnDrawCallback" : function() {
            },
            "aoColumns" : [{
                "title" : "产品类别",
                 "mData" : 'productType'
            }, {
            	"title" : "SKU",
                "mData" : 'sku'
            }, {
            	"title" : "产品名称",
                "mData" : 'productName'
            }, {
            	"title" : "模具效果图",
                "mData" : function(data,type,row){
                	imgs=data.tempImgs.split(';');
                	var content="";
                	for(var i=0;i<imgs.length;i++){
                		content+='<img src='+imgs[i]+' class="img-thumbnail">';
                	}
                	return content;
                }
            }],
            "footerCallback" : function(row, data, start, end, display) {
            },
            initComplete : function() {
            }
        });
        $('#search').click(function(event) { 
			table.ajax.reload();
		});
       
       var toolbar =$('<ul class="dropdown-menu"></ul>');
       toolbar.append('<li><a href="#" id="del">删除</a></li>');
       toolbar.append('<li><a href="#" id="edit">修改</a></li>');
    
       $("div.toolbar-btn").append('<button  class=" btn btn-primary " id="add">创建模具</button> ');
       $("div.toolbar-btn").append(toolbar);
       $.bindEvent(table);//绑定各种事件
       
       $('#add').click(function() {
    	
           var $f = $('<div role="form"></div>');
           $f.append('<p>申请更多模具请与我们的客服联系</p>');
        
    	   $f.dialog({
               title : "修改字典",
               onClose : function() {
                   $(this).dialog("destroy");
               }
           });
	    });
      
       
    }
})(jQuery);
