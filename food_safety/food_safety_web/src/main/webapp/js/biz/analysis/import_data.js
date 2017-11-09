/**
 * Created by ichinait on 2016/3/28.
 */
$(function(){
    initAlgorithm(2);
});

function importData(){
    clearErrorInfo();
    if(!validateForm()){
        return;
    }
    //参数信息
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
    //算法信息
    var algorithmInfo = {
        id:$('#algorithm').val(),
        algorithmName:$('#algorithm option:selected').text(),
        algorithmParams:algorithmParams
    };
    $("#algorithmInfo").val($.toJSON(algorithmInfo));
    $('#saveBtn').attr('disabled',true);
    $('#dataForm').submit();
}


function toDataListPage(){
    window.location.href = ctx +'/data/toDataManagePage'
}


function toListPage(){
    window.location.href = ctx +'/project/toProjectManagePage';
}

function validateForm(){
    var text = '';
    if($('#algorithm').val() == 0){
        $('#errMsg').html('请选择处理数据的算法');
        $('#algorithm').focus();
        return false;
    }

    var paramsValue = true;
    $('input[name="paramsValue"]').each(function(){
        if(this.value.length ==0){
            text = '请输入参数值';
            setError(this,text);
            paramsValue = false;
            return false;
        }
        paramsValue = true;
    });
    if(!paramsValue){
        return false;
    }
    if($('input[type="file"]').val().length == 0){
        text = '请选择文件';
        setError($('input[name="file"]'),text);
        return false;
    }
    return true;
}