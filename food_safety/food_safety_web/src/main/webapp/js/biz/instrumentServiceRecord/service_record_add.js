/**
 * Created by ichinait on 2016/4/29.
 */

$(function(){
    $( "input[name='serviceDate']").change(function(){
       clearErrorInfo();
    });

    $( "input[name='serviceDate']" ).datetimepicker({
        changeYear:true,
        changeMonth:true,
        yearRange:"1980:2050",
        dateFormat:'yy-mm-dd',
        timeFormat:'HH:mm:ss'
    });
});

function saveRecord(){
    var o = $( "input[name='serviceDate']").val();
    if(o.length == 0){
        setError($( "input[name='serviceDate']"),'请选择服务时间！');
        return ;
    }


    var url = ctx + '/instrumentServiceRecord/save';
    $.ajax({
        url:url,
        data:$('#srForm').serialize(),
        type:'post',
        success:function(res){
            if(res.success == 1){
                window.location.href = ctx + '/instrumentServiceRecord/toInstrumnetServicePage?id='+$('input[name="instrumentId"]').val();
            }
        },
        error:function(e){

        }
    })
}

function toListPage(){
    window.location.href = ctx + '/instrumentServiceRecord/toInstrumnetServicePage?id='+$('input[name="instrumentId"]').val();
}
