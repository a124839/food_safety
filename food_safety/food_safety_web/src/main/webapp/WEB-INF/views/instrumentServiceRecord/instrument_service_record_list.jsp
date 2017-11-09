<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>设备服务记录</title>
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
        设备服务记录
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">设备服务记录</a></li>
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
                  <label  class="col-sm-2 control-label">服务类型</label>

                  <div class="col-sm-3">
                    <select id = "type" class="form-control">
                        <option value="">全部</option>
                      <c:forEach items="${types}" var="o">
                            <option value="${o.id}">${o.name}</option>
                      </c:forEach>
                    </select>
                  </div>
                   <div class="col-sm-2">
                   	<button type="button" class="btn btn-primary pull-right" onclick="search();">搜索</button>
                   </div>
                </div>
            <div class="form-group">
              	<div class="col-sm-2"><a href="javascript:toAddPage();">添加</a></div>
              </div>
            
              <table id="example2" class="table table-bordered table-hover text-center ">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>设备编号</th>
                  <th>设备名称</th>
                  <th>服务类别</th>
                  <th>服务时间</th>
                    <th>备注</th>
                    <th>操作人</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody id="serviceRecord_list">
                
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
    <script type="text/javascript">
        var instrumentId = '${param.id}'
    </script>
	<script	src="${pageContext.request.contextPath }/js/biz/instrumentServiceRecord/instrument_service_record_list.js?ver=${ver}" type="text/javascript"></script>

</body>
</html>