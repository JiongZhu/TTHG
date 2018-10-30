<!--<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>-->

<!DOCTYPE HTML>
<html>
<head>
<title>Home</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--slider-->
<link href="css/slider.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery.nivo.slider.js"></script>
<link rel="Stylesheet" type="text/css" href="style/loginDialog.css" />
<script type="text/javascript">
    $(window).load(function() {
        $('#slider').nivoSlider();
    });
    </script>
</head>
<body>
<div class="header">	
  <div class="wrap"> 
	<div class="header-top">
		 <div class="logo">
			<img src="images/logo.png" alt="">
		 </div>
		  <div class="tui">
			欢迎进入"脱胎换骨"4s管理系统!<!--<a  class="tui" href="index.html">退出</a>-->
		
		 </div>
		 <div class="menu">
			<div id="cssmenu">
				<ul>
				   <li class="active"><a href="index.jsp"><span>HOME</span></a></li>
				   <li class="has-sub"><a href="selectaction!showBrand"><span>SHOW</span></a></li>
				   <li class="has-sub"><a href="order.jsp"><span>ORDER</span></a></li>
                   <li class="has-sub"><a href="about.jsp"><span>ABOUT</span></a></li>
				   <li class="last"><a href="contact.jsp"><span>CONTACT</span></a></li>
				</ul>
            </div>
		  </div>	
		  <div class="clear"></div> 
	   </div>
   </div>	
</div>
      <!------ Slider ------------>
	  <div class="slider">
	      	<div class="slider-wrapper theme-default">
	            <div id="slider" class="nivoSlider">
	                <img src="images/banner2.jpg" alt="" />
	                <img src="images/banner1.jpg" alt="" />
	                <img src="images/banner3.jpg" alt="" />
	                <img src="images/banner4.jpg" alt="" />
	                <img src="images/banner5.jpg" alt="" />
	            </div>
	        </div>
   		</div>
		<!------End Slider ------------>
	<div class="main">
         
		<div class="content-top">
        <div class="c1">
                <h4>top selling</h4>
                </div>
			<div class="wrap">
				<div class="section group">
           
				<div class="col_1_of_3 span_1_of_3">
					<div class="thumb-pad2">
	                    <div class="thumbnail">
	                        <h4>Number 1</h4>
	                        <figure><img style="width:204px;height:204px;" src="images/pic1.png" alt=""><em class="sp1"></em></figure>
	                        <div class="caption">
	                            <p>从汽车诞生直到今天，奔驰汽车的发展史不仅仅是一个传奇，更是代表了人类汽车工业的发展史。现在，奔驰汽车已过百岁寿辰，然而它的名字和公司的口号一样，依然叫的响亮。</p>
	                            <a href="selectaction!showType?carBrand=奔驰" class="btn-default btn1">more</a>
	                        </div>
	                    </div>
                	</div>
				</div>
                
				<div class="col_1_of_3 span_1_of_3">
					<div class="thumb-pad2">
	                    <div class="thumbnail">
	                        <h4>Number 2</h4>
	                        <figure><img style="width:204px;height:204px;" src="images/pic2.png" alt=""><em class="sp1"></em></figure>
	                        <div class="caption">
	                            <p>连续三年获得发动机比赛第一名，奥迪是一个国际著名豪华汽车品牌。其代表的高技术水平、质量标准、创新能力、以及经典车型款式让奥迪成为世界最成功的汽车品牌之一。</p>
	                            <a href="selectaction!showType?carBrand=奥迪" class="btn-default btn1">more</a>
	                        </div>
	                    </div>
	                </div>
				</div>
				<div class="col_1_of_3 span_1_of_3">
					<div class="thumb-pad2">
	                    <div class="thumbnail">
	                        <h4>Number 3</h4>
	                        <figure><img style="width:204px;height:204px;" src="images/pic.png" alt=""><em class="sp1"></em></figure>
	                        <div class="caption">
	                            <p>宝马轿车车身造型具有鲜明的特色，圆形灯具配以矩形水箱通风栅架形成与众不同的风格。宝马轿车的经营有自己独特的一面，它奉行的品牌战略是“精品战略”，追求的是最高的质量。</p>
	                            <a href="selectaction!showType?carBrand=宝马" class="btn-default btn1">more</a>
	                        </div>
	                    </div>
                    </div>
				</div>
				<div class="clear"></div> 
			</div>
			</div>
		</div>
        </div>
        
		<div class="content-middle">
			<div class="wrap">
				<div class="section group example">
					<div class="col_1_of_2 span_1_of_2">
						<div class="thumb-pad3">
	                   		<figure><img src="images/pic3.jpg" alt=""><em class="sp1"></em></figure>
	                    </div>
					 </div>
					<div class="col_1_of_2 span_1_of_2">
						<p class="title1">TTHG</p>
					    <p class="title2">专业的汽车网站</p>
					 	<a href="#" class="btn" >Join US</a>
                       	
    <div id="LoginBox">

        <div class="login-top">
                <div class="row1">
         <a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">×</a>
        </div>
		<h1>USER LOGIN</h1>
		<form action="tthg" method="post">
			<input type="text" name="username" value="UserName" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'User Id';}">
			<input type="password" name="password" value="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'password';}">
	  
	    <div class="forgot">
	    	
	    	<input type="submit" value="Login" >
	    </div>
	     </form>
	</div>
	<div class="login-bottom">
		<h3>New User &nbsp;<a href="regist.jsp">Register</a>&nbsp Here</h3>
	</div>
</div>	
    </div>

	<script type="text/javascript">
	$(function ($) {
		//弹出登录
		$(".btn").hover(function () {
			$(this).stop().animate({
				opacity: '1'
			}, 600);
		}, function () {
			$(this).stop().animate({
				opacity: '0.6'
			}, 1000);
		}).on('click', function () {
			$("body").append("<div id='mask'></div>");
			$("#mask").addClass("mask").fadeIn("slow");
			$("#LoginBox").fadeIn("slow");
		});
		//
		//按钮的透明度
		$("#loginbtn").hover(function () {
			$(this).stop().animate({
				opacity: '1'
			}, 600);
		}, function () {
			$(this).stop().animate({
				opacity: '0.8'
			}, 1000);
		});
		//文本框不允许为空---按钮触发
		$("#loginbtn").on('click', function () {
			var txtName = $("#txtName").val();
			var txtPwd = $("#txtPwd").val();
			if (txtName == "" || txtName == undefined || txtName == null) {
				if (txtPwd == "" || txtPwd == undefined || txtPwd == null) {
					$(".warning").css({ display: 'block' });
				}
				else {
					$("#warn").css({ display: 'block' });
					$("#warn2").css({ display: 'none' });
				}
			}
			else {
				if (txtPwd == "" || txtPwd == undefined || txtPwd == null) {
					$("#warn").css({ display: 'none' });
					$(".warn2").css({ display: 'block' });
				}
				else {
					$(".warning").css({ display: 'none' });
				}
			}
		});
		//文本框不允许为空---单个文本触发
		$("#txtName").on('blur', function () {
			var txtName = $("#txtName").val();
			if (txtName == "" || txtName == undefined || txtName == null) {
				$("#warn").css({ display: 'block' });
			}
			else {
				$("#warn").css({ display: 'none' });
			}
		});
		$("#txtName").on('focus', function () {
			$("#warn").css({ display: 'none' });
		});
		//
		$("#txtPwd").on('blur', function () {
			var txtName = $("#txtPwd").val();
			if (txtName == "" || txtName == undefined || txtName == null) {
				$("#warn2").css({ display: 'block' });
			}
			else {
				$("#warn2").css({ display: 'none' });
			}
		});
		$("#txtPwd").on('focus', function () {
			$("#warn2").css({ display: 'none' });
		});
		//关闭
		$(".close_btn").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
			$("#LoginBox").fadeOut("fast");
			$("#mask").css({ display: 'none' });
		});
	});
	</script>
					</div>
					<div class="clear"></div> 
			    </div>
		    </div>
		</div>
       
		<div class="content-bottom">
			<div class="wrap">
				<p class="title3">个性体贴，追求卓越，优质服务。</p>
				<div class="section group">
				<div class="lsidebar span_1_of_bottom">
					<div class="thumb-pad4">
	                    <div class="thumbnail">
	                     	<figure><img src="images/bolt.png" alt=""/><em class="sp1"></em></figure>
	                    </div>
                    </div>
				</div>
				<div class="cont span_2_of_bottom">
				      <p>购车够服务,购车前后我们都是笑脸迎人。购车选我，服务随你。购的不仅仅是车，更是服务。购买省心，拥有放心，享受安心，售后称心！</p>
					  <p>你的惠顾是我的荣耀，我的服务是您的一切。你来或者不来，服务就在这里，只为等你。你拥有我们的汽车，我们拥有你的微笑。您的满意，我们的目标。</p>
				      <p>我们的车很快，我们的服务更快！我们的汽车，满足你的需求。无论您走多远，我们对您的呵护始终如一。</p>
				      <p>销售与车速齐飞，服务与质量双赢。性能引爆销量，品质造就美（信）誉。做4S精品店，带给你最好的享受。 </p>
			          <a href="about.jsp" class="btn2">more</a>				
				</div>	
				<div class="clear"></div> 			
		       </div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="wrap">
		<div class="footer-top">
				<div class="col_1_of_4 span_1_of_4">
					<h3>信息与服务</h3>
					<ul class="first">
						<li><a href="contact.jsp">与我们联系</a></li>
						<li><a href="about.jsp">条款和条件</a></li>
						<li><a href="about.jsp">法律告知</a></li>
					</ul>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3>类别导航</h3>
				  <ul class="first">
						<li><a href="show.jsp">汽车展示</a></li>
						<li><a href="index.jsp">热销排行</a></li>
						<li><a href="show.jsp">汽车分类</a></li>
					</ul>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h3>公司信息</h3>
					<ul class="first">
						<li><a href="order.jsp">预约登记</a></li>
						<li><a href="contact.jsp">公司地址</a></li>
						<li><a href="show.jsp">价格查询</a></li>
				    </ul>
				</div>
				<div class="col_1_of_4 span_1_of_4 footer-lastgrid">
					<h3>与我们联系</h3>
					<ul class="last">
							<li><span>+91-123-456789</span></li>
							<li><span>+00-123-000000</span></li>
						</ul>
			    </div>
				<div class="clear"></div> 
		</div>
		<div class="copy">
			<p>Copyright &copy; 2017.Company name All rights reserved <a target="_blank" href="">&nbsp;Thoroughly To Remould News</p>
		</div>
	</div>
</div>

</body>
</html>

    	
    	
            
