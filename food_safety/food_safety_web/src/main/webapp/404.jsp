<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" session="false"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
 <head>
        <meta charset="UTF-8">
        <title>出问题啦！</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
       <%@include file="/common/common.jsp" %>
    </head>
<body class="skin-blue">
       <%--  <%@include file="/common/top.jsp" %> --%>
       
         <!-- Main content -->
                <section class="content">
                 
                    <div class="error-page">
                        <h2 class="headline text-info"> 404</h2>
                        <div class="error-content">
                            <h3><i class="fa fa-warning text-yellow"></i> 页面不知道哪去了！</h3>
                            <p>
                                请求的路径不存在
                                您可以<a href='${pageContext.request.contextPath }/index'>返回首页</a>，或者联系管理员
                            </p>
                        </div><!-- /.error-content -->
                    </div><!-- /.error-page -->

                </section><!-- /.content -->
    </body>
</html>