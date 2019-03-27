package cn.smbms.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.dao.bill.BillDao;
import cn.smbms.dao.provider.ProviderDao;
import cn.smbms.pojo.Provider;
@Service("ProviderService")
public class ProviderServiceImpl implements ProviderService {
	
	@Autowired
	private ProviderDao providerDao;
	@Autowired
	private BillDao  billDao;
	
	@Override
	public int add(Provider provider) {
		// TODO Auto-generated method stub
		return providerDao.add(provider);
	}
	@Override
	public List<Provider> getProviderList(String proName, String proCode,Integer currentPageNo,Integer pageSize) {
		// TODO Auto-generated method stub
		return providerDao.getProviderList(proName, proCode,currentPageNo,pageSize);
	}
	
	/**
	 * 业务：根据ID删除供应商表的数据之前，需要先去订单表里进行查询操作
	 * 若订单表中无该供应商的订单数据，则可以删除
	 * 若有该供应商的订单数据，则不可以删除
	 * 返回值billCount
	 * 1> billCount == 0  删除---1 成功 （0） 2 不成功 （-1）
	 * 2> billCount > 0    不能删除 查询成功（0）查询不成功（-1）
	 * 
	 * ---判断
	 * 如果billCount = -1 失败
	 * 若billCount >= 0 成功
	 */
	@Override
	public int deleteProviderById(String delId) {
		// TODO Auto-generated method stub
		return providerDao.deleteProviderById(delId);
	}
	
	@Override
	public Provider getProviderById(String id) {
		// TODO Auto-generated method stub
		return providerDao.getProviderById(id);
	}
	@Override
	public int modify(Provider provider) {
		// TODO Auto-generated method stub
		return providerDao.modify(provider);
	}
	@Override
	public int getProviderCount(String proName, String proCode) {
		// TODO Auto-generated method stub
		return providerDao.getProviderCount(proName, proCode);
	}
	@Override
	public List<Provider> getAllProviderName() {
		// TODO Auto-generated method stub
		return providerDao.getAllProviderName();
	}
	




}
