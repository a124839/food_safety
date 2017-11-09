$(function(){
	
	loadData();
});

function showAddPage(){
	window.location.href = ctx + '/firstType/toAddPage';
}

function search(){
	$('#currsor').val(1);
	loadData();
}
	
function loadData(){
	var url = ctx +'/firstType/firstType';
	var postData = {
			curr:$("#currsor").val(),
			id:$('select[name="firstType"]').val()
			
	    };
	$.ajax({
		url : url,
		data : postData,
		type : "POST",
		success : function(data) {
			if (data.success==1) {
				var content = '';
				var i = 1;
				$.each(data.list, function(index, o) {
					content +='<tr><td>'+i+'</td><td>'+ (o.name)+'</td><td>'+ (o.memo==null?'': o.memo)+'</td><td>'+ o.operator+'</td><td>'+moment(o.ct).format('YYYY-MM-DD HH:mm:ss')+'</td><td>';
					if(judgeAuthorities('sampleTypeLv1ManageEdit')){
						content += '<a href="javascript:toEditPage(\''+ o.id+'\')">修改</a>&nbsp;';
					}
					if(judgeAuthorities('sampleTypeLv1ManageDel')){
						content += '<a href="javascript:showWarnning(\''+ o.id+'\')">删除</a>';
					}

					content += '</td></tr>';
					i++;
				});
				if (content.length == 0) {
					content += '<tr><td colspan="11" >暂无数据</td></tr>';
				}
				$("#firsttype_list").html(content);
				loadPage('laypager', data.totalPages, loadData);
			}
		},
		error : function(e) {
			console.log(e);
			setModal('错误', '查询样品列表失败！', null);
		}
	});
}


var typeId = '';
function deleteType() {
	$.ajax({
		url : ctx + '/firstType/delete',
		data : 'typeId=' + typeId,
		type : 'post',
		success : function(data) {
			if (data.success==1) {
				window.location.href = ctx +'/firstType/toQueryPage';
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
	setModal('警告', '确认删除此类型？', deleteType);
	typeId = id;
}
function editType(id) {
	window.location.href = ctx + '/serviceType/edit?id='+id;
}

function toAddPage(){
	window.location.href = ctx + '/firstType/toAddPage';
}


function toEditPage(id){
	window.location.href = ctx + '/firstType/toEditPage?id='+id;
}

