$(function() {
	loadData();
});



function search() {
	$("#currsor").val(1);//将currsor值设置为1，不然后台会做分页，导致查不到数据
	loadData();
}
function loadData() {
	var url = ctx + '/sample/querySample';
	var postData = {
		sampleName:$("#sampleName").val(),
		producingArea:$("#producingArea").val(),
		manufactor:$("#manufactor").val()
	};
	$.ajax({
		url : url,
		data : 'data='+$.toJSON(postData)+'&curr='+$("#currsor").val(),
		type : "POST",
		success : function(data) {
			if (data.success==1) {
				var content = '';
				var i = 1;
				$.each(data.list, function(index, o) {
					content += '<tr><td>'+i+'</td><td>'+o.code+'</td><td>' + o.name + '</td><td>'+ o.batches+'</td><td>'+ o.manufactor+'</td><td>'+o.producingArea+'</td><td>' +moment(o.productionDate).format('YYYY-MM-DD') + '</td><td>'
							+   (o.status==1?'正常':'已删除')
							+ '</td><td>'+ o.operator+'</td><td>';
					if(judgeAuthorities('sampleManageView')){
						content += '<a href="javascript:viewSample(\'' + o.id+'\')">查看</a>&nbsp;';
					}
					if(judgeAuthorities('sampleManageEdit')){
						content += '<a href="javascript:editSample(\'' + o.id+'\')">修改</a>&nbsp;';
					}

					if (true && judgeAuthorities('sampleManageDel')) {
						content += '<a href="javascript:showWarnning(\'' + o.id+ '\')"> 删除</a>';
					}
					content += '</td></tr>';
					i++;
				});
				if (content.length == 0) {
					content += '<tr><td colspan="11" >暂无数据</td></tr>';
				}
				$("#sample_list").html(content);
				loadPage('laypager', data.totalPages, loadData);
			}
		},
		error : function(e) {
			console.log(e);
			setModal('错误', '查询样品列表失败！', null);
		}
	});
}


var sampleId = '';//用于修改和删除实验
function deleteSample() {
	$.ajax({
		url : ctx + '/sample/delete',
		data : 'sampleId=' + sampleId,
		type : 'post',
		success : function(data) {
			if (data.success) {
				loadData();
			} else {
				setModal('错误', '删除失败！', loadData);
			}
		},
		error : function(e) {
			console.log(e);
		}
	});
}

function showWarnning(id) {
	setModal('警告', '确认删除此样品？', deleteSample);
	sampleId = id;
}
/**
 * 显示编辑用户资料modal
 * @param id
 * @param name
 * @param roleName
 */
function editSample(id) {
	window.location.href = ctx + '/sample/edit?id='+id;
}


function toAddPage(){
	window.location.href = ctx + '/sample/toAddPage';
}

function viewSample(id){
	window.location.href = ctx + '/sample/view?id='+id;
}

/**
 * 清除已显示的错误信息
 */
function clearErrorInfo(){
	$('div .has-error').removeClass('has-error');
	$("#errMsg").text('');
}