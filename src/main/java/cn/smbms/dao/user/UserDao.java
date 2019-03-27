package cn.smbms.dao.user;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.User;

public interface UserDao {
	/**
	 * 增加用户信息
	 */
	public int add(User user);

	/**
	 * 通过userCode获取User
	 */
	public User getLoginUser(String userCode);

	/**
	 * 通过条件查询-userList
	 */
	public List<User> getUserList(@Param("userName")String userName,
			@Param("userRole")Integer userRole,
			@Param("currentPageNo")Integer currentPageNo,
			@Param("pageSize")Integer pageSize);
	/**
	 * 通过条件查询-用户表记录数
	 */
	public int getUserCount(@Param("userName")String userName,@Param("userRole")Integer userRole);
	
	/**
	 * 通过userId删除user
	 */
	public int deleteUserById(Integer delId); 
	
	
	/**
	 * 通过userId获取user
	 */
	public User getUserById(String id); 
	
	/**
	 * 修改用户信息
	 */
	public int modify(User user);
	
	
	/**
	 * 修改当前用户密码
	 */
	public int updatePwd(@Param("id")int id,@Param("pwd")String pwd);
	
	//用户编号重名验证
	public  int  checkUserCode(String userCode);
}
