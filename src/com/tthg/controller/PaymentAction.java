package com.tthg.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tthg.entity.Payment;
import com.tthg.service.IPageService;
import com.tthg.service.IPaymentService;
import com.tthg.util.DateJsonValueProcessor;
/**
 * 客户支付action
 * @author 葛康  编写者
 * @since 2016-12-25 编写时间
 *
 */
@Controller
public class PaymentAction {
	private Payment payment;//支付实体
	private JSONObject payJson;//json
	private JSONArray payJsonA;
	private String rows;// 每页显示的记录数  
	private String page;// 当前第几页

	@Autowired
	private IPaymentService paymentService;//支付服务层接口
	@Autowired
	private IPageService pageService;//分页服务层接口
	
	public PaymentAction(){
		
	}
	//set和get
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public IPaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(IPaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public JSONObject getPayJson() {
		return payJson;
	}
	public void setPayJson(JSONObject payJson) {
		this.payJson = payJson;
	}
	public JSONArray getPayJsonA() {
		return payJsonA;
	}
	public void setPayJsonA(JSONArray payJsonA) {
		this.payJsonA = payJsonA;
	}
	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public IPageService getPageService() {
		return pageService;
	}
	public void setPageService(IPageService pageService) {
		this.pageService = pageService;
	}
	public String execute(){
		return this.preShowAllPa();
	}
	
	//添加支付记录
	public String preAddPa(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//获取页面参数
		//日期序列化
		System.out.println("request.getParameter"+request.getParameter("str"));
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		//反序列化
		payment=(Payment)JSONObject.toBean(jsobj,Payment.class);
		System.out.println("request.getParameter"+request.getParameter("str"));
		
		boolean result=paymentService.insertPayment(payment,1);
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
        payJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
	}
	//删除支付记录
	public String preRemovePa(){
		HttpServletRequest request=ServletActionContext.getRequest(); //创建HttpServletRequest对象
		String ids=request.getParameter("ids");//获取页面参数
		String[] idStr = ids.split(",");//分离参数
		int[] id=new int[idStr.length];
		System.out.println(id.length);
		for(int i=0;i<id.length;i++){
			id[i]=Integer.parseInt(idStr[i]);//属性转化
		}
		boolean result=paymentService.deletePayment(id);
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
        payJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
	}
	//修改支付记录
	public String preUpdatePa(){
		//创建HttpServletRequest对象
		HttpServletRequest request=ServletActionContext.getRequest();
		//转换json
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));
		//日期序列化
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
		//json转换时间格式
		payment=(Payment)JSONObject.toBean(jsobj,Payment.class);//json转化		

		boolean result=paymentService.updatePayment(1, payment);
		//传递后台数据
        String jsonStr="";
        if(result){
    	   jsonStr="{\"success\":true}";
        }else{
    	   jsonStr="{\"success\":false}";
        }
        payJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
	}
	//全查支付记录
	public String preShowAllPa(){
		String url="from Payment pay";
		List<Payment> paymentList=paymentService.getAllPayment();//执行全查操作

		List list=pageService.getList(url,page, rows);//分页操作

		//为多余属性赋值
		List uList=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Payment pay=(Payment) list.get(i);
			pay.setReNo(pay.getRepair().getReNo());
			uList.add(pay);
		}
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"repair"});//过滤多余属性（维修登记实体）
		//日期序列化
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));//日期格式化

		//向页面传值
		Map map=new HashMap();
		map.put("total", paymentList.size());
		map.put("rows", uList);

		payJson= JSONObject.fromObject(map,jsonConfig); //转换成json  

		System.out.println(payJson);
		return "success";
	}

	//复合查询
	public String preSelect(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		HttpSession se=request.getSession();//创建Session对象
		//执行复合查询
		List paymentList=paymentService.getPaymentByComposition(payment);
		String url=(String) se.getAttribute("hqlStr");
		//分页查询
		List list=pageService.getList(url,page, rows);
		System.out.println("list长度"+list.size());
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"repair"});
		//日期格式化
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		//为多余属性赋值
		List evList=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Payment pay=(Payment) list.get(i);
			pay.setReNo(pay.getRepair().getReNo());
			evList.add(pay);
		}
		//向页面传值
		Map map=new HashMap();
		map.put("total",paymentList.size());
		map.put("rows", evList);

		payJson=JSONObject.fromObject(map,jsonConfig);//转换成json 
		return "success";
	}


}
