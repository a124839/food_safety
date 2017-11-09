<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>角色管理</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<%@include file="/common/common.jsp"%>
<!-- DATA TABLES -->
<link
	href="${pageContext.request.contextPath }/AdminLTE/plugins/datatables/dataTables.bootstrap.css"
	rel="stylesheet" type="text/css" />

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@include file="/common/top.jsp" %>
  <%@include file ="/common/left.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
    <!-- Content Header (Page header) -->
    <section class="content-header">
    <h1>
        角色管理
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">角色管理</a></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
            </div>
            <!-- /.box-header -->
            <form class="form-horizontal">
            <div class="box-body">
            	<div class="form-group">
                  <label for="userName" class="col-sm-1 control-label">名称</label>

                  <div class="col-sm-3">
                    <input type="text" class="form-control" id="roleName" placeholder="名称">
                  </div>
                   <div class="col-sm-2">
                   	<button type="button" class="btn btn-primary pull-right" onclick="search();">搜索</button>
                   </div>
                </div>
            <div class="form-group">
                <shiro:hasPermission name="roleManageAdd">
                  <div class="col-sm-2"><a href="javascript:showAddModal();">添加</a></div>
                </shiro:hasPermission>

              </div>

              <table id="example2" class="table table-bordered table-hover text-center ">
                <thead>
                <tr>
                  <th>角色名称</th>
                  <th>英文名称</th>
                  <th>创建时间</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody id="role_list">
                
                </tbody>
              </table>
                <div class="form-group">
              
              </div>
              <div class="pull-right"><%@include file ="/common/pager.jsp" %></div>
            </div>
            <!-- /.box-body -->
            </form>
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
<%@include file="/common/footer.jsp" %>
</div>
<!-- ./wrapper -->
	

<div class="modal fade" id="roleEditModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">编辑角色信息</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group" id="editRoleFormGroup">
            <input type="hidden"  id="oldRoleName">
            <label for="editRoleName" class="control-label">名称:</label>
            <input type="text" class="form-control" id="editRoleName">
            <label for="editShiroName" class="control-label">英文名称:</label>
            <input type="text" class="form-control" id="editShiroName"  placeholder="由字母组成">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <p id="editErrMsg" class="text-danger pull-left"></p>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="updateRole();">保存</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="roleAddModal" tabindex="-1" role="dialog" aria-labelledby="AddUserModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="AddUserModalLabel">添加角色</h4>
      </div>
      <div class="modal-body">
        <form role="form">
        <div class="form-group" id="roleFormGroup">
          <label for="addRoleName" class="control-label">名称:</label>
          <input type="text" class="form-control" id="addRoleName">
          <label for="addShiroName" class="control-label">英文名称:</label>
          <input type="text" class="form-control" id="addShiroName"  placeholder="由字母组成">
          </div>
        </form>
      </div>
      <div class="modal-footer">
      	<p id="errMsg" class="text-danger pull-left"></p>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="addRole();">保存</button>
      </div>
    </div>
  </div>
</div>
	<!-- js时间库 -->
	<script	src="${pageContext.request.contextPath }/js/moment/moment-with-locales.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath }/js/biz/role/role_biz.js?ver=${ver}" type="text/javascript"></script>

</body>
</html>