<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>添加算法</title>
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
      添加算法
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">添加算法</a></li>
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
            <form class="form-horizontal" method="post" enctype="multipart/form-data" id="algorithmForm">
              <input name="data" type="hidden" id="datas">
            <div class="box-body">
              <!-- 内容 -->
              <div class="form-group">
                <label for="instrumentName" class="col-sm-2 control-label">算法名称</label>
                <div class="col-sm-4">
                  <input type="text" class="form-control" id="algorithmName" placeholder="算法名称">
                </div>
                <label for="instrumentName" class="col-sm-1 control-label">算法类型</label>
                <div class="col-sm-4">
                  <select class="form-control" id="algorithmType">
                    <option value="1">预处理</option>
                    <option value="2">评价</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="sampleCode" class="col-sm-2 control-label">示例代码</label>
                <div class="col-sm-9">
                  <textarea class="form-control" name="sampleCode" style="height:200px;"></textarea>

                </div>
                </div>
                <div class="form-group">
                  <label for="example" class="col-sm-2 control-label" >示例</label>
                  <div class="col-sm-9">
                    <textarea class="form-control" name="example" style="height:200px;"></textarea>

                  </div>
              </div>
              <div id="params">


              </div>

              <div class="form-group">
                <label for="memo" class="col-sm-2 control-label">备注</label>
                <div class="col-sm-9">
                  <textarea class="form-control" name="memo" style="height:100px;"></textarea>

                </div>
              </div>
                <div class="form-group">
                    <label for="memo" class="col-sm-2 control-label">附件</label>
                    <div class="col-sm-9">
                        <input type="file" name="attachment">

                    </div>
                </div>
              <div class="form-group">
                <div class="col-sm-10"></div>
                <div class="col-sm-2">
                  <div class="col-sm-5">
                    <a href="javascript:delParams()" class="pull-right">删除参数</a>
                  </div>
                  <div class="col-sm-5">
                    <a href="javascript:addParams()" class="pull-right">添加参数</a>
                  </div>
                </div>

              </div>
              <div class="form-group">
                <div class="col-sm-10 pull-right">
                  <p id="errMsg" class="text-danger "></p>
                </div>

              </div>
            <div class="form-group">
              <div class="col-sm-6">
                <button type="button" class="btn btn-primary pull-right" onclick="save();" id="saveBtn">保存</button>
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
	<script src="${pageContext.request.contextPath }/js/biz/algorithm/algorithm_add.js?ver=${ver}" type="text/javascript"></script>

</body>
</html>