<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" session="false"
    pageEncoding="UTF-8"%>
    <%response.setStatus(HttpServletResponse.SC_OK);%>
<!DOCTYPE html>
<html>
 <head>
        <meta charset="UTF-8">
        <title>出问题啦！</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
       <%@include file="/common/common.jsp" %>
        
    </head>
<body class="skin-blue">
       <section class="content">
                 
                    <div class="error-page">
                        <h2 class="headline">500</h2>
                        <div class="error-content">
                            <h3><i class="fa fa-warning text-yellow"></i> 额...好像出了点问题</h3>
                            <p>
                                我们会尽快解决，
                                你可以先 <a href='${pageContext.request.contextPath}/index'>回到首页</a>，或者联系管理员
                        </div>
                    </div><!-- /.error-page -->

                </section><!-- /.content -->
    </body>
</html>