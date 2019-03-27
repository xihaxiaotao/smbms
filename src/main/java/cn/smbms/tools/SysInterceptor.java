package cn.smbms.tools;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SysInterceptor extends HandlerInterceptorAdapter{
	  public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)throws Exception{
		  if(request.getSession().getAttribute("userSession")==null){
			  response.sendRedirect(request.getContextPath()+"/401.jsp");
			  return  false;
		  }else{
			  return  true;
		  }
	  }
}
