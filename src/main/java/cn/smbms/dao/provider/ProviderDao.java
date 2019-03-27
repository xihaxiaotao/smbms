package cn.smbms.dao.provider;

import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Provider;

public interface ProviderDao {
	
	/**
	 * 增加供应商
	 */
	public int add(Provider provider);


	/**
	 * 通过供应商名称、编码获取供应商列表-模糊查询-providerList
	 */
	public List<Provider> getProviderList(@Param("proName")String proName,@Param("proCode")String proCode,
			                              @Param("currentPageNo")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	
	/**
	 * 通过proId删除Provider
	 */
	public int deleteProviderById(String delId); 
	
	
	/**
	 * 通过proId获取Provider
	 */
	public Provider getProviderById(String id); 
	
	/**
	 * 修改供应商信息
	 */
	public int modify(Provider provider);
	
    //查询总记录数
	public int getProviderCount(@Param("proName")String proName,@Param("proCode") String proCode);
	
	//查询所有的供应商名称
	public List<Provider>  getAllProviderName();
}
