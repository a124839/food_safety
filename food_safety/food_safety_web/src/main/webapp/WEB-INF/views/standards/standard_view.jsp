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
<title>查看标准信息</title>
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
      查看标准信息
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">查看标准信息</a></li>
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
              <div class="box-body">
                <!-- 内容 -->
                <table class="table table-bordered text-center">
                  <tr><td><label>编号</label></td><td>${standards.code} </td><td><label>设备名称</label></td><td>${standards.name} </td></tr>
                  <tr><td><label>类型</label></td><td>
                      <c:choose>
                          <c:when test="${standards.type == 1}">
                              国家标准
                          </c:when>
                          <c:when test="${standards.type == 2}">
                              行业标准
                          </c:when>
                          <c:when test="${standards.type == 3}">
                              企业标准
                          </c:when>
                          <c:when test="${standards.type == 4}">
                              自定标准
                          </c:when>
                      </c:choose>

                  </td> </td><td><label>针对对象</label></td><td>${standards.target}</td> </tr>
                  <tr><td><label>发布日期</label></td><td> <fmt:formatDate value="${standards.releaseTime}" type="date" pattern="yyyy-MM-dd"/></td><td><label>年限</label></td><td>${standards.period}</td></tr>
                  <tr><td><label>发布部门</label></td><td>${standards.issuanceDepartment}</td><td><label>简单说明</label></td><td>${standards.summary} </td></tr>
                    <tr><td><label>备注</label></td><td colspan="3"><textarea class="form-control" style="height:100px;">${standards.memo}</textarea></td></tr>
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
<script type="text/javascript">
  function toListPage(){
    window.location.href = ctx + '/standards/toQueryPage';
  }
</script>
</body>
</html>