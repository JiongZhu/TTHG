package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Subscription;
/**
 * 购车合同接口
 * @author 朱宣羽  编写者
 * @since 2016-12-12 编写时间
 *
 */
public interface ISubscriptionDAO {
	void add(Subscription subscription);//新增购车合同
	void delete(int[] ids);//删除购车合同
	void update(Subscription subscription);//修改购车合同
	List<Subscription> searchAll();//全查购车合同
	List<Subscription> searchSubscription(Subscription subscription);//根据条件(客户姓名、身份证号、签订日期)组合查询购车合同
}
