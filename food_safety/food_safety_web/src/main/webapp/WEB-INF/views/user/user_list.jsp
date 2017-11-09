<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>用户管理</title>
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
        用户管理
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">用户管理</a></li>
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
                  <label for="userName" class="col-sm-1 control-label">姓名</label>

                  <div class="col-sm-2">
                    <input type="text" class="form-control" id="userName" placeholder="姓名">
                  </div>
                   <label for="roleName" class="col-sm-1 control-label">角色</label>

                  <div class="col-sm-2">
	                  <select class="form-control" id="roleName">
	                    <option value="0">全部</option>
	                    <c:forEach items="${roles}" var="o">
                          <option value="${o.id}">${o.roleName}</option>
                        </c:forEach>
	                  </select>
                  </div>
                  <label for="userName" class="col-sm-1 control-label">状态</label>
                  <div class="col-sm-1 col-lg-2">
                    <select class="form-control" name="status">
                      <option value="0">全部</option>
                      <option value="2">待审核</option>
                    </select>
                  </div>
                   <div class="col-sm-2">
                   	<button type="button" class="btn btn-primary pull-right" onclick="search();">搜索</button>
                   </div>
                </div>
            <div class="form-group">
               <%-- <shiro:hasPermission name="userManageAdd">
                  <div class="col-sm-2"><a href="javascript:showAddModal();">添加</a></div>
                </shiro:hasPermission>--%>

              </div>
            
              <table id="example2" class="table table-bordered table-hover text-center ">
                <thead>
                <tr>
                  <th>序号</th>
                  <th>账号</th>
                  <th>姓名</th>
                  <th>出生日期</th>
                  <th>联系方式</th>
                  <th>角色</th>
                  <th>学号</th>
                  <th>毕业院校</th>
                  <th>研究方向</th>
                  <th>导师</th>
                  <th>状态</th>
                  <th>创建时间</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody id="user_list">
                
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
	

<div class="modal fade" id="userEditModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">编辑用户信息</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="editUserName" class="control-label">姓名:</label>
            <input type="text" class="form-control" id="editUserName">
          </div>
           <div class="form-group">
            <label for="editPassword" class="control-label">密码:</label>
            <input type="password" class="form-control" id="editPassword">
          </div>
          <div class="form-group">
            <label for="editPassword" class="control-label">联系方式:</label>
            <input type="text" class="form-control" id="editContact">
          </div>

          <div class="form-group">
            <label for="editRoleName" class="control-label">角色:</label>
            <select class="form-control" id="editRoleName">
	                   <c:forEach items="${roles }" var="o">
	                   	 <option value="${o.id }">${o.roleName }</option>
	                   </c:forEach>
	                  </select>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="updateUser();" data-dismiss="modal">保存</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="userAddModal" tabindex="-1" role="dialog" aria-labelledby="AddUserModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="AddUserModalLabel">添加用户</h4>
      </div>
      <div class="modal-body">
        <form role="form">
        <div class="form-group" id="loginAccountFormGroup">
            <label for="addLoginAccount" class="control-label" >账号:</label>
            <input type="text" class="form-control" id="addLoginAccount"  placeholder="可以由数字、字母、下划线组成，不能超过32个字符" maxlength="32">
          </div>
          <div class="form-group"  id="passwordFormGroup">
            <label for="addPassword" class="control-label">密码:</label>
            <input type="password" class="form-control" id="addPassword" placeholder="可以由数字、字母、下划线组成，不能超过16个字符" maxlength="16">
          </div>
          <div class="form-group" id="userNameFormGroup">
            <label for="addUserName" class="control-label">姓名:</label>
            <input type="text" class="form-control" id="addUserName" placeholder="中文名或者英文名，不能超过16个字符" maxlength="16">
          </div>
          <div class="form-group">
            <label for="addRoleNameS" class="control-label">角色:</label>
            <select class="form-control"  id="addRoleNameS">
	                     <c:forEach items="${roles }" var="o">
	                   	 <option value="${o.id }">${o.roleName }</option>
	                   </c:forEach>
	                  </select>
          </div>
        </form>
      </div>
      <div class="modal-footer">
      	<p id="errMsg" class="text-danger pull-left"></p>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="addUser();">保存</button>
      </div>
    </div>
  </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade bs-example-modal-sm" id="verify_result" tabindex="-1" role="dialog" aria-labelledby="verify_resultModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"  aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="verify_resultModalLabel"></h4>
      </div>
      <div class="modal-body" id="verify_resultModalBody"></div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" id="verify_resultModalConfirm" data-dismiss="modal">确认</button>
      </div>
    </div>
  </div>
</div>
	<!-- js时间库 -->
	<script	src="${pageContext.request.contextPath }/js/moment/moment-with-locales.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath }/js/biz/user/user_biz.js?ver=${ver}" type="text/javascript"></script>

</body>
</html>