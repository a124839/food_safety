<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="0">
<title>评价管理</title>
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
        评价管理
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">评价管理</a></li>
      </ol>
    </section>

    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">

            <div class="box-header">
            </div>
            <!-- /.box-header -->
            <form class="form-horizontal">
            <div class="box-body">
            	<div class="form-group">
                    <label for="cname" class="col-sm-2  col-lg-1 control-label">名称：</label>

                  <div class="col-sm-2">
                    <input type="text" class="form-control" id="cname" placeholder="名称">
                  </div>
                  <div class="col-sm-1">
                    <button type="button" class="btn btn-primary pull-right" onclick="search();">搜索</button>
                  </div>
                </div>

            <div class="form-group">
              	<div class="col-sm-2">
                        <a href="javascript:comment();">评价</a>
                </div>
              </div>

              <table id="example2" class="table table-bordered table-hover text-center ">
                <thead>
                <tr>
                  <th>序号</th>
                  <th>名称</th>
                  <th>建模样品数</th>
                  <th>模型主成分数</th>
                  <th>R²（决定系数）</th>
                  <th>RMSCV（交叉验证均方根误差）</th>
                  <th>备注</th>
                  <th>上传人 </th>
                  <th>上传时间</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody id="comment_list">

                </tbody>
              </table>
                <div class="form-group">
              
              </div>
              <div class="pull-right"><%@include file ="/common/pager.jsp" %></div>
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
<div class="modal fade" id="fileAddModal" tabindex="-1" role="dialog" aria-labelledby="AddUserModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="AddUserModalLabel">导入数据</h4>
      </div>
      <form role="form" id="dataForm" enctype="multipart/form-data" method="post" >
        <input type="hidden" name="analysisId" />
        <div class="modal-body">
          <div class="form-group">
            <label for="resolution" class="control-label">名称:</label>
            <input class="form-control" name="name" >
            <label for="resolution" class="control-label">建模样品数:</label>
            <input class="form-control" name="jmyps">
            <label for="resolution" class="control-label">模型主成分数:</label>
            <input class="form-control" name="zcfs">
            <label for="resolution" class="control-label">R²（决定系数）:</label>
            <input class="form-control" name="r">
            <label for="resolution" class="control-label">RMSCV（交叉验证均方根误差）:</label>
            <input class="form-control" name="rmscv">

          </div>
          <div class="form-group">
            <label for="memo" class="control-label">备注:</label>
            <textarea rows="5" name="memo" class="form-control"></textarea>
          </div>
          <div class="form-group">
            <%--@declare id="filename"--%><label for="fileName" class="control-label">图谱文件:</label>
            <input type="file" name="file1" >
          </div>
          <div class="form-group" >
            <%--@declare id="filename"--%><label for="fileName" class="control-label">模型文件:</label>
            <input type="file" name="file2" >
          </div>
        </div>
        <div class="modal-footer">
          <p id="errMsg" class="text-danger pull-left"></p>
          <button type="button" class="btn btn-primary" onclick="submitForm();" id="saveBtn">上传</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        </div>
      </form>
    </div>
  </div>
</div>


<div class="modal fade" id="fileAddModal2" tabindex="-1" role="dialog" aria-labelledby="AddUserModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="AddUserModalLabel2">模型参数</h4>
      </div>
      <form role="form" id="dataForm2" enctype="multipart/form-data" method="post" >
        <div class="modal-body">
          <div class="form-group">
            <label for="name" class="control-label">名称:</label>
            <span class="form-control" id="name" ></span>
            <label for="jmyps" class="control-label">建模样品数:</label>
            <span class="form-control" id="jmyps"></span>
            <label for="zcfs" class="control-label">模型主成分数:</label>
            <span class="form-control" id="zcfs"></span>
            <label for="r" class="control-label">R²（决定系数）:</label>
            <span class="form-control" id="r"></span>
            <label for="rmscv" class="control-label">RMSCV（交叉验证均方根误差）:</label>
            <span class="form-control" id="rmscv"></span>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
        </div>
      </form>
    </div>
  </div>
</div>



<script type="text/javascript">
  var analysisId = '${param.analysisId}';
</script>

	<!-- js时间库 -->
	<script	src="${pageContext.request.contextPath }/js/moment/moment-with-locales.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath }/js/JQuery/jquery.form.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath }/js/biz/comment/comment_biz.js?ver=${ver}" type="text/javascript"></script>


</body>
</html>