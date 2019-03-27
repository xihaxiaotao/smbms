package cn.smbms.service.provider;

import java.util.List;

import cn.smbms.pojo.Provider;

public interface ProviderService {
	/**
	 * 增加供应商
	 */
	public int add(Provider provider);


	/**
	 * 通过供应商名称、编码获取供应商列表-模糊查询-providerList
	 */
	public List<Provider> getProviderList(String proName,String proCode,Integer currentPageNo,Integer pageSize);
	
	/**
	 * 通过proId删除Provider
	 */
	public int deleteProviderById(String delId);
	
	
	/**
	 * 通过proId获取Provider
	 */
	public Provider getProviderById(String id);
	
	/**
	 * 修改用户信息
	 */
	public int modify(Provider provider);

    //查询总记录数
	public int getProviderCount(String proName, String proCode);
	
	//查询所有的供应商名称
	public List<Provider>  getAllProviderName();
}
