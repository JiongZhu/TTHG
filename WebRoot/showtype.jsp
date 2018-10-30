<!--<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>-->
<!DOCTYPE HTML>
<html>
<head>
<title>品牌展示</title>
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
                   <li class="has-sub "><a href="about.jsp"><span>ABOUT</span></a></li>
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
                   <a href="selectaction!showBrand" class="heading4"><p style="color:#FFF ; padding-right:0px;"><img src="images/arrow.png"/> 车辆展示</p></a> 
        </div>       
    <div id="mainContent">
    	<h2 style="color:#fff"><img src="images/comment.png"/><s:property value="carBrand"/></h2>
    	
         <s:iterator value="#session.vlist" var="var">
                <div class="shopId">
        	<div class="shopIdHeader">
        	<form action="order!tiaozhuan"  method="post"> 
            	<div class="shopIdPrice">RMB&nbsp;&nbsp;<s:property  value="#var.GuidePrice"/>万</div>
                <div><input type="submit" value="预约" class="buttonbuy" /></div>
            </form>
            </div>
            <a href="selectaction!showdetail?carname=<s:property  value="#var.VehicleName"/>" target="_blank">
           <div class="img"><img src="images/<s:property  value="#var.Photo"/>.png" /></div>
           <div class="shopIdTitle"><s:property  value="#var.VehicleName"/></div></a>
        </div>
          </s:iterator>   
    </div>
                    
  
					  </div>

							<div class="clear"></div>
                                                            <center>
    
    <a class="link" href="selectaction!showType?carBrand=<s:property value="carBrand"/>&pageNo=1">第一页</a>&nbsp;&nbsp;&nbsp;
    <a class="link" href="selectaction!showType?carBrand=<s:property value="carBrand"/>&pageNo=<s:property value="prevPage"/>">上一页</a>&nbsp;&nbsp;&nbsp; 
    <a class="link" href="selectaction!showType?carBrand=<s:property value="carBrand"/>&pageNo=<s:property value="nextPage"/>">下一页</a>&nbsp;&nbsp;&nbsp; 
    <a class="link" href="selectaction!showType?carBrand=<s:property value="carBrand"/>&pageNo=<s:property value="fenleisunPage"/>">最后一页</a>&nbsp;&nbsp;&nbsp; 
   
    </center>
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