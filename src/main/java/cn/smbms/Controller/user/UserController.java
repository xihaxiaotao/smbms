package cn.smbms.Controller.user;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mysql.jdbc.StringUtils;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
	private  UserService  userService;
    
    @Autowired
	private  RoleService  roleService;
    //登录页面
    @RequestMapping(value="/tologin",method=RequestMethod.GET)
    public  String  toLogin(){
    	return  "login";
    }
    //登录验证
    @RequestMapping("/login")
    public String login(String userCode,String userPassword,HttpSession session,HttpServletRequest request){
    	User user=userService.login(userCode, userPassword);
    	if(user != null){
    		String info="";
    		if(user.getUserCode().equals(userCode)){
        		if(user.getUserPassword().equals(userPassword)){
                    //登录成功，重定向
        			session.setAttribute("userSession", user);
        			info= "frame";
        		}else{
        			request.setAttribute("error", "用户名或密码不正确");
        		    info="login";	
        		}
        	}
    		return info;
    	}else {
    		//登录失败，转发
    		//System.out.println(111);
    		request.setAttribute("error", "用户名或密码不正确");
    		return  "login";
    	}

     }
     //注销
     @RequestMapping("/logout")
     public  String  logout(HttpSession session){
    	 session.invalidate();
    	 return  "login";
     }
     //用户列表
     @RequestMapping("/getUserList")
     public  String  getUserList(String queryUserName,Integer queryUserRole,Integer currentPageNo,Integer pageSize,Model model,HttpServletRequest request){
    	 PageSupport  param=new PageSupport();
    	 if(currentPageNo==null){
    		 currentPageNo=1;
    	 }
    	 if(pageSize==null){
    		 pageSize=5;
    	 }
    	 if(queryUserRole==null){
    		 queryUserRole=null;
    	 }
    	 if(queryUserName==null){
    		 queryUserName=null;
    	 }
    	 List<User> userList=userService.getUserList(queryUserName, queryUserRole, (currentPageNo-1)*pageSize, pageSize);
         List<Role> roleList=roleService.getRoleList();
    	 param.setPageSize(5);
    	 param.setCurrentPageNo(currentPageNo);
    	 param.setTotalCount(userService.getUserCount(queryUserName, queryUserRole));
    	 
    	 model.addAttribute("userList",userList);
    	 model.addAttribute("roleList",roleList);
//    	 request.setAttribute("param",param);
    	 //分页跳转的路径
    	 request.setAttribute("pageUrl","getUserList" );
    	 request.setAttribute("queryUserName", queryUserName);
 		 request.setAttribute("queryUserRole", queryUserRole);
    	 request.setAttribute("totalPageCount", param.getTotalPageCount());
    	 request.setAttribute("totalCount", param.getTotalCount());
    	 request.setAttribute("currentPageNo", param.getCurrentPageNo());
    	 return "user/userlist";
     }
     //页面跳转到“修改密码”页面
     @RequestMapping("/toUpdatePwd")
     public String toUpdatePwd(){
    	 return "pwdmodify";
     }
     
     //检验旧密码是否正确
     @RequestMapping(value="/checkOldPwd",method=RequestMethod.GET)
     @ResponseBody
     public Object getPwdByUserId(@RequestParam String oldpassword,HttpSession session){
//    	 System.out.println(oldpassword);
    	 HashMap<String,String> resultMap=new HashMap<String,String>();
    	 User user=(User) session.getAttribute("userSession");
//    	 System.out.println();
    	 if(StringUtils.isNullOrEmpty(oldpassword)){
    		 resultMap.put("result", "error");
    	 }else if(!user.getUserPassword().equals(oldpassword)){
    		 resultMap.put("result", "false");
    	 }else if(user.getUserPassword().equals(oldpassword)){
    		 resultMap.put("result", "true");
    	 }
    	 
    	 return resultMap;
     }
     
     //修改密码
     @ResponseBody
     @RequestMapping("/updatePwd")
     public Object updatePwd(String newpassword,HttpSession session){
    	 User user=(User) session.getAttribute("userSession");
    	 int num=userService.updatePwd(user.getId(), newpassword);
    	 HashMap<String,String> resultMap=new HashMap<String,String>();
    	 if(num>0){
    		 resultMap.put("result", "1");
    	 }else{
    		 resultMap.put("result", "0");
    	 }
    	 return resultMap;
     }

     //获取所有用户角色
     @ResponseBody
     @RequestMapping(value="/getRoleList")
     public Object  getRoleList(){
    	 List<Role> roleList=roleService.getRoleList();
    	 return roleList;
     }
     
   //新增用户页面
     @RequestMapping("/toAddUser")
     public  String  toAdd(){
    	 return  "user/useradd";
     }
     
     //用户编码重名验证
     @ResponseBody
     @RequestMapping(value="/checkUserCode")
     public  Object  checkCode(String userCode){
    	 int num=userService.checkUserCode(userCode);
    	 HashMap<String,String> resultMap=new HashMap<String,String>();
    	 if(num>0){
    		 resultMap.put("userCode", "exist");
    	 }else{
    		 resultMap.put("userCode", "noexist");
    	 }
    	 return resultMap;
     }
     
     //查看用户详情信息
     @ResponseBody
     @RequestMapping("/getUserInfo")
     public  Object  getUserInfo(String id){
    	 User user=userService.getUserById(id);
    	 return  user;
     }
     
     
     //页面跳转到修改用户页面
     @RequestMapping("/toUpdateUser")
     public  String toUpdateUser(String id,Model model){
    	 User user=userService.getUserById(id);
    	 model.addAttribute("user", user);
    	 return  "user/usermodify";
     }
     
     //修改用户信息
     @RequestMapping("/updateUser")
     public String updateUser(User user,HttpSession session){
    	 System.out.println("userid:"+user.getId());
    	 int num=userService.modify(user);
    	 if(num>0){
			   return  "redirect:/user/getUserList";
		   }else{
			   return  "user/usermodify";
		   }
     }
     
     //删除用户
     @ResponseBody
     @RequestMapping("/delUser")
     public Object  delUser(Integer id){
    	 HashMap<String,String> resultMap=new HashMap<String,String>();
    	 int num=userService.deleteUserById(id);
    	 if(num>0){
			   resultMap.put("delResult", "true");
		 }else{
			   resultMap.put("delResult", "false");
		 }
		 return  resultMap;
     }
     
	//添加用户
     @RequestMapping("/addUser")
     public String  addUser(User user){
    	 int num=userService.add(user);
    	 if(num>0){
			   return  "redirect:/user/getUserList";
		   }else{
			   return  "user/useradd";
		   }
     }
}
