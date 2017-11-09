/**
 * 2017年6月28日11:19:31 @CL
 * dataAnalysis.js
 */

$(function(){
	loadAlgorithmData();
	loadSampleData();
});

function loadAlgorithmData() {
	var url = ctx + '/algorithm/query';
	var algorithmName = $("#algorithmName").val();
	var postData = {
		curr : $("#algorithmCurrsor").val()
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

function loadSampleData() {
	if($('#samplesName').val().length == 0){
		$('#samplesName').attr('data-value','');
	}
	var url = ctx + '/data/query';
	var postData = {
			curr : $("#currsor").val(),
			projectName:$('#projectName').val(),
			instrumentId:$('#instrumentName').val(),
			samplesId:$('#samplesName').attr('data-value')
		};
	
	$.ajax({
		url : url,
		data : postData,
		type : "POST",
		success : function(data) {
			if (data.success==1) {
				var isAllChecked = true;//翻页时判断是否全部被选中
				//createTable(data.data);
				var i = 1;
				var content = '';
				$.each(data.list, function(index, o) {
					content+= '"<tr><td>'+i+'</td><td>' + o.projectName + '</td><td>'+ o.intrumentName+ '</td><td>' + o.sampleName + '</td><td>'+ o.resolution+'</td><td>'+ o.wavelengthRange+'</td><td>'+ o.scanningTimes+'</td><td>'+ o.scanningDuration+'</td><td>'+ o.fileName+'</td><td>'+o.uploader+'</td><td>'+ moment(o.createTime).format('YYYY-MM-DD HH:mm:ss')+ '</td><td>' ;
					if(judgeAuthorities('dataManageView')){
						content += '<a href="javascript:viewData(\''+ o.id+'\')">查看</a>&nbsp;';
					}
					if(judgeAuthorities('dataManageDel')){
						content += '<a href="javascript:showDeleteModel(\''+ o.id+'\')">删除</a>';
					}
					content += '</td></tr>';
					i++;
				});

				if (content.length == 0) {
					content += '<tr><td colspan="12" >暂无数据</td></tr>';
				}
				$("#data_list").html(content);
				loadPage('laypager', data.totalPages, loadData);
                //bindDataListClickEvent();
			}
		},
		error : function(e) {
			console.log(e);
			setModal('错误', '查询数据列表失败！', null);
		}
	});
}

function showAlgorithmModal(){
	$('#params').html('');
    //初始化算法选择控件
	initAlgorithm(1);
	queryData();
	$('#showAlgorithm').modal('show');
}

/**
 * 查询样品数据
 */
function queryData(){

	var url = ctx + '/data/query';
	var postData = {
		curr : $("#currsor2").val(),
		projectName:$('#projectName2').val(),
		pageSize:10
	};
	$.ajax({
		url : url,
		data : postData,
		type : "POST",
		success : function(data) {
			if (data.success==1) {
				var isAllChecked = true;//翻页时判断是否全部被选中
				var content = '';
				$.each(data.list, function(index, o) {
					content += '<tr><td><input type="checkbox" value="'+ o.id+'"';
					if(dataIds.indexOf(o.id)!= -1){
						content += ' checked = "checked">';
					}else{
						isAllChecked = false;
					}
					content += '</td><td>' + o.projectName + '</td><td>'+ o.intrumentName+ '</td><td>' + o.sampleName + '</td><td>'+ moment(o.createTime).format('YYYY-MM-DD HH:mm:ss')+ '</td></tr>';
				});
				if (content.length == 0) {
					content += '<tr><td colspan="4" >暂无数据</td></tr>';
				}
				$("#datas").html(content);
				if(isAllChecked){//设置全选checkbox的选中状态
					document.getElementById('all').checked = true;
				}else{
					$('#all').attr('checked',isAllChecked);
				}
				//有东西了，才能绑定事件
				bindClickEvent();
				loadPage2('laypager2', data.totalPages, queryData);
			}
		},
		error : function(e) {
			console.log(e);
			setModal('错误', '查询数据列表失败！', null);
		}
	});
}

$('#all').click(function(){
	var allFlag = $('#all').is(':checked');
	$('#datas input:checkbox').each(function(){
		this.checked = allFlag;
		if(allFlag){
			if(dataIds.indexOf(this.value) == -1){
				dataIds.push(this.value);
			}
		}else{
			dataIds.remove(this.value);
		}
	});
});
/**
 * 绑定每条数据checkbox的点击事件
 */
function bindClickEvent(){
	$('#datas input:checkbox').click(function(){
		if($(this).is(':checked')){
			dataIds.push(this.value);
		}else{
			dataIds.remove(this.value);
		}
	});
}