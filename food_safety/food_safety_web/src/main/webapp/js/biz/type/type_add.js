$(function(){
	$('input[type="radio"]').change(function(){
        if($(this).val() == 2){
            $('#cateloryLv1').show();
        }else{
            $('#cateloryLv1').hide();
            $('#parentId').val("");
        }
    });
});

//function toListPage(){
//	window.location.href = ctx +'/type/toQueryPage';
//}

/*
 * 以下是新写代码。。。。。。。。。。。。。。。。。。。。。。。。。。
 */

var code_flag = false;
function saveType(){
       clearErrorInfo();
    if(!validateForm()){
        return;
    }

    $('input[name="categoryLv1Id"]').val($('select[name="categoryLv1"]').val());
    $('input[name="categoryLv2Id"]').val($('select[name="categoryLv2"]').val());
    $("#saveBtn").addClass("disabled");
    $("#saveBtn").attr("disabled",true);

    var url = ctx + '/type/save';
    $.ajax({
       url:url,
        data:$('#typeForm').serialize(),
        type:'post',
        success:function(data){
            if(data.success == 1){
               toListPage();
            }else{
                $("#saveBtn").attr("disabled",false);
                $("#saveBtn").removeClass("disabled");
            }
        },
        error:function(e){
            console.log(e);
            $("#saveBtn").attr("disabled",false);
            $("#saveBtn").removeClass("disabled");
        }
    });
}


function toListPage(){
    window.location.href = ctx + '/type/toQueryPage';
}

function validateForm(){

    var code = $('input[name="code"]').val();
    if($.trim(code).length == 0){
        $('#errMsg').text('请输入编号！');
        return false;
    }
    validateCode();
    if(!code_flag){
        $('#errMsg').text('编号已被使用！');
        return false;
    }

    //一级名称
    var c1 = $('select[name="categoryLv1"]').val();
    var o = $('input[name="name"]').val();
    if($.trim(o).length == 0){
        $('#errMsg').text('请输入名称！');
        return false;
    }


    return true;
}

function validateCode() {
    var code = $('input[name="code"]').val();
    $.ajax({
        url:ctx+'/type/validate',
        data:{
            code:code
        },
        async:false,
        success:function(res){
            code_flag = res.flag;
        }

    })
}



