<%@ page language="java"  contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>登录</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <%@include file="/common/common.jsp" %>
</head>
<body class="hold-transition login-page">
 <div class="l-logo ">
   
  </div>
<div class="login-box">

  <!-- /.login-logo -->
  <div class="login-box-body" >
    <p style="font-size:18px;padding-top:20px;padding-bottom:20px;">用户登录</p>
  <!--   <p class="login-box-msg">Sign in to start your session</p> -->

    <form action="../../index2.html" method="post">
      <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="用户名" id="loginAccount">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="密码" id="password">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-4">
        <small id="errorMsg" class="text-red"></small>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button id="login" type="button" class="btn btn-primary btn-block btn-flat" onclick="userLogin();">登录</button>
        </div>
          <div class="col-xs-4">
              <button id="reg" type="button" class="btn btn-primary btn-block btn-flat" onclick="toRegPage();">注册</button>
          </div>
        <!-- /.col -->
      </div>
    </form>

    <!-- /.social-auth-links -->

  </div>
  <!-- /.login-box-body -->

</div>
  <div class="login-foot">版权所有：北京工商大学</div>	
</body>
    <script type="text/javascript">
        var returnUrl = '${param.goTo}';
  //绑定回车事件
    $('#login').bind('keypress',function(e){
    	if(e.keyCode == '13'){
    		userLogin();
    	}
    });
    
    $("#password").keyup(function(e){
    	if(e.keyCode == '13'){
    		userLogin();
    	}
    });
    	function userLogin(){
    		$("#errorMsg").html("");
    		$("#login").addClass("disabled");
            $("#login").attr('disabled',true);
    		var url = ctx+"/login";
    		var postData = {
    				loginAccount:$("#loginAccount").val(),
    				password:$("#password").val()
    		};
    		$.post(url,postData,function(data){
    			if(data.success == 1){
    				if(returnUrl != ''){
                        window.location.href = returnUrl;
                    }else{
                        window.location.href = ctx+'/index';
                    }
    			}else if(data.errorCode == 1002){
    				$("#login").removeClass("disabled");
                    $("#login").attr('disabled',false);
    				$("#errorMsg").html("用户名或密码错误！");
    			}else{
    				$("#login").removeClass("disabled");
                    $("#login").attr('disabled',false);
    				$("#errorMsg").html(data.errorMessage);
    			}
    		});
    	}
        function toRegPage(){
            window.location.href = ctx + '/register.jsp';
        }
    </script>

</html>