<%@ page language="java"  contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>注册</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <%@include file="/common/common.jsp" %>
    <script	src="${pageContext.request.contextPath }/js/JQuery/jquery.form.js" type="text/javascript"></script>
</head>
<body class="hold-transition register-page">
 <div class="l-logo ">
   
  </div>
<div class="register-box">

  <!-- /.login-logo -->
  <div class="login-box-body" >
    <p style="font-size:18px;padding-top:20px;">注册用户</p>
  <!--   <p class="login-box-msg">Sign in to start your session</p> -->

     <div class="register-box-body">
    <p class="login-box-msg" id="err_msg" style="color: red"></p>

    <form action="" method="post" id="userForm">

      <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="用户名4-20位，不能包含特殊字符" name="loginAccount" required>
     <!--   <span class="glyphicon glyphicon-user form-control-feedback"></span>-->  
      </div>
     <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="密码" name="password" required>
     <!--   <span class="glyphicon glyphicon-user form-control-feedback"></span>-->  
      </div>
        <div class="form-group has-feedback">
            <input type="text" class="form-control" placeholder="学号" name="stuCode" >
            <!--   <span class="glyphicon glyphicon-user form-control-feedback"></span>-->
        </div>
        <div class="form-group has-feedback">
            <input type="text" class="form-control" placeholder="姓名" name="userName" required>
            <!--   <span class="glyphicon glyphicon-user form-control-feedback"></span>-->
        </div>
        <div class="form-group has-feedback">
            <input type="text" class="form-control" placeholder="联系方式" name="contact" required>
            <!--   <span class="glyphicon glyphicon-user form-control-feedback"></span>-->
        </div>
      <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="出生年月" name="birthday" required>
     <!--   <span class="glyphicon glyphicon-user form-control-feedback"></span>-->  
      </div>
      <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="研究方向" name="researchDirection">
     <!--   <span class="glyphicon glyphicon-user form-control-feedback"></span>-->  
      </div>
      <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="毕业院校" name="graduatedSchool">
     <!--   <span class="glyphicon glyphicon-user form-control-feedback"></span>-->  
      </div>
     <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="导师" name="tutor">
     <!--   <span class="glyphicon glyphicon-user form-control-feedback"></span>-->  
      </div>

     <div class="form-group has-feedback" style="padding-bottom:25px;">
         <div class="col-xs-8 col-sm-8 col-lg -8">
             类型：   <input type="radio" name="roleId"  checked  value="1"/>&nbsp;学生
         </div>
          <div class="col-xs-4 col-sm-4 col-lg -4">
                 <input type="radio" name="roleId" value="0" />&nbsp;导师
          </div>

      
     <!--   <span class="glyphicon glyphicon-user form-control-feedback"></span>-->
      </div>
      <div class="row clear">
      <div class="col-xs-8"></div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button type="button" class="btn btn-primary btn-block btn-flat" onclick="reg()">注册</button>
        </div>

        <!-- /.col -->
      </div>
    </form>
   
  </div>

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
        var accountFlag = false;
    	function reg(){
            var account = $('input[name="loginAccount"]').val();
            if(account.length ==0){
//                $("#loginAccountFormGroup").addClass('has-error');
                $("#err_msg").text('用户名为必填项！');
                $('input[name="loginAccount"]').focus();
                return ;
            }
            if(!isRegisterUserName(account)){
                $("#err_msg").text('用户名不合法！');
                $('input[name="loginAccount"]').focus();
                return ;
            }


            validateAccount();
            if(!accountFlag){
//                $("#loginAccountFormGroup").addClass('has-error');
                $("#err_msg").text('此账号已存在！');
                return ;
            }
            if($('input[name="password"]').val().length ==0){
                $("#err_msg").text('密码为必填项！');
                $('input[name="password"]').focus();
                return ;
            }
            if($('input[name="userName"]').val().length ==0){
                $("#err_msg").text('姓名为必填项！');
                $('input[name="userName"]').focus();
                return ;
            }
            if($('input[name="contact"]').val().length ==0){
                $("#err_msg").text('联系方式为必填项！');
                $('input[name="contact"]').focus();
                return ;
            }
            if($('input[name="researchDirection"]').val().length ==0){
                $("#err_msg").text('研究方向为必填项！');
                $('input[name="researchDirection"]').focus();
                return ;
            }
            if($('input[name="graduatedSchool"]').val().length ==0){
                $("#err_msg").text('毕业院校为必填项！');
                $('input[name="graduatedSchool"]').focus();
                return ;
            }


    		$("#err_msg").html("");
            var url = ctx + '/reg';
    		$("#userForm").ajaxSubmit({
               url: url,
                success: function (res) {
                    if(res.success == 1){
                        $("#err_msg").html("提交成功，请等待审核！");
                    }else{
                        $("#err_msg").html("注册失败，请稍后重试！");
                    }
                },
                error:function(e){
                    console.log(e);
                }
            });
    	}

        $('input[name="birthday"]').datepicker({
            dateFormat:'yy-mm-dd',
            changeYear:true,
            changeMonth:true,
            yearRange:"1920:2020"
        });

        /**
         * 判断account是否可以注册
         */
        function validateAccount(){
            var url = ctx+'/validateAccount';
            $.ajax({
                url:url,
                data:{
                    loginAccount:$('input[name="loginAccount"]').val()
                },
                type:'post',
                async:false,
                success:function(data){
                    if(data.success){
                        accountFlag = data.flag;
                    }
                },
                error:function(e){
                    console.log(e);
                }
            });
        }

        function isRegisterUserName(s) {
            var patrn=/^([a-zA-Z0-9]|[._@]){4,19}$/;
            if (!patrn.exec(s))
                return false;
            return true
        }
    </script>

</html>