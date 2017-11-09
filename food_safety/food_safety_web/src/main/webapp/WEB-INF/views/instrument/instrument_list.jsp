
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>设备管理</title>
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
        设备管理
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">设备管理</a></li>
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
                  <label for="instrumentName" class="col-sm-1 control-label">设备名称</label>

                  <div class="col-sm-1">
                    <input type="text" class="form-control" id="instrumentName" placeholder="设备名称">
                  </div>
                  <label for="model" class="col-sm-1 control-label">设备型号</label>

                  <div class="col-sm-1">
                    <input type="text" class="form-control" id="model" placeholder="型号">
                  </div>
                    <label for="manufactor" class="col-sm-1 control-label">生产商 </label>

                  <div class="col-sm-1">
                    <input type="text" class="form-control" id="manufactor" placeholder="生产商 ">
                  </div>
                    <label for="manufactor" class="col-sm-1 control-label">设备分类 </label>
                  <div class="col-sm-1"  >
                      <select class="form-control" id="category">
                          <option value="">全部</option>
                          <option value="1">平台设备</option>
                          <option value="2">辅助设备</option>
                      </select>
                  </div>
                   <div class="col-sm-1">
                   	<button type="button" class="btn btn-primary pull-right" onclick="search();">搜索</button>
                   </div>
                </div>
            <div class="form-group">
                <shiro:hasPermission name="instrumentManageAdd">
                    <div class="col-sm-2"><a href="javascript:toAddPage();">添加</a></div>
                </shiro:hasPermission>

              </div>
            
              <table id="example2" class="table table-bordered table-hover text-center ">
                <thead>
                <tr>
                  <th>序号</th>
                    <th>名称</th>
                    <th>资产编号</th>
                  <th>序列号/SN</th>
                  <th>类型</th>
                    <th>分类</th>
                  <th>型号</th>
                 <%-- <th>购买日期</th>
                  <th>生产日期</th>--%>
                  <th>生产商</th>
                    <th>操作人</th>
                  <th>状态</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody id="instrument_list">
                
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
	<script	src="${pageContext.request.contextPath }/js/biz/instrument/instrument_biz.js?ver=${ver}" type="text/javascript"></script>

</body>
</html>