package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Objection;
/**
 * 异议协调处理接口
 * @author 朱宣羽  编写者
 * @since 2016-12-12 编写时间
 *
 */
public interface IObjectionDAO {
	void add(Objection objection);//新增异议协调处理记录
	void delete(int[] ids);//删除异议协调处理记录
	void update(Objection objection);//修改异议协调处理记录
	List<Objection> searchAll();//全查异议协调处理记录
	List<Objection> searchObjection(Objection objection);//根据条件(客户姓名、处理时间)查询异议协调处理记录
}
