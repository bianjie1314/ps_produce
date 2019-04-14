

(function($) {
	$(function() {
		initUI();
     });
	
    function initUI() {
        var table = $('#datatable').DataTable({
            "dom" : '<"toolbar-btn"> <"toolbar-aws" <"toolbar-search"f> <"toolbar-refresh">>tr<"row"<"col-xs-6"<"col-xs-6"l><"col-xs-6"i>><"col-xs-6"p>>',
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
            "sAjaxSource" : ctx + '/score/personList',
            "fnServerData" : function(sSource, aoData, fnCallback) {
                for ( var d in aoData) {
                    if (aoData[d].name == "sSearch") {
                        aoData[d].value = encodeURI(aoData[d].value);
                    }
                }
                if($("#isProduction").val()){
                    aoData.push({"name": "term", "value": $("#isProduction").val()});
                }else{
                    aoData.push({"name": "term", "value": "0"});
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
            "aoColumns" : [ 
                {
            	"title" : "科目",
            	"mData" : function(data,type,row){
            		if(data.state==1){
            			return data.course_name;
            		}else if(data.state==2){
            			return data.course_name+"(补考)"
            		}else{
            			return data.course_name+"(重修)";
            		}
            	}
                
            },
            {
            	"title" : "分数",
                "mData" : 'score'
            }
            ],
            "footerCallback" : function(row, data, start, end, display) {
            },
            initComplete : function() {
            	
            }
            
        });
        var toolbar = $('<ul class="dropdown-menu"></ul>');
        $("div.toolbar-btn").append(toolbar);
        $.bindEvent(table);
        var isProduction = '<div class="input-group"><select class="form-control" id="isProduction"><option value="0">请选择</option><option value="1">第一学期</option><option value="2">第二学期</option><option value="3">第三学期</option><option value="4">第四学期</option><option value="5">第五学期</option><option value="6">第六学期</option></select></div>';
        $('#datatable_filter').prepend(isProduction);
        $('#datatable_filter').find('#isProduction').change(function () {
            table.ajax.reload(null, false);
        });
       
       $('#add').click(function() {
    	   var $f = $('<div role="form" class="row"></div>');
    	     $f.append('<div class="form-group col-md-4"><div class="input-group "><label class="input-group-addon">专业</label><select dialog-id="office" class="form-control" onclick="loadClazz(this)"></select></div> </div>');
  		     $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">班级</label><select id="clazz" class="form-control" onclick="loadUser(this)"></select></div> </div>');
  		     $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">课程</label><select dialog-id="course" class="form-control" "></select></div> </div>');
    		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">姓名</label><select id="name" class="form-control" "></select></div> </div>');
    		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">分数</label><input dialog-id="score" class="form-control" required></div> </div>');
    		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">类型</label><select id="state" class="form-control" "><option value="1">统考</option><option value="2">补考</option><option value="3">重修</option></select></div> </div>');
    		 //$f.append('<div class="form-group col-md-6"><div class="input-group">点击到<a href="'+ctx+'/system/dict">字典页</a>添加主题</div></div>');
     		for(i in offices){
     			 var c=offices[i]
     			 $f.find('[dialog-id=office]').append('<option value="'+c.id+'">'+c.name+'</option>');
              }
   		 for(i in courses){
     			 var c=courses[i]
     			 $f.find('[dialog-id=course]').append('<option value="'+c.id+'">'+c.courseName+'</option>');
              }
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
    	         	   var officeId=$f.find("[dialog-id=office]").val();
    	         	   var courseId=$f.find("[dialog-id=course]").val();
    	         	   var score=$f.find("[dialog-id=score]").val();
    	         	   var stuId=$f.find("#name").val();
    	         	  var clazzId=$f.find("#clazz").val();
    	         	  var state=$f.find("#state").val();
    	         	  if(!clazzId){
    	         		 $.messager.popup("请选班级!");
     	         		   return false; 
    	         	  }
    	         	   if(!stuId) {
   	         		   $.messager.popup("请选择学生!");
   	         		   return false;
   	         	   }
    	         	 if(!officeId) {
     	         		   $.messager.popup("请选择专业!");
     	         		   return false;
     	         	   }
    	         	   if(!score) {
    	        		   $.messager.popup("请填写分数!");
    	        		   return false;
    	        	   }
    	         	   var param = {
    	         			  officeId:officeId,
    	     			      stuId:stuId,
    	     			      courseId:courseId,
    	     			      score:score,
    	     			      clazzId:clazzId,
    	     			      state:state
    	         	   }
    	         	   $.ajax({
    	                    url : ctx + "/score/save",
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
         
       });
       
    }
})(jQuery);
