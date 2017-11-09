<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>导入数据</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<%@include file="/common/common.jsp"%>
<!-- DATA TABLES -->
<link
	href="${pageContext.request.contextPath }/AdminLTE/plugins/datatables/dataTables.bootstrap.css"
	rel="stylesheet" type="text/css" />
  <script src="${pageContext.request.contextPath }/js/echarts/echarts.min.js"></script>

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
      导入数据
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">导入数据</a></li>
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
            <form class="form-horizontal"  id="dataForm" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath }/analysis/importData">
              <input type="hidden" id="algorithmInfo" name="algorithmInfo">
                <div class="box-body">
                  <!-- 内容 -->
                  <div class="form-group">
                    <div class="col-sm-12"><label>算法信息</label></div>
                  </div>
                  <div class="form-group">
                    <label for="algorithm" class="control-label col-sm-2" >算法:</label>
                    <div class="col-sm-9">
                      <select class="form-control"  name="algorithm" id="algorithm"></select>
                    </div>
                  </div>
                  <div id="params">

                  </div>
                  <div class="form-group">
                    <div class="col-sm-12"><label>数据列表</label></div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-12">
                      <table class="table table-bordered table-hover text-center">
                        <thead>
                        <tr>
                          <th>实验名称</th>
                          <th>仪器</th>
                          <th>样品</th>
                          <th>数据</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${datas}" var="o">
                          <tr>
                            <td>${o.projectName}</td>
                            <td>${o.intrumentName}</td>
                            <td>${o.sampleName}</td>
                            <td><input type="file" name="${o.id}"></td>
                          </tr>
                        </c:forEach>
                        </tbody>
                      </table>

                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-11 pull-right">
                      <p id="errMsg" class="text-danger "></p>
                    </div>

                  </div>
                  <div class="form-group">
                    <div class="col-sm-6">
                      <button type="button" class="btn btn-primary pull-right" onclick="importData();" id="saveBtn">导入</button>
                    </div>
                    <div class="col-sm-6">
                      <button type="button" class="btn btn-primary" onclick="toDataListPage();">返回</button>
                    </div>
                  </div>
                </div>
            </form>

            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
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
    <script type="text/javascript">
      var analysisDatas = '${analysisDatas}';
    </script>
	<script src="${pageContext.request.contextPath }/js/biz/analysis/import_data.js?ver=${ver}" type="text/javascript"></script>

</body>
</html>