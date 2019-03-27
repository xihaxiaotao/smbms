package cn.smbms.dao.bill;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;

public interface BillDao {
	/**
	 * 增加订单
	 */
	public int add(Bill bill);


	/**
	 * 通过查询条件获取订单列表-模糊查询-getBillList
	 */
	public List<Bill> getBillList( @Param("productName")String queryProductName, @Param("providerId")Integer queryProviderId, @Param("isPayment")Integer queryIsPayment,
			                      @Param("currentPageNo")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	
	/**
	 * 通过delId,或者供应商id,删除订单
	 */
	public int deleteBillById(@Param("delId")String delId,@Param("providerId")String providerId); 
	
	
	/**
	 * 通过billId获取Bill
	 */
	public Bill getBillById(String id); 
	
	/**
	 * 修改订单信息
	 */
	public int modify(Bill bill);

	/**
	 * 根据供应商ID查询订单数量
	 */
	public int getBillCountByProviderId(String providerId);

	//根据条件,查询订单数量
	public int getBillCount(@Param("productName")String productName,@Param("providerId") Integer providerId,
			@Param("isPayment")Integer isPayment);
}
