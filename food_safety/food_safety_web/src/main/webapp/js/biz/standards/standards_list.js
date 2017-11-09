/**
 * Created by ichinait on 16-4-6.
 */

$(function(){
    loadData();
});


function search(){
    $('#currsor').val(1);
    loadData();
}


function loadData(){
    var postData = {
        curr:$('#currsor').val(),
        type:$('#type').val(),
        target:$('#target').val(),
        name:$('#standardName').val(),
        status:$('#status').val()
    };
console.log($.toJSON(postData));
    $.ajax({
       url:ctx+'/standards/query',
       data:postData,
       type:'post',
        success:function(res){
            if(res.success == 1){
                var i = 1;
                var _html = '';
                $.each(res.list,function(index,o){
                    _html += '<tr><td>'+i+'</td><td>'+ o.code+'</td><td>'+ o.name+'</td><td>'+setType( o.type)+'</td><td>'+ o.target+'</td><td>'+ moment(o.releaseTime).format('YYYY-MM-DD')+'</td>' +
                        '<td>'+ o.issuanceDepartment+'</td><td>'+ o.period+'</td><td>'+ setStatus(o.status)+'</td><td>'+ o.operator+'</td><td>';
                    if(judgeAuthorities('standardsManageView')){
                        _html += '<a href="javascript:view(\''+o.id+'\')">查看</a>&nbsp;';
                    }
                    if(judgeAuthorities('standardsManageEdit')){
                        _html += '<a  href="javascript:edit(\''+o.id+'\')">修改</a>&nbsp;';
                    }
                    if(judgeAuthorities('standardsManageDel')){
                        _html += '<a href="javascript:showWarnning(\''+o.id+'\')">删除</a>&nbsp;';
                    }
                    if(judgeAuthorities('standardsManageDownload')){
                        _html += '<a href="javascript:download(\''+ o.attachmentId+'\')">下载附件</a>';
                    }
                    _html += '</td></tr>';
                    i++;
                });
                if(_html.length == 0){
                    _html = '<tr><td colspan="11">暂无记录</td></tr>';
                }
                $('#standards_list').html(_html);
            }
        },
        error:function(e){
            console.log(e);
        }
    });
}


function toAddPage(){
    window.location.href = ctx + '/standards/toAddPage';
}

function download(id){
    window.location.href = ctx + '/attachment/download?attachmentId='+id+'&type=standard';
}

function view(id){
    window.location.href = ctx + '/standards/view?id='+id

}

function edit(id){
    window.location.href = ctx + '/standards/toEditPage?id='+id

}
var standardId = '';
function showWarnning(id){
    setModal("警告",'确认删除此标准?',deleteRecord);
    standardId = id;
}

function deleteRecord(){
    var url = ctx + '/standards/delete';
    $.ajax({
        url:url,
        data:{"id":standardId},
        type:'post',
        success:function(res){
            if(res.success == 1){
                loadData();
            }
        },
        error:function(e){
            console.log(e);
        }
    })
}

function setType(type){
    var content = '';
    switch (type){
        case '1':
            content = '国家标准';
            break;
        case '2':
            content = '行业标准';
            break;
        case '3':
            content = '企业标准';
            break;
        case '4':
            content = '自定标准';
            break;
    }
    return content;
}


function setStatus(status){
    var content = '';
    switch (status){
        case 1:
            content = '正常';
            break;
        case 2:
            content = '<font color="red">已过期</font>';
            break;
        default :
            content = '正常';
    }
    return content;
}

