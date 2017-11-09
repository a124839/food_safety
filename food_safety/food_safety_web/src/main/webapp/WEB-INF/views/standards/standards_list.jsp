<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>标准管理</title>
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
      标准管理
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">标准管理</a></li>
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
              <!-- 内容 -->
              <div class="form-group">
                <label for="standardName" class="col-sm-1 control-label">名称</label>

                <div class="col-sm-2">
                  <input type="text" class="form-control" id="standardName" placeholder="名称">
                </div>
                <label for="target" class="col-sm-1 control-label">针对对象</label>

                <div class="col-sm-2">
                  <input type="text" class="form-control" id="target" placeholder="针对对象">
                </div>
                <label for="type" class="col-sm-1 control-label">类型 </label>

                <div class="col-sm-1">
                  <select id="type" class="form-control">

                    <option value="0">全部</option>
                    <option value="1">国家标准</option>
                    <option value="2">行业标准</option>
                    <option value="3">企业标准</option>
                    <option value="4">自定标准</option>
                  </select>
                </div>
                <label for="status" class="col-sm-1 control-label">状态 </label>
                <div class="col-sm-1">
                  <select id="status" class="form-control">
                    <option value="0">全部</option>
                    <option value="1">正常</option>
                    <option value="2">过期</option>
                  </select>
                </div>
                <div class="col-sm-1">
                  <button type="button" class="btn btn-primary pull-right" onclick="search();">搜索</button>
                </div>
              </div>
              <div class="form-group">
                <shiro:hasPermission name="standardsManageAdd">
                  <div class="col-sm-2"><a href="javascript:toAddPage();">添加</a></div>
                </shiro:hasPermission>

              </div>

              <table id="example2" class="table table-bordered table-hover text-center ">
                <thead>
                <tr>
                  <th>序号</th>
                  <th>编号</th>
                  <th>名称</th>
                  <th>类型</th>
                  <th>针对对象</th>
                    <th>发布日期</th>
                  <th>发布部门</th>
                    <th>年限</th>
                  <th>状态</th>
                  <th>操作人</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody id="standards_list">

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
	<!-- js时间库 -->
	<script src="${pageContext.request.contextPath }/js/moment/moment-with-locales.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/JQuery/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/biz/standards/standards_list.js?ver=${ver}"></script>

</body>
</html>