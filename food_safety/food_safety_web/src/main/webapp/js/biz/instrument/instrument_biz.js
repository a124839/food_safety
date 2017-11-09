$(function() {
	loadData();
});



function search() {
	$("#currsor").val(1);//将curror值设置为1，不然后台会做分页，导致查不到数据
	loadData();
}
function loadData() {
	var url = ctx + '/instrument/queryInstrument';
	var postData = {
		curr : $("#currsor").val(),
		instrumentName:$("#instrumentName").val(),
		model:$("#model").val(),
		manufactor:$("#manufactor").val(),
		category:$('#category').val()
	};
	$.ajax({
		url : url,
		data :postData,
		type : "POST",
		success : function(data) {
			if (data.success==1) {
				var content = '';
				var i = 1;
				$.each(data.list, function(index, o) {
					content += '<tr><td>'+i+'</td><td>' + o.name + '</td><td>'+o.code+'</td><td>'+ (o.sn==null?'': o.sn)+'</td><td>'+ o.type+'</td><td>'+ setCategory(o.category)+'</td><td>'+o.model+'</td><<td>' +o.manufactor+ '</td><td>'+ o.operator+'</td><td>'
							+  (o.status==1?'正常':'停用');
					if(judgeAuthorities('instrumentManageView')){
						content +=  '</td><td><a href="javascript:view(\'' + o.id+ '\')"> 查看</a>&nbsp;';
					}
					if(judgeAuthorities('instrumentManageEdit')){
						content += '<a href="javascript:toEditPage(\'' + o.id+'\')">修改</a>';
					}
                    if(o.attachmentId != null && o.attachmentId != '' && judgeAuthorities('instrumentManageDownload')){
                        content += '<a href="javascript:download(\'' + o.attachmentId+ '\')"> 下载附件</a>';
                    }
					if(judgeAuthorities('instrumentManageDel')){
						content += '<a href="javascript:showWarnning(\'' + o.id+ '\')"> 删除</a>';
					}
					if(judgeAuthorities('instrumentManageServer')){
						content += '<a href="javascript:toInstrumentServiceListPage(\'' + o.id+ '\')"> 服务记录</a>';
					}
					content += '</td></tr>';
					i++;
				});
				if (content.length == 0) {
					content += '<tr><td colspan="11" >暂无数据</td></tr>';
				}
				$("#instrument_list").html(content);
				loadPage('laypager', data.totalPages, loadData);
			}
		},
		error : function(e) {
			console.log(e);
			setModal('错误', '查询设备列表失败！', null);
		}
	});
}


var instrumentId = '';//用于修改和删除实验
function deleteInstrument() {
	$.ajax({
		url : ctx + '/instrument/delete',
		data : 'instrumentId=' + instrumentId,
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
	instrumentId = id;
	setModal('警告', '确认删除此设备？', deleteInstrument);
}

function toAddPage(){
	window.location.href = ctx + '/instrument/toAddPage';
}

function setCategory(category){

	switch (category){
		case '1':
			return '平台设备';
		case '2':
			return '辅助设备';
		default:
			return '平台设备';
	}
}




function toEditPage(id){
	window.location.href = ctx + '/instrument/toEditPage?id='+id;
}

function toInstrumentServiceListPage(id){
	window.location.href = ctx + '/instrumentServiceRecord/toInstrumnetServicePage?id='+id;
}

function download(attachmentId){
	window.location.href = ctx + '/attachment/download?attachmentId='+attachmentId+"&type=instrument";
}

function view(id){
	window.location.href = ctx + '/instrument/view?id='+id;
}