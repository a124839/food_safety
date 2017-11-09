function saveServiceType(){
    clearErrorInfo();
   if(!validateForm()){
      return;
   }

    $("#saveBtn").addClass("disabled");
    $("#saveBtn").attr("disabled",true);
    var url = ctx + '/serviceType/save';
    $.ajax({
       url:url,
        data:$('#serviceTypeForm').serialize(),
        type:'post',
        success:function(data){
            if(data.success == 1){
               toListPage();
            }else{
                $("#saveBtn").removeClass("disabled");
                $("#saveBtn").attr("disabled",false);
            }
        },
        error:function(e){
            console.log(e);
            $("#saveBtn").removeClass("disabled");
            $("#saveBtn").attr("disabled",false);
        }
    });
}


function toListPage(){
	window.location.href = ctx +'/serviceType/toQuery2Page';
}

function validateForm(){
    var flag = true;
    var o = $('input[name="name"]').val();
    if($.trim(o).length == 0){
        $('#errMsg').text('请输入名称！');
        flag = false;
    }
    $.ajax({
       url:ctx+'/serviceType/queryTypeByName',
        data:{
            name:o
        },
        type:'post',
        async:false,
        success: function (res) {
            if(res.success == 1 && res.has){
                $('#errMsg').text('名称已存在！');
                flag = false;
            }
        }
    });

    return flag;
}
