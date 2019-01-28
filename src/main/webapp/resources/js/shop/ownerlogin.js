/**
 * 登陆页面的js
 */

$(function(){
	var loginUrl = '/myo2o/shop/ownerlogincheck';
	var loginCount = 0;
	//调整注册页面路径
	$('#register').click(function() {
		window.location.href = '/myo2o/shop/register';//页面重定向
	});
	//登陆提交
	$('#submit').click(function(){
		var userName = $('#username').val();
		var password = $('#psw').val();
		var verifyCodeActual = $('#j_captcha').val();
		var needVerify = false;
		
		//登陆失败3次就需要验证码登陆，避免机器攻击
		if (loginCount >= 3) {
			if (!verifyCodeActual) {
				$.toast('请输入验证码！');
				return;
			} else {
				needVerify = true;
			}
		}
		$.ajax({
			url : loginUrl,
			async : false,
			cache : false,
			type : "post",
			dataType : 'json',
			data : {
				userName : userName,
				password : password,
				verifyCodeActual : verifyCodeActual,
				needVerify : needVerify
			},
			success : function(data) {
				if (data.success) {
					$.toast('登录成功！');//jq插件，消息弹窗
					window.location.href = '/myo2o/shop/shoplist';
				} else {
					$.toast('登录失败！');
					loginCount++;
					if (loginCount >= 3) {
						$('#verifyPart').show();
					}
				}
			}
		});
	});
	
});