package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Reception;
/**
 * 展厅接待接口
 * @author 朱宣羽  编写者
 * @since 2016-12-12 编写时间
 *
 */
public interface IReceptionDAO {
	void add(Reception reception);//新增展厅接待
	void delete(int[] ids);//删除展厅接待
	void update(Reception reception);//修改展厅接待
	List<Reception> searchAll();//全查展厅接待
	List<Reception> searchReception(Reception reception);//根据条件(客户姓名、接待员、接待时间、审核状态)组合查询展厅接待
}
