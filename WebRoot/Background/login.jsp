<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>"脱胎换轱"汽车4S店管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-easyui-1.3.5/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/cloud.js"></script>
<script type="text/javascript" src="js/prefixfree.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>

<script language="javascript">
	$(function(){
    	$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		$(window).resize(function(){  
    		$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    	});  	  
	});  
    	function ajaxlogin(){
    		var params=$('#loginForm').serialize();
    		$.ajax({
    			type:"post",
    			async:false,
    			url:"BackgroundLoginAction!login",  			
    			dataType:"json",
    			data:params,
    			success:function(data){
    				if(data.result!=null){
    					alert(data.result);
    				}else{
    					window.location.href=data.url; 
    				}    				
    			},
    			error:function(data){
    				alert("服务器出错，请刷新页面重试！");
    			}
    		});
    	}; 
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录"脱胎换轱"汽车4S店后台管理系统</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    <form id="loginForm">
      <ul>
    	<li><input name="user.uname" type="text"/><span>用户名</span></li>
    	<li><input name="user.passward" type="password"/><span>密&nbsp;&nbsp;&nbsp;码</span></li>
   		<li><input name="validateCode" style="width:100px" id="validateCode" type="text"/><span>验证码</span>
    		<img src="validateCodeAction" class="loginvldimg" id="validateCodeImg" title="点击重新生成验证码" />
			<span id="resetCode" style="display:inline-block"></span></li>
      </ul>
      <input type="button" class="loginbtn" value="登录" onclick="ajaxlogin()" /><label><input name="" type="checkbox" value="" checked="checked" />记住密码</label><label><a href="#">忘记密码？</a></label>
    </form>   
    </div>   
    </div>            
    <div class="loginbm"><a href="login.jsp ">"脱胎换轱"项目组</a> 版权所有Copyright ©  2014-2017  All Rights Reserved.</div>	    
</body>

</html>
