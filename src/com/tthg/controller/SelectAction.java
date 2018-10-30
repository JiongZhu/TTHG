package com.tthg.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tthg.entity.Vehicle;
import com.tthg.entity.VehicleBrand;
import com.tthg.service.SelectServiceDao;
/**
 * 车辆展示,分类搜索action
 * @author 吴玉双  编写者
 * @since 2016-12-19 编写时间
 */
@Controller
public class SelectAction extends ActionSupport{ 
       @Autowired 
       private SelectServiceDao selectServiceDao;
       private String selecttext;
       private String carBrand;
       private String carname;
       
       public SelectAction(){
    	   
    	   
       }
    private int pageNum=4;//每页显示得记录数；
   	private int pageNo;	//当前页编号；
   	private int prevPage;//上一页；
   	private int nextPage;	//下一页；
 //  	private int sumPage;//总页数；
   	private int fenleisunPage;//分类总页数
      // private PaginDao pagindao;

   
  
   		//获取总页数
   		//pageNum=4;
   		//paginServiceDao=new PaginServiceDaoImp();
   		//sumPage=paginServiceDao.sumPage(pageNum);
   	    //pagindao=new PaginDaoImp();	
     		
   	
   	public int getPageNum() {
   		return pageNum;
   	}
   	public int getFenleisunPage() {
		return selectServiceDao.flsumPage(pageNum, carBrand);
	}
	public void setFenleisunPage(int fenleisunPage) {
		this.fenleisunPage = fenleisunPage;
	}
	public void setPageNum(int pageNum) {
   		this.pageNum = pageNum;
   	}
   	public int getPageNo() {
   		return pageNo;
   	}
   	public void setPageNo(int pageNo) {
   		this.pageNo = pageNo;
   	}
   	public int getPrevPage() {
   		//上一页设置
   		if(pageNo<=1){
   			prevPage=1;
   		}
   		else {
   			prevPage=pageNo-1;	
   			}
   		
   		return prevPage;
   	}
   	public void setPrevPage(int prevPage) {
   		this.prevPage = prevPage;
   	}
   	public int getNextPage() {
   		//下一页设置
   		if(pageNo>=fenleisunPage){
   			nextPage=fenleisunPage;	
   		}
   		else{
   			nextPage=pageNo+1;
   			
   		}
   		return nextPage;
   	}
   	public void setNextPage(int nextPage) {
   		this.nextPage = nextPage;
   	}
   
  
   	public void init() {

   	//	paginServiceDao = new PaginServiceDaoImp();
   		fenleisunPage = selectServiceDao.sumPage(pageNum);

   	}

	public String getCarname() {
		return carname;
	}


	public void setCarname(String carname) {
		
		try {
			String ss=new String(carname.getBytes("ISO-8859-1"),"utf-8");
			this.carname = ss;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public String getCarBrand() {
		
	
		return carBrand;
	}


	public void setCarBrand(String carBrand) {
		try {
			String ss=new String(carBrand.getBytes("ISO-8859-1"),"utf-8");
			this.carBrand = ss;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public SelectServiceDao getSelectServiceDao() {
		return selectServiceDao;
	}

	public void setSelectServiceDao(SelectServiceDao selectServiceDao) {
		this.selectServiceDao = selectServiceDao;
	}

	public String getSelecttext() {
		return selecttext;
	}

	public void setSelecttext(String selecttext) {
		this.selecttext = selecttext;
	}
       public String execute()throws Exception{
//    	  
//    	   List<Vehicle> vlist= selectServiceDao.selectByCarname(selecttext);
//    	   ActionContext act=ActionContext.getContext();
//    	   Map session=act.getSession();
//    	   session.put("userlist", vlist);
//    	   
    	   return SUCCESS;
       }
       
       
       public String showBrand(){
    	   List<VehicleBrand> blist=selectServiceDao.showBrand();
    	   ActionContext act=ActionContext.getContext();
    	   Map session=act.getSession();
    	   session.put("blist", blist);   
    	   System.out.println(blist.get(0).getBrandName());
    	   
    	   
    	   return SUCCESS;
       }
       public String show(){
    	   System.err.println("action中show方法");
    	   List<VehicleBrand> clist=selectServiceDao.showBrand();
    	   ActionContext act=ActionContext.getContext();
    	   Map session=act.getSession();
    	   session.put("clist", clist);   
    	   System.out.println(clist.get(0).getBrandName());   
    	   return "success2";
       }
       public String showType(){
//    	   try {
//			String ss=new String(carBrand.getBytes("ISO-8859-1"),"utf-8"); 
   		  this.init();
   		   if(pageNo<=1){
   			pageNo=1;		
   	       	}
   		   if(pageNo>=fenleisunPage){
   			  pageNo=fenleisunPage;
   		    }		
    	   System.out.println("进入action中showType方法+carBrand="+carBrand);
    	   List<Vehicle> vlist=selectServiceDao.getCarByPageNo(pageNum, pageNo, carBrand);
    	   if(vlist.size()>0){
    		   ActionContext act=ActionContext.getContext();
        	   Map session=act.getSession();
        	   session.put("vlist", vlist);
        	
        	   System.out.println(vlist.get(0).getBrand());  
    	       return "showtype";}
    	   else{
    		   
    		   return "notype";
    	   }
//    	   } catch (UnsupportedEncodingException e) {
//   			// TODO Auto-generated catch block
//   			e.printStackTrace();
//   		}
       }
	   public String showdetail(){
		   System.out.println("进入action中showdetail方法");
		   System.out.println("carname="+carname);
		   Vehicle v=selectServiceDao.showVehicle(carname);
		   
		   ActionContext act=ActionContext.getContext();
		   Map  session=act.getSession();
		   session.put("v", v);		   
		   return "showdetail";
	   }
	   public String select(){
		   System.out.println("进入action中select方法");
		   List<Vehicle> list=selectServiceDao.selectByCarname(selecttext);
		   ActionContext act=ActionContext.getContext();
		   Map session=act.getSession();
		   session.put("list", list);
		   if(list==null){
			   
			   session.put("list", "搜索结果");
			   return "notype";
		   }
		   return "success3";
	   }
}
