<%@ page contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="<%=basePath%>js/gerny/role/role.js"></script>
<script src="<%=basePath%>js/jQuery/ztree/jquery.ztree.all.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/jQuery/ztree/metroStyle.css">
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h4 class="box-title">角色管理</h4>
				</div>
				<div id="role_tips" style="display:none" class="alert alert-warning alert-dismissible" role="alert">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <strong>提示!</strong>请选择一列进行编辑！
				</div>
              <div class="margin">
              	  <div class="btn-group">
                  <button type="button" class="btn btn-success" data-toggle="modal" onclick="role_edit_modal('add')">新增</button>
                </div>
                <div class="btn-group">
                  <button type="button" class="btn btn-success"  onclick="role_edit_modal('edit')">修改</button>
                </div>
                <div class="btn-group">
                  <button type="button" class="btn btn-default" onclick="role_remove_modal()">删除</button>
                </div>
              
              </div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="role_list" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th><input id="role_check_all" type="checkbox"/></th>
								<th>角色名</th>
								<th>操作</th>
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
<div class="modal fade" id="role_edit_modal" tabindex="-1" role="dialog" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 id="role_edit_title" class="modal-title"></h4>
      </div>
      <div class="modal-body">
         <form id="role_edit_form" role = "form" class="form-horizontal"> 
         		
         	  <input type = "hidden"  name = "roleid"></input> 	
			  <div class = "form-group"> 
			   <label class="col-sm-2 control-label" for = "rolename">角色名</label> 
			   <div class="col-sm-10"> 
			    <input type = "text" class = "form-control" name = "rolename"
			     placeholder = "输入角色名"></input> 
			   </div> 
			  </div>
			 </form> 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="submit" class="btn btn-primary" onclick="role_edit_submit()">保存</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="role_confirm_modal" tabindex="-1" role="dialog" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 id="role_edit_title" class="modal-title"></h4>
      </div>
      <div class="modal-body">
        您确定要删除该用户么？
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-primary" onclick="role_remove_submit()">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="role_permission_modal" tabindex="-1" role="dialog" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 id="role_edit_title" class="modal-title"></h4>
      </div>
      <div class="modal-body">
			<div class="zTreeDemoBackground left">
		        <ul id="role_module_tree" class="ztree"></ul>
		    </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="submit" class="btn btn-primary" onclick="role_permission_submit()">保存</button>
      </div>
    </div>
  </div>
</div>
