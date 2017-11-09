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
                    <li class="active"><a href="#">KHLSSVM</a></li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-header">
                            </div> 
                            <!-- 1.显示算法 -->
                            <!-- 1.1 显示算法的名称，类型，等信息 -->
                            <div class="col-sm-12">
                                <label>算法信息</label>
                            </div>
                            
                            <!-- 2. 选择数据 -->
                            <!-- 2.1 显示选择数据按钮及参数 -->
                            
                            <!-- 3. 数据列表 -->
                            <!-- 3.1 数据可以选择 -->                         
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
    <!-- ./wrapper -->
    <!-- js时间库 -->
    <script
        src="${pageContext.request.contextPath }/js/moment/moment-with-locales.min.js"
        type="text/javascript"></script>
    <script type="text/javascript">
      var analysisDatas = '${analysisDatas}';
      var cacheKey = '${cacheKey}';
      var x = '${x}';
      var y = '${y}';
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
        src="${pageContext.request.contextPath }/js/biz/analysis/analysis_info.js?ver=${ver}"
        type="text/javascript"></script>
    <script
        src="${pageContext.request.contextPath }/js/biz/algorithm/algorithm_biz.js?ver=${ver}"
        type="text/javascript"></script>
    <script
        src="${pageContext.request.contextPath }/js/biz/data/data_biz.js?ver=${ver}"
        type="text/javascript"></script>
</body>
</html>