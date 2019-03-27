package cn.smbms.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.dao.user.UserDao;
import cn.smbms.pojo.User;

/**
 * service层捕获异常，进行事务处理
 * 事务处理：调用不同dao的多个方法，必须使用同一个connection（connection作为参数传递）
 * 事务完成之后，需要在service层进行connection的关闭，在dao层关闭（PreparedStatement和ResultSet对象）
 * @author Administrator
 *
 */
@Service("UserService")
public class UserServiceImpl implements UserService{

	@Autowired
	private  UserDao  userDao;
	
    //增加用户信息
	@Override
	public int add(User user){
		return userDao.add(user);
	}
	
	//用户登录
	@Override
	public User login(String userCode, String userPassword) {
		return userDao.getLoginUser(userCode);
	}
	
	//根据条件查询用户列表
	@Override
	public List<User> getUserList(String queryUserName, Integer queryUserRole,Integer currentPageNo, Integer pageSize) {
		return userDao.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
	}
	
	//根据条件查询用户表记录数
	@Override
	public int getUserCount(String queryUserName, Integer queryUserRole) {
		return userDao.getUserCount(queryUserName, queryUserRole);
	}
	
	//根据userCode查询出User
	@Override
	public User selectUserCodeExist(String userCode) {
		return userDao.getLoginUser(userCode);
	}
	
	//根据ID删除user
	@Override
	public int deleteUserById(Integer delId) {
		return userDao.deleteUserById(delId);
	}
	
	//根据ID查找user
	@Override
	public User getUserById(String id) {
		return userDao.getUserById(id);
	}
	
	//修改用户信息
	@Override
	public int modify(User user) {
		return userDao.modify(user);
	}
	
	//根据userId修改密码
	@Override
	public int updatePwd(int id, String pwd) {
		return userDao.updatePwd(id, pwd);
	}

	@Override
	public int checkUserCode(String userCode) {
		// TODO Auto-generated method stub
		return userDao.checkUserCode(userCode);
	}
	
	
	
}
