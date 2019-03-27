package cn.smbms.service.user;

import java.util.List;

import cn.smbms.pojo.User;

public interface UserService {
	/**
	 * 增加用户信息
	 */
	public int add(User user);
	
	/**
	 * 用户登录
	 */
	public User login(String userCode,String userPassword);
	
	/**
	 * 根据条件查询用户列表
	 */
	public List<User> getUserList(String queryUserName,Integer queryUserRole,Integer currentPageNo, Integer pageSize);
	/**
	 * 根据条件查询用户表记录数
	 */
	public int getUserCount(String queryUserName,Integer queryUserRole);
	
	/**
	 * 根据userCode查询出User
	 */
	public User selectUserCodeExist(String userCode);
	
	/**
	 * 根据ID删除user
	 */
	public int deleteUserById(Integer delId);
	
	/**
	 * 根据ID查找user
	 */
	public User getUserById(String id);
	
	/**
	 * 修改用户信息
	 */
	public int modify(User user);
	
	/**
	 * 根据userId修改密码
	 */
	public int updatePwd(int id,String pwd);
	
	
	//用户编号重名验证
	public  int  checkUserCode(String userCode);
}
