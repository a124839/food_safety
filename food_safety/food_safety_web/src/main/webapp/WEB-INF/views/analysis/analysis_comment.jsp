<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>分析详情</title>
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
      分析详情
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">分析详情</a></li>
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
                    <div class="col-sm-12"><label>算法信息</label></div>
                  </div>
                 <div class="form-group">
                   <table class="table table-bordered table-hover text-center">
                     <tr>
                       <td><b>算法名称</b></td><td colspan="3">${algorithmInfo.algorithmName}</td>
                     </tr>
                     <c:forEach items="${algorithmInfo.algorithmParams}" var="o">
                       <tr><td><b>参数名称</b></td><td>${o.name}</td><td><b>默认值</b></td><td>${o.value}</td></tr>
                     </c:forEach>
                   </table>

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
                    <%--      <th>文件名</th>
                          <th>上传人 </th>
                          <th>上传时间</th>--%>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${datas}" var="o">
                          <tr>
                            <td>${o.projectName}</td>
                            <td>${o.intrumentName}</td>
                            <td>${o.sampleName}</td>
                           <%-- <td>${o.fileName}</td>
                            <td>${o.uploader}</td>
                            <td><fmt:formatDate value="${o.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/> </td>--%>
                          </tr>
                        </c:forEach>
                        </tbody>
                      </table>

                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-12">
                      <div id="main" style="width:100%;height: 800px;">


                      </div>
                    </div>

                  </div>
                  <div class="form-group">
                    <div class="col-sm-6">
                      <button type="button" class="btn btn-primary pull-right" onclick="toAnalysisListPage();">返回</button>
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
      var analysisDatas = '${values}';
      var x = '${x}';
      var y = '${y}';
    </script>
	<script src="${pageContext.request.contextPath }/js/biz/analysis/analysis_info.js?ver=${ver}" type="text/javascript"></script>

</body>
</html>