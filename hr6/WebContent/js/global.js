//共通代码
var baseUrl = 'http://192.168.30.157:8080/hr6';

//全局ajax事件回调，处理ajax请求session超时
$.ajaxSetup({
	complete:function(xhr){
		
		var sessionStatus = xhr.getResponseHeader('sessionStatus');
				
		if (sessionStatus == 'timeout') {
			alert('登录超时，请重新登录');
			location.href = baseUrl + '/logout.jsp';
		}
	}
});