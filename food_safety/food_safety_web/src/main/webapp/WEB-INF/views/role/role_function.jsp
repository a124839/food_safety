<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<title>角色授权管理</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<%@include file="/common/common.jsp"%>
<!-- DATA TABLES -->
<link
	href="${pageContext.request.contextPath }/AdminLTE/plugins/datatables/dataTables.bootstrap.css"
	rel="stylesheet" type="text/css" />
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@include file="/common/top.jsp" %>
  <%@include file ="/common/left.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
    <!-- Content Header (Page header) -->
    <section class="content-header">
    <h1>
        角色管理
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active"><a href="#">角色授权管理</a></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
            </div>
            <!-- /.box-header -->
            <form class="form-horizontal"  id="form">
            <div class="box-body">
              <div class="form-group">
                <div class="col-sm-2">
                  <button type="button" class="btn btn-primary pull-right" onclick="history.go(-1);">返回</button>
                </div>
                <div class="col-sm-2">
                  <button type="button" class="btn btn-primary " onclick="saveRoleFuction();">保存</button>
                </div>
              </div>
              <table id="example2" class="table table-bordered table-hover text-center ">
                <input id="id" name="id" value="${roleFunc.id}" type="hidden"/>
                <tr>
                  <th style="width:150px;" >
                    <input class="xiao_01" onclick="checks(1, this);" type="checkbox" name="authorities" value="projectManage" <c:if test="${fn:contains(roleFunc.authorities, 'projectManage')}"> checked="checked"</c:if>>试验信息管理:
                  </th>
                  <td style="text-align: left; width: 500px;">
                    <input class="xiao_01_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="projectManageAdd" <c:if test="${fn:contains(roleFunc.authorities, 'projectManageAdd')}"> checked="checked"</c:if>>添加&nbsp;&nbsp;
                    <input class="xiao_01_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="projectManageView" <c:if test="${fn:contains(roleFunc.authorities, 'projectManageView')}"> checked="checked"</c:if>>查看&nbsp;&nbsp;
                    <input class="xiao_01_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="projectManageEdit" <c:if test="${fn:contains(roleFunc.authorities, 'projectManageEdit')}"> checked="checked"</c:if>>编辑&nbsp;&nbsp;
                    <input class="xiao_01_01_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="projectManageDel" <c:if test="${fn:contains(roleFunc.authorities, 'projectManageDel')}"> checked="checked"</c:if>>删除&nbsp;&nbsp;
                    <input class="xiao_01_01_05" onclick="checks(3, this);" type="checkbox" name="authorities" value="projectManageStart" <c:if test="${fn:contains(roleFunc.authorities, 'projectManageStart')}"> checked="checked"</c:if>>开始试验&nbsp;&nbsp;
                    <input class="xiao_01_01_06" onclick="checks(3, this);" type="checkbox" name="authorities" value="projectManageEnd" <c:if test="${fn:contains(roleFunc.authorities, 'projectManageEnd')}"> checked="checked"</c:if>>结束试验&nbsp;&nbsp;
                    <input class="xiao_01_01_07" onclick="checks(3, this);" type="checkbox" name="authorities" value="projectManageReport" <c:if test="${fn:contains(roleFunc.authorities, 'projectManageReport')}"> checked="checked"</c:if>>生成实验报告
                  </td>
                  <td></td>
                </tr>

                <tr>
                  <th style="width:150px;">
                  <input class="xiao_02" onclick="checks(1, this);" type="checkbox" name="authorities" value="dataManage" <c:if test="${fn:contains(roleFunc.authorities, 'dataManage')}"> checked="checked"</c:if>>原始数据管理:
                  </th>
                  <td style="text-align: left;">
                    <input class="xiao_02_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="dataManageImport" <c:if test="${fn:contains(roleFunc.authorities, 'dataManageImport')}"> checked="checked"</c:if>>导入数据&nbsp;&nbsp;
                    <input class="xiao_02_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="dataManagePreprocess" <c:if test="${fn:contains(roleFunc.authorities, 'dataManagePreprocess')}"> checked="checked"</c:if>>预处理&nbsp;&nbsp;
                    <input class="xiao_02_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="dataManageExport" <c:if test="${fn:contains(roleFunc.authorities, 'dataManageExport')}"> checked="checked"</c:if>>导出&nbsp;&nbsp;
                    <input class="xiao_02_01_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="dataManageView" <c:if test="${fn:contains(roleFunc.authorities, 'dataManageView')}"> checked="checked"</c:if>>查看&nbsp;&nbsp;
                    <input class="xiao_02_01_05" onclick="checks(3, this);" type="checkbox" name="authorities" value="dataManageDel" <c:if test="${fn:contains(roleFunc.authorities, 'dataManageDel')}"> checked="checked"</c:if>>删除&nbsp;&nbsp;
                  </td>
                  <td></td>
                </tr>

              <tr>
                <th>
                <input class="xiao_03" onclick="checks(1, this);" type="checkbox" name="authorities" value="analysisManage" <c:if test="${fn:contains(roleFunc.authorities, 'analysisManage')}"> checked="checked"</c:if>>数据分析管理:
                </th>
                <td style="text-align: left;">
                  <input class="xiao_03_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="analysisManageView" <c:if test="${fn:contains(roleFunc.authorities, 'analysisManageView')}"> checked="checked"</c:if>>查看&nbsp;&nbsp;
                  <input class="xiao_03_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="analysisManageComment" <c:if test="${fn:contains(roleFunc.authorities, 'analysisManageComment')}"> checked="checked"</c:if>>评价&nbsp;&nbsp;
                </td>
                <td></td>
              </tr>

              <tr>
                <th>
                  <input class="xiao_04" onclick="checks(1, this);" type="checkbox" name="authorities" value="reportManage" <c:if test="${fn:contains(roleFunc.authorities, 'reportManage')}"> checked="checked"</c:if>>试验报告管理:
                </th>
                <td></td>
                <td></td>
              </tr>

              <tr>
                <th rowspan="9" >
                  <input class="xiao_05" onclick="checks(1, this);" type="checkbox" name="authorities" value="basicInformationManage" <c:if test="${fn:contains(roleFunc.authorities, 'basicInformationManage')}"> checked="checked"</c:if>>基础信息管理:
                </th>
                <td style="text-align: left;">
                  <input id="xiao_05_01" class="xiao_05_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="algorithmManage" <c:if test="${fn:contains(roleFunc.authorities, 'algorithmManage')}"> checked="checked"</c:if>>算法管理
                </td>
                <td style="text-align: left;">
                  <input class="xiao_05_01_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="algorithmManageAdd" <c:if test="${fn:contains(roleFunc.authorities, 'algorithmManageAdd')}"> checked="checked"</c:if>>添加&nbsp;&nbsp;
                  <input class="xiao_05_01_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="algorithmManageView" <c:if test="${fn:contains(roleFunc.authorities, 'algorithmManageView')}"> checked="checked"</c:if>>查看&nbsp;&nbsp;
                  <input class="xiao_05_01_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="algorithmManageEdit" <c:if test="${fn:contains(roleFunc.authorities, 'algorithmManageEdit')}"> checked="checked"</c:if>>修改&nbsp;&nbsp;
                  <input class="xiao_05_01_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="algorithmManageStop" <c:if test="${fn:contains(roleFunc.authorities, 'algorithmManageStop')}"> checked="checked"</c:if>>停用&nbsp;&nbsp;
                  <input class="xiao_05_01_05" onclick="checks(3, this);" type="checkbox" name="authorities" value="algorithmManageDown" <c:if test="${fn:contains(roleFunc.authorities, 'algorithmManageDown')}"> checked="checked"</c:if>>下载附件&nbsp;&nbsp;
                  <!--
                  <input class="xiao_05_01_01" onclick="checks(2, this);" type="checkbox" name="authorities" value="algorithmManageFileUp" <c:if test="${fn:contains(roleFunc.authorities, 'algorithmManageFileUp')}"> checked="checked"</c:if>>上传附件&nbsp;&nbsp;
                  -->
                </td>
              </tr>
              <tr>
                <td style="text-align: left;">
                  <input id="xiao_05_02" class="xiao_05_02" onclick="checks(2, this);" type="checkbox" name="authorities" value="sampleManage" <c:if test="${fn:contains(roleFunc.authorities, 'sampleManage')}"> checked="checked"</c:if>>样品管理
                </td>
                <td style="text-align: left;">
                  <input class="xiao_05_02_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="sampleManageAdd" <c:if test="${fn:contains(roleFunc.authorities, 'sampleManageAdd')}"> checked="checked"</c:if>>添加&nbsp;&nbsp;
                  <input class="xiao_05_02_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="sampleManageView" <c:if test="${fn:contains(roleFunc.authorities, 'sampleManageView')}"> checked="checked"</c:if>>查看&nbsp;&nbsp;
                  <input class="xiao_05_02_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="sampleManageEdit" <c:if test="${fn:contains(roleFunc.authorities, 'sampleManageEdit')}"> checked="checked"</c:if>>修改&nbsp;&nbsp;
                  <input class="xiao_05_02_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="sampleManageDel" <c:if test="${fn:contains(roleFunc.authorities, 'sampleManageDel')}"> checked="checked"</c:if>>删除&nbsp;&nbsp;
                </td>
              </tr>
              <tr>
                <td style="text-align: left;">
                  <input id="xiao_05_03" class="xiao_05_03" onclick="checks(2, this);" type="checkbox" name="authorities" value="instrumentManage" <c:if test="${fn:contains(roleFunc.authorities, 'instrumentManage')}"> checked="checked"</c:if>>设备管理
                </td>
                <td style="text-align: left;">
                  <input class="xiao_05_03_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="instrumentManageAdd" <c:if test="${fn:contains(roleFunc.authorities, 'instrumentManageAdd')}"> checked="checked"</c:if>>添加&nbsp;&nbsp;
                  <input class="xiao_05_03_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="instrumentManageView" <c:if test="${fn:contains(roleFunc.authorities, 'instrumentManageView')}"> checked="checked"</c:if>>查看&nbsp;&nbsp;
                  <input class="xiao_05_03_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="instrumentManageEdit" <c:if test="${fn:contains(roleFunc.authorities, 'instrumentManageEdit')}"> checked="checked"</c:if>>修改&nbsp;&nbsp;
                  <input class="xiao_05_03_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="instrumentManageDel" <c:if test="${fn:contains(roleFunc.authorities, 'instrumentManageDel')}"> checked="checked"</c:if>>删除&nbsp;&nbsp;
                  <input class="xiao_05_03_05" onclick="checks(3, this);" type="checkbox" name="authorities" value="instrumentManageDownload" <c:if test="${fn:contains(roleFunc.authorities, 'instrumentManageDownload')}"> checked="checked"</c:if>>下载附件&nbsp;&nbsp;
                  <input class="xiao_05_03_06" onclick="checks(3, this);" type="checkbox" name="authorities" value="instrumentManageServer" <c:if test="${fn:contains(roleFunc.authorities, 'instrumentManageServer')}"> checked="checked"</c:if>>服务记录&nbsp;&nbsp;
                </td>
              </tr>
              <tr>
                <td style="text-align: left;">
                  <input id="xiao_05_04" class="xiao_05_04" onclick="checks(2, this);" type="checkbox" name="authorities" value="standardsManage" <c:if test="${fn:contains(roleFunc.authorities, 'standardsManage')}"> checked="checked"</c:if>>标准管理
                </td>
                <td style="text-align: left;">
                  <input class="xiao_05_04_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="standardsManageAdd" <c:if test="${fn:contains(roleFunc.authorities, 'standardsManageAdd')}"> checked="checked"</c:if>>添加&nbsp;&nbsp;
                  <input class="xiao_05_04_02" onclick="checks(3, this);" type="checkbox" name="authorities" value="standardsManageView" <c:if test="${fn:contains(roleFunc.authorities, 'standardsManageView')}"> checked="checked"</c:if>>查看&nbsp;&nbsp;
                  <input class="xiao_05_04_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="standardsManageEdit" <c:if test="${fn:contains(roleFunc.authorities, 'standardsManageEdit')}"> checked="checked"</c:if>>修改&nbsp;&nbsp;
                  <input class="xiao_05_04_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="standardsManageDel" <c:if test="${fn:contains(roleFunc.authorities, 'standardsManageDel')}"> checked="checked"</c:if>>删除&nbsp;&nbsp;
                  <input class="xiao_05_04_05" onclick="checks(3, this);" type="checkbox" name="authorities" value="standardsManageDownload" <c:if test="${fn:contains(roleFunc.authorities, 'standardsManageDownload')}"> checked="checked"</c:if>>下载附件&nbsp;&nbsp;
                </td>
              </tr>
              <tr>
                <td style="text-align: left;">
                  <input id="xiao_05_05" class="xiao_05_05" onclick="checks(2, this);" type="checkbox" name="authorities" value="sampleTypeLv1Manage" <c:if test="${fn:contains(roleFunc.authorities, 'sampleTypeLv1Manage')}"> checked="checked"</c:if>>样品类别管理
                </td>
                <td style="text-align: left;">
                  <input class="xiao_05_05_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="sampleTypeLv1ManageAdd" <c:if test="${fn:contains(roleFunc.authorities, 'sampleTypeLv1ManageAdd')}"> checked="checked"</c:if>>添加&nbsp;&nbsp;
                  <input class="xiao_05_05_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="sampleTypeLv1ManageEdit" <c:if test="${fn:contains(roleFunc.authorities, 'sampleTypeLv1ManageEdit')}"> checked="checked"</c:if>>修改&nbsp;&nbsp;
                  <input class="xiao_05_05_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="sampleTypeLv1ManageDel" <c:if test="${fn:contains(roleFunc.authorities, 'sampleTypeLv1ManageDel')}"> checked="checked"</c:if>>删除&nbsp;&nbsp;
                </td>
              </tr>
                <tr>
                  <td style="text-align: left;">
                    <input id="xiao_05_06" class="xiao_05_06" onclick="checks(2, this);" type="checkbox" name="authorities" value="sampleTypeLv2Manage" <c:if test="${fn:contains(roleFunc.authorities, 'sampleTypeLv2Manage')}"> checked="checked"</c:if>>样品种类管理
                  </td>
                  <td style="text-align: left;">
                    <input class="xiao_05_06_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="sampleTypeLv2ManageAdd" <c:if test="${fn:contains(roleFunc.authorities, 'sampleTypeLv2ManageAdd')}"> checked="checked"</c:if>>添加&nbsp;&nbsp;
                    <input class="xiao_05_06_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="sampleTypeLv2ManageEdit" <c:if test="${fn:contains(roleFunc.authorities, 'sampleTypeLv2ManageEdit')}"> checked="checked"</c:if>>修改&nbsp;&nbsp;
                    <input class="xiao_05_06_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="sampleTypeLv2ManageDel" <c:if test="${fn:contains(roleFunc.authorities, 'sampleTypeLv2ManageDel')}"> checked="checked"</c:if>>删除&nbsp;&nbsp;
                  </td>
                </tr>
              <tr>
                <td style="text-align: left;">
                  <input id="xiao_05_07" class="xiao_05_07" onclick="checks(2, this);" type="checkbox" name="authorities" value="serviceTypeManage" <c:if test="${fn:contains(roleFunc.authorities, 'serviceTypeManage')}"> checked="checked"</c:if>>服务类型管理
                </td>
                <td style="text-align: left;">
                  <input class="xiao_05_07_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="serviceTypeManageAdd" <c:if test="${fn:contains(roleFunc.authorities, 'serviceTypeManageAdd')}"> checked="checked"</c:if>>添加&nbsp;&nbsp;
                  <input class="xiao_05_07_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="serviceTypeManageEdit" <c:if test="${fn:contains(roleFunc.authorities, 'serviceTypeManageEdit')}"> checked="checked"</c:if>>修改&nbsp;&nbsp;
                  <input class="xiao_05_07_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="serviceTypeManageDel" <c:if test="${fn:contains(roleFunc.authorities, 'serviceTypeManageDel')}"> checked="checked"</c:if>>删除&nbsp;&nbsp;
                </td>
              </tr>
              <tr>
                <td style="text-align: left;">
                  <input id="xiao_05_08" class="xiao_05_08" onclick="checks(2, this);" type="checkbox" name="authorities" value="userManage" <c:if test="${fn:contains(roleFunc.authorities, 'userManage')}"> checked="checked"</c:if>>用户管理
                </td>
                <td style="text-align: left;">
                  <input class="xiao_05_08_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="userManageAdd" <c:if test="${fn:contains(roleFunc.authorities, 'userManageAdd')}"> checked="checked"</c:if>>添加&nbsp;&nbsp;
                  <input class="xiao_05_08_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="userManageEdit" <c:if test="${fn:contains(roleFunc.authorities, 'userManageEdit')}"> checked="checked"</c:if>>修改&nbsp;&nbsp;
                  <input class="xiao_05_08_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="userManageDel" <c:if test="${fn:contains(roleFunc.authorities, 'userManageDel')}"> checked="checked"</c:if>>删除&nbsp;&nbsp;
                  <input class="xiao_05_08_05" onclick="checks(3, this);" type="checkbox" name="authorities" value="approvalUsers" <c:if test="${fn:contains(roleFunc.authorities, 'approvalUsers')}"> checked="checked"</c:if>>审批用户
                </td>
              </tr>
              <tr>
                <td style="text-align: left;">
                  <input id="xiao_05_09" class="xiao_05_09" onclick="checks(2, this);" type="checkbox" name="authorities" value="roleManage" <c:if test="${fn:contains(roleFunc.authorities, 'roleManage')}"> checked="checked"</c:if>>角色管理
                </td>
                <td style="text-align: left;">
                  <input class="xiao_05_09_01" onclick="checks(3, this);" type="checkbox" name="authorities" value="roleManageAdd" <c:if test="${fn:contains(roleFunc.authorities, 'roleManageAdd')}"> checked="checked"</c:if>>添加&nbsp;&nbsp;
                  <input class="xiao_05_09_03" onclick="checks(3, this);" type="checkbox" name="authorities" value="roleManageEdit" <c:if test="${fn:contains(roleFunc.authorities, 'roleManageEdit')}"> checked="checked"</c:if>>修改&nbsp;&nbsp;
                  <input class="xiao_05_09_04" onclick="checks(3, this);" type="checkbox" name="authorities" value="roleManageDel" <c:if test="${fn:contains(roleFunc.authorities, 'roleManageDel')}"> checked="checked"</c:if>>删除&nbsp;&nbsp;
                  <input class="xiao_05_09_05" onclick="checks(3, this);" type="checkbox" name="authorities" value="roleManageAuth" <c:if test="${fn:contains(roleFunc.authorities, 'roleManageAuth')}"> checked="checked"</c:if>>授权&nbsp;&nbsp;
                </td>
              </tr>
                </table>

              </div>
            <!-- /.box-body -->
            </form>
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
<%@include file="/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

	<!-- js时间库 -->
	<script	src="${pageContext.request.contextPath }/js/moment/moment-with-locales.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath }/js/biz/role/role_function.js?ver=${ver}" type="text/javascript"></script>

</body>
</html>