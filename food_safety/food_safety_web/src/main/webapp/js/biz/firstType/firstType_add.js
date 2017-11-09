function saveFirstType(){
//    clearErrorInfo();
//    if(!validateForm()){
//        return;
//    }

    $("#saveBtn").addClass("disabled");
    
    var url = ctx + '/firstType/save';
    $.ajax({
       url:url,
        data:$('#firstTypeForm').serialize(),
        type:'post',
        success:function(data){
            if(data.success == 1){
               toListPage();
            }else{
                $("#saveBtn").removeClass("disabled");
            }
         
        },
        error:function(e){
            console.log(e);  
            $("#saveBtn").removeClass("disabled");
        }
    });
}


function toListPage(){
	window.location.href = ctx +'/firstType/toQueryPage';
}

function validateForm(){
    
    var o = $('input[name="name"]').val();
    if($.trim(o).length == 0){
        $('#errMsg').text('请输入名称！');
        return false;
    }
    return true;
}