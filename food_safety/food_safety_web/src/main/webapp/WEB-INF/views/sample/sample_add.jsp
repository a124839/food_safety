<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>添加样品</title>
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
      添加样品
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">添加样品</a></li>
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
                <input type="hidden" name="indicators">
              <div class="box-body">
                <!-- 内容 -->
                <div class="form-group">
                  <label class="col-sm-1 control-label">编号</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="code"/>
                  </div>

                  <label class="col-sm-1 control-label">名称</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="name"/>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label">类别</label>
                  <div class="col-sm-5">
    	            <input type="hidden" name="categoryLv1Id"/>
					<input type="hidden" name="categoryLv2Id"/>
                    <select  class="form-control" name="categoryLv1">
                      <c:forEach items="${lv1}" var="o">
                          <option value="${o.id}">${o.name}</option>
                      </c:forEach>
                    </select>
                  </div>
                  <label class="col-sm-1 control-label">种类</label>
                  <div class="col-sm-5">
                    <select  class="form-control" name="categoryLv2">
                        <c:forEach items="${lv2}" var="o">
                            <option value="${o.id}">${o.name}</option>
                        </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label">批次</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="batches"/>
                  </div>

                  <label class="col-sm-1 control-label">产地</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="producingArea"/>
                  </div>
                </div>

                <div class="form-group">
                  <label class="col-sm-1 control-label">生产日期</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="productionDate" readonly/>
                  </div>
                  <label class="col-sm-1 control-label">生产商</label>
                  <div class="col-sm-5">
                    <input type="text" class="form-control" name="manufactor"/>
                  </div>
                </div>
                <div class="form-group">
                  <label for="memo" class="col-sm-1 control-label">备注</label>
                  <div class="col-sm-11">
                    <textarea class="form-control" name="memo" style="height:100px;"></textarea>

                  </div>
                </div>
                <div id="indicators">


                </div>
                  <div class="form-group">
                      <div class="col-sm-9"></div>
                      <div class="col-sm-3">
                          <div class="col-sm-5">
                              <a href="javascript:addParams()" class="pull-right">添加指标</a>
                          </div>
                          <div class="col-sm-5">
                              <a href="javascript:delParams()" class="pull-right">删除指标</a>
                          </div>

                      </div>

                  </div>
                <div class="form-group">
                  <div class="col-sm-11 pull-right">
                    <p id="errMsg" class="text-danger "></p>
                  </div>

                </div>
                <div class="form-group">
                  <div class="col-sm-6">
                    <button type="button" class="btn btn-primary pull-right" onclick="saveSample();">保存</button>
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
    <script src="${pageContext.request.contextPath }/js/biz/sample/sample_add.js?ver=${ver}" type="text/javascript"></script>
</body>
</html>