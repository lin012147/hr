
var baseUrl = 'http://localhost:7777/hr';

//全局ajax事件回调，处理ajax请求你session超时
$.ajaxSetup({
	complete:function(xhr){
		var sessionStatus = xhr.getResponseHeader('sessionStatus');
		if (sessionStatus == 'timeout'){
			alert('登录超时，请重新登录');
			location.href = baseUrl + '/logout.jsp';
		}
	}
})

