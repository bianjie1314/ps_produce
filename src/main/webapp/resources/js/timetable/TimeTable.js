function loadClazz(_this){
	   
	   $("#clazz").html("");
		for(i in clazzs){
			 var c=clazzs[i]
			 if(c.officeId==$(_this).val())
			 $("#clazz").append('<option value="'+c.id+'">'+c.clazzName+'</option>');
       } 
	}
  
		

		for(i in offices){
			 var c=offices[i]
			 $("#office").append('<option value="'+c.id+'">'+c.name+'</option>');
			 
		}
    }
   
	
    
