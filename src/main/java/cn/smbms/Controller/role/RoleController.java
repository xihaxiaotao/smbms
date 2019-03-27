package cn.smbms.Controller.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.smbms.service.role.RoleService;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
	private  RoleService  roleService;
    
   
}
