var roletables, roleModuleTreeObj,selectRoleid;
$(function() {
	roletables = $('#role_list')
			.DataTable(
					{
						"processing" : true,
						"serverSide" : true,
						"language" : {
							"url" : 'js/jQuery/datatables/i18.json'
						},
						"ajax" : {
							"url" : "role/list",
							"type" : "POST"
						},
						"columns" : [
								{
									"data" : "roleid",
									"render" : function(data, type, full, meta) {
										return '<input type="checkbox" class="role_check_child"  value="'
												+ data + '" />';
									},
									"bSortable" : false
								}, {
									data : 'rolename'
								},{
									"data" : "roleid",
									"render" : function(data, type, full, meta) {
										return '<a href="javascript:role_permission_modal('+data+')"><i class="fa fa-user-plus"></i>权限配置</a>';
									},
									"bSortable" : false
								}],
						"paging" : true,
						"lengthChange" : false,
						"searching" : false,
						"ordering" : false,
						"info" : true,
						"autoWidth" : false
					});

	
	 $("#role_check_all").on("click", function () {
         if ($(this).prop("checked") === true) {
             $(".role_check_child").prop("checked", $(this).prop("checked"));
             $('#role_list tbody tr').addClass('selected');
         } else {
             $(".role_check_child").prop("checked", false);
             $('#role_list tbody tr').removeClass('selected');
         }
     });
	 $('#role_list tbody').on('click', '.role_check_child', function () {
         var $tr = $(this).parents('tr');
         $tr.toggleClass('selected');
         var $tmp = $('.role_check_child');
         $('#role_check_all').prop('checked', $tmp.length == $tmp.filter(':checked').length);

     });
	 
	 //form validate
	 $('#role_edit_form').bootstrapValidator({
	        message: 'This value is not valid',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        excluded: [':disabled'],
	        fields: {
	          
	            rolename: {
	                validators: {
	                	regexp: {
                            regexp: /[^\x00-\xff]/,
                            message: '只能输入中文字符.'
                        },
	                    notEmpty: {
	                        message: '角色名不能为空'
	                    }
	            
	                }
	            }
	        }
	    });
	 
});
function toDate(jsonDate) {
	var date = new Date(jsonDate).toLocaleString(); // parsedIntDate passed to
	return date;
}

// 修改角色
function role_edit_modal(type) {
	if(type=="add"){
		$('#role_edit_title').html('<i class="fa fa-street-view"> </i>新增角色');
		$('#role_edit_modal').modal('show');
	}else{
		$('#role_edit_title').html('<i class="fa fa-street-view"> </i>修改角色');
		if(roletables.rows('.selected').data().length == 1){
	    	$('#role_edit_modal').modal('show');
	    	var data = roletables.row('.selected').data();
	    	delete data.password;
	    	$("#role_edit_form").fillForm(data);
	    }else{
	    	toastr.warning('请选择一列进行操作');
	    	return;
	    }
	}
    
}
function role_remove_modal(){
	if(roletables.rows('.selected').data().length == 1){
		$('#role_confirm_modal').modal('show');
	}else{
    	toastr.warning('请选择一列进行操作');
    	return;
  }
}

function role_remove_submit(){
	 $.ajax({
			type : "POST",
			url : "role/remove/"+roletables.row('.selected').data().roleid,
			success : function(data) {
				toastr.success('操作成功！');
				roletables.draw();
			}
	});
	 $('#role_confirm_modal').modal('hide');
}


// 角色增加提交
function role_edit_submit() {
	
	var bootstrapValidator = $("#role_edit_form").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){
		$.ajax({
			type : "POST",
			url : "role/edit",
			data : $('#role_edit_form').serialize(),// 序列化表单值  
			success : function(data) {
				$('#role_edit_modal').modal('hide');
				toastr.success('操作成功！');
				roletables.draw();
			}
		});
	}
}

$('#role_edit_modal').on('hide.bs.modal', function () {
	$("INPUT[name='roleid']").val("");
	$('#role_edit_form').data('bootstrapValidator').resetForm(true);
})

//权限配置
var setting = {
	async: {
		enable: true,
		url: "module/list",
		autoParam: ["id", "name"]
	},
    check: {
        enable: true
		//chkboxType: { "Y": "p", "N": "s" }
    },
    data: {
        simpleData: {
            enable: true,
            idKey: 'moduleid',
            pIdKey: 'parentid'
        },
        key: {
        	name: "modulename"
		}
    }
};
function role_permission_modal(roleid){
	selectRoleid = roleid;
	console.info(roleid);
	$('#role_permission_modal').modal('show');
	roleModuleTreeObj = $.fn.zTree.init($("#role_module_tree"), setting);
	$.ajax({
		url : "role/module/"+selectRoleid,
		success : function(data) {
			if(data!= null&& data.length >0){
				for(var i = 0;i++;i<data.length){
					var node = roleModuleTreeObj.getNodeByParam("moduleid",data.moduleid, null); 
					roleModuleTreeObj.selectNode(node);
				}
			}
		}
	});
	
	
	
}

function role_permission_submit(){
	var nodes = roleModuleTreeObj.getSelectedNodes();
	var modules = []; 
	for(var i=0;i<nodes.length;i++){
		modules.push(nodes[i].moduleid); //获取选中节点的值
	}
	$.ajax({
		type : "POST",
		url : "role/module/edit"/+selectRoleid,
		data:modules,
		success : function(data) {
			console.info(11111111111);
		}
	});

}