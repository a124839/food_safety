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
				</ol>
			</section>
			<!-- / 头结束-->
			<!-- 主题部分 -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header"></div>
							<!-- 预想思路 -->
							<!-- 1.显示算法 -->
							<!-- 1.1 显示算法的名称，类型，等信息 -->
							<div class="col-sm-12">
								<label>算法信息</label>
							</div>

							<!-- 这个放哪？？？ -->
							<!-- 隐藏域 -->
							<form method="post" action="" id="analysisForm">
								<input type="hidden" id="analysisParams" name="params">
							</form>
							<form method="post"
								action="${pageContext.request.contextPath}/analysis/toImportResultPage"
								id="dataForm2">
								<input type="hidden" id="dataIds" name="idsStr">
							</form>
							<!-- /以上直接从datalist.jsp中拿过来的 -->

							<form role="form" id="dataAnalysis" class="form-horizontal">
								<div class="form-group">
									<table id="example2"
										class="table table-bordered table-hover text-center ">
										<thead>
											<tr>
												<th>序号</th>
												<th>名称</th>
												<th>类型</th>
												<th>状态</th>
												<th>操作人</th>
												<th>创建时间</th>
												<th>选择算法</th>
												<th>操作</th>
											</tr>
										</thead>
										<!-- 1.2 算法要能够被勾选 -->
										<tbody id="algorithm_list">
										</tbody>
									</table>
									<div id="laypager2"></div>
									<input type="hidden" id="algorithmCurrsor" value="1">
								</div>

								<!-- 2.勾选算法后显示此算法详情 -->
								<div class="col-sm-12 hide" id="showAlogrithmDetails">
									<div class="form-group">
										<table class="table table-bordered text-center">
											<tr>
												<td><b>算法名称</b></td>
												<td>${algorithm.name}</td>
												<td><b>算法类型</b></td>
												<td><c:choose>
														<c:when test="${algorithm.type == 1}">预处理</c:when>
														<c:when test="${algorithm.type == 2}">评价</c:when>
														<c:when test="${algorithm.type == 3}">建模</c:when>
														<c:when test="${algorithm.type == 4}">模型转移</c:when>
													</c:choose></td>
											</tr>
											<c:forEach items="${params}" var="o">
												<tr>
													<td><b>参数名称</b></td>
													<td>${o.name}</td>
													<td><b>默认值</b></td>
													<td>${o.value}</td>
												</tr>
											</c:forEach>
										</table>
										<div class="form-group"></div>
										<div class="form-group">
											<label for="sampleCode" class="col-sm-2 control-label">示例代码</label>
											<div class="col-sm-10">
												<textarea class="form-control" style="height: 200px;">${algorithm.sampleCode}</textarea>
											</div>
										</div>
									</div>
								</div>
								<!-- 2.1 添加按钮绑定事件，确定使用此算法，显示算法详情；取消则取消此算法详情显示 -->
								<!-- 2.2 每个算法的每个按钮都绑定不同数据选定事件 -->
								<div id="pdsSelectParams" class="form-group hide">
									<div class="col-sm-2 ">
										<input type="button" value="选择主机标准谱Xm" class="btn btn-primary">
									</div>
									<div class="col-sm-2">
										<input type="text" class="form-control"
											id="showSelectedDatas1" placeholder="显示已选择数据" readonly>
									</div>
									<div class="col-sm-2 ">
										<input type="button" value="选择从机标准谱Xs" class="btn btn-primary">
									</div>
									<div class="col-sm-2">
										<input type="text" class="form-control"
											id="showSelectedDatas2" placeholder="显示已选择数据" readonly>
									</div>
									<div class="col-sm-2">
										<input type="button" value="选择从机待测谱Xc" class="btn btn-primary">
									</div>
									<div class="col-sm-2">
										<input type="text" class="form-control"
											id="showSelectedDatas3" placeholder="显示已选择数据" readonly>
									</div>

									<br> <br> <label for="selectParam1"
										class="col-sm-2 control-label">请输入左窗口数:</label>
									<div class="col-sm-2">
										<input type="text" id="selectParam1" class="form-control " />

									</div>


									<label for="selectParam2" class="col-sm-2 control-label">
										请输入右窗口数:</label>
									<div class="col-sm-2">
										<input type="text" id="selectParam2" class="form-control " />

									</div>


									<label for="selectParam3" class="col-sm-2 control-label">
										请输入主成分数:</label>
									<div class="col-sm-2">
										<input type="text" id="selectParam3" class="form-control " />
									</div>

									<br> <br>
									<div class="col-sm-2 col-sm-offset-10">
										<input type="reset" value="重置" class="btn btn-primary">
										&nbsp;&nbsp;&nbsp; <input type="button" value="开始处理"
											class="btn btn-primary" id="handleData">
									</div>
									<!-- 隐藏域，给定pds最后参数 -->
									<input type="hidden" name="pds_falg" value="3">
								</div>
								<!-- sst -->
								<div id="sstSelectParams" class="form-group hide">
									<div class="col-sm-2 ">
										<input type="button" value="选择主机标准谱Xm" class="btn btn-primary">
									</div>
									<div class="col-sm-2">
										<input type="text" class="form-control"
											id="showSelectedDatas1" placeholder="显示已选择数据" readonly>
									</div>
									<div class="col-sm-2 ">
										<input type="button" value="选择从机标准谱Xs" class="btn btn-primary">
									</div>
									<div class="col-sm-2">
										<input type="text" class="form-control"
											id="showSelectedDatas2" placeholder="显示已选择数据" readonly>
									</div>
									<div class="col-sm-2">
										<input type="button" value="选择从机待测谱Xc" class="btn btn-primary">
									</div>
									<div class="col-sm-2">
										<input type="text" class="form-control"
											id="showSelectedDatas3" placeholder="显示已选择数据" readonly>
									</div>

									<br> <br> <label for="selectParam3"
										class="col-sm-2 control-label"> 请输入主成分数:</label>
									<div class="col-sm-2">
										<input type="text" id="selectParam3" class="form-control " />
									</div>

									<br> <br>
									<div class="col-sm-2 col-sm-offset-10">
										<input type="reset" value="重置" class="btn btn-primary">
										&nbsp;&nbsp;&nbsp; <input type="button" value="开始处理"
											class="btn btn-primary" id="handleData">
									</div>
								</div>

								<!-- plscv -->
								<div id="plscvSelectParams" class="form-group hide">
									<div class="col-sm-2 ">
										<input type="button" value="选择x" class="btn btn-primary">
									</div>
									<div class="col-sm-2">
										<input type="text" class="form-control"
											id="showSelectedDatas1" placeholder="显示已选择数据" readonly>
									</div>
									<div class="col-sm-2 ">
										<input type="button" value="选择y" class="btn btn-primary">
									</div>
									<div class="col-sm-2">
										<input type="text" class="form-control"
											id="showSelectedDatas1" placeholder="显示已选择数据" readonly>
									</div>

									<label for="selectParam1" class="col-sm-2 control-label">
										请输入主成分数:</label>
									<div class="col-sm-2">
										<input type="text" id="selectParam2" class="form-control " />
									</div>
									<br> <br>
                                    <div class="col-sm-2 col-sm-offset-10">
                                        <input type="reset" value="重置" class="btn btn-primary">
                                        &nbsp;&nbsp;&nbsp; <input type="button" value="开始处理"
                                            class="btn btn-primary" id="handleData">
                                    </div>
								</div>

								<!-- khlssvm -->
								<div id="khSelectParams" class="form-group hide">
									<div class="col-sm-2 ">
										<input type="button" value="选择训练集Xtrain"
											class="btn btn-primary">
									</div>
									<div class="col-sm-2">
										<input type="text" class="form-control"
											id="showSelectedDatas1" placeholder="显示已选择数据" readonly>
									</div>
									<div class="col-sm-2 ">
										<input type="button" value="选择训练集真值Ytrain"
											class="btn btn-primary">
									</div>
									<div class="col-sm-2">
										<input type="text" class="form-control"
											id="showSelectedDatas2" placeholder="显示已选择数据" readonly>
									</div>
									<div class="col-sm-2">
										<input type="button" value="选择测试集Xtest"
											class="btn btn-primary">
									</div>
									<div class="col-sm-2">
										<input type="text" class="form-control"
											id="showSelectedDatas3" placeholder="显示已选择数据" readonly>
									</div>
									<div class="col-sm-2">
										<input type="button" value="选择测试集真值Ytest"
											class="btn btn-primary">
									</div>
									<div class="col-sm-2">
										<input type="text" class="form-control"
											id="showSelectedDatas3" placeholder="显示已选择数据" readonly>
									</div>
                                    <!-- KH算法中的参数给定，比较多 -->
									<label for="selectParam1" class="col-sm-2 control-label">KH总运行次数:</label>
									<div class="col-sm-2">
										<input type="text" id="selectParam1" class="form-control " />

									</div>

									<label for="selectParam2" class="col-sm-2 control-label">
										KH中磷虾的个数:</label>
									<div class="col-sm-2">
										<input type="text" id="selectParam2" class="form-control " />

									</div>
									<label for="selectParam3" class="col-sm-2 control-label">
										磷虾觅食最大迭代次数:</label>
									<div class="col-sm-2">
										<input type="text" id="selectParam3" class="form-control " />
									</div>
									<label for="selectParam3" class="col-sm-2 control-label">
										gam最大值:</label>
									<div class="col-sm-2">
										<input type="text" id="selectParam3" class="form-control " />
									</div>
									<label for="selectParam3" class="col-sm-2 control-label">
										gam最小值:</label>
									<div class="col-sm-2">
										<input type="text" id="selectParam3" class="form-control " />
									</div>
									<label for="selectParam3" class="col-sm-2 control-label">
										sigma最大值:</label>
									<div class="col-sm-2">
										<input type="text" id="selectParam3" class="form-control " />
									</div>
									<label for="selectParam3" class="col-sm-2 control-label">
										sigma最小值:</label>
									<div class="col-sm-2">
										<input type="text" id="selectParam3" class="form-control " />
									</div>
									<br> <br>
                                    <div class="col-sm-2 col-sm-offset-10">
                                        <input type="reset" value="重置" class="btn btn-primary">
                                        &nbsp;&nbsp;&nbsp; <input type="button" value="开始处理"
                                            class="btn btn-primary" id="handleData">
                                    </div>
									
								</div>
								
								
								<!-- 3.显示数据 -->
								<!-- 3.1 勾选数据 -->
								<!-- 3.1 按照不同算法绑定的不同事件勾选几组数据 -->
								<div class="col-sm-12">
									<label>数据信息</label>
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
								<!-- 4.处理数据 -->

							</form>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
	<div class="pull-right"><%@include file="/common/pager.jsp"%></div>
	<!-- js时间库 -->
	<script
		src="${pageContext.request.contextPath }/js/moment/moment-with-locales.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath }/js/biz/dataAnalysis/dataAnalysis_biz1.js?ver=${ver}"
		type="text/javascript"></script>

</body>
</html>