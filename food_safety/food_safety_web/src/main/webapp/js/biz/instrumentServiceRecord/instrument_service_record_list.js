$(function() {
	loadData();
});



function search() {
	$("#currsor").val(1);//将curror值设置为1，不然后台会做分页，导致查不到数据
	loadData();
}
function loadData() {
	var url = ctx + '/instrumentServiceRecord/queryServiceRecord';
	var postData = {
		curr : $("#currsor").val(),
		typeId:$('#type').val(),
		instrumentId:instrumentId

	};
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
						content +='<tr><td>'+i+'</td><td>'+ o.code+'</td><td>'+ o.instrumentName+'</td><td>'+ o.type+'</td><td>'+  moment(o.serviceDate).format('YYYY-MM-DD HH:mm:ss')+'</td><td>'+(o.memo==null?'': o.memo)+'</td><td>'+ o.operator+'</td><td>';
						
						content += '<a href="javascript:showWarnning(\'' + o.id+'\')">删除</a>&nbsp;';
						content += '<a href="javascript:toEditPage(\'' + o.id+'\')">修改</a>';
						content += '</td></tr>';
						i++;
				});
				if (content.length == 0) {
					content += '<tr><td colspan="8" >暂无数据</td></tr>';
				}
				$("#serviceRecord_list").html(content);
				loadPage('laypager', data.totalPages, loadData);
			}
		},
		error : function(e) {
			console.log(e);
			setModal('错误', '查询服务列表失败！', null);
		}
	});
}
var recordId = -1;
function deleteInstrumentServiceRecord() {
	console.log('delete:' + recordId);
	$.ajax({
		url : ctx + '/instrumentServiceRecord/delete',
		data : {id:recordId},
		type : 'post',
		success : function(data) {
			if (data.success==1) {
				loadData();
			} else {
				setModal('错误', '删除失败！', loadData);
			}
		},
		error : function(e) {
			console.log(e)
		}
	});
}

function showWarnning(id) {
	setModal('警告', '确认删除此记录？', deleteInstrumentServiceRecord);
	recordId = id;
}

/**
 * 更新用户信息
 */
function updateInstrumentServiceRecord() {
	$("#serviceRecordEditModal").modal('hide');
	var url = ctx + '/instrumentServiceRecord/update';
	var postData = {
		userName : $("#editUserName").val(),
		roleName : $("#editRoleName").val(),
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
//			setModal('信息', '修改失败！', loadData);
			alert("修改失败！");
			loadData();
		}
	});
}
/**
 * 添加服务记录
 */
function addInstrumentServiceRecord(){
	clearErrorInfo();
	var url = ctx+'/instrumentServiceRecord/instrumentServiceRecord_add';
	var TypeId = $("TypeId").val();

	/*if(InstrumentId.length ==0){
		$("#InstrumentIdFormGroup").addClass('has-error');
		$("#errMsg").text('编号为必填项！');
		return ;
	}*/
	/*if(serviceType.length ==0){
		$("#serviceTypeFormGroup").addClass('has-error');
		$("#errMsg").text('服务类型为必填项！');
		return ;
	}*/
	
	var postData = {
			instrumentName:instrumentName,
			instrumentId:instrumentId,
			serviceType:serviceType,
	};
	$.post(url,postData,function(data,state){
		$("#serviceRecordAddModal").modal("hide");
		$("#currsor").val(1);
		if (state == 'success' && data.success == 1) {
			setModal('信息', '添加成功！', loadData);
		} else {
			//setModal('信息', '添加失败！', loadData);
			alert('添加失败');
		}
	})
}


/**
 * 清除已显示的错误信息
 */
function clearErrorInfo(){
	$('div .has-error').removeClass('has-error');
	$("#errMsg").text('');
}


function toAddPage(){
	window.location.href = ctx +'/instrumentServiceRecord/toAddPage?id='+instrumentId;
}

function toEditPage(id){
    window.location.href = ctx +'/instrumentServiceRecord/toEditPage?id='+id;
}