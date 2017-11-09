<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>算法信息</title>
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
      算法信息
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">算法信息</a></li>
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
                  <table class="table table-bordered text-center">
                    <tr>
                      <td><b>算法名称</b></td><td>${algorithm.name}</td> <td><b>算法类型</b></td><td>
                      <c:choose>
                        <c:when test="${algorithm.type == 1}">预处理</c:when>
                        <c:when test="${algorithm.type == 2}">评价</c:when>

                      </c:choose>
                    </td>
                    </tr>
                    <c:forEach items="${params}" var="o">
                      <tr><td><b>参数名称</b></td><td>${o.name}</td><td><b>默认值</b></td><td>${o.value}</td></tr>
                    </c:forEach>
                  </table>
                  <div class="form-group">

                  </div>
                  <div class="form-group">
                    <label for="sampleCode" class="col-sm-2 control-label">示例代码</label>
                    <div class="col-sm-10">
                       <textarea class="form-control" style="height: 200px;">${algorithm.sampleCode}</textarea>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="example" class="col-sm-2 control-label">算法示例</label>
                    <div class="col-sm-10" >
                       <textarea class="form-control" style="height: 200px;">${algorithm.example}  </textarea>
                    </div>

                  </div>
                  <div class="form-group">
                    <label for="example" class="col-sm-2 control-label">备注信息</label>
                    <div class="col-sm-10">
                       <textarea class="form-control" style="height: 100px;">${algorithm.memo}</textarea>
                    </div>
                  </div>
                  <div class="form-group">
                  </div>
                  <div class="form-group">
                    <div class="col-sm-6">
                      <button type="button" class="btn btn-primary pull-right" onclick="toListPage();">返回</button>
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
	<script src="${pageContext.request.contextPath }/js/biz/algorithm/algorithm_add.js?ver=${ver}" type="text/javascript"></script>

</body>
</html>