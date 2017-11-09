/**
 * Created by Dawn on 2016/07/04.
 */
$(function () {
    //
    // $('#xiao').click();
    // $('#userMsg').hide();

	 var curURL = window.location.href;
     var lastIndex = curURL.lastIndexOf("/");
     curURL = curURL.substring(lastIndex+1, curURL.length);
     if(curURL == 'index'){
     	getOnlineUser();
     }

});

function getOnlineUser(){
	$('#onlineBody').empty();
	$('#pager').empty();
	
    var url = ctx +'/index/onlineUser';
    $.ajax({
        url:url,
        type:'post',
        success:function(data){
            if(data.length > 0){
                var nums = 15; //每页出现的数量
                var pages = Math.ceil(data.length/nums); //得到总页数

                var thisDate = function(curr){
                    var str = '', last = curr*nums - 1;
                    last = last >= data.length ? (data.length-1) : last;
                    for(var i = (curr*nums - nums); i <= last; i++){
                        str += '<tr><td>'+ nullFormart(data[i].userName) +'</td><td>'+nullFormart(data[i].tutor)+'</td>' +
                            '<td>'+nullFormart(data[i].researchDirection)+'</td><td>'+nullFormart(data[i].contact)+'</td></tr>';
                    }
                    return str;
                };
                laypage.dir = ctx +'/css/laypage/laypage.css';//自定义样式路径
                //调用分页
                laypage({
                    cont: 'pager',//容器。
                    pages: pages,//总页数
                    //curr: 1, //初始化当前页
                    first:'首页',
                    last:'尾页',
                    prev:'<',
                    next:'>',
                    groups: 5, //连续显示分页数
                    jump: function(obj){
                        document.getElementById('onlineBody').innerHTML = thisDate(obj.curr);
                    }
                });
            }else{
                var content = '<tr><td colspan="4">暂无数据</td>></tr>';
                $('#onlineBody').html(content);
            }
        },
        error:function(e){

        }
    })

    function  nullFormart(o) {
        if(o=='null' || o==null || o.length == 0){
            return '无';
        }
        return o;
    }
}
