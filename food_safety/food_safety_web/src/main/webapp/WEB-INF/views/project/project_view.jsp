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
<title>实验信息</title>
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
      实验信息
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">实验信息</a></li>
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
            <form class="form-horizontal" id="projectForm">
              <input type="hidden" name="id" value="${project.id}">
              <div class="box-body">
                <!-- 内容 -->
                  <table class="table table-bordered text-center">
                      <tr><td><label>实验名称</label></td><td colspan="3">${project.name }</td></tr>
                      <tr><td><label>开始时间</label></td><td> <fmt:formatDate value="${project.startDate}" type="date" pattern="yyyy-MM-dd"/></td><td><label>结束时间</label></td><td><fmt:formatDate value="${project.endDate}" type="date" pattern="yyyy-MM-dd"/></td></tr>
                      <c:if test="${empty project.schemaAttachmentId and !empty project.standardAttachmentId}">
                        <tr><td colspan="4"><a href="${pageContext.request.contextPath}/attachment/download?attachmentId=${project.standardAttachmentId}&type=project">下载实验标准</a></td></tr>

                      </c:if>
                    <c:if test="${empty project.standardAttachmentId and !empty project.schemaAttachmentId}">
                      <tr><td colspan="4"><a href="${pageContext.request.contextPath}/attachment/download?attachmentId=${project.schemaAttachmentId}&type=project">下载实验方案</a></td></tr>

                    </c:if>
                    <c:if test="${!empty project.standardAttachmentId and !empty project.schemaAttachmentId}">
                      <tr><td colspan="2"><a href="${pageContext.request.contextPath}/attachment/download?attachmentId=${project.schemaAttachmentId}&type=project">下载实验方案</a></td><td colspan="2"><a href="${pageContext.request.contextPath}/attachment/download?attachmentId=${project.standardAttachmentId}&type=project">下载实验标准</a></td></tr>

                    </c:if>
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
    <script src="${pageContext.request.contextPath }/js/biz/project/project_add.js?ver=${ver}" type="text/javascript"></script>
</body>
</html>