Array.prototype.indexOf = function(item){
	for(var i = 0;i<this.length;i++){
		if(this[i] == item){
			return i;
		}
	}
	return -1;
};

Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};

/**
 * 发送异步的ajax
 * @param url
 * @param postData
 * @param success
 * @param error
 */
function sendAsyncAjax(url,postData,successfn,errorfn){
	$.ajax({
		url:ctx+url,
		data:postData,
		type:"post",
		async:true,
		success:function(data){
			successfn(data);
		},
		error:function(e){
			errorfn(e)
		}
	});
	
}

function initAlgorithm(category){
	var url = ctx +'/algorithm/queryNoPage';
	$.ajax({
		url:url,
		data:{category:category},
		type:'post',
		success:function(o){
			if(o.success == 1){
				var options = '<option value="0">选择算法</option>';
				$.each(o.algorithms,function(index,item){
					options += '<option value="'+item.id+'">'+item.name+'</option>'
				});
				$('#algorithm').html(options);
				bindChangeEvent();
			}
		},
		error:function(e){
			console.log(e);
		}

	});
}
/**
 * 绑定算法选择事件
 */
function bindChangeEvent(){
	$('#algorithm').change(function(){
		var algorithmId = this.value;
		if(algorithmId == 0){
			$('#params').html('');
			return;
		}
		$.ajax({
			url:ctx+'/algorithm/queryParamsNoPage',
			data:{'algorithmId':algorithmId},
			type:'post',
			success:function(o){
				if(o.success == 1){
					var content = '';
					$.each(o.params,function(index,item){
						content += '<div class="form-group"><label for="paramsName" class="col-sm-2 control-label">参数:</label><div class="col-sm-3"><label name="paramsName" class="col-sm-3 control-label">'+item.name+'</label></div>' +
							'<label for="paramsValue" class="col-sm-2 control-label">默认值</label><div class="col-sm-4"><input type="text" name="paramsValue" class="form-control" value="'+item.value+'"></div></div>';
					});
					$('#params').html(content);
				}
			},
			error:function(e){
				console.log(e);
			}

		});
	});
}

/**
 * 清除已显示的错误信息
 */
function clearErrorInfo(){
	$('div .has-error').removeClass('has-error');
	$("#errMsg").text('');
}

function setError(o,text){
	$("#errMsg").html(text);
	$(o).parent('div').addClass('has-error');
	$(o).focus();
}


function queryTypes(id,type){
    var url = ctx +'/type/queryByParentId';
    $.ajax({
        url:url,
        data:{
            id:id,
            type:1
        },
        type:'post',
        success:function(res){
            var content = '';
            if(type == 0){
            	content += "<option value=''>全部</option>";
            }
            $.each(res.types,function(index,o){
                content += '<option value="'+ o.id+'">'+ o.name+'</option>';
            });
            $('select[name="categoryLv2"]').html(content);
        },
        error:function(e){

        }

    })
}


function isNum(o) {
	return parseInt(o)==o
}