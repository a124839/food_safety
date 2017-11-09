<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>欢迎使用</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
   <%--  <%@include file="/common/common.jsp" %> --%>
   <!-- Ionicons -->

<link rel="stylesheet" href="${pageContext.request.contextPath }/AdminLTE/dist/css/ionicons.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/AdminLTE/dist/css/font-awesome.min.css">
      <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE/bootstrap/css/bootstrap.min.css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE/dist/css/skins/_all-skins.min.css">
    <!-- jQuery 2.2.0 -->
    <script src="${pageContext.request.contextPath }/js/JQuery/jquery-2.2.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/laypage/laypage.js"></script>
    <script type="text/javascript">
  		var headerHost = '${header["host"]}';
  		var ctx = '${pageContext.request.contextPath }';
	</script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

 
    <!-- Left side column. contains the logo and sidebar -->
    <%@include file="/common/top.jsp" %>
    <%@include file="/common/left.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->

        <!-- Main content -->
        <section class="content">
            <!-- Info boxes -->
            <!--  
            <div class="row">
                <shiro:hasPermission name="projectManage">
                    <div class="col-lg-3 col-xs-6">
                        <div class="small-box bg-aqua">
                            <div class="inner">
                                <h3>${countProject}<span style="font-size: 14px;">个</span></h3>
                                <p>实验</p>
                            </div>
                            <div class="icon">
                                <i class="ion ion-clipboard"></i>
                            </div>
                            <a href="${pageContext.request.contextPath}/project/toProjectManagePage"
                               class="small-box-footer">查看 <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="algorithmManage">
                    <div class="col-lg-3 col-xs-2">
                        <div class="small-box bg-green">
                            <div class="inner">
                                <h3>${countAlgorithm}<span style="font-size: 14px;">个</span></h3>
                                <p>算法</p>
                            </div>
                            <div class="icon">
                                <i class="ion ion-calculator"></i>
                            </div>
                            <a href="${pageContext.request.contextPath}/algorithm/toAlgorithmManagePage"
                               class="small-box-footer">查看 <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="sampleManage">
                    <div class="col-lg-3 col-xs-2">
                        <div class="small-box bg-yellow">
                            <div class="inner">
                                <h3>${countSample}<span style="font-size: 14px;">个</span></h3>

                                <p>样品</p>
                            </div>
                            <div class="icon">
                                <i class="ion ion-social-dropbox"></i>
                            </div>
                            <a href="${pageContext.request.contextPath}/sample/toSamplePage" class="small-box-footer">查看<i
                                    class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="userManage">
                    <div class="col-lg-3 col-xs-2">
                        <div class="small-box bg-red">
                            <div class="inner">
                                <h3>${countUser}<span style="font-size: 14px;">个</span></h3>

                                <p>用户</p>
                            </div>
                            <div class="icon">
                                <i class="ion ion-person-stalker"></i>
                            </div>
                            <a href="${pageContext.request.contextPath}/user/toUserManagePage" class="small-box-footer">查看
                                <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="userManage">
                    <div class="col-lg-3 col-xs-2">
                        <div class="small-box bg-red">
                            <div class="inner">
                                <h3>${countUser}<span style="font-size: 14px;">个</span></h3>

                                <p>用户</p>
                            </div>
                            <div class="icon">
                                <i class="ion ion-person-stalker"></i>
                            </div>
                            <a href="${pageContext.request.contextPath}/user/toUserManagePage" class="small-box-footer">查看
                                <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </shiro:hasPermission>
                <shiro:hasPermission name="userManage">
                    <div class="col-lg-3 col-xs-2">
                        <div class="small-box bg-red">
                            <div class="inner">
                                <h3>${countUser}<span style="font-size: 14px;">个</span></h3>

                                <p>用户</p>
                            </div>
                            <div class="icon">
                                <i class="ion ion-person-stalker"></i>
                            </div>
                            <a href="${pageContext.request.contextPath}/user/toUserManagePage" class="small-box-footer">查看
                                <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </shiro:hasPermission>
            </div> --> 
            <!-- /.row 
            <div class="box box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">站内统计</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i>
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <div class="table-responsive">
                        <table class="table no-margin">
                            <thead>
                            <!--
                            <tr>
                              <th>Order ID</th>
                              <th>Item</th>
                              <th>Status</th>
                              <th>Popularity</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><label>实验</label></td>
                                <td>${countProject}</td>
                                <td><label>算法</label></td>
                                <td>${countAlgorithm}</td>
                            </tr>
                            <tr>
                                <td><label>样品</label></td>
                                <td>${countSample}</td>
                                <td><label>设备</label></td>
                                <td>${countInstrument}</td>
                            </tr>
                            <tr>
                                <td><label>标准</label></td>
                                <td>${countStandards}</td>
                                <td><label>用户</label></td>
                                <td>${countUser}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>-->
            <div class="box box-default">
                <div class="box-header with-border">
                    <h3 class="box-title">系统简介</h3>

                </div>
                <p style="padding: 20px; line-height: 32px;">

                    系统简介
                </p>

            </div>

            <div class="box box-default">
                <div class="box-header with-border">
                    <h3 class="box-title">在线人员</h3>

                </div>
                <div class="box-body">
                    <div class="table-responsive">
                        <table class="table no-margin">
                            <thead>
                            <tr>
                                <th>用户名称</th>
                                <th>导师</th>
                                <th>研究方向</th>
                                <th>联系方式</th>
                            </tr>
                            </thead>
                            <tbody id="onlineBody">
                            </tbody>
                        </table>
                        <div id="pager"></div>
                    </div>
                    <!-- /.table-responsive -->
                </div>

            </div>
            <!-- /.box -->
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@include file="/common/footer.jsp" %>

</div>

<!-- Bootstrap 3.3.5 -->
<script src="${pageContext.request.contextPath }/AdminLTE/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath }/AdminLTE/dist/js/app.min.js"></script>
<!-- SlimScroll 1.3.0 -->
<script src="${pageContext.request.contextPath }/AdminLTE/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath }/AdminLTE/dist/js/demo.js"></script>
<script src="${pageContext.request.contextPath }/js/biz/webSocket/onlineUser.js"></script>


<!-- ./wrapper -->
</body>
</html>
