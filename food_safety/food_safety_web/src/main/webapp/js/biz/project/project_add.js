$(function(){
    $('input[name="startDate"]').datepicker({
        dateFormat:'yy-mm-dd',
        changeYear:true,
        changeMonth:true,
        yearRange:"1980:2050"
    });
    $('input[type!="hidden"]').blur(function(){
        clearErrorInfo();
    });
    $('input[name="endDate"]').datepicker({
        dateFormat:'yy-mm-dd',
        changeYear:true,
        changeMonth:true,
        yearRange:"1980:2050"
    });
    $('input[type!="hidden"]').blur(function(){
        clearErrorInfo();
    });
});


function saveProject(){
	clearErrorInfo();
	if(!validateForm()){
		return;
	}
    $('#saveBtn').addClass('disabled');
	$('#saveBtn').attr('disabled',true);

	$("#projectForm").ajaxSubmit({
		url:ctx+'/project/save',
		success:function(data){
			if(data.success == 1){
				toListPage();
			}else{
                $('#saveBtn').removeClass('disabled');
				$('#saveBtn').attr('disabled',false);

			}
		},
		error:function(e){
            $('#saveBtn').removeClass('disabled');
			$('#saveBtn').attr('disabled',false);

			console.log(e);
		}
		
	});
}

function toListPage(){
	window.location.href = ctx +'/project/toProjectManagePage';
}

function validateForm(){
	var text = '';

	if($.trim($('input[name="name"]').val()).length == 0){
		text = '请输入实验名称';
		setError($('input[name="name"]'),text);
		return false;
	}

	if($.trim($('input[name="startDate"]').val()).length == 0){
		text = '请输入开始日期';
		setError($('input[name="startDate"]'),text);
		return false;
	}
	if($.trim($('input[name="endDate"]').val()).length == 0){
		text = '请输入结束时间';
		setError($('input[name="endDate"]'),text);
		return false;
	}

	return true;
}