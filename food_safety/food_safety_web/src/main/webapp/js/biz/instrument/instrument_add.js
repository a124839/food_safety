/**
 * Created by ichinait on 16-3-24.
 */
$(function() {
    $('input[type!="hidden"]').blur(function(){
        clearErrorInfo();
    });
    $( "input[name='productionDate']" ).datepicker({
            dateFormat:'yy-mm-dd',
            changeYear:true,
            changeMonth:true,
            yearRange:"1980:2050"
        });
    $( "input[name='purchaseDate']" ).datepicker({
        dateFormat:'yy-mm-dd',
        changeYear:true,
        changeMonth:true,
        yearRange:"1980:2050"
    });
    $( "input[name='installStartDate']" ).datepicker({
        dateFormat:'yy-mm-dd',
        changeYear:true,
        changeMonth:true,
        yearRange:"1980:2050"
    });

    $( "input[name='installEndDate']" ).datepicker({
        dateFormat:'yy-mm-dd',
        changeYear:true,
        changeMonth:true,
        yearRange:"1980:2050"
    });
});
function saveInstrument(){
    clearErrorInfo();
    if(!validateForm()){
        return;
    }
    $('#saveBtn').addClass('disabled');
    $('#saveBtn').attr('disabled',true);

    var url = ctx + '/instrument/save';
    $('#instrumentForm').ajaxSubmit({
        url:url,
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
    window.location.href = ctx + '/instrument/toInstrumentPage';
}

function validateForm(){
    var text = '';

    if($.trim($('input[name="code"]').val()).length == 0){
        text = '请输入资产编号';
       setError($('input[name="code"]'),text);
        return false;
    }
/*    if($.trim($('input[name="sn"]').val()).length == 0){
        text = '请输入序列号';
        setError($('input[name="sn"]'),text);
        return false;
    }*/
    if($.trim($('input[name="name"]').val()).length == 0){
        text = '请输入设备名称';
        setError($('input[name="name"]'),text);
        return false;
    }
/*    if($.trim($('input[name="model"]').val()).length == 0){
        text = '请输入设备型号';
        setError($('input[name="model"]'),text);
        return false;
    }
    if($.trim($('input[name="type"]').val()).length == 0){
        text = '请输入设备类型';
        setError($('input[name="type"]'),text);
        return false;
    }*/

/*   if($.trim($('input[name="purchaseDate"]').val()).length == 0){
        text = '请输入购买日期';
        setError($('input[name="purchaseDate"]'),text);
        return false;
    }*/

/*    if($.trim($('input[name="installStartDate"]').val()).length == 0){
        text = '请输入安装日期';
        setError($('input[name="installStartDate"]'),text);
        return false;
    }*/
    if($.trim($('input[name="manufactor"]').val()).length == 0){
        text = '请输入生产商';
        setError($('input[name="manufactor"]'),text);
        return false;
    }

 /* if($.trim($('input[name="productionDate"]').val()).length == 0){
        text = '请输入生产日期';
        setError($('input[name="productionDate"]'),text);
        return false;
    }
    if($.trim($('input[name="performances"]').val()).length == 0){
        text = '请输入性能指标';
        setError($('input[name="performances"]'),text);
        return false;
    }*/

    return true;
}


