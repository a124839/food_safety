$(function() {
	loadData();
});



function search() {
	$("#currsor").val(1);//将curror值设置为1，不然后台会做分页，导致查不到数据
	loadData();
}
function loadData() {
	var url = ctx + '/algorithm/query';
	var algorithmName = $("#algorithmName").val();
	var postData = {
		curr : $("#currsor").val()
	};
	if (algorithmName.length != 0) {
		postData.algorithmName = algorithmName
	}
	$.ajax({
		url : url,
		data : postData,
		type : "POST",
		success : function(data) {
			if (data.success == 1) {
				var content = '';
				var i = 1;
				$.each(data.list, function(index, o) {
					content += generateHTML(o,i);
					i++;
				});
				if (content.length == 0) {
					content += '<tr><td colspan="7" >暂无数据</td></tr>';
				}
				$("#algorithm_list").html(content);
				loadPage('laypager', data.totalPages, loadData);
			}
		},
		error : function(e) {
			console.log(e);
			setModal('错误', '查询项目列表失败！', null);
		}
	});
}

function generateHTML(o,i){
	var _html = '';
	_html += '<tr><td>'+i+'</td><td>'+ o.name+'</td><td>'+ (o.type==1?'预处理':'评价')+'</td><td>'+  (o.status==1?'正常':'停用')+'</td><td>'+ o.operator+'</td><td>'+ moment(o.ct).format('YYYY-MM-DD')+'</td><td>';
	if(judgeAuthorities('algorithmManageView')){
		_html += '<a href="javascript:showInfo(\''+ o.id+'\')">查看</a>&nbsp;';
	}
	if(o.category ==2 && judgeAuthorities('algorithmManageEdit')){
		_html+='<a href="javascript:toEditPage(\''+ o.id+'\')">修改</a>&nbsp;';
	}
	if(judgeAuthorities('algorithmManageStop')){
		_html+='<a href="javascript:showWarnning(\''+ o.id+'\')">停用</a>&nbsp;'
	}

    if(o.attachmentId != null && judgeAuthorities('algorithmManageDown')){
        _html += ' <a href="javascript:download(\''+ o.attachmentId+'\')">下载附件</a>';
    }

    _html +='</td></tr>';
	return _html;
}



var algorithmId = '';//用于修改和删除实验
function deleteAlgorithm() {
	console.log('delete:' + algorithmId);
	$.ajax({
		url : ctx + '/algorithm/delete',
		data : 'algorithmId=' + algorithmId,
		type : 'post',
		success : function(data) {
			if (data.success == 1) {
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
	setModal('警告', '确认停用此算法？', deleteAlgorithm);
	algorithmId = id;
}
/**
 * 显示编辑用户资料modal
 * @param id
 * @param name
 * @param roleName
 */
function editUser(id, name, roleName) {
	algorithmId = id;
	if (roleName == '管理员') {
		$("#editRoleName").attr('disabled', true);
	}
	$("#editPassword"	).val('');//清空之前的密码
	$("#editUserName").val(name);
	$("#editRoleName").val(roleName);
	$("#userEditModal").modal('show');
}
/**
 * 显示添加用户modal
 */
function showAddPage() {
	window.location.href = ctx + '/algorithm/add';
}


/**
 * 清除已显示的错误信息
 */
function clearErrorInfo(){
	$('div .has-error').removeClass('has-error');
	$("#errMsg").text('');
}


function showInfo(id){
	window.location.href =  ctx + '/algorithm/info?algorithmId='+id;
}


function toEditPage(id){
	window.location.href =  ctx + '/algorithm/edit?algorithmId='+id;
}

function download(id){
	window.location.href = ctx + '/attachment/download?attachmentId='+id+"&type=algorithm";
}