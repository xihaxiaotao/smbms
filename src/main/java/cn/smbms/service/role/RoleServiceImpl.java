package cn.smbms.service.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.dao.role.RoleDao;
import cn.smbms.pojo.Role;
@Service("RoleService")
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> getRoleList() {
		// TODO Auto-generated method stub
		return roleDao.getRoleList();
	}
	
	
}
