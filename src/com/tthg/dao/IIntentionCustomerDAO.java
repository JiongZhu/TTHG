package com.tthg.dao;

import java.util.List;

import com.tthg.entity.IntentionCustomer;
/**
 * 意向客户接口
 * @author 朱宣羽  编写者
 * @since 2016-12-12 编写时间
 *
 */
public interface IIntentionCustomerDAO {
	void add(IntentionCustomer customer);//新增意向客户
	void delete(int[] ids);//删除意向客户
	void update(IntentionCustomer customer);//修改意向客户
	List<IntentionCustomer> searchAll();//全查意向客户
	List<IntentionCustomer> searchCustomer(IntentionCustomer customer);//根据条件(客户姓名、身份证号、意向车型)组合查询意向客户
}
