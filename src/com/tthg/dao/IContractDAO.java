package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Contract;
/**
 * 购车合同接口
 * @author 朱宣羽  编写者
 * @since 2016-12-12 编写时间
 *
 */
public interface IContractDAO {
	void add(Contract contract);//新增购车合同
	void delete(int[] ids);//删除购车合同
	void update(Contract contract);//修改购车合同
	List<Contract> searchAll();//全查购车合同
	List<Contract> searchContract(Contract contract);//根据条件(合同编号、签订时间)组合查询购车合同
}
