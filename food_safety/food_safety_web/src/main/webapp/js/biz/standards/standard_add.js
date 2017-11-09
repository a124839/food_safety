/**
 * Created by ichinait on 16-4-6.
 */

$(function(){
    $('input[name="releaseTime"]').datepicker({
        dateFormat:'yy-mm-dd',
        changeYear:true,
        changeMonth:true,
        yearRange:"1980:2050"
    });
    $('input[type!="hidden"]').blur(function(){
        clearErrorInfo();
    });
});


function toListPage(){
    window.location.href = ctx + '/standards/toQueryPage';
}

function saveStandard(){
    clearErrorInfo();
    if(!validateForm()){
        return ;
    }
    $("#standardForm").ajaxSubmit({
        url:ctx+'/standards/add',
        success:function(res){
            toListPage();
        }
    });
}


function validateForm(){
    var o = $('input[name="code"]');
    if($(o).val().length == 0){
        setError(o,'请输入标准编号');
        return false;
    }
    o = $('input[name="name"]');
    if($(o).val().length == 0){
        setError(o,'请输入标准名称');
        return false;
    }
    o = $('input[name="target"]');
    if($(o).val().length == 0){
        setError(o,'请输入针对对象');
        return false;
    }
    o = $('input[name="releaseTime"]');
    if($(o).val().length == 0){
        setError(o,'请输入发布名称');
        return false;
    }
    o = $('input[name="period"]');
    if($(o).val().length == 0){
        setError(o,'请输入年限');
        return false;
    }
    o = $('input[name="issuanceDepartment"]');
    if($(o).val().length == 0){
        setError(o,'请输入发布部门');
        return false;
    }
    o = $('input[name="attachment"]');
    if($(o).val().length == 0){
        setError(o,'请选择附件');
        return false;
    }
    return true;
}
