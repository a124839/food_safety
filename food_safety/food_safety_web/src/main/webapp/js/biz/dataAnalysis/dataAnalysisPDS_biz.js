/**
 * 2017年7月12日09:04:00 CL
 */
$(function(){
	loadSampleData();
	loadSampleDataXs();
	loadSampleDataXc();
	
	
});
//
var dataIds = [];//已选中的分析数据的checkbox的值
var xmdataIds = [];//xm选中的分析数据的checkbox的值
var xsdataIds = [];
var xcdataIds = [];
//载入数据
function loadSampleData() {
	var url = ctx + '/data/query';
	if( $("#currsor").val() == null){
		var currsor = $("#currsor").val(1);
	}
	var postData = {		
			curr : $("#currsor").val()					
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
					if(xmdataIds.indexOf(o.id)!= -1){
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
				
                
				if(isAllChecked){//设置全选checkbox的选中状态
					document.getElementById('xm_all').checked = true;
				}else{
					$('#xm_all').attr('checked',isAllChecked);
				}
				//有东西了，才能绑定事件
				
//				bindClickEvent();
//				bindClick();
				bindClickEventXm();
				loadPage('laypager', data.totalPages, loadSampleData);
			}
			
		},
		error : function(e) {
			console.log(e);
			setModal('错误', '查询数据列表失败！', null);
		}
	});
	return true;
}

	//笨方法之xs数据显示
function loadSampleDataXs() {
	var url = ctx + '/data/query';
	if( $("#currsor1").val() == null){
		var currsor = $("#currsor1").val(1);
	}
	var postData = {		
			curr : $("#currsor1").val()					
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
					if(xsdataIds.indexOf(o.id)!= -1){
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
				$("#xs_data_list").html(content);
				
                
				if(isAllChecked){//设置全选checkbox的选中状态
					document.getElementById('xs_all').checked = true;
				}else{
					$('#xs_all').attr('checked',isAllChecked);
				}
				//有东西了，才能绑定事件
				
//				bindClickEvent();
//				bindClick();
				bindClickEventXs();
				loadPage('laypager1', data.totalPages, loadSampleDataXs);
			}
			
		},
		error : function(e) {
			console.log(e);
			setModal('错误', '查询数据列表失败！', null);
		}
	});
	return true;
}

//本方法之xc
//笨方法之xs数据显示
function loadSampleDataXc() {
	var url = ctx + '/data/query';
	if( $("#currsor2").val() == null){
		var currsor = $("#currsor2").val(1);
	}
	var postData = {		
			curr : $("#currsor2").val()					
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
					if(xcdataIds.indexOf(o.id)!= -1){
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
				$("#xc_data_list").html(content);
				
                
				if(isAllChecked){//设置全选checkbox的选中状态
					document.getElementById('xc_all').checked = true;
				}else{
					$('#xc_all').attr('checked',isAllChecked);
				}
				//有东西了，才能绑定事件
				
//				bindClickEvent();
//				bindClick();
				bindClickEventXc();
				loadPage('laypager2', data.totalPages, loadSampleDataXc);
			}
			
		},
		error : function(e) {
			console.log(e);
			setModal('错误', '查询数据列表失败！', null);
		}
	});
	return true;
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

//思路2--笨方法
//给每一个button绑定一个事件
$('#xm_all').click(function(){
	var allFlag = $('#xm_all').is(':checked');
	$('#data_list input:checkbox').each(function(){
		this.checked = allFlag;
		if(allFlag){
			if(xmdataIds.indexOf(this.value) == -1){
				xmdataIds.push(this.value);
			}
		}else{
			xmdataIds.remove(this.value);
		}
	});
});

$('#xs_all').click(function(){
	var allFlag = $('#xs_all').is(':checked');
	$('#xs_data_list input:checkbox').each(function(){
		this.checked = allFlag;
		if(allFlag){
			if(xsdataIds.indexOf(this.value) == -1){
				xsdataIds.push(this.value);
			}
		}else{
			xsdataIds.remove(this.value);
		}
	});
});

$('#xc_all').click(function(){
	var allFlag = $('#xc_all').is(':checked');
	$('#xc_data_list input:checkbox').each(function(){
		this.checked = allFlag;
		if(allFlag){
			if(xcdataIds.indexOf(this.value) == -1){
				xcdataIds.push(this.value);
			}
		}else{
			xcdataIds.remove(this.value);
		}
	});
});


/**
 * 绑定每条数据checkbox的点击事件
 */
function bindClickEventXm(){
	$('#data_list input:checkbox').click(function(){
		if($(this).is(':checked')){
			xmdataIds.push(this.value);
		}else{
			xmdataIds.remove(this.value);
		}
	});
}

function bindClickEventXs(){
	$('#xs_data_list input:checkbox').click(function(){
		if($(this).is(':checked')){
			xsdataIds.push(this.value);
		}else{
			xsdataIds.remove(this.value);
		}
	});
}

function bindClickEventXc(){
	$('#xc_data_list input:checkbox').click(function(){
		if($(this).is(':checked')){
			xcdataIds.push(this.value);
		}else{
			xcdataIds.remove(this.value);
		}
	});
}

function showXmModal(){
	loadSampleData();
	$('#selectXmDatas').modal('show');
}

function showXsModal(){
	loadSampleDataXs();
	$('#selectXsDatas').modal('show');
}

function showXcModal(){
	loadSampleDataXc();
	$('#selectXcDatas').modal('show');
}
//结束

//取消按钮
function reset(){
	modal.find()
}

function analysisPDSData(){
	//这里要改写，如果输入的参数不全要补全参数
	//需要一个验证，验证是参数是否输入完全，是否都选择了数据
//	if($('#algorithm').val() == 0){
//        $('#errMsg2').html('请选择处理数据的算法');
//        $('#algorithm').focus();
//        return false;
//    }
	
    var paramsValue = true;
    //
    $('input[name="paramsValue"]').each(function(){
    	//判断参数值是否为空
        if(this.value.length ==0){
            $('#errMsg2').html('请输入参数值');
            $(this).parent('div').addClass('has-error');
            $(this).focus();
            paramsValue = false;
            return false;
        }
        //不为空则置为true
        paramsValue = true;
    });
    
    
    if(!paramsValue){
        return false;
    }
    alert('判断参数值是否为空');
    
    //如果没选择数据返回错误
    if( xmdataIds ==0 || xsdataIds == 0 || xcdataIds == 0){
        $('#errMsg2').html('请给每一个参数选择要处理的数据文件');
        return false;
    }
    
//	var algorithmParams = [];
//	//取到每一个参数找到参数名，参数值，打包成josn数据
//	$('#params').each(function(index,elem){
//		//这个改成自己取的参数
////		alert($("input[name='paramsValue']").attr("id"));
////		var paramsName = $(elem).find('label[name="paramsName"]').html();
//		var paramsName = $(elem).find("input[name='paramsValue']").attr("id");
//		var paramsValue = $(elem).find('input[name="paramsValue"]').val();
//		var params = {
//			name:paramsName,
//			value:paramsValue
//		};
//		//如果参数名和参数值都大于0则push到数组里
//		if(paramsName.length >0 && paramsValue.length >0){
//			algorithmParams.push(params);
//		}
//		alert(params + '=======');
//	});
    
    
    var wleft = $("#wleft").val();
    var wright = $("#wright").val();
    var numcomp = $("#numcomp").val();
    var paramsFlag = false
    if(wleft.length >0 && wright.length >0 && numcomp.length >0){
    	paramsFlag = true;    	
    }
    
	alert('取到每一个参数找到参数名，参数值，打包成josn数据');
	if(!paramsFlag){
		$('#errMsg2').html('请填写参数');
	}
	//这个有可能得改
	var params = {		
		xmdataIds:xmdataIds,
		xsdataIds:xsdataIds,
		xcdataIds:xcdataIds,		
		wleft:wleft,
		wright:wright,
		numcomp:numcomp
	};
	//window.location.href = ctx + '/analysis/analysisData?params='+ $.toJSON(params);
	$('#dataAnalysisPDSParams').val($.toJSON(params));//把数组转换成json字符串
	$('#handleData').addClass("disabled");
    $('#handleData').attr('disabled',true);
	var url = ctx + '/dataAnalysis/dataAnalysisPDS';
	alert('/把数组转换成json字符串');	
	$('#dataAnalysisForm').attr('action',url);
	$('#dataAnalysisForm').submit();//此处采用post的方式提交 防止参数过长
}



//绑定按钮2
//$("button[name='selectDatas']").click(function(){
//	$("#selectDatas").modal('show');
//	var btnId = $("button[name='selectDatas']").attr('id');
//	
//	judgeButton(btnId);
//	
//});

//判断是哪个按钮
function judgeButton(o){
	
	if(o == xm){
		return xmdataIds;
	}else if(o ==xs){
		return xsdataIds;
	}else if(o ==xc){
		return xcdataIds;
	}
}

//为每个checkbox绑定事件--新
function bindClick(){
	$('#data_list input:checkbox').click(function(){
		if($(this).is(':checked')){
			judgeButton(btnId).push(this.value);
		}else{
			judgeButton(btnId).remove(this.value);
		}
	});
}



