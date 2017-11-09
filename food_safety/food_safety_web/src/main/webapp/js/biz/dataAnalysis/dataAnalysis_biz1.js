/**
 * 2017年6月30日10:54:21 @CL
 * 
 */

$(function(){
	loadAlgorithmData();
	loadSampleData();
	var flag1 = loadAlgorithmData();
	var flag2 = loadSampleData();
	alert(flag1);
	//判断算法是否被点中是则显示细节
	//为radio点击绑定事件
	if(flag1){
//		alert($("input[name='alogrithmSelect']"+'选取radio==').val());
		alert($(":radio").val()+'选取radio');
		alert($("input[name=alogrithmSelect]:checked").val());
	}
	alert('已经执行完了？？');
	
	
});

var dataIds = [];//已选中的分析数据的checkbox的值

function loadAlgorithmData() {
	var url = ctx + '/algorithm/query';
	var algorithmName = $("#algorithmName").val();
	var postData = {
		curr : $("#algorithmCurrsor").val()
	};

	$.ajax({
		url : url,
		data : postData,
		type : "POST",
		success : function(data) {
			if (data.success == 1) {
				var content = '';
				var i = 1;
				$.each(data.list, function(index, o) {
					content += '<tr><td>'+i+'</td><td>'+ o.name+'</td><td>'+ judgeType(o,i)+'</td><td>'+  (o.status==1?'正常':'停用')+'</td><td>'+ o.operator+'</td><td>'+ moment(o.ct).format('YYYY-MM-DD')+'</td><td>';
					if(i ==1){
						content += '<input type="radio"  value="'+o.id+'" name="alogrithmSelect" /></td>';
					}else{
						content += '<input type="radio"  value="'+o.id+'" name="alogrithmSelect"/></td>';
					}
//					content += '<input type="radio"  value="'+o.id+'" name="alogrithmSelect" /></td>';
					//提交按钮
					content += '<td><button class="btn btn-primary"  name="confirmAlogritmBtn" id="'+o.id+'">确定使用此算法</button>';
					content += reSelect() + '</td></tr>';
					//测试					
					i++;
				});
				if (content.length == 0) {
					content += '<tr><td colspan="7" >暂无数据</td></tr>';
				}
				$("#algorithm_list").html(content);
				bindRadioClick();
				//用来做分页
				loadPage('laypager', data.totalPages, loadAlgorithmData);
			}
		},
		error : function(e) {
			console.log(e);
			setModal('错误', '查询项目列表失败！', null);
		}
	});
	return true;
}

//为算法按钮绑定事件
function bindChoseAlogrithm(){
	
	$("button[name='confirmAlogritmBtn']").click(function(){
		//1.传入此算法的id
		id = $(this).attr('id');
		//2.如果此算法id与对应的算法id相等则显示此算法需要输入的参数
		
		//2.1如果为PDS算法
		
		//2.2如果为SST算法
		//2.3如果为PLSCV算法
		//2.4如果为KHLSSVM算法
		//3.如果不相等则抛出异常表示没有此算法
	});
}

//为处理数据按钮绑定事件
$("#handleData").click(function(){
	//提交表单数据
	//包括算法的名称
	//选择的数据
	//填写的参数
	//返回到一个数据展示页面
});

//pds算法选择
function pds(){
	//为选择的数据按钮绑定选择数据事件
	
}


//为radio点击绑定事件--radio
function bindRadioClick(){
	if($("input[name='alogrithmSelect']:checked").val() != null){
		$(":radio").change(function(){
			showDetials();
		});
	}
}

//显示算法细节--radio
function showDetials(){
	var alogrithmId = $("input[name='alogrithmSelect']:checked").val();
	alert(alogrithmId);
	if(alogrithmId != null){
		//显示隐藏的div
		$('#showAlogrithmDetails').toggle();
//		$('#showAlogrithmDetails').attr('class','col-sm-12');		 
//		showInfo(alogrithmId);
	}else{
		alert('unchecked error');
	}
}

//获取算法详情--radio
function showInfo(id){
	window.location.href =  ctx + '/algorithm/info?algorithmId='+id;
}

//判断算法类型
function judgeType(o,i){

	var str = '';
	if(o.type == 1){
		str = '预处理';
	}
	if(o.type == 2){
		str = '评价';
	}
	if(o.type == 3){
		str = '建模';
	}
	if(o.type == 4){
		str = '模型转移';
	}
	return str;
}

//直接从data.js中搬过来的可能要改
//此方法对应alogrithmController
//TODO
function toImportPage(){
	if(dataIds.length == 0){
		$('#errMsg2').html('请选择要处理的数据文件');
		return false;
	}
	$('#dataIds').val($.toJSON(dataIds));
	$('#dataForm2').submit();
}

//载入数据
function loadSampleData() {

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
					//后添加--2017年7月5日14:53:35
					content += '<tr><td><input type="checkbox" value="'+ o.id+'"';
					if(dataIds.indexOf(o.id)!= -1){
						content += ' checked = "checked">';
					}else{
						isAllChecked = false;
					}
					content+= '"<tr><td>'+i+'</td><td>' + o.projectName + '</td><td>'
						+ o.intrumentName+ '</td><td>' + o.sampleName + '</td><td>'+ o.resolution+'</td><td>'
							+ o.wavelengthRange+'</td><td>'+ o.scanningTimes+'</td><td>'+ o.scanningDuration
								+'</td><td>'+ o.fileName+'</td><td>'+o.uploader+'</td><td>'+ moment(o.createTime).format('YYYY-MM-DD HH:mm:ss')+ '</td><td>' ;										
					content += '</td></tr>';
					i++;
				});
				
				
				if (content.length == 0) {
					content += '<tr><td colspan="12" >暂无数据</td></tr>';
				}
				$("#data_list").html(content);
				
//                //bindDataListClickEvent();
				if(isAllChecked){//设置全选checkbox的选中状态
					document.getElementById('all').checked = true;
				}else{
					$('#all').attr('checked',isAllChecked);
				}
				//有东西了，才能绑定事件
				bindClickEvent();
//				loadPage2('laypager2', data.totalPages, queryData);
				loadPage('laypager2', data.totalPages, loadSampleData);
			}
			
		},
		error : function(e) {
			console.log(e);
			setModal('错误', '查询数据列表失败！', null);
		}
	});
	return true;
}

//直接从data.js中拿过来的
/**
 * 绑定每条数据checkbox的点击事件
 */
function bindClickEvent(){
	$('#data_list input:checkbox').click(function(){
		if($(this).is(':checked')){
			dataIds.push(this.value);
		}else{
			dataIds.remove(this.value);
		}
	});
}
//取消按钮，逻辑可能要改
function reSelect(o){
	return '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" class="btn btn-primary" value="取消">'
}

//直接拿过来的
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