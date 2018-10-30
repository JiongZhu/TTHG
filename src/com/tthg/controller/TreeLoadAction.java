package com.tthg.controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tthg.entity.Tree;
import com.tthg.entity.User;
import com.tthg.service.ITreeServiceDAO;
@Controller
public class TreeLoadAction extends ActionSupport {
	private Tree tree;
	private JSONArray treeNodes;
	@Autowired
	private ITreeServiceDAO isd;
	public Tree getTree() {
		return tree;
	}
	public void setTree(Tree tree) {
		this.tree = tree;
	}
	public JSONArray getTreeNodes() {
		return treeNodes;
	}
	public void setTreeNodes(JSONArray treeNodes) {
		this.treeNodes = treeNodes;
	}
	public ITreeServiceDAO getIsd() {
		return isd;
	}
	public void setIsd(ITreeServiceDAO isd) {
		this.isd = isd;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	public String treeLoad(){
		User loginUser=(User)ActionContext.getContext().getSession().get("loginUser");
		int power=loginUser.getPower();
		List<Tree> treeList=new ArrayList<Tree>();
		int[] ids;
		int i;
		if(power==0){
			ids=new int[21];
			for(i=0;i<ids.length;i++){
				if(i<7){
					ids[i]=i+1;
				}else{
					ids[i]=i+26;
				}
			}
			treeList=isd.getById(ids);
		}
		if(power==1){
			ids=new int[27];
			for(i=0;i<ids.length-2;i++){
				if(i<9){
					ids[i]=i+1;
				}else if(i<11){
					ids[i]=i+2;
				}else if(i<14){
					ids[i]=i+3;
				}else if(i<21){
					ids[i]=i+4;
				}else{
					ids[i]=i+12;
				}
			}
			ids[25]=45;
			ids[26]=46;
			treeList=isd.getById(ids);
		}
		if(power==2){
			ids=new int[14];
			for(i=0;i<ids.length-3;i++){
				if(i<7){
					ids[i]=i+1;
				}else{
					ids[i]=i+18;
				}
			}
			ids[11]=32;
			ids[12]=45;
			ids[13]=46;
			treeList=isd.getById(ids);
		}
		if(power==3){
			int[] a={8,12,13,15,17,22,23,24,45,46};
			treeList=isd.getById(a);
		}
		if(power==4){
			int[] a={8,9,10,45,46};
			treeList=isd.getById(a);
		}
		if(power==5){
			int[] a={25,29,30,31,45,46};
			treeList=isd.getById(a);
		}
		treeNodes=JSONArray.fromObject(treeList);
		return SUCCESS;
	}
}
