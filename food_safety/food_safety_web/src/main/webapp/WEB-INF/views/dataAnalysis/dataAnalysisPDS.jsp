<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>分析详情</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<%@include file="/common/common.jsp"%>
<!-- DATA TABLES -->
<link
	href="${pageContext.request.contextPath }/AdminLTE/plugins/datatables/dataTables.bootstrap.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath }/js/echarts/echarts.min.js"></script>

</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<%@include file="/common/top.jsp"%>
		<%@include file="/common/left.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>数据处理</h1>
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/index"><i
							class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active"><a href="#">数据处理</a></li>
					<li class="active"><a href="#">PDS</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<!-- 1.显示算法 -->
								<!-- 1.1 显示算法的名称，类型，等信息 -->
								<div class="col-sm-12">
									<h2>
										<label>PDS算法信息</label>
									</h2>
								</div>
								<div class="">
									<p>
										&nbsp;&nbsp;&nbsp;
										<mark>function [Xc_after]=
											pds_ZJ(Xm,Xs,Xc,wleft,wright,numcomp,flag)</mark>
									</p>
									<p>
										&nbsp;&nbsp;&nbsp;
										<mark>参数说明:</mark>
										&nbsp;&nbsp;&nbsp; Xc_after为转换后的光谱&nbsp;&nbsp;
										,&nbsp;&nbsp;Xm为主机标准噗,&nbsp;&nbsp;Xs为子机标准谱,&nbsp;&nbsp;Xc为子机待测谱,
										&nbsp;&nbsp;wleft、wright分别为左右窗口数,&nbsp;&nbsp;numcomp为因子数/主成分数，
									</p>
								</div>

								<!-- 直接拿过来的，需要改 -->
								<form method="post" action="" id="dataAnalysisForm">
									<input type="hidden" id="dataAnalysisPDSParams" name="pdsParams">
								</form>

								<%-- <form method="post"
									action="${pageContext.request.contextPath}/analysis/toImportResultPage"
									id="dataForm2">
									<input type="hidden" id="dataIds" name="idsStr">
								</form> --%>


								<!-- 2. 选择数据 -->
								<!-- 2.1 显示选择数据按钮及参数 -->
								<form role="form" id="dataAnalysisPDS" class="form-horizontal">
									<div class="col-sm-12">
										<h2>
											<label>PDS参数选择</label>
										</h2>
									</div>
									<div id="pdsSelectParams" class="form-group">
										<div class="col-sm-2 ">
											<!-- <input type="button" value="选择主机标准谱Xm"
												class="btn btn-primary"> -->
											<!-- <button class="btn btn-primary"  type="button" data-toggle="modal" data-target=".bs-selectDatas-modal-lg" id="xm" name="xm">选择主机标准谱Xm</button> -->
											<button class="btn btn-primary"  type="button" data-toggle="modal" id="xm" name="selectDatas" onclick="showXmModal()">选择主机标准谱Xm</button>
										</div>
										<!-- <div class="col-sm-2">
											<input type="text" class="form-control"
												id="showSelectedDatas1" placeholder="显示已选择数据" readonly>
										</div> -->
										<div class="col-sm-2 ">
											<!-- <button class="btn btn-primary" type="button" data-toggle="modal" data-target=".bs-selectDatas-modal-lg" id="xs" name="xs">选择从机标准谱Xs</button> -->
											<button class="btn btn-primary"  type="button" data-toggle="modal" id="xs" name="selectDatas" onclick="showXsModal()">选择主机标准谱Xs</button>
										</div>
										<!-- <div class="col-sm-2">
											<input type="text" class="form-control"
												id="showSelectedDatas2" placeholder="显示已选择数据" readonly>
										</div> -->
										<div class="col-sm-2">
											<!-- <button class="btn btn-primary" type="button" data-toggle="modal" data-target=".bs-selectDatas-modal-lg" id="xc" name="xc">选择从机待测谱Xc</button> -->
											<button class="btn btn-primary"  type="button" data-toggle="modal" id="xc" name="selectDatas" onclick="showXcModal()">选择主机标准谱Xc</button>
										</div>
										<!-- <div class="col-sm-2">
											<input type="text" class="form-control"
												id="showSelectedDatas3" placeholder="显示已选择数据" readonly>
										</div> -->

										
										<div class="col-sm-12"></div>
										<div class="form-group" id="params">
											<label for="wleft" class="col-sm-2 control-label">请输入左窗口数:</label>
											<input class="col-sm-2 " name="paramsValue" id="wleft">
											<label for="wright" class="col-sm-2 control-label">请输入右窗口数:</label>
											<input class="col-sm-2  " name="paramsValue" id="wright">
											<label for="numcomp" class="col-sm-2 control-label">请输入主成分数:</label>
											<input class="col-sm-2  " name="paramsValue" id="numcomp">

										</div>

										<div class="col-sm-2 col-sm-offset-10">
											<input type="reset" value="重置" class="btn btn-primary">
											&nbsp;&nbsp;&nbsp;
											<button type="button" class="btn btn-primary"
												onclick="analysisPDSData();" id="handleData">开始处理</button>
										</div>

									</div>
									<!-- 3. 数据列表 -->
									<!-- 3.1 数据可以选择 -->
									<!-- div class="col-sm-12">
										<h2>
											<label>数据信息</label>
										</h2>
									</div>
									<div class="form-group">
										<table id="example2"
											class="table table-bordered table-hover text-center ">
											<thead>
												<tr>
													<th><input type="checkbox" id="all"></th>
													<th>序号</th>
													<th>实验名称</th>
													<th>仪器</th>
													<th>样品</th>
													<th>分辨率</th>
													<th>波长范围</th>
													<th>扫描次数</th>
													<th>扫描时间</th>
													<th>文件名</th>
													<th>上传人</th>
													<th>上传时间</th>
												</tr>
											</thead>
											<tbody id="data_list">
												<tr>
													<td colspan="7"></td>
												</tr>
											</tbody>

										</table>
										<div class="form-group"></div>
									</div>
 -->
								</form>
								<!-- /表单 -->
							</div>
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<%@include file="/common/footer.jsp"%>
	</div>
	<div class="pull-right"><%@include file="/common/pager.jsp"%></div>
	<!-- 3.0 数据列表改-->
	<!-- 3.0.1 更改为模态框弹出来选择数据 -->
	<!-- !!模态框一般直接作为body的子元素 -->
	<div class="modal fade bs-selectDatas-modal-lg" id="selectXmDatas" tabindex="-1" role="dialog"
		aria-labelledby="selectDataTitle" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
				    <!-- 模态框的右上角的x号 -->
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					
					<!-- <h4 class="modal-title" id="selectAlgorithmModal">选择算法</h4> -->
					<!-- <div class="" id="modalTitle"> -->
					</div>
					<h4 id="selectDataTitle" class="modal-title">选择Xm</h4>
					<div class="modal-body">
						<div class="form-group">
							<label for="projectName2" class="control-label col-sm-2">实验:</label>
							<div class="col-sm-7">
								<input type="text" id="projectName" class="form-control" />
							</div>
							<div class="col-sm-2">
								<button type="button" class="btn btn-primary pull-right"
									onclick="searchData();">搜索数据</button>
							</div>
						</div>
						<div class="form-group">
							<table class="table table-bordered table-hover text-center">
								<thead>
									<tr>
										<th><input type="checkbox" id="xm_all" class="checkbox-info"></th>
										<th>序号</th>
										<th>实验名称</th>
										<th>仪器</th>
										<th>样品</th>
										<th>分辨率</th>
										<th>波长范围</th>
										<th>扫描次数</th>
										<th>扫描时间</th>
										<th>文件名</th>
										<th>上传人</th>
										<th>上传时间</th>
									</tr>
								</thead>
								<tbody id="data_list">
									<tr>
										<td colspan="7"></td>
									</tr>
								</tbody>
							</table>
							<div id="laypager"></div>
							<input type="hidden" id="currsor" value="1">
						</div>
						
					</div>
					<div class="modal-footer">
						<p id="errMsg2" class="text-danger pull-left"></p>

						<button type="button" class="btn btn-primary"
							onclick="bindClickEventXm();" id="confirmSelectedXm" data-dismiss="modal">确定</button>
						<button type="button" class="btn btn-default" type="reset">取消</button>
						<!-- &nbsp; <a href="javascript:toImportPage()">重新选择数据</a> -->
					</div>

				</div>
			</div>
		</div>

<div class="modal fade bs-selectDatas-modal-lg" id="selectXsDatas" tabindex="-1" role="dialog"
        aria-labelledby="selectDataTitle" aria-hidden="true"
        data-backdrop="static">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <!-- 模态框的右上角的x号 -->
                    <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    
                    <!-- <h4 class="modal-title" id="selectAlgorithmModal">选择算法</h4> -->
                    <!-- <div class="" id="modalTitle"> -->
                    </div>
                    <h4 id="selectDataXs" class="modal-title">选择Xs</h4>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="projectName2" class="control-label col-sm-2">实验:</label>
                            <div class="col-sm-7">
                                <input type="text" id="projectName1" class="form-control" />
                            </div>
                            <div class="col-sm-2">
                                <button type="button" class="btn btn-primary pull-right"
                                    onclick="searchData();">搜索数据</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <table class="table table-bordered table-hover text-center">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox" id="xs_all" class="checkbox-info"></th>
                                        <th>序号</th>
                                        <th>实验名称</th>
                                        <th>仪器</th>
                                        <th>样品</th>
                                        <th>分辨率</th>
                                        <th>波长范围</th>
                                        <th>扫描次数</th>
                                        <th>扫描时间</th>
                                        <th>文件名</th>
                                        <th>上传人</th>
                                        <th>上传时间</th>
                                    </tr>
                                </thead>
                                <tbody id="xs_data_list">
                                    <tr>
                                        <td colspan="7"></td>
                                    </tr>
                                </tbody>
                            </table>
                            <div id="laypager1"></div>
                            <input type="hidden" id="currsor1" value="1">
                        </div>
                        
                    </div>
                    <div class="modal-footer">
                        <p id="errMsg2" class="text-danger pull-left"></p>

                        <button type="button" class="btn btn-primary"
                            onclick="bindClickEventXs();" id="confirmSelectedDatasXs" data-dismiss="modal">确定</button>
                        <button type="button" class="btn btn-default" type="reset">取消</button>
                        <!-- &nbsp; <a href="javascript:toImportPage()">重新选择数据</a> -->
                    </div>

                </div>
            </div>
        </div>

<div class="modal fade bs-selectDatas-modal-lg" id="selectXcDatas" tabindex="-1" role="dialog"
        aria-labelledby="selectDataTitle" aria-hidden="true"
        data-backdrop="static">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <!-- 模态框的右上角的x号 -->
                    <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    
                    <!-- <h4 class="modal-title" id="selectAlgorithmModal">选择算法</h4> -->
                    <!-- <div class="" id="modalTitle"> -->
                    </div>
                    <h4 id="selectDataXs" class="modal-title">选择Xc</h4>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="projectName2" class="control-label col-sm-2">实验:</label>
                            <div class="col-sm-7">
                                <input type="text" id="projectName2" class="form-control" />
                            </div>
                            <div class="col-sm-2">
                                <button type="button" class="btn btn-primary pull-right"
                                    onclick="searchData();">搜索数据</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <table class="table table-bordered table-hover text-center">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox" id="xc_all" class="checkbox-info"></th>
                                        <th>序号</th>
                                        <th>实验名称</th>
                                        <th>仪器</th>
                                        <th>样品</th>
                                        <th>分辨率</th>
                                        <th>波长范围</th>
                                        <th>扫描次数</th>
                                        <th>扫描时间</th>
                                        <th>文件名</th>
                                        <th>上传人</th>
                                        <th>上传时间</th>
                                    </tr>
                                </thead>
                                <tbody id="xc_data_list" >
                                    <tr>
                                        <td colspan="7"></td>
                                    </tr>
                                </tbody>
                            </table>
                            <div id="laypager2"></div>
                            <input type="hidden" id="currsor2" value="1">
                        </div>
                        
                    </div>
                    <div class="modal-footer">
                        <p id="errMsg2" class="text-danger pull-left"></p>

                        <button type="button" class="btn btn-primary"
                            onclick="bindClickEventXc();" id="confirmSelectedDatasXc" data-dismiss="modal">确定</button>
                        <button type="button" class="btn btn-default" type="reset">取消</button>
                        <!-- &nbsp; <a href="javascript:toImportPage()">重新选择数据</a> -->
                    </div>

                </div>
            </div>
        </div>
<!-- 	</div> -->
	<!-- ./wrapper -->
	<!-- js时间库 -->
	<script
		src="${pageContext.request.contextPath }/js/moment/moment-with-locales.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
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
	<script
		src="${pageContext.request.contextPath }/js/biz/dataAnalysis/dataAnalysisPDS_biz.js?ver=${ver}"
		type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/js/JQuery/jquery.form.js" type="text/javascript"></script>
</body>
</html>