$(function(){
	$('select[name="categoryLv1"]').change(function(){
		queryTypes(this.value,0);
	});
	loadData();
});

function showAddPage(){
	window.location.href = ctx + '/type/toAddPage';
}

function search(){
	$('#currsor').val(1);
	loadData();
}
	
function loadData(){
	var url = ctx +'/type/queryType';
	var postData = {
        categoryLv1Id:$('select[name="categoryLv1"]').val(),
	categoryLv2Id:$('select[name="categoryLv2"]').val()
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
						content +='<tr><td>'+i+'</td><td>'+o.code+'</td><td>'+ (o.categoryLv1Name)+'</td><td>'+  (o.categoryLv2Name)+'</td><td>'+ (o.memo==null?'': o.memo)+'</td><td>'+ o.operator+'</td><td>'+moment(o.ct).format('YYYY-MM-DD HH:mm:ss')+'</td><td>';
						if(judgeAuthorities('sampleTypeLv2ManageEdit')){
							content += '<a href="javascript:toEditPage(\''+ o.id+'\')">修改</a>&nbsp;';
						}
						if(judgeAuthorities('sampleTypeLv2ManageDel')){
							content += '<a href="javascript:showWarnning(\''+ o.id+'\')">删除</a>';
						}
						content += '</td></tr>';
						i++;
					});
					if (content.length == 0) {
						content += '<tr><td colspan="12" >暂无数据</td></tr>';
					}
					$("#type_list").html(content);
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
		url : ctx + '/type/delete',
		data : 'typeId=' + typeId,
		type : 'post',
		success : function(data) {
			if (data.success) {
				loadData();
			} else {
//				setModal('错误', '此样品类型已经被使用，不能删除！', loadData);
				alert("此样品类型已经被使用，不能删除！");
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
	window.location.href = ctx + '/type/edit?id='+id;
}

function toAddPage(){
	window.location.href = ctx + '/type/toAddPage';
}

function toEditPage(id){
	window.location.href = ctx + '/type/toEditPage?id='+id;
}
