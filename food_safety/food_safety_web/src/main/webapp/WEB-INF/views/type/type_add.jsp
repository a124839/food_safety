<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>添加样品种类</title>
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
        添加样品种类
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">添加样品种类</a></li>
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
            <form class="form-horizontal" id="typeForm" enctype="multipart/form-data" method="post" >
              <div class="box-body">
                <!-- 内容 -->
                
          <%--        <div class="form-group">
                  <label class="col-sm-4 control-label">类别</label>
                  <div class="col-sm-2">
                    <input type="radio" name="category" checked="checked" value="1"/>
                  </div>

                  <label class="col-sm-4 control-label">种类</label>
                  <div class="col-sm-2">
                    <input type="radio"  name="category" value="2"/>
                  </div>
                </div>--%>
                
                <%--<div id="cateloryLv1" <c:if test="${type.parentId == '0'}">style="display:none"</c:if> >--%>
                <div class="form-group">
                  <label for="parentId" class="col-sm-2 control-label">类别</label>
                  <div class="col-sm-9">
                   <select class="form-control" name="parentId" id="parentId">
                   	<c:forEach items="${types}" var="o">
                   		<option value = "${o.id }" <c:if test="${type.parentId == o.id}"> selected</c:if> >${o.name }</option>
                   	</c:forEach>
                   </select>
                  </div>
                </div>

                  <div class="form-group">
                      <label class="col-sm-2 control-label">编号</label>
                      <div class="col-sm-9">
                          <input type="text" class="form-control" name="code"/>
                      </div>
                  </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">名称</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="name"/>
                  </div>
                </div>
                <div class="form-group">
                <label for="memo" class="col-sm-2 control-label">备注</label>
                <div class="col-sm-9">
                  <textarea class="form-control" name="memo" style="height:100px;"></textarea>

                </div>
              </div>
                <div class="form-group">
                  <div class="col-sm-11 pull-right">
                    <p id="errMsg" class="text-danger "></p>
                  </div>

                </div>
                <div class="form-group">
                  <div class="col-sm-6">
                    <button type="button" class="btn btn-primary pull-right" onclick="saveType();" id="saveBtn">保存</button>
                  </div>
                  <div class="col-sm-6">
                    <button type="button" class="btn btn-info" onclick="toListPage();">取消</button>
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
    <script	src="${pageContext.request.contextPath }/js/JQuery/jquery.form.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath }/js/biz/type/type_add.js?ver=${ver}" type="text/javascript"></script>
</body>
</html>