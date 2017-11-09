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
<title>修改设备信息</title>
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
      修改设备信息
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">修改设备信息</a></li>
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
            <form class="form-horizontal" id="instrumentForm" enctype="multipart/form-data" method="post">
              <input type="hidden" name="id" value="${instrument.id}">
              <div class="box-body">
                <!-- 内容 -->
                <div class="form-group">
                  <label class="col-sm-1 control-label">资产编号</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="code" value="${instrument.code}"/>
                  </div>
                  <label class="col-sm-1 control-label">序列号/SN</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="sn" value="${instrument.sn}"/>
                  </div>

                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label">设备名称</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="name" value="${instrument.name}"/>
                  </div>

                  <label class="col-sm-1 control-label">设备型号</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="model" value="${instrument.model}"/>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label">设备类型</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="type" value="${instrument.type}"/>
                  </div>
                  <label class="col-sm-1 control-label">设备分类</label>
                  <div class="col-sm-5">
                    <select class="form-control" name="category">
                      <option value="1" <c:if test="${instrument.category == 1}">selected="selected" </c:if> >平台设备</option>
                      <option value="2" <c:if test="${instrument.category == 2}">selected="selected" </c:if> >辅助设备</option>
                    </select>
                  </div>

                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label">购买日期</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="purchaseDate" readonly="readonly" value="<fmt:formatDate value="${instrument.purchaseDate}" type="date" pattern="yyyy-MM-dd"/>"/>
                  </div>
                  <label class="col-sm-1 control-label">价格</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="price" value="${instrument.price}"/>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label">安装开始日期</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="installStartDate" readonly value="<fmt:formatDate value="${instrument.installStartDate}" type="date" pattern="yyyy-MM-dd"/>"/>
                  </div>
                  <label class="col-sm-1 control-label">安装结束日期</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="installEndDate" readonly value="<fmt:formatDate value="${instrument.installEndDate}" type="date" pattern="yyyy-MM-dd"/>"/>
                  </div>

                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label">生产商</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="manufactor" value="${instrument.manufactor}"/>
                  </div>
                  <label class="col-sm-1 control-label">生产日期</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="productionDate" readonly value="<fmt:formatDate value="${instrument.productionDate}" type="date" pattern="yyyy-MM-dd"/>"/>
                  </div>
                </div>
                <div class="form-group">

                  <label class="col-sm-1 control-label">图谱坐标(X)</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="x" value="${instrument.x}"/>
                  </div>
                  <label class="col-sm-1 control-label">图谱坐标(Y)</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="y" value="${instrument.y}"/>
                  </div>

                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label">性能指标</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="performances" value="${instrument.performances}"/>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label">备注</label>
                  <div class="col-sm-11">
                    <textarea class="form-control" name="memo" style="height:100px;">${instrument.memo}</textarea>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label">附件</label>
                  <div class="col-sm-5">
                    <input type="file" name="attachment"/>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-10 pull-right">
                    <p id="errMsg" class="text-danger "></p>
                  </div>

                </div>
                <div class="form-group">
                  <div class="col-sm-6">
                    <button type="button" class="btn btn-primary pull-right" onclick="saveInstrument();">保存</button>
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
    <script src="${pageContext.request.contextPath }/js/biz/instrument/instrument_add.js?ver=${ver}" type="text/javascript"></script>
</body>
</html>