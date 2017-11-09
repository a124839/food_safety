var webSocket = null;
var tryTime = 0;
$(function () {
    initSocket();

    window.onbeforeunload = function () {
    	//离开页面时的其他操作
    };
});

/**
 * 初始化websocket，建立连接
 */
function initSocket() {
	if ('WebSocket' in window) {
    	webSocket = new WebSocket("ws://"+headerHost + ctx+"/webSocketServer");
    }
    else if ('MozWebSocket' in window) {
    	webSocket = new MozWebSocket("ws://"+headerHost + ctx+"/webSocketServer");

    } 
    else {
    	webSocket = new SockJS("http://"+headerHost + ctx+"/sockjs/webSocketServer");
    }
    
    // 收到服务端消息
    webSocket.onmessage = function (msg) {
       // console.log(msg);
        $('#tongzhi').append('<li><a href="#"><i class="fa fa-user text-light-blue">'+msg.data+'</i></a></li>');
        $('#userMsg').click();

        var curURL = window.location.href;
        var lastIndex = curURL.lastIndexOf("/");
        curURL = curURL.substring(lastIndex+1, curURL.length);
        if(curURL == 'index'){
        	getOnlineUser();
        }

        setTimeout(function () {
            $('#userMsg').click();
        }, 2000);

    };
    
    // 异常
    webSocket.onerror = function (event) {
        console.log(event);
    };
    
    // 建立连接
    webSocket.onopen = function (event) {
        console.log(event);
    };
    
    // 断线重连
    webSocket.onclose = function () {
        // 重试3次，每次之间间隔10秒
        if (tryTime < 3) {
            setTimeout(function () {
                webSocket = null;
                tryTime++;
                initSocket();
            }, 10000);
        } else {
            tryTime = 0;
        }
    };
}