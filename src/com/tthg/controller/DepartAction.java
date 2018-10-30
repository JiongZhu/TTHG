package com.tthg.controller;

import java.io.IOException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tthg.entity.Department;
import com.tthg.service.IDepartService;
import com.tthg.service.IPageService;
/**
 * 部门action
 * @author 葛康  编写者
 * @since 2016-12-15 编写时间
 *
 */
@Controller
public class DepartAction {
	private Department depart;//部门实体
	private JSONObject dmJson;//jsonobject

	private String rows;// 每页显示的记录数  
	private String page;// 当前第几页

	@Autowired
	private IDepartService departService;//部门服务层接口
	@Autowired
	private IPageService pageService;//分页接口
	
	public DepartAction(){
		
	}

	//get和set
	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}
	
	public IDepartService getDepartService() {
		return departService;
	}

	public void setDepartService(IDepartService departService) {
		this.departService = departService;
	}

	public JSONObject getDmJson() {
		return dmJson;
	}

	public void setDmJson(JSONObject dmJson) {
		this.dmJson = dmJson;
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
		return this.preShowAllDm();
	}

	//部门添加操作
	public String preAddDm(){
		HttpServletRequest request=ServletActionContext.getRequest();//创建HttpServletRequest对象
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//json转化
		depart=(Department)JSONObject.toBean(jsobj,Department.class);//反序列化
		depart.setId(-1);
		System.out.println("sdsfffse"+depart.getId());
		boolean result=departService.insertDepart(depart);//执行添加操作
	    //传递后台数据
       String jsonStr="";
       if(result){
    	   jsonStr="{\"success\":true}";
       }else{
    	   jsonStr="{\"success\":false}";
       }
       dmJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
		
	}

	//部门删除操作
	public String preRemoveDm() throws IOException{
		HttpServletRequest request=ServletActionContext.getRequest();// 创建HttpServletRequest对象
		String ids=request.getParameter("ids");//获取页面参数
		String[] idStr = ids.split(",");//分离ids
		int[] id=new int[idStr.length];
		System.out.println(id.length);
		for(int i=0;i<id.length;i++){
			id[i]=Integer.parseInt(idStr[i]);//转变类型
		}
       boolean result=departService.deleteDepart(id);//执行删除操作
       //传递后台数据
       String jsonStr="";
       if(result){
    	   jsonStr="{\"success\":true}";
       }else{
    	   jsonStr="{\"success\":false}";
       }
       dmJson= JSONObject.fromObject(jsonStr);//json转换
		System.out.println(jsonStr);

		return "success";
	}

	//部门修改操作
	public String preUpdateDm(){
		HttpServletRequest request=ServletActionContext.getRequest();// 创建HttpServletRequest对象
		JSONObject jsobj=JSONObject.fromObject(request.getParameter("str"));//转换成json	
		depart=(Department)JSONObject.toBean(jsobj,Department.class);	//json实例化	
		boolean result=departService.updateDepart(depart.getId(), depart);//执行修改操作
		//传递后台数据
       String jsonStr="";
       if(result){
    	   jsonStr="{\"success\":true}";
       }else{
    	   jsonStr="{\"success\":false}";
       }
       dmJson= JSONObject.fromObject(jsonStr);//json转换
		return "success";
	}

	//部门全查操作
	public String preShowAllDm(){
		List<Department> dmList=departService.getAllDepart();//部门全查
		String url="from Department depart";
		List list=pageService.getList(url,page, rows);//调用分页方法
		//过滤
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"users"});//过滤关联实体
		System.out.println(list.size());
		Map map=new HashMap();
		map.put("total", dmList.size());
		map.put("rows", list);

		dmJson= JSONObject.fromObject(map,jsonConfig); //转换成json	  

		System.out.println(dmJson);
		return "success";
	}

	//根据部门编号查询
	public String preGetDmByNo(){
		HttpServletRequest request=ServletActionContext.getRequest();// 创建HttpServletRequest对象
		String deno=request.getParameter("deNo");//获取页面参数
		if ("".equals(deno)||null==deno) {
			this.preShowAllDm();
		}else{
			//过滤
			JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
			jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
			jsonConfig.setExcludes(new String[]{"users"});
			Map map=new HashMap();
			List<Department> list=departService.getDepartByNo(deno);//执行查询操作
			
			map.put("total",list.size());
			map.put("rows", list);
	
			dmJson=JSONObject.fromObject(map,jsonConfig);//转换成json	
		}
		return "success";
	}

}
