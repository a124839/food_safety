<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>修改服务记录</title>
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
      修改服务记录
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">修改服务记录</a></li>
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
            <form class="form-horizontal" id="srForm" method="post" >

                <input type="hidden" value="${record.instrumentId}" name="instrumentId">
                <input type="hidden" value="${record.id}" name="id">
              <div class="box-body">
                <!-- 内容 -->
                

                
                <div class="form-group">
                  <label class="col-sm-2 control-label">服务类型</label>
                  <div class="col-sm-9">
                   <select class="form-control" name="type">
                   	<c:forEach items="${types}" var="o">
                   		<option value = "${o.id }" <c:if test="${record.type == o.id}">selected</c:if> >${o.name }</option>
                   	</c:forEach>
                   </select>
                  </div>
                </div>

                
                <div class="form-group">
                  <label class="col-sm-2 control-label">服务时间</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="serviceDate" readonly value="<fmt:formatDate value="${record.serviceDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
                  </div>
                </div>
                <div class="form-group">
                <label for="memo" class="col-sm-2 control-label">备注</label>
                <div class="col-sm-9">
                  <textarea class="form-control" name="memo" style="height:100px;">${record.memo}</textarea>

                </div>
              </div>
                <div class="form-group">
                  <div class="col-sm-11 pull-right">
                    <p id="errMsg" class="text-danger "></p>
                  </div>

                </div>
                <div class="form-group">
                  <div class="col-sm-6">
                    <button type="button" class="btn btn-primary pull-right" onclick="saveRecord();" id="saveBtn">保存</button>
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
    <script src="${pageContext.request.contextPath }/js/biz/instrumentServiceRecord/service_record_add.js?ver=${ver}" type="text/javascript"></script>
</body>
</html>