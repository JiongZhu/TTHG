<!--<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>-->
<!DOCTYPE HTML>
<html>
<head>
<title>试车预约</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />

<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">

</script>
</head>
<body>
<div class="header">	
  <div class="wrap"> 
	<div class="header-top">
		 <div class="logo">
			 <a href="index.html"><img src="images/logo.png" alt=""></a>
		 </div>
		  <div class="tui">
			欢迎<s:property value="username"/>进入"脱胎换骨"4s管理系统!<!--<a  class="tui" href="index.html">退出</a>-->
		
		 </div>
		 <div class="menu">
			<div id="cssmenu">
				<ul>
				   <li class="has-sub"><a href="index.jsp"><span>HOME</span></a></li>
				   <li class="has-sub"><a href="selectaction!showBrand"><span>SHOW</span></a></li>
				   <li class="active"><a href="order.jsp"><span>ORDER</span></a></li>
                   <li class="has-sub"><a href="about.jsp"><span>ABOUT</span></a></li>
				   <li class="has-sub"><a href="contact.jsp"><span>CONTACT</span></a></li>
				</ul>
            </div>
		  </div>	
		  <div class="clear"></div> 
	   </div>
   </div>	
</div>
     <div class="main">
		<div class="content-top">
			<div class="wrap">
				<div class="about">		
				<div class="section group">	
					<div class="col1 span_1_of_contact">
						<div class="company_address">
		      				<div class="contact-left">
								<h3>Address 1</h3>	
								<p>Telephone:18151283070</p>
								<p>Fax:1-22-5555</p>
								<a href="#"><p>Email:TTHG@mail.com</p></a>
							</div>
						    <div class="contact-left1">
								<h3>Address 2</h3>	
								<p>Telephone:1-22-5555</p>
								<p>Fax:1-22-5555</p>
								<a href="#"><p>Email:TTHG@mail.com</p></a>
						    </div>
							<div class="clear"></div> 	
						</div>
					</div>				
					<div class="col1 span_2_of_contact">
					  <div class="contact-form">
					  	<h3>Contact Us</h3>
						   <form method="post" action="order!order">
						    	<div>
							    	<span><label>Name</label></span>
							    	<span><input name="customerName" type="text" class="textbox"></span>
							    </div>
							    <div>
							    	<span><label>ScheduledPersonNum</label>
							    	</span>
						    	  <span><input name="scheduledPersonNum" type="text" class="textbox1"></span>
							    </div>
							    <div>
							    	<span><label>Telphone</label></span>
							    	<span><input name="telephone" type="text" class="textbox"></span>
							    </div>
							    <div>
							    	<span><label>ScheduledTime</label></span>
							    	<span><input name="scheduledTime" type="text" class="textbox"></span>
							    </div>
                         <div>
							    	<span><label>IntentionCarStyle</label></spyan>
							    	<span>
							    		<select name="cname">
							        <option value="宝马7系01">宝马7系01</option>
							    	<option value="宝马m4">宝马m4</option>
							    	<option value="奔驰">奔驰</option>
							    	<option value="帕萨特">帕萨特</option>
							    	<option value="宝马i8">宝马i8</option>
							    	<option value="奥迪a4">奥迪a4</option>
							    	<option value="奔驰GLC">奔驰GLC</option>
							    	</select>
                                    </span>
						
							    </div>
 <!-- 						    <div>
							    	<span><label>Age</label></span>
                                  <span> <select name="age">
		</select></span>--%>
         <div>
							    	<span><label>Age</label></span>
                                  <span> <select name="age">
		</select></span>
		<script type="text/javascript">
			$(function() {
			var arr = [];
				var min = 21;
				var max = 60;
				var now = 21;
				var $option;
				for(var i = min; i <= max; i++) {
				$option = '';
				$option += '<option value="'+i+'">'+i+'</option>';
					$('select[name=age]').append($option);
			}
			$('select[name=age]').val(now);
	})
		</script>

						    </div>
							  <div>
							    	<span><label>sex</label></span>
							    	<span><input name="radios" type="radio" class="radio" value="Male">Male
							    	<input name="radios" type="radio" class="radio" value="Female">Female</span>
							    </div>-->
							    <div>
							    	<span><label>Remarks</label></span>
							    	<span><textarea name="description"> </textarea></span>
							    </div>
							   <div>
							   		<input type="submit" value="提交">
							  </div>
						    </form>
						</div>
	  				</div>	
  				<div class="clear"></div> 			
			  </div>
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

    	
    	
            
