<!--<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>-->
<!DOCTYPE HTML>
<html>
<head>
<title>分类展示</title>
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
					<div class="col span_1_of_about">>
    <!-- end #sidebar1 -->
    <!-- begin #mainContent -->
    
    <div id="mainContent">
      <div >
      <form action="#" method="post">
                 
                  
                         <input type="text"  class="search-input" name="bname" value="爱车搜索" onfocus="javascript:if(this.value=='文学儿童图书折扣优惠')this.value='';">
                         <input type="submit"  class="buttons" value="搜索"/>

              </form>
              </div>
    	<h2 style="color:#fff"><img src="images/comment.png">车型导航</h2>
    
          <div class="col_1_of_4 span_1_of_4">
					
					<ul class="first">
					<s:subset source="#session.clist" start="0" count="8">
               <s:iterator  var="var" >
						<li><a href="selectaction!showType?carBrand=<s:property value="#var.BrandName"/>"><s:property value="#var.BrandName"/></a></li>
	           </s:iterator>  
                 </s:subset>
					</ul>
				</div>
				
                    <div class="col_1_of_4 span_1_of_4">
					
					<ul class="first">
					<s:subset source="#session.clist" start="8" count="8">
               <s:iterator  var="var" >
						<li><a href="selectaction!showType?carBrand=<s:property value="#var.BrandName"/>"><s:property value="#var.BrandName"/></a></li>
	           </s:iterator>  
                 </s:subset>
					</ul>
				</div>
				 <div class="col_1_of_4 span_1_of_4">
					
					<ul class="first">
					<s:subset source="#session.clist" start="16" >
               <s:iterator  var="var" >
						<li><a href="selectaction!showType?carBrand=<s:property value="#var.BrandName"/>"><s:property value="#var.BrandName"/></a></li>
	           </s:iterator>  
                 </s:subset>
					</ul>
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