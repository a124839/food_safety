 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <header class="main-header">
    <!-- Logo -->
    <a href="index2.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>食品检测</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top " role="navigation">
    
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
     <div class="topbg">
         <img alt="" src="${pageContext.request.contextPath }/images/topbg.png">
<!--
         <div id="userMsg" style="display: none;">
             <div class="box collapsed-box">
                 <div class="pull-right">
                     <button id="xiao" type="button" class="btn btn-box-tool"  data-widget="collapse"></i>
                     </button>
                 </div>
                 <div id="tongzhi" class="box-body">
                 </div>
             </div>
         </div>
-->

     </div>
      <div class="navbar-custom-menu ">
        <ul class="nav navbar-nav">
            <li  class="dropdown notifications-menu">
                <a id="userMsg" href="#" class="dropdown-toggle" data-toggle="dropdown" >
                    <i class="fa fa-bell-o"></i>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <!-- inner menu: contains the actual data -->
                        <ul id="tongzhi" class="menu">
                        </ul>
                    </li>
                    <li class="footer">
                    	<a href="javascript:clearTZMsg();" >清空</a>
                    </li>
                </ul>
            </li>

          <!-- User Account: style can be found in dropdown.less -->
          <li class="user"  style="line-height: 20px; color:#ffffff;padding-top: 15px;padding-bottom: 15px;height:20px;">
              <span class="hidden-xs">${user.userName } &nbsp;${role.shiroName}</span>
              </li>
          </li>
          <li> <a href="javascript:signOut();" >退出</a></li>
          <!-- Control Sidebar Toggle Button -->
         <!--  <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li> -->
        </ul>

      </div>
    </nav>

  </header>
<!-- 添加websocket连接 -->
<script src="${pageContext.request.contextPath }/js/sockjs/sockjs-1.1.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/biz/webSocket/sock.js"></script>

  <script type="text/javascript">
  $.ajaxSetup({
	  global:false,
	  type:"post",
	  complete:function(xhr,status){
		  if($.parseJSON(xhr.responseText).errorCode == '1002'){
			  window.location.href = '${pageContext.request.contextPath}/login.jsp';
		  }
	  }
  });
  
	function signOut(){
		var url = '${pageContext.request.contextPath}/signOut';
		$.post(url,function(){
			window.location.href =' ${pageContext.request.contextPath}/login.jsp';
		});
	}


  function clearTZMsg(){
  	$('#tongzhi').empty();
  }
</script>
