package cn.smbms.service.bill;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;

public interface BillService {
	/**
	 * 增加订单
	 */
	public int add(Bill bill);


	/**
	 * 通过条件获取订单列表-模糊查询-billList
	 */
	public List<Bill> getBillList(String queryProductName,Integer queryProviderId,Integer queryIsPayment,Integer currentPageNo,Integer pageSize);
	
	/**
	 * 通过billId删除Bill
	 */
	public int deleteBillById(String delId,String providerId);
	
	
	/**
	 * 通过billId获取Bill
	 */
	public Bill getBillById(String id);
	
	/**
	 * 修改订单信息
	 */
	public int modify(Bill bill);

    //查询订单总记录数
	public int getBillCount(String productName, Integer providerId,
			Integer isPayment);
	
}
