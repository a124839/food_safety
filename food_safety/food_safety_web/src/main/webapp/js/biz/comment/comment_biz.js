/**
 * Created by IChinait on 2016/6/28.
 */

$(function(){
  loadData();  
});


function search() {
    $("#currsor").val(1);
    loadData();
}

function loadData(){
    var url = ctx +'/comment/query';
    var data = {
        name:$('#cname').val(),
        curr : $("#currsor").val(),
        analysisId:analysisId
    };
    $.ajax({
       url:url, 
        data:data,
        type:'post',
        success:function (res) {
            if(res.success == 1){
                var content = '';
                $.each(res.list,function (i,o) {
                    
                    content +="<tr><td>"+(i+1)+"</td><td>"+o.name+"</td><td>"+o.jmyps+"</td><td>"+o.mxzcfs+"</td><td>"+o.rR+"</td><td>"+o.rmscv+"</td><td>"+o.memo+"</td><td>"+o.operator+"</td><td>"+moment(o.ct).format('YYYY-MM-DD HH:mm:ss')+"</td><td>";
                    content += '&nbsp;<a href="javascript:modalView(\''+ o.id+'\')">查看模型参数</a>';
                    content += '&nbsp;<a href="javascript:download(\''+ o.modalAttachmentId+'\')">下载模型</a>';
                    content += '&nbsp;<a href="javascript:download(\''+ o.dataAttachmentId+'\')">下载数据</a>';
                    content += '</td></tr>';
                });
                if(content.length==0){
                    content = '<tr><td colspan="10">暂无数据</td></tr>';
                }
                $('#comment_list').html(content);
            }
        },
        error:function (e) {
            console.log(e);
        }
    
        
    });
    
}


function comment(){
    $("#fileAddModal").modal("show");
    $('input[name="analysisId"]').val(analysisId);
}

/**
 * 上传数据
 */
function submitForm(){
    clearErrorInfo();
//    if(!validateForm()){
//        return;
//    }
    if($('input[name="name"]').val().length == 0){
        $('input[name="name"]').focus();
        $('#errMsg').html("请输入名称！");
        return false;
    }
    if($('input[name="file1"]').val().length == 0){
        $('input[name="file1"]').focus();
        $('#errMsg').html("请选择图谱文件！");
        return false;
    }
    if($('input[name="file2"]').val().length == 0){
        $('input[name="file2"]').focus();
        $('#errMsg').html("请选择模型文件！");
        return false;
    }
    $('#saveBtn').addClass('disabled');
    $('#saveBtn').attr('disabled',true);

    var url = ctx+'/analysis/comment';
    $("#dataForm").ajaxSubmit({
        url:url,
        success:function(data){
            $("#fileAddModal").modal("hide");
            $('#saveBtn').removeClass('disabled');
            $('#saveBtn').attr('disabled',false);
            if(data.success == 1){
                setModal('信息', '上传成功！', loadData);
            }
        },
        error:function(e){
            console.log(e);
            alert("上传文件出错，请检查您的文件或联系管理员！");
            $('#saveBtn').removeClass('disabled');
            $('#saveBtn').attr('disabled',false);
        },

        resetForm:false
    });
}


function download(id){
    window.location.href = ctx + '/attachment/download?attachmentId='+id+'&type=modal';
}

function modalView(id){

    var url = ctx + '/analysis/queryCommentById';
    $.ajax({
        url:url,
        data:{id:id},
        type:'post',
        success:function (res) {
            if(res.success == 1){
                $("#fileAddModal2").modal("show");
                var o = res.comment;
                $('#name').text(o.name);
                $('#jmyps').text(o.jmyps);
                $('#zcfs').text(o.mxzcfs);
                $('#rmscv').text(o.rmscv);
                $('#r').text(o.rR);
            }else{
                alert("未找到相关参数！");
            }
        }

    })



}
