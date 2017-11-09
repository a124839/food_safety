<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>添加标准</title>
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
      添加标准
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">添加标准</a></li>
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
            <form class="form-horizontal" id="standardForm" enctype="multipart/form-data" method="post">
              <input type="hidden" name="id" value="${standards.id}">

              <div class="box-body">
                <!-- 内容 -->
                <div class="form-group">
                  <label class="col-sm-1 control-label">编号</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="code" value="${standards.code}" maxlength="254"/>
                  </div>
                  <label class="col-sm-1 control-label">名称</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="name" value="${standards.name}" maxlength="254"/>
                  </div>

                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label">针对对象</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="target" value="${standards.name}" maxlength="254"/>
                  </div>

                  <label class="col-sm-1 control-label">类型</label>
                  <div class="col-sm-5">
                    <select name="type" class="form-control">
                      <option value="1" <c:if test="${standards.type == 1}">selected="selected"</c:if>>国家标准</option>
                      <option value="2" <c:if test="${standards.type == 2}">selected="selected"</c:if>>行业标准</option>
                      <option value="3" <c:if test="${standards.type == 3}">selected="selected"</c:if>>企业标准</option>
                      <option value="4" <c:if test="${standards.type == 4}">selected="selected"</c:if>>自定标准</option>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label">发布时间</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="releaseTime" readonly value="<fmt:formatDate value="${standards.releaseTime}" type="date" pattern="yyyy-MM-dd"/>"/>
                  </div>
                  <label class="col-sm-1 control-label">年限</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="period" value="${standards.period}" maxlength="254"/>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label">发布部门</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="issuanceDepartment" value="${standards.issuanceDepartment}"/>
                  </div>
                  <label class="col-sm-1 control-label">简单说明</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="summary" value="${standards.summary}" maxlength="254"/>
                  </div>
                </div>

                </div>
                <div class="form-group">
                  <label for="memo" class="col-sm-1 control-label">备注</label>
                  <div class="col-sm-10">
                    <textarea class="form-control" name="memo" style="height:100px;">${standards.memo}</textarea>
                  </div>

                </div>
              <div>
                <label class="col-sm-1 control-label">附件</label>
                <div class="col-sm-10">
                  <input type="file" name="attachment"/>
                </div>
              </div>
              <div class="form-group">

              </div>
                <div class="form-group">
                  <div class="col-sm-6">
                    <button type="button" class="btn btn-primary pull-right" onclick="saveStandard();">保存</button>
                  </div>
                  <div class="col-sm-6">
                    <button type="button" class="btn btn-info" onclick="toListPage();">取消</button>
                  </div>
                </div>
              <div class="form-group">

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
<script	src="${pageContext.request.contextPath }/js/JQuery/jquery.form.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath }/js/biz/standards/standard_add.js?ver=${ver}" type="text/javascript"></script>
</body>
</html>