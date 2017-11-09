<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>样品信息</title>
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
      样品信息
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">样品信息</a></li>
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
            <form class="form-horizontal" id="sampleForm">
              <input type="hidden" value="${sample.id}" name="id">
                <input type="hidden" name="indicators">
              <div class="box-body">
                <!-- 内容 -->
                <table class="table table-bordered text-center">
                  <tr>
                    <td><label>编号</label></td>
                    <td>${sample.code}</td>
                    <td><label>名称</label></td>
                    <td>${sample.name}</td>
                  </tr>
                   <tr>
                    <td><label >类别</label></td>
                    <td>${sample.categoryLv1Name}</td>
                    <td><label>种类</label></td>
                    <td>${sample.categoryLv2Name}</td>
                  </tr>
                  <tr>
                    <td><label >批次</label></td>
                    <td>${sample.batches}</td>
                    <td><label>产地</label></td>
                    <td>${sample.producingArea}</td>
                  </tr>
                  <tr>
                    <td><label >生产日期</label></td>
                    <td><fmt:formatDate value="${sample.productionDate}" type="date" pattern="yyyy-MM-dd"/></td>
                    <td><label >生产商</label></td>
                    <td>${sample.manufactor}</td>
                  </tr>
                  <tr>
                    <td><label >备注</label></td>
                    <td colspan="3">${sample.memo}</td>
                  </tr>
                  <c:forEach items="${sample.sampleIndicators}" var="o">
                    <tr>
                      <td><label >指标名称</label></td>
                      <td>${o.indicatorName}</td>
                      <td><label >指标值</label></td>
                      <td>${o.indicatorValue}</td>
                    </tr>

                  </c:forEach>
                </table>
                <div class="form-group">


                </div>
                <div class="form-group">
                  <div class="col-sm-6">
                    <button type="button" class="btn btn-info  pull-right" onclick="toListPage();">返回</button>
                  </div>
                </div>

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
    <script src="${pageContext.request.contextPath }/js/biz/sample/sample_add.js?ver=${ver}" type="text/javascript"></script>
</body>
</html>