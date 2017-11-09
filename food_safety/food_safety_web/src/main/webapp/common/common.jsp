<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  import="java.util.Date"%>

<!-- bootstrap 3.0.2 -->
<link href="${pageContext.request.contextPath }/AdminLTE/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/AdminLTE/dist/css/ionicons.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/AdminLTE/dist/css/font-awesome.min.css">

<!-- Theme style -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/AdminLTE/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/AdminLTE/dist/css/skins/_all-skins.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/AdminLTE/plugins/iCheck/flat/blue.css">
<!-- Morris chart -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/AdminLTE/plugins/morris/morris.css">
<!-- jvectormap -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<!-- Date Picker -->
<%--<link rel="stylesheet" href="${pageContext.request.contextPath }/AdminLTE/plugins/datepicker/datepicker3.css">--%>
<!-- Daterange picker -->
<%--<link rel="stylesheet" href="${pageContext.request.contextPath }/AdminLTE/plugins/daterangepicker/daterangepicker-bs3.css">--%>
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/AdminLTE/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/JQueryUI/jquery-ui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/JQueryUI/jquery-ui.structure.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/JQueryUI/jquery-ui.theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/JQueryUI/jquery-ui-timepicker-addon.css">

<!-- jQuery 2.0.2 -->
<script src="${pageContext.request.contextPath }/js/JQuery/jquery-2.2.0.min.js"></script>
<script src="${pageContext.request.contextPath }/js/JQuery/jquery.json-2.4.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="${pageContext.request.contextPath }/js/JQuery/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath }/js/JQuery/jquery-ui-timepicker-addon.js"></script>
<script src="${pageContext.request.contextPath }/js/JQuery/datepicker-zh-TW.js"></script>

<%--<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>--%>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.5 -->
<script src="${pageContext.request.contextPath }/AdminLTE/bootstrap/js/bootstrap.min.js"></script>
<!-- Morris.js charts -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script> -->
<%-- <script src="${pageContext.request.contextPath }/AdminLTE/plugins/morris/morris.min.js"></script> --%>
<!-- Sparkline -->
<script src="${pageContext.request.contextPath }/AdminLTE/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<%--<script src="${pageContext.request.contextPath }/AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${pageContext.request.contextPath }/AdminLTE/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>--%>
<!-- jQuery Knob Chart -->
<script src="${pageContext.request.contextPath }/AdminLTE/plugins/knob/jquery.knob.js"></script>
<!-- daterangepicker -->
<%--<script src="${pageContext.request.contextPath }/js/moment/moment-with-locales.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath }/AdminLTE/plugins/daterangepicker/daterangepicker.js"></script>--%>
<!-- datepicker -->
<%--<script src="${pageContext.request.contextPath }/AdminLTE/plugins/datepicker/bootstrap-datepicker.js"></script>--%>
<!-- Bootstrap WYSIHTML5 -->
<script src="${pageContext.request.contextPath }/AdminLTE/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="${pageContext.request.contextPath }/AdminLTE/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath }/AdminLTE/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath }/AdminLTE/dist/js/app.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<%-- <script src="${pageContext.request.contextPath }/AdminLTE/dist/js/pages/dashboard.js"></script> --%>
<!-- AdminLTE for demo purposes -->
<%--<script src="${pageContext.request.contextPath }/AdminLTE/dist/js/demo.js"></script>--%>
<script type="text/javascript">
	var headerHost = '${header["host"]}';
  	var ctx = '${pageContext.request.contextPath }';
</script>
<%
  request.setAttribute("ver", new Date().getTime());
%>
<script src="${pageContext.request.contextPath }/js/common/commonJS.js?ver=${ver}"></script>

        