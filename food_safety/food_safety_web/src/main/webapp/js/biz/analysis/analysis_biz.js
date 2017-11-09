/**
 * Created by ichinait on 2016/3/28.
 */
$(function(){
    loadData();
});

function search() {
    $("#currsor").val(1);//将curror值设置为1，不然后台会做分页，导致查不到数据
    loadData();
}


function loadData(){
    var projectName = $("#projectName").val();
    var postData = {
        curr : $("#currsor").val()
    };
    if (projectName.length != 0) {
        postData.projectName = projectName
    }
    var url = ctx + '/analysis/query';

    $.ajax({
        url:url,
        data:postData,
        type:'post',
        success:function(res){
            if(res.success == 1){
                var content = '';
                var i = 1;
                $.each(res.list,function(index,o){
                    content += '<tr><td>'+i+'</td><td>'+ o.projectName+'</td><td>'+ o.algorithmName+'</td><td>'+moment( o.ct).format('YYYY-MM-DD HH:mm:ss')+'</td><td>'+ o.operator+'</td><td>';

                    if(judgeAuthorities('analysisManageView')){
                        content += '<a href="javascript:view(\''+ o.id+'\')">查看分析</a>&nbsp;';
                    }
                    if(judgeAuthorities('analysisManageComment')){
                        content += '&nbsp;<a href="javascript:comment(\''+ o.id+'\')">评价</a>';
                       
                    }
                    content += '</td></tr>';
                    i++;
                });
                if(content.length==0){
                    content += '<tr><td colspan="6">暂无数据</td></tr>';
                }

                $('#analysis_list').html(content);
            }
        },
        error:function(e){
            console.log(e);
        }
    })

}


function view(id){
    window.location.href = ctx +'/analysis/view?id='+id;
}

function comment(id){
    window.location.href = ctx +'/comment/list?analysisId='+id;
    // $('input[name="analysisId"]').val(id);
    // $("#fileAddModal").modal("show");
}



