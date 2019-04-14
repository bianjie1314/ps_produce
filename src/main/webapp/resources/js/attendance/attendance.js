
function loadClazz(_this){
	   $("#clazz").html("");
		for(i in clazzs){
 			 var c=clazzs[i]
 			 if(c.officeId==$(_this).val())
 			 $("#clazz").append('<option value="'+c.id+'">'+c.clazzName+'</option>');
          } 
	}
function loadUser(_this){
	   $("#name").html("");
		for(i in students){
			 var c=students[i]
			 if(c.clazzId==$(_this).val())
			 $("#name").append('<option value="'+c.id+'">'+c.name+'</option>');
       } 
	}
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
            "sAjaxSource" : ctx + '/attendance/source',
            "fnServerData" : function(sSource, aoData, fnCallback) {
                for ( var d in aoData) {
                    if (aoData[d].name == "sSearch") {
                        aoData[d].value = encodeURI(aoData[d].value);
                    }
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
                "title" : "<input type='checkbox' name='check-all'>",
                "mRender" : function(data, type, row) {
                    return "<input type='checkbox' name='check-item'>";
                }
            },{
                "title" : "专业",
                 "mData" : 'officeName'
                 
            }, {
            	"title" : "学生",
                "mData" : 'stuName'
            }, {
            	"title" : "创建时间",
                "mData" : function(data, type, row) { if(data.createDate==null)return ""; return $.formatDate(data.createDate,'%Y-%M-%d %H:%m:%s')}
            }, 
            {
            	"title" : "班级",
            	"mData" : function(data,type,row){
                    for(i in clazzs){
   				  var c=clazzs[i]
   				  if(c.id==data.clazzId){
   					 return c.clazzName;
   				    }
   			
                   }
                   return ""; 
                   }},{
            	"title" : "缺勤时间",
            	"mData" : 'absenceTime'
            },
            {
            	"title" : "缺勤课程",
            	"mData" : 'courseName'}
            ,
             {
            	"title" : "备注",
                "mData" : 'remarks'
            }],
            "footerCallback" : function(row, data, start, end, display) {
            },
            initComplete : function() {
            }
        });
        
       var toolbar =$('<ul class="dropdown-menu"></ul>');
       toolbar.append('<li><a href="#" id="del">删除</a></li>');
       toolbar.append('<li><a href="#" id="edit">修改</a></li>');
    
       $("div.toolbar-btn").append('<button  class=" btn btn-primary " id="add">添加</button> ');
       $("div.toolbar-btn").append('<button  class=" btn btn-default btn-dropdown">更多操作</button> ');
       $("div.toolbar-btn").append(toolbar);
       $.bindEvent(table);//绑定各种事件
       
       $('#del').click(function() {
			var rows = table.rows('.selected');
			var data = rows.data();
			var ids = new Array();
			for(var i=0;i<data.length;i++){	
				ids.push(data[i].id);
			}	
			if(ids.length<1){
				$.messager.popup 
				("请选择任务!");
				return false;
			}
			$.messager.model = { 
		        ok:{ text: "确定",'classed': ' btn-primary' },
		        cancel: { text: "取消", 'classed': ' btn-default' }
	        };
		    $.messager.confirm("", "确认选中"+ids.length+"删除？", function() { 
		    	  $.ajax({
			 		   url : ctx + "/attendance/delete",
			 		   type:"post",			 		  
			 		   data :"ids="+ids.join(),
			 		   success : function(d) {	
			 			   table.ajax.reload();
			 			   $.messager.popup("删除成功!");
			 		   }
				});
		    });
	    });
       $('#edit').click(function() {
    	   var rows = table.rows('.selected');
		   var datas = rows.data();
		   var data=datas[0];
    	   var $f = $('<div role="form" class="row"></div>');
  		 $f.append('<div class="form-group col-md-12"><div class="input-group"><label class="input-group-addon">缺勤时间</label><input type="text"  style="width: 300px" name="time"   id="reservation1" class="form-control"/></div> </div>')
  	     $f.append('<div class="form-group col-md-4"><div class="input-group "><label class="input-group-addon">专业</label><select dialog-id="office" class="form-control" onclick="loadClazz(this)"></select></div> </div>');
  		$f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">班级</label><select id="clazz" class="form-control" onclick="loadUser(this)"></select></div> </div>');
  		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">姓名</label><select id="name" class="form-control"></select></div> </div>');
   		$f.append('<div class="form-group col-md-12"><div class="input-group"><label class="input-group-addon">备注</label><input dialog-id="remarks" class="form-control" required></div></div>');
   		$f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">课程</label><select dialog-id="course" class="form-control" "></select></div> </div>');
  		 for(i in offices){
    			 var c=offices[i]
    			 $f.find('[dialog-id=office]').append('<option value="'+c.id+'">'+c.name+'</option>');
             }
  		
  		 for(i in courses){
    			 var c=courses[i]
    			 $f.find('[dialog-id=course]').append('<option value="'+c.id+'">'+c.courseName+'</option>');
             }
  		$f.find("#reservation1").val(data.absenceTime);
  		for(i in clazzs){
			 var c=clazzs[i]
			 if(c.officeId==data.officeId)
				 $f.find("#clazz").append('<option value="'+c.id+'">'+c.clazzName+'</option>');
         }
  		$f.find("#name").html('<option value="'+data.stuId+'">'+data.stuName+'</option>');
  		$f.find("#clazz").val(data.clazzId);
  		$f.find('[dialog-id=office]').val(data.officeId);
  		$f.find('[dialog-id=remarks]').val(data.remarks);
  		$f.find('[dialog-id=course]').val(data.courseId);
  		 $f.find('#reservation1').daterangepicker({
  			 timePicker: true, //显示时间
  		        timePicker24Hour: true, //时间制
  		        timePickerSeconds: true, //时间显示到秒
  		        format: 'YYYY-MM-DD HH:mm:ss',
  		        startDate: new Date(),
  		        endDate: new Date(),
  		        minDate:1999-12-12,
  		        maxDate:2050-12-30,
  		        timePicker: true,
  		        timePickerIncrement: 1,
  		 
  		        locale:{
  		            applyLabel: "确认",
  		            cancelLabel: "取消",
  		            resetLabel: "重置",
  		 
  		        }
  		    }
  		, function(start, end, label) {
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
  	         	   var clazz=$f.find("#clazz").val();
  	         	   var officeId=$f.find("[dialog-id=office]").val();
  	         	   var officeName=$f.find("[dialog-id=office]").find("option:selected").text();
  	         	   var courseName=$f.find("[dialog-id=course]").find("option:selected").text();
  	         	  var courseId=$f.find("[dialog-id=course]").val();
  	         	   var stuId=$f.find("#name").val();
  	         	   var stuName=$f.find("#name").find("option:selected").text();
  	         	   var absenceTime= $f.find("#reservation1").val();
  	         	  if(!stuId) {
 	         		   $.messager.popup("请选择学生!");
 	         		   return false;
 	         	   }
  	         	 if(!officeId) {
   	         		   $.messager.popup("请选择专业!");
   	         		   return false;
   	         	   }
  	         	   if(!remarks) {
  	         		   $.messager.popup("请填写原因!");
  	         		   return false;
  	         	   }
  	         	  if(!absenceTime) {
  	        		   $.messager.popup("请选择时间段!");
  	        		   return false;
  	        	   }
  	         	   var param = {
  	         			   id:data.id,
  	         			  remarks : remarks,
  	         			  absenceTime:absenceTime,
  	     			      officeId:officeId,
  	     			      officeName:officeName,
  	     			      stuName:stuName,
  	     			      stuId:stuId,
  	     			      courseName:courseName,
  	     			      courseId:courseId,
  	     			      clazzId:clazz
  	         	   }
  	         	   $.ajax({
  	                    url : ctx + "/attendance/update",
  	                    type : "POST",
  	                    contentType : 'application/json; charset=utf-8',
  	                    dataType : 'json',
  	                    data : JSON.stringify(param),
  	                    success : function(result) {
  	                        if (result.ret == 1) {
  	                            $.messager.popup("更新成功!");
  	                        } else {
  	                        	   $.messager.popup("更新失败!");
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
       
       $('#add').click(function() {
    	   var $f = $('<div role="form" class="row"></div>');
    		 $f.append('<div class="form-group col-md-12"><div class="input-group"><label class="input-group-addon">缺勤时间</label><input type="text"  style="width: 300px" name="time"   id="reservation1" class="form-control"/></div> </div>')
    	     $f.append('<div class="form-group col-md-4"><div class="input-group "><label class="input-group-addon">专业</label><select dialog-id="office" class="form-control" onclick="loadClazz(this)"></select></div> </div>');
    		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">班级</label><select id="clazz" class="form-control" onclick="loadUser(this)"></select></div> </div>');
    		 $f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">姓名</label><select id="name" class="form-control" "></select></div> </div>');
     		$f.append('<div class="form-group col-md-12"><div class="input-group"><label class="input-group-addon">备注</label><input dialog-id="remarks" class="form-control" required></div></div>');
     		$f.append('<div class="form-group col-md-4"><div class="input-group"><label class="input-group-addon">课程</label><select dialog-id="course" class="form-control" "></select></div> </div>');
    		 for(i in offices){
      			 var c=offices[i]
      			 $f.find('[dialog-id=office]').append('<option value="'+c.id+'">'+c.name+'</option>');
               }
    		 for(i in courses){
      			 var c=courses[i]
      			 $f.find('[dialog-id=course]').append('<option value="'+c.id+'">'+c.courseName+'</option>');
               }
    		 $f.find('#reservation1').daterangepicker({
    			 timePicker: true, //显示时间
    		        timePicker24Hour: true, //时间制
    		        timePickerSeconds: true, //时间显示到秒
    		        format: 'YYYY-MM-DD HH:mm:ss',
    		        startDate: new Date(),
    		        endDate: new Date(),
    		        minDate:1999-12-12,
    		        maxDate:2050-12-30,
    		        timePicker: true,
    		        timePickerIncrement: 1,
    		 
    		        locale:{
    		            applyLabel: "确认",
    		            cancelLabel: "取消",
    		            resetLabel: "重置",
    		 
    		        }
    		    }
    		, function(start, end, label) {
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
    	         	   var officeId=$f.find("[dialog-id=office]").val();
    	         	   var officeName=$f.find("[dialog-id=office]").find("option:selected").text();
    	         	   var courseName=$f.find("[dialog-id=course]").find("option:selected").text();
    	         	  var courseId=$f.find("[dialog-id=course]").val();
    	         	   var stuId=$f.find("#name").val();
    	         	   var stuName=$f.find("#name").find("option:selected").text();
    	         	   var absenceTime= $f.find("#reservation1").val();
    	         	   var clazzId=$f.find("#clazz").val();
    	         	  if(!stuId) {
   	         		   $.messager.popup("请选择学生!");
   	         		   return false;
   	         	   }
    	         	  if(!clazzId){
    	         		 $.messager.popup("请选择班级!");
   	         		   return false;
    	         		  
    	         	  }
    	         	 if(!officeId) {
     	         		   $.messager.popup("请选择专业!");
     	         		   return false;
     	         	   }
    	         	   if(!remarks) {
    	         		   $.messager.popup("请填写原因!");
    	         		   return false;
    	         	   }
    	         	  if(!absenceTime) {
    	        		   $.messager.popup("请选择时间段!");
    	        		   return false;
    	        	   }
    	         	   var param = {
    	         			  remarks : remarks,
    	         			  absenceTime:absenceTime,
    	     			      officeId:officeId,
    	     			      officeName:officeName,
    	     			      stuName:stuName,
    	     			      stuId:stuId,
    	     			      courseName:courseName,
    	     			      courseId:courseId,
    	     			      clazzId:clazzId
    	         	   }
    	         	   $.ajax({
    	                    url : ctx + "/attendance/save",
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
