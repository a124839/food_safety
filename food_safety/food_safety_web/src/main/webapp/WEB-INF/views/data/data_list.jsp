<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="0">
<title>数据管理</title>
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
        数据管理
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">数据管理</a></li>
      </ol>
    </section>
<input type="hidden" id="datasId"/>    <!-- Main content -->
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
                      <label for="userName" class="col-sm-2 col-md-2 2ol-lg-1 control-label">实验名称：</label>

                  <div class="col-sm-2">
                    <input type="text" class="form-control" id="projectName" placeholder="实验名称">
                  </div>
                   <label for="roleName" class="col-sm-2 col-lg-1 control-label">设备：</label>

                  <div class="col-sm-2">
	                  <select class="form-control"  name="instrument" id="instrumentName">
	                  <option value=''>全部</option>
		                 <c:forEach items="${instruments}" var="o" >
		                     <option value="${o.id}">${o.name}-${o.code}</option>
		                 </c:forEach>
	                  </select>
                  </div>
                  <label for="roleName" class="col-sm-2  col-lg-1 control-label">样品：</label>

                  <div class="col-sm-2">
                    <input type="text" class="form-control" id="samplesName" placeholder="样品编号">
                  </div>
                   <div class="col-sm-2 col-lg-1">
                   	<button type="button" class="btn btn-primary pull-right" onclick="search();">搜索</button>
                   </div>
                      <shiro:hasPermission name="dataManageExport">
                        <div class="col-sm-1">
                            <button type="button" class="btn btn-primary" onclick="exportData();">导出</button>
                        </div>
                      </shiro:hasPermission>
                </div>
            <div class="form-group">
              	<div class="col-sm-3 col-lg-3">
                    <shiro:hasPermission name="dataManageImport">
                        <a href="javascript:showAddModal();">导入数据</a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="dataManagePreprocess">
                        &nbsp;/&nbsp;<a href="javascript:showAlgorithmModal();">预处理</a>
                    </shiro:hasPermission>
                </div>
              </div>

              <table id="example2" class="table table-bordered table-hover text-center ">
                <thead>
                <tr>
                    <th>序号</th>
                  <th>实验名称</th>
                  <th>仪器</th>
                    <th>样品</th>
                    <th>分辨率</th>
                    <th>波长范围</th>
                    <th>扫描次数</th>
                    <th>扫描时间</th>
                  <th>文件名</th>
                  <th>上传人 </th>
                  <th>上传时间</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody id="data_list">
                <tr><td colspan="7"></td></tr>
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
      <div class="modal-body">
        <div class="form-group" id="projectFormGroup">
            <label for="project" class="control-label" >实验:</label>
            <select class="form-control"  name="project" id="project">
                <c:forEach items="${projects}" var="o" >
                <option value="${o.id}">${o.name}</option>
                 </c:forEach>
	            </select>
          </div>
          <div class="form-group"  id="instrumentFormGroup">
            <label for="instrument" class="control-label">设备:</label>
             <select class="form-control"  name="instrument" id="instrument">
                 <c:forEach items="${instruments}" var="o" >
                     <option value="${o.id}">${o.name}-${o.code}</option>
                 </c:forEach>
	                  </select>
          </div>
          <div class="form-group">
              <label for="resolution" class="control-label">分辨率:</label>
              <input class="form-control" name="resolution">
              <label for="wavelengthRange" class="control-label">波长范围:</label>
              <input class="form-control" name="wavelengthRange">
              <label for="scanningTimes" class="control-label">扫描次数:</label>
              <input class="form-control" name="scanningTimes">
              <label for="scanningDuration" class="control-label">扫描时间:</label>
              <input class="form-control" name="scanningDuration" readonly>
          </div>
          <%--<div class="form-group"  id="sampleFormGroup">
              <label for="sample" class="control-label">样品:</label>
              <!--  <input type="text" class="form-control" id="samples" name="sample" placeholder="样品名称"> -->
              <select class="form-control"  name="sample" id="samples">
                  <c:forEach items="${samples}" var="o" >
                      <option value="${o.id}">${o.name}-${o.code}</option>
                  </c:forEach>
              </select>
          </div>--%>
          <div class="form-group" id="userNameFormGroup">
                <label for="fileName" class="control-label">选择文件:</label>
            <input type="file" name="file"  multiple="multiple">
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
<%-- <div class="modal fade" id="fileAddModal" tabindex="-1" role="dialog" aria-labelledby="AddUserModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="AddUserModalLabel">导入数据</h4>
      </div>
        <form role="form" id="dataForm" enctype="multipart/form-data" method="post" >
      <div class="modal-body">
        <div class="form-group" id="projectFormGroup">
            <label for="project" class="control-label" >实验:</label>
            <select class="form-control"  name="project" id="project">
                <c:forEach items="${projects}" var="o" >
                <option value="${o.id}">${o.name}</option>
                 </c:forEach>
	            </select>
          </div>
          <div class="form-group"  id="instrumentFormGroup">
            <label for="instrument" class="control-label">设备:</label>
             <select class="form-control"  name="instrument" id="instrument">
                 <c:forEach items="${instruments}" var="o" >
                     <option value="${o.id}">${o.name}-${o.code}</option>
                 </c:forEach>
	                  </select>
          </div>
          <div class="form-group">
              <label for="resolution" class="control-label">分辨率:</label>
              <input class="form-control" name="resolution">
              <label for="resolution" class="control-label">波长范围:</label>
              <input class="form-control" name="wavelengthRange">
              <label for="resolution" class="control-label">扫描次数:</label>
              <input class="form-control" name="scanningTimes">
              <label for="resolution" class="control-label">扫描时间:</label>
              <input class="form-control" name="scanningDuration" readonly>
          </div>

          <div class="form-group"  id="sampleFormGroup">
              <label for="sample" class="control-label">样品:</label>
             <!--  <input type="text" class="form-control" id="samples" name="sample" placeholder="样品名称"> -->
             <select class="form-control"  name="sample" id="samples">
                  <c:forEach items="${samples}" var="o" >
                      <option value="${o.id}">${o.name}-${o.code}</option>
                  </c:forEach>
              </select>
          </div>
          <div class="form-group" id="userNameFormGroup">
            @declare id="filename"<label for="fileName" class="control-label">选择文件:</label>
            <input type="file" name="file"  multiple="multiple">
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
</div> --%>
<form method="post" action="" id="analysisForm">
    <input type="hidden" id="analysisParams" name="params">
</form>

<form method="post" action="${pageContext.request.contextPath}/analysis/toImportResultPage" id="dataForm2">
    <input type="hidden" id="dataIds" name="idsStr">
</form>
<div class="modal fade" id="selectAlgorithm" tabindex="-1" role="dialog" aria-labelledby="selectAlgorithmModal" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="selectAlgorithmModal">选择算法</h4>
                    <div class="modal-body">
                        <form role="form" id="algorithmForm" class="form-horizontal">

                        <div class="form-group">
                            <label for="algorithm" class="control-label col-sm-2" >算法:</label>
                           <div class="col-sm-9">
                               <select class="form-control"  name="algorithm" id="algorithm"></select>
                           </div>
                        </div>
                            <div id="params">

                            </div>
                        <div class="form-group">
                            <label for="projectName2" class="control-label col-sm-2" >实验:</label>
                            <div class="col-sm-7">
                                <input type="text" id="projectName2" class="form-control"/>
                            </div>
                            <div class="col-sm-2">
                                <button type="button" class="btn btn-primary pull-right" onclick="searchData();">搜索数据</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <table class="table table-bordered table-hover text-center">
                                <thead>
                                   <tr>
                                       <th><input type="checkbox" id="all"></th>
                                       <th>实验名称</th>
                                       <th>设备</th>
                                       <th>样品</th>
                                       <th>上传时间</th>
                                   </tr>
                                </thead>
                                <tbody id="datas">



                                </tbody>
                            </table>
                            <div id="laypager2"></div>
                            <input type="hidden" id="currsor2" value="1">
                        </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <p id="errMsg2" class="text-danger pull-left"></p>

                        <button type="button" class="btn btn-primary" onclick="analysisData();" id="handleButton">处理</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                       &nbsp; <a href="javascript:toImportPage()">导入数据</a>
                    </div>

            </div>
        </div>
    </div>

</div>


<div class="modal fade bs-example-modal-sm" id="pp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <%--<button type="button" class="close" data-dismiss="modal"  aria-hidden="true">&times;</button>--%>
                <h4 class="modal-title" id="myModalLabel2"></h4>
            </div>
            <div class="modal-body" id="modalBody2">
                正在处理...
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>--%>
            </div>
        </div>
    </div>
</div>



<script>
    function loadPage2(id,totalPages,callback){
        laypage.dir = '${pageContext.request.contextPath }/css/laypage/laypage.css';//自定义样式路径
        laypage({
            cont: document.getElementById(id), //容器。值支持id名、原生dom对象，jquery对象,
            pages: totalPages, //总页数
            skin: '#1874CD', //加载内置皮肤，也可以直接赋值16进制颜色值，如:#c00
            groups: 10, //连续显示分页数
            curr: $("#currsor2").val(), //当前页
            jump:function(obj,first){
                $("#currsor2").val(obj.curr);
                if(!first){
                    callback();
                }
            }
        });
    }
</script>
	<!-- js时间库 -->
	<script	src="${pageContext.request.contextPath }/js/moment/moment-with-locales.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath }/js/JQuery/jquery.form.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath }/js/biz/data/data_biz.js?ver=${ver}" type="text/javascript"></script>


</body>
</html>