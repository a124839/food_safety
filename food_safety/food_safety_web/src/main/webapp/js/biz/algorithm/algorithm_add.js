/**
 * Created by ichinait on 2016/3/22.
 */
$(function(){
    $('input[type!="hidden"]').blur(function(){
        clearErrorInfo();
    });
});

function addParams(){
    var content = '<div class="form-group"><label for="instrumentName" class="col-sm-2 control-label">参数名称</label><div class="col-sm-4"><input type="text" class="form-control" name="paramsName" placeholder="参数名称">'
       +'</div><label for="instrumentName" class="col-sm-1 control-label">默认值</label><div class="col-sm-4"><input type="text" name="paramsValue" class="form-control"placeholder="默认值"></div></div>';
    $('#params').append(content);
}

function delParams(){
    var id = $('#params .form-group:last').find('input[name="id"]').val();
    if(id != null){
        ajaxDelParams(id);
    }else{
        $('#params .form-group:last').remove();
    }
}

function ajaxDelParams(id){
    var url = ctx + '/algorithm/deleteParams';
    $.ajax({
        url:url,
        data:{'paramId':id},
        type:'post',
        success:function(o){
            $('#params .form-group:last').remove();
        },
        error:function(e){

        }
    });
}

function save(){
    clearErrorInfo();
    if(!validateForm()){
        return;
    }
    var algorithmParams = [];
    var postParams = {
        algorithmName:$('#algorithmName').val(),
        algorithmType:$('#algorithmType').val(),
        sampleCode:$('textarea[name="sampleCode"]').val(),
        example:$('textarea[name="example"]').val(),
        memo:$('textarea[name="memo"]').val()
       
    };
    $('#params .form-group').each(function(index,elem){
        var paramsName = $(elem).find('input[name="paramsName"]').val();
        var paramsValue = $(elem).find('input[name="paramsValue"]').val();
        var params = {
            name:paramsName,
            value:paramsValue
        };
        if(paramsName.length >0 && paramsValue.length >0){
            algorithmParams.push(params);
        }
    });

    postParams.algorithmParams = algorithmParams;
    $('#datas').val($.toJSON(postParams));
    $('#saveBtn').addClass('disabled');
    $('#saveBtn').attr('disabled',true);

    var url = ctx +'/algorithm/save';
    $('#algorithmForm').ajaxSubmit({
        url:url,
        success:function(o){
            if(o.success == 1){
                window.location.href = ctx +'/algorithm/toAlgorithmManagePage';
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
    window.location.href = ctx +'/algorithm/toAlgorithmManagePage';
}


function edit(){
    clearErrorInfo();
    if(!validateForm()){
        return;
    }
    var algorithmParams = [];
    var postParams = {
        algorithmName:$('#algorithmName').val(),
        algorithmType:$('#algorithmType').val(),
        id:$('#algorithmId').val(),
        sampleCode:$('textarea[name="sampleCode"]').val(),
        example:$('textarea[name="example"]').val(),
        memo:$('textarea[name="memo"]').val()
    };
    $('#params .form-group').each(function(index,elem){
        var paramsName = $(elem).find('input[name="paramsName"]').val();
        var paramsValue = $(elem).find('input[name="paramsValue"]').val();
        var id = $(elem).find('input[name="id"]').val();
        var params = {
            id:id,
            name:paramsName,
            value:paramsValue
        };
        if(paramsName.length >0 && paramsValue.length >0){
            algorithmParams.push(params);
        }
    });

    postParams.algorithmParams = algorithmParams;
    $('#datas').val($.toJSON(postParams));
    var url = ctx +'/algorithm/save';
    $('#saveBtn').addClass('disabled');
    $('#saveBtn').attr('disabled',true);

    $('#algorithmForm').ajaxSubmit({
        url:url,
        success:function(o){
            if(o.success == 1){
                window.location.href = ctx +'/algorithm/toAlgorithmManagePage';
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


function validateForm(){
    var text = '';

    if($.trim($('#algorithmName').val()).length == 0){
        text = '请输入算法名称';
        setError($('#algorithmName'),text);
        return false;
    }
    if($.trim($('textarea[name="sampleCode"]').val()).length == 0){
        text = '请输入示例代码';
        setError($('textarea[name="sampleCode"]'),text);
        return false;
    }

    if($.trim($('textarea[name="example"]').val()).length == 0){
        text = '请输入示例';
        setError($('textarea[name="example"]'),text);
        return false;
    }

   if($('#algorithmId').val() == null ||  $('#algorithmId').val() ==''){
       if($.trim($('input[name="attachment"]').val()).length == 0){
           text = '请上传附件';
           setError($('textarea[name="attachment"]'),text);
           return false;
       }
   }
    var paramsName = true;
    var paramsValue =true;
    $('input[name="paramsName"]').each(function(){
        if($.trim(this.value).length ==0){
            text = '请输入参数名称';
            setError(this,text);
            paramsName =false;
            return false;
        }
    });
    if(!paramsName){
        return false;
    }

    $('input[name="paramsValue"]').each(function(){
        if($.trim(this.value).length ==0){
            text = '请输入参数值';
            setError(this,text);
            paramsValue = false;
            return false;
        }
    });
    if(!paramsValue){
        return false;
    }
    return true;
}