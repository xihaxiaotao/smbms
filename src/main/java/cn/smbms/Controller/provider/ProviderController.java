package cn.smbms.Controller.provider;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import cn.smbms.pojo.Provider;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/provider")
public class ProviderController {
    
	@Autowired
	private  ProviderService  providerService;
	
	@Autowired
	private  BillService  billService;
	//分页显示——供应商列表
	@RequestMapping("/getProviderList")
	public  String  getProviderList(String queryProCode,String queryProName,
			                        Integer currentPageNo,Integer pageSize,
			                        Model model,HttpServletRequest request){
   	 PageSupport  param=new PageSupport();
   	 if(currentPageNo==null){
   		 currentPageNo=1;
   	 }
   	 if(pageSize==null){
   		 pageSize=5;
   	 }
   	 if(queryProName==null){
   		queryProName=null;
   	 }
   	 if(queryProCode==null){
   		queryProCode=null;
   	 }
     List<Provider> providerList=providerService.getProviderList(queryProName, queryProCode,(currentPageNo-1)*pageSize, pageSize);
   	 param.setPageSize(5);
   	 param.setCurrentPageNo(currentPageNo);
   	 param.setTotalCount(providerService.getProviderCount(queryProName, queryProCode));
   	 
   	 model.addAttribute("providerList",providerList);
   	 request.setAttribute("pageUrl","getProviderList" );   	 //分页跳转的路径
	 request.setAttribute("queryProName", queryProName);
	 request.setAttribute("queryProCode", queryProCode);
   	 request.setAttribute("totalPageCount", param.getTotalPageCount());
   	 request.setAttribute("totalCount", param.getTotalCount());
   	 request.setAttribute("currentPageNo", param.getCurrentPageNo());
   	 return "provider/providerlist";
		
	}
	//页面跳转到“添加供应商”页面
	@RequestMapping(value="/toAddProvider",method=RequestMethod.GET)
	public String toAddProvider(@ModelAttribute("provider")Provider provider){
		return "provider/provideradd";
	}

	//新增供应商，JSR 303数据校验
 	@RequestMapping(value="/toAddProvider",method=RequestMethod.POST)
 	public String  addProvider(@Valid Provider provider,BindingResult bResult,@RequestParam(value="a_file")MultipartFile[]file,HttpSession session)throws Exception{
 		if(bResult.hasErrors()){
 			return "provider/provideradd";
 		}
 		//文件上传
 		//获取根路径
 		String  path=session.getServletContext().getRealPath("/statics/pic");	
 		for (int i = 0; i < file.length; i++) {
 			//获取原文件名
 			String  fileName=file[i].getOriginalFilename();
 			file[i].transferTo(new File(path+File.separator+fileName));
 		
 			if(i==0){
 				provider.setCompanyLicPicPath(path+File.separator+fileName);
 			}
 			if(i==1){
 				provider.setOrgCodePicPath(path+File.separator+fileName);
 			}
		}
 		
 		provider.setCreatedBy(1);
 		provider.setCreationDate(new Date());
 		if(providerService.add(provider)>0){
 			return  "redirect:/provider/getProviderList";
 		}
 		return  "provider/provideradd";
 	}
 	
 
	//查看供应商详情,REST风格访问
	@RequestMapping(value="/toProviderInfo/{id}")
	public String toProviderInfo(@PathVariable String id,HttpServletRequest request){
		Provider pro=providerService.getProviderById(id);
		request.setAttribute("provider", pro);
		return "provider/providerview";
	}
	
	//页面跳转到“修改供应商”页面
	@RequestMapping(value="/toUpdateProvider/{id}")
	public String toUpdateProvider(@PathVariable String id,HttpServletRequest request){
		Provider pro=providerService.getProviderById(id);
		request.setAttribute("provider", pro);
		return "provider/providermodify";
	}
	
	//修改供应商信息
	@RequestMapping("/updateProvider")
	public String updateProvider(String id,Provider provider){
		System.out.println("=====id====="+provider.getId());
		provider.setModifyDate(new Date());
		if(providerService.modify(provider)>0){
			return "redirect:/provider/getProviderList";
		}
		return "redirect:/provider/toUpdateProvider/"+provider.getId();
	}
	
	//删除供应商
	@ResponseBody
	@RequestMapping("/delProvider")
	public Object  delProvider(String proid){
		//如果供应商下有订单，先删除订单记录，再删供应商
//		System.out.println("供应商id"+proid);
		HashMap<String,String> resultMap=new HashMap<String,String>();
		int num=billService.deleteBillById(null,proid);
		int num2=0;
		if(num==0||num>0){
			 num2=providerService.deleteProviderById(proid);
//			 System.out.println("num1:"+num+"\tnum2:"+num2);
			 if(num2>0){
				 resultMap.put("delResult", "true");
			 }else{
				 resultMap.put("delResult", "false");
			 }
		}
		return resultMap;
	}
	
	
}
