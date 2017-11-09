$(function() {
	loadData();
});



function search() {
	$("#currsor").val(1);//将curror值设置为1，不然后台会做分页，导致查不到数据
	loadData();
}
function loadData() {
	var url = ctx + '/project/queryProject';
	var projectName = $("#projectName").val();
	var postData = {
		curr : $("#currsor").val()
	};
	if (projectName.length != 0) {
		postData.projectName = projectName
	}
	$.ajax({
		url : url,
		data : postData,
		type : "POST",
		success : function(data) {
			if (data.success==1) {
				var content = '';
				var i = 1;
				$.each(data.list, function(index, o) {
					content += '<tr><td>'+i+'</td><td>' + o.name + '</td><td>'+ moment(o.startDate).format('YYYY-MM-DD')+'</td><td>'+ moment(o.endDate).format('YYYY-MM-DD')+'</td><td>' +(o.status==1?'准备中':o.status==2?'实验中':'实验结束') + '</td><td>'+ o.operator+'</td><td>'
							+ moment(o.ct).format('YYYY-MM-DD HH:mm:ss')
							+ '</td><td>';
					if(judgeAuthorities('projectManageView')){
						content += '<a href="javascript:viewProject(\'' + o.id + '\')">查看</a>&nbsp;'
					}
					if (o.status == 1) {
						if(judgeAuthorities('projectManageEdit')){
							content += '<a href="javascript:editProject(\'' + o.id + '\')">修改</a>'
						}
						if(judgeAuthorities('projectManageStart')){
							content += '<a href="javascript:startProject(\'' + o.id+ '\')"> 开始实验</a>';
						}
						if(judgeAuthorities('projectManageDel')){
							content += '<a href="javascript:showWarnning(\'' + o.id+ '\')"> 删除</a>';
						}
					}
					if (o.status == 2) {
						if(judgeAuthorities('projectManageEdit')){
							content += '<a href="javascript:editProject(\'' + o.id + '\')">修改</a>'
						}
						if(judgeAuthorities('projectManageEnd')){
							content += '<a href="javascript:finishProject(\'' + o.id+ '\')"> 结束实验</a>';
						}
					}
					if(o.status == 3){
						if(judgeAuthorities('projectManageReport')){
							content += '<a href="javascript:report(\'' + o.id + '\')">生成实验报告</a>'
						}
					}
					content += '</td></tr>';
					i++;
				});
				if (content.length == 0) {
					content += '<tr><td colspan="8" >暂无数据</td></tr>';
				}
				$("#project_list").html(content);
				loadPage('laypager', data.totalPages, loadData);
			}
		},
		error : function(e) {
			console.log(e);
			setModal('错误', '查询项目列表失败！', null);
		}
	});
}
var projectId = '';//用于修改和删除实验
function deleteProject() {
	console.log('delete:' + projectId);
	$.ajax({
		url : ctx + '/project/delete',
		data : 'id=' + projectId,
		type : 'post',
		success : function(data) {
			if (data.success) {
				loadData();
			} else {
				setModal('错误', '删除失败！', loadData);
			}
		},
		error : function(e) {
            console.log('error'+e.status);
		}
	});
}


function startProject(id) {
    console.log('start project ---'+id);
    $.ajax({
        url : ctx + '/project/start',
        data : {id:id},
        type : 'post',
        success : function(data) {
            if (data.success) {
                loadData();
            } else {
                setModal('错误', '开始实验失败！', loadData);
            }
        },
        error : function(e) {
            console.log('error'+e.status);
        }
    });
}

function finishProject(id) {
    console.log('finish project ---'+id);
    $.ajax({
        url : ctx + '/project/finish',
        data : {id:id},
        type : 'post',
        success : function(data) {
            if (data.success) {
                loadData();
            } else {
                setModal('错误', '结束实验失败！', loadData);
            }
        },
        error : function(e) {
            console.log('error'+e.status);
        }
    });
}

function showWarnning(id) {
	projectId = id;
	setModal('警告', '确认删除此实验？', deleteProject);
}


function toAddPage(){
	window.location.href = ctx +'/project/toAddPage';
}


function editProject(id){
	window.location.href = ctx +'/project/edit?id='+id;
}

function viewProject(id){
	window.location.href = ctx +'/project/view?id='+id;
}

function report(id){
	window.location.href = ctx +'/project/report?id='+id;
}
/**
 * 清除已显示的错误信息
 */
function clearErrorInfo(){
	$('div .has-error').removeClass('has-error');
	$("#errMsg").text('');
}