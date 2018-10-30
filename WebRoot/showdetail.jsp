<!--<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>-->
<!DOCTYPE HTML>
<html>
<head>
<title>汽车信息展示</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
				   <li class="active"><a href="selectaction!showBrand"><span>SHOW</span></a></li>
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
     <div class="main">
		<div class="content-top">
			<div class="wrap">
				<div class="about">
				<div class="about-top">
					<div class="col span_1_of_about">

    <!-- end #sidebar1 -->
    <!-- begin #mainContent -->
         <div>
                  <a href="selectaction!showType?carBrand=<s:property value="#session.v.Brand"/>" class="heading4"><p style="color:#FFF ; padding-right:0px;"><img src="images/arrow.png"/> 返回</p></a> 
      </div>       
    <div id="mainContent">
    	<h2 style="color:#fff"><img src="images/comment.png"/>&nbsp;&nbsp;<s:property value="#session.v.VehicleName"/></h2>
    	
      <div class="col1 span_2_of_contact">
					  <div class="contact-form">
						    <form method="post" action="#">
						    	<div>
							    	<span><label>Brand&nbsp;品牌:&nbsp;<s:property value="#session.v.Brand"/></label></span>	
							    </div>
							    <div>
							  <span><label>GuidePrice&nbsp;指导价格:&nbsp;<s:property value="#session.v.GuidePrice"/>万</label></span>
							    </div>
                                <div>
							    	<span><label>MJ&nbsp;年款:&nbsp;<s:property value="#session.v.MJ"/>年</label></span>	
							    </div>
							    <div>
							  <span><label>Manufacturea&nbsp;厂商:<s:property value="#session.v.Manufacturea"/></label></span>
							    </div>
                               <div>
							    	<span><label>Rank&nbsp;级别:&nbsp;<s:property value="#session.v.Rank"/></label></span>	
							    </div>
							    <div>
							  <span><label>Engine&nbsp;引擎:&nbsp;<s:property value="#session.v.Engine"/></label></span>
							    </div>
                                <div>
						<span><label>Gearbox&nbsp;变速箱:&nbsp;<s:property value="#session.v.Gearbox"/></label></span>	
							    </div>
							    <div>
							  <span><label>Lwh&nbsp;长宽高:&nbsp;<s:property value="#session.v.Lwh"/></label></span>
							    </div>
                              <div>
						<span><label>MaxSpeed&nbsp;最快速度:&nbsp;<s:property value="#session.v.MaxSpeed"/>km/h</label></span>	
							    </div>
							    <div>
							  <span><label>FuelConsumption&nbsp;耗油量:&nbsp;<s:property value="#session.v.FuelConsumption"/>L</label></span>
							    </div>
                                 <div>
						<span><label>Warranty&nbsp;保修期:&nbsp;<s:property value="#session.v.Warranty"/>年</label></span>	
							    </div>
                                  <div>
						<span><label>Color&nbsp;颜色:&nbsp;<s:property value="#session.v.Color"/></label></span>	
							    </div>
							    <div>
							  <span><label>Displacement&nbsp;排量:&nbsp;<s:property value="#session.v.Displacement"/>T</label></span>
							    </div>
							    <div>
							  <span><label>NumberofCylinders&nbsp;气缸数:&nbsp;<s:property value="#session.v.NumberofCylinders"/></label></span>
							    </div>
							   
						    </form>
                            
                            
                            
                            <form method="post" action="#" style="float:right; margin-top:-400px; width:400px; height:350px">
						    <img src="images/<s:property value="#session.v.Photo"/>.png"/>
						    </form>
						</div>
                    
                    
                    
             
	  				</div>
         
    </div>

            
					  </div>
							<div class="clear"></div>
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