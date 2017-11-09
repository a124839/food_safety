<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>处理结果页面</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<%@include file="/common/common.jsp"%>
<!-- DATA TABLES -->
<link
	href="${pageContext.request.contextPath }/AdminLTE/plugins/datatables/dataTables.bootstrap.css"
	rel="stylesheet" type="text/css" />
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<%@include file="/common/top.jsp"%>
		<%@include file="/common/left.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>处理结果页</h1>
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/index"><i
							class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active"><a href="#">处理结果</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<!-- <div class="box-header"></div> -->
							<!-- /.box-header -->
							<!-- 结果显示页-->
                            
							<div class="col-xs-6 col-md-3">
								<div class="thumbnail">
									<img src="${pageContext.request.contextPath }/images/dA01.png" alt="">
									<div class="caption">
										<h3>PDS</h3>
										<p>模型转移算法PDS(分段直接标准化)</p>
										<p>
											<a href="${pageContext.request.contextPath}/dataAnalysis/toDataAnalysisPDSPage" class="btn btn-primary" role="button">选择此算法</a>
											<a href="#" class="btn btn-default" role="button" id="pds">查看公式</a>
										</p>
										<div class="hidden" id="pds">
										  [Xc_after]= pds(Xm,Xs,Xc,wleft,wright,numcomp,flag)  
										</div>
									</div>
								</div>
							</div>
                            
                            
                            <div class="col-xs-6 col-md-3">
                                <div class="thumbnail">
                                    <img src="${pageContext.request.contextPath }/images/dA01.png" alt="">
                                    <div class="caption">
                                        <h3>SST</h3>
                                        <p>模型转移算法SST(光谱空间变换)</p>
                                        <p>
                                            <a href="${pageContext.request.contextPath}/dataAnalysis/toDataAnalysisSSTPage" class="btn btn-primary" role="button">选择此算法</a>
                                            <!-- <a href="#" class="btn btn-default" role="button">查看公式</a> -->
                                            <button type="button" class="btn" onclick="showFormulae();">查看公式</button>
                                            
                                        </p>
                                    </div>
                                </div>
                            </div>
                            
                            
                            <div class="col-xs-6 col-md-3">
                                <div class="thumbnail">
                                    <img src="${pageContext.request.contextPath }/images/dA02.png" alt="...">
                                    <div class="caption">
                                        <h3>PLSCV</h3>
                                        <p>建模算法PLSCV(交叉验证偏最小二乘)</p>
                                        <p>
                                            <a href="${pageContext.request.contextPath}/dataAnalysis/toDataAnalysisPLSCVPage" class="btn btn-primary" role="button">选择此算法</a>
                                            <a href="#" class="btn btn-default" role="button" id = "plscv">查看公式</a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            
                            
                            <div class="col-xs-6 col-md-3">
                                <div class="thumbnail">
                                    <img src="${pageContext.request.contextPath }/images/dA02.png" alt="...">
                                    <div class="caption">
                                        <h3>KHLSSVM</h3>
                                        <p>建模算法SST(磷虾群最小二乘支持回归向量机)</p>
                                        <p>
                                            <a href="${pageContext.request.contextPath}/dataAnalysis/toDataAnalysisKHLSSVMPage" class="btn btn-primary" role="button">选择此算法</a>
                                            <a href="#" class="btn btn-default" role="button" id = "khlssvm">查看公式</a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            
                            
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>
				<!-- /.col -->
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<%@include file="/common/footer.jsp"%>
	</div>
	<!-- ./wrapper -->
	<!-- js时间库 -->
	<script
		src="${pageContext.request.contextPath }/js/moment/moment-with-locales.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath }/js/biz/dataAnalysis/dataAnalysis2_biz.js?ver=${ver}"
		type="text/javascript"></script>

</body>
</html>