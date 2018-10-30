/**
 * 登录页面中用到的JavaScript脚本
 */
$(document).ready(function(){
	//验证验证码是否正确
	$("#validateCode").blur(function(){
		//获取用户输入的验证码
		var usersValidateCode = $(this).val();
		//使用post方式提交到服务器上指定的应用程序，判断验证码是否正确
		$.ajax({
			type:"post",
			async:false,
			url:"BackgroundLoginAction!validateCode",
			dataType:"json",
			data:{usersValidateCode:usersValidateCode},
			success:function(data){
				if(data.result == "correct") {
					$("#resetCode").html("验证码正确");
				} else {
					$("#resetCode").html("验证码错误");
				}
			},
			error:function(data){			
				alert("服务器出现异常，请重新填写")
			}
		});
	});
	
	//点击刷新重新生成验证码
	$("#validateCodeImg").click(function(){
		//获取当前时间的毫秒值
		var timenow = new Date().getTime();      
		//追加当前时间的毫秒值可以确保每次请求都不一样，重新生成验证码
		$(this).attr("src","validateCodeAction?d="+timenow);
	});
	
});