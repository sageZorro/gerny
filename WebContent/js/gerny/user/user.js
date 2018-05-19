var usertables;
$(function() {
	usertables = $('#user_list')
			.DataTable(
					{
						"processing" : true,
						"serverSide" : true,
						"language" : {
							"url" : 'js/jQuery/datatables/i18.json'
						},
						"ajax" : {
							"url" : "user/list",
							"type" : "POST"
						},
						"columns" : [
								{
									"data" : "userid",
									"render" : function(data, type, full, meta) {
										return '<input type="checkbox" class="user_check_child"  value="'
												+ data + '" />';
									},
									"bSortable" : false
								}, {
									data : 'username'
								}, {
									data : 'truename'
								}, {
									data : 'phone'
								}, {
									data : 'mobile'
								}, {
									data : 'createdate'
								} ],
						"columnDefs" : [ {
							"render" : function(data, type, row) {
								return toDate(data);
							},
							"targets" : 5
						}, ],
						"paging" : true,
						"lengthChange" : false,
						"searching" : false,
						"ordering" : false,
						"info" : true,
						"autoWidth" : false
					});

	
	 $("#user_check_all").on("click", function () {
         if ($(this).prop("checked") === true) {
             $(".user_check_child").prop("checked", $(this).prop("checked"));
             $('#user_list tbody tr').addClass('selected');
         } else {
             $(".user_check_child").prop("checked", false);
             $('#user_list tbody tr').removeClass('selected');
         }
     });
	 $('#user_list tbody').on('click', '.user_check_child', function () {
         var $tr = $(this).parents('tr');
         $tr.toggleClass('selected');
         var $tmp = $('.user_check_child');
         $('#user_check_all').prop('checked', $tmp.length == $tmp.filter(':checked').length);

     });
	 
	 //form validate
	 $('#user_edit_form').bootstrapValidator({
	        message: 'This value is not valid',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        excluded: [':disabled'],
	        fields: {
	            username: {
	                validators: {
	                    notEmpty: {},
	                    stringLength: {
	                        min: 3,
	                        max: 20
	                    },
	                    regexp: {
	                        regexp: /^[a-zA-Z0-9_\.]+$/,
	                        message: '请输入英文用户名！'
	                    },
	                    different: {
	                        field: 'password',
	                        message: '用户名和密码不能相同！'
	                    }
	                }
	            },
	            truename: {
	                validators: {
	                	regexp: {
                            regexp: /[^\x00-\xff]/,
                            message: '只能输入中文字符.'
                        },
	                    notEmpty: {
	                        message: '用户名不能为空'
	                    }
	            
	                }
	            },
	            password: {
	                validators: {
	                    notEmpty: {},
	                    identical: {
	                        field: 'confirmPassword'
	                    },
	                    different: {
	                        field: 'username',
	                        message: '用户名和密码不能相同'
	                    }
	                }
	            },
	            mobile: {
	                validators: {
	                    notEmpty: {},
	                    digits: {},
	                    phone: {
	                        country: 'CN'
	                    }
	                }
	            },
	            phone: {
	                validators: {
	                    digits: {
	                    	message: '请输入有效的电话号码'
	                    },
	                }
	            }
	        }
	    });
	 
});
function toDate(jsonDate) {
	var date = new Date(jsonDate).toLocaleString(); // parsedIntDate passed to
	return date;
}

// 修改用户
function user_edit_modal(type) {
	if(type=="add"){
		$('#user_edit_title').html('<i class="fa fa-street-view"> </i>新增用户');
		$('#user_edit_modal').modal('show');
	}else{
		$('#user_edit_title').html('<i class="fa fa-street-view"> </i>修改用户');
		if(usertables.rows('.selected').data().length == 1){
	    	$('#user_edit_modal').modal('show');
	    	var data = usertables.row('.selected').data();
	    	delete data.password;
	    	$("#user_edit_form").fillForm(data);
	    }else{
	    	toastr.warning('请选择一列进行操作');
	    	return;
	    }
	}
    
}
function user_remove_modal(){
	if(usertables.rows('.selected').data().length == 1){
		$('#user_confirm_modal').modal('show');
	}else{
    	toastr.warning('请选择一列进行操作');
    	return;
  }
}

function user_remove_submit(){
	 $.ajax({
			type : "POST",
			url : "user/remove/"+usertables.row('.selected').data().userid,
			success : function(data) {
				toastr.success('操作成功！');
				usertables.draw();
			}
	});
	 $('#user_confirm_modal').modal('hide');
}


// 用户增加提交
function user_edit_submit() {
	
	var bootstrapValidator = $("#user_edit_form").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){
		$.ajax({
			type : "POST",
			url : "user/edit",
			data : $('#user_edit_form').serialize(),// 序列化表单值  
			success : function(data) {
				$('#user_edit_modal').modal('hide');
				toastr.success('操作成功！');
				usertables.draw();
			}
		});
	}
}

$('#user_edit_modal').on('hide.bs.modal', function () {
	$("INPUT[name='userid']").val("");
	$('#user_edit_form').data('bootstrapValidator').resetForm(true);
})
