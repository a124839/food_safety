<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>查看设备信息</title>
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
      查看设备信息
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">查看设备信息</a></li>
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
            <form class="form-horizontal" id="instrumentForm">
              <input type="hidden" name="id" value="${instrument.id}">
              <div class="box-body">
                <!-- 内容 -->
                <table class="table table-bordered  text-center">
                  <tr><td><label>资产编号</label></td><td>${instrument.code} </td><td><label>序列号</label></td><td>${instrument.sn}</td></tr>
                  <tr><td><label>设备名称</label></td><td>${instrument.name} </td><td><label>设备型号</label></td><td>${instrument.model}</td></tr>
                  <tr><td><label>设备类型</label></td><td>${instrument.type} </td><td><label>设备分类</label></td><td>
                    <c:choose>
                        <c:when test="${instrument.category == 1}">
                          平台设备
                        </c:when>
                        <c:when test="${instrument.category == 2}">
                            辅助设备
                        </c:when>
                    </c:choose>

                  </td></tr>
                  <tr><td><label>购买日期</label></td><td> <fmt:formatDate value="${instrument.purchaseDate}" type="date" pattern="yyyy-MM-dd"/></td><td><label>价格</label></td><td>${instrument.price}</td></tr>
                  <tr><td><label>安装开始日期</label></td><td> <fmt:formatDate value="${instrument.installStartDate}" type="date" pattern="yyyy-MM-dd"/></td><td><label>安装结束日期</label></td><td><fmt:formatDate value="${instrument.installEndDate}" type="date" pattern="yyyy-MM-dd"/></td></tr>

                  <tr><td><label>生产商</label></td><td>${instrument.manufactor} </td><td><label>生产日期</label></td><td><fmt:formatDate value="${instrument.productionDate}" type="date" pattern="yyyy-MM-dd"/></td></tr>
                  <tr><td><label>谱图坐标(x)</label></td><td>${instrument.x} </td><td><label>谱图坐标(y)</label></td><td>${instrument.y}</td></tr>
                  <tr><td><label>性能指标</label></td><td colspan="3">${instrument.performances}</td></tr>
                  <tr><td><label>备注</label></td><td colspan="3">${instrument.memo} </td></tr>
                  </table>
                <div class="form-group">

                </div>
                <div class="form-group">
                  <div class="col-sm-6 pull-right">
                    <button type="button" class="btn btn-info" onclick="toListPage();">返回</button>
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
    <script src="${pageContext.request.contextPath }/js/biz/instrument/instrument_add.js?ver=${ver}" type="text/javascript"></script>
</body>
</html>