$(function() {
	loadData();
});



function search() {
	$("#currsor").val(1);//将curror值设置为1，不然后台会做分页，导致查不到数据
	loadData();
}
function loadData() {
	var url = ctx + '/user/query';
	var roleId = $("#roleName").val();
	var userName = $("#userName").val();
	var postData = {
		curr : $("#currsor").val()
	};
	if (roleId != 0) {
		postData.roleId = roleId;
	}
	if (userName.length != 0) {
		postData.userName = userName
	}
    postData.status = $('select[name="status"]').val();
	$.ajax({
		url : url,
		data : postData,
		type : "POST",
		success : function(data) {
			if (data.success==1) {
				//createTable(data.data);
				var content = '';
				var i = 1;
				$.each(data.list, function(index, o) {
					content += '<tr><td>'+i+'</td><td>' + o.loginAccount + '</td><td>'
							+ (o.userName == null ? '' : o.userName)
							+ '</td><td>'+(o.birthday==null?'':moment(o.birthday).format('YYYY-MM-DD'))+'</td><td>'+ o.contact+'</td><td>' + o.roleName + '</td><td>'+ o.stuCode+'</td><td>'+ o.graduatedSchool+'</td><td>'+ o.researchDirection+'</td><td>'+ o.tutor+'</td><td>'+getStatus(o.status)+'</td><td>'+ moment(o.createTime).format('YYYY-MM-DD HH:mm:ss')+ '</td><td>';
					if(judgeAuthorities('userManageEdit') && o.status == 1){
						content += '<a href="javascript:editUser(\'' + o.id + '\',\'' + o.userName + '\',\'' + o.roleId+'\',\''+o.loginAccount + '\',\''+ o.contact+'\')">修改</a>';
					}
					if(o.status == 2){
						if(judgeAuthorities('approvalUsers')){
							content += '&nbsp;<a href="javascript:showV(\''+o.id+'\','+1+')">通过</a>';
							content += '&nbsp;<a href="javascript:showV(\''+o.id+'\','+3+')">不通过</a>';
						}
					}
					if (o.loginAccount != 'admin' && judgeAuthorities('userManageDel') && o.status ==1 ) {
						content += '<a href="javascript:showWarnning(\'' + o.id + '\')"> 禁用</a>';
					}
					content += '</td></tr>';
					i++;
				});
				if (content.length == 0) {
					content += '<tr><td colspan="13" >暂无数据</td></tr>';
				}
				$("#user_list").html(content);
				loadPage('laypager', data.totalPages, loadData);
			}
		},
		error : function(e) {
			console.log(e);
			setModal('错误', '查询用户列表失败！', null);
		}
	});
}
var userId = '';//用于修改和删除用户
var accountFlag = false;//判断account是否可以注册,true表示可以
function deleteUser() {
	console.log('delete:' + userId);
	$.ajax({
		url : ctx + '/user/delete',
		data : 'userId=' + userId,
		type : 'post',
		success : function(data) {
			if (data.success) {
				loadData();
			} else {
				setModal('错误', '禁用失败！', loadData);
			}
		},
		error : function(e) {

		}
	});
}

function showWarnning(id) {
	console.log('删除用户');
	setModal('警告', '确认禁用此用户？', deleteUser);
	userId = id;
}
/**
 * 显示编辑用户资料modal
 * @param id
 * @param name
 * @param roleName
 */
function editUser(id, name, roleId,loginAccount,contact) {
	userId = id;
	if (loginAccount == 'admin') {
		$("#editRoleName").attr('disabled', true);
	}else{
		$("#editRoleName").attr('disabled', false);
	}
	$("#editPassword"	).val('');//清空之前的密码
	$("#editUserName").val(name);
	$("#editRoleName").val(roleId);
	$('#editContact').val(contact);
	$("#userEditModal").modal('show');
}
/**
 * 更新用户信息
 */
function updateUser() {
	//$("#userEditModal").modal('hide');
	var url = ctx + '/user/update';
	var postData = {
		userName : $("#editUserName").val(),
		roleId : $("#editRoleName").val(),
		contact:$('#editContact').val(),
		id : userId
	};
	var password = $("#editPassword").val();
	if (password != null && password.length > 0) {
		postData.password = password;
	}
	$.post(url, postData, function(data, state) {
		if (state == 'success' && data.success == 1) {
			setModal('信息', '修改成功！', loadData);
		} else {
			setModal('信息', '修改失败！', loadData);
		}
	});
}
/**
 * 显示添加用户modal
 */
function showAddModal() {
	//show之前先把之前的内容清空
	$('#addLoginAccount').val('');
	$("#addPassword").val('');
	$("#addUserName").val('');
	clearErrorInfo();
	//清空完了就显示吧
	$("#userAddModal").modal("show");
}
/**
 * 添加用户
 */
function addUser(){
	clearErrorInfo();
	var url = ctx+'/user/add';
	var loginAccount = $('#addLoginAccount').val();
	var password = $("#addPassword").val();
	var userName = $("#addUserName").val();
	if(loginAccount.length ==0){
		$("#loginAccountFormGroup").addClass('has-error');
		$("#errMsg").text('账号为必填项！');
		return ;
	}
	validateAccount();
	if(!accountFlag){
		$("#loginAccountFormGroup").addClass('has-error');
		$("#errMsg").text('此账号已存在！');
		return ;
	}
	if(password.length ==0){
		$("#passwordFormGroup").addClass('has-error');
		$("#errMsg").text('密码为必填项！');
		return ;
	}
	if(userName.length ==0){
		$("#userNameFormGroup").addClass('has-error');
		$("#errMsg").text('名字为必填项！');
		return ;
	}
	
	var postData = {
			loginAccount:loginAccount,
			password:password,
			userName:userName,
			roleId:$('#addRoleNameS').val()
	};
	$.post(url,postData,function(data,state){
		$("#userAddModal").modal("hide");
		$("#currsor").val(1);
		if (state == 'success' && data.success == 1) {

			setModal('信息', '添加成功！', loadData);
		} else {
			setModal('信息', '添加失败！', loadData);
		}
	})
}


/**
 * 判断account是否可以注册
 */
function validateAccount(){
	var url = ctx+'/user/validateAccount';
	$.ajax({
		url:url,
		data:'loginAccount='+$('#addLoginAccount').val(),
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


/**
 * 清除已显示的错误信息
 */
function clearErrorInfo(){
	$('div .has-error').removeClass('has-error');
	$("#errMsg").text('');
}
var uid = '';
var status = -1;
function showV(id,s){
    uid = id;
    status = s;
    setModal('提示','确定审核此用户？',verify);

}

function verify(){
    var url = ctx + '/user/verify';
    $.ajax({
        url:url,
        data:{
            uid:uid,
            status:status
        },
        type:'post',
        success:function(res){
            if(res.success == 1){
				setVerifyResult('提示','审核成功！',loadData);
            }else{
				setVerifyResult('提示','审核失败！',loadData);
            }
        },
        error:function(e){

        }
    })
}

function getStatus(status){
    var content = '';
    switch (status){
        case 1:
            content = "正常";
            break;
        case 2:
            content = '待审核';
            break;
        case 3:
            content = '审核不通过';
            break;
       
    }
    return content;
}

function setVerifyResult(title,body,callback){

	$("#verify_resultModalLabel").text(title);
	$("#verify_resultModalBody").html(body);
	$('#verify_result').modal('show');
	//绑定之前先移除
	$('#verify_resultModalConfirm').unbind();
	$("#verify_resultModalConfirm").click(function(){
		if(callback != null){
			callback();
		}
		//$('#myModal').modal('hide');
	});
}