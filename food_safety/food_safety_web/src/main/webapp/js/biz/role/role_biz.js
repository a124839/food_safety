$(function() {
	loadData();
});


function search() {
	$("#currsor").val(1);//将curror值设置为1，不然后台会做分页，导致查不到数据
	loadData();
}


function loadData() {
	var url = ctx + '/roleManage/query';
	var roleName = $("#roleName").val();
	var postData = {
		curr : $("#currsor").val()
	};
	if (roleName != 0) {
		postData.roleName = roleName;
	}

	$.ajax({
		url : url,
		data : postData,
		type : "POST",
		success : function(data) {
			if (data.success==1) {
				var content = '';
				$.each(data.list, function(index, o) {
					content += '<tr><td>' + o.roleName + '</td><td>' + o.shiroName + '</td>' +
							'<td>' + moment(o.createTime).format('YYYY-MM-DD HH:mm:ss') + '</td><td>';
					if (o.shiroName != 'admin') {
						if(judgeAuthorities('roleManageEdit')){
							content += 	'<a href="javascript:editRole(\'' + o.id + '\',\'' + o.roleName+'\',\'' + o.shiroName+'\')">修改</a>&nbsp;&nbsp;';
						}
						if(judgeAuthorities('roleManageDel')){
							content += 	'<a href="javascript:showWarnning(\'' + o.id + '\')"> 删除</a>&nbsp;&nbsp;'
						}
						if(judgeAuthorities('roleManageAuth')){
							content += 	'<a href="javascript:roleFunction(\'' + o.id + '\')">授权</a>';
						}

					}
					content += '</td></tr>';
				});
				if (content.length == 0) {
					content += '<tr><td colspan="4" >暂无数据</td></tr>';
				}
				$("#role_list").html(content);
				loadPage('laypager', data.totalPages, loadData);
			}
		},
		error : function(e) {
			console.log(e);
			setModal('错误', '查询角色列表失败！', null);
		}
	});
}


var roleId = '';//用于修改和删除用户
var roleFlag = false;//判断role是否可以注册,true表示可以
function deleteRole() {
	console.log('delete:' + roleId);
	$.ajax({
		url : ctx + '/roleManage/delete',
		data : 'roleId=' + roleId,
		type : 'post',
		success : function(data) {
			if (data.success) {
				loadData();
			} else {
				setModal('错误', '删除失败！', loadData);
			}
		},
		error : function(e) {

		}
	});
}

function showWarnning(id) {
	console.log('删除角色');
	setModal('警告', '确认删除此角色？', deleteRole);
	roleId = id;
}

/**
 * 显示编辑角色资料modal
 * @param id
 * @param roleName
 */
function editRole(id, roleName, shiroName) {
	roleId = id;
	$("#oldRoleName").val(roleName);
	$("#editRoleName").val(roleName);
	$("#editShiroName").val(shiroName);
	$("#roleEditModal").modal('show');
}
/**
 * 更新角色信息
 */
function updateRole() {
	clearErrorInfo();

	var oldRoleName = $("#oldRoleName").val();
	var roleName =  $("#editRoleName").val();
	if(oldRoleName != roleName){
		validateRole('edit');
	}
	if(!roleFlag){
		$("#editRoleFormGroup").addClass('has-error');
		$("#editErrMsg").text('此角色已存在！');
		return ;
	}

	$("#roleEditModal").modal('hide');
	var url = ctx + '/roleManage/update';
	var postData = {
		roleName : roleName,
		shiroName : $("#editShiroName").val(),
		id : roleId
	};
	$.post(url, postData, function(data, state) {
		if (state == 'success' && data.success == 1) {
			setModal('信息', '修改成功！', loadData);
		} else {
			setModal('信息', '修改失败！', loadData);
		}
	});
}

/**
 * 显示添加角色modal
 */
function showAddModal() {
	//show之前先把之前的内容清空
	$("#addUserName").val('');
	clearErrorInfo();
	//清空完了就显示吧
	$("#roleAddModal").modal("show");
}
/**
 * 添加角色
 */
function addRole(){
	clearErrorInfo();
	var url = ctx+'/roleManage/add';
	var roleName = $("#addRoleName").val();
	if(roleName.length ==0){
		$("#roleFormGroup").addClass('has-error');
		$("#errMsg").text('角色名称为必填项！');
		return ;
	}
	validateRole('add');
	if(!roleFlag){
		$("#roleFormGroup").addClass('has-error');
		$("#errMsg").text('此角色已存在！');
		return ;
	}

	var shiroName = $("#addShiroName").val();
	if(shiroName.length ==0){
		$("#roleFormGroup").addClass('has-error');
		$("#errMsg").text('角色英文名称为必填项！');
		return ;
	}

	var postData = {
			roleName : roleName,
			shiroName : shiroName
	};
	$.post(url,postData,function(data,state){
		$("#roleAddModal").modal("hide");
		$("#currsor").val(1);
		if (state == 'success' && data.success == 1) {
			setModal('信息', '添加成功！', loadData);
		} else {
			setModal('信息', '添加失败！', loadData);
		}
	})
}


/**
 * 判断Role是否可以添加
 */
function validateRole(type){
	var url = ctx+'/roleManage/validateRole';
	var roleName = type=='add'?$('#addRoleName').val():$('#editRoleName').val();
	$.ajax({
		url:url,
		data:'roleName='+ roleName,
		type:'post',
		async:false,
		success:function(data){
			if(data.success){
				roleFlag = data.flag;
			}
		},
		error:function(e){
			console.log(e);
		}
	});
}


/**
 * 清除已显示的错误信息
 */
function clearErrorInfo(){
	$('div .has-error').removeClass('has-error');
	$("#errMsg").text('');
	$("#editErrMsg").text('');
}

/**
 * 角色授权
 * @param roleId
 */
function roleFunction(roleId){
	window.location.href = ctx + '/roleFunctionManage/toAuthorizationPage/' + roleId;
}