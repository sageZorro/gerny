<%@ page contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <link rel="stylesheet" href="<%=basePath%>css/jQuery/ztree/metroStyle.css">
<script src="<%=basePath%>js/jQuery/ztree/jquery.ztree.all.min.js"></script>
<script src="<%=basePath%>js/gerny/module/module.js"></script>
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h4 class="box-title">模块管理</h4>
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
					<div class="col-md-12">
						<div class="zTreeDemoBackground left">
					        <ul id="module_tree" class="ztree"></ul>
					    </div>
				    </div>
				   
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>
	
	
	
	<!-- /.row -->
</section>

