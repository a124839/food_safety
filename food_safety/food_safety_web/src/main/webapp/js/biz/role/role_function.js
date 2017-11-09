/**
 * Created by xiao on 2016/04/18.
 */
//选中
function checks(lv, o){
    var obj = $(o).attr('class');
    var arr = obj.split('_');
    if(lv == 1){
        if($(o).prop("checked")){
            $("input[class^='"+obj+"']").prop("checked", true);
        } else {
            $("input[class^='"+obj+"']").prop("checked", false);
        }
    } else if(lv == 2){
        var lv1 = arr[0]+'_'+arr[1];
        if(judgeThisLVHadChecked(lv, arr)){
            $("."+lv1).prop("checked", true);
        } else {
            $("."+lv1).prop("checked", false);
        }

        if($(o).prop("checked")){
            $("input[class^='"+obj+"']").prop("checked", true);
        } else {
            $("input[class^='"+obj+"']").prop("checked", false);
        }
    } else if(lv == 3){
        var lv1 = arr[0]+'_'+arr[1];
        var lv2 = arr[0]+'_'+arr[1]+'_'+arr[2];
        var had = false;
        if(judgeThisLVHadChecked(lv, arr)){
            had = true;
            $("."+lv2).prop("checked", true);
        }
        //else {
        //	$("."+lv2).prop("checked", false);
        //}

        if(judgeThisLVHadChecked(2, arr)){
            $("."+lv1).prop("checked", true);
        }
        else if(had) {
        	$("."+lv1).prop("checked", true);
        }

    }

}
// 判断同等级下是否有选中的
function judgeThisLVHadChecked(lv, arr){
    var count = 0;

    if(lv == 2){
        var o = arr[0]+'_'+arr[1];
        $("input[id][class^='"+o+"']").each(function(){
            if($(this).prop("checked")){
                count += 1;
            }
        });
    } else if(lv == 3) {
        var o = arr[0]+'_'+arr[1]+'_'+arr[2]+'_';
        $("input[class^='"+o+"']").each(function(){
            if($(this).prop("checked")){
                count += 1;
            }
        });
    }

    if(count > 0){
        return true;
    } else {
        return false;
    }
}


function saveRoleFuction(){
    var url = ctx+'/roleFunctionManage/roleAuthorization';

    $.ajax({
        url:url,
        data:$('#form').serialize(),
        type:'post',
        async:false,
        success:function(data){
            if(data.success){
              window.location.href = ctx + '/roleManage/toQueryPage';
            }
        },
        error:function(e){
            console.log(e);
        }
    });
}
