 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <aside class="main-sidebar">
 <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<script type="text/javascript">
    var authorities = '<%=request.getSession().getAttribute("authorities")%>';
    var authorityList = [];
    if(authorities.length > 0){
        authorityList = authorities.replace('[','').replace(']','').split(',');
    }

    function judgeAuthorities(permission){
        if(authorityList.length > 0){
            for(var i = 0;i<authorityList.length;i++){
                var authority = $.trim(authorityList[i]);
                if(permission ==  authority){
                    return true;
                }
            }
        }

        return false;
    }
</script>

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <ul class="sidebar-menu">
        <li class="header">MAIN NAVIGATION</li>
          <shiro:hasPermission name="projectManage">
             <li>
             <!--  -->
              <a href="${pageContext.request.contextPath}/project/toProjectManagePage">
                <i class="fa fa-flask"></i> <span>实验信息管理</span>
              </a>
            </li>
          </shiro:hasPermission>
         <li>
             <!--  -->
              <a href="${pageContext.request.contextPath}/msProject/list">
                <i class="fa fa-flask"></i> <span>多源实验管理</span>
              </a>
            </li>
        <shiro:hasPermission name="dataManage">
        <li>
          <a href="${pageContext.request.contextPath}/data/toDataManagePage">
            <i class="fa fa-database"></i> <span>原始数据管理</span>
          </a>
        </li>
        </shiro:hasPermission>
          <shiro:hasPermission name="analysisManage">
              <li>
                  <a href="${pageContext.request.contextPath}/analysis/toAnalysisManagePage">
                      <i class="fa fa-balance-scale"></i> <span>数据分析管理</span>
                  </a>
              </li>
          </shiro:hasPermission>
           <li >
               <a href="${pageContext.request.contextPath}/dataAnalysis/toDataAnalysisPage">
                   <i class="fa fa-object-group"></i> <span>数据建模处理</span>
               </a>
           </li>
          <%--<shiro:hasPermission name="reportManage">
              <li>
                  <a href="#">
                      <i class="fa fa-book"></i> <span>实验报告管理</span>
                  </a>
              </li>
          </shiro:hasPermission>--%>
         <shiro:hasPermission name="basicInformationManage">
       <li class="treeview ">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>基础信息管理</span> <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu menu-open" style="display: block">
              <shiro:hasPermission name="algorithmManage">
                  <li>
                      <a href="${pageContext.request.contextPath}/algorithm/toAlgorithmManagePage">
                          <i class="fa fa-pencil"></i> <span>算法管理</span>
                      </a>
                  </li>
              </shiro:hasPermission>
			  
                 
              
              <shiro:hasPermission name="sampleManage">
                  <li >
                      <a href="${pageContext.request.contextPath}/sample/toSamplePage">
                          <i class="fa fa-object-group"></i> <span>样品管理</span>
                      </a>
                  </li>
              </shiro:hasPermission>

              <shiro:hasPermission name="instrumentManage">
                  <li>
                      <a href="${pageContext.request.contextPath}/instrument/toInstrumentPage">
                          <i class="fa fa-cogs"></i> <span>设备管理</span>
                      </a>
                  </li>
              </shiro:hasPermission>


              <shiro:hasPermission name="standardsManage">
                  <li>
                      <a href="${pageContext.request.contextPath}/standards/toQueryPage">
                          <i class="fa fa-file-text-o"></i> <span>标准管理</span>
                      </a>
                  </li>
              </shiro:hasPermission>
            <shiro:hasPermission name="sampleTypeLv1Manage">
              <li>
                  <a href="${pageContext.request.contextPath}/firstType/toQueryPage">
                      <i class="fa fa-indent"></i> <span>样品类别管理</span>
                  </a>

              </li>
             </shiro:hasPermission>
             <shiro:hasPermission name="sampleTypeLv2Manage">
              <li>
                  <a href="${pageContext.request.contextPath}/type/toQueryPage">
                      <i class="fa fa-outdent"></i> <span>样品种类管理</span>
                  </a>
              </li>
             </shiro:hasPermission>
              <shiro:hasPermission name="serviceTypeManage">
                  <li>
                      <a href="${pageContext.request.contextPath}/serviceType/toQuery2Page">
                          <i class="fa fa-android"></i> <span>服务类型管理</span>
                      
                      </a>
                  </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="userManage">
                  <li>
                      <a href="${pageContext.request.contextPath}/user/toUserManagePage">
                          <i class="glyphicon glyphicon-user"></i> <span>用户管理</span>
                      </a>
                  </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="roleManage">
                  <li>
                      <a href="${pageContext.request.contextPath}/roleManage/toQueryPage">
                          <i class="fa fa-leaf"></i> <span>角色管理</span>
                      </a>
                  </li>
              </shiro:hasPermission>
          </ul>
        </li>
         </shiro:hasPermission>
     <%--   <li class="header">LABELS</li>
        <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>--%>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

        <script type="text/javascript">
        $(function(){
        	$('li.active').removeClass('active');
        	var href = window.location.pathname;
            var ary =href.split('/');
            var parent_path = ary[ary.length-2];
        	$('li>[href*="'+parent_path+'"]').parent().attr('class','active');
        })
		</script>
        
        