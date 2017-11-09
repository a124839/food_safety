<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>样品管理</title>
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
        样品管理
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">样品管理</a></li>
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
                  <label for="sampleName" class="col-sm-1 control-label">样品名称</label>

                  <div class="col-sm-2">
                    <input type="text" class="form-control" id="sampleName" placeholder="样品名称">
                  </div>
                  <label for="producingArea" class="col-sm-1 control-label">样品产地</label>

                  <div class="col-sm-2">
                    <input type="text" class="form-control" id="producingArea" placeholder="样品产地">
                  </div>
                    <label for="manufactor" class="col-sm-1 control-label">生产商 </label>

                  <div class="col-sm-2">
                    <input type="text" class="form-control" id="manufactor" placeholder="生产商 ">
                  </div>
                   <div class="col-sm-1">
                   	<button type="button" class="btn btn-primary pull-right" onclick="search();">搜索</button>
                   </div>
                </div>
            <div class="form-group">
                <shiro:hasPermission name="sampleManageAdd">
                  <div class="col-sm-2"><a href="javascript:toAddPage();">添加</a></div>
                </shiro:hasPermission>

              </div>
            
              <table id="example2" class="table table-bordered table-hover text-center ">
                <thead>
                <tr>
                  <th>序号</th>
                  <th>编号</th>
                  <th>名称</th>
                  <th>批次</th>
                  <th>生产商</th>
                  <th>产地</th>
                  <th>生产日期</th>
                  <th>状态</th>
                  <th>操作人</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody id="sample_list">
                
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
	<script	src="${pageContext.request.contextPath }/js/moment/moment-with-locales.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath }/js/biz/sample/sample_biz.js?ver=${ver}" type="text/javascript"></script>

</body>
</html>