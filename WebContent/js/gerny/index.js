/** zorro 2017.4 **/
toastr.options = {
	"positionClass":'toast-top-center',
	"closeButton": true
};
//对象扩展
(function($){
	
		// tabs 扩展对象
	   $.fn.addTab = function(code,title,url){
		 var currentDiv =  $("#tabs-"+code);
		
		 if(currentDiv.length > 0){
			 var index = currentDiv.index();
			 $(this).tabs( "option", "active",index-1);
			 currentDiv.children().attr("src",url);
		 }else{
			 var ul = $(this).children("ul");
			 //TODO 判断已存在选项卡
			 var tabtitle = '<li class="nav nav-tabs"><a href="#tabs-'+code+'">'+title+'</a><span onclick="close_tabs(this)" class="ui-icon ui-icon-closethick"></span></li>';
			 
			 var content = '<div id="tabs-'+code+'" class="tab-pane">';
			 /*content+='<iframe scrolling="auto" src="'+url+'" frameborder="0" style="width:100%;height:100%;">';
			 content+='</iframe></div>';*/
			 content+='</div>';
			 $(this).children("ul").append(tabtitle);
			 var index = ul.children().length -1;
			 $(this).append(content);
			 this.tabs( "refresh" );
			 $(this).tabs( "option", "active",index);
			 $("#tabs-"+code).load(url);
		 }
		
	   }
	   
	   
	   
	   //对象
	   $.fn.fillForm=function(obj){  
		   var formid="#"+$(this).attr("id") ;//input,textarea,select;  
		  /* var objs=jsonstring.split(',');  
		   console.info(objs);*/
		   
		   $.each(obj, function(name, val) {  
		     var k= name;  
		     var v= $.trim(val);  
		     //alert(k+"  "+v);  
		     selector="[name='"+k+"']";  
		     //alert(selector);  
		     //alert($(selector).length);  
		     if($(selector).length>0){  
		       for(j=0;j<$(selector).length;j++){  
		          //text or password   
		          if($(selector).attr("type")=="text"||$(selector).attr("type")=="password"||$(selector).attr("type")=="hidden"){  
		             $(selector).val(v);  
		          }  
		          //combo select   
		          if($(selector).attr("type")==null&&$(selector).length==1){  
		              if($("select"+selector).length==1){  
		                  for(n=0;n<$(selector+" option").length;n++){  
		                    //alert(n+":"+$(selector+" option:eq("+n+")").val()+"["+$(selector+" option:eq("+n+")").html()+"]==?=="+v);  
		                    if($(selector+" option:eq("+n+")").val()==v){  
		                       $(selector+" option:eq("+n+")").attr("selected",true);  
		                       break;  
		                    }  
		                  };  
		              }  
		          }  
		          // checkbox  
		          if($(selector).attr("type")=="checkbox"){  
		             var checkboxselector="input[type='checkbox'][name='"+k+"']";  
		             var options=v.split('|');  
		             for(m=0;m<options.length;m++){  
		                for(k=0;k<$(checkboxselector).length;k++)  
		                {  
		                   if($(checkboxselector+":eq("+k+")").val()==options[m]){  
		                      $(checkboxselector+":eq("+k+")").attr("checked",true);  
		                   }  
		                }  
		             }  
		          }  
		          //readio  
		          if($(selector).attr("type")=="radio"){  
		             var radioselector="input[type='radio'][name='"+k+"']";  
		             for(k=0;k<$(radioselector).length;k++){  
		                if($(radioselector+":eq("+k+")").val()==v){  
		                   $(radioselector+":eq("+k+")").attr("checked",true);  
		                }  
		             }  
		          }  
		            
		         //textarea  
		          if($("textarea[name='"+k+"']").length==1){  
		              $("textarea[name='"+k+"']").val(v);  
		          }  
		            
		           
		       }  
		     }  
		       
		   });
		}  
	   
	})(jQuery)
	
function close_tabs(obj){
	var currentLi = $(obj).parent();
	var index = currentLi.index();
	var code = $(obj).prev().attr("href");
	$("#gerny_tabs").tabs( "option", "active",index-1);
	$(code).remove();
	currentLi.remove();
}   
function open_tab(code,title,url){
	var tabs = $('#gerny_tabs').tabs();
	tabs.addTab(code,title,url);
	
}




