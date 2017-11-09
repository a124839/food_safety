$(function() {
	$( "#progressbar" ).progressbar({
		value: 37
	});
    $('input[type!="hidden"]').blur(function(){
        clearErrorInfo();
    });
	initSample();
	loadData();
	$( "input[name='scanningDuration']" ).datetimepicker({
		dateFormat:'yy-mm-dd',
		timeFormat:'HH:mm:ss'
	});
});

var dataIds = [];//已选中的分析数据的checkbox的值
//var dataIds2 = [];//已选中的要导出数据的checkbox的值

function search() {
	$("#currsor").val(1);//将curror值设置为1，不然后台会做分页，导致查不到数据
	loadData();
}

function loadData() {
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

/**
 * 显示导入文件modal
 */
function showAddModal() {
	clearErrorInfo();
	//清空完了就显示吧
	$("#fileAddModal").modal("show");
}

function showAlgorithmModal(){
	$('#params').html('');
    //初始化算法选择控件
	initAlgorithm(1);
	queryData();
	$('#selectAlgorithm').modal('show');
}

/**
 * 上传数据
 */
function submitForm(){
    clearErrorInfo();
    if(!validateForm()){
        return;
    }
	$('#saveBtn').addClass('disabled');
    $('#saveBtn').attr('disabled',true);

    var url = ctx+'/data/upload';
	$("#dataForm").ajaxSubmit({
			url:url,
			success:function(data){
				$("#fileAddModal").modal("hide");
				$('#saveBtn').removeClass('disabled');
                $('#saveBtn').attr('disabled',false);
                if(data.success == 1){
					if(data.errorFiles.length == 0){
						setModal('信息', '上传成功！', loadData);
					}else{
						alert("文件未找到对应样品："+data.errorFiles);
					}
				}
			},
			resetForm:false
		});
}


function deleteData(){
	
	var url = ctx+'/data/del';
	$.ajax({
		url:url,
		data:'id='+$("#datasId").val(),
		type:"post",
		success:function(data){
			loadData();
//			if(	data.success == 1){
//				
//			}else{
//				setModal('错误', '删除失败，请重试！', loadData);
//			}
		},
		error:function(e){
			console.log(e);
		}

	});
}

function showDeleteModel(id){
	$("#datasId").val(id);
	setModal('警告', '确定删除此数据？', deleteData);
}


function viewData(id){
	window.location.href = ctx+'/data/toDatasViewPage?id='+id;
}
/**
 * 初始化样品输入框
 */
function initSample(){
	var availableTags = [];
	$.ajax({
		url:ctx+'/sample/queryAll',
		type:'post',
		success:function(o){
			$.each(o.samples,function(index,item){
				var $o={
						label:item.code,
						val:item.id
				};
				availableTags.push($o);
			});
			$( "#samplesName" ).autocomplete({
				minLength: 3,
				autoFocus: true,
				source: availableTags,
				select: function( event, ui ) {
					$("#samplesName").attr('data-value',ui.item.val)
				}
			});
		},
		error:function(e){
			
		}
		
	});
	
}



function searchData() {
	$("#currsor2").val(1);//将curror值设置为1，不然后台会做分页，导致查不到数据
	queryData();
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


/*$('#checkAll').click(function(){
    var allFlag = $('#checkAll').is(':checked');
    $('#data_list input:checkbox').each(function(){
        this.checked = allFlag;
        if(allFlag){
            if(dataIds2.indexOf(this.value) == -1){
                dataIds2.push(this.value);
            }
        }else{
            dataIds2.remove(this.value);
        }
    });
});*/
/**
 * 绑定数据列表每条数据checkbox的点击事件
 */
/*function bindDataListClickEvent(){
    $('#data_list input:checkbox').click(function(){
        if($(this).is(':checked')){
            dataIds2.push(this.value);
        }else{
            dataIds2.remove(this.value);
        }
    });
}*/
/**
 * 分析数据
 */
function analysisData(){
    if($('#algorithm').val() == 0){
        $('#errMsg2').html('请选择处理数据的算法');
        $('#algorithm').focus();
        return false;
    }
    var paramsValue = true;
    $('input[name="paramsValue"]').each(function(){
        if(this.value.length ==0){
            $('#errMsg2').html('请输入参数值');
            $(this).parent('div').addClass('has-error');
            $(this).focus();
            paramsValue = false;
            return false;
        }
        paramsValue = true;
    });

    if(!paramsValue){
        return false;
    }
    if(dataIds.length == 0){
        $('#errMsg2').html('请选择要处理的数据文件');
        return false;
    }

	var algorithmParams = [];
	$('#params .form-group').each(function(index,elem){
		var paramsName = $(elem).find('label[name="paramsName"]').html();
		var paramsValue = $(elem).find('input[name="paramsValue"]').val();
		var params = {
			name:paramsName,
			value:paramsValue
		};

		if(paramsName.length >0 && paramsValue.length >0){
			algorithmParams.push(params);
		}
	});
	var algorithmInfo = {
		id:$('#algorithm').val(),
		algorithmName:$('#algorithm option:selected').text(),
		algorithmParams:algorithmParams
	};
	var params = {
		dataIds:dataIds,
		algorithmInfo:algorithmInfo
	};
	//window.location.href = ctx + '/analysis/analysisData?params='+ $.toJSON(params);
	$('#analysisParams').val($.toJSON(params));
	$('#handleButton').addClass("disabled");
    $('#handleButton').attr('disabled',true);
	// $('#selectAlgorithm').modal('hide');
	// $("#pp").modal("show");
	var url = ctx + '/analysis/analysisData';
	// if(dataIds.length >2){
	// 	url = ctx + '/analysis/asyncAnalysisData';
	// 	$('#analysisForm').attr('action',url);
	// 	$('#analysisForm').ajaxSubmit({
	// 		url:url,
	// 		success:function(res){
	// 			if(res.success == 1){
	// 				$('#selectAlgorithm').modal('hide');
	// 				setModal('提示','已提交服务器，计算完成后会通知您。',null);
	// 			}
	// 		}
	// 	});
    //
	// }else{
	// 	$('#analysisForm').attr('action',url);
	// 	$('#analysisForm').submit();//此处采用post的方式提交 防止参数过长
	// }
	$('#analysisForm').attr('action',url);
	$('#analysisForm').submit();//此处采用post的方式提交 防止参数过长
}

function exportData(){
	var postData = {
		projectName:$('#projectName').val(),
		instrumentId:$('#instrumentName').val(),
		samplesId:$('#samplesName').attr('data-value')
	};
    window.location.href = ctx + '/data/batchDownload?data='+$.toJSON(postData);
}


function validateForm(){
    var text = '';

    if($.trim($('input[name="resolution"]').val()).length == 0){
        text = '请输入分辨率';
        setError($('input[name="resolution"]'),text);
        return false;
    }

    if($.trim($('input[name="wavelengthRange"]').val()).length == 0){
        text = '请输入波长范围';
        setError($('input[name="wavelengthRange"]'),text);
        return false;
    }
    if($.trim($('input[name="scanningTimes"]').val()).length == 0){
        text = '请输入扫描次数';
        setError($('input[name="scanningTimes"]'),text);
        return false;
    }
	
	if(!isNum($.trim($('input[name="scanningTimes"]').val()))){
		text = '请输入整数';
		setError($('input[name="scanningTimes"]'),text);
		return false;
	}
	
    if($.trim($('input[name="scanningDuration"]').val()).length == 0){
        text = '请输入扫描时间';
        setError($('input[name="scanningDuration"]'),text);

        return false;
    }

    if($('input[name="file"]').val().length == 0){
        text = '请选择文件';
        setError($('input[name="file"]'),text);
        return false;
    }
	/*var filePath = $('input[name="file"]').val();
	var dotIndex = filePath.lastIndexOf(".");
	var type = filePath.substring(filePath.lastIndexOf(".")+1,filePath.length);
	if(dotIndex != -1 && type.toLowerCase() != "csv"){
		$("#errMsg").text('上传文件格式错误！请上传正确格式的文件');
		return false;
	}*/

    return true;
}


function toImportPage(){
	if(dataIds.length == 0){
		$('#errMsg2').html('请选择要处理的数据文件');
		return false;
	}
	$('#dataIds').val($.toJSON(dataIds));
	$('#dataForm2').submit();
}