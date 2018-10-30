package com.tthg.dao;

import java.util.List;

import com.tthg.entity.Invoice;
/**
 * 开具发票接口
 * @author 朱宣羽  编写者
 * @since 2016-12-12 编写时间
 *
 */
public interface IInvoiceDAO {
	void add(Invoice invoice);//新增发票
	void delete(int[] ids);//删除发票
	void update(Invoice invoice);//修改发票
	List<Invoice> searchAll();//全查发票
	List<Invoice> searchInvoice(Invoice invoice);//根据条件(发票编号、订单编号、开具时间)组合查询发票
}
