package com.tthg.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.opensymphony.xwork2.ActionSupport;
import com.tthg.entity.Appointment;
import com.tthg.entity.Vehicle;
import com.tthg.service.IAppointmentServiceDAO;
import com.tthg.service.IVehicleServiceDAO;
import com.tthg.util.DateJsonValueProcessor;
import com.tthg.util.ReadJsonFile;

@Controller
public class AppointmentAction extends ActionSupport {
	// 对象实例
	private Appointment appointment;
	// ajax返回参数
	private JSONObject jsonObj;//json对象
	private JSONArray jsonArr;//json数组
	@Autowired
	private IAppointmentServiceDAO isd;
	@Autowired
	private IVehicleServiceDAO ivd;
	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}

	public JSONArray getJsonArr() {
		return jsonArr;
	}

	public void setJsonArr(JSONArray jsonArr) {
		this.jsonArr = jsonArr;
	}

	public IAppointmentServiceDAO getIsd() {
		return isd;
	}

	public void setIsd(IAppointmentServiceDAO isd) {
		this.isd = isd;
	}

	public IVehicleServiceDAO getIvd() {
		return ivd;
	}

	public void setIvd(IVehicleServiceDAO ivd) {
		this.ivd = ivd;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	// 新增试乘试驾预约
	public String addAppointment() {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsobj = JSONObject.fromObject(request.getParameter("str"));//获取前台表单封装的json数据
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));//处理前台封装过来的日期格式
		appointment = (Appointment) JSONObject.toBean(jsobj, Appointment.class);//将json数据转换为实体
		appointment.setCarStyleId(Integer.parseInt(appointment.getCname()));//处理外键
		appointment.setIntentionId(appointment.getIid());//处理外键
		appointment.setState("未审核");
		isd.add(appointment);
		return SUCCESS;
	}

	// 修改试乘试驾预约信息
	public String updateAppointment() {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsobj = JSONObject.fromObject(request.getParameter("str"));//获取前台表单封装的json数据
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));//处理前台封装过来的日期格式
		appointment = (Appointment) JSONObject.toBean(jsobj, Appointment.class);//将json数据转换为实体
		appointment.setCarStyleId(Integer.parseInt(appointment.getCname()));//处理外键
		appointment.setIntentionId(appointment.getIid());//处理外键
		isd.update(appointment);
		HttpSession session=request.getSession();
		session.setAttribute("zstate",appointment);
		return SUCCESS;
	}

	// 删除试乘试驾预约
	public String deleteAppointment() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");//获取前台传过来的选定的记录的id数组
		String[] idStr = ids.split(",");
		int[] id = new int[idStr.length];
		for (int i = 0; i < id.length; i++) {
			id[i] = Integer.parseInt(idStr[i]);//转换为int数组
		}
		isd.delete(id);
		return SUCCESS;
	}
	//销售顾问预约登记审核通过提示信息
	public String zState(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Map map=new HashMap();
		Appointment app=(Appointment)session.getAttribute("zstate");
		String str=app.getState();
		if(str.equals("已审核")){
			map.put("result", true);
			map.put("msg","编号为"+app.getAppointmentNo()+"的预约登记已审核通过！" );
		}else{
			map.put("result", false);
		}
		jsonObj=JSONObject.fromObject(map);
		session.removeAttribute("zstate");
		return SUCCESS;
	}
	// 多条件组合查询
	public String searchAppointment() {
		Map map = new HashMap();
		List list = isd.searchAppointment(appointment);
		String path = ServletActionContext.getServletContext().getRealPath(
				"/Background/sales/appointment")
				+ "\\" + "AppointmentTitle.json";//从文件读取前台datagrid需要动态显示的表头字段
		JSONArray title = new ReadJsonFile(path).getJsonArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));// 数据库向前台数据日期格式处理
		map.put("total", list.size());//总的记录数，用于前台分页
		map.put("rows", list);//记录的详细内容
		map.put("title", title);//表头字段名及列属性设置
		jsonConfig.setExcludes(new String[]{"vehicle","customer"});//设置实体字段的过滤，用于解决hibernate建立实体间联系问题
		jsonObj = JSONObject.fromObject(map, jsonConfig);//将数据封装为JSONObject数据类型
		return SUCCESS;
	}

	// 全查试乘试驾预约
	public String appointmentList() {
		Map map = new HashMap();
		List list = isd.searchAll();
		String path = ServletActionContext.getServletContext().getRealPath(
				"/Background/sales/appointment")
				+ "\\" + "AppointmentTitle.json";//从文件读取前台datagrid需要动态显示的表头字段
		JSONArray title = new ReadJsonFile(path).getJsonArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));// 数据库向前台数据日期格式处理
		map.put("total", list.size());//总的记录数，用于前台分页
		map.put("rows", list);//记录的详细内容
		map.put("title", title);//表头字段名及列属性设置
		jsonConfig.setExcludes(new String[]{"vehicle","customer"});//设置实体字段的过滤，用于解决hibernate建立实体间联系问题
		jsonObj = JSONObject.fromObject(map, jsonConfig);//将数据封装为JSONObject数据类型
		return SUCCESS;
	}
	//处理前台下拉列表key-value的对应问题
	public String combobox(){
		List<Vehicle> vlist=ivd.searchAll();
		List<Map<String,Object>> clist=new ArrayList<Map<String,Object>>();	
		for(int i=0;i<vlist.size();i++){
			Vehicle v=vlist.get(i);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("vid", v.getId());
			map.put("vname",v.getVehicleName());//前台下拉列表combobox数据，k-v对应
			clist.add(map);
		}
		jsonArr=JSONArray.fromObject(clist);//将数据封装为JSONArray数据类型
		return INPUT;
	}
}
