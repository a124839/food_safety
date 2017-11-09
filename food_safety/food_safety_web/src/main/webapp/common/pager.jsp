<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="laypager"></div>
<input type="hidden" id="currsor" value="1">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/laypage/laypage.js"></script>

<script>
function loadPage(id,totalPages,callback){
	laypage.dir = '${pageContext.request.contextPath }/css/laypage/laypage.css';//自定义样式路径
	laypage({
	    cont: document.getElementById(id), //容器。值支持id名、原生dom对象，jquery对象,
	    pages: totalPages, //总页数
	    skin: '#1874CD', //加载内置皮肤，也可以直接赋值16进制颜色值，如:#c00
	    groups: 10, //连续显示分页数
	    curr: $("#currsor").val(), //当前页
	    jump:function(obj,first){
	    	$("#currsor").val(obj.curr);
	    	if(!first){
	    		callback();
	    	}
	    }
	});
}
</script>