<%@ page contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="<%=basePath%>js/gerny/user/user.js"></script>
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h4 class="box-title">用户管理</h4>
				</div>
				<div id="user_tips" style="display:none" class="alert alert-warning alert-dismissible" role="alert">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <strong>提示!</strong>请选择一列进行编辑！
				</div>
              <div class="margin">
              	  <div class="btn-group">
                  <button type="button" class="btn btn-success" data-toggle="modal" onclick="user_edit_modal('add')">新增</button>
                </div>
                <div class="btn-group">
                  <button type="button" class="btn btn-success"  onclick="user_edit_modal('edit')">修改</button>
                </div>
                <div class="btn-group">
                  <button type="button" class="btn btn-default" onclick="user_remove_modal()">删除</button>
                </div>
              
              </div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="user_list" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th><input id="user_check_all" type="checkbox"/></th>
								<th>用户名</th>
								<th>姓名</th>
								<th>电话</th>
								<th>手机</th>
								<th>创建日期</th>
							</tr>
						</thead>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</section>


<!-- Modal -->
<div class="modal fade" id="user_edit_modal" tabindex="-1" role="dialog" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 id="user_edit_title" class="modal-title"></h4>
      </div>
      <div class="modal-body">
         <form id="user_edit_form" role = "form" class="form-horizontal"> 
         		
         	  <input type = "hidden"  name = "userid"></input> 	
			  <div class = "form-group"> 
			   <label class="col-sm-2 control-label" for = "username">用户名</label> 
			   <div class="col-sm-10"> 
			    <input type = "text" class = "form-control" name = "username"
			     placeholder = "请输入用户名"></input> 
			   </div> 
			  </div>
			  <div class = "form-group"> 
			   <label class="col-sm-2 control-label" for = "password">密码</label> 
			   <div class="col-sm-10"> 
			    <input type = "password" class = "form-control" name = "password"
			     placeholder = "请输入密码"></input> 
			   </div> 
			  </div>
			   <div class = "form-group"> 
			   <label class="col-sm-2 control-label" for = "truename">真实姓名</label> 
			   <div class="col-sm-10"> 
			    <input type = "text" class = "form-control" name = "truename"
			     placeholder = "请输入用户名"></input> 
			   </div> 
			  </div>
			  <div class = "form-group"> 
			   <label class="col-sm-2 control-label" for = "phone">电话号码</label> 
			   <div class="col-sm-10"> 
			    <input type="text" class="form-control" name = "phone"
			      placeholder = "请输入电话号码"></input> 
			   </div> 
			  </div> 
			  <div class = "form-group"> 
			   <label class="col-sm-2 control-label" for = "mobile">手机</label> 
			   <div class="col-sm-10"> 
			    <input type="text" class="form-control" name = "mobile"
			      placeholder = "请输入电话号码"></input> 
			   </div> 
			  </div> 
			 
			<!--   <div class = "form-group"> 
			   <label class="col-sm-2 control-label" for = "idCard">请上传身份证</label> 
			   <div class="col-sm-10"> 
			    <input type = "file" id = "idCard" style="display:inline-block;"></input> 
			   </div> 
			  </div> 
			  <div class = "form-group"> 
			   <label class="col-sm-2 control-label" for = "profession">选择职业</label> 
			   <div class="col-sm-10"> 
			    <select id = "profession" class = "form-control"> 
			     <option>软件工程师</option> 
			     <option>测试工程师</option> 
			     <option>硬件工程师</option> 
			     <option>质量分析师</option> 
			    </select> 
			   </div> 
			  </div>  -->
			 </form> 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="submit" class="btn btn-primary" onclick="user_edit_submit()">保存</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="user_confirm_modal" tabindex="-1" role="dialog" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 id="user_edit_title" class="modal-title"></h4>
      </div>
      <div class="modal-body">
        您确定要删除该用户么？
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-primary" onclick="user_remove_submit()">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>