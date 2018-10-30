package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Callback;
/**
 * 客户回访接口
 * @author 朱宣羽  编写者
 * @since 2016-12-12 编写时间
 *
 */
public interface ICallbackDAO {
	void add(Callback callback);//新增客户回访记录
	void delete(int[] ids);//删除客户回访记录
	void update(Callback callback);//修改客户回访记录
	List<Callback> searchAll();//全查客户回访记录
	List<Callback> searchCallback(Callback callback);//根据条件(客户姓名、回访时间)组合查询客户回访记录
}
